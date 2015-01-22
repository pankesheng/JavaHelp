package bean;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

/** 索引Field集合，用于存到索引里 */
public class IndexField {
	
	private String id;// 这个索引的唯一标识：如果是留言使用0_留言id来表示;如果是附件使用 留言ID_附件ID来表示
	private String title;// 留言的标题
	private List<String> content;// 留言的内容，转换后的内容（通过Tika转换）
	private List<String> atths;
	private int parentId;// 当前对象的父类id，如果是留言，该id为0
	private int objId;// 当前存储对象的id
	private Date createDate;// 创建的时间
	private String type;// 操作的对象类型，可能是Message或者Attachment

	public String getType() {
		return type;
	}
	@Field("msg_type")
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	@Field
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	@Field("msg_title")
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getContent() {
		return content;
	}
	@Field("msg_content")
	public void setContent(List<String> content) {
		this.content = content;
	}
	public int getParentId() {
		return parentId;
	}
	@Field("msg_pid")
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getObjId() {
		return objId;
	}
	@Field("msg_id")
	public void setObjId(int objId) {
		this.objId = objId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	@Field("msg_date")
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<String> getAtths() {
		return atths;
	}
	@Field("msg_atts")
	public void setAtths(List<String> atths) {
		this.atths = atths;
	}
}
