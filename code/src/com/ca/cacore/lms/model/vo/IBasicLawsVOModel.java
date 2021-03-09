package com.ca.cacore.lms.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

/**
* @since:    2014年3月2日   
* @author    ss
* @description:封装关于基本法的信息
*/
public interface IBasicLawsVOModel extends IPageCount {
	public String getSerno();

	public void setSerno(String serno);

	public String getIndex_calcno();

	public void setIndex_calcno(String index_calcno);

	public String getVariable_no();

	public void setVariable_no(String variable_no);

	public String getVariable_name();

	public void setVariable_name(String variable_name);

	public String getAttribute_no();

	public void setAttribute_no(String attribute_no);

	public String getVariable_order();

	public void setVariable_order(String variable_order);

	public String getData_flag();

	public void setData_flag(String data_flag);

	public String getAttribute_name();

	public void setAttribute_name(String attribute_name);

	public String getAttribute_type();

	public void setAttribute_type(String attribute_type);

	public String getResult_type();

	public void setResult_type(String result_type);

	public String getResult_rule();

	public void setResult_rule(String result_rule);

	public String getIndex_type();

	public void setIndex_type(String index_type);

	public String getCalc_order();

	public void setCalc_order(String calc_order);

	public String getCalc_module();

	public void setCalc_module(String calc_module);

	public String getCalc_mthd();

	public void setCalc_mthd(String calc_mthd);

	public String getMemo();

	public void setMemo(String memo);

	public String getBasiclaw_no();

	public void setBasiclaw_no(String basiclaw_no);

	public String getImpmeansver_name();

	public void setImpmeansver_name(String impmeansver_name);

	public String getBasiclaw_ver();

	public void setBasiclaw_ver(String basiclaw_ver);

	public String getDept_type();

	public void setDept_type(String dept_type);

	public String getIss_unit();

	public void setIss_unit(String iss_unit);

	public Date getIss_date();

	public void setIss_date(Date iss_date);

	public Date getStart_date();

	public void setStart_date(Date start_date);

	public Date getEnd_date();

	public void setEnd_date(Date end_date);

	public String getCode();

	public void setCode(String code);

	public String getImpmeans_no();

	public void setImpmeans_no(String impmeans_no);

	public String getIndex_no();

	public void setIndex_no(String index_no);

	public String getIndex_name();

	public void setIndex_name(String index_name);

	public String getIndex_order();

	public void setIndex_order(String index_order);

	public String getIs_valid();

	public void setIs_valid(String is_valid);

	public String getImpmeans_name();

	public void setImpmeans_name(String impmeans_name);

	public String getPara_type();

	public void setPara_type(String para_type);

	public String getRank_no();

	public void setRank_no(String rank_no);

	public String getPara_no();

	public void setPara_no(String para_no);

	public String getPara_name();

	public void setPara_name(String para_name);

	public String getStart_value1();

	public void setStart_value1(String start_value1);

	public String getEnd_value1();

	public void setEnd_value1(String end_value1);

	public String getStart_value2();

	public void setStart_value2(String start_value2);

	public String getEnd_value2();

	public void setEnd_value2(String end_value2);

	public String getPara_value();

	public void setPara_value(String para_value);

	public String getPara_flag();

	public void setPara_flag(String para_flag);

	public String getRank_name();

	public void setRank_name(String rank_name);

	public String getRank_seq();

	public void setRank_seq(String rank_seq);

	public String getRank_type();

	public void setRank_type(String rank_type);

	public String getRank_level();

	public void setRank_level(String rank_level);

	public String getImpmeansver_no();

	public void setImpmeansver_no(String impmeansver_no);

	public String getDept_no();

	public void setDept_no(String dept_no);

	public String getRela_type();

	public void setRela_type(String rela_type);

	public String getTarget_rank();

	public void setTarget_rank(String target_rank);

	public String getChg_type();

	public void setChg_type(String chg_type);

	public String getCon_no();

	public void setCon_no(String con_no);

	public String getCon_flag();

	public void setCon_flag(String con_flag);

	public String getData_value();

	public void setData_value(String data_value);

	public String getWarn_flag();

	public void setWarn_flag(String warn_flag);

	public String getExpand_1();

	public void setExpand_1(String expand_1);

	public String getExpand_2();

	public void setExpand_2(String expand_2);

	public Date getCrt_date();

	public void setCrt_date(Date crt_date);

	public Date getMdf_date();

	public void setMdf_date(Date mdf_date);

	public String getCrt_user();

	public void setCrt_user(String crt_user);

	public String getMdf_user();

	public void setMdf_user(String mdf_user);

	@Override
	public PageCount getPageCount();

	@Override
	public void setPageCount(PageCount pageCount);

	@Override
	public int getStart();

	@Override
	public int getLimit();
}
