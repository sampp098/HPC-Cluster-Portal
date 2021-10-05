package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "group_users")
public class GroupUsers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "guid")
	private int guid;
	
	@Column(name = "gid")
	private int gid;
	
	@Column(name = "uid")
	private int uid;

	public int getGuid() {
		return guid;
	}

	public void setGuid(int guid) {
		this.guid = guid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public GroupUsers() {
		//super();
	}

	public GroupUsers(int gid, int uid) {
		super();
		this.gid = gid;
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "GroupUsers [guid=" + guid + ", gid=" + gid + ", uid=" + uid
				+ "]";
	}


	
	

}
