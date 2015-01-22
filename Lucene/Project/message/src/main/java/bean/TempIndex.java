package bean;

/** 未提交的索引记录，用于存数据库已防止内存中未提交的索引丢失 */
public class TempIndex {

	private int id;
	private int objId;// 要操作的对象id，可能是MEssage也可能是Attachment
	private String type;// 需要操作的对象类型，Message和Attachment
	private int operator;// 操作的类型是Add还是Delete或者Update
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getObjId() {
		return objId;
	}
	public void setObjId(int objId) {
		this.objId = objId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	
}
