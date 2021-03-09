package com.newtouch.report.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public class EmpAnalysisModel implements IEmpAnalysisModel{

	private String branch_id; //机构id
	private String branch_name;//机构名称
	private String person_no;//人员编码
	private Date entry_date1;//入职时间从
	private Date entry_date2;//至
	private Date end_date1;//离职时间从
	private Date end_date2;//至
	private Date sys_date1;//系统时间从
	private Date sys_date2;//至
	private String work_relation;//用工性质
	private Integer num1;//专职委派人数
	private Integer num2;//兼职委派人数
	private Integer num3;//劳动合同人数
	private Integer num4;//劳务合同人数
	private Integer num5;//劳派合同人数
	private Integer num6;//个代合同人数
	private Integer total;//汇总
	private Integer total1;//专职委派人数汇总
	private Integer total2;//兼职委派人数汇总
	private Integer total3;//劳动合同人数汇总
	private Integer total4;//劳务合同人数汇总
	private Integer total5;//劳派合同人数汇总
	private Integer total6;//个代合同人数汇总
	private Integer total7;//总汇总
	
	private String remark;//备注
	private String dept_list;//权限
	
	

	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getPerson_no() {
		return person_no;
	}
	public void setPerson_no(String person_no) {
		this.person_no = person_no;
	}
	public Date getEntry_date1() {
		return entry_date1;
	}
	public void setEntry_date1(Date entry_date1) {
		this.entry_date1 = entry_date1;
	}
	public Date getEntry_date2() {
		return entry_date2;
	}
	public void setEntry_date2(Date entry_date2) {
		this.entry_date2 = entry_date2;
	}
	public Date getEnd_date1() {
		return end_date1;
	}
	public void setEnd_date1(Date end_date1) {
		this.end_date1 = end_date1;
	}
	public Date getEnd_date2() {
		return end_date2;
	}
	public void setEnd_date2(Date end_date2) {
		this.end_date2 = end_date2;
	}
	public Date getSys_date1() {
		return sys_date1;
	}
	public void setSys_date1(Date sys_date1) {
		this.sys_date1 = sys_date1;
	}
	public Date getSys_date2() {
		return sys_date2;
	}
	public void setSys_date2(Date sys_date2) {
		this.sys_date2 = sys_date2;
	}
	public String getWork_relation() {
		return work_relation;
	}
	public void setWork_relation(String work_relation) {
		this.work_relation = work_relation;
	}
	public Integer getNum1() {
		return num1;
	}
	public void setNum1(Integer num1) {
		this.num1 = num1;
	}
	public Integer getNum2() {
		return num2;
	}
	public void setNum2(Integer num2) {
		this.num2 = num2;
	}
	public Integer getNum3() {
		return num3;
	}
	public void setNum3(Integer num3) {
		this.num3 = num3;
	}
	public Integer getNum4() {
		return num4;
	}
	public void setNum4(Integer num4) {
		this.num4 = num4;
	}
	public Integer getNum5() {
		return num5;
	}
	public void setNum5(Integer num5) {
		this.num5 = num5;
	}
	public Integer getNum6() {
		return num6;
	}
	public void setNum6(Integer num6) {
		this.num6 = num6;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getTotal1() {
		return total1;
	}
	public void setTotal1(Integer total1) {
		this.total1 = total1;
	}
	public Integer getTotal2() {
		return total2;
	}
	public void setTotal2(Integer total2) {
		this.total2 = total2;
	}
	public Integer getTotal3() {
		return total3;
	}
	public void setTotal3(Integer total3) {
		this.total3 = total3;
	}
	public Integer getTotal4() {
		return total4;
	}
	public void setTotal4(Integer total4) {
		this.total4 = total4;
	}
	public Integer getTotal5() {
		return total5;
	}
	public void setTotal5(Integer total5) {
		this.total5 = total5;
	}
	public Integer getTotal6() {
		return total6;
	}
	public void setTotal6(Integer total6) {
		this.total6 = total6;
	}
	public Integer getTotal7() {
		return total7;
	}
	public void setTotal7(Integer total7) {
		this.total7 = total7;
	}
	public String getDept_list() {
		return dept_list;
	}
	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}
	
	private PageCount pageCount = new PageCount();
	@Override
	public PageCount getPageCount() {
		// TODO Auto-generated method stub
		return pageCount;
	}
	@Override
	public void setPageCount(PageCount pageCount) {
		// TODO Auto-generated method stub
		this.pageCount = pageCount;
	}
	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return this.getPageCount().getStart();
	}
	@Override
	public int getLimit() {
		// TODO Auto-generated method stub
		return this.getPageCount().getLimit();
	}
	
}
