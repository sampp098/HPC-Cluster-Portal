package bab.mvc.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.tika.Tika;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ChannelSftp.LsEntry;

import bab.mvc.Execute;
import bab.mvc.StaticData;
import bab.mvc.data.entities.complex.GroupJobsComplex;
import bab.mvc.data.entities.complex.JobsComplex;
import bab.mvc.data.entities.pure.Applications;
import bab.mvc.data.entities.pure.FeesGroup;
import bab.mvc.data.entities.pure.FeesGroupUsers;
import bab.mvc.data.entities.pure.HPCTariff;
import bab.mvc.data.entities.pure.Jobs;
import bab.mvc.data.entities.pure.User;
import bab.mvc.data.services.ApplicationsService;
import bab.mvc.data.services.FeesGroupService;
import bab.mvc.data.services.FeesGroupUsersService;
import bab.mvc.data.services.HPCTariffService;
import bab.mvc.data.services.JobsService;
import bab.mvc.data.services.PermissionsService;
import bab.mvc.data.services.UserService;
import bab.mvc.data.services.util.Files;
import bab.mvc.data.ssh.JobSSH;
import bab.mvc.data.ssh.Qstat;
import bab.mvc.data.ssh.UserSSH;


@Controller
@RequestMapping("/job")
public class JobController {
	
	
	@RequestMapping(value="/myjobs",method=RequestMethod.GET)
	public String jobslist(HttpSession session, Model model) {
		
		System.out.println("invoking jobslist MyJobs :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobmylist")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		User u=((User) session.getAttribute("currentuser"));
		Qstat.applyMonitorUser(u.getUserName(),u.getPass());
		List<JobsComplex> jobs= new JobsService().ReadByUidComplex(u.getUid(),"","0001-00-00","");
		model.addAttribute("jobs", jobs);
		String s=(String)session.getAttribute("success");
		if(s!=null) {
			model.addAttribute("success", s);
			session.removeAttribute("success");
		}
		return "jobslist";
	}
	
	
	@RequestMapping(value="/mygroupreport",method=RequestMethod.GET)
	public String groupReport(HttpSession session, Model model) {
		
		System.out.println("invoking jobslist MyJobs :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobreport2")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=((User) session.getAttribute("currentuser"));
		Qstat.applyMonitorUser(u.getUserName(),u.getPass());
		
		FeesGroup fg= new FeesGroupService().ReadByOwnerUid(u.getUid()).get(0);
		model.addAttribute("feesgroup", fg);
				
		GroupJobsComplex jobs= new JobsService().ReadGroupJobs(fg.getFgid());
		
		model.addAttribute("groupjobs", jobs);
		
		return "mygroupreport";
	}
	
	@RequestMapping(value="/userreport",method=RequestMethod.GET)
	public String userReport(HttpSession session, Model model) {
		
		System.out.println("invoking jobslist MyJobs :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobreport1")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=((User) session.getAttribute("currentuser"));
		Qstat.applyMonitorUser(u.getUserName(),u.getPass());
		
		FeesGroup fg= new FeesGroupService().ReadByMemberUid(u.getUid()).get(0);
		model.addAttribute("feesgroup", fg);
		
		List<HPCTariff> tariffs=new HPCTariffService().read();
		model.addAttribute("tariffs", tariffs);
		
		User owner=new UserService().getUserByUid(fg.getUid());
		model.addAttribute("owner",owner);
		
		List<Applications> apps =new ApplicationsService().ReadActives();
		model.addAttribute("applications", apps);
		
		List<JobsComplex> jobs= new JobsService().ReadByUidComplex(u.getUid(), "", "0001-00-00", "");
		model.addAttribute("jobs", jobs);
		
		return "userreport";
	}
	//userreportsearch
	
	@RequestMapping(value="/userreportsearch",method=RequestMethod.POST)
	public String userReport(HttpSession session, Model model, @RequestParam String appname, @RequestParam String state, String date) {
		
		System.out.println("invoking jobslist MyJobs :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobreport1")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=((User) session.getAttribute("currentuser"));
		Qstat.applyMonitorUser(u.getUserName(),u.getPass());
		
		FeesGroup fg= new FeesGroupService().ReadByMemberUid(u.getUid()).get(0);
		model.addAttribute("feesgroup", fg);
		
		List<HPCTariff> tariffs=new HPCTariffService().read();
		model.addAttribute("tariffs", tariffs);
		
		User owner=new UserService().getUserByUid(fg.getUid());
		model.addAttribute("owner",owner);
		
		List<Applications> apps =new ApplicationsService().ReadActives();
		model.addAttribute("applications", apps);
		String app=appname;
		String st=state;
		if(appname.equals("All")) {
			app="";
		}
		if(state.equals("All")) {
			st="";
		}
		
		List<JobsComplex> jobs= new JobsService().ReadByUidComplex(u.getUid(), app, date, st);
		model.addAttribute("jobs", jobs);
		
		return "userreport";
	}
	
	@RequestMapping(value="/myjobssearch",method=RequestMethod.POST)
	public String jobslistSearch(HttpSession session, HttpServletRequest request,
			 					@RequestParam String jname) {
		System.out.println("invoking myjobssearch MyJobs :POST ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobreport1")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		User u=((User) session.getAttribute("currentuser"));
		Qstat.applyMonitorUser(u.getUserName(),u.getPass());
		List<Jobs> jobs= new JobsService().ReadSearch(u.getUid(), jname);
		request.setAttribute("jobs", jobs);
		
		return "jobslist";
	}
	
	@RequestMapping(value="/attachfiles",method=RequestMethod.GET)
	public String attachfiles(HttpSession session, Model model) {
		
		System.out.println("invoking attachfiles attachfiles :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		int uid=((User) session.getAttribute("currentuser")).getUid();
		
		model.addAttribute("applications", new ApplicationsService().ReadActives());
		return "attachfiles";
	}
	
	@RequestMapping(value="/attachfiles",method=RequestMethod.POST)
	public String attachfiles(HttpSession session, Model model,RedirectAttributes redirectAttributes,HttpServletRequest request,@RequestParam int appid) {
		
		System.out.println("invoking attachfiles attachfiles :POST ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=(User) session.getAttribute("currentuser");
		
		Applications app =new ApplicationsService().ReadByAppidActives(appid).get(0);
		model.addAttribute("application", app);
		//if(button.equals("attach")) {
			try {
				if(new Files().uploadFilesToServer( request,app.getAppname(), u.getUserName().toLowerCase(), u.getPass())) {
					model.addAttribute("success", "File has been uploded successfully");
				}else {
					model.addAttribute("error", "Upload failed!");
				}
				
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		
			//String[] scripts=app.getScripts().split("::");
		String[] files=app.getFiles().split("::");
		int f=1;
		Vector<String> selectors=new Vector<String>();
		for (String fileExtention : files) {
			try {
				System.out.println("-------------------------->list files:" + Execute.homeDir
						+ u.getUserName().toLowerCase() + "/" + app.getAppname());
				Vector<LsEntry> ls = new Execute().listFiles(u.getUserName().toLowerCase(), u.getPass(),
						Execute.homeDir + u.getUserName().toLowerCase() + "/" + app.getAppname(), fileExtention);
				
				selectors.add(getFileSelector(ls, "file"+f++));
				System.out.println("--------------------> ls size:" + ls.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[] scripts=app.getScripts().split("::");
		List<String> reportScript=new ArrayList<String>();
		for (int i = 0; i <files.length ; i++) {
			for (int j=0;j<scripts.length;j++) {
				int count = (scripts[j].length() - scripts[j].replaceAll("file"+(i+1),"").length())/("file"+(i+1)).length();
				for(int k=0; k<count; k++) {
					reportScript.add("file: "+selectors.get(i));
				}
				scripts[j]=scripts[j].replaceAll("file"+(i+1), selectors.get(i));
			}
		}
		model.addAttribute("appscripts",reportScript);
		
		
		List<FeesGroupUsers> fgus=(new FeesGroupUsersService().ReadByUid(u.getUid()));
		
		
		if(fgus.size()>0) {
			int fgid=fgus.get(0).getFgid();
			FeesGroup fg=(FeesGroup) (new FeesGroupService().ReadByfgid(fgid).get(0));
			
			if(fg.getActive()!=1) {
				model.addAttribute("message", "Your group '"+fg.getFgname()+"' is suspended! please contact to your supervisor!");
				return "errorpage";
			}
			if(fg.getCharge()<0) {
				model.addAttribute("message", "Your group'"+fg.getFgname()+"' credit(Charge) is Zero! please contact to your supervisor!");
				return "errorpage";
			}
			if(fgus.get(0).getState()==1) {
				model.addAttribute("feesgroup",fg);
				
				User owner=new UserService().getUserByUid(fg.getUid());
				model.addAttribute("owner",owner);
		
			
				String[] str=new UserSSH().userGroups(u.getUserName()).split(",");
				List<String> groups = new ArrayList<String>();
				String strr="";
				for (String string : str) {
					groups.add(string);
					strr+=",'"+string.replace("\n", "")+"'";
				}
				model.addAttribute("tariff", new HPCTariffService().getUserTarrifList(strr.substring(1)));
				
				
				String script="";
				for (String s : app.getScripts().split("::")) {
					script+=s+"\n";
				}
				
				model.addAttribute("scripts",script);
			
				//redirectAttributes.addAllAttributes(request.getParameterMap());
			
				return "forward:/job/shownewjob";
			}
			model.addAttribute("message", "You are inactive member in group '"+fg.getFgname()+"'! please contact to your supervisor!");
			return "errorpage";
		}
		model.addAttribute("message", "You are not joined in an HPC Group yet! please contact to your admin or supervisor!");
		return "errorpage";
	}
	@RequestMapping(value="/skipattachfiles",method=RequestMethod.POST)
	public String skipattachfiles(HttpSession session, Model model,RedirectAttributes redirectAttributes,HttpServletRequest request,@RequestParam int appid) {
		
		System.out.println("invoking attachfiles attachfiles :POST ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=(User) session.getAttribute("currentuser");
		
		Applications app =new ApplicationsService().ReadByAppidActives(appid).get(0);
		model.addAttribute("application", app);
		
			//String[] scripts=app.getScripts().split("::");
		String[] files=app.getFiles().split("::");
		int f=1;
		Vector<String> selectors=new Vector<String>();
		for (String fileExtention : files) {
			try {
				System.out.println("-------------------------->list files:" + Execute.homeDir
						+ u.getUserName().toLowerCase() + "/" + app.getAppname());
				Vector<LsEntry> ls = new Execute().listFiles(u.getUserName().toLowerCase(), u.getPass(),
						Execute.homeDir + u.getUserName().toLowerCase() + "/" + app.getAppname(), fileExtention);
				
				selectors.add(getFileSelector(ls, "file"+f++));
				System.out.println("--------------------> ls size:" + ls.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[] scripts=app.getScripts().split("::");
		List<String> reportScript=new ArrayList<String>();
		for (int i = 0; i <files.length ; i++) {
			for (int j=0;j<scripts.length;j++) {
				int count = (scripts[j].length() - scripts[j].replaceAll("file"+(i+1),"").length())/("file"+(i+1)).length();
				for(int k=0; k<count; k++) {
					reportScript.add("file: "+selectors.get(i));
				}
				scripts[j]=scripts[j].replaceAll("file"+(i+1), selectors.get(i));
			}
		}
		model.addAttribute("appscripts",reportScript);
		
		
		List<FeesGroupUsers> fgus=(new FeesGroupUsersService().ReadByUid(u.getUid()));
		
		
		if(fgus.size()>0) {
			int fgid=fgus.get(0).getFgid();
			FeesGroup fg=(FeesGroup) (new FeesGroupService().ReadByfgid(fgid).get(0));
			if(fg.getActive()!=1) {
				model.addAttribute("message", "Your group '"+fg.getFgname()+"' is suspended! please contact to your supervisor!");
				return "errorpage";
			}
			if(fgus.get(0).getState()==1) {
				model.addAttribute("feesgroup",fg);
				
				User owner=new UserService().getUserByUid(fg.getUid());
				model.addAttribute("owner",owner);
		
				
				String[] str=new UserSSH().userGroups(u.getUserName()).split(",");
				List<String> groups = new ArrayList<String>();
				String strr="";
				for (String string : str) {
					groups.add(string);
					strr+=",'"+string.replace("\n", "")+"'";
				}
				model.addAttribute("tariff", new HPCTariffService().getUserTarrifList(strr.substring(1)));
				
				String script="";
				for (String s : app.getScripts().split("::")) {
					script+=s+"\n";
				}
				
				model.addAttribute("scripts",script);
			
				//redirectAttributes.addAllAttributes(request.getParameterMap());
			
				return "forward:/job/shownewjob";
			}
			model.addAttribute("message", "You are inactive member in group '"+fg.getFgname()+"'! please contact to your supervisor!");
			return "errorpage";
		}
		model.addAttribute("message", "You are not joined in an HPC Group yet! please contact to your admin or supervisor!");
		return "errorpage";
	}	
	
	@RequestMapping(value="/shownewjob",method=RequestMethod.POST)
	public String shownewjob(HttpSession session, Model model) {
		
		System.out.println("invoking attachfiles shownewjob :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		
		return "newjob";
	}
	
	@RequestMapping(value="/newjob",method=RequestMethod.POST)
	public String donewjob(HttpSession session, Model model,HttpServletRequest request,
			 @RequestParam String jname,
			 @RequestParam int appid,
			 @RequestParam int nodes,
			 @RequestParam int cores,
			 @RequestParam int memory,
			 //@RequestParam String mainfile,
			 @RequestParam int hour,
			 @RequestParam int hpctid,
			 @RequestParam(value = "advanced", required = false) String advanced,
			 @RequestParam(value = "editscripts", required = false) String editscripts) {
		
		System.out.println("invoking athentication user :post");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=((User) session.getAttribute("currentuser"));
		
		
		int fgid=((FeesGroupUsers) (new FeesGroupUsersService().ReadByUid(u.getUid()).get(0))).getFgid();

		FeesGroup fg=(FeesGroup) (new FeesGroupService().ReadByfgid(fgid).get(0));
		Applications app = new ApplicationsService().ReadByAppidActives(appid).get(0);
		
		HPCTariff tariff= new HPCTariffService().ReadByHPCTid(hpctid).get(0);
		
		String appHome=Execute.homeDir+u.getUserName().toLowerCase()+"/"+app.getAppname()+"/";
		//String file1=appHome+"/"+mainfile;
		String script="#!/bin/bash"
				+ "\n#PBS -l mem="+Math.min(memory, tariff.getMaxmemory())+"GB"
				+ "\n#PBS -l nodes="+Math.min(nodes, tariff.getMaxnodes())+":ppn="+Math.min(cores, tariff.getMaxcores())+""
				+ "\n#PBS -l walltime="+hour+":00:00"
				+ "\n#PBS -o "+appHome+"\\$PBS_JOBID.out"
				+ "\n#PBS -e "+appHome+"\\$PBS_JOBID.err"
				+ "\n#PBS -q "+tariff.getName()+""
				+ "\n#PBS -N "+jname+""
				+ "\ncd \\$PBS_O_WORKDIR"
				
				
				//+ "\nexport MFILE="+file1
				//+ "\nmatlab < \\$MFILE > \\$PBS_JOBID.log"
				;
		
		for(String export : app.getExports().split("::")) {
			script+="\n"+export;
		}
		String[] files=app.getFiles().split("::");
		String[] scripts=app.getScripts().split("::");
		
		if(advanced!=null && editscripts!=null ) {
			script+="\n"+editscripts;
		}else {
			for (int i = 0; i <files.length ; i++) {
				for (int j=0;j<scripts.length;j++) {
					String file=appHome+((String) request.getParameter("file"+(i+1)));
					System.out.println("--------------------> file selector param for "+"file"+(i+1)+":"+file);
					scripts[j]=scripts[j].replaceAll("file"+(i+1), file);
				}
			}
		
			for (String str : scripts) {
				script+="\n"+str;
			}
		}
		
		System.out.println("----------------------------->shell:\n"+script+"\n");
		
		int jobid =new JobSSH().JobAdd(u.getUserName().toLowerCase(), u.getPass(), jname,appHome, script);
		if(jobid!=-1) {
			System.out.println("----------------------------->qsub done!");
			new JobsService().create(new Jobs(jname, app.getAppname(), u.getUid(), nodes, cores, memory, jobid, tariff.getName(), hour, hpctid, fgid, "Q",0.0, new Date()));
			session.setAttribute("success","Job has been submited successfully.");
			return "redirect:/job/myjobs";
		}else {
			System.out.println("----------------------------->qsub failed!");
			model.addAttribute("warning", "Failed to submit your job");
			
			return "newjob";
		}
		
		
	}
	
	@RequestMapping(value="/editselected",method=RequestMethod.GET)
	public String editjob( Model model,HttpSession session, @RequestParam String appname) {
		System.out.println("invoking athentication user :post");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobdelete")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=((User) session.getAttribute("currentuser"));
		
		System.out.println("----------------------------!> listFiles(session, model, appname)");

		return listFiles(session, model, appname);

	}
	
	@RequestMapping(value="/deleteselected",method=RequestMethod.POST)
	public String deletejob( Model model,HttpSession session, @RequestParam String jobid) {
		System.out.println("invoking athentication user :post");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobdelete")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=((User) session.getAttribute("currentuser"));

			try {
				new Execute().sshExe(Execute.host, u.getUserName(), u.getPass(), "qdel "+jobid );
				
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		session.setAttribute("success","Job has been deleted successfully.");
		return "redirect:/job/myjobs";
	}
	
	private String getFileSelector(Vector<LsEntry> ls, String name) {
		
		String selector="<select class=\"round-input\" style=\"text-align:center;\" id=\"files\" name=\""+name+"\">";
		for (LsEntry files : ls) {
			selector+="\n	<option value=\""+files.getFilename()+"\" >";
			selector+="\n	"+files.getFilename();
			selector+="\n	</option>";
		}
		selector+="</select>";
		
		
		return selector;
	}
	
	
	//-------------------------------------------------------------------------filemanager
	
	
	@RequestMapping(value="/listfiles", method=RequestMethod.GET)
	public String listFiles(HttpSession session, Model model, @RequestParam String appname) {
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=((User) session.getAttribute("currentuser"));
		try {
			Vector<LsEntry> ls= new Execute().listFiles(u.getUserName(), u.getPass(), Execute.homeDir+u.getUserName()+"/"+appname, "*");
			model.addAttribute("files",ls);
			model.addAttribute("appname",appname);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listfiles";
	}
	
	@RequestMapping(value="/deletefile", method=RequestMethod.GET)
	public String deleteFile(HttpSession session, Model model, @RequestParam String filename, @RequestParam String appname) {
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=((User) session.getAttribute("currentuser"));
		try {
			
			new Execute().deleteFile(u.getUserName(), u.getPass(), Execute.homeDir+u.getUserName()+"/"+appname, filename);
			
			Vector<LsEntry> ls= new Execute().listFiles(u.getUserName(), u.getPass(), Execute.homeDir+u.getUserName()+"/"+appname, "*");
			model.addAttribute("files",ls);
			model.addAttribute("appname",appname);
			//for (LsEntry lsEntry : ls) {
			//	System.out.println("---------->"+lsEntry.getFilename());
			//}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/job/listfiles?appname="+appname;
	}
	@RequestMapping(value="/uploadfiles",method=RequestMethod.POST)
	public String uploadfiles(HttpSession session, Model model,HttpServletRequest request, @RequestParam String appname) {
		
		System.out.println("invoking attachfiles attachfiles :get ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=(User) session.getAttribute("currentuser");

			try {
				new Files().uploadFilesToServer( request,appname, u.getUserName().toLowerCase(), u.getPass());
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listFiles(session, model, appname);
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public void   getFile(HttpServletResponse response ,HttpSession session, @RequestParam String appname, @RequestParam String filename) {
			System.out.println("invoking in getFiles download ----------------------------------->app:"+appname+" filename: "+filename);
			if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
				session.removeAttribute("currentuser");
				session.removeAttribute("permisions");
				return;
			}
			User u = ((User) session.getAttribute("currentuser"));
			InputStream is;
			try {
		        
				new Files().downloadFilesFromServer(appname, u.getUserName(), u.getPass(), filename, response.getOutputStream());
				//response.setContentType("application/text");
				Tika tika = new Tika();
			    String contentType = tika.detect(filename);
			    System.out.println("------contentType:>"+contentType);
				response.setContentType(contentType);
				response.setHeader("Content-Disposition", "attachment; filename="+filename);
				//return resource;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@RequestMapping(value = "/filemanager2", method = RequestMethod.GET)
	public String   dest(Model model,HttpSession session, HttpServletRequest request) {
		System.out.println("Entered rm2 method ");
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		Map md = model.asMap();
		for (Object modelKey : md.keySet()) {
	        Object modelValue = md.get(modelKey);
	        System.out.println(modelKey + " -- " + modelValue);
	    }
		
		java.util.Enumeration<String> reqEnum = request.getParameterNames();
	    while (reqEnum.hasMoreElements()) {
	        String s = reqEnum.nextElement();
	        System.out.println(s);
	        System.out.println("==" + request.getParameter(s));
	    }
		return "redirect:/user/hpchome";
	}
			

	@RequestMapping(value ="/myfiles", method = RequestMethod.GET)
	public String  elfinder(HttpSession session ,Model model) {
		if(!PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobnew")) {
			session.removeAttribute("currentuser");
			session.removeAttribute("permisions");
			return "redirect:/user/login";
		}
		User u=(User)session.getAttribute("currentuser");
		if(u!=null) {
			if(PermissionsService.exist((List<String>) session.getAttribute("permisions"), "jobmylist")) {
				if(session.getAttribute("rootdir")!=null) {
					
					
				}else {
					String rootDir;
					try {
						rootDir = new Execute().sshExe(Execute.host, u.getUserName(), u.getPass(), "cd $HOME;pwd");
						session.setAttribute("rootdir", rootDir);
					} catch (JSchException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//elfinder
				return "home";
			}
			
			
		}
		session.removeAttribute("currentuser");
		session.removeAttribute("permisions");

		return "login";
	}
}
