package bab.mvc.data.entities.pure;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	
	@Column(name = "userName")
	private String userName;
	
	
	@Column(name = "pass")
	private String pass;
	
	@Column(name = "nationalCode")
	private String nationalCode;
	
	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "state")
	private int state;
	
	@Column(name = "SN_PN")
	private String SN_PN;
	
	@Column(name = "unid")
	private int unid;
	
	@Column(name = "facultyid")
	private int facultyid;
	
	@Column(name = "field")
	private String field;
	
	@Column(name = "gradeid")
	private int gradeid;
	
	@Column(name = "nationalCard")
	private String nationalCard;
	
	@Column(name = "recommendation")
	private String recommendation;
	
	@Column(name = "createtime", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	
	@Column(name = "exptime", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date exptime;
	
	@Column(name = "isactive")
	private int isactive;
	
	@Column(name = "st_pr_card")
	private String st_pr_card;
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSN_PN() {
		return SN_PN;
	}

	public void setSN_PN(String sN_PN) {
		SN_PN = sN_PN;
	}

	public int getUnid() {
		return unid;
	}

	public void setUnid(int unid) {
		this.unid = unid;
	}

	public int getFacultyid() {
		return facultyid;
	}

	public void setFacultyid(int facultyid) {
		this.facultyid = facultyid;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getGradeid() {
		return gradeid;
	}

	public void setGradeid(int gradeid) {
		this.gradeid = gradeid;
	}

	public String getNationalCard() {
		return nationalCard;
	}

	public void setNationalCard(String nationalCard) {
		this.nationalCard = nationalCard;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getExptime() {
		return exptime;
	}

	public void setExptime(Date exptime) {
		this.exptime = exptime;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	
	public String getSt_pr_card() {
		return st_pr_card;
	}

	public void setSt_pr_card(String st_pr_card) {
		this.st_pr_card = st_pr_card;
	}
	
	public User() {
		super();
	}

	public User(String fname, String lname, String userName, String pass,
			String nationalCode, String email, String address,
			String phone, int state, String sN_PN, int unid, int facultyid,
			String field, int gradeid, String nationalCard,
			String recommendation, Date createtime, Date exptime, int isactive, String st_pr_card) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.userName = userName;
		this.pass = pass;
		this.nationalCode = nationalCode;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.state = state;
		SN_PN = sN_PN;
		this.unid = unid;
		this.facultyid = facultyid;
		this.field = field;
		this.gradeid = gradeid;
		this.nationalCard = nationalCard;
		this.recommendation = recommendation;
		this.createtime = createtime;
		this.exptime = exptime;
		this.isactive = isactive;
		this.st_pr_card=st_pr_card;
	}
	
	public User(String fname, String lname, String userName, String pass,String email, Date createtime, int isactive) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.userName = userName;
		this.pass = pass;
		this.email = email;
		this.createtime = createtime;
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", fname=" + fname + ", lname=" + lname
				+ ", userName=" + userName + ", pass=" + pass
				+ ", nationalCode=" + nationalCode + ", email=" + email
				+ ", address=" + address + ", phone="
				+ phone + ", state=" + state + ", SN_PN=" + SN_PN + ", unid="
				+ unid + ", facultyid=" + facultyid + ", field=" + field
				+ ", gradeid=" + gradeid + ", nationalCard=" + nationalCard
				+ ", recommendation=" + recommendation + ", createtime="
				+ createtime + ", exptime=" + exptime + ", isactive="
				+ isactive +", st_pr_card="+st_pr_card+ "]";
	}
		
}
