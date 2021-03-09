package  com.ca.cacore.msss.domain;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.msss.dao.IPkgLifeDao;
import com.ca.cacore.msss.dao.IProductLlifeDao;
import com.ca.cacore.msss.model.bo.IPkgLifeModel;
import com.ca.cacore.msss.model.bo.IPkgLifeRelationModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.bo.PkgLifeModel;
import com.ca.cacore.msss.model.bo.PkgLifeRelationModel;
import com.ca.cacore.msss.model.vo.IPkgLifeVOModel;
import com.ca.cacore.msss.model.vo.IProductLlifeVOModel;
import com.ca.cacore.msss.model.vo.PkgLifeVOModel;
import com.ca.cacore.msss.model.vo.ProductLlifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.serverbase.ServerBase;
/**
 * 
 * @author Wang_ds
 * @since 2013-11-20
 * @description 产品组合信息维护Domain层
 */
@Service
public class PkgLifeDomain extends ServerBase implements  IPkgLifeDomain{
	@Autowired private IPkgLifeDao PkgLifeDao;
	@Autowired private IProductLlifeDao ProductLlifeDao;
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个List<IPkgLifeVOModel> 对象
	 * @description 查询出所有产品组合信息，或根据条件查询
	 */
	public List<IPkgLifeVOModel> queryPkgLifeList(IPkgLifeVOModel model){
		return PkgLifeDao.queryPkgLifeList(model);
	}	

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个Boolean对象
	 * @description 修改出产品组合信息
	 */
	public Boolean modifyPkgLife(IPkgLifeVOModel model,IUserModel user) {
		
		Date timeTemp;
		if(model.getStart_date() == null){
			timeTemp = new java.sql.Date(System.currentTimeMillis());//没有录入起售时间，默认为立即起售
		}else{
			timeTemp = model.getStart_date();
		}
		//校验开始
		//字段的长度校验
		checkPkgLifeLength(model);
		if(model.getProduct_id().length == 1){
			 throw new BusinessException("没有添加产品信息，请先添加再保存！");//没有添加产品信息为空检验
		}
		//校验是否录入存在相同的产品
		checkProductIdIsSame(model);
		//校验停售时间不能小于起售时间
		if(model.getEnd_date() != null){
	        if(model.getEnd_date().before(timeTemp)){
	        	throw new BusinessException("停售时间不能小于起售时间！");
	        }
		}
		//校验结束
		IPkgLifeRelationModel relationModel = new PkgLifeRelationModel(); //寿险产品组合组合关联信息
		IPkgLifeRelationModel relationModelForDelete = new PkgLifeRelationModel(); //寿险产品组合组合关联信息
		IPkgLifeModel modelBO = new PkgLifeModel();
		IPkgLifeVOModel modelForQuery = new PkgLifeVOModel();//用于查询IPkgLifeVOModel
		modelForQuery.setSeq_id(model.getSeq_id());
		modelBO = PkgLifeDao.getPkgLifeBO(modelForQuery);//获取到要被修改的产品组合
		
		modelBO.setPkg_abbr(model.getPkg_abbr());
		modelBO.setPkg_enName(model.getPkg_enName());
		modelBO.setPkg_enAbbr(model.getPkg_enAbbr());
		modelBO.setRemark(model.getRemark());
		modelBO.setStatus(CodeTypeConst.PRO_STATUS_CODE1);
		modelBO.setStart_date(timeTemp);
		modelBO.setEnd_date(model.getEnd_date());
		modelBO.setModifyUser(user.getEmp_id());
		
		PkgLifeDao.modifyPkgLife(modelBO, user);
		//删除该产品组合下的产品关系
		relationModelForDelete.setPkg_id(model.getSeq_id()+""); //产品组合代码存产品组合的seq_id
		PkgLifeDao.deletePkgLifeRelation(relationModelForDelete,user);
		
		//重新循环添加寿险产品组合关联信息
		if(model.getProduct_id() != null){
			for(int i=0;i<model.getProduct_id().length-1;i++){
				
				relationModel.setPkg_id(model.getSeq_id()+"");//产品组合代码存产品组合的seq_id
				
				//拿产品的seq_id
				IProductLlifeVOModel VOModel = new ProductLlifeVOModel();
				VOModel.setProduct_id(model.getProduct_id()[i]);
				VOModel.setInsBranch_id(model.getInsBranch_id()[i]);
				IProductLlifeModel BOModel =  ProductLlifeDao.getProductLlifeBO(VOModel);
				//产品和产品组合的关联都是通过seq_id
				relationModel.setProduct_id(BOModel.getSeq_id()+"");
				relationModel.setInsBranch_id(BOModel.getInsBranch_id());
				relationModel.setRemark(model.getRemark());
				relationModel.setStatus(CodeTypeConst.PRO_STATUS_CODE1);
				relationModel.setCreateUser(user.getEmp_id());
				relationModel.setModifyUser(user.getEmp_id());
				PkgLifeDao.addPkgLifeRelation(relationModel,user);
			}
		}
	
		return true;
	}
	
	/**
	 * @param 传入IPkgLifeVOModel model
	 * @return 返回一个PkgLifeVOModel对象
	 * @description 查询出产品组合信息
	 */
	@Override
	public IPkgLifeVOModel getPkgLifeVO(IPkgLifeVOModel model) {
		
		IPkgLifeVOModel pkgLifeModelVO =  PkgLifeDao.getPkgLifeVO(model);
		IProductLlifeVOModel productLlifeVOModel = new ProductLlifeVOModel();//传产品组合组合代码和名称
		productLlifeVOModel.setPkg_id(pkgLifeModelVO.getSeq_id()+"");
		
		//取出该组合下的产品组合信息
		List<IProductLlifeVOModel> productList = ProductLlifeDao.queryProductLlifeVOList4Pkg(productLlifeVOModel);
		pkgLifeModelVO.setProductList(productList);
		return pkgLifeModelVO;
	}

	/**
	 * @param 传入IPkgLifeVOModel model
	 * @param 传入IUserModel user
	 * @return 返回一个Boolean对象
	 * @description 添加产品组合信息
	 */
	@Override
	public Boolean addPkgLife(IPkgLifeVOModel model, IUserModel user) {
		
		Date timeTemp;
		if(model.getStart_date() == null){
			timeTemp = new java.sql.Date(System.currentTimeMillis());//没有录入起售时间，默认为立即起售
		}else{
			timeTemp = model.getStart_date();
		}
		//校验开始
		//字段的长度校验
		checkPkgLifeLength(model);
		
		if(model.getProduct_id().length == 1){
			throw new BusinessException("没有添加产品信息，请先添加再保存！");//没有添加产品信息为空检验
		}

		if(!checkPkgIdAndNameUnique(model)){
			throw new BusinessException("该产品组合已存在，请重新录入！");
		}
		//校验是否录入存在相同的产品
		checkProductIdIsSame(model);
		 //校验停售时间不能小于起售时间
		if(model.getEnd_date() != null){
	        if(model.getEnd_date().before(timeTemp)){
	        	throw new BusinessException("停售时间不能小于起售时间！");
	        }
		}
        //校验结束
	
		IPkgLifeModel pkgLifeModelBO = new PkgLifeModel();//产品组合组合信息
		IPkgLifeRelationModel relationModel = new PkgLifeRelationModel(); //寿险产品组合组合关联信息
		//新增产品组合信息
		pkgLifeModelBO.setPkg_id(model.getPkg_id());
		pkgLifeModelBO.setPkg_name(model.getPkg_name());
		pkgLifeModelBO.setPkg_abbr(model.getPkg_abbr());
		pkgLifeModelBO.setPkg_enName(model.getPkg_enName());
		pkgLifeModelBO.setPkg_enAbbr(model.getPkg_enAbbr());
		pkgLifeModelBO.setRemark(model.getRemark());
		pkgLifeModelBO.setStatus(CodeTypeConst.PRO_STATUS_CODE1);
		pkgLifeModelBO.setStart_date(timeTemp);
		pkgLifeModelBO.setEnd_date(model.getEnd_date());
		pkgLifeModelBO.setCreateUser(user.getEmp_id());
		pkgLifeModelBO.setModifyUser(user.getEmp_id());
		
		Integer seq_id = PkgLifeDao.addPkgLife(pkgLifeModelBO, user);
		
		//循环添加寿险产品组合关联信息
		for(int i=0;i<model.getProduct_id().length-1;i++){
			String pkg_id= ""+seq_id;
			relationModel.setPkg_id(pkg_id.trim());
			
			//拿产品的seq_id
			IProductLlifeVOModel VOModel = new ProductLlifeVOModel();
			VOModel.setProduct_id(model.getProduct_id()[i]);
			VOModel.setInsBranch_id(model.getInsBranch_id()[i]);
			IProductLlifeModel BOModel =  ProductLlifeDao.getProductLlifeBO(VOModel);
			//产品和产品组合的关联都是通过seq_id
			relationModel.setProduct_id(BOModel.getSeq_id()+"");
			relationModel.setInsBranch_id(BOModel.getInsBranch_id());
			relationModel.setRemark(model.getRemark());
			relationModel.setStatus(CodeTypeConst.PRO_STATUS_CODE1);
			relationModel.setCreateUser(user.getEmp_id());
			relationModel.setModifyUser(user.getEmp_id());
			PkgLifeDao.addPkgLifeRelation(relationModel,user);
		}
		
		return true;
	}
	/**
     * 校验产品产品组合是否唯一
     * @param model 产品model
     * @throws BusinessException 不合法时抛出此异常
     */
	public Boolean checkPkgIdAndNameUnique(IPkgLifeVOModel model){//产品组合代码是否相同的校验方法实现
		IPkgLifeVOModel queryVO = new PkgLifeVOModel();
		queryVO.setPkg_id(model.getPkg_id().trim());
        queryVO.setPkg_name(model.getPkg_name().trim());
		IPkgLifeModel pkgLifeModel = PkgLifeDao.getPkgLifeBO(queryVO);
		
		if(pkgLifeModel != null){//判断是不是为空。
			if(!pkgLifeModel.getSeq_id().equals(model.getSeq_id()) && 
				pkgLifeModel.getPkg_id().equals(model.getPkg_id()) &&  pkgLifeModel.getPkg_name().equals(model.getPkg_name())){   //不是同一条数据才不允许修改，是就允许修改。
					return false;
				}
		}
		return true;
	}
	
	 /**
     * 校验是否录入存在相同的产品
     * @param model 产品model
     * @throws BusinessException 不合法时抛出此异常
     */
    public void checkProductIdIsSame(IPkgLifeVOModel model) throws BusinessException{
    	String[] product_id = model.getProduct_id();
    	String[] insBranch_id = model.getInsBranch_id();
    	//将产品代码和保险公司代码拼接在一起比较
    	String[] product_idAndInsBranch_id = new String [product_id.length-1];
    	if(product_id != null){
          	 if(product_id.length > 2){//至少有2条，校验是否有相同的产品信息
		    	for(int i=0;i<product_id.length-1;i++){
		    		product_idAndInsBranch_id[i] = product_id[i] + insBranch_id[i];
		    	}
        	 }
      	 }
       	for(int i=0;i<product_idAndInsBranch_id.length;i++){        
       			 for(int j=i+1;j<product_idAndInsBranch_id.length;j++){       //如果元素相同，保存到set中           
       				 if(product_idAndInsBranch_id[i].equals(product_idAndInsBranch_id[j])){                
       					 throw new BusinessException("存在相同的产品！");
       				 }
       			 }
       	}
    }
    
    /**
     * 校验录入字段的长度超过数据库定义的长度
     * @param model 产品model
     * @throws BusinessException 不合法时抛出此异常
     */
    public void checkPkgLifeLength(IPkgLifeVOModel model) throws BusinessException{
    	if(model.getPkg_id().length() > 10){
    		 throw new BusinessException("产品组合代码不能超过10个字符");
    	}
    	if(model.getPkg_name().length() > 50){
    		throw new BusinessException("产品组合名称不能超过50个字符");
    	}
    	if(model.getPkg_abbr().length() > 50){
    		throw new BusinessException("产品组合简称不能超过50个字符");
    	}
    	if(model.getPkg_enName().length() > 50){
    		throw new BusinessException("产品组合英文名称不能超过50个字符");
    	}
    	if(model.getPkg_enAbbr().length() >50){
    		throw new BusinessException("产品组合英文简称不能超过50个字符");
    	}
    	if(model.getRemark().length() >501){
    		throw new BusinessException("备注说明不能超过500个字符");
    	}
    }
    
}
