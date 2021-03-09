package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.vo.IPolicyImageVoModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.daobase.BaseDao;
import com.newtouch.core.returnmsg.ReturnMsg;

@SuppressWarnings("unchecked")
@Component
public class PolicyImageDao extends BaseDao implements IPolicyImageDao{


	public boolean addPolicyImage(IPolicyImageModel model) {
		this.getSqlMapClientTemplate().insert("policyImage.addPolicyImage",model);
		return true;
	}

	/**
	 * 2014-1-3 修改只单独校验投保单表
	 * 验证机构及投保单号是否对应,并反馈给前台页面
	 */
	public IPolicyImageModel validateBranchAndPolicy(IPolicyImageModel model) {
		
		return  (IPolicyImageModel)this.getSqlMapClientTemplate().queryForObject("policyImage.validateBranchAndPolicy", model);
	}
	
	/** 
	* 2014-1-3 15:38
	* @param model
	* @return IPolicyImageModel
	* @description:验证机构及保单号是否对应,并反馈给前台页面
	 */
	public IPolicyImageModel validateBranchAndContract(IPolicyImageModel model) {
		
		return (IPolicyImageModel)this.getSqlMapClientTemplate().queryForObject("policyImage.validateBranchAndContract", model);
	}


	/**
	 * 2013-12-19 
	 * 通过file_id 查找影像文件信息
	 */
	public IPolicyImageModel getFileByFileId(IPolicyImageModel model) {
		
		return (IPolicyImageModel) this.getSqlMapClientTemplate().queryForObject("policyImage.getFileByFileId",model);
	}
	

	/**
	 * 
	* 2014-2-13
	* @param model
	* @return boolean
	* @description:通过file_id 检验文件是否进行关联,如果文件是send_code是未关联的则返回true 
	 */
	public boolean checkFileByFileId(IPolicyImageModel model) {
		int count=(Integer) this.getSqlMapClientTemplate().queryForObject("policyImage.checkFileByFileId",model);
		if(count>0){ //如果大于0表示已经存在,并未关联需要进行关联操作
			return true;
		}else{
			return false; //==0表示库里没有资料,
		}
	}


	/**
	 * 2013-12-19 17:33
	 * 通过seq_id 删除影像信息
	 */
	public boolean deletePolicyImageInfo(IPolicyImageModel model) {
		int count=this.getSqlMapClientTemplate().delete("policyImage.deletePolicyImageInfo",model);
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	}


	/**
	 * 2013-12-20 9:53
	 * 通过file_id 关联保单影像信息给保单信息(send_code,policy_id)
	 */
	public boolean concernPolicyImage(IPolicyImageModel model) {
		int count=this.getSqlMapClientTemplate().update("policyImage.concernPolicyImage", model);
		if(count>0){ //如果保存成功,则对相应的保单表或投保单表进行更新影像件上传日期操作
			String bus_type = model.getBus_type();
			if("1".equals(bus_type)){ //投保单
				this.getSqlMapClientTemplate().update("policyImage.updatePolicy", model);
			}else if("2".equals(bus_type)){ //保单
				this.getSqlMapClientTemplate().update("policyImage.updateContract", model);
			}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 2014-2-15 10:49
	 * 保单管理-->投保单影像件上传-->查询没有添加影像件的投保单
	 * @param model
	 * @return List<IPolicyImageVoModel>
	 */
	public List<IPolicyImageVoModel> queryAddPolicyImage(IPolicyImageVoModel model) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("policyImage.queryAddPolicyImage_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		
		return (List<IPolicyImageVoModel>)this.getSqlMapClientTemplate().queryForList("policyImage.queryAddPolicyImage", model);
	}


	/**
	 * 2013-12-23 10:08
	 * 保单管理-->投保单影像管理-->投保单及影像查询方法
	 * @param model
	 * @return List<IPolicyImageVoModel>
	 */
	public List<IPolicyImageVoModel> queryPolicyImage(IPolicyImageVoModel model) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("policyImage.queryPolicyImage_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		
		return (List<IPolicyImageVoModel>)this.getSqlMapClientTemplate().queryForList("policyImage.queryPolicyImage", model);
	}
	
	/**
	 * 2014-2-15 10:49
	 * 保单管理-->保单影像件上传-->查询没有添加影像件的保单
	 * @param model
	 * @return List<IPolicyImageVoModel>
	 */
	public List<IPolicyImageVoModel> queryAddContractImage(IPolicyImageVoModel model) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("policyImage.queryAddContractImage_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		
		return (List<IPolicyImageVoModel>)this.getSqlMapClientTemplate().queryForList("policyImage.queryAddContractImage", model);
	}
	
	/**
	 * 2014-2-8 10:08
	 * 保单管理-->保单影像管理-->保单及影像查询方法
	 * @param model
	 * @return List<IPolicyImageVoModel>
	 */
	public List<IPolicyImageVoModel> queryContractImage(IPolicyImageVoModel model) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("policyImage.queryContractImage_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		
		return (List<IPolicyImageVoModel>)this.getSqlMapClientTemplate().queryForList("policyImage.queryContractImage", model);
	}


	/**
	 * 
	 * 2013-12-23 16:23
	 * @param model
	 * @return 
	 * @description:保单管理-->保单影像管理-->保单及影像查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewPolicyImage(IPolicyImageVoModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyImageModel> list = this.getSqlMapClientTemplate().queryForList("policyImage.viewPolicyImage", model); //获取与投保单id相关联的所有影像信息
		if(list.size()>0){
			returnMsg.setDataList(TransHelper.list2MapList(list));
		}
		
		IPolicyImageVoModel voModel = (IPolicyImageVoModel) this.getSqlMapClientTemplate().queryForObject("policyImage.queryPolicyForImage",model);
		returnMsg.setDataTable(TransHelper.obj2map(voModel));
		return returnMsg;
	}
	
	/**
	 * 
	 * 2014-2-13 18:54
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewPolicyImageForAdd(IPolicyImageVoModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IPolicyImageVoModel voModel = (IPolicyImageVoModel) this.getSqlMapClientTemplate().queryForObject("policyImage.viewPolicyImageForAdd",model);
		returnMsg.setDataTable(TransHelper.obj2map(voModel));
		return returnMsg;
	}
	
	/**
	 * 
	 * 2014-2-10 10:55
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewContractImage(IPolicyImageVoModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyImageModel> list = this.getSqlMapClientTemplate().queryForList("policyImage.viewPolicyImage", model); //获取与保单id相关联的所有影像信息
		if(list.size()>0){
			returnMsg.setDataList(TransHelper.list2MapList(list));
		}
		
		IPolicyImageVoModel voModel = (IPolicyImageVoModel) this.getSqlMapClientTemplate().queryForObject("policyImage.queryContractForImage",model);
		returnMsg.setDataTable(TransHelper.obj2map(voModel));
		return returnMsg;
	}
	
	/**
	 * 
	 * 2014-2-13 18:41
	 * @param model
	 * @return ReturnMsg
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	public ReturnMsg viewContractImageForAdd(IPolicyImageVoModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		IPolicyImageVoModel voModel = (IPolicyImageVoModel) this.getSqlMapClientTemplate().queryForObject("policyImage.viewContractImageForAdd",model);
		returnMsg.setDataTable(TransHelper.obj2map(voModel));
		return returnMsg;
	}

	/**
	* @param model
	* @return 
	* @description:使用者：许小珍---回执和问题件明细的时候显示图片
	 */
	@Override
	public List<IPolicyImageModel> getImageList(IPolicyImageVoModel model) {
		List<IPolicyImageModel> list = this.getSqlMapClientTemplate().queryForList("policyImage.viewPolicyImage", model);
		return list;
	}

	
}
