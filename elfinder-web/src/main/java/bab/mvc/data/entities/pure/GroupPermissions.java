package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "group_permissions")
public class GroupPermissions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpid")
	private int gpid;
	
	@Column(name = "gid")
	private int gid;
	
	@Column(name = "pid")
	private int pid;

	public int getGpid() {
		return gpid;
	}

	public void setGpid(int gpid) {
		this.gpid = gpid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public GroupPermissions() {
		//super();
	}

	public GroupPermissions(int gid, int pid) {
		super();
		this.gid = gid;
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "GroupPermissions [gpid=" + gpid + ", gid=" + gid + ", pid="
				+ pid + "]";
	}


	

}
