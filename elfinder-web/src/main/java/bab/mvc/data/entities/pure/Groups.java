package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "groups")
public class Groups {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gid")
	private int gid;
	
	@Column(name = "gname")
	private String gname;
	
	@Column(name = "gdescription")
	private String gdescription;

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGdescription() {
		return gdescription;
	}

	public void setGdescription(String gdescription) {
		this.gdescription = gdescription;
	}

	public Groups() {
		//super();
	}

	public Groups(String gname, String gdescription) {
		super();
		this.gname = gname;
		this.gdescription = gdescription;
	}

	@Override
	public String toString() {
		return "Group [gid=" + gid + ", gname=" + gname + ", gdescription="
				+ gdescription + "]";
	}


}
