package bab.mvc.data.entities.pure;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table (name = "hpctariff")
public class HPCTariff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hpctid")
	private int hpctid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "corecost")
	private int corecost;
	
	@Column(name = "memcost")
	private int memcost;
	
	@Column(name = "maxnodes")
	private int maxnodes;
	
	@Column(name = "maxcores")
	private int maxcores;
	
	
	@Column(name = "maxmemory")
	private int maxmemory;
	
	@Column(name = "maxstorage")
	private int maxstorage;
		
	@Column(name = "mincharge")
	private int mincharge;

	@Column(name = "queuesize")
	private int queuesize;
	
	@Column(name = "maxuserjobs")
	private int maxuserjobs;
	
	@Column(name = "maxusercjobs")
	private int maxusercjobs;
	
	@Column(name = "maxcput")
	private int maxcputime;
	
	@Column(name = "maxwallt")
	private int maxwalltime;
	
	@Column(name = "active")
	private int active;

	public HPCTariff() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public HPCTariff(String name, int corecost, int memcost, int maxnodes, int maxcores, int maxmemory, int maxstorage,
			int mincharge, int queuesize, int maxuserjobs, int maxusercjobs, int maxcputime, int maxwalltime,
			int active) {
		super();
		this.name = name;
		this.corecost = corecost;
		this.memcost = memcost;
		this.maxnodes = maxnodes;
		this.maxcores = maxcores;
		this.maxmemory = maxmemory;
		this.maxstorage = maxstorage;
		this.mincharge = mincharge;
		this.queuesize = queuesize;
		this.maxuserjobs = maxuserjobs;
		this.maxusercjobs = maxusercjobs;
		this.maxcputime = maxcputime;
		this.maxwalltime = maxwalltime;
		this.active = active;
	}

	

	public int getHpctid() {
		return hpctid;
	}

	public void setHpctid(int hpctid) {
		this.hpctid = hpctid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCorecost() {
		return corecost;
	}

	public void setCorecost(int corecost) {
		this.corecost = corecost;
	}

	public int getMemcost() {
		return memcost;
	}

	public void setMemcost(int memcost) {
		this.memcost = memcost;
	}

	public int getMaxnodes() {
		return maxnodes;
	}

	public void setMaxnodes(int maxnodes) {
		this.maxnodes = maxnodes;
	}

	public int getMaxcores() {
		return maxcores;
	}

	public void setMaxcores(int maxcores) {
		this.maxcores = maxcores;
	}

	public int getMaxmemory() {
		return maxmemory;
	}

	public void setMaxmemory(int maxmemory) {
		this.maxmemory = maxmemory;
	}

	public int getMaxstorage() {
		return maxstorage;
	}

	public void setMaxstorage(int maxstorage) {
		this.maxstorage = maxstorage;
	}

	public int getMincharge() {
		return mincharge;
	}

	public void setMincharge(int mincharge) {
		this.mincharge = mincharge;
	}

	public int getQueuesize() {
		return queuesize;
	}

	public void setQueuesize(int queuesize) {
		this.queuesize = queuesize;
	}

	public int getMaxuserjobs() {
		return maxuserjobs;
	}

	public void setMaxuserjobs(int maxuserjobs) {
		this.maxuserjobs = maxuserjobs;
	}

	public int getMaxusercjobs() {
		return maxusercjobs;
	}

	public void setMaxusercjobs(int maxusercjobs) {
		this.maxusercjobs = maxusercjobs;
	}

	public int getMaxcputime() {
		return maxcputime;
	}

	public void setMaxcputime(int maxcputime) {
		this.maxcputime = maxcputime;
	}

	public int getMaxwalltime() {
		return maxwalltime;
	}

	public void setMaxwalltime(int maxwalltime) {
		this.maxwalltime = maxwalltime;
	}



	public int getActive() {
		return active;
	}



	public void setActive(int active) {
		this.active = active;
	}



	@Override
	public String toString() {
		return "HPCTariff [hpctid=" + hpctid + ", name=" + name + ", corecost=" + corecost + ", memcost=" + memcost
				+ ", maxnodes=" + maxnodes + ", maxcores=" + maxcores + ", maxmemory=" + maxmemory + ", maxstorage="
				+ maxstorage + ", mincharge=" + mincharge + ", queuesize=" + queuesize + ", maxuserjobs=" + maxuserjobs
				+ ", maxusercjobs=" + maxusercjobs + ", maxcputime=" + maxcputime + ", maxwalltime=" + maxwalltime
				+ ", active=" + active + "]";
	}

	

	
	
	
	
}
