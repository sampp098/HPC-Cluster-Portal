package bab.mvc.data.entities.pure;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "messages")
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mid")
	private int mid;
	
	@Column(name = "mtopic")
	private String mtopic;
	
	@Column(name = "mtext")
	private String mtext;
	
	
	@Column(name = "fromuid")
	private int fromuid;
	
	
	@Column(name = "touid")
	private int touid;

	@Column(name = "postdate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date postdate;

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMtopic() {
		return mtopic;
	}

	public void setMtopic(String mtopic) {
		this.mtopic = mtopic;
	}

	public String getMtext() {
		return mtext;
	}

	public void setMtext(String mtext) {
		this.mtext = mtext;
	}

	public int getFromuid() {
		return fromuid;
	}

	public void setFromuid(int fromuid) {
		this.fromuid = fromuid;
	}

	public int getTouid() {
		return touid;
	}

	public void setTouid(int touid) {
		this.touid = touid;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public Messages() {
		//super();
	}

	public Messages(String mtopic, String mtext, int fromuid, int touid,
			Date postdate) {
		super();
		this.mtopic = mtopic;
		this.mtext = mtext;
		this.fromuid = fromuid;
		this.touid = touid;
		this.postdate = postdate;
	}

	@Override
	public String toString() {
		return "Messages [mid=" + mid + ", mtopic=" + mtopic + ", mtext="
				+ mtext + ", fromuid=" + fromuid + ", touid=" + touid
				+ ", postdate=" + postdate + "]";
	}
	
}