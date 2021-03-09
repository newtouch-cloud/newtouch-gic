package com.ca.cacore.dms.dao;

import java.util.List;

import com.ca.cacore.dms.model.vo.IDocumentMagVOModel;
import com.ca.cacore.dms.model.bo.IDocumentMagModel;
/**
 * 
 * @author zdd03
 *
 */
public interface IDocumentMagDao {

	List<IDocumentMagModel> Querydocmanagent(IDocumentMagModel model);

	List<IDocumentMagModel> exportdocmanagent(IDocumentMagModel model);

	int checkpermissionYON(IDocumentMagModel model);

	IDocumentMagModel checkapjbYON(IDocumentMagModel model);

	IDocumentMagModel checkrpjbYON(IDocumentMagModel model);

	String importdocmanagent(IDocumentMagModel model);

	IDocumentMagModel documentexistYON(IDocumentMagModel model);

	void insertsysEnumdanzheng(IDocumentMagModel model);

}
