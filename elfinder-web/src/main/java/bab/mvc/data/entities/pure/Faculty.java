package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "faculty")
public class Faculty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "facultyid")
	private int facultyid;
	
	@Column(name = "facultyname")
	private String facultyname;

	public int getFacultyid() {
		return facultyid;
	}

	public void setFacultyid(int facultyid) {
		this.facultyid = facultyid;
	}

	public String getFacultyname() {
		return facultyname;
	}

	public void setFacultyname(String facultyname) {
		this.facultyname = facultyname;
	}

	public Faculty() {
		//super();
	}

	public Faculty(String facultyname) {
		super();
		this.facultyname = facultyname;
	}

	@Override
	public String toString() {
		return "Faculty [facultyid=" + facultyid + ", facultyname="
				+ facultyname + "]";
	}
	
	



	

}
