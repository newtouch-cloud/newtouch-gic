package  com.ca.cacore.manage.dao.funcmenu;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IFuncMenuModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.vo.IFuncButtonVOModel;
import com.ca.cacore.manage.model.vo.IFuncMenuVOModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 
 * @author 王得胜
 * @since 2013-11-10
 * @discrible 功能菜单DAO层
 */
@Component
public class FuncMenuDao extends BaseDao implements IFuncMenuDao{
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象
	 * @return  返回一个List对象
	 * @discrible 查询出所有的功能菜单model
	 */
	@Override
	public List<IFuncMenuModel> queryAllFuncMenu(IFuncMenuModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("FuncMenu.queryFuncMenu_count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IFuncMenuModel>)this.getSqlMapClientTemplate().queryForList("FuncMenu.queryFuncMenu", model);
	}
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象
	 * @return  返回一个IFuncMenuModel对象
	 * @discrible 查询指定参数的一个功能菜单model
	 */
	@Override
	public IFuncMenuModel getFuncMenu(IFuncMenuModel model) {
		return (IFuncMenuModel)this.getSqlMapClientTemplate().queryForObject("FuncMenu.queryFuncMenuBySeqId", model);
	}
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象，user对象
	 * @return  返回一个IFuncMenuModel
	 * @discrible 添加一个功能菜单model，或根据条件查询
	 */
	@Override
	public IFuncMenuModel addFuncMenu(IFuncMenuModel model,IUserModel user) {
		this.getSqlMapClientTemplate().insert("FuncMenu.insertFuncMenu",model);
		return null;
	}
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象，user对象
	 * @return  返回一个boolean
	 * @discrible 更新一个功能菜单model
	 */
	@Override
	public boolean updateFuncMenu(IFuncMenuModel model) {
		this.getSqlMapClientTemplate().update("FuncMenu.updateFuncMenu",model);
		return true;
	}
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象，user对象
	 * @return  返回一个boolean
	 * @discrible 删除一个功能菜单model
	 */
	@Override
	public boolean deleteFuncMenu(IFuncMenuModel model) {
		this.getSqlMapClientTemplate().delete("FuncMenu.deleteFuncMenu",model);
		return true;
	}
	@Override
	public List<IFuncMenuModel> funcmenuTree() {
		// TODO Auto-generated method stub
		return (List<IFuncMenuModel>)this.getSqlMapClientTemplate().queryForList("FuncMenu.queryfuncmenuTree");
	}
	
	@Override
	public List<IFuncMenuVOModel> queryAllFuncMenuPrior(IFuncMenuVOModel model) {
		return (List<IFuncMenuVOModel>)this.getSqlMapClientTemplate().queryForList("FuncMenu.queryAllFuncMenuPrior",model);
	}

	@Override
	public List<IFuncButtonVOModel> queryFuncMenuButtons(IFuncButtonVOModel model) {
		return (List<IFuncButtonVOModel>)this.getSqlMapClientTemplate().queryForList("FuncMenu.queryFuncMenuButtons",model);
	}

}
