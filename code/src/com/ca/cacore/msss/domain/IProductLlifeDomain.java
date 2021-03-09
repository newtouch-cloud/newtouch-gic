package  com.ca.cacore.msss.domain;
import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.vo.IProductLlifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;


/**
 * 
 * @author Wang_ds
 * @since 2013-11-20
 * @description 产品信息维护Domain层接口
 */
public interface IProductLlifeDomain {
	/**
	 * @param model 传入IProductLlifeVOModel
	 * @return 返回一个 List<IProductLlifeVOModel>对象
	 * @description 查询出产品信息，或根据条件查询
	 */
	public List<IProductLlifeVOModel> queryProductLlifeList(IProductLlifeVOModel model);
	/*public List<IProductLlifeVOModel> queryProductLlifeVOList(IProductLlifeVOModel model);*/
	/**
	 * @param 传入IProductLlifeVOModel model,IUserModel user
	 * @return 返回一个Boolean
	 * @description 修改产品信息
	 */
	public Boolean modifyProductLlife(IProductLlifeVOModel model,IUserModel user);
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ReturnMsg对象
	 * @description 查询单个产品信息
	 */
	public IProductLlifeVOModel getProductLlifeVO(IProductLlifeVOModel model);
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 添加产品信息
	 */
	public Boolean addProductLlife(IProductLlifeVOModel model, IUserModel user);
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个ModelAndView对象
	 * @description 添加产品评估信息
	 */
	public Boolean addProductEvaluation(IProductLlifeVOModel model,IUserModel user);
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个String对象
	 * @description getProductLife4Pkg 用于前台输入产品代码，带出产品信息
	 */
	public IProductLlifeVOModel getProductLife4Pkg(IProductLlifeVOModel model);
	/**
     * 校验是否录入存在相同的产品
     * @param model 产品model
     * @throws BusinessException 不合法时抛出此异常
     */
	public Boolean productLlifeIdIsHave(IProductLlifeVOModel model);
	
	/**
	 * @param 传入IProductLlifeVOModel model
	 * @return 返回一个Boolean对象
	 * @description productLlifeNameIsHave 判断产品名称是否重复
	 */
    public Boolean productLlifeNameIsHave(IProductLlifeVOModel model);
    
    /** 
    * 
    * @param model
    * @param user
    * @return Boolean
    * @description:
    */
    public Boolean deleteProductLlife(IProductLlifeModel model, IUserModel user);
}