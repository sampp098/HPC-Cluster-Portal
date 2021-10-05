package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "fees_group_users")
public class FeesGroupUsers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fguid")
	private int fguid;
	
	@Column(name = "fgid")
	private int fgid;
	
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "state")
	private int state;
	
	@Column(name = "storage")
	private int storage;

	@Column(name = "start", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	
	@Column(name = "end", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;

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

	
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public FeesGroupUsers() {
		
	}
	
	public FeesGroupUsers(int fgid, int uid, int state, int storage, Date start, Date end) {
		super();
		this.fgid = fgid;
		this.uid = uid;
		this.state = state;
		this.storage = storage;
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "FeesGroupUsers [fguid=" + fguid + ", fgid=" + fgid + ", uid=" + uid + ", state=" + state + ", storage="
				+ storage + ", start=" + start + ", end=" + end + "]";
	}

	
	
	
}
