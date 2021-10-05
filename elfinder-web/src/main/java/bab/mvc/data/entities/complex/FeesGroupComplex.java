package bab.mvc.data.entities.complex;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import bab.mvc.data.entities.pure.User;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//---------------------------------join
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table (name = "fees_group")
public class FeesGroupComplex {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fgid")
	private int fgid;
	
	@Column(name = "fgname")
	private String fgname;
	
	@Column(name = "active")
	private int active;
	
	//@Column(name = "uid", nullable=false)
	//private int uid;
	
	
	//@Column(name = "fid")
	//private int fid;
	
	//@Column(name = "qid")
	//private int qid;
	
	@Column(name = "charge")
	private double charge;
	
	@Column(name = "fgdescription")
	private String fgdescription;
	
	
	@OneToOne(optional=false)
	@JoinColumn(name="uid")
	private User owner;

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

	//public int getUid() {
	//	return uid;
	//}

	//public void setUid(int uid) {
	//	this.uid = uid;
	//}

	/*public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}
	*/

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

	public FeesGroupComplex() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public FeesGroupComplex(String fgname, /*int uid, int fid, int qid,*/ double charge, String fgdescription, User owner, int active
			/*, Fees fee, Queue queue*/) {
		super();
		this.fgname = fgname;
		//this.uid = uid;
		//this.fid = fid;
		//this.qid = qid;
		this.charge = charge;
		this.fgdescription = fgdescription;
		this.owner = owner;
		this.active=active;
	}

	@Override
	public String toString() {
		return "FeesGroupComplex [fgid=" + fgid + ", fgname=" + fgname + ", active=" + active + ", charge=" + charge
				+ ", fgdescription=" + fgdescription + ", owner=" + owner + "]";
	}

	
	
	
	
	
}
