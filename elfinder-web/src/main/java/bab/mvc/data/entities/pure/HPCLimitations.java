package bab.mvc.data.entities.pure;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import java.util.Date;

@Entity
@Table (name = "hpclimitations")
public class HPCLimitations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hpclid")
	private int hpclid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "nodemax")
	private int nodemax;
	
	
	@Column(name = "coremax")
	private int coremax;
	
	
	@Column(name = "memmax")
	private int memmax;
	
	@Column(name = "storagemax")
	private int storagemax;
	//@Column(name = "startTime", columnDefinition="DATETIME")
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date startTime;

	public int getHpclid() {
		return hpclid;
	}

	public void setHpclid(int hpclid) {
		this.hpclid = hpclid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNodemax() {
		return nodemax;
	}

	public void setNodemax(int nodemax) {
		this.nodemax = nodemax;
	}

	public int getCoremax() {
		return coremax;
	}

	public void setCoremax(int coremax) {
		this.coremax = coremax;
	}

	public int getMemmax() {
		return memmax;
	}

	public void setMemmax(int memmax) {
		this.memmax = memmax;
	}

	public int getStoragemax() {
		return storagemax;
	}

	public void setStoragemax(int storagemax) {
		this.storagemax = storagemax;
	}

	public HPCLimitations() {
		//super();
	}

	public HPCLimitations(String name, int nodemax, int coremax, int memmax,
			int storagemax) {
		super();
		this.name = name;
		this.nodemax = nodemax;
		this.coremax = coremax;
		this.memmax = memmax;
		this.storagemax = storagemax;
	}

	@Override
	public String toString() {
		return "Hpclimitations [hpclid=" + hpclid + ", name=" + name
				+ ", nodemax=" + nodemax + ", coremax=" + coremax + ", memmax="
				+ memmax + ", storagemax=" + storagemax + "]";
	}


	

}
