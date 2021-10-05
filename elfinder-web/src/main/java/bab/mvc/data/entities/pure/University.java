package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "university")
public class University {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unid")
	private int unid;
	
	@Column(name = "unname")
	private String unname;

	public int getUnid() {
		return unid;
	}

	public void setUnid(int unid) {
		this.unid = unid;
	}

	public String getUnname() {
		return unname;
	}

	public void setUnname(String unname) {
		this.unname = unname;
	}

	public University() {
		//super();
	}

	public University(String unname) {
		super();
		this.unname = unname;
	}

	@Override
	public String toString() {
		return "University [unid=" + unid + ", unname=" + unname + "]";
	}




	

}
