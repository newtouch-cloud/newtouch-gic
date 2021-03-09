package com.ca.cacore.dms.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.dms.daomain.IDocumentMagDaomain;
import com.ca.cacore.dms.model.bo.IDocumentMagModel;
import com.ca.cacore.dms.model.vo.IDocumentMagVOModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
/**
 * 
 * @author zdd03
 *
 */
@Service
public class DocumentMagService extends ServerBase implements IDocumentMagService{
    @Autowired
	private IDocumentMagDaomain iDocumentMagDaomain;
	@Override
	public ReturnMsg Querydocmanagent(IDocumentMagModel model) {
		ReturnMsg msg = new ReturnMsg();
		List<IDocumentMagModel> MagVOModels= iDocumentMagDaomain.Querydocmanagent(model);
		msg.setDataList(TransHelper.list2MapList(MagVOModels));
		return msg;
	}
	@Override
	public List<IDocumentMagModel> exportdocmanagent(IDocumentMagModel model) {
		
		return  iDocumentMagDaomain.exportdocmanagent(model);
	}
	@Override
	public String importdocmanagent(IDocumentMagModel model) {
		String msg="0";
	
			/*int count =iDocumentMagDaomain.checkpermissionYON(model);*/
			/*if(count==0) {
				msg= "无权限操作！";
				return msg;
			}*/
		
		return msg;
	}
	@Override
	public void insertsysEnumdanzheng(IDocumentMagModel model) {
		iDocumentMagDaomain.insertsysEnumdanzheng(model);
		
	}
    @Override
    public String importdocmanagent2(IDocumentMagModel model) {
    	String msg ="0";
    	
			msg= iDocumentMagDaomain.importdocmanagent(model);
    	return msg;
    }
    @Override
    public IDocumentMagModel checkapjbYON(IDocumentMagModel model) {
    IDocumentMagModel checkapjbYON = iDocumentMagDaomain.checkapjbYON(model);
	return checkapjbYON;
    }
}
