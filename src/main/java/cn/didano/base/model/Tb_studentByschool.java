package cn.didano.base.model;

import java.util.List;

public class Tb_studentByschool {

	private Integer classId;
	private List<Hand_WholeStudentParents4PhoneBook> list;
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public List<Hand_WholeStudentParents4PhoneBook> getList() {
		return list;
	}
	public void setList(List<Hand_WholeStudentParents4PhoneBook> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Tb_studentByschool [classId=" + classId + ", list=" + list + "]";
	}
	
}
