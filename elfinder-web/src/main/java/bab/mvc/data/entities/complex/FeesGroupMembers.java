package bab.mvc.data.entities.complex;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import bab.mvc.data.entities.pure.User;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//---------------------------------join
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@Table (name = "fees_group_users")
public class FeesGroupMembers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fguid")
	private int fguid;
	
	@Column(name = "fgid")
	private int fgid;
	
	@Column(name = "uid")
	private int uid;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="uid")

	private  Set<User> users;


	public int getFguid() {
		return fguid;
	}


	public void setFguid(int fguid) {
		this.fguid = fguid;
	}


	public int getFgid() {
		return fgid;
	}


	public void setFgid(int fgid) {
		this.fgid = fgid;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	@Override
	public String toString() {
		return "FeesGroupMembers [fguid=" + fguid + ", fgid=" + fgid + ", uid="
				+ uid + ", users=" + users + "]";
	}

}
