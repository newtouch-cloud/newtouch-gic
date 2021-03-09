package com.ca.cacore.dms.webapp.service;

import com.ca.cacore.dms.model.vo.IDocumentMagVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

import java.util.List;

import com.ca.cacore.dms.model.bo.IDocumentMagModel;
/**
 * 
 * @author zdd03
 *
 */
public interface IDocumentMagService {

	ReturnMsg Querydocmanagent(IDocumentMagModel model);

	List<IDocumentMagModel> exportdocmanagent(IDocumentMagModel model);

	String importdocmanagent(IDocumentMagModel model);

	void insertsysEnumdanzheng(IDocumentMagModel model);

	String importdocmanagent2(IDocumentMagModel model);

	IDocumentMagModel checkapjbYON(IDocumentMagModel model);

}
