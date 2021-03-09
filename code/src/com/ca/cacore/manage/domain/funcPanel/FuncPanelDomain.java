package com.ca.cacore.manage.domain.funcPanel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.dao.funcPanel.IFuncPanelDao;
import com.ca.cacore.manage.model.bo.IFuncPanelModel;
import com.ca.cacore.manage.model.bo.IMenuModel;
import com.ca.cacore.manage.model.vo.ISearchParamsModel;
import com.ca.cacore.common.CodeTypeConst;

/**
* @since:    2014年2月15日   
* @author    ss
* @description:
*/
@Service
public class FuncPanelDomain implements IFuncPanelDomain {

	@Autowired private IFuncPanelDao funcPanelDao;
	/** 
	* 获得员工的自定制常用功能信息
	* @param emp_id
	* @return List<String>
	* @description:通过员工代码获得员工的自定制常用功能信息
	*/
	@Override
	public List<String> getFuncPanelConfInfo(String emp_id) {
		List<String> rList=new ArrayList<String>();
		List<IFuncPanelModel> list=funcPanelDao.getFuncPanelConfInfo(emp_id);
		int countTop=0;
		int countLeft=0;
		int countRight=0;
		for (IFuncPanelModel model : list) {
			if (CodeTypeConst.rightTop == model.getBelong_zone()) {
				countTop++;
			}else if(CodeTypeConst.rightBottomleft == model.getBelong_zone()) {
				countLeft++;
			}else {
				countRight++;
			}
		}
		String rightTop="";
		String rightBottomLeft="";
		String rightBottomRight="";
		int flag0=0;
		int flag1=0;
		int flag2=0;
		for (IFuncPanelModel model : list) {
			if (model.getBelong_zone().equals(CodeTypeConst.rightTop)) {
				flag0++;
				rightTop+="{belong_row:'"+model.getBelong_row()+"',belong_column:'"+model.getBelong_column()+"',belong_zone:'"+model.getBelong_zone()+"',style:'"+model.getStyle()+"',link_url:'"+model.getLink_url()+"',func_name:'"+model.getFunc_name()+"',is_last:'"+model.getIs_last()+"'}";
				if (flag0<countTop) {
					rightTop+=",";
				}
			}else if (model.getBelong_zone().equals(CodeTypeConst.rightBottomleft)) {
				flag1++;
				rightBottomLeft+="{belong_row:'"+model.getBelong_row()+"',belong_column:'"+model.getBelong_column()+"',belong_zone:'"+model.getBelong_zone()+"',style:'"+model.getStyle()+"',link_url:'"+model.getLink_url()+"',func_name:'"+model.getFunc_name()+"',is_last:'"+model.getIs_last()+"'}";
				if (flag1<countLeft) {
					rightBottomLeft+=",";
				}
			} else {
				flag2++;
				rightBottomRight+="{belong_row:'"+model.getBelong_row()+"',belong_column:'"+model.getBelong_column()+"',belong_zone:'"+model.getBelong_zone()+"',style:'"+model.getStyle()+"',link_url:'"+model.getLink_url()+"',func_name:'"+model.getFunc_name()+"',is_last:'"+model.getIs_last()+"'}";
				if (flag2<countRight) {
					rightBottomRight+=",";
				}
			}
		}
		rightTop="{rightTop:["+rightTop+"]}";
		rightBottomLeft="{rightBottomLeft:["+rightBottomLeft+"]}";
		rightBottomRight="{rightBottomRight:["+rightBottomRight+"]}";
		rList.add(rightTop);
		rList.add(rightBottomLeft);
		rList.add(rightBottomRight);
		return rList;
	}
	
	/** 
	* 
	* @param model
	* @return List<String>
	* @description:获得弹出式搜索的信息
	*/
	public List<String> getMenuInfo(ISearchParamsModel model){
		List<String> rList=new ArrayList<String>();
		List<IMenuModel> list=funcPanelDao.getMenuInfo(model);
		for (IMenuModel iMenuModel : list) {
			String str="{menu_name:'"+iMenuModel.getMenu_name()+"',menu_url:'"+iMenuModel.getMenu_url()+"'}";
			rList.add(str);
		}
		return rList;
	}
	/** 
	* 获得弹出式搜索的信息
	* @param model
	* @return 
	* @description:created by wang_ds
	*/
	@Override
	public List<String> getSechInfo(ISearchParamsModel model) {
		List<ISearchParamsModel> rList=funcPanelDao.getSechInfo(model); 
		List<String> msgList = new ArrayList<String>();
		for(int i=0 ;i<rList.size();i++){
			String str = "{seachMsg:'"+rList.get(i).getKeyWord()+"'}";
			msgList.add(str);
		}
		return msgList;
	}

	@Override
	public List<String> getManagerID(ISearchParamsModel model) {
		List<ISearchParamsModel> rList=funcPanelDao.getManagerID(model);
		List<String> msgList = new ArrayList<String>();
		for(int i=0 ;i<rList.size();i++){
			String str = "{seachMsg:'"+rList.get(i).getKeyWord()+"'}";
			msgList.add(str);
		}
		return msgList;
	}
}
