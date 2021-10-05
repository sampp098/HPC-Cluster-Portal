package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "hpcuserlimitation")
public class HPCUserLimitation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hpculid")
	private int hpculid;
	
	@Column(name = "hpclid")
	private int hpclid;
	
	@Column(name = "uid")
	private int uid;

	public int getHpculid() {
		return hpculid;
	}

	public void setHpculid(int hpculid) {
		this.hpculid = hpculid;
	}

	public int getHpclid() {
		return hpclid;
	}

	public void setHpclid(int hpclid) {
		this.hpclid = hpclid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public HPCUserLimitation() {
		//super();
	}

	public HPCUserLimitation(int hpclid, int uid) {
		super();
		this.hpclid = hpclid;
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Hpcuserlimitation [hpculid=" + hpculid + ", hpclid=" + hpclid
				+ ", uid=" + uid + "]";
	}


	

}
