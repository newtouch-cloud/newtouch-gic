package com.newtouch.peoplemanage.webapp.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.excel.ExcelUtil;
import com.newtouch.peoplemanage.dao.IPersonManageDao;
import com.newtouch.peoplemanage.domain.IPersonManageDomain;
import com.newtouch.peoplemanage.model.po.EducationVOModel;
import com.newtouch.peoplemanage.model.po.GleaderInfoVOModel;
import com.newtouch.peoplemanage.model.po.IEducationVOModel;
import com.newtouch.peoplemanage.model.po.IEmpFamilyVOModel;
import com.newtouch.peoplemanage.model.po.IEmpNoteVOModel;
import com.newtouch.peoplemanage.model.po.IEnumInfoVOModel;
import com.newtouch.peoplemanage.model.po.IGleaderInfoVOModel;
import com.newtouch.peoplemanage.model.po.ILicenseInfoVOModel;
import com.newtouch.peoplemanage.model.vo.IPersonManageVOModel;
import com.newtouch.peoplemanage.model.vo.PersonManageVOModel;
import com.newtouch.newutil.PersionPDFUtil;

/**
 * 人员管理service实现类
 * 
 * @author Ming Ying
 * @time 2017-11-30
 */
@Service
public class PersonManageServiceImpl extends ServerBase implements IPersonManageService {
	@Autowired
	private IPersonManageDomain personManageDomain;
	
	@Autowired
	private IPersonManageDao personManagedao;

	@Override
	public ReturnMsg queryPersonVOs(IPersonManageVOModel model) {
		// 初始化返回参数
		ReturnMsg msg = new ReturnMsg();
		// 获取查询结果集
		List<IPersonManageVOModel> relist = personManageDomain.queryPersonVOs(model);
		//List<IPersonManageVOModel> list = new ArrayList<IPersonManageVOModel>();
		/*for (int i=0; i<relist.size(); i++){
			IPersonManageVOModel person = relist.get(i);
			String branch_level = personManagedao.queryGleaderPermission(person);
			if("0".equals(branch_level) || "1".equals(branch_level)){
				person.setMark("1");
			}else{
				person.setMark("0");
			}
			list.add(person);
		}*/
		// 设置返回参数
		msg.setDataList(TransHelper.list2MapList(relist));
		return msg;
	}

	public ReturnMsg PersonInfoView(String person_no) {
		// 初始化返回参数
		ReturnMsg msg = new ReturnMsg();
		// 获取查询结果集
		IPersonManageVOModel remodel = personManageDomain.queryPersonVOById(person_no);
		List<IPersonManageVOModel> list = personManagedao.queryEmpNoteInfo(person_no);
		if(list!=null && list.size()>0){
			String content_code = list.get(0).getContent_code();
			if(content_code != null){
				remodel.setContent_code(content_code);
			}
		}
		msg.setDataTable(TransHelper.obj2map(remodel));
		return msg;
	}

	// 查询某个人的家庭成员信息
	@Override
	public ReturnMsg queryPersonFamily(String person_id) {
		// 初始化返回参数
		ReturnMsg msg = new ReturnMsg();
		// 获取查询结果集
		List<IEmpFamilyVOModel> list = personManageDomain.queryPersonFamily(person_id);
		// 设置返回参数
		msg.setDataList(TransHelper.list2MapList(list));
		return msg;

	}

	@Override
	public ReturnMsg queryPersonEducation(IEducationVOModel model) {
		ReturnMsg msg = new ReturnMsg();
		// 获取查询结果集
		List<IEducationVOModel> list = personManageDomain.queryPersonEducation(model);
		// 设置返回参数
		msg.setDataList(TransHelper.list2MapList(list));
		return msg;
	}

	@Override
	public ReturnMsg queryEmpNote(String person_no) {
		ReturnMsg msg = new ReturnMsg();
		List<IEmpNoteVOModel> list = personManageDomain.queryEmpNote(person_no);
		msg.setDataList(TransHelper.list2MapList(list));
		return msg;
	}

	@Override
	public ReturnMsg queryLicenseInfo(String person_no) {
		ReturnMsg msg = new ReturnMsg();
		List<ILicenseInfoVOModel> list = personManageDomain.queryLicenseInfo(person_no);
		msg.setDataList(TransHelper.list2MapList(list));
		return msg;
	}

	@Override
	public ReturnMsg queryPositionInfo(String person_no) {
		ReturnMsg msg = new ReturnMsg();
		List<IPersonManageVOModel> list = personManageDomain.queryPositionInfo(person_no);
		msg.setDataList(TransHelper.list2MapList(list));
		return msg;
	}

	@Override
	public ByteArrayOutputStream sentpersioninfo(String person_no) {
		IPersonManageVOModel remodel = personManageDomain.queryPersonVOById(person_no);
		ByteArrayOutputStream employee = PersionPDFUtil.fillEmployee(remodel);
		return employee;
	}

	@Override
	public ByteArrayOutputStream sentzhiyezheng(String person_no) {
		IPersonManageVOModel remodel = personManageDomain.queryPersonVOById(person_no);
		IEducationVOModel model = new EducationVOModel();
		model.setPerson_no(person_no);
		model.setType("E");
		List<IEducationVOModel> education = personManageDomain.queryPersonEducation(model);
		model.setType("W");
		List<IEducationVOModel> works = personManageDomain.queryPersonEducation(model);
		ByteArrayOutputStream fillOperation = PersionPDFUtil.fillOperation(remodel, education, works);
		return fillOperation;
	}

	@Override
	public String getbranch_level(String branch_id) {
		return personManageDomain.getbranch_level(branch_id);
	}

	@Override
	public List<BranchModel> queryBranchLevel() {
		// TODO Auto-generated method stub
		return personManageDomain.queryBranchLevel();
	}

	@Override
	public List<IPersonManageVOModel> queryPersonInfoList(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		List<IPersonManageVOModel> relist = personManageDomain.queryPersonVOs(model);
		return relist;
	}
	
	@Override
	public String importPersonInfos(MultipartFile file,IUserModel user) throws IOException, ParseException {
		//为空校验开始
        //ValidateHelper.IsNullOrEmptyThrowException(uploadFileName, "文件不可为空，请检查。");//文件为空校验
        String info = "";// 导入的回显消息
        Map<String, List<Object>> dataMap = readDataFromExcel2(file);
		//校验数据是否完全正确
		List<String> msgList = personManageDomain.checkTraPlanInfoIsTrue(dataMap);
		//List<String> msgList = new ArrayList<String>();
		if (msgList.size() == 0) { //校验数据是否完全正确
			//将excel数据成lsit
			List<PersonManageVOModel> personInfoList = setPropertyPersonManageVOModel(dataMap);
			//解析人员信息入库    
			for (PersonManageVOModel personInfo : personInfoList) {
				personManagedao.updatePersonInfo(personInfo);
				if(personInfo.getPerson_class()!=null && ("高管人员").equals(personInfo.getPerson_class())){
					personInfo.setGleader("1");
					int count = personManagedao.findGleaderInfo(personInfo.getPerson_no());
					if(count == 0){
						personManagedao.importInsertGleaderInfo(personInfo);
					}else{
						personManagedao.importUpdateGleaderInfo(personInfo);
					}
				}
			}
			info = "导入数据成功";  
		}else{
			 for(String s :msgList){
				 info = info+s+"\\r\\n";
			 }
			 info = info+"请核对有误的数据再重新导入！";
		}
		
		return info;
	}
	
	
	/**
	 * 将excel封装成List
	* 
	* @param dataMap
	* @return List<CmainPolicyVOMModel>
	* @description:
	 */
	private List<PersonManageVOModel> setPropertyPersonManageVOModel(Map<String, List<Object>> dataMap) {
		List<PersonManageVOModel> personList = new ArrayList<PersonManageVOModel>();
		for (Map.Entry<String, List<Object>> entry : dataMap.entrySet()) {
			List<Object> practice = entry.getValue();
			if (practice.size() < 1) { // 如果长度小于1 则continue
				continue;
			}
			for (int i = 0; i < practice.size(); i++) {
				PersonManageVOModel person = new PersonManageVOModel();
				Map praMap = (HashMap) practice.get(i);
				person.setSeq_id(ActionHelper.getNullToStr(praMap.get("seq_id")));//序号
				person.setPerson_no(ActionHelper.getNullToStr(praMap.get("person_no")));//员工编码                             
				person.setPerson_name(ActionHelper.getNullToStr(praMap.get("person_name")));//员工姓名 
				person.setPerson_status(ActionHelper.getNullToStr(praMap.get("person_status")));//员工状态
				person.setBankcard_number(ActionHelper.getNullToStr(praMap.get("bankcard_number")));//银行账号
				person.setBank_name(ActionHelper.getNullToStr(praMap.get("bank_name")));//开户行
				if(!ValidateHelper.IsNullOrEmpty(ActionHelper.getNullToStr(praMap.get("retire_time"))))
				person.setRetire_time(DateUtil.string2Date(praMap.get("retire_time")));//退休时间
				person.setPerson_class(ActionHelper.getNullToStr(praMap.get("person_class")));//人员分类	
				person.setApproval_time(ActionHelper.getNullToStr(praMap.get("approval_time")));//高管批复时间
				person.setApproval_file(ActionHelper.getNullToStr(praMap.get("approval_file")));//高管批复文号
				person.setEmployment_term(ActionHelper.getNullToStr(praMap.get("employment_term")));//高管聘期
				personList.add(i, person);
			}
		}
		return personList;
	}
	
	
		// 设置导入人员的信息，取数据使用
		private Map<String, List<Object>> readDataFromExcel2(MultipartFile excelFile) throws IOException {
				ExcelUtil util = new ExcelUtil();
				// 导入信息对应导入模板的列字段
				String[] titles = new String[] {
				"seq_id" , //序号
				"person_no"	,//	员工编码
				"person_name"	,//	员工姓名
				"person_status"	,//	员工状态
				"bankcard_number"	,//	银行账号
				"bank_name"	,//	开户行
				"retire_time"	,//  退休时间
				"person_class"	,//	人员分类
				"approval_time"	,//	高管批复时间
				"approval_file"	,//	高管批复文号
				"employment_term"	,//	高管聘期
				};
				Map<String, List<Object>> resultMap = util.initSheet4Stream(
						excelFile.getInputStream(), new Object(), titles);
				return resultMap;
			}

		@Override
		public void modifySysEmployee(IPersonManageVOModel model) {
			// TODO Auto-generated method stub
			personManagedao.modifySysEmployee(model);
		}

		@Override
		public void modifySysEmployeeInfo(IPersonManageVOModel model) {
			// TODO Auto-generated method stub
			personManagedao.modifySysEmployeeInfo(model);
		}

		@Override
		public void modifySysAgentInfo(IPersonManageVOModel model) {
			// TODO Auto-generated method stub
			personManagedao.modifySysAgentInfo(model);
		}

		@Override
		public void modifySysEmpNote(IPersonManageVOModel model) {
			// TODO Auto-generated method stub
			personManagedao.modifySysEmpNote(model);
		}

		@Override
		public List<IEnumInfoVOModel> querySelectList(String up_enum) {
			// TODO Auto-generated method stub
			return personManagedao.querySelectList(up_enum);
		}

		@Override
		public ReturnMsg queryGleaderInfo(String person_no) {
			// TODO Auto-generated method stub
			ReturnMsg msg = new ReturnMsg();
			IGleaderInfoVOModel  gleaderInfo = personManagedao.queryGleaderInfo(person_no);
			String upload_approval_file = gleaderInfo.getUpload_approval_file();
			if(upload_approval_file!=null && !"".equals(upload_approval_file)){
				String filename = upload_approval_file.substring(upload_approval_file.lastIndexOf("/")+1);
				gleaderInfo.setFilename(filename);
			}
			msg.setDataTable(TransHelper.obj2map(gleaderInfo));
			return msg;
		}

		@Override
		public ReturnMsg updateApprovalFile(GleaderInfoVOModel gleaderInfo) {
			// TODO Auto-generated method stub
			ReturnMsg msg = new ReturnMsg();
			try{
				personManagedao.updateApprovalFile(gleaderInfo);
				msg.setSuccessMessage("上传成功");
			}catch(Exception e){
				msg.setFailMessage("上传失败");
				e.printStackTrace();
			}
			IGleaderInfoVOModel model = personManagedao.queryGleaderInfo(gleaderInfo.getPerson_no());
			String upload_approval_file = model.getUpload_approval_file();
			String filename = upload_approval_file.substring(upload_approval_file.lastIndexOf("/")+1);
			model.setFilename(filename);
			msg.setDataTable(TransHelper.obj2map(model));
			return msg;
		}

		@Override
		public ReturnMsg updateGleaderInfo(GleaderInfoVOModel gleaderInfo) {
			// TODO Auto-generated method stub
			ReturnMsg msg = new ReturnMsg();
			try{
				personManagedao.updateGleaderInfo(gleaderInfo);
				msg.setSuccessMessage("保存成功");
			}catch(Exception e){
				msg.setFailMessage("保存失败");
				e.printStackTrace();
			}
			IGleaderInfoVOModel model = personManagedao.queryGleaderInfo(gleaderInfo.getPerson_no());
			msg.setDataTable(TransHelper.obj2map(model));
			return msg;
		}
		
		//查询当前用户是否有操作高管的权限
		@Override
		public int queryUserDataAuth(IPersonManageVOModel personVO) {
			// TODO Auto-generated method stub
			return personManagedao.queryUserDataAuth(personVO);
		}

		@Override
		public Boolean findSysAgentInfo(IPersonManageVOModel model) {
			// TODO Auto-generated method stub
			int count = personManagedao.findSysAgentInfo(model);
			if(count == 0){
				return false;
			}else{
				return true;
			}
		}

		@Override
		public Boolean findSysEmpNote(IPersonManageVOModel model) {
			// TODO Auto-generated method stub
			int count = personManagedao.findSysEmpNote(model);
			if(count == 0){
				return false;
			}else{
				return true;
			}
		}

		@Override
		public void insertSysAgentInfo(IPersonManageVOModel model) {
			// TODO Auto-generated method stub
			personManagedao.insertSysAgentInfo(model);
		}

		@Override
		public void insertSysEmpNote(IPersonManageVOModel model) {
			// TODO Auto-generated method stub
			personManagedao.insertSysEmpNote(model);
		}

		@Override
		public Boolean findGleaderInfo(String person_no) {
			// TODO Auto-generated method stub
			int count = personManagedao.findGleaderInfo(person_no);
			if(count == 0){
				return false;
			}else{
				return true;
			}
		}

		@Override
		public ReturnMsg insertGleaderInfo(GleaderInfoVOModel gleaderInfo) {
			// TODO Auto-generated method stub
			ReturnMsg msg = new ReturnMsg();
			try{
				personManagedao.insertGleaderInfo(gleaderInfo);
				msg.setSuccessMessage("保存成功");
			}catch(Exception e){
				msg.setFailMessage("保存失败");
				e.printStackTrace();
			}
			IGleaderInfoVOModel model = personManagedao.queryGleaderInfo(gleaderInfo.getPerson_no());
			msg.setDataTable(TransHelper.obj2map(model));
			return msg;
		}

		@Override
		public void updatePersonClass(IPersonManageVOModel model) {
			// TODO Auto-generated method stub
			personManagedao.updatePersonClass(model);
		}
		@Override
		public void saveEmployeePosition(IPersonManageVOModel model) {
			personManagedao.saveEmployeePosition(model);
		}
		
		@Override
		public void saveEmployee(IPersonManageVOModel model) {
			personManagedao.saveEmployee(model);
		}
		
		@Override
		public void saveEmployeeInfo(IPersonManageVOModel model) {
			personManagedao.saveEmployeeInfo(model);
		}
		
		@Override
		public void saveEduction(IPersonManageVOModel model) {
			personManagedao.saveEduction(model);
		}
		@Override
		public String queryTeamNameBranchName(IPersonManageVOModel model) {
			IPersonManageVOModel salesVOModel=personManageDomain.queryTeamNameBranchName(model);
			String returnInfo="";
			//判断根据组织代码查找到的信息是否为空
			if (!model.getTeam_id().equals("")&&salesVOModel!=null) {
				//信息不为空时拼接相应需要反馈的信息
				returnInfo="{isSuccess:'"+true+"',team_name:'"+salesVOModel.getTeam_name()+"',branch_id:'"+salesVOModel.getBranch_id()+"',branch_name:'"+salesVOModel.getBranch_name()+"'}";
			}else{
				//返回失败
				returnInfo="{isSuccess:'"+false+"'}";
			}
			return returnInfo;
		}
	@Override
	public void modifySysEmployee1(IPersonManageVOModel personVO) {
		personManagedao.modifySysEmployee1(personVO);
	}
		@Override
		public String findSysEmployor(String personNo) {
			// TODO Auto-generated method stub
			String person_no1 = personManagedao.findSysEmployor(personNo);
			if(person_no1==null||person_no1.equals("")) {
				person_no1=personNo+"001";
				return person_no1;
			}else {
				return person_no1;
			}
			
		}

}
