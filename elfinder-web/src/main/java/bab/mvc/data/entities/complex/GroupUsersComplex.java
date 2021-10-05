package bab.mvc.data.entities.complex;

import javax.persistence.Entity;
import javax.persistence.Table;

import bab.mvc.data.entities.pure.Groups;
import bab.mvc.data.entities.pure.User;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "group_users")
public class GroupUsersComplex {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "guid")
	private int guid;

	
	@Column(name = "uid")
	private int uid;

	@OneToOne(optional=false)
	@JoinColumn(name="gid", referencedColumnName="gid")
	private Groups group;
	
	public GroupUsersComplex() {
		
	}

	public GroupUsersComplex(int uid, Groups group) {
		super();
		this.uid = uid;
		this.group = group;
	}

	@Override
	public String toString() {
		return "GroupUsersComplex [guid=" + guid + ", uid=" + uid + ", groupname=" + group + "]";
	}

	public int getGuid() {
		return guid;
	}

	public void setGuid(int guid) {
		this.guid = guid;
	}


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups gname) {
		this.group = gname;
	}
	
	

}
