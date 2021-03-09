package com.ca.cacore.manage.domain.branch;

import com.ca.cacore.manage.dao.branch.IBranchDao;
import com.ca.cacore.manage.model.bo.*;
import com.ca.cacore.manage.model.vo.BranchVOModel;
import com.ca.cacore.manage.model.vo.BusinessLicenseHisModel;
import com.ca.cacore.manage.model.vo.IBranchStatusHisVOModel;
import com.ca.cacore.manage.model.vo.IBranchVOModel;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.newutil.string.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class BranchDomain implements IBranchDomain {
	@Autowired private IBranchDao branchDao;
	
	/**
	 * 查询所有机构信息 *
	 */
	@Override
	public List<IBranchVOModel> queryAllBranch(IBranchModel model) {
		return branchDao.queryAllBranch(model);
	}
	@Override
	public List<IBranchVOModel> exportBranchInfo(IBranchModel model){
		return branchDao.exportBranchInfo(model);
		
	};
	/**
	 * 查询某个机构信息
	 */
	@Override
	public IBranchModel getByBranch(IBranchModel model) {
		return branchDao.getByBranch(model);
	}
	
	/**
	 * 添加获取上级信息
	 */
	@Override
	public IBranchVOModel getBranchAddVO(IBranchModel model){
		//通过机构记录的主键得到机构信息
		IBranchModel branchModel =branchDao.getByBranch(model);
		//将要带人到下个页面的数据放到带信息的model里
		IBranchVOModel branchVOModel = new BranchVOModel();
		branchVOModel.setBranch_parentid(branchModel.getBranch_id());
		branchVOModel.setParent_branch_name(branchModel.getBranch_name());
		branchVOModel.setParent_branch_level(branchModel.getBranch_level());
		branchVOModel.setSeq_id(branchModel.getSeq_id());
		String branch_level = nextBranchLevel(model);
		String branch_level_name = branchDao.queryBranchLevelName(branch_level);
		branchVOModel.setBranch_level_name(branch_level_name);
		//判断同上级机构级别判断新增机构级别：因为没有3级机构，所以2级后时4级
//		int increment=1;
//		if(branchModel.getBranch_level().equals("2")){
//			//如果上级机构为2级机构，那么增量为2
//			increment=2;
//			//新增机构为4级机构，那么新增的机构代码是后台自动生成的
//			String branch_id="";
//			List<IBranchVOModel> list=branchDao.getSubBranchInfo(branchVOModel.getBranch_parentid());
//			if (list.size()==0) {
//				branch_id=branchVOModel.getBranch_parentid().substring(0, 5)+"0001";
//			}else {
//				Integer max=0;
//				for(int i=0;i<list.size();i++){
//					IBranchVOModel subBranch=list.get(i);
//					if(Integer.parseInt(subBranch.getBranch_id())>max){
//						max=Integer.parseInt(subBranch.getBranch_id());
//					}
//				}
//				branch_id=String.valueOf(max+1);
//			}
//			branchVOModel.setBranch_id(branch_id);
//		}
//		branchVOModel.setBranch_level(String.valueOf((Integer.parseInt(branchModel.getBranch_level())+increment)));
		return branchVOModel;
	}
	
	
	/**
	 * 添加机构保存
	 */
	@Override
	public String addBranch(IBranchModel model,IUserModel user)  throws BusinessException {

		List<IBranchModel> relist = branchDao.queryByVerifyAll(model);
		if (relist.size()!=0) {
			for(IBranchModel branch :relist){
				if(model.getBranch_id().equals(branch.getBranch_id())){
					throw new BusinessException("机构号已存在，请核实。");
				}
				if(model.getBranch_name().equals(branch.getBranch_name())){
					throw new BusinessException("机构名称已存在，请核实。");
				}
			}
		}
		//创建branchId
		String nextlv = this.nextBranchLevel(model);
		model.setBranch_level(nextlv);
		String nextval=this.nextBranchId(model.getBranch_parentid());
		model.setBranch_id(nextval);

		
		//设置创建人和修改人
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		//获得机构路径
		IBranchModel branchModel=new BranchModel();
		branchModel.setBranch_id(model.getBranch_parentid());
		IBranchVOModel branchVOModel=branchDao.getBranchView(branchModel);
		//当添加的机构为一级机构时，它的parent_branch_id为空，所以获得的机构路径对象也为空，需排除这种情况
		if (branchVOModel!=null) {
			model.setBranch_allpath(branchVOModel.getBranch_allpath()+"-"+model.getBranch_id());
		}else {
			model.setBranch_allpath(model.getBranch_id());//添加一级机构时机构路径为自身的机构代码
		}
		
		
		branchDao.addBranch(model);
		return nextval;
	}
    

	/*
	 * 更新机构信息 
	 */
	@Override
	public boolean updateBranch(IBranchModel model,IUserModel user)throws BusinessException {
		if (ValidateHelper.isNull(model.getBranch_name ())) {
			throw new BusinessException("机构名称不能为空，请核实。");
		}
		IBranchModel model0 = new BranchModel();
		model0.setSeq_id(model.getSeq_id());
		model0.setBranch_abbr(model.getBranch_abbr());
		model0.setBranch_id(model.getBranch_id());
		List<IBranchModel> relist = branchDao.queryByVerifyOtherData(model0);
		if (relist.size()!=0) {
			for(IBranchModel branch :relist){
				if(model.getBranch_name().equals(branch.getBranch_name())){
					throw new BusinessException("机构名称已存在，请核实。");
				}
			}
		}
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		model0 = branchDao.getByBranch(model);

		model0.setBranch_name(model.getBranch_name());
		model0.setBranch_abbr(model.getBranch_abbr());
		Date tda=model.getFound_date();
		model0.setFound_date(model.getFound_date());
		model0.setDelegate(model.getDelegate());
		model0.setAddress(model.getAddress());
		model0.setZip(model.getZip());
		model0.setTelephone(model.getTelephone());
		model0.setFax(model.getFax());
		model0.setEmail(model.getEmail());
		model0.setRemark(model.getRemark());
		/*model0.setCost_center(model.getCost_center());
		model0.setSettle_center(model.getSettle_center());*/
		model0.setProvince_code(model.getProvince_code());//by zdd 20190614
		model0.setCity_code(model.getCity_code());//by zdd 20190614
		model0.setArea_code(model.getArea_code());
		model0.setPermitarea(model.getPermitarea());
		model0.setPermitcode(model.getPermitcode());
		model0.setChannelcode(model.getChannelcode());
		model0.setProvince(model.getProvince());
		model0.setCity(model.getCity());
		model0.setArea(model.getArea());
		model0.setStatus(model.getStatus());
		model0.setExittime(model.getExittime());
		model0.setRecall_date(model.getRecall_date());
		model0.setDeatailedaddress(model.getDeatailedaddress());
		model0.setBuslicensefounddate(model.getBuslicensefounddate());
		model0.setUnifiedSocialCreditNO(model.getUnifiedSocialCreditNO());
		IBranchModel ncmodel = new BranchModel();
		ncmodel.setBranch_id(model.getBranch_id());
//		IBranchModel modelNc=branchDao.queryNcBranch(ncmodel);
//		if(modelNc==null){
//			ncmodel.setCost_center(model0.getCost_center());
//			ncmodel.setBranch_id(model0.getBranch_id());
//			ncmodel.setBranch_name(model0.getBranch_name());
//			ncmodel.setBranch_parentid(model0.getBranch_parentid());
//			branchDao.addNcBranch(ncmodel);
//		}
		
		
		if("4".equals(model0.getBranch_level().trim()))
		{
			/*ITeamModel model1=new TeamModel();
			model1.setTeam_name(model0.getBranch_name());
			model1.setTeam_id(model0.getBranch_id());
			model1.setFound_date(model0.getFound_date());
			model1.setZip(model0.getZip());
			model1.setTelephone(model0.getTelephone());
			model1.setFax(model0.getFax());
			model1.setRemark(model0.getRemark());
			model1.setModifyUser(user.getEmp_id());
			teamDao.modifyTeam(model1, user);*/
			branchDao.updateBranch(model0);
		}
		else
		{
			branchDao.updateBranch(model0);
		    /*branchDao.updateNcDept(model0);*/
		}
		
		
		return true;
		
	}
	
	
	/**
	 * 查看明细（包含上级名称） *
	 */
	@Override
	public IBranchVOModel getBranchView(IBranchModel model){
		return branchDao.getBranchView(model);
	}
	
	
	/**
	 * 更改机构状态 和添加到机构轨迹表的信息 *
	 */
	
	public Integer updateStatus(IBranchModel model,IUserModel user){
		Integer flag=null;
		
		IBranchModel model0 = branchDao.getByBranch(model);
		IBranchStatusHisModel bshModel = new BranchStatusHisModel();
		bshModel.setChannel_id(CodeTypeConst.CODE_TYPE_CHANNEL_ID);
		bshModel.setBranch_id(model0.getBranch_id());
		bshModel.setBef_stat_code(model0.getStatus());
		bshModel.setApproval_id(111111);
		bshModel.setProcess_completed("Y");
		bshModel.setRemark("");
		bshModel.setCreateUser(user.getEmp_id());
		bshModel.setModifyUser(user.getEmp_id());
		
		
		if("1".equals(model0.getStatus())){
			/*if(!model0.getBranch_level().equals("4")  && !model0.getBranch_level().equals("2") ){
				checkIsAllBranchInvlid(model0.getBranch_id(),"1");
			}
			else if(model0.getBranch_level().equals("2"))
			{
				checkIsAllBranchInvlid(model0.getBranch_id(),"1");
				checkIsAllTeamInvlid(model0.getBranch_id(),"1");
			}
			else {
				checkIsAllTeamInvlid(model0.getBranch_id(),"1");
			}*/
			if (!model0.getBranch_level().equals("0")) {
				checkIsAllBranchInvlid(model0.getBranch_id(),"1");
			}
			if("4".equals(model0.getBranch_level()))
			{
				/*ITeamModel teamModel=new TeamModel();
				teamModel.setStatus("0");
				teamModel.setTeam_id(model0.getBranch_id());
				teamModel.setModifyUser(user.getEmp_id());
				tshModel.setAft_stat_code("0");
				teamDao.invalidTeam(teamModel, user);
				teamDao.addTeamStatusHis(tshModel, user);*/
				
				model0.setStatus("0");
				bshModel.setAft_stat_code("0");
				model0.setModifyUser(user.getEmp_id());
				Date date = new Date(System.currentTimeMillis());
				model0.setRecall_date(date);
				branchDao.updateBranch(model0);
				branchDao.addBranchStatusHis(bshModel);				
			}
			else
			{
				model0.setStatus("0");
				bshModel.setAft_stat_code("0");
				model0.setModifyUser(user.getEmp_id());
				Date date = new Date(System.currentTimeMillis());
				model0.setRecall_date(date);
				branchDao.updateBranch(model0);
				branchDao.addBranchStatusHis(bshModel);
			}			
		    flag=1;
		}
		else if("0".equals(model0.getStatus()))
		{
			/*if(!model0.getBranch_level().equals("4") && !model0.getBranch_level().equals("2")){
				checkIsAllBranchInvlid(model0.getBranch_id(),"0");
			}
			else if(model0.getBranch_level().equals("2"))
			{
				checkIsAllBranchInvlid(model0.getBranch_id(),"0");
				checkIsAllTeamInvlid(model0.getBranch_id(),"0");
			}
			else {
				checkIsAllTeamInvlid(model0.getBranch_id(),"0");
			}*/
			if (!model0.getBranch_level().equals("0")) {
				checkIsAllBranchInvlid(model0.getBranch_id(),"0");
			}
			if("4".equals(model0.getBranch_level()))
			{
				/*ITeamModel teamModel=new TeamModel();
				teamModel.setStatus("1");
				teamModel.setTeam_id(model0.getBranch_id());
				teamModel.setModifyUser(user.getEmp_id());	
				tshModel.setAft_stat_code("1");
				teamDao.validTeam(teamModel, user);
				teamDao.addTeamStatusHis(tshModel, user);	*/
				
				model0.setStatus("1");
				bshModel.setAft_stat_code("1");
				model0.setRecall_date(null);
				model0.setModifyUser(user.getEmp_id());
				branchDao.updateBranch(model0);
				branchDao.addBranchStatusHis(bshModel);
			}
			else
			{
				model0.setStatus("1");
				bshModel.setAft_stat_code("1");
				model0.setRecall_date(null);
				model0.setModifyUser(user.getEmp_id());
				branchDao.updateBranch(model0);
				branchDao.addBranchStatusHis(bshModel);
			}
			flag=0;
		}
		return flag;
	}

	
	/**
	 * 检查信息是否存在
	 */
	@Override
	public int checkRepeatReturnInt(IBranchModel model) {
		List<IBranchModel> list = branchDao.queryByVerifyOtherData(model);
		return list.size();
	}

	/**
	 * 2013-12-18新添加方法 BY 张晨
	 * 校验前台输入的数据是否已存在 
	 * @param model
	 * @return Integer
	 */
	@Override
	public Integer queryForVerifyData(IBranchModel model) {
		
		return branchDao.queryForVerifyData(model);
	}
	
	/**
	 * 轨迹数据查询
	 */
	@Override
	public List<IBranchStatusHisVOModel> queryAllBranchStatusHis(
			IBranchStatusHisVOModel model) {
		return branchDao.queryAllBranchStatusHis(model);
	}
	@Override
	public List<ITree> queryBranchTree(IBranchModel model){
		return branchDao.queryBranchTree(model);
	}

	@Override
	public List<ITree> queryBranchTreeExceptCentral(IBranchModel model){
		return branchDao.queryBranchTreeExceptCentral(model);
	}
	/**
	 * 工作流使用
	 */
	@Override
	public List<ITree> queryBranchTree4WF(IBranchModel model) {
		return branchDao.queryBranchTree4WF(model);
	}

	/** 
	* 校验该机构的下级机构是否全部为无效 ss
	* @param branch_parentid 
	* @param status 
	* @description:根据上级机构代码校验是否所有的下级机构都无效  ss
	*/
	@Override
	public void checkIsAllBranchInvlid(String branch_parentid,String status){
		List<IBranchVOModel> list=branchDao.getSubBranchInfo(branch_parentid);
		for (IBranchVOModel iBranchVOModel : list) {
			if (iBranchVOModel.getStatus().equals(status)) {
//				throw new BusinessException("该机构下存在状态"+("1".equals(status)?"有效":"无效")+"的下级机构："+iBranchVOModel.getBranch_name()+("1".equals(status)?",不允许注销":",不允许恢复"));
				throw new BusinessException("该机构下存在状态"+("1".equals(status)?"有效":"无效")+"的下级机构,"+("1".equals(status)?"不允许注销。":"不允许恢复。"));
			}
		}
	}
	

	
	/**
	 * 查询机构下一个机构代码
	 * yanqiguang
	 * 2017年12月25日上午11:19:00
	 * TODO
	 */
	@Override
	public String nextBranchId(String branch_parentId)
	{
		String nextId;
		int temp_id;
		List<IBranchVOModel> sublist = branchDao.getSubBranchInfo(branch_parentId);
		if(sublist.size()>0){
			temp_id=sublist.size()+1;
		}else{
			temp_id=1;
		}
		String temp_str=StrUtil.alignLeft(Integer.toString(temp_id), 3);
		nextId=branch_parentId+temp_str;
		return nextId;		
	}
	
	/** 
	* 
	* @param model
	* @return String
	* @description:查询机构下一级别
	*/
	private String nextBranchLevel(IBranchModel model) {
		IBranchModel branch = branchDao.getByBranch(model);
		String parent_level = branch.getBranch_level();
		String branch_level = Integer.toString(Integer.valueOf(parent_level) + 1);
		return branch_level;
	}
	
	/** 
	* 判断该机构下是否拥有人员,团队下存在人员，不能注销该团队
	* @param
	* @param
	* @description:
	*/
	@Override
	public List<ICustomerVOModel> isHasPerson(ICustomerVOModel model) {
		return branchDao.isHasPerson(model);
	}

	/**
	 * 查询省  孙豪
	 */
	@Override
	public List<IBranchModel> queryProvince() {
		return branchDao.queryProvince();
	}

	/**
	 * 查询市  孙豪
	 */
	@Override
	public List<IBranchModel> queryCity(String parent_code) {
		return branchDao.queryCity(parent_code);
	}
	
	/**
	 * 查询县  孙豪
	 */
	@Override
	public List<IBranchModel> queryArea(String parent_code) {
		return branchDao.queryArea(parent_code);
	}

	/**
	 * 根据省市县code查询name
	 * @param area_code
	 * @return
	 */
	@Override
	public IBranchVOModel getCityByCode(String area_code) {
		return branchDao.getCityByCode(area_code);
	}
	@Override
	public List<ITree> queryBranchTreeTeam(IBranchModel model) {
		return branchDao.queryBranchTreeTeam(model);
	}
	
	/**
	 * 添加机构保存
	 * by zdd 20190611
	 */
	@Override
	public String insertBranchZdd(IBranchModel model,IUserModel user)  throws BusinessException {
        
		//验证是否有权限插入
		int orNoPower = branchDao.isORNoPower(model);
		System.out.println("==================="+orNoPower+"=====================");
		if(orNoPower==0) {
			throw new BusinessException("无权限操作。");
		}
		//验证是否存在上级
		int num=branchDao.querHeightYON(model);
		if(num==0) {/*zdd0722*/
			/*throw new BusinessException(model.getBranch_id()+"机构号不正确，请核实。");*/
			throw new BusinessException("编码与名称不匹配，请核实。");
		}
		/*zdd05*/
	/*	List<IBranchModel> relist = branchDao.queryByVerifyAll(model);
		if (relist.size()!=0) {
			for(IBranchModel branch :relist){
				if(model.getBranch_id().equals(branch.getBranch_id())){
					throw new BusinessException(model.getBranch_id()+"机构号已存在，请核实。");
				}
				if(model.getBranch_name().equals(branch.getBranch_name())){
					throw new BusinessException(model.getBranch_id()+"机构名称已存在，请核实。");
				}
			}
		}*/
		
		//设置创建人和修改人
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		//获得机构路径
	    model.setBranch_allpath(model.getBranch_id());//添加一级机构时机构路径为自身的机构代码
	    String msg="sucess";
        /*try {
		branchDao.addBranch(model);
        }catch(Exception e) {
        	msg="fail";
        }*/
		return msg;
	}
	/**
	 * by zddd 20190611
	 * @param branch_id
	 * @return branch_parentid
	 */
	private String nextBranchIdzdd(IBranchModel model) {
		String sublist = branchDao.getParentInfo(model);
		return null;
	}
	@Override
	public String importbranchIMgORPdf(IBranchModel model) {
		/*int count=branchDao.getIsORNoBranchId(model.getBranch_id());
		if(count==0) {
			return "0";//返回不存在
		}*/
		try {
		   branchDao.insertBusinessLicense(model);
		   return "1";
		}catch(Exception e) {
			return "-1";
		}
	}  
	/**
	 * zdd 20190613
	 * @return
	 */
	@Override
	public List<String> getBranchLeavel(){
		List<String> models = branchDao.getBranchLeavel();
		return models;
		
	}
	/**
	 * zdd 20190613
	 * @return
	 */
	@Override
	public String getCityByNamez(String arename) {
		return branchDao.getCityByNamez(arename);
	}
	@Override
	public List<String> getzzstuts(String str) {
		return branchDao.getzzstuts(str);
	}
	/*zdd05*/
	@Override
	public String insertbranchList(IBranchModel model) {
		String province=model.getProvince();
		String city = model.getCity();
		String area = model.getArea();
		if(!(province==null || "".equals(province))) {
		model.setProvince_code(branchDao.getCityByNamez(province));
		}
		if(!(city==null || "".equals(city))) {
			model.setCity_code(branchDao.getCityByNamez(city));
			}
		if(!(area==null || "".equals(area))) {
			model.setArea_code(branchDao.getCityByNamez(area));
		}
		boolean t = branchDao.updatebeanchyezhizhao(model);
		return null;
		
		
	}
	@Override
	public List<BusinessLicenseHisModel> businessLicenseHis(int parseInt) {
		// TODO Auto-generated method stub
		return branchDao.businessLicenseHis(parseInt);
	}
	@Override
	public String upbusinessLicenseHis(BusinessLicenseHisModel model) {
		branchDao.upbusinessLicenseHis(model);
		return "1";
	}

	@Override
	public String branchIdSelect(String branch_id) {
		return branchDao.branchIdSelect(branch_id);
	}
	@Override
	public IBranchVOModel queryBranch(IBranchModel model){
		return branchDao.queryBranch(model);
	}
	
	/**
	 * 添加机构保存
	 */
	@Override
	public String addBranchJunior(IBranchModel model, IUserModel user)  throws BusinessException {

		List<IBranchModel> relist = branchDao.queryByVerifyAll(model);
		if (relist.size()!=0) {
			for(IBranchModel branch :relist){
				if(model.getBranch_id().equals(branch.getBranch_id())){
					throw new BusinessException("机构号已存在，请核实。");
				}
				if(model.getBranch_name().equals(branch.getBranch_name())){
					throw new BusinessException("机构名称已存在，请核实。");
				}
			}
		}
		//查找下级机构的层级
		String nextlv = this.nextBranchLevel(model);
		model.setBranch_level(nextlv);

		
		//设置创建人和修改人
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		
		
		
		branchDao.addBranchJunior(model);
		return model.getBranch_id();
	}
	
}
