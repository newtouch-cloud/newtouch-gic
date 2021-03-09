package  com.ca.cacore.manage.dao.funcmenu;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.manage.model.bo.IFuncMenuModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.vo.IFuncButtonVOModel;
import com.ca.cacore.manage.model.vo.IFuncMenuVOModel;


@Repository
public interface IFuncMenuDao {
	public List<IFuncMenuModel>  queryAllFuncMenu(IFuncMenuModel model);
	public IFuncMenuModel getFuncMenu(IFuncMenuModel model);
	public IFuncMenuModel addFuncMenu(IFuncMenuModel model,IUserModel user);
	public boolean updateFuncMenu(IFuncMenuModel model);
	public boolean deleteFuncMenu(IFuncMenuModel model);
	public List<IFuncMenuModel>  funcmenuTree();
	public List<IFuncMenuVOModel> queryAllFuncMenuPrior(IFuncMenuVOModel model);
	public List<IFuncButtonVOModel> queryFuncMenuButtons(IFuncButtonVOModel model);
}