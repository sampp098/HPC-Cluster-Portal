package bab.mvc.data.ssh;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.jcraft.jsch.JSchException;

import bab.mvc.Execute;
import bab.mvc.data.entities.complex.GroupJobsComplex;
import bab.mvc.data.entities.complex.JobsComplex;
import bab.mvc.data.entities.pure.FeesGroup;
import bab.mvc.data.entities.pure.Jobs;
import bab.mvc.data.services.JobsService;

public class Qstat {
	
	public static final String cmd="ssh root@"+Execute.host+" qstat -f :jobid | grep \""
			+ "euser"+"\\|"
			+ "Job_Name"+"\\|"
			+ "resources_used.cput"+"\\|"
			+ "resources_used.mem"+"\\|"
			+ "resources_used.walltime"+"\\|"
			+ "queue"+"\\|"
			+ "job_state"+"\\|"
			+ "ctime"+"\\|"
			+ "Error_Path"+"\\|"
			+ "Output_Path"+"\\|"
			+ "mtime"+"\\|"
			+ "qtime"+"\\|"
			+ "Resource_List.mem"+"\\|"
			+ "Resource_List.neednodes"+"\\|"
			+ "Resource_List.neednodes"+"\\|"
			+ "Resource_List.nodes"+"\\|"
			+ "Resource_List.nodes"+"\\|"
			+ "euser"+"\\|"
			+ "etime"+"\\|"
			+ "submit_args"+"\\|"
			+ "comp_time"+"\\|"
			+ "submit_host"+"\\|"
			+ "init_work_dir"
			+ "\"";
	
	public static String testQstat(int jobid) {
		
		try {
			return new Execute().sshExe(Execute.host, Execute.username,Execute.pass, cmd.replaceAll(":jobid", ""+jobid)).replaceAll("$PBS_JOBID", ""+jobid);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "err";
	}
	
	public static Map<Integer, Properties> testQstat(List<Integer> ints) {
		
		//List<Properties> properties=new ArrayList<Properties>();
		Map<Integer, Properties> mps = new HashMap<Integer, Properties>();
		List<String> cmds=new ArrayList<String>();
		for (int i : ints) {
			cmds.add(cmd.replaceAll(":jobid", ""+i));
		}
		
		try {
			List<String> outs=new Execute().sshExe(Execute.host, Execute.username, Execute.pass, cmds);
			System.out.println("------------------------------------------------------------");
			for (int i = 0; i < outs.size(); i++) {
				System.out.println("joid= "+ints.get(i)+"\n"+outs.get(i));
				Properties p =parsePropertiesString("joid= "+ints.get(i)+"\n"+outs.get(i));
				mps.put(ints.get(i), p);
			}
			
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mps;
	}
	
	public static List<Integer> getAvailableJoIds() {
		List<Integer> jobids=new ArrayList<>();
		try {
			String[] lines=new Execute().sshExe(Execute.host, Execute.username,Execute.pass, "ssh root@"+Execute.host+" qstat").split("[\n]");
			for (int i=2;i<lines.length; i++) {
				String jobid=lines[i].split("[.]")[0];
				jobids.add(Integer.parseInt(jobid ));
			}

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobids;
	}
	
	public static List<Integer> getAvailableUserJoIds(String username, String pass) {
		List<Integer> jobids=new ArrayList<>();
		try {
			String[] lines=new Execute().sshExe(Execute.host, username,pass,"qstat").split("[\n]");
			for (int i=2;i<lines.length; i++) {
				String jobid=lines[i].split("[.]")[0];
				jobids.add(Integer.parseInt(jobid ));
			}

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobids;
	}
	
	public static Properties parsePropertiesString(String s) throws IOException {
        final Properties p = new Properties();
        p.load(new StringReader(s));
        return p;
    }
	
	
	public static void applyMonitorAll() {
		
		System.out.println("---------applyMonitor-------");
		// get available Jobs from Qstat
		List<Integer> input = Qstat.getAvailableJoIds();
		
		if(input.size()<=0) {
			return;
		}
		
		// jobs from Qstat -f
		Map<Integer, Properties> qstatJobs = Qstat.testQstat(input);
		
		System.out.println("---------applyMonitor-------qstatJobs:"+qstatJobs.size());
		// Jobs from DB
		List<GroupJobsComplex> lgJobs = new JobsService().ReadGroupJobsNotC(input);

		// -----getting changes

		List<Jobs> newJobs = new ArrayList<Jobs>();
		List<FeesGroup> newGroups = new ArrayList<FeesGroup>();
		
		System.out.println("---------applyMonitor-------qstatJobs:"+qstatJobs.size()+"     lgJobs:"+lgJobs.size());
		
		//Existing in DB
		for (int i = 0; i < lgJobs.size(); i++) {
			
			GroupJobsComplex gjobs = lgJobs.get(i);
			Set<JobsComplex> jobs = gjobs.getJobs();
			System.out.println("-----------------groupname-----"+gjobs.getFgname());
			// get selected group as object
			FeesGroup fg = new FeesGroup(gjobs.getFgname(), gjobs.getOwner().getUid(), gjobs.getCharge(),
					gjobs.getFgdescription(),gjobs.getActive());
				fg.setFgid(gjobs.getFgid());
			for (JobsComplex jDB : jobs) {
				Jobs newJob;
				
				int jobid = jDB.getJobid();
				Properties jQS = qstatJobs.get(jobid);
				
				double lastCpuTime = jDB.getCputime();
				
				double newCpuTime = 0.0;
				double newMemUsed = 0.0;
				double payment=0.0;
				String state="Q";
				try {

					state = jQS.getProperty("job_state");
					if (state.equals("R")) {
						if (jQS.getProperty("resources_used.cput") != null) {
							String[] cpuUsed = jQS.getProperty("resources_used.cput").split("[:]");

							if (cpuUsed.length >= 2) {
								newCpuTime = Double.parseDouble(cpuUsed[0]) + (Double.parseDouble(cpuUsed[1]) / 60)
										+ (Double.parseDouble(cpuUsed[2]) / 3600);
							}
							if (newCpuTime > lastCpuTime) {
								payment = (newCpuTime - lastCpuTime) * jDB.getTariff().getCorecost();
								fg.setCharge(fg.getCharge() - payment);
							}
							newJob = new Jobs(jDB.getJname(), jDB.getAppname(), jDB.getUser().getUid(),
									jDB.getNodenum(), jDB.getCorenum(), jDB.getMemnum(), jobid,
									jDB.getTariff().getName(), jDB.getWalltime(), jDB.getTariff().getHpctid(),
									jDB.getFgid(), state, newCpuTime, jDB.getCreateDate());
							newJob.setJid(jDB.getJid());
							newJobs.add(newJob);
						} else {
							newJob = new Jobs(jDB.getJname(), jDB.getAppname(), jDB.getUser().getUid(),
									jDB.getNodenum(), jDB.getCorenum(), jDB.getMemnum(), jobid,
									jDB.getTariff().getName(), jDB.getWalltime(), jDB.getTariff().getHpctid(),
									jDB.getFgid(), state, 0.0, jDB.getCreateDate());
							newJob.setJid(jDB.getJid());
							newJobs.add(newJob);
						}

					} else if (state.equals("C")) {
						
						if (jQS.getProperty("resources_used.cput") != null) {
							// pars with kB to GB conversion
							newMemUsed = Double.parseDouble(jQS.getProperty("resources_used.mem").replaceAll("kb", ""))
									/ 1024 / 1024;
							String[] cpuUsed = jQS.getProperty("resources_used.cput").split("[:]");

							if (cpuUsed.length >= 2) {
								newCpuTime = Double.parseDouble(cpuUsed[0]) + (Double.parseDouble(cpuUsed[1]) / 60)
										+ (Double.parseDouble(cpuUsed[2]) / 3600);
							}

							if (newCpuTime > lastCpuTime) {
								payment = ((newCpuTime - lastCpuTime) * jDB.getTariff().getCorecost())
										+ (newCpuTime * newMemUsed * jDB.getTariff().getMemcost());
								fg.setCharge(fg.getCharge() - payment);
							}
							newJob = new Jobs(jDB.getJname(), jDB.getAppname(), jDB.getUser().getUid(),
									jDB.getNodenum(), jDB.getCorenum(), jDB.getMemnum(), jobid,
									jDB.getTariff().getName(), jDB.getWalltime(), jDB.getTariff().getHpctid(),
									jDB.getFgid(), state, newCpuTime, jDB.getCreateDate());
							newJob.setJid(jDB.getJid());
							newJobs.add(newJob);
						} else {
							newJob = new Jobs(jDB.getJname(), jDB.getAppname(), jDB.getUser().getUid(),
									jDB.getNodenum(), jDB.getCorenum(), jDB.getMemnum(), jobid,
									jDB.getTariff().getName(), jDB.getWalltime(), jDB.getTariff().getHpctid(),
									jDB.getFgid(), state, 0.0, jDB.getCreateDate());
							newJob.setJid(jDB.getJid());
							newJobs.add(newJob);
						}
					} else {
						qstatJobs.remove(jobid);
						continue;
					}

					
				} catch (Exception e) {
					System.err.println("-------@@@@@@@@EEEEERRRRRRRR--------------->Parsing Err");
				}

				// remove from QSTAT jobs Collection
				qstatJobs.remove(jobid);
			}
			
			newGroups.add(fg);

		}
		/*
		//Nod Existing in DB
		for(Map.Entry<Integer, Properties> jQS : qstatJobs.entrySet()) {
			int jobid=jQS.getKey();
			Properties p=jQS.getValue();
			String[] appname=p.getProperty("init_work_dir").split("[/]");
			String state=p.getProperty("job_state");
			Jobs j=new Jobs(
					p.getProperty("Job_Name"),
					appname[appname.length-1],
					1,//uid
					Integer.parseInt(p.getProperty("Resource_List.nodes").split("[:]")[0]),
					Integer.parseInt(p.getProperty("Resource_List.nodes").split("[=]")[1]),
					Integer.parseInt(p.getProperty("Resource_List.mem").replaceAll("gb", "")),
					jobid,
					p.getProperty("queue"),
					0,//walltime
					0,//hpctid
					1,//fgid
					state,
					0.0,
					new Date() //createDate
					);
			
		}*/
		
		//update
		new JobsService().updateQstat(newGroups, newJobs);
		
		//creates
		//new JobsService().???;
	}
	
	public static void applyMonitorUser(String username, String pass) {
		
		System.out.println("---------applyMonitor-------");
		// get available Jobs from Qstat
		List<Integer> input = Qstat.getAvailableUserJoIds(username, pass);
		
		if(input.size()<=0) {
			return;
		}
		
		// jobs from Qstat -f
		Map<Integer, Properties> qstatJobs = Qstat.testQstat(input);
		
		System.out.println("---------applyMonitor-------qstatJobs:"+qstatJobs.size());
		// Jobs from DB
		List<GroupJobsComplex> lgJobs = new JobsService().ReadGroupJobsNotC(input);

		// -----getting changes

		List<Jobs> newJobs = new ArrayList<Jobs>();
		List<FeesGroup> newGroups = new ArrayList<FeesGroup>();
		
		System.out.println("---------applyMonitor-------qstatJobs:"+qstatJobs.size()+"     lgJobs:"+lgJobs.size());
		
		//Existing in DB
		for (int i = 0; i < lgJobs.size(); i++) {
			
			GroupJobsComplex gjobs = lgJobs.get(i);
			Set<JobsComplex> jobs = gjobs.getJobs();
			System.out.println("-----------------groupname-----"+gjobs.getFgname());
			// get selected group as object
			FeesGroup fg = new FeesGroup(gjobs.getFgname(), gjobs.getOwner().getUid(), gjobs.getCharge(),
					gjobs.getFgdescription(), gjobs.getActive());
				fg.setFgid(gjobs.getFgid());
			for (JobsComplex jDB : jobs) {
				Jobs newJob;
				
				int jobid = jDB.getJobid();
				Properties jQS = qstatJobs.get(jobid);
				
				double lastCpuTime = jDB.getCputime();
				
				double newCpuTime = 0.0;
				double newMemUsed = 0.0;
				double payment=0.0;
				String state="Q";
				try {

					state = jQS.getProperty("job_state");
					if (state.equals("R")) {
						if (jQS.getProperty("resources_used.cput") != null) {
							String[] cpuUsed = jQS.getProperty("resources_used.cput").split("[:]");

							if (cpuUsed.length >= 2) {
								newCpuTime = Double.parseDouble(cpuUsed[0]) + (Double.parseDouble(cpuUsed[1]) / 60)
										+ (Double.parseDouble(cpuUsed[2]) / 3600);
							}
							if (newCpuTime > lastCpuTime) {
								payment = (newCpuTime - lastCpuTime) * jDB.getTariff().getCorecost();
								fg.setCharge(fg.getCharge() - payment);
							}
							newJob = new Jobs(jDB.getJname(), jDB.getAppname(), jDB.getUser().getUid(),
									jDB.getNodenum(), jDB.getCorenum(), jDB.getMemnum(), jobid,
									jDB.getTariff().getName(), jDB.getWalltime(), jDB.getTariff().getHpctid(),
									jDB.getFgid(), state, newCpuTime, jDB.getCreateDate());
							newJob.setJid(jDB.getJid());
							newJobs.add(newJob);
						} else {
							newJob = new Jobs(jDB.getJname(), jDB.getAppname(), jDB.getUser().getUid(),
									jDB.getNodenum(), jDB.getCorenum(), jDB.getMemnum(), jobid,
									jDB.getTariff().getName(), jDB.getWalltime(), jDB.getTariff().getHpctid(),
									jDB.getFgid(), state, 0.0, jDB.getCreateDate());
							newJob.setJid(jDB.getJid());
							newJobs.add(newJob);
						}

					} else if (state.equals("C")) {
						
						if (jQS.getProperty("resources_used.cput") != null) {
							// pars with kB to GB conversion
							newMemUsed = Double.parseDouble(jQS.getProperty("resources_used.mem").replaceAll("kb", ""))
									/ 1024 / 1024;
							String[] cpuUsed = jQS.getProperty("resources_used.cput").split("[:]");

							if (cpuUsed.length >= 2) {
								newCpuTime = Double.parseDouble(cpuUsed[0]) + (Double.parseDouble(cpuUsed[1]) / 60)
										+ (Double.parseDouble(cpuUsed[2]) / 3600);
							}

							if (newCpuTime > lastCpuTime) {
								payment = ((newCpuTime - lastCpuTime) * jDB.getTariff().getCorecost())
										+ (newCpuTime * newMemUsed * jDB.getTariff().getMemcost());
								fg.setCharge(fg.getCharge() - payment);
							}
							newJob = new Jobs(jDB.getJname(), jDB.getAppname(), jDB.getUser().getUid(),
									jDB.getNodenum(), jDB.getCorenum(), jDB.getMemnum(), jobid,
									jDB.getTariff().getName(), jDB.getWalltime(), jDB.getTariff().getHpctid(),
									jDB.getFgid(), state, newCpuTime, jDB.getCreateDate());
							newJob.setJid(jDB.getJid());
							newJobs.add(newJob);
						} else {
							newJob = new Jobs(jDB.getJname(), jDB.getAppname(), jDB.getUser().getUid(),
									jDB.getNodenum(), jDB.getCorenum(), jDB.getMemnum(), jobid,
									jDB.getTariff().getName(), jDB.getWalltime(), jDB.getTariff().getHpctid(),
									jDB.getFgid(), state, 0.0, jDB.getCreateDate());
							newJob.setJid(jDB.getJid());
							newJobs.add(newJob);
						}
					} else {
						qstatJobs.remove(jobid);
						continue;
					}

					
				} catch (Exception e) {
					System.err.println("-------@@@@@@@@EEEEERRRRRRRR--------------->Parsing Err");
				}

				// remove from QSTAT jobs Collection
				qstatJobs.remove(jobid);
			}
			
			newGroups.add(fg);

		}
		/*
		//Nod Existing in DB
		for(Map.Entry<Integer, Properties> jQS : qstatJobs.entrySet()) {
			int jobid=jQS.getKey();
			Properties p=jQS.getValue();
			String[] appname=p.getProperty("init_work_dir").split("[/]");
			String state=p.getProperty("job_state");
			Jobs j=new Jobs(
					p.getProperty("Job_Name"),
					appname[appname.length-1],
					1,//uid
					Integer.parseInt(p.getProperty("Resource_List.nodes").split("[:]")[0]),
					Integer.parseInt(p.getProperty("Resource_List.nodes").split("[=]")[1]),
					Integer.parseInt(p.getProperty("Resource_List.mem").replaceAll("gb", "")),
					jobid,
					p.getProperty("queue"),
					0,//walltime
					0,//hpctid
					1,//fgid
					state,
					0.0,
					new Date() //createDate
					);
			
		}*/
		
		//update
		new JobsService().updateQstat(newGroups, newJobs);
		
		//creates
		//new JobsService().???;
	}
}
