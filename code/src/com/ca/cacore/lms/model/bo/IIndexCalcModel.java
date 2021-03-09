package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IIndexCalcModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getIndex_calcno();

	public void setIndex_calcno(String index_calcno);

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


	public Date getCrt_date();

	public void setCrt_date(Date crt_date);

	public Date getMdf_date();

	public void setMdf_date(Date mdf_date);

	public String getCrt_user();

	public void setCrt_user(String crt_user);

	public String getMdf_user();

	public void setMdf_user(String mdf_user);

	public String getData_flag();

	public void setData_flag(String data_flag);

}