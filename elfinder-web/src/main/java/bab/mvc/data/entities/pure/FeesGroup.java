package bab.mvc.data.entities.pure;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import java.util.Date;

@Entity
@Table (name = "fees_group")
public class FeesGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fgid")
	private int fgid;
	
	@Column(name = "fgname")
	private String fgname;
	
	@Column(name = "uid")
	private int uid;
		
	@Column(name = "charge")
	private double charge;
	
	@Column(name = "fgdescription")
	private String fgdescription;
	//@Column(name = "startTime", columnDefinition="DATETIME")
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date startTime;
	
	@Column(name = "active")
	private int active;
	
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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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
	
	
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public FeesGroup() {
		//super();
	}

	public FeesGroup(String fgname, int uid, double charge, String fgdescription, int active) {
		super();
		this.fgname = fgname;
		this.uid = uid;
		this.charge = charge;
		this.fgdescription = fgdescription;
		this.active = active;
	}

	@Override
	public String toString() {
		return "FeesGroup [fgid=" + fgid + ", fgname=" + fgname + ", uid=" + uid + ", charge=" + charge
				+ ", fgdescription=" + fgdescription + ", active=" + active + "]";
	}

		
}
