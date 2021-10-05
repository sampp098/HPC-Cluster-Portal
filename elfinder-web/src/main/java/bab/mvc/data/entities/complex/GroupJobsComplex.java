package bab.mvc.data.entities.complex;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import bab.mvc.data.entities.pure.Jobs;
import bab.mvc.data.entities.pure.User;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//---------------------------------join
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table (name = "fees_group")
public class GroupJobsComplex {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fgid")
	private int fgid;
	
	@Column(name = "fgname")
	private String fgname;
	
	//@Column(name = "uid", nullable=false)
	//private int uid;
	
	
	//@Column(name = "fid")
	//private int fid;
	
	//@Column(name = "qid")
	//private int qid;
	
	@Column(name = "charge")
	private double charge;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "fgdescription")
	private String fgdescription;
	
	
	@OneToOne(optional=false)
	@JoinColumn(name="uid")
	private User owner;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="fgid")
	private Set<JobsComplex> jobs;

	public int getFgid() {
		return fgid;
	}

	public void setFgid(int fgid) {
		this.fgid = fgid;
	}

	public String getFgname() {
		return fgname;
	}

	public void setFgname(String fgname) {
		this.fgname = fgname;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public String getFgdescription() {
		return fgdescription;
	}

	public void setFgdescription(String fgdescription) {
		this.fgdescription = fgdescription;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<JobsComplex> getJobs() {
		return jobs;
	}

	public void setJobs(Set<JobsComplex> jobs) {
		this.jobs = jobs;
	}

	public GroupJobsComplex() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public GroupJobsComplex(String fgname, double charge, int active, String fgdescription, User owner, Set<JobsComplex> jobs) {
		super();
		this.fgname = fgname;
		this.charge = charge;
		this.fgdescription = fgdescription;
		this.owner = owner;
		this.jobs = jobs;
		this.active=active;
	}

	@Override
	public String toString() {
		return "GroupJobsComplex [fgid=" + fgid + ", fgname=" + fgname + ", charge=" + charge + ", active=" + active
				+ ", fgdescription=" + fgdescription + ", owner=" + owner + ", jobs=" + jobs + "]";
	}

	
}
