package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "basics")
public class Basics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid")
	private int bid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "value")
	private String value;

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Basics(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Basics() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Basics [bid=" + bid + ", name=" + name + ", value=" + value + "]";
	}
	
	

	

}
