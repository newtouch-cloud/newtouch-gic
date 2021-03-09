package com.newtouch.peoplemanage.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.stringutil.StringUtil;
import com.newtouch.peoplemanage.dao.IPersonManageDao;
import com.newtouch.peoplemanage.model.po.IEducationVOModel;
import com.newtouch.peoplemanage.model.po.IEmpFamilyVOModel;
import com.newtouch.peoplemanage.model.po.IEmpNoteVOModel;
import com.newtouch.peoplemanage.model.po.ILicenseInfoVOModel;
import com.newtouch.peoplemanage.model.vo.IPersonManageVOModel;

@Service
public class PersonManageDomain implements IPersonManageDomain {

	@Autowired
	private IPersonManageDao personManagedao;

	@Override
	public List<IPersonManageVOModel> queryPersonVOs(IPersonManageVOModel model) {
		List<IPersonManageVOModel> list = personManagedao.queryPersonVOs(model);
		return list;
	}

	@Override
	public IPersonManageVOModel queryPersonVOById(String person_no) {
		IPersonManageVOModel remodel = personManagedao.queryPersonVOById(person_no);
		return remodel;
	}

	@Override
	public List<IEmpFamilyVOModel> queryPersonFamily(String person_id) {
		List<IEmpFamilyVOModel> list = personManagedao.queryPersonFamily(person_id);
		return list;
	}

	@Override
	public List<IEducationVOModel> queryPersonEducation(IEducationVOModel model) {
		List<IEducationVOModel> list = personManagedao.queryPersonEducation(model);
		return list;
	}

	@Override
	public List<IEmpNoteVOModel> queryEmpNote(String person_no) {
		List<IEmpNoteVOModel> list = personManagedao.queryEmpNote(person_no);
		return list;
	}

	@Override
	public List<ILicenseInfoVOModel> queryLicenseInfo(String person_no) {
		List<ILicenseInfoVOModel> list = personManagedao.queryLicenseInfo(person_no);
		return list;
	}

	@Override
	public List<IPersonManageVOModel> queryPositionInfo(String person_no) {
		List<IPersonManageVOModel> list = personManagedao.queryPositionInfo(person_no);
		return list;
	}

	@Override
	public String getbranch_level(String branch_id) {
		return personManagedao.getbranch_level(branch_id);
	}


	@Override
	public List<BranchModel> queryBranchLevel() {
		// TODO Auto-generated method stub
		return personManagedao.queryBranchLevel();
	}

	@Override
	public List<String> checkTraPlanInfoIsTrue(Map<String, List<Object>> dataMap) throws ParseException {
		List<String> msgList = new ArrayList<String>();
		int line = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date employment_term1 = null;//高管聘期
		Date approval_time1 = null;//高管批复时间
		//证件类型编码
		/*String certi_codes[] = { "11", "12", "13", "14", "15",
				"16", "17", "21", "22", "23", "24", "29", "31",
				"32", "33", "34", "99" };
		List<String> codes = Arrays.asList(certi_codes);*/
		for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
			List<Object> practice = entry.getValue();
			if (practice.size() < 1) { // 如果长度小于1 则continue
				continue;
			}
			
			for (int i = 0; i < practice.size(); i++) {
				line++;
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (HashMap<String, Object>) practice.get(i);
				// 获取校验字段
				String person_no = ActionHelper.getNullToStr(map.get("person_no"));//员工编码                             
				String person_name = ActionHelper.getNullToStr(map.get("person_name"));//员工姓名 
				String person_status = ActionHelper.getNullToStr(map.get("person_status"));//员工状态
				String bankcard_number = ActionHelper.getNullToStr(map.get("bankcard_number"));//银行账号
				String bank_name = ActionHelper.getNullToStr(map.get("bank_name"));//开户行
				
				Object retire_time = ActionHelper.getNullToStr(map.get("retire_time"));//退休时间
				String person_class = ActionHelper.getNullToStr(map.get("person_class"));//人员分类	
				String approval_time = ActionHelper.getNullToStr(map.get("approval_time"));//高管批复时间
				String approval_file = ActionHelper.getNullToStr(map.get("approval_file"));//高管批复文号
				String employment_term = ActionHelper.getNullToStr(map.get("employment_term"));//高管聘期
				
				//校验
				/*"person_no"	,//	员工编码
				"person_name"	,//	员工姓名
				"person_status"	,//	员工状态
				"bankcard_number"	,//	银行账号
				"bank_name"	,//	开户行
				"retire_time"	,//  退休时间
				"person_class"	,//	人员分类
				"approval_time"	,//	高管批复时间
				"approval_file"	,//	高管批复文号
				"employment_term"	,//	高管聘期*/
				
				if ("".equals(person_no.trim())) {
					msgList.add("第" + (i + 2) + "行，员工编码不能为空，请核对！");
				}else if(StringUtil.getBytesLength(person_no.trim())>50){
					msgList.add("第" + (i + 2) + "行，员工编码长度超出数据库设计范围，请核对！");
				}else{
					int count = personManagedao.findPersonIsExist(person_no.trim());
					if(count == 0){
						msgList.add("第" + (i + 2) + "行，员工编码在系统中不存在，请核对！");
					}
					if(count > 1){
						msgList.add("第" + (i + 2) + "行，员工编码在系统中多个，请核对！");
					}
					if(count == 1){
						if("".equals(person_name.trim())){
							msgList.add("第" + (i + 2) + "行，员工姓名不能为空，请核对！");
						}else if(StringUtil.getBytesLength(person_name.trim())>50){
							msgList.add("第" + (i + 2) + "行，员工姓名长度超出数据库设计范围，请核对！");
						}
						if("".equals(person_status.trim())){
							msgList.add("第" + (i + 2) + "行，员工状态不能为空，请核对！");
						}else if(StringUtil.getBytesLength(person_status.trim())>30){
							msgList.add("第" + (i + 2) + "行，员工状态长度超出数据库设计范围，请核对！");
						}/*else if(!"不在职".equals(person_status.trim()) || !"在职".equals(person_status.trim()) || !"离职".equals(person_status.trim())
								|| !"离退休".equals(person_status.trim()) || !"返聘".equals(person_status.trim())){
							msgList.add("第" + (i + 2)+ "行，员工状态类型不对,应为不在职、在职、离职、离退休、返聘，请核对。");
						}*/
						/*if("".equals(person_status.trim())){
							msgList.add("第" + (i + 2) + "行，员工状态不能为空，请核对！");
						}else if(StringUtil.getBytesLength(person_status.trim())>30){
							msgList.add("第" + (i + 2) + "行，员工状态长度超出数据库设计范围，请核对！");
							if(!"".equals(retire_time)){
								try {
									DateUtil.string2Date(retire_time);
								} catch (Exception e) {
									msgList.add("第" + (i + 2)+ "行，退休时间格式不正确，请核对。");
								}
							}
						}else if("不在职".equals(person_status.trim()) || "在职".equals(person_status.trim()) || "离职".equals(person_status.trim())
								|| "离退休".equals(person_status.trim()) || "返聘".equals(person_status.trim())){
							if("离退休".equals(person_status.trim())){
								if("".equals(retire_time)){
									msgList.add("第" + (i + 2) + "行，员工状态为离退休时,退休时间不能为空，请核对！");
								}else{
									try {
										DateUtil.string2Date(retire_time);
									} catch (Exception e) {
										msgList.add("第" + (i + 2)+ "行，退休时间格式不正确，请核对。");
									}
								}
							}
						}else{
							msgList.add("第" + (i + 2)+ "行，员工状态类型不对,应为不在职、在职、离职、离退休、返聘，请核对。");
						}*/
						/*if("".equals(bankcard_number.trim())){
							msgList.add("第" + (i + 2) + "行，银行账号不能为空，请核对！");
						}else if(StringUtil.getBytesLength(bankcard_number.trim())>50){
							msgList.add("第" + (i + 2) + "行，银行账号长度超出数据库设计范围，请核对！");
						}
						if("".equals(bank_name.trim())){
							msgList.add("第" + (i + 2) + "行，开户行不能为空，请核对！");
						}else if(StringUtil.getBytesLength(bank_name.trim())>50){
							msgList.add("第" + (i + 2) + "行，开户行长度超出数据库设计范围，请核对！");
						}*/
						if(!"".equals(bankcard_number.trim()) && StringUtil.getBytesLength(bankcard_number.trim())>50){
							msgList.add("第" + (i + 2) + "行，银行账号长度超出数据库设计范围，请核对！");
						}
						if(!"".equals(bank_name.trim()) && StringUtil.getBytesLength(bank_name.trim())>50){
							msgList.add("第" + (i + 2) + "行，开户行长度超出数据库设计范围，请核对！");
						}
						if(!"".equals(retire_time)){
							try {
								DateUtil.string2Date(retire_time);
							} catch (Exception e) {
								msgList.add("第" + (i + 2)+ "行，退休时间格式不正确，请核对。");
							}
						}
						if("".equals(person_class.trim())){
							msgList.add("第" + (i + 2) + "行，人员分类不能为空，请核对！");
						}else if(StringUtil.getBytesLength(person_class.trim())>255){
							msgList.add("第" + (i + 2) + "行，人员分类长度超出数据库设计范围，请核对！");
						}else if("高管人员".equals(person_class.trim()) || "业务人员".equals(person_class.trim()) || "非业务人员".equals(person_class.trim())){
							if("高管人员".equals(person_class.trim())){
								if("".equals(approval_time)){
									msgList.add("第" + (i + 2) + "行，当人员分类为高管人员时，高管批复时间不能为空，请核对！");
								}else{
									try {
										DateUtil.string2Date(approval_time);
									} catch (Exception e) {
										msgList.add("第" + (i + 2)+ "行，高管批复时间格式不正确，应为yyyy/mm/dd格式,请核对。");
									}
								}
								if("".equals(approval_file.trim())){
									msgList.add("第" + (i + 2) + "行，当人员分类为高管人员时，高管批复文号不能为空，请核对！");
								}else if(StringUtil.getBytesLength(approval_file.trim())>255){
									msgList.add("第" + (i + 2) + "行，高管批复文号超出数据库设计范围，请核对！");
								}
								if("".equals(employment_term)){
									msgList.add("第" + (i + 2) + "行，当人员分类为高管人员时，高管聘期不能为空，请核对！");
								}else{
									try {
										DateUtil.string2Date(employment_term);
									} catch (Exception e) {
										msgList.add("第" + (i + 2)+ "行，高管聘期格式不正确，应为yyyy/mm/dd格式,请核对。");
									}
								}
								/*if(!"".equals(approval_time) && !"".equals(employment_term)){
									try {
										employment_term1 = sdf.parse(employment_term);
										approval_time1 = sdf.parse(approval_time);
										if(employment_term1.after(approval_time1)){
											msgList.add("第" + (i + 2)+ "行，高管聘期大于高管批复时间,高管聘期应小于等于高管批复时间,请核对。");
										}
									}catch (Exception e) {
										msgList.add("第" + (i + 2)+ "行，高管聘期或高管批复时间格式不正确，应为yyyy/mm/dd格式,请核对。");
									}
								}*/
							}else{
								if(!"".equals(approval_time)){
									try {
										DateUtil.string2Date(approval_time);
									} catch (Exception e) {
										msgList.add("第" + (i + 2)+ "行，高管批复时间格式不正确，应为yyyy/mm/dd格式,请核对。");
									}
								}
								if(!"".equals(approval_file.trim()) && StringUtil.getBytesLength(approval_file.trim())>255){
									msgList.add("第" + (i + 2) + "行，高管批复文号超出数据库设计范围，请核对！");
								}
								if(!"".equals(employment_term)){
									try {
										DateUtil.string2Date(employment_term);
									} catch (Exception e) {
										msgList.add("第" + (i + 2)+ "行，高管聘期格式不正确，应为yyyy/mm/dd格式,请核对。");
									}
								}
								/*if(!"".equals(approval_time) && !"".equals(employment_term)){
									try {
										employment_term1 = sdf.parse(employment_term);
										approval_time1 = sdf.parse(approval_time);
										if(employment_term1.after(approval_time1)){
											msgList.add("第" + (i + 2)+ "行，高管聘期大于高管批复时间,高管聘期应小于等于高管批复时间,请核对。");
										}
									} catch (Exception e) {
										msgList.add("第" + (i + 2)+ "行，高管聘期或高管批复时间格式不正确，应为yyyy/mm/dd格式,请核对。");
									}
								}*/
							}
							
						}else{
							msgList.add("第" + (i + 2)+ "行，人员分类类型不对,应为高管人员、业务人员、非业务人员，请核对。");
						}
					}
				}
				
				
			}	
			
		}	
				if (line == 0) {
					msgList.add("excel中数据为空，请重新录入。");
				}
				return msgList;
			}


	@Override
	public IPersonManageVOModel queryTeamNameBranchName(IPersonManageVOModel model) {
		return personManagedao.queryTeamNameBranchName(model);
	}
}
