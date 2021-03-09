package com.ca.cacore.csm.dao.customer;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerJieChuModel;
import com.ca.cacore.csm.model.bo.ICustomerLogModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.bo.IInfclaimsModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.ca.cacore.csm.model.vo.ILaspolicyModel;
import com.newtouch.core.daobase.BaseDao;
import com.newtouch.utils.dateutil.DateUtil;

/**
 * 
 * @since: 2013年12月24日
 * @author newtouchlxy
 * @description:客户联系信息和客户联系信息Dao
 */

@Component
public class CustomerDao extends BaseDao implements ICustomerDao {

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:增加客户基本信息
	 */
	public boolean addCustomer(ICustomerModel model) {
		this.getSqlMapClientTemplate().insert("Customer.insertCustomer", model);
		return true;
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:插入客户联系信息
	 */
	public Boolean addCustomerContanct(ICustomerContactModel model) {
		this.getSqlMapClientTemplate().insert("Customer.insertCustomerContact", model);
		return true;
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:分页查询客户信息和客户联系信息
	 */
	public List<ICustomerVOModel> getVOAll(ICustomerVOModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("Customer.queryCount", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		List<ICustomerVOModel> list = (List<ICustomerVOModel>) this.getSqlMapClientTemplate().queryForList("Customer.queryAll", model);
		return list;

	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:详细信息查看
	 */
	public CustomerViewVOModel getView(ICustomerModel model) {
		return (CustomerViewVOModel) this.getSqlMapClientTemplate().queryForObject("Customer.queryView", model);
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:查询customerModel根据
	 */
	@SuppressWarnings("unchecked")
	public List<CustomerViewVOModel> queryCustomer(ICustomerModel model) {
		return this.getSqlMapClientTemplate().queryForList("Customer.queryCustomerModel", model);
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:查询客户联系根据
	 */
	public List<ICustomerContactModel> queryCustomerContact(ICustomerModel model) {
		return (List<ICustomerContactModel>) this.getSqlMapClientTemplate().queryForList("Customer.queryCustomerContactModel", model);
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:查询客户客户最新的一条联系信息 by 张晨
	 */
	public ICustomerContactModel getNewestCustomerContact(ICustomerContactModel model) {
		return (ICustomerContactModel) this.getSqlMapClientTemplate().queryForObject("Customer.getNewestCustomerContact", model);
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:更新用户基本信息
	 */
	public boolean updateCustomer(ICustomerModel model) {
		this.getSqlMapClientTemplate().update("Customer.updateCustomer", model);
		return true;
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:更新客户联系信息
	 */
	public boolean updateCustomerContact(ICustomerContactModel model) {
		this.getSqlMapClientTemplate().update("Customer.updateCustomerContact", model);
		return true;
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:查询出最后更新的客户联系信息
	 */
	public ICustomerContactModel queryNewestCustomerContact(ICustomerContactModel model) {
		return (ICustomerContactModel) this.getSqlMapClientTemplate().queryForObject("Customer.queryNewestCustomerContact", model);
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:严格查询客户基本信息
	 */
	public ICustomerModel queryRigid(ICustomerModel model) {
		return (ICustomerModel) this.getSqlMapClientTemplate().queryForObject("Customer.queryRigid", model);
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 * @description:修改客户基本信息
	 */
	public boolean modifyCustomer(ICustomerModel model) {
		this.getSqlMapClientTemplate().update("Customer.updateCustomer", model);
		return true;
	}

	@Override
	public List<ICustomerModel> validateCustomer(ICustomerModel model) {
		return null;
	}

	/**
	 * 
	 * 2014-1-4 张晨
	 * 
	 * @param model
	 * @return ICustomerModel
	 * @description:查询最新修改的客户信息
	 */
	public ICustomerVOModel getCustomerNewInfo(ICustomerVOModel model) {

		return (ICustomerVOModel) this.getSqlMapClientTemplate().queryForObject("Customer.getCustomerNewInfo", model);
	}

	/**
	 * 
	 * 2014-1-6 范清雨
	 * 
	 * @param model
	 * @return String
	 * @description:查询客户信息的seq_id
	 */
	public Integer getSeqId(ICustomerModel model) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Customer.querySeqId", model);
	}

	/**
	 * 
	 * 2014-1-6 范清雨
	 * 
	 * @param model
	 * @return
	 * @description:查询客户信息的seq_id
	 */
	public boolean addCustomerLog(ICustomerLogModel model) {
		this.getSqlMapClientTemplate().insert("Customer.insertCustomerLog", model);
		return true;
	}

	/**
	 * 
	 * 2014-2-7 张晨
	 * 
	 * @param model
	 * @return ICustomerModel
	 * @description:校验会员编号是否存在
	 */
	public Integer checkMemberId(ICustomerModel model) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Customer.checkMemberId", model);
	}

	/**
	 * 客户信息维护导出
	 */
	@Override
	public List<ICustomerVOModel> daochuCustomer(ICustomerVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("Customer.queryCustomerForExport", model);
	}

	/**
	 * 添加客户接触录入
	 */
	@Override
	public boolean addCustomerJieChu(ICustomerJieChuModel model) {
		this.getSqlMapClientTemplate().insert("Customer.addCustomerJieChu", model);
		return true;
	}

	/**
	 * 根据客户ID跳到 客户接触录入页面
	 */
	@Override
	public CustomerViewVOModel queryJieChu(ICustomerModel model) {
		return (CustomerViewVOModel) this.getSqlMapClientTemplate().queryForObject("Customer.queryForJieChu", model);
	}

	/**
	 * 理赔信息查询
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<IInfclaimsModel> queryInfclaimsAll(ICustomerModel model) {
		return this.getSqlMapClientTemplate().queryForList("Customer.queryInfclaims", model);
	}

	/**
	 * 客户查询明细接触信息
	 */
	@Override
	public List<ICustomerJieChuModel> queryJiechuAll(ICustomerModel model) {
		return this.getSqlMapClientTemplate().queryForList("Customer.queryJiechu", model);
	}

	/**
	 * 承包信息
	 */
	@Override
	public List<ILaspolicyModel> queryLas(ICustomerModel model) {
		return this.getSqlMapClientTemplate().queryForList("Customer.queryLast", model);
	}

	/**
	 * 更新附件上传时间
	 * 
	 * @param model
	 * @return boolean
	 * @description:
	 */
	@Override
	public boolean updateCustomterTime(ICustomerModel model) {
		this.getSqlMapClientTemplate().update("Customer.updateCustomterTime", model);
		return true;
	}

	@Override
	public ICustomerModel getClasscode(ICustomerModel model) {
		return (ICustomerModel) this.getSqlMapClientTemplate().queryForObject("Customer.getClasscode", model);
	}

	@Override
	public boolean deleteCustomerContanct(ICustomerModel model) {
		int flag = this.getSqlMapClientTemplate().delete("Customer.deleteCustomerContanct", model);
		if (flag >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 新增
	 */
	@Override
	public boolean queryCustomer1(ICustomerVOModel model) {
		/*int count = (Integer) this.getSqlMapClientTemplate().queryForObject("Customer.customerCount", model);
		String count1 = StrUtil.alignLeft(count, 14);
		// String count2 = StrUtil.alignRight(count, 5);
		// String seq_id = model.getSeq_id();
		StringBuilder customer = new StringBuilder();
		// customer.append(seq_id);
		customer.append(count1);
		// customer.append(count2);
		String customer1 = customer.toString();
		// String customer2 = customer.toString();
*/		
		java.sql.Date date = DateUtil.sysDate();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		int i = (int) ((Math.random()*9+1)*1000);
		StringBuilder builder = new StringBuilder();
		String num = df.format(date);
		builder.append(num);
		builder.append(i);
		String customer_id = builder.toString();
		model.setCustomer_id(customer_id);
		// model.setOrg_id(customer2);
		this.getSqlMapClientTemplate().insert("Customer.queryCustomer1", model);
		// this.getSqlMapClientTemplate().insert("Customer.addCustomer2",model);
		return true;
	}

	/*
	 * 修改
	 */
	@Override
	public boolean modifySave(ICustomerVOModel model) {
		this.getSqlMapClientTemplate().update("Customer.modifySave", model);
		return true;
	}

	/*
	 * 修改查询
	 */
	@Override
	public List<CustomerVOModel> goModify(String customer_id) {
		List<CustomerVOModel> list = this.getSqlMapClientTemplate().queryForList("Customer.goModify", customer_id);
		return list;
	}

}
