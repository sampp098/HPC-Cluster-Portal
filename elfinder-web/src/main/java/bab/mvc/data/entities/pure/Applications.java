package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "applications")
public class Applications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appid")
	private int appid;
	
	@Column(name = "appname")
	private String appname;
	
	@Column(name = "exports")
	private String exports;

	@Column(name = "scripts")
	private String scripts;
	
	@Column(name = "files")
	private String files;
	
	@Column(name = "active")
	private int active;
	
	
	public String getScripts() {
		return scripts;
	}

	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getExports() {
		return exports;
	}

	public void setExports(String exports) {
		this.exports = exports;
	}

	
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Applications() {
		//super();
	}

	public Applications(String appname, String exports, String scripts, String files, int active) {
		super();
		this.appname = appname;
		this.exports = exports;
		this.scripts = scripts;
		this.files = files;
		this.active = active;
	}

	@Override
	public String toString() {
		return "Applications [appid=" + appid + ", appname=" + appname + ", exports=" + exports + ", scripts=" + scripts
				+ ", files=" + files + ", active=" + active + "]";
	}

	

	

}
