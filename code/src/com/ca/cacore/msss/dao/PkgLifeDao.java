package  com.ca.cacore.msss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.vo.IPkgLifeVOModel;
import com.ca.cacore.msss.model.bo.IPkgLifeModel;
import com.ca.cacore.msss.model.bo.IPkgLifeRelationModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
 * @author Wang_ds
 * @since 2013-11-20
 * @description 组合产品信息维护DAO层
 */
@Component
public class PkgLifeDao extends BaseDao implements IPkgLifeDao {
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个List<IPkgLifeVOModel>对象
	 * @description 查询出所有产品组合信息，或根据条件查询
	 */
	@Override
	public List<IPkgLifeVOModel> queryPkgLifeList(IPkgLifeVOModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("PkgLife.queryPkgLife_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IPkgLifeVOModel>)this.getSqlMapClientTemplate().queryForList("PkgLife.queryPkgLife", model);
	}
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个Boolean对象
	 * @description 修改出产品组合信息
	 */
	@Override
	public Boolean modifyPkgLife(IPkgLifeModel model,IUserModel user) {
		this.getSqlMapClientTemplate().update("PkgLife.updatePkgLife",model);
		return null;
	}
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个PkgLifeModel对象
	 * @description 查询出产品组合信息
	 */
	@Override
	public IPkgLifeModel getPkgLifeBO(IPkgLifeVOModel model) {
		return (IPkgLifeModel) this.getSqlMapClientTemplate().queryForObject("PkgLife.getPkgLifeBO", model);
	}
	
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个PkgLifeVOModel对象
	 * @description 查询出产品组合信息
	 */
	@Override
	public IPkgLifeVOModel getPkgLifeVO(IPkgLifeVOModel model) {
		return (IPkgLifeVOModel) this.getSqlMapClientTemplate().queryForObject("PkgLife.getPkgLifeVO", model);
	}
	
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个Integer对象
	 * @description 添加产品组合信息  返回添加的产品组合的seq_id
	 */
	@Override
	public Integer addPkgLife(IPkgLifeModel model,IUserModel user) {
		return (Integer) this.getSqlMapClientTemplate().insert("PkgLife.insertPkgLife",model);
	}
	/**
	 * @param 传入IPkgLifeRelationModel relationModel
	 * @param 传入IUserModel user
	 * @return 返回一个Boolean对象
	 * @description 新增寿险产品组合关联信息
	 */
	@Override
	public Boolean addPkgLifeRelation(IPkgLifeRelationModel relationModel,IUserModel user) {
		this.getSqlMapClientTemplate().insert("PkgLife.insertPkgLifeRelation",relationModel);
		return true;
	}
	/**
	 * @param 传入IPkgLifeRelationModel relationModelForDelete
	 * @param 传入IUserModel user
	 * @return 返回一个Boolean对象
	 * @description 删除寿险产品组合关联信息
	 */
	@Override
	public Boolean deletePkgLifeRelation(IPkgLifeRelationModel relationModelForDelete, IUserModel user) {
		this.getSqlMapClientTemplate().delete("PkgLife.deletePkgLifeRelation", relationModelForDelete);
		return true;
	}
	

	


}
