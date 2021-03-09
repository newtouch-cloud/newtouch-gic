package com.newtouch.report.model.vo;

import java.math.BigDecimal;
import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
/**
* @since:    2017年11月29日   
* @description:协议管理-Model
*/
public abstract class Sysbranchcommission implements IPageCount{
	private Integer seq_id;
	private String branch_id;// 中介公司id-----------经营单位
	private String branch_name;// 中介公司名称
	private String cpybranch_id;//保险公司id
	private String cpybranch_name;//保险公司名称
	private BigDecimal g_day_count;//起保口径-本日-承保数量
	private BigDecimal g_day_amount;//起保口径-本日-保险金额
	private BigDecimal g_day_premium;//起保口径-本日-承保保费
	private BigDecimal g_day_fee;//起保口径-本日-手续费
	private String newg_day_premium;//起保口径-本日-承保保费(同期)
	private String newg_day_fee;//起保口径-本日-手续费(同期)
	private BigDecimal g_month_count;//起保口径-本月-承保数量
	private BigDecimal g_month_amount;//起保口径-本月-保险金额
	private BigDecimal g_month_premium;//起保口径-本月-承保保费
	private BigDecimal g_month_fee;//起保口径-本月-手续费
	private String newg_month_premium;//起保口径-本月-承保保费(同期)
	private String newg_month_fee;//起保口径-本月-手续费(同期)
	private BigDecimal g_year_count;//起保口径-本年-承保数量
	private BigDecimal g_year_amount;//起保口径-本年-保险金额
	private BigDecimal g_year_premium;//起保口径-本年-承保保费
	private BigDecimal g_year_fee;//起保口径-本年-手续费
	private String newg_year_premium;//起保口径-本年-承保保费(同期)
	private String newg_year_fee;//起保口径-本年-手续费(同期)
	private BigDecimal u_day_count;//核保口径-本日-承保数量
	private BigDecimal u_day_amount;//核保口径-本日-保险金额
	private BigDecimal u_day_premium;//核保口径-本日-承保保费
	private BigDecimal u_day_fee;//核保口径-本日-手续费
	private String newu_day_premium;//核保口径-本日-承保保费(同期)
	private String newu_day_fee;//核保口径-本日-手续费(同期)
	private BigDecimal u_month_count;//核保口径-本月-承保数量
	private BigDecimal u_month_amount;//核保口径-本月-保险金额
	private BigDecimal u_month_premium;//核保口径-本月-承保保费
	private BigDecimal u_month_fee;//核保口径-本月-手续费
	private String newu_month_premium;//核保口径-本月-承保保费(同期)
	private String newu_month_fee;//核保口径-本月-手续费(同期)
	private BigDecimal u_year_count;//核保口径-本年-承保数量
	private BigDecimal u_year_amount;//核保口径-本年-保险金额
	private BigDecimal u_year_premium;//核保口径-本年-承保保费
	private BigDecimal u_year_fee;//核保口径-本年-手续费
	private String newu_year_premium;//核保口径-本年-承保保费(同期)
	private String newu_year_fee;//核保口径-本年-手续费(同期)
	
	private BigDecimal all_month_count;//总计口径-本月-承保数量
	private BigDecimal all_month_amount;//总计口径-本月-保险金额
	private BigDecimal all_month_premium;//总计口径-本月-承保保费
	private BigDecimal all_month_fee;//总计口径-本月-手续费
	private String newall_month_premium;//总计口径-本月-承保保费(同期)
	private String newall_month_fee;//总计口径-本月-手续费(同期)
	
	private BigDecimal all_year_count;//总计口径-本年-承保数量
	private BigDecimal all_year_amount;//总计口径-本年-保险金额
	private BigDecimal all_year_premium;//总计口径-本年-承保保费
	private BigDecimal all_year_fee;//总计口径-本年-手续费
	private String newall_year_premium;//总计口径-本年-承保保费(同期)
	private String newall_year_fee;//总计口径-本年-手续费(同期)
	
	private Date term_date;//期次
	private String person_flag;
	private String date_flag;
	private Integer branch_level ;
	private String cpy_branch_id;
	private String createUser;
	private Integer org_level;//保险公司机构层级
	
	
	private PageCount pageCount = new PageCount();
	
	

	public String getCpybranch_id() {
		return cpybranch_id;
	}

	public void setCpybranch_id(String cpybranch_id) {
		this.cpybranch_id = cpybranch_id;
	}

	public String getCpybranch_name() {
		return cpybranch_name;
	}

	public void setCpybranch_name(String cpybranch_name) {
		this.cpybranch_name = cpybranch_name;
	}

	public String getNewg_day_premium() {
		return newg_day_premium;
	}

	public void setNewg_day_premium(String newg_day_premium) {
		this.newg_day_premium = newg_day_premium;
	}

	public String getNewg_day_fee() {
		return newg_day_fee;
	}

	public void setNewg_day_fee(String newg_day_fee) {
		this.newg_day_fee = newg_day_fee;
	}

	public String getNewg_month_premium() {
		return newg_month_premium;
	}

	public void setNewg_month_premium(String newg_month_premium) {
		this.newg_month_premium = newg_month_premium;
	}

	public String getNewg_month_fee() {
		return newg_month_fee;
	}

	public void setNewg_month_fee(String newg_month_fee) {
		this.newg_month_fee = newg_month_fee;
	}

	public String getNewg_year_premium() {
		return newg_year_premium;
	}

	public void setNewg_year_premium(String newg_year_premium) {
		this.newg_year_premium = newg_year_premium;
	}

	public String getNewg_year_fee() {
		return newg_year_fee;
	}

	public void setNewg_year_fee(String newg_year_fee) {
		this.newg_year_fee = newg_year_fee;
	}

	public String getNewu_day_premium() {
		return newu_day_premium;
	}

	public void setNewu_day_premium(String newu_day_premium) {
		this.newu_day_premium = newu_day_premium;
	}

	public String getNewu_day_fee() {
		return newu_day_fee;
	}

	public void setNewu_day_fee(String newu_day_fee) {
		this.newu_day_fee = newu_day_fee;
	}

	public String getNewu_month_premium() {
		return newu_month_premium;
	}

	public void setNewu_month_premium(String newu_month_premium) {
		this.newu_month_premium = newu_month_premium;
	}

	public String getNewu_month_fee() {
		return newu_month_fee;
	}

	public void setNewu_month_fee(String newu_month_fee) {
		this.newu_month_fee = newu_month_fee;
	}

	public String getNewu_year_premium() {
		return newu_year_premium;
	}

	public void setNewu_year_premium(String newu_year_premium) {
		this.newu_year_premium = newu_year_premium;
	}

	public String getNewu_year_fee() {
		return newu_year_fee;
	}

	public void setNewu_year_fee(String newu_year_fee) {
		this.newu_year_fee = newu_year_fee;
	}

	public BigDecimal getG_day_count() {
		return g_day_count;
	}

	public void setG_day_count(BigDecimal g_day_count) {
		this.g_day_count = g_day_count;
	}

	public BigDecimal getG_day_amount() {
		return g_day_amount;
	}

	public void setG_day_amount(BigDecimal g_day_amount) {
		this.g_day_amount = g_day_amount;
	}

	public BigDecimal getG_day_premium() {
		return g_day_premium;
	}

	public void setG_day_premium(BigDecimal g_day_premium) {
		this.g_day_premium = g_day_premium;
	}

	public BigDecimal getG_day_fee() {
		return g_day_fee;
	}

	public void setG_day_fee(BigDecimal g_day_fee) {
		this.g_day_fee = g_day_fee;
	}

	public BigDecimal getG_month_count() {
		return g_month_count;
	}

	public void setG_month_count(BigDecimal g_month_count) {
		this.g_month_count = g_month_count;
	}

	public BigDecimal getG_month_amount() {
		return g_month_amount;
	}

	public void setG_month_amount(BigDecimal g_month_amount) {
		this.g_month_amount = g_month_amount;
	}

	public BigDecimal getG_month_premium() {
		return g_month_premium;
	}

	public void setG_month_premium(BigDecimal g_month_premium) {
		this.g_month_premium = g_month_premium;
	}

	public BigDecimal getG_month_fee() {
		return g_month_fee;
	}

	public void setG_month_fee(BigDecimal g_month_fee) {
		this.g_month_fee = g_month_fee;
	}

	public BigDecimal getG_year_count() {
		return g_year_count;
	}

	public void setG_year_count(BigDecimal g_year_count) {
		this.g_year_count = g_year_count;
	}

	public BigDecimal getG_year_amount() {
		return g_year_amount;
	}

	public void setG_year_amount(BigDecimal g_year_amount) {
		this.g_year_amount = g_year_amount;
	}

	public BigDecimal getG_year_premium() {
		return g_year_premium;
	}

	public void setG_year_premium(BigDecimal g_year_premium) {
		this.g_year_premium = g_year_premium;
	}

	public BigDecimal getG_year_fee() {
		return g_year_fee;
	}

	public void setG_year_fee(BigDecimal g_year_fee) {
		this.g_year_fee = g_year_fee;
	}

	public BigDecimal getU_day_count() {
		return u_day_count;
	}

	public void setU_day_count(BigDecimal u_day_count) {
		this.u_day_count = u_day_count;
	}

	public BigDecimal getU_day_amount() {
		return u_day_amount;
	}

	public void setU_day_amount(BigDecimal u_day_amount) {
		this.u_day_amount = u_day_amount;
	}

	public BigDecimal getU_day_premium() {
		return u_day_premium;
	}

	public void setU_day_premium(BigDecimal u_day_premium) {
		this.u_day_premium = u_day_premium;
	}

	public BigDecimal getU_day_fee() {
		return u_day_fee;
	}

	public void setU_day_fee(BigDecimal u_day_fee) {
		this.u_day_fee = u_day_fee;
	}

	public BigDecimal getU_month_count() {
		return u_month_count;
	}

	public void setU_month_count(BigDecimal u_month_count) {
		this.u_month_count = u_month_count;
	}

	public BigDecimal getU_month_amount() {
		return u_month_amount;
	}

	public void setU_month_amount(BigDecimal u_month_amount) {
		this.u_month_amount = u_month_amount;
	}

	public BigDecimal getU_month_premium() {
		return u_month_premium;
	}

	public void setU_month_premium(BigDecimal u_month_premium) {
		this.u_month_premium = u_month_premium;
	}

	public BigDecimal getU_month_fee() {
		return u_month_fee;
	}

	public void setU_month_fee(BigDecimal u_month_fee) {
		this.u_month_fee = u_month_fee;
	}

	public BigDecimal getU_year_count() {
		return u_year_count;
	}

	public void setU_year_count(BigDecimal u_year_count) {
		this.u_year_count = u_year_count;
	}

	public BigDecimal getU_year_amount() {
		return u_year_amount;
	}

	public void setU_year_amount(BigDecimal u_year_amount) {
		this.u_year_amount = u_year_amount;
	}

	public BigDecimal getU_year_premium() {
		return u_year_premium;
	}

	public void setU_year_premium(BigDecimal u_year_premium) {
		this.u_year_premium = u_year_premium;
	}

	public BigDecimal getU_year_fee() {
		return u_year_fee;
	}

	public void setU_year_fee(BigDecimal u_year_fee) {
		this.u_year_fee = u_year_fee;
	}

	public Date getTerm_date() {
		return term_date;
	}

	public void setTerm_date(Date term_date) {
		this.term_date = term_date;
	}

	public PageCount getPageCount() {
		return pageCount;
	}

	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}

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

	public String getPerson_flag() {
		return person_flag;
	}

	public void setPerson_flag(String person_flag) {
		this.person_flag = person_flag;
	}

	public String getDate_flag() {
		return date_flag;
	}

	public void setDate_flag(String date_flag) {
		this.date_flag = date_flag;
	}

	public Integer getBranch_level() {
		return branch_level;
	}

	public void setBranch_level(Integer branch_level) {
		this.branch_level = branch_level;
	}

	public BigDecimal getAll_month_count() {
		return all_month_count;
	}

	public void setAll_month_count(BigDecimal all_month_count) {
		this.all_month_count = all_month_count;
	}

	public BigDecimal getAll_month_amount() {
		return all_month_amount;
	}

	public void setAll_month_amount(BigDecimal all_month_amount) {
		this.all_month_amount = all_month_amount;
	}

	public BigDecimal getAll_month_premium() {
		return all_month_premium;
	}

	public void setAll_month_premium(BigDecimal all_month_premium) {
		this.all_month_premium = all_month_premium;
	}

	public BigDecimal getAll_month_fee() {
		return all_month_fee;
	}

	public void setAll_month_fee(BigDecimal all_month_fee) {
		this.all_month_fee = all_month_fee;
	}

	public String getNewall_month_premium() {
		return newall_month_premium;
	}

	public void setNewall_month_premium(String newall_month_premium) {
		this.newall_month_premium = newall_month_premium;
	}

	public String getNewall_month_fee() {
		return newall_month_fee;
	}

	public void setNewall_month_fee(String newall_month_fee) {
		this.newall_month_fee = newall_month_fee;
	}

	public BigDecimal getAll_year_count() {
		return all_year_count;
	}

	public void setAll_year_count(BigDecimal all_year_count) {
		this.all_year_count = all_year_count;
	}

	public BigDecimal getAll_year_amount() {
		return all_year_amount;
	}

	public void setAll_year_amount(BigDecimal all_year_amount) {
		this.all_year_amount = all_year_amount;
	}

	public BigDecimal getAll_year_premium() {
		return all_year_premium;
	}

	public void setAll_year_premium(BigDecimal all_year_premium) {
		this.all_year_premium = all_year_premium;
	}

	public BigDecimal getAll_year_fee() {
		return all_year_fee;
	}

	public void setAll_year_fee(BigDecimal all_year_fee) {
		this.all_year_fee = all_year_fee;
	}

	public String getNewall_year_premium() {
		return newall_year_premium;
	}

	public void setNewall_year_premium(String newall_year_premium) {
		this.newall_year_premium = newall_year_premium;
	}

	public String getNewall_year_fee() {
		return newall_year_fee;
	}

	public void setNewall_year_fee(String newall_year_fee) {
		this.newall_year_fee = newall_year_fee;
	}

	public String getCpy_branch_id() {
		return cpy_branch_id;
	}

	public void setCpy_branch_id(String cpy_branch_id) {
		this.cpy_branch_id = cpy_branch_id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getOrg_level() {
		return org_level;
	}

	public void setOrg_level(Integer org_level) {
		this.org_level = org_level;
	}
	
	
	
}
