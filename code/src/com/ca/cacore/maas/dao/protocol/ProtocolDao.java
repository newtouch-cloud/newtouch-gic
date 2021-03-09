package com.ca.cacore.maas.dao.protocol;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.maas.model.bo.IProtocolModel;
import com.ca.cacore.maas.model.bo.ProtocolModel;
import com.newtouch.core.daobase.BaseDao;
/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-dao层
*/
@Component
public class ProtocolDao extends BaseDao implements IProtocolDao {

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-增加
	*/
	@Override
	public boolean addProtocol(IProtocolModel model) {
		this.getSqlMapClientTemplate().insert("protocol.addProtocol",model);
		return true;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-查询
	*/
	@Override
	public List<IProtocolModel> queryProtocol(IProtocolModel model) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("protocol.queryProtocols_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		/**
		 *  to_char(ca.startdate,'yyyy-mm-dd') startdate,
	             to_char(ca.enddate,'yyyy-mm-dd') enddate,
	             to_char(ca.dateofsign,'yyyy-mm-dd') dateofsign,
		 */
		return this.getSqlMapClientTemplate().queryForList("protocol.queryProtocols",model);
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-修改
	*/
	@Override
	public boolean modifyProtocol(IProtocolModel model) {
		this.getSqlMapClientTemplate().update("protocol.updateProtocol",model);
		return true;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-导出
	*/
	@Override
	public List<IProtocolModel> queryProtocolForExport(IProtocolModel model) {
		return this.getSqlMapClientTemplate().queryForList("protocol.queryProtocolForExport",model);
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-获取签订人信息(由工号查询姓名)
	*/
	@Override
	public IProtocolModel getProtocolPersonInformation(IProtocolModel model) {
		return (IProtocolModel) this.getSqlMapClientTemplate().queryForObject("protocol.getProtocolInfo",model);
	}
	/**
	 * 查询保险公司协议机构是否重复
	 * @param branch_id
	 * @return
	 */
	public boolean checkBranch(String branch_id){
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("protocol.queryCountBranch",branch_id);
		if(count>=1){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 查询保险公司协议
	 */
	public boolean checkAgreement_no(IProtocolModel model){
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("protocol.queryCountAgreement_no",model);
		if(count>=1){
			return false;
		}else{
			return true;
		}
	}
	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-维护-页面
	*/
	@Override
	public IProtocolModel getProtocolModifyView(IProtocolModel model) {
		return (IProtocolModel) this.getSqlMapClientTemplate().queryForObject("protocol.getProtocolView",model);
	}

	@Override
	public List<IProtocolModel> getProductName(IProtocolModel model) {
		return null;
				//this.getSqlMapClientTemplate().queryForList("protocol.getProductName",model);
	}

	@Override
	public List<IProtocolModel> getProductCode(IProtocolModel model) {
		return null;
				//this.getSqlMapClientTemplate().queryForList("protocol.getProductCode",model);
	}

	@Override
	public Integer addProtocolView() {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("protocol.queryProtocols_count");
	}

	@Override
	public Integer updateProtocolStatus(IProtocolModel model) {
		return this.getSqlMapClientTemplate().update("protocol.updateProtocolStatus",model);
	}

	@Override
	public List<ProtocolModel> checkagreement(IProtocolModel model) {
		return this.getSqlMapClientTemplate().queryForList("protocol.checkagreement",model);
	}

}
