package bab.mvc.data.entities.pure;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
@Table (name = "news")
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nid")
	private int nid;
	
	@Column(name = "topic")
	private String topic;
	
	@Column(name = "text")
	private String text;
	
	
	@Column(name = "uid")
	private int uid;
	
	
	@Column(name = "postdate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date postdate;
	
	
	@Column(name = "expdate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expdate;


	public int getNid() {
		return nid;
	}


	public void setNid(int nid) {
		this.nid = nid;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public Date getPostdate() {
		return postdate;
	}


	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}


	public Date getExpdate() {
		return expdate;
	}


	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}


	public News() {
	//	super();
	}


	public News(String topic, String text, int uid, Date postdate, Date expdate) {
		super();
		this.topic = topic;
		this.text = text;
		this.uid = uid;
		this.postdate = postdate;
		this.expdate = expdate;
	}


	@Override
	public String toString() {
		return "News [nid=" + nid + ", topic=" + topic + ", text=" + text
				+ ", uid=" + uid + ", postdate=" + postdate + ", expdate="
				+ expdate + "]";
	}


	

}
