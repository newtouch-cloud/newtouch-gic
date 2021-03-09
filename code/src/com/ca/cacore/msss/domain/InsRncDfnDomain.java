package  com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.msss.dao.IInsRncDfnDao;
import com.ca.cacore.msss.model.bo.IInsRncDfnModel;
import com.ca.cacore.msss.model.vo.IInsRncDfnVOModel;
import com.ca.cacore.mass.model.bo.CompanyBranchModel;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.core.serverbase.ServerBase;
/**
 * 
 * @author Wang_ds
 * @since 2013-11-20
 * @description Domain层
 */
@Service
public class InsRncDfnDomain extends ServerBase implements IInsRncDfnDomain {
	@Autowired private IInsRncDfnDao InsRncDfnDao;
	/**
	 * @param 传入IInsRncDfnVOModel model
	 * @return 返回一个List<IInsRncDfnVOModel> 对象
	 * @description 查询出险类信息，或根据条件查询
	 */
	public List<IInsRncDfnModel> queryInsRncDfnList(IInsRncDfnModel model){
		return InsRncDfnDao.queryInsRncDfnList(model);
	}	
	/** 
	* 
	* @param model
	* @return boolean
	* @description:新增 其他保险公司 产品信息
	*/
	public boolean insRncDfnAdd(IInsRncDfnModel model) {
		return InsRncDfnDao.insRncDfnAdd(model);
	}
	/** 
	* 
	* @param model
	* @return boolean
	* @description: 修改 其他保险公司 产品信息
	*/
	public boolean insRncDfnModify(IInsRncDfnModel model) {
		return InsRncDfnDao.insRncDfnModify(model);
	}
	/** 
	* 
	* @param model
	* @return IInsRncDfnModel
	* @description:查询 其他保险公司 产品信息
	*/
	public IInsRncDfnModel getInsRncDfn(IInsRncDfnModel model) {
		return InsRncDfnDao.getInsRncDfn(model);
	}
	/** 
	* 
	* @param model
	* @return 
	* @description:校验险种代码是否存在
	*/
	public boolean checkRiskCode(IInsRncDfnVOModel model) {
		IInsRncDfnVOModel m=InsRncDfnDao.checkRiskCode(model);
		if(m!=null){
			if(model.getRiskCode().equals(m.getRiskCode()) && model.getProduct_source().equals(m.getProduct_source())){
				return false;
			}
		}
		return true;
	}
	
	/** 
	* 
	* @param model
	* @return boolean
	* @description:校验险种代码是否存在（修改产品信息时）
	*/
	public boolean checkRiskCodeUnique(IInsRncDfnVOModel model) {
		IInsRncDfnVOModel voModel=InsRncDfnDao.checkRiskCode(model);
		if(voModel!=null&&!model.getRiskCode().equals(model.getRiskCodeOrign())){//如果险种代码存在且不是原来的险种代码
			return false;
		}
		return true;
	}
	/**
	 * 查询所有险类  by 孙豪
	 */
	@Override
	public List<IInsRncDfnModel> queryClassCode(IInsRncDfnModel model) {
		return InsRncDfnDao.queryClassCode(model);
	}
	/**
	 * 查询所有险种  by 孙豪
	 */
	@Override
	public List<IInsRncDfnModel> queryRiskCode(IInsRncDfnModel model) {
		return InsRncDfnDao.queryRiskCode(model);
	}
	/**
	 * 查询所有险别by 孙豪
	 */
	@Override
	public List<IInsRncDfnModel> queryTypeCode(IInsRncDfnModel model) {
		return InsRncDfnDao.queryTypeCode(model);
	}
	/**
	 * 查询所有总公司 孙豪
	 */
	@Override
	public List<CompanyBranchModel> queryCompany() {
		return InsRncDfnDao.queryCompany();
	}
	/**
	 * 修改状态 孙豪
	 */
	@Override
	public Integer updateStatus(IInsRncDfnModel model) {
		return InsRncDfnDao.updateStatus(model);
	}
	@Override
	public List<ITree> queryCompanyTree() {
		return InsRncDfnDao.queryCompanyTree();
	}
	@Override
	public String queryPoundage(IInsRncDfnModel model) {
		return InsRncDfnDao.queryPoundage(model);
	}
	//by zdd02 20190617
	@Override
	public List<IInsRncDfnModel> exportInfo(IInsRncDfnModel model) {
		return InsRncDfnDao.exportInfo(model);
	}
	//by zdd02 20190617
	@Override
	public String selectIsOrNobranchname(IInsRncDfnModel model) {
		String str1=InsRncDfnDao.selectIsOrNobranchname(model.getBranch_id());
		if(str1==null||"".equals(str1)) {
			return "-1";
		}
		return "1";
	}
	//by zdd02 20190617
		@Override
	public boolean insRncDfnAddzdd(IInsRncDfnModel model) {
		return InsRncDfnDao.insRncDfnAddzdd(model);
	}
		//by zdd02 20190617
	@Override
	public IInsRncDfnModel selectCheckInsRncDfnModel(IInsRncDfnModel model) {
			
	   return InsRncDfnDao.selectCheckInsRncDfnModel(model);
	}
	//by zdd02 20190617
	@Override
	public IInsRncDfnModel checkLimitsBranchid(IInsRncDfnModel model) {
		return InsRncDfnDao.checkLimitsBranchid(model);
	}
	@Override
	public List<IInsRncDfnModel> getsysemlist(String branch_id) {
		// TODO Auto-generated method stub
		return InsRncDfnDao.getsysemlist(branch_id);
	}
	//zdd0724
	@Override
	public String isPORL(IInsRncDfnModel model) {
		// TODO Auto-generated method stub
		return InsRncDfnDao.isPORL(model);
	}
	@Override
	public void insRncDfnUpdate(IInsRncDfnModel model) {
		// TODO Auto-generated method stub
		InsRncDfnDao.insRncDfnUpdate(model);
	}
}
