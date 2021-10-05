package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "grade")
public class Grade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gradeid")
	private int gradeid;
	
	@Column(name = "gradename")
	private String gradename;

	public int getGradeid() {
		return gradeid;
	}

	public void setGradeid(int gradeid) {
		this.gradeid = gradeid;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public Grade() {
		//super();
	}

	public Grade(String gradename) {
		super();
		this.gradename = gradename;
	}

	@Override
	public String toString() {
		return "Grade [gradeid=" + gradeid + ", gradename=" + gradename + "]";
	}



	

}
