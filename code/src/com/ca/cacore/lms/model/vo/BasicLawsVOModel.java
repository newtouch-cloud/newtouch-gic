package com.ca.cacore.lms.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2014年3月2日   
* @author    ss
* @description:封装关于基本法的信息
*/
public class BasicLawsVOModel implements IBasicLawsVOModel {

	private String serno;// 01.serno
	private String index_calcno;// 指标计算码
	private String variable_no;// 变量编码
	private String variable_name;// 变量名称
	private String attribute_no;// 属性编码
	private String variable_order;// 变量顺序
	private String data_flag;// 09.有效性标志
	private String attribute_name;// 属性名称
	private String attribute_type;// 属性类型
	private String result_type;// 结果类型
	private String result_rule;// 对应规则
	private String index_type;// 指标类型
	private String calc_order;// 计算顺序
	private String calc_module;// 计算类
	private String calc_mthd;// 计算方法
	private String memo;// 备注信息
	private String basiclaw_no;// 基本法编号
	private String impmeansver_name;// 实施办法名称
	private String basiclaw_ver;// 基本法版本
	private String dept_type;// 部门类型
	private String iss_unit;// 颁布单位
	private Date iss_date;// 颁布日期
	private Date start_date;// 03.开始日期
	private Date end_date;// 04.结束日期
	private String Code;// Name
	private String impmeans_no;// 子办法编号
	private String index_no;// 指标代码
	private String index_name;// 指标名称
	private String index_order;// 计算顺序
	private String is_valid;// 是否有效
	private String impmeans_name;// 子办法名称
	private String para_type;// 参数类型
	private String rank_no;// 职级代码
	private String para_no;// 参数代码
	private String para_name;// 参数名称
	private String start_value1;// 开始值一
	private String end_value1;// 结束值一
	private String start_value2;// 开始值二
	private String end_value2;// 结束值二
	private String para_value;// 参数值
	private String para_flag;// 参数用途
	private String rank_name;// 职级名称
	private String rank_seq;// 职级序列
	private String rank_type;// 职级类型
	private String rank_level;// 职级级别
	private String impmeansver_no;// 实施办法编号
	private String dept_no;// 部门编码
	private String rela_type;// 关系类型
	private String target_rank;// 目标职级
	private String chg_type;// 转换类型
	private String con_no;// 条件序号
	private String con_flag;// 条件标志
	private String data_value;// 数值
	private String warn_flag;// 参加预警标志
	private String expand_1;// 扩展一
	private String expand_2;// 扩展二
	private Date crt_date;// 05.创建日期
	private Date mdf_date;// 06.修改日期
	private String crt_user;// 07.创建操作员
	private String mdf_user;// 08.修改操作员
	private PageCount pageCount = new PageCount();
	public BasicLawsVOModel() {
	}

	public BasicLawsVOModel(String serno) {
		this.serno = serno;
	}

	public String getSerno() {
		return serno;
	}

	public void setSerno(String serno) {
		this.serno = serno;
	}

	public String getIndex_calcno() {
		return index_calcno;
	}

	public void setIndex_calcno(String index_calcno) {
		this.index_calcno = index_calcno;
	}

	public String getVariable_no() {
		return variable_no;
	}

	public void setVariable_no(String variable_no) {
		this.variable_no = variable_no;
	}

	public String getVariable_name() {
		return variable_name;
	}

	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}

	public String getAttribute_no() {
		return attribute_no;
	}

	public void setAttribute_no(String attribute_no) {
		this.attribute_no = attribute_no;
	}

	public String getVariable_order() {
		return variable_order;
	}

	public void setVariable_order(String variable_order) {
		this.variable_order = variable_order;
	}

	public String getData_flag() {
		return data_flag;
	}

	public void setData_flag(String data_flag) {
		this.data_flag = data_flag;
	}

	public String getAttribute_name() {
		return attribute_name;
	}

	public void setAttribute_name(String attribute_name) {
		this.attribute_name = attribute_name;
	}

	public String getAttribute_type() {
		return attribute_type;
	}

	public void setAttribute_type(String attribute_type) {
		this.attribute_type = attribute_type;
	}

	public String getResult_type() {
		return result_type;
	}

	public void setResult_type(String result_type) {
		this.result_type = result_type;
	}

	public String getResult_rule() {
		return result_rule;
	}

	public void setResult_rule(String result_rule) {
		this.result_rule = result_rule;
	}

	public String getIndex_type() {
		return index_type;
	}

	public void setIndex_type(String index_type) {
		this.index_type = index_type;
	}

	public String getCalc_order() {
		return calc_order;
	}

	public void setCalc_order(String calc_order) {
		this.calc_order = calc_order;
	}

	public String getCalc_module() {
		return calc_module;
	}

	public void setCalc_module(String calc_module) {
		this.calc_module = calc_module;
	}

	public String getCalc_mthd() {
		return calc_mthd;
	}

	public void setCalc_mthd(String calc_mthd) {
		this.calc_mthd = calc_mthd;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBasiclaw_no() {
		return basiclaw_no;
	}

	public void setBasiclaw_no(String basiclaw_no) {
		this.basiclaw_no = basiclaw_no;
	}

	public String getImpmeansver_name() {
		return impmeansver_name;
	}

	public void setImpmeansver_name(String impmeansver_name) {
		this.impmeansver_name = impmeansver_name;
	}

	public String getBasiclaw_ver() {
		return basiclaw_ver;
	}

	public void setBasiclaw_ver(String basiclaw_ver) {
		this.basiclaw_ver = basiclaw_ver;
	}

	public String getDept_type() {
		return dept_type;
	}

	public void setDept_type(String dept_type) {
		this.dept_type = dept_type;
	}

	public String getIss_unit() {
		return iss_unit;
	}

	public void setIss_unit(String iss_unit) {
		this.iss_unit = iss_unit;
	}

	public Date getIss_date() {
		return iss_date;
	}

	public void setIss_date(Date iss_date) {
		this.iss_date = iss_date;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getImpmeans_no() {
		return impmeans_no;
	}

	public void setImpmeans_no(String impmeans_no) {
		this.impmeans_no = impmeans_no;
	}

	public String getIndex_no() {
		return index_no;
	}

	public void setIndex_no(String index_no) {
		this.index_no = index_no;
	}

	public String getIndex_name() {
		return index_name;
	}

	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}

	public String getIndex_order() {
		return index_order;
	}

	public void setIndex_order(String index_order) {
		this.index_order = index_order;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

	public String getImpmeans_name() {
		return impmeans_name;
	}

	public void setImpmeans_name(String impmeans_name) {
		this.impmeans_name = impmeans_name;
	}

	public String getPara_type() {
		return para_type;
	}

	public void setPara_type(String para_type) {
		this.para_type = para_type;
	}

	public String getRank_no() {
		return rank_no;
	}

	public void setRank_no(String rank_no) {
		this.rank_no = rank_no;
	}

	public String getPara_no() {
		return para_no;
	}

	public void setPara_no(String para_no) {
		this.para_no = para_no;
	}

	public String getPara_name() {
		return para_name;
	}

	public void setPara_name(String para_name) {
		this.para_name = para_name;
	}

	public String getStart_value1() {
		return start_value1;
	}

	public void setStart_value1(String start_value1) {
		this.start_value1 = start_value1;
	}

	public String getEnd_value1() {
		return end_value1;
	}

	public void setEnd_value1(String end_value1) {
		this.end_value1 = end_value1;
	}

	public String getStart_value2() {
		return start_value2;
	}

	public void setStart_value2(String start_value2) {
		this.start_value2 = start_value2;
	}

	public String getEnd_value2() {
		return end_value2;
	}

	public void setEnd_value2(String end_value2) {
		this.end_value2 = end_value2;
	}

	public String getPara_value() {
		return para_value;
	}

	public void setPara_value(String para_value) {
		this.para_value = para_value;
	}

	public String getPara_flag() {
		return para_flag;
	}

	public void setPara_flag(String para_flag) {
		this.para_flag = para_flag;
	}

	public String getRank_name() {
		return rank_name;
	}

	public void setRank_name(String rank_name) {
		this.rank_name = rank_name;
	}

	public String getRank_seq() {
		return rank_seq;
	}

	public void setRank_seq(String rank_seq) {
		this.rank_seq = rank_seq;
	}

	public String getRank_type() {
		return rank_type;
	}

	public void setRank_type(String rank_type) {
		this.rank_type = rank_type;
	}

	public String getRank_level() {
		return rank_level;
	}

	public void setRank_level(String rank_level) {
		this.rank_level = rank_level;
	}

	public String getImpmeansver_no() {
		return impmeansver_no;
	}

	public void setImpmeansver_no(String impmeansver_no) {
		this.impmeansver_no = impmeansver_no;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getRela_type() {
		return rela_type;
	}

	public void setRela_type(String rela_type) {
		this.rela_type = rela_type;
	}

	public String getTarget_rank() {
		return target_rank;
	}

	public void setTarget_rank(String target_rank) {
		this.target_rank = target_rank;
	}

	public String getChg_type() {
		return chg_type;
	}

	public void setChg_type(String chg_type) {
		this.chg_type = chg_type;
	}

	public String getCon_no() {
		return con_no;
	}

	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}

	public String getCon_flag() {
		return con_flag;
	}

	public void setCon_flag(String con_flag) {
		this.con_flag = con_flag;
	}

	public String getData_value() {
		return data_value;
	}

	public void setData_value(String data_value) {
		this.data_value = data_value;
	}

	public String getWarn_flag() {
		return warn_flag;
	}

	public void setWarn_flag(String warn_flag) {
		this.warn_flag = warn_flag;
	}

	public String getExpand_1() {
		return expand_1;
	}

	public void setExpand_1(String expand_1) {
		this.expand_1 = expand_1;
	}

	public String getExpand_2() {
		return expand_2;
	}

	public void setExpand_2(String expand_2) {
		this.expand_2 = expand_2;
	}

	public Date getCrt_date() {
		return crt_date;
	}

	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}

	public Date getMdf_date() {
		return mdf_date;
	}

	public void setMdf_date(Date mdf_date) {
		this.mdf_date = mdf_date;
	}

	public String getCrt_user() {
		return crt_user;
	}

	public void setCrt_user(String crt_user) {
		this.crt_user = crt_user;
	}

	public String getMdf_user() {
		return mdf_user;
	}

	public void setMdf_user(String mdf_user) {
		this.mdf_user = mdf_user;
	}

	public PageCount getPageCount() {
		return pageCount;
	}
	
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public int getStart() {
		return this.getPageCount().getStart();
	}

	@Override
	public int getLimit() {
		return this.getPageCount().getLimit();
	}

}
