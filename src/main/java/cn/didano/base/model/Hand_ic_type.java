package cn.didano.base.model;

/**
 * @author Administrator
 *Rewrite Date：<修改日期:2017-03-09>
 *Log ID：<1>
 *Depiction：<为借口传递数据>
 *Writer：杨朝强
 */
public class Hand_ic_type {
	public String ic_number;
	public Integer staff_id;
	public Integer type;
	public String getIc_number() {
		return ic_number;
	}
	public void setIc_number(String ic_number) {
		this.ic_number = ic_number;
	}
	public Integer getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}