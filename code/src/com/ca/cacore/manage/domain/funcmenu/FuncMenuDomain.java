package  com.ca.cacore.manage.domain.funcmenu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.funcmenu.IFuncMenuDao;
import com.ca.cacore.manage.model.bo.IFuncMenuModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.vo.FuncButtonVOModel;
import com.ca.cacore.manage.model.vo.IFuncButtonVOModel;
import com.ca.cacore.manage.model.vo.IFuncMenuVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
/**
 * 
 * @author 王得胜
 * @since 2013-11-10
 * @discrible 功能菜单domain层
 */
@Service
public class FuncMenuDomain extends ServerBase implements IFuncMenuDomain{
	@Autowired private IFuncMenuDao FuncMenuDao;
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象
	 * @return  返回一个List对象
	 * @discrible 查询出所有的功能菜单model
	 */
	public List<IFuncMenuModel> queryAllFuncMenu(IFuncMenuModel model){
		return FuncMenuDao.queryAllFuncMenu(model);
	}	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象
	 * @return  返回一个IFuncMenuModel对象
	 * @discrible 查询指定参数的一个功能菜单model
	 */
	
	public IFuncMenuModel getFuncMenu(IFuncMenuModel model){
		return FuncMenuDao.getFuncMenu(model);
	}
	
	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象，user对象
	 * @return  返回一个boolean
	 * @discrible 删除一个功能菜单model
	 */
	public boolean deleteFuncMenu(IFuncMenuModel model){
		return FuncMenuDao.deleteFuncMenu(model);
	}

	/**
	 * @author 王得胜
	 * @param 传入一个FuncMenuModel对象，user对象
	 * @return  返回一个IFuncMenuModel
	 * @discrible 添加一个功能菜单model，或根据条件查询
	 */
	@Override
	public ReturnMsg addFuncMenu(IFuncMenuModel model,IUserModel user) {
		
			IFuncMenuModel Remodel = FuncMenuDao.addFuncMenu(model,user);
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
		// TODO Auto-generated method stub
		return FuncMenuDao.updateFuncMenu(model);
	}
	@Override
	public List<IFuncMenuModel> funcMenuTree() {
		// TODO Auto-generated method stub
		return FuncMenuDao.funcmenuTree();
	}
	
	@Override
	public List<IFuncMenuVOModel> queryAllFuncMenuPrior(IFuncMenuVOModel model){
		List<IFuncMenuVOModel> fvms = FuncMenuDao.queryAllFuncMenuPrior(model);
		for(IFuncMenuVOModel fvm : fvms){
			IFuncButtonVOModel m = new FuncButtonVOModel(fvm.getMenu_id());
			List<IFuncButtonVOModel> fbms = FuncMenuDao.queryFuncMenuButtons(m);
			fvm.setFbms(fbms);
		}
		return FuncMenuDao.queryAllFuncMenuPrior(model);
	}

	
}
