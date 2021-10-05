package bab.mvc.data.entities.pure;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "jobs")
public class Jobs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jid")
	private int jid;
	
	@Column(name = "jname")
	private String jname;
	
	@Column(name = "appname")
	private String appname;
	
	
	@Column(name = "uid")
	private int uid;
	
	
	@Column(name = "nodenum")
	private int nodenum;
	
	@Column(name = "corenum")
	private int corenum;
	
	@Column(name = "memnum")
	private int memnum;

	@Column(name = "jobid")
	private int jobid;

	@Column(name = "queue")
	private String queue;
	
	//@Column(name = "walltime", columnDefinition="DATETIME")
	@Column(name = "walltime")
	//@Temporal(TemporalType.TIMESTAMP)
	private int walltime;

	@Column(name = "hpctid")
	private int hpctid;
	
	@Column(name = "fgid")
	private int fgid;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "cputime")
	private double cputime;
	
	
	@Column(name = "createdate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;


	public Jobs() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Jobs(String jname, String appname, int uid, int nodenum, int corenum, int memnum, int jobid, String queue,
			int walltime, int hpctid, int fgid, String state, double cputime, Date createDate) {
		super();
		this.jname = jname;
		this.appname = appname;
		this.uid = uid;
		this.nodenum = nodenum;
		this.corenum = corenum;
		this.memnum = memnum;
		this.jobid = jobid;
		this.queue = queue;
		this.walltime = walltime;
		this.hpctid = hpctid;
		this.fgid = fgid;
		this.state = state;
		this.cputime = cputime;
		this.createDate = createDate;
	}


	@Override
	public String toString() {
		return "Jobs [jid=" + jid + ", jname=" + jname + ", appname=" + appname + ", uid=" + uid + ", nodenum="
				+ nodenum + ", corenum=" + corenum + ", memnum=" + memnum + ", jobid=" + jobid + ", queue=" + queue
				+ ", walltime=" + walltime + ", hpctid=" + hpctid + ", fgid=" + fgid + ", state=" + state + ", cputime="
				+ cputime + ", createDate=" + createDate + "]";
	}


	public int getJid() {
		return jid;
	}


	public void setJid(int jid) {
		this.jid = jid;
	}


	public String getJname() {
		return jname;
	}


	public void setJname(String jname) {
		this.jname = jname;
	}


	public String getAppname() {
		return appname;
	}


	public void setAppname(String appname) {
		this.appname = appname;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public int getNodenum() {
		return nodenum;
	}


	public void setNodenum(int nodenum) {
		this.nodenum = nodenum;
	}


	public int getCorenum() {
		return corenum;
	}


	public void setCorenum(int corenum) {
		this.corenum = corenum;
	}


	public int getMemnum() {
		return memnum;
	}


	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}


	public int getJobid() {
		return jobid;
	}


	public void setJobid(int jobid) {
		this.jobid = jobid;
	}


	public String getQueue() {
		return queue;
	}


	public void setQueue(String queue) {
		this.queue = queue;
	}


	public int getWalltime() {
		return walltime;
	}


	public void setWalltime(int walltime) {
		this.walltime = walltime;
	}


	public int getHpctid() {
		return hpctid;
	}


	public void setHpctid(int hpctid) {
		this.hpctid = hpctid;
	}


	public int getFgid() {
		return fgid;
	}


	public void setFgid(int fgid) {
		this.fgid = fgid;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public double getCputime() {
		return cputime;
	}


	public void setCputime(double cputime) {
		this.cputime = cputime;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
		
}
