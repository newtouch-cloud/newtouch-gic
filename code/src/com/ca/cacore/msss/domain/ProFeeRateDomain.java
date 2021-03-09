package  com.ca.cacore.msss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.dao.IProFeeRateDao;
import com.ca.cacore.msss.model.bo.IProductFeeRateModel;
import com.ca.cacore.msss.model.bo.ProductFeeRateModel;
import com.ca.cacore.msss.model.vo.IProductFeeRateVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.serverbase.ServerBase;
/**
 * 
 * @author fqy
 * @since 2014-1-10
 * @description 产品手续费率管理Domain层
 */
@Service
public class ProFeeRateDomain extends ServerBase implements IProFeeRateDomain{
	@Autowired private IProFeeRateDao ProFeeRateDao;
	
	/**
	 * @param model 传入IProductFeeRateVOModel
	 * @return 返回一个List<IProductFeeRateVOModel>对象
	 * @description 根据查询条件返回产品手续费率信息
	 */
	@Override
	public List<IProductFeeRateVOModel> queryProFeeRateList(
			IProductFeeRateVOModel model) {
		return ProFeeRateDao.queryProFeeRateList(model);
	}

	/**
	 * @param model 传入IProductFeeRateVOModel
	 * @return 返回一个IProductFeeRateVOModel对象
	 * @description 查询选中的产品手续费率明细信息
	 */
	@Override
	public IProductFeeRateVOModel queryProFeeRateView(IProductFeeRateVOModel model) {
		return ProFeeRateDao.queryProFeeRateView(model);
	}
	
	/**
	 * @param model 传入IProductFeeRateVOModel，IUserModel
	 * @return 返回boolean
	 * @description 修改选中的产品手续费率信息
	 */
	@Override
	public boolean modifyProFeeRate(IProductFeeRateVOModel model, IUserModel user) {
		// 进行入参校验
		IProductFeeRateVOModel newmodel = (IProductFeeRateVOModel)this.queryProFeeRateView(model);
		newmodel.setCoverage_period(model.getCoverage_period());
		newmodel.setCoverage_year(model.getCoverage_year());
		newmodel.setCharge_type(model.getCharge_type());
		newmodel.setCharge_year(model.getCharge_year());
		newmodel.setPolicy_year(model.getPolicy_year());
		newmodel.setPolicy_period(model.getPolicy_period());
		newmodel.setFee_rate(model.getFee_rate());
		newmodel.setStart_date(model.getStart_date());
		newmodel.setEnd_date(model.getEnd_date());
		//获得操作人员信息
		newmodel.setModifyUser(user.getEmp_id());
		if(newmodel.getStart_date() != null && newmodel.getEnd_date() != null){ //校验结束时间不能小于开始时间
	        if(newmodel.getEnd_date().before(newmodel.getStart_date())){
	        	throw new BusinessException("开始时间必须小于结束时间");
	        }
        }
		return ProFeeRateDao.modifyProFeeRate(newmodel);
	}
	
	/** 
	* @param model
	* @return boolean
	* @description:新增产品手续费率信息
	*/
	@Override
	public boolean insertProFeeRate(IProductFeeRateVOModel model) {
		//业务校验开始
		if(!proFeeRateIsHave(model)){
			throw new BusinessException("该纬度的产品手续费率已经配置过，请重新配置。");
		}
		if(model.getStart_date() != null && model.getEnd_date() != null){ //校验结束时间不能小于开始时间
	        if(model.getEnd_date().before(model.getStart_date())){
	        	throw new BusinessException("开始时间必须小于结束时间");
	        }
        }
		//业务校验结束
		return ProFeeRateDao.insertProFeeRate(model);
	}
	
	/**
	 * @param 传入IProductFeeRateVOModel model
	 * @return 返回一个Boolean对象
	 * @description proFeeRateIsHave 校验相同纬度的产品手续费是否已经配置过
	 */
	public Boolean proFeeRateIsHave(IProductFeeRateVOModel model){
		IProductFeeRateModel bomodel = new ProductFeeRateModel();
		bomodel.setInsBranch_id(model.getInsBranch_id());
		bomodel.setProduct_id(model.getProduct_id());
		bomodel.setPolicy_year(model.getPolicy_year());
		bomodel.setCharge_year(model.getCharge_year());
		IProductFeeRateModel sysmodel = ProFeeRateDao.getProFeeRateBO(bomodel);
		if(sysmodel != null){//判断是不是为空。
			return false;
		}
		return true;
	}
}
