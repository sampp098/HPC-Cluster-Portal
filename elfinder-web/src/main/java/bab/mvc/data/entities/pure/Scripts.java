package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "scripts")
public class Scripts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scid")
	private int scid;
	
	@Column(name = "scname")
	private String scname;
	
	@Column(name = "scdescription")
	private String scdescription;

	@Column(name = "script")
	private String script;

	public int getScid() {
		return scid;
	}

	public void setScid(int scid) {
		this.scid = scid;
	}

	public String getScname() {
		return scname;
	}

	public void setScname(String scname) {
		this.scname = scname;
	}

	public String getScdescription() {
		return scdescription;
	}

	public void setScdescription(String scdescription) {
		this.scdescription = scdescription;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Scripts() {
		//super();
	}

	public Scripts(String scname, String scdescription, String script) {
		super();
		this.scname = scname;
		this.scdescription = scdescription;
		this.script = script;
	}

	@Override
	public String toString() {
		return "Scripts [scid=" + scid + ", scname=" + scname
				+ ", scdescription=" + scdescription + ", script=" + script
				+ "]";
	}
	
	
	
}

	
