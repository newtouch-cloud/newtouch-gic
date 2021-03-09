package com.newtouch.protocol.webapp.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.excel.ExcelUtil;
import com.newtouch.utils.stringutil.StringUtil;
import com.newtouch.enumpak.dao.EnumDao;
import com.newtouch.enumpak.vo.EnumEntity;
import com.newtouch.protocol.dao.ProtocolManageDao;
import com.newtouch.protocol.domain.IProtocolManageDomain;
import com.newtouch.protocol.model.vo.ContractType;
import com.newtouch.protocol.model.vo.IProtocolManageModel;
import com.newtouch.protocol.model.vo.ProtocolCategoryModel;
import com.newtouch.protocol.model.vo.ProtocolManageModel;
import com.newtouch.newutil.DateUtil;
import com.newtouch.newutil.lang.Constants;

@Service
public class ProtocolManageService implements IProtocolManageService {
	@Autowired
	IProtocolManageDomain protocolManageDomain;
	@Autowired
	private ICommonSeqDao seqDao;
	@Autowired
	private EnumDao enumDao;
	@Autowired
	private ProtocolManageDao protocolManageDao;

	/**
	 * 
	 * @param model
	 * @return
	 * @description:协议管理-增加
	 */
	@Override
	public ReturnMsg addProtocol(List<IProtocolManageModel> list) {
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			// TODO 校验协议号是否存在

			protocolManageDomain.addProtocol(list);
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
	 * @description:协议管理-维护
	 */
	@Override
	public ReturnMsg updateProtocol(List<IProtocolManageModel> list) {
		ReturnMsg returnMsg = new ReturnMsg();
		try {

			protocolManageDomain.updateProtocol(list);
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
	 * @description:协议管理-查询
	 */
	@Override
	public ReturnMsg queryProtocol(IProtocolManageModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IProtocolManageModel> list = protocolManageDomain.queryProtocol(model);
		// 编码转换为name
		// 获取签订状态
		List<EnumEntity> listSignType = enumDao.findEnum(Constants.PROTOCOL_SIGN_TYPE);
		// 获取数据类型
		List<EnumEntity> listStatus = enumDao.findEnum(Constants.PROTOCOL_STATUS);
		// 获取协议合同类型
		List<Map<String, Object>> listCon = this.getProtocolType();
		for (IProtocolManageModel entity : list) {
			for (EnumEntity signEntity : listSignType) {
				if (null != entity.getSign_type() && entity.getSign_type().equals(signEntity.getCode())) {
					entity.setSign_type(signEntity.getName());
				}
			}
			for (EnumEntity statusEntity : listStatus) {
				if (null != entity.getStatus() && entity.getStatus().equals(statusEntity.getCode())) {
					entity.setStatus(statusEntity.getName());
				}
			}
			for (Map<String, Object> map : listCon) {
				if (null != entity.getProtocol_category() && entity.getProtocol_category().equals(map.get("proCode"))) {
					entity.setProtocol_category((String) map.get("proName"));
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> listConType = (List<Map<String, Object>>) map.get("contractList");
					for (Map<String, Object> conType : listConType) {
						if (null != entity.getContract_type()
								&& entity.getContract_type().equals(conType.get("conCode"))) {
							entity.setContract_type((String) conType.get("conName"));
						}
					}
				}
			}
		}
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:协议管理-修改
	 */

	@Override
	public ReturnMsg modifyProtocol(IProtocolManageModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			if (StringUtil.isNull(model.getBranch_id())) {
				throw new BusinessException("机构编码不能为空");
			}
			if (StringUtil.isNull(model.getBranch_name())) {
				throw new BusinessException("使用机构不能为空");
			}
			// if(StringUtil.isNull(model.getAgreement_no())){
			// throw new BusinessException("协议号不能为空");
			// }
			if (StringUtil.isNull(model.getStartdate())) {
				throw new BusinessException("生效日期不能为空");
			}
			if (StringUtil.isNull(model.getEnddate())) {
				throw new BusinessException("终止日期不能为空");
			}
			if (StringUtil.isNull(model.getDateofsign())) {
				throw new BusinessException("协议签订日期不能为空");
			}
			
			if(null!=model.getSup_protocol_no()&&!"".equals(model.getSup_protocol_no())){
				if(StringUtil.isNull(model.getSup_protocol_content())){
					throw new BusinessException("补充协议编号不为空时，补充协议内容为必填");
				}
				if(StringUtil.isNull(model.getSup_protocol_stadate())){
					throw new BusinessException("补充协议编号不为空时，补充协议生效日期为必填");
				}
				ProtocolManageModel modelPro = new ProtocolManageModel();
				modelPro.setSup_protocol_content(model.getSup_protocol_content());
				modelPro.setAgreement_no(model.getAgreement_no());
				modelPro.setSup_protocol_stadate(model.getSup_protocol_stadate());
				modelPro.setAgreement_no(model.getAgreement_no());
				modelPro.setStartdate(model.getStartdate());
				// 先判断补充协议表中是否存在历史协议
				int count = protocolManageDao.findSupProtocol(modelPro);
				// 处理补充协议的编号
				modelPro.setSup_protocol_no(model.getAgreement_no() + "-" + (count + 1));
				// 在补充协议表中增加当前版本的协议数据
				protocolManageDao.saveSupProtocol(modelPro);
				// 修改协议为当前补充协议数据内容
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				model.setStartdate(sdf.format(model.getSup_protocol_stadate()));
				Date endDate = DateUtil.string2Date(model.getEnddate(), "yyyy-MM-dd");
				if(model.getSup_protocol_stadate().getTime()>endDate.getTime()){
					throw new BusinessException("补充协议生效日期不能大于协议止期");
				}
			}
			protocolManageDomain.modifyProtocl(model);
			returnMsg.setSuccessMessage("修改成功");
			returnMsg.setDataTable(TransHelper.obj2map(model));
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}

	@Override
	public List<IProtocolManageModel> queryProtocolForExport(IProtocolManageModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProtocolManageModel getProtocolPersonInformation(IProtocolManageModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @description:协议管理-修改-页面
	 */
	@Override
	public ReturnMsg getProtocolModifyView(IProtocolManageModel model, String type) {
		ReturnMsg returnMsg = new ReturnMsg();
		IProtocolManageModel l = protocolManageDomain.getProtocolModifyView(model);
		if (!"modify".equals(type)) {
			String path = l.getPicture_path();
			if (null == path) {
				l.setPicture_pathName("未上传影像文件！");
				returnMsg.setDataTable(TransHelper.obj2map(l));
				return returnMsg;
			}
			int num = path.lastIndexOf("/");
			String pathNew = path.substring(num + 1, path.length());
			l.setPicture_pathName(pathNew);
		}
		returnMsg.setDataTable(TransHelper.obj2map(l));
		return returnMsg;
	}

	// 注销
	@Override
	public ReturnMsg updateProtocolStatus(IProtocolManageModel model) {
		ReturnMsg msg = new ReturnMsg();
		try {
			protocolManageDomain.updateProtocolStatus(model);
			msg.setDataTable(TransHelper.obj2map(model));
			msg.setSuccessMessage("解除成功");

		} catch (Exception e) {
			msg.setFailMessage(e.getMessage());
		}

		return msg;
	}

	/**
	 * 导入协议内容
	 */
	@SuppressWarnings("null")
	@Override
	public String importProtocol(MultipartFile file, IUserModel user, String protocol_category) throws IOException {

		String info = "";// 导入的回显消息
		Map<String, List<Object>> dataMap = readDataFromExcel(file, protocol_category);
		// map数据转换实体类数据
		List<ProtocolManageModel> listMap = this.getProtocolModel(dataMap);
		if (listMap.isEmpty()) {
			return "导入模板数据不能为空";
		}
		for (ProtocolManageModel model : listMap) {
			if (!"1".equals(model.getProtocol_category()) && "1".equals(protocol_category)) {
				return "导入协议模板数据错误，请选择保险公司协议模板导入";
			}
			if (!"2".equals(model.getProtocol_category()) && "2".equals(protocol_category)) {
				return "导入协议模板数据错误，请选择代理协议模板导入";
			}
			if (!"3".equals(model.getProtocol_category()) && "3".equals(protocol_category)) {
				return "导入协议模板数据错误，请选择其他协议模板导入";
			}
		}
		// 校验续签数据是否正确
		Map<String, Object> mapSign = getCheckSignData(listMap);
		if (!"续签验证通过".equals(mapSign.get("info").toString())) {
			return mapSign.get("info").toString();
		}
		// 根据协议号+签订类型补全续签信息
		List<ProtocolManageModel> listMapModel = this.singType(listMap);
		List<ProtocolManageModel> listMapModel01 = new ArrayList<>();
		List<ProtocolManageModel> listMapModel02 = new ArrayList<>();
		for (ProtocolManageModel model : listMapModel) {
			if (null != model.getSup_protocol_no() && !"".equals(model.getSup_protocol_no())) {
				listMapModel02.add(model);
			} else {
				listMapModel01.add(model);
			}
		}
		// 处理补充协议数据
		if (!listMapModel02.isEmpty()) {
			for (ProtocolManageModel model : listMapModel02) {
				boolean flag = protocolManageDao.checkAgreement_no(model);
				if (flag) {
					info = "需要添加补充协议内容的协议号" + model.getAgreement_no() + "不存在！";
					return info;
				}
			}
			Map<String, Object> map = this.getCheckData(listMapModel02);
			if (!"数据验证通过".equals(map.get("info").toString())) {
				return map.get("info").toString();
			}
			for (ProtocolManageModel model : listMapModel02) {
				// 先判断补充协议表中是否存在历史协议
				int count = protocolManageDao.findSupProtocol(model);
				// 处理补充协议的编号
				model.setSup_protocol_no(model.getAgreement_no() + "-" + (count + 1));
				// 在补充协议表中增加当前版本的协议数据
				protocolManageDao.saveSupProtocol(model);
				// 修改协议为当前补充协议数据内容
				protocolManageDao.updateSupProtocol(model);
			}
			if (listMapModel01.isEmpty()) {
				info = "导入数据成功";
			}
		}
		// 数据校验
		Map<String, Object> map = this.getCheckData(listMapModel01);
		if (!"数据验证通过".equals(map.get("info").toString())) {
			return map.get("info").toString();
		}
		// 批量插入数据
		if (!listMapModel01.isEmpty()) {
			int remainRows = listMapModel01.size(); // 剩余行数
			int flushRows = 1000; // 刷新行数
			int fromIndex = 0; // 起始下标
			int toIndex = flushRows; // 结束下标
			if (toIndex > remainRows) {
				toIndex = remainRows;
			}
			while (remainRows > 0) {
				List<ProtocolManageModel> subList = listMapModel01.subList(fromIndex, toIndex);
				// 插入数据
				protocolManageDomain.addProtocolNew(subList);
				fromIndex = toIndex;
				remainRows = listMapModel01.size() - toIndex;
				if (remainRows > flushRows) {
					toIndex = toIndex + flushRows;
				} else {
					toIndex = toIndex + remainRows;
				}
			}
			info = "导入数据成功";
		}
		return info;
	}

	// 设置导入保单的信息，取数据使用
	private Map<String, List<Object>> readDataFromExcel(MultipartFile excelFile, String proCategory)
			throws IOException {
		ExcelUtil util = new ExcelUtil();
		String[] titles = null;
		// 导入信息对应导入模板的列字段
		if ("1".equals(proCategory)) {
			titles = new String[] { "agreement_no", // 协议号
					"protocol_category", // 协议类型
					"contract_type", // 合同类型
					"branch_id", // 中介公司所属机构编码
					"branch_name", // 中介公司所属机构
					"party_a", // 甲方
					"party_b", // 乙方
					"ins_branch", // 乙方归属保险公司机构编码
					"ins_branchname", // 乙方归属保险公司机构名称
					"dateofsign", // 协议签订日期
					"startdate", // 协议生效起期
					"enddate", // 协议生效止期
					"sup_protocol_no", // 补充协议编号
					"sup_protocol_content", // 补充协议内容
					"sup_protocol_stadate", // 补充协议生效日期
					"status", // 协议状态
					"sign_type",// 签订类型
					"remarks"//备注
			};
		}
		if ("2".equals(proCategory)) {
			titles = new String[] { "agreement_no", // 协议号
					"protocol_category", // 协议类型
					"contract_type", // 合同类型
					"branch_id", // 中介公司所属机构编码
					"branch_name", // 中介公司所属机构
					"party_a", // 甲方
					"party_b", // 乙方
					"ins_branch", // 乙方归属保险公司机构编码
					"ins_branchname", // 乙方归属保险公司机构名称
					"push_code", // 乙方推荐维修码
					"enterprise_type", // 乙方企业类型
					"credit_code", // 乙方社会统一信用代码
					"isconsult", // 汽车信息咨询
					"bank_name", // 乙方开户行
					"bank_code", // 乙方银行账号
					"party_b_name", // 乙方联系人
					"party_b_phone", // 乙方联系人电话
					"amount", // 合同金额
					"dateofsign", // 协议签订日期
					"startdate", // 协议生效起期
					"enddate", // 协议生效止期
					"sup_protocol_no", // 补充协议编号
					"sup_protocol_content", // 补充协议内容
					"sup_protocol_stadate", // 补充协议生效日期
					"status", // 协议状态
					"sign_type",// 签订类型
					"remarks"//备注
			};
		}
		if ("3".equals(proCategory)) {
			titles = new String[] { "agreement_no", // 协议号
					"protocol_category", // 协议类型
					"contract_type", // 合同类型
					"branch_id", // 中介公司所属机构编码
					"branch_name", // 中介公司所属机构
					"party_a", // 甲方
					"party_b", // 乙方
					"party_c", // 丙方
					"amount", // 合同金额
					"dateofsign", // 协议签订日期
					"startdate", // 协议生效起期
					"enddate", // 协议生效止期
					"sup_protocol_no", // 补充协议编号
					"sup_protocol_content", // 补充协议内容
					"sup_protocol_stadate", // 补充协议生效日期
					"status", // 协议状态
					"sign_type",// 签订类型
					 "remarks"//备注
			};
		}
		Map<String, List<Object>> resultMap = util.initSheet4Stream(excelFile.getInputStream(), new Object(), titles);
		return resultMap;
	}

	/**
	 * 数据转换为实体类
	 */
	public List<ProtocolManageModel> getProtocolModel(Map<String, List<Object>> map) {
		List<ProtocolManageModel> list = new ArrayList<>();
		// 获取签订状态
		List<EnumEntity> listSignType = enumDao.findEnum(Constants.PROTOCOL_SIGN_TYPE);
		// 获取数据类型
		List<EnumEntity> listStatus = enumDao.findEnum(Constants.PROTOCOL_STATUS);
		// 获取协议合同类型
		List<Map<String, Object>> listCon = this.getProtocolType();
		if (!map.isEmpty()) {
			for (String key : map.keySet()) {
				List<Map<String, Object>> listcum = (List) map.get(key);
				for (Map<String, Object> cum : listcum) {
					try {
						ProtocolManageModel model = new ProtocolManageModel();
						String seq_id = seqDao.queryCommonSeq("seq_id");
						model.setSeq_id(Integer.parseInt(seq_id));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if (cum.containsKey("agreement_no")) {
							boolean isNumFlag = isNum(cum.containsKey("agreement_no") + "");
							if (isNumFlag) {
								model.setAgreement_no(new BigDecimal(cum.get("agreement_no") + "").toString().trim());
							} else {
								model.setAgreement_no(cum.get("agreement_no").toString());
							}

						}
						if (cum.containsKey("protocol_category") && cum.containsKey("contract_type")) {
							for (Map<String, Object> mapPro : listCon) {
								if (null != cum.get("protocol_category")
										&& cum.get("protocol_category").equals(mapPro.get("proName"))) {
									model.setProtocol_category((String) mapPro.get("proCode"));
									List<Map<String, Object>> listConType = (List<Map<String, Object>>) mapPro
											.get("contractList");
									for (Map<String, Object> conType : listConType) {
										if (null != cum.get("contract_type")
												&& cum.get("contract_type").equals(conType.get("conName"))) {
											model.setContract_type((String) conType.get("conCode"));
											break;
										} else {
											model.setContract_type("00");
										}
									}
								} /*
									 * else { model.setProtocol_category("00");
									 * model.setContract_type("00"); }
									 */
								// break;
							}
							if (model.getProtocol_category() == null) {
								model.setProtocol_category("00");
							}
						}
						if (cum.containsKey("branch_id")) {
							model.setBranch_id(cum.get("branch_id").toString().trim());
						}
						if (cum.containsKey("branch_name")) {
							model.setBranch_name(cum.get("branch_name").toString().trim());
						}
						if (cum.containsKey("party_a")) {
							model.setParty_a(cum.get("party_a").toString().trim());
						}
						if (cum.containsKey("party_b")) {
							model.setParty_b(cum.get("party_b").toString().trim());
						}
						if (cum.containsKey("party_c")) {
							model.setParty_c(cum.get("party_c").toString().trim());
						}
						if (cum.containsKey("ins_branch")) {
							model.setIns_branch(cum.get("ins_branch").toString().trim());
						}
						if (cum.containsKey("ins_branchname")) {
							model.setIns_branchname(cum.get("ins_branchname").toString().trim());
						}
						if (cum.containsKey("push_code")) {
							model.setPush_code(cum.get("push_code").toString().trim());
						}
						if (cum.containsKey("enterprise_type")) {
							model.setEnterprise_type(cum.get("enterprise_type").toString().trim());
						}
						if (cum.containsKey("credit_code")) {
							model.setCredit_code(cum.get("credit_code").toString().trim());
						}
						if (cum.containsKey("isconsult")) {
							model.setIsconsult(cum.get("isconsult").toString().trim());
						}
						if (cum.containsKey("bank_name")) {
							model.setBank_name(cum.get("bank_name").toString().trim());
						}
						if (cum.containsKey("bank_code")) {
							model.setBank_code(cum.get("bank_code").toString().trim());
						}
						if (cum.containsKey("party_b_name")) {
							model.setParty_b_name(cum.get("party_b_name").toString().trim());
						}
						if (cum.containsKey("party_b_phone")) {
							model.setParty_b_phone(cum.get("party_b_phone").toString().trim());
						}
						if (cum.containsKey("amount")) {
							model.setAmount(Double
									.parseDouble("".equals(cum.get("amount")) ? "0" : cum.get("amount").toString()));
						}
						if (cum.containsKey("dateofsign")) {
							model.setDateofsign(cum.get("dateofsign").toString().trim());
						}
						if (cum.containsKey("startdate")) {
							model.setStartdate(cum.get("startdate").toString().trim());
						}
						if (cum.containsKey("enddate")) {
							model.setEnddate(cum.get("enddate").toString().trim());
						}
						if (cum.containsKey("sup_protocol_no")&&!"".equals(cum.get("sup_protocol_no").toString().trim())) {
						    model.setSup_protocol_no(cum.get("sup_protocol_no").toString().trim());
						}
						if (cum.containsKey("sup_protocol_content")&&!"".equals(cum.get("sup_protocol_content").toString().trim())) {
						    model.setSup_protocol_content(cum.get("sup_protocol_content").toString().trim());
						 }
						if (cum.containsKey("sup_protocol_stadate")&&!"".equals(cum.get("sup_protocol_stadate").toString().trim())) {
						    model.setSup_protocol_stadate(sdf.parse(cum.get("sup_protocol_stadate").toString().trim()));
						}
						if (cum.containsKey("remarks")&&!"".equals(cum.get("remarks").toString().trim())) {
							model.setRemarks(cum.get("remarks").toString().trim());
						}
						
						/*if (cum.containsKey("sup_protocol_no")) {
							model.setSup_protocol_no(cum.get("sup_protocol_no").toString().trim());
						}
						if (cum.containsKey("sup_protocol_content")) {
							model.setSup_protocol_content(cum.get("sup_protocol_content").toString().trim());
						}
						if (cum.containsKey("sup_protocol_stadate")) {
							model.setSup_protocol_stadate(sdf.parse(cum.get("sup_protocol_stadate").toString().trim()));
						}
						if (cum.containsKey("remarks")) {
							model.setRemarks(cum.get("remarks").toString().trim());
						}*/
						if (cum.containsKey("status")) {
							for (EnumEntity statusEntity : listStatus) {
								if (null != cum.get("status") && cum.get("status").equals(statusEntity.getName())) {
									model.setStatus(statusEntity.getCode());
									break;
								} else {
									model.setStatus("00");
								}
							}
						}
						if (cum.containsKey("sign_type")) {
							for (EnumEntity signEntity : listSignType) {
								if (null != cum.get("sign_type") && cum.get("sign_type").equals(signEntity.getName())) {
									model.setSign_type(signEntity.getCode());
									break;
								} else {
									model.setSign_type("00");
								}
							}
						}
						if (null != model) {
							list.add(model);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}

	/**
	 * 校验续签数据
	 */
	public Map<String, Object> getCheckSignData(List<ProtocolManageModel> listMap) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < listMap.size(); i++) {
			if ("0".equals(listMap.get(i).getSign_type())) {
				if (null == listMap.get(i).getAgreement_no() || "".equals(listMap.get(i).getAgreement_no())) {
					map.put("info", "第" + (i + 1) + "行协议号不能为空");
					return map;
				}
				if (null == listMap.get(i).getDateofsign() || "".equals(listMap.get(i).getDateofsign())) {
					map.put("info", "第" + (i + 1) + "行协议签订日期不能为空");
					return map;
				}
				if (null == listMap.get(i).getStartdate() || "".equals(listMap.get(i).getStartdate())) {
					map.put("info", "第" + (i + 1) + "行协议生效起期不能为空");
					return map;
				}
				if (null == listMap.get(i).getEnddate() || "".equals(listMap.get(i).getEnddate())) {
					map.put("info", "第" + (i + 1) + "行协议生效止期不能为空");
					return map;
				}
				if (null == listMap.get(i).getStatus() || "".equals(listMap.get(i).getStatus())) {
					map.put("info", "第" + (i + 1) + "行协议状态不能为空");
					return map;
				}
				boolean flag = protocolManageDao.checkAgreement_no(listMap.get(i));
				if (flag) {
					map.put("info", "第" + (i + 1) + "行需要续签的协议号"
							+ new BigDecimal(listMap.get(i).getAgreement_no() + "").toString() + "的协议不存在");
					return map;
				}
			}
		}
		map.put("info", "续签验证通过");
		return map;
	}

	/**
	 * 校验数据
	 */
	public Map<String, Object> getCheckData(List<ProtocolManageModel> listMap) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < listMap.size(); i++) {
			if (!"0".equals(listMap.get(i).getSign_type())) {
				if (null == listMap.get(i).getAgreement_no() || "".equals(listMap.get(i).getAgreement_no())) {
					map.put("info", "第" + (i + 1) + "行协议号不能为空");
					return map;
				}
				if (null == listMap.get(i).getBranch_id() || "".equals(listMap.get(i).getBranch_id())) {
					map.put("info", "第" + (i + 1) + "行中介公司所属机构编码不能为空");
					return map;
				}
				if (null == listMap.get(i).getBranch_name() || "".equals(listMap.get(i).getBranch_name())) {
					map.put("info", "第" + (i + 1) + "行中介公司所属机构名称不能为空");
					return map;
				}
				if (null == listMap.get(i).getDateofsign() || "".equals(listMap.get(i).getDateofsign())) {
					map.put("info", "第" + (i + 1) + "行协议签订日期不能为空");
					return map;
				}
				if (null == listMap.get(i).getStartdate() || "".equals(listMap.get(i).getStartdate())) {
					map.put("info", "第" + (i + 1) + "行协议生效起期不能为空");
					return map;
				}
				if (null == listMap.get(i).getEnddate() || "".equals(listMap.get(i).getEnddate())) {
					map.put("info", "第" + (i + 1) + "行协议生效止期不能为空");
					return map;
				}
				if (null == listMap.get(i).getStatus() || "".equals(listMap.get(i).getStatus())) {
					map.put("info", "第" + (i + 1) + "行协议状态不能为空");
					return map;
				}
				if (null == listMap.get(i).getSign_type() || "".equals(listMap.get(i).getSign_type())) {
					map.put("info", "第" + (i + 1) + "行签订类型不能为空");
					return map;
				}
				if (null != listMap.get(i).getSup_protocol_no() && !"".equals(listMap.get(i).getSup_protocol_no())) {
					if (null == listMap.get(i).getSup_protocol_stadate()
							|| "".equals(listMap.get(i).getSup_protocol_stadate())
							|| null == listMap.get(i).getSup_protocol_content()
							|| "".equals(listMap.get(i).getSup_protocol_content())) {
						map.put("info", "第" + (i + 1) + "行补充协议编号不为空,请补充补充协议内容和生效日期");
						return map;
					}
				}
				// 检测输入协议类型和合同类型是否正确
				if ("00".equals(listMap.get(i).getProtocol_category())) {
					map.put("info", "第" + (i + 1) + "行协议类型不正确，请填写正确的协议类型(其他协议/代理协议/保险公司协议)");
					return map;
				}
				if ("3".equals(listMap.get(i).getProtocol_category())) {
					if ("00".equals(listMap.get(i).getContract_type())) {
						map.put("info", "第" + (i + 1) + "行合同类型不正确，请填写正确的其他协议合同类型(保险公司合同/业务合作协议/项目合作协议/固定资产合同/"
								+ "租房合同/租车合同/广告宣传合同/会务合同/培训合同/其他)");
						return map;
					}
				}
				if ("2".equals(listMap.get(i).getProtocol_category())) {
					if ("00".equals(listMap.get(i).getContract_type())) {
						map.put("info", "第" + (i + 1)
								+ "行合同类型不正确，请填写正确的代理协议合同类型(居间合同（总版）/业务合作协议（总版）/居间合同（地版）/业务合作协议（地版）/" + "/其他)");
						return map;
					}
				}
				if ("1".equals(listMap.get(i).getProtocol_category())) {
					if ("00".equals(listMap.get(i).getContract_type())) {
						map.put("info", "第" + (i + 1) + "行合同类型不正确，请填写正确的保险公司协议合同类型(保险代理合同/协赔合同/服务合同/" + "/其他)");
						return map;
					}
				}
				if ("00".equals(listMap.get(i).getStatus())) {
					map.put("info", "第" + (i + 1) + "行协议状态不正确，请填写正确的协议状态(有效/到期自动终止/提前终止)");
					return map;
				}
				if ("00".equals(listMap.get(i).getSign_type())) {
					map.put("info", "第" + (i + 1) + "行签订类型不正确，请填写正确的签订类型(续签/新签)");
					return map;
				}

				if ("2".equals(listMap.get(i).getProtocol_category())) {
					/*
					 * if (null == listMap.get(i).getPush_code() ||
					 * "".equals(listMap.get(i).getPush_code())) {
					 * map.put("info", "第" + (i + 1) +
					 * "行，协议类型为代理协议时，不能为空"); return map; }
					 */
					if (null == listMap.get(i).getParty_a() || "".equals(listMap.get(i).getParty_a())) {
						map.put("info", "第" + (i + 1) + "行甲方数据不能为空");
						return map;
					}
					if (null == listMap.get(i).getParty_b() || "".equals(listMap.get(i).getParty_b())) {
						map.put("info", "第" + (i + 1) + "行乙方数据不能为空");
						return map;
					}
					/*
					 * if (null == listMap.get(i).getIns_branchname() ||
					 * "".equals(listMap.get(i).getIns_branchname())) {
					 * map.put("info", "第" + (i + 1) + "行乙方归属保险公司机构名称不能为空");
					 * return map; } if (null == listMap.get(i).getIns_branch()
					 * || "".equals(listMap.get(i).getIns_branch())) {
					 * map.put("info", "第" + (i + 1) + "行乙方归属保险公司机构编码不能为空");
					 * return map; } if (null == listMap.get(i).getIsconsult()
					 * || "".equals(listMap.get(i).getIsconsult())) {
					 * map.put("info", "第" + (i + 1) + "行汽车信息咨询不能为空"); return
					 * map; } if (null == listMap.get(i).getBank_name() ||
					 * "".equals(listMap.get(i).getBank_name())) {
					 * map.put("info", "第" + (i + 1) + "行乙方开户行不能为空"); return
					 * map; } if (null == listMap.get(i).getBank_code() ||
					 * "".equals(listMap.get(i).getBank_code())) {
					 * map.put("info", "第" + (i + 1) + "行乙方银行账号不能为空"); return
					 * map; }
					 */
				}
				if ("3".equals(listMap.get(i).getProtocol_category())) {
					if (null == listMap.get(i).getParty_a() || "".equals(listMap.get(i).getParty_a())) {
						map.put("info", "第" + (i + 1) + "行甲方数据不能为空");
						return map;
					}
					if (null == listMap.get(i).getParty_b() || "".equals(listMap.get(i).getParty_b())) {
						map.put("info", "第" + (i + 1) + "行乙方数据不能为空");
						return map;
					}
				}
				if (null == listMap.get(i).getProtocol_category() || "".equals(listMap.get(i).getProtocol_category())) {
					map.put("info", "第" + (i + 1) + "行协议类型不能为空");
					return map;
				}
				if (null == listMap.get(i).getContract_type() || "".equals(listMap.get(i).getContract_type())) {
					map.put("info", "第" + (i + 1) + "行合同类型不能为空");
					return map;
				}
				// 判断当协议类型为保险公司协议时，保险公司机构不能为空 当协议类型为代理协议时，维修推荐不为空
				if ("1".equals(listMap.get(i).getProtocol_category())) {
					if (null == listMap.get(i).getIns_branch() || "".equals(listMap.get(i).getIns_branch())) {
						map.put("info", "第" + (i + 1) + "行，协议类型为保险公司类型时，保险公司所属机构编码不能为空");
						return map;
					}
					if (null == listMap.get(i).getIns_branchname() || "".equals(listMap.get(i).getIns_branchname())) {
						map.put("info", "第" + (i + 1) + "行，协议类型为保险公司类型时，保险公司所属机构名称不能为空");
						return map;
					}
				}
				// 判断协议号必须由数字组成
				boolean isNumFlag = isNum(listMap.get(i).getAgreement_no());
				if (!isNumFlag) {
					map.put("info", "第" + (i + 1) + "行协议号" + listMap.get(i).getAgreement_no() + "包含其他字符");
					return map;
				}
				if (null == listMap.get(i).getSup_protocol_no() || "".equals(listMap.get(i).getSup_protocol_no())) {
					// 判断协议号是否已经存在
					boolean flag = protocolManageDao.checkAgreement_no(listMap.get(i));
					if (!flag) {
						map.put("info", "第" + (i + 1) + "行协议号" + listMap.get(i).getAgreement_no() + "已经存在");
						return map;
					}
				}
				// 判断导入中介机构是否存储
				IProtocolManageModel model = new ProtocolManageModel();
				model.setBranch_id(listMap.get(i).getBranch_id());
				model.setBranch_name(listMap.get(i).getBranch_name());
				model.setIns_branch(listMap.get(i).getIns_branch());
				model.setIns_branchname(listMap.get(i).getIns_branchname());
				Integer count = protocolManageDao.findSysBranch(model);
				if (count == 0) {
					map.put("info", "第" + (i + 1) + "行中介机构编码" + listMap.get(i).getBranch_id() + ",与"
							+ listMap.get(i).getBranch_name() + "名称不匹配");
					return map;
				}
				if (!"3".equals(listMap.get(i).getProtocol_category())) {

					// 判断导入保险机构是否存在
					Integer countCpy = protocolManageDao.findCpyBranch(model);
					if (countCpy == 0) {
						map.put("info", "第" + (i + 1) + "行保险公司机构编码" + listMap.get(i).getIns_branch() + ",与"
								+ listMap.get(i).getIns_branchname() + "名称不匹配");
						return map;
					}
				}
				// 判断导入开始日期小于结束日期
				Date Dateofsign = DateUtil.string2Date(listMap.get(i).getDateofsign(), "yyyy-MM-dd");
				Date StartDate = DateUtil.string2Date(listMap.get(i).getStartdate(), "yyyy-MM-dd");
				Date endDate = DateUtil.string2Date(listMap.get(i).getEnddate(), "yyyy-MM-dd");

				if (Dateofsign.getTime() > StartDate.getTime()) {
					map.put("info", "第" + (i + 1) + "行，签订日期大于生效日期");
					return map;
				}
				if (StartDate.getTime() > endDate.getTime()) {
					map.put("info", "第" + (i + 1) + "行，生效日期大于结束日期");
					return map;
				}
				Date getSup_protocol_stadate = listMap.get(i).getSup_protocol_stadate();
				if(null!=getSup_protocol_stadate&&!"".equals(getSup_protocol_stadate)&&getSup_protocol_stadate.getTime()>endDate.getTime()){
					map.put("info", "第" + (i + 1) + "行，补充协议生效日期大于结束日期");
					return map;
				}
			}
		}
		/*
		 * for (ProtocolManageModel model : listMap) { if (null ==
		 * model.getAgreement_no() || "".equals(model.getAgreement_no())) {
		 * map.put("info", "协议号不能为空"); return map; } if (null ==
		 * model.getProtocol_category() ||
		 * "".equals(model.getProtocol_category())) { map.put("info",
		 * "协议类型不能为空"); return map; } if (null == model.getContract_type() ||
		 * "".equals(model.getContract_type())) { map.put("info", "合同类型不能为空");
		 * return map; } if (null == model.getBranch_id() ||
		 * "".equals(model.getBranch_id())) { map.put("info", "中介公司所属机构编码不能为空");
		 * return map; } if (null == model.getBranch_name() ||
		 * "".equals(model.getBranch_name())) { map.put("info",
		 * "中介公司所属机构名称不能为空"); return map; } if (null == model.getParty_a() ||
		 * "".equals(model.getParty_a())) { map.put("info", "甲方数据不能为空"); return
		 * map; } if (null == model.getParty_b() ||
		 * "".equals(model.getParty_b())) { map.put("info", "乙方数据不能为空"); return
		 * map; } if (null == model.getDateofsign() ||
		 * "".equals(model.getDateofsign())) { map.put("info", "协议签订日期不能为空");
		 * return map; } if (null == model.getStartdate() ||
		 * "".equals(model.getStartdate())) { map.put("info", "协议生效起期不能为空");
		 * return map; } if (null == model.getEnddate() ||
		 * "".equals(model.getEnddate())) { map.put("info", "协议生效止期不能为空");
		 * return map; } if (null == model.getStatus() ||
		 * "".equals(model.getStatus())) { map.put("info", "协议状态不能为空"); return
		 * map; } if (null == model.getSign_type() ||
		 * "".equals(model.getSign_type())) { map.put("info", "签订类型不能为空");
		 * return map; } if (null != model.getSup_protocol_no() &&
		 * !"".equals(model.getSup_protocol_no())) { if (null ==
		 * model.getSup_protocol_stadate() ||
		 * "".equals(model.getSup_protocol_stadate()) || null ==
		 * model.getSup_protocol_content() ||
		 * "".equals(model.getSup_protocol_content())) { map.put("info",
		 * "补充协议编号不为空,请补充补充协议内容和生效日期"); return map; } } if ("2" ==
		 * model.getProtocol_category()) { if (null == model.getInsBranch_name()
		 * || "".equals(model.getInsBranch_name())) { map.put("info",
		 * "乙方归属保险公司机构名称不能为空"); return map; } if (null == model.getIns_branch()
		 * || "".equals(model.getIns_branch())) { map.put("info",
		 * "乙方归属保险公司机构编码不能为空"); return map; } if (null == model.getIsconsult()
		 * || "".equals(model.getIsconsult())) { map.put("info", "汽车信息咨询不能为空");
		 * return map; } if (null == model.getBank_name() ||
		 * "".equals(model.getBank_name())) { map.put("info", "乙方开户行不能为空");
		 * return map; } if (null == model.getBank_code() ||
		 * "".equals(model.getBank_code())) { map.put("info", "乙方银行账号不能为空");
		 * return map; } } boolean flag =
		 * protocolManageDao.checkAgreement_no(model); if(!flag){
		 * map.put("info", "协议号"+model.getAgreement_no()+"已经存在"); return map; }
		 * }
		 */
		map.put("info", "数据验证通过");
		return map;
	}

	@Override
	public ReturnMsg queryProtocolAll(IProtocolManageModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<ProtocolManageModel> list = protocolManageDomain.queryProtocolAll(model);
		// 获取签订状态
		List<EnumEntity> listSignType = enumDao.findEnum(Constants.PROTOCOL_SIGN_TYPE);
		// 获取数据类型
		List<EnumEntity> listStatus = enumDao.findEnum(Constants.PROTOCOL_STATUS);
		// 获取协议合同类型
		List<Map<String, Object>> listCon = this.getProtocolType();
		for (IProtocolManageModel entity : list) {
			for (EnumEntity signEntity : listSignType) {
				if (null != entity.getSign_type() && entity.getSign_type().equals(signEntity.getCode())) {
					entity.setSign_type(signEntity.getName());
				}
			}
			for (EnumEntity statusEntity : listStatus) {
				if (null != entity.getStatus() && entity.getStatus().equals(statusEntity.getCode())) {
					entity.setStatus(statusEntity.getName());
				}
			}
			for (Map<String, Object> map : listCon) {
				if (null != entity.getProtocol_category() && entity.getProtocol_category().equals(map.get("proCode"))) {
					entity.setProtocol_category((String) map.get("proName"));
					List<Map<String, Object>> listConType = (List<Map<String, Object>>) map.get("contractList");
					for (Map<String, Object> conType : listConType) {
						if (null != entity.getContract_type()
								&& entity.getContract_type().equals(conType.get("conCode"))) {
							entity.setContract_type(conType.get("conName").toString());
						}
					}
				}
			}
		}
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg addProtocolPdf(IProtocolManageModel model) {
		protocolManageDomain.addProtocolPdf(model);
		return null;
	}

	@Override
	public List<ProtocolCategoryModel> findCategory() {
		// TODO Auto-generated method stub
		return protocolManageDomain.findCategory();
	}

	@Override
	public List<ContractType> findContract() {
		// TODO Auto-generated method stub
		return protocolManageDomain.findContract();
	}

	/**
	 * 获取协议类型和合同类型
	 */
	public List<Map<String, Object>> getProtocolType() {
		// 协议类型
		List<ProtocolCategoryModel> listPro = new ArrayList<>();
		// 合同类型
		List<ContractType> listCon = new ArrayList<>();
		// 获取协议信息
		listPro = protocolManageDomain.findCategory();
		// 获取合信息
		listCon = protocolManageDomain.findContract();
		List<Map<String, Object>> listMap = new ArrayList<>();
		for (ProtocolCategoryModel proModel : listPro) {
			List<Map<String, Object>> contractList = new ArrayList<>();
			for (ContractType contract : listCon) {
				if (proModel.getCode().equals(contract.getParentId())) {
					Map<String, Object> conMap = new HashMap<>();
					conMap.put("conCode", contract.getCode());
					conMap.put("conName", contract.getName());
					contractList.add(conMap);
				}
			}
			Map<String, Object> map = new HashMap<>();
			map.put("proCode", proModel.getCode());
			map.put("proName", proModel.getName());
			map.put("contractList", contractList);
			listMap.add(map);
		}
		return listMap;
	}

	/**
	 * 根据协议号补全续签数据
	 */
	public List<ProtocolManageModel> singType(List<ProtocolManageModel> list) {
		List<ProtocolManageModel> listSign = new ArrayList<>();
		for (ProtocolManageModel model : list) {
			if ("0".equals(model.getSign_type())) {
				ProtocolManageModel model01 = new ProtocolManageModel();
				model01.setAgreement_no(model.getAgreement_no());
				// 根据协议号查询数据
				List<ProtocolManageModel> listModel = protocolManageDomain.queryProtocolsAgr(model01);
				if (!listModel.isEmpty() && listModel.size() == 1) {
					for (ProtocolManageModel imodel : listModel) {
						imodel.setAgreement_no(
								this.getAgreement_no(imodel.getProtocol_category(), imodel.getBranch_id()));
						imodel.setStartdate(model.getStartdate());
						imodel.setEnddate(model.getEnddate());
						imodel.setStatus(model.getStatus());
						imodel.setSign_type("0");
						listSign.add(imodel);
					}
				}
			} else {
				listSign.add(model);
			}
		}
		return listSign;
	}

	/**
	 * 判断当前协议号是否由存数字组成
	 */
	public static boolean isNum(String agreementNo) {
		String regex = "^[0-9]+$";
		return agreementNo.matches(regex);
	}

	/**
	 * 生成协议号
	 */
	public String getAgreement_no(String protocol_type, String branch_id) {
		String agreement_no = "";
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		// 获取当前的合同类型
		if (protocol_type.length() < 2) {
			agreement_no = "0" + protocol_type;
		} else {
			agreement_no = protocol_type;
		}
		if (branch_id.length() > 4) {
			branch_id = branch_id.substring(0, 4);
		}
		agreement_no += branch_id + year;
		// 处理当前表中数据
		ProtocolManageModel model = protocolManageDao.findAgreementNo();
		String agreement_code = "00001";
		if (null != model) {
			Integer i = Integer.parseInt(agreement_code) + 1;
			agreement_code = i + "";
			while (agreement_code.toString().length() < 5) {
				agreement_code = "0" + agreement_code;
			}
		}
		return agreement_no + agreement_code;
	}
}
