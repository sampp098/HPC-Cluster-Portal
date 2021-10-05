package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "permissions")
public class Permissions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pid")
	private int pid;
	
	@Column(name = "pname")
	private String pname;
	
	@Column(name = "pdescription")
	private String pdescription;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdescription() {
		return pdescription;
	}

	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}

	public Permissions() {
		//super();
	}

	public Permissions(String pname, String pdescription) {
		super();
		this.pname = pname;
		this.pdescription = pdescription;
	}

	@Override
	public String toString() {
		return "Permissions [pid=" + pid + ", pname=" + pname
				+ ", pdescription=" + pdescription + "]";
	}


	

}
