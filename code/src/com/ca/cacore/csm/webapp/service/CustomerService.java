package com.ca.cacore.csm.webapp.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.domain.CustomerAndCustomerContact.ICustomerDomain;
import com.ca.cacore.csm.model.bo.ICustomerContactModel;
import com.ca.cacore.csm.model.bo.ICustomerJieChuModel;
import com.ca.cacore.csm.model.bo.ICustomerLogModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.bo.IInfclaimsModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.ca.cacore.csm.model.vo.ILaspolicyModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c14export.CatchExportSql;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.excel.ExcelUtil;
import com.newtouch.utils.stringutil.StringUtil;
import com.newtouch.protocol.dao.ProtocolManageDao;
import com.newtouch.protocol.model.vo.IProtocolManageModel;
import com.newtouch.protocol.model.vo.ProtocolManageModel;

/**
 * @since: 2013年12月24日
 * @author newtouchlxy
 * @description:客户基本信息和联系信息Service
 */

@Service
public class CustomerService extends ServerBase implements ICustomerService {

	@Autowired
	private ICustomerDomain customerDomain;
	@Autowired
	private CatchExportSql catchExportSql; // 导出操作
	@Autowired
	private ProtocolManageDao protocolManageDao;
	/**
	 * 
	 * @param model1
	 * @param model2
	 * @param user
	 * @return
	 * @description:添加客户信息和联系信息
	 */
	public ReturnMsg addCustomerAndCustomerContact(ICustomerModel customermodel, ICustomerContactModel contactmodel,
			ICustomerLogModel logmodel, IUserModel user, String customer_id, String[] contactpersonInfo)
			throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			customerDomain.addCustomerr(customermodel, contactmodel, logmodel, user, customer_id, contactpersonInfo);
			returnMsg.setSuccessMessage("保存成功");
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:查询分页VO信息
	 */
	public ReturnMsg getVOAll(ICustomerVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ICustomerVOModel> list = customerDomain.getVOAll(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 * @description:查询出客户的信息以及所有联系人信息,理赔，接触，承包
	 */
	public ReturnMsg getView(ICustomerModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<Map<String, Object>> sum_list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		CustomerViewVOModel model2 = new CustomerViewVOModel();
		model2 = customerDomain.queryCustomer(model);
		// 客户联系信息
		model2.setListCont(customerDomain.queryCustomerContact(model));
		// 理赔信息
		List<IInfclaimsModel> list1 = customerDomain.queryInfclaimsAll(model);
		model2.setListinf(list1);
		double sum1 = 0;
		if (!list1.isEmpty()) {
			for (IInfclaimsModel key : list1) {
				sum1 += key.getDetermined_money() + key.getUndetermined_money();
			}
		}
		map.put("sum1", sum1);// 理赔合计
		// 接触录入信息
		model2.setListJieChu(customerDomain.queryJieChuAll(model));
		// 承保信息
		List<ILaspolicyModel> list2 = customerDomain.queryLas(model);
		model2.setListLas(list2);
		double sum2 = 0;
		if (!list2.isEmpty()) {
			for (ILaspolicyModel key : list2) {
				sum2 += key.getNetpremium();
			}
		}
		map.put("sum2", sum2);// 承保合计
		sum_list.add(map);
		returnMsg.setDataList(sum_list);
		List<CustomerViewVOModel> list = new ArrayList<CustomerViewVOModel>();
		list.add(model2);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		return returnMsg;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:查询出客户的信息以及最新联系人信息
	 */
	public ReturnMsg getModifyView(ICustomerModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		/*
		 * ICustomerContactModel model0 = new CustomerContactModel();
		 * model0.setCustomer_id(model.getCustomer_id());
		 */
		CustomerViewVOModel voModel = customerDomain.getModifyView(model);
		returnMsg.setDataTable(TransHelper.obj2map(voModel));
		return returnMsg;
	}

	/**
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @description:修改客户基本信息和客户联系信息
	 */
	public ReturnMsg modifyCustomerAndCustomerContact(CustomerViewVOModel model, IUserModel user,
			String[] contactpersonInfo) throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			if (ValidateHelper.isNull(model.getBranch_id())) {
				throw new BusinessException("机构代码不能为空,请核实。");
			}
			customerDomain.modifyCustomerAndCustomerContact(model, user, contactpersonInfo);
			returnMsg.setSuccessMessage("修改成功");
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:检查是否存在客户（依照证件类型和证件号码） true 存在 false 不存在
	 */
	public boolean customerIsRepeat(ICustomerModel model) {
		return customerDomain.verifyIsExistIdentityAndType(model);
	}

	/**
	 * 
	 * 2014-2-7
	 * 
	 * @param model
	 * @return
	 * @description:校验会员编号是否存在
	 */
	public boolean checkMemberId(ICustomerModel model) {

		return customerDomain.checkMemberId(model);
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

		return customerDomain.getCustomerNewInfo(model);
	}

	/**
	 * xxz: 异步查询客户信息
	 */
	@Override
	public CustomerVOModel getCustomerInfoByCusBranID(CustomerVOModel model) {
		CustomerVOModel cvm=model;
		return cvm;
	}

	/**
	 * 客户信息维护导出
	 */
	@Override
	public List<ICustomerVOModel> daochuCustomer(ICustomerVOModel model) {
		return customerDomain.doachuCustomer(model);
	}

	/**
	 * 客户接触信息校验为空
	 */
	@Override
	public ReturnMsg addCustomerJieChu(ICustomerJieChuModel model, IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			if (StringUtil.isNull(model.getCustomer_id())) {
				throw new BusinessException("客户代码不可为空，请核实!");
			}

			if (StringUtil.isNull(model.getAction_time())) {
				throw new BusinessException("日期不可为空，请核实!");
			}
			if (StringUtil.isNull(model.getAction_notes())) {
				throw new BusinessException("内容不可为空，请核实!");
			}
			customerDomain.addCustomerJieChu(model, user);
			returnMsg.setSuccessMessage("客户接触信息录入成功");
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	/**
	 * 查询客户接触信息
	 */
	@Override
	public ReturnMsg queryJieChu(ICustomerModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		CustomerViewVOModel mo = customerDomain.queryJieChu(model);
		returnMsg.setDataTable(TransHelper.obj2map(mo));
		return returnMsg;
	}

	@Override
	public ICustomerModel getClasscode(ICustomerModel model) {
		return customerDomain.getClasscode(model);
	}

	/*
	 * (non-Javadoc) 新增
	 * 
	 */

	@Override
	public ReturnMsg queryCustomer1(ICustomerVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			if (StringUtil.isNull(model.getBranch_id())) {
				throw new BusinessException("使用机构不能为空");
			}

			if (StringUtil.isNull(model.getInsured_name())) {
				throw new BusinessException("客户姓名不能为空");
			}
			/*
			 * if(StringUtil.isNull(model.getInsured_phone())){ throw new
			 * BusinessException("终止日期不能为空"); }
			 */
			if (StringUtil.isNull(model.getInsured_cid())) {
				throw new BusinessException("客户证件号码不能为空");
			}
			model.setCreateUser(this.user().getOptID());
			customerDomain.queryCustomer1(model);
			returnMsg.setSuccessMessage("保存成功");
			returnMsg.setDataTable(TransHelper.obj2map(model));
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;

	}

	/*
	 * 修改
	 */

	@Override
	public ReturnMsg modifySave(CustomerVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataTable(TransHelper.obj2map(model));
		try {
			customerDomain.modifySave(model);
			returnMsg.setSuccessMessage("修改成功");
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}

		return returnMsg;
	}
	/*
	
	*/

	@Override
	public ReturnMsg goModify(String customer_id) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<CustomerVOModel> list = customerDomain.goModify(customer_id);
		List<Map<String, Object>> list_result = TransHelper.list2MapList(list);
		Map<String, Object> map = list_result.get(0);
		returnMsg.setDataTable(map);
		return returnMsg;
	}


	private Map<String, List<Object>> readDataFromExcel(MultipartFile excelFile) throws IOException {
		ExcelUtil util = new ExcelUtil();
		String[] titles = null;
		// 导入信息对应导入模板的列字段
		titles = new String[] {"insured_name", // 客户姓名
				"branch_id", // 销售公司编号
				"branch_name", // 销售公司名称
				"insured_cid", // 证件号码
				"insured_company_type", // 客户性质
				"home_address", // 客户地址
				"insured_mailbox", // 客户邮箱
				"insured_phone", // 客户手机号
				"insured_tel" // 客户固话
		};
		Map<String, List<Object>> resultMap = util.initSheet4Stream(excelFile.getInputStream(), new Object(), titles);
		return resultMap;
	}

	/**
	 * 数据转换为实体类
	 */
	public List<CustomerVOModel> getQueryAndModifyModel(Map<String, List<Object>> map) {
		List<CustomerVOModel> list = new ArrayList<>();
		if (!map.isEmpty()) {
			for (String key : map.keySet()) {
				List<Map<String, Object>> listcum = (List) map.get(key);
				for (Map<String, Object> cum : listcum) {
					CustomerVOModel model = new CustomerVOModel();
					if (cum.containsKey("branch_id")) {
						model.setBranch_id(cum.get("branch_id").toString().trim());
					}
					if (cum.containsKey("insured_name")) {
						model.setInsured_name(cum.get("insured_name").toString().trim());
					}
					if (cum.containsKey("insured_cid")) {
						model.setInsured_cid(cum.get("insured_cid").toString().trim());
					}
					if (cum.containsKey("insured_company_type")) {
						if ("单位".equals(cum.get("insured_company_type").toString().trim())) {
							model.setInsured_company_type("1");
						}
						if ("个人".equals(cum.get("insured_company_type").toString().trim())) {
							model.setInsured_company_type("2");
						}
						if(!"单位".equals(cum.get("insured_company_type").toString().trim())&&
								!"个人".equals(cum.get("insured_company_type").toString().trim())){
							model.setInsured_company_type(cum.get("insured_company_type").toString().trim());
						}
					}
					if (cum.containsKey("home_address")) {
						model.setHome_address(cum.get("home_address").toString().trim());
					}
					if (cum.containsKey("insured_mailbox")) {
						model.setInsured_mailbox(cum.get("insured_mailbox").toString().trim());
					}
					if (cum.containsKey("insured_phone")) {
						model.setInsured_phone(cum.get("insured_phone").toString().trim());
					}
					if (cum.containsKey("insured_tel")) {
						model.setInsured_tel(cum.get("insured_tel").toString().trim());
					}
					if (null != model) {
						list.add(model);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 校验导入数据是否正确
	 */
	public Map<String, Object> getCheckData(List<CustomerVOModel> listMap) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < listMap.size(); i++) {
			if (null == listMap.get(i).getInsured_name() || "".equals(listMap.get(i).getInsured_name())) {
				map.put("info", "第" + (i + 1) + "行客户姓名不能为空");
				return map;
			}
			if (null == listMap.get(i).getInsured_cid() || "".equals(listMap.get(i).getInsured_cid())) {
				map.put("info", "第" + (i + 1) + "行客户证件号不能为空");
				return map;
			}
			if (null == listMap.get(i).getInsured_company_type()
					|| "".equals(listMap.get(i).getInsured_company_type())) {
				map.put("info", "第" + (i + 1) + "行客户性质不能为空");
				return map;
			}
			if (null == listMap.get(i).getHome_address() || "".equals(listMap.get(i).getHome_address())) {
				map.put("info", "第" + (i + 1) + "行客户地址不能为空");
				return map;
			}
			if (null == listMap.get(i).getInsured_phone() || "".equals(listMap.get(i).getInsured_phone())) {
				map.put("info", "第" + (i + 1) + "行客户手机号码不能为空");
				return map;
			}
			if (!"1".equals(listMap.get(i).getInsured_company_type())
					&& !"2".equals(listMap.get(i).getInsured_company_type())) {
				map.put("info", "第" + (i + 1) + "行客户性质(单位/个人)填写不正确不能为空");
				return map;
			}
			if(!"".equals(listMap.get(i).getBranch_id())&&!"".equals(listMap.get(i).getBranch_name())
					&& null!=listMap.get(i).getBranch_id()&&null!=listMap.get(i).getBranch_name()){
				IProtocolManageModel model = new ProtocolManageModel();
				model.setBranch_id( listMap.get(i).getBranch_id());
				model.setBranch_name(listMap.get(i).getBranch_name());
				Integer count = protocolManageDao.findSysBranch(model);
				if (count == 0) {
					map.put("info", "第" + (i + 1) + "行填写的销售机构不存在");
					return map;
				}
			}
		}
		map.put("info", "数据验证通过");
		return map;
	}

	@Override
	public ReturnMsg updateCustomer(ICustomerVOModel model) {
		// TODO Auto-generated method stub
		ReturnMsg msg = new ReturnMsg();
		return null;
	}
}
