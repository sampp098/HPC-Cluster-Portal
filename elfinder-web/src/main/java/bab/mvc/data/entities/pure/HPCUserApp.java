package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "hpcuserapp")
public class HPCUserApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hpcuappid")
	private int hpcuappid;
	
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "appid")
	private int appid;

	public int getHpcuappid() {
		return hpcuappid;
	}

	public void setHpcuappid(int hpcuappid) {
		this.hpcuappid = hpcuappid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public HPCUserApp() {
		//super();
	}

	public HPCUserApp(int uid, int appid) {
		super();
		this.uid = uid;
		this.appid = appid;
	}

	@Override
	public String toString() {
		return "Hpcuserapp [hpcuappid=" + hpcuappid + ", uid=" + uid
				+ ", appid=" + appid + "]";
	}


	

}
