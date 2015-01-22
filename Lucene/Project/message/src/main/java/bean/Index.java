package bean;

import java.util.Date;

/** 索引对象，用于显示到页面 */
public class Index {
	
	private int msgId;// 留言ID
	private String title;// 带高亮的留言标题
	private String summary;// 带高亮的留言摘要
	private Date createDate;// 留言创建时间

	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
