package  com.ca.cacore.manage.domain.funcmenu;
import java.util.List;

import com.ca.cacore.manage.model.bo.IFuncMenuModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.vo.IFuncMenuVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;



public interface IFuncMenuDomain {

	public List<IFuncMenuModel> queryAllFuncMenu(IFuncMenuModel model);
	public IFuncMenuModel getFuncMenu(IFuncMenuModel model);
	public ReturnMsg addFuncMenu(IFuncMenuModel model,IUserModel user);
	public boolean updateFuncMenu(IFuncMenuModel model);
	public boolean deleteFuncMenu(IFuncMenuModel model);
	public List<IFuncMenuModel> funcMenuTree();
	public List<IFuncMenuVOModel> queryAllFuncMenuPrior(IFuncMenuVOModel model);

}