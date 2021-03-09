package com.newtouch.organization.webapp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.organization.domain.IAgencyDomain;
import com.newtouch.organization.model.vo.IAgencyModel;
import com.newtouch.organization.model.vo.impl.AgencyModel;
import com.newtouch.organization.webapp.service.IAgencyService;

@Service
public class AgencyService extends ServerBase implements IAgencyService {
	@Autowired
	IAgencyDomain iagencyDomain;

	// 中介管理查询
	@Override
	public ReturnMsg selectAgency(IAgencyModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IAgencyModel> list = iagencyDomain.selectAgency(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	// 中介管理维护查询
	@Override
	public ReturnMsg QueryAgencyInfo(IAgencyModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IAgencyModel model1 = iagencyDomain.QueryAgencyInfo(model);
		returnMsg.setDataTable(TransHelper.obj2map(model1));
		return returnMsg;
	}

	// 根据修改代理机构编码
	@Override
	public Integer updateAgencyNO(String repair_coding_mdf, String repair_coding) {
		int count = iagencyDomain.updateAgencyNO(repair_coding_mdf, repair_coding);

		return count;

	}

	// 中介管理维护保存
	@Override
	public ReturnMsg insertAgency(IAgencyModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		@SuppressWarnings("unused")
		Integer flag = iagencyDomain.insertAgency(model);
		return returnMsg;
	}

	// 根据机构树查协议号
	@Override
	public ReturnMsg queryAgreement(IAgencyModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IAgencyModel> list1 = iagencyDomain.queryAgreement(model);
		returnMsg.setDataList(TransHelper.list2MapList(list1));
		return returnMsg;

	}

	// 根据协议号查询时间
	@Override
	public Integer queryAgreementNo(IAgencyModel model) {

		int count = iagencyDomain.queryAgreementNo(model);

		return count;
	}

	// 明细
	@Override
	public ReturnMsg selectUpdateAgency(IAgencyModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IAgencyModel model2 = iagencyDomain.selectUpdateAgency(model);
		returnMsg.setDataTable(TransHelper.obj2map(model2));
		return returnMsg;
	}

	// 中介表查询协议
	@Override
	public List<Map<String, Object>> queryRelationAn(AgencyModel model) {
		List<Map<String, Object>> list2 = iagencyDomain.queryRelationAn(model);
		list2.size();
		return list2;
	}

	// 删除
	@Override
	public Integer deleteAgency(List<String> list, String repair_coding) {
		Integer flag = iagencyDomain.deleteAgency(list, repair_coding);
		return flag;
	}

	// 数据校验
	@SuppressWarnings("unused")
	private ReturnMsg checkMessage(Map<String, Object> map, int i) {
		ReturnMsg returnMsg = new ReturnMsg();
		// 验证信息是否为空
		String agency_no = "";
		/*try {
			agency_no = map.get("agency_no").toString();
		} catch (Exception e) {
			returnMsg.setFailMessage(new Message("", "第" + (i + 1) + "合作代理机构编码不能为空"));
			return returnMsg;
		}*/
		if (map.get("org_id") == null) {
			returnMsg.setFailMessage(new Message("", "第" + (i + 1) + "财险公司编码不能为空"));
			return returnMsg;
		}
		if (map.get("agency_name").equals("")) {
			returnMsg.setFailMessage(new Message("", "第" + (i + 1) + "合作代理机构名称不能为空"));
			return returnMsg;
		}
		/*if (map.get("social_code").equals("")) {
			returnMsg.setFailMessage(new Message("", "第" + (i + 1) + "统一社会信用编码不能为空"));
			return returnMsg;
		}*/
		// if(map.get("permission_license").equals("")){
		// returnMsg.setFailMessage(new Message("","第"+(i+1)+"许可证编号不能为空"));
		// return returnMsg;
		// }
		// if(map.get("found_date").equals("")){
		// returnMsg.setFailMessage(new Message("","第"+(i+1)+"成立时间不能为空"));
		//
		// return returnMsg;
		// }
		// if(map.get("agency_address").equals("")){
		// returnMsg.setFailMessage(new Message("","第"+(i+1)+"地址不能为空"));
		// return returnMsg;
		// }
		// String regex =
		// "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		
		if(map.containsKey("agency_phone")){
			if (!map.get("agency_phone").equals("")&&null!=map.get("agency_phone")) {
				String agency_phone = map.get("agency_phone").toString();
				if (!agency_phone.matches("^[0-9]*$")) {
					returnMsg.setFailMessage(new Message("", "第" + (i + 1) + "行电话应为数字"));
					return returnMsg;
				}
			}
		}
		
		return returnMsg;

	}

	// 校验在数据库中是否重复
	private ReturnMsg repeatExcel(List<String> eList) {
		ReturnMsg returnMsg = new ReturnMsg();

		List<String> remove_list = new ArrayList<String>(); // 要删除的list
		List<String> add_list = new ArrayList<String>();
		add_list.addAll(eList);
		List<String> dblistMap = iagencyDomain.queryExcel(); // 查询数据库的list
		if (dblistMap.size() > 0) {
			for (int j = 0; j < dblistMap.size(); j++) {
				if (eList.contains(dblistMap.get(j))) {

					// 重复的先删除
					remove_list.add((String) dblistMap.get(j));
					// remove_list.add("11");
				}
			}
		}
		// 删除重复数据库
		if (remove_list.size() > 0) {
			iagencyDomain.deleteExcel(remove_list);
			returnMsg.setSuccessMessage(new Message("", "已删除重复数据！"));
		}
		return returnMsg;
	}

	// 导入
	@Override
	public ReturnMsg makecheckInfo(Map<String, List<Object>> excelMap) {
		ReturnMsg returnMsg = this.checkInfo(excelMap);
		if (!returnMsg.isSuccessflag()) {
			return returnMsg;
		} else {
			returnMsg = this.callDaoInfo(excelMap);
			return returnMsg;
		}
	}

	// 校验是否为空
	@SuppressWarnings("unchecked")
	public ReturnMsg checkInfo(Map<String, List<Object>> excelMap) {
		ReturnMsg returnMsg = new ReturnMsg();
		String SHEETNAME = "Sheet1";
		boolean flag = false;
		for (String key : excelMap.keySet()) {
			if (excelMap.get(key).size() > 0)
				flag = flag || true;
		}
		// 如果没有数据返回添加数据消息
		if (!flag) {
			returnMsg.setFailMessage(new Message("", "你导入的表格没有数据，请重新导入！"));
			return returnMsg;
		}
		int size2 = excelMap.get(SHEETNAME).size();
		List<String> eList = new ArrayList<String>(); // 表格获取的list
		for (int i = 0; i < size2; i++) {
			Map<String, Object> map = (Map<String, Object>) excelMap.get(SHEETNAME).get(i);
			// 校验数据是否为空
			returnMsg = this.checkMessage(map, i);
			if (!returnMsg.isSuccessflag()) {
				return returnMsg;
			}
			// 删除重复数据
			String repair_coding = map.get("repair_coding").toString();
			if (!repair_coding.equals("")) {
				eList.add(repair_coding);
			}
		}
		returnMsg = repeatExcel(eList);
		if (!returnMsg.isSuccessflag()) {
			return returnMsg;
		}

		return returnMsg;
	}

	private ReturnMsg callDaoInfo(Map<String, List<Object>> excelMap) {
		ReturnMsg returnMsg = new ReturnMsg();
		String SHEETNAME = "Sheet1";
		for (int i = 0; i < excelMap.get(SHEETNAME).size(); i++) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) excelMap.get(SHEETNAME).get(i);
			// System.out.println(map.get("agency_phone").getClass());
			if(map.containsKey("agency_phone")){
			map.put("agency_phone", new BigDecimal(Double.valueOf(map.get("agency_phone").toString())).toString());
			}
			// System.out.println(map.get("agency_phone"));
			iagencyDomain.importInfo(map);
			if (!returnMsg.isSuccessflag()) {
				returnMsg.setFailMessage(new Message("", "导入失败！"));
			}

		}
		returnMsg.setSuccessMessage(new Message("", "导入成功！"));
		return returnMsg;
	}

	// 统一社会信用编码保存
	@Override
	public ReturnMsg updateSocialCode(AgencyModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		iagencyDomain.updateSocialCode(model);
		return returnMsg;

	}

	@Override
	public int deleteAgencyAll(String agency_no) {
		Integer flag = iagencyDomain.deleteAgencyAll(agency_no);
		return flag;
	}

	@Override
	public int deleteAreement(String agency_no) {
		Integer flag = iagencyDomain.deleteAreement(agency_no);
		return flag;
	}
	// 查询excel表格
	// @Override
	// public List<String> queryExcel(String repair_coding) {
	// List<String> lists = iagencyDomain.queryExcel();
	// return lists;
	// }

	public static void main(String[] args) {
		Double a = 1.7611645515E10;
		System.out.println(new BigDecimal(a).toString());
	}

	// 查询推荐维修码是否重复
	@Override
	public Integer queryrepair_coding(IAgencyModel model) {
		Integer count = iagencyDomain.queryrepair_coding(model);

		return count;
	}
}
