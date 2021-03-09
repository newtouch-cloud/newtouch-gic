package com.ca.cacore.dms.daomain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.dms.dao.IDocumentMagDao;
import com.ca.cacore.dms.model.vo.IDocumentMagVOModel;
import com.ca.cacore.dms.model.bo.IDocumentMagModel;
/**
 * 
 * @author zdd03
 *
 */
@Service
public class DocumentMagDaomain implements IDocumentMagDaomain {
	
	
	@Autowired
   private IDocumentMagDao iDocumentMagDao;
	@Override
	public List<IDocumentMagModel> Querydocmanagent(IDocumentMagModel model) {
		List<IDocumentMagModel> MagVOModels=iDocumentMagDao.Querydocmanagent(model);
		return MagVOModels;
	}
	@Override
	public List<IDocumentMagModel> exportdocmanagent(IDocumentMagModel model) {
		
		return iDocumentMagDao.exportdocmanagent(model);
	}
	@Override
	public int checkpermissionYON(IDocumentMagModel model) {
		return iDocumentMagDao.checkpermissionYON(model);
		
	}
	@Override
	public IDocumentMagModel checkapjbYON(IDocumentMagModel model) {
		return  iDocumentMagDao.checkapjbYON(model);
		
	}
	@Override
	public IDocumentMagModel checkrpjbYON(IDocumentMagModel model) {
		
		return iDocumentMagDao.checkrpjbYON(model);
	}
	@Override
	public String importdocmanagent(IDocumentMagModel model) {
		
		return iDocumentMagDao.importdocmanagent(model);
	}
	@Override
	public IDocumentMagModel documentexistYON(IDocumentMagModel model) {
		
		return iDocumentMagDao.documentexistYON(model);
	}
	@Override
	public void insertsysEnumdanzheng(IDocumentMagModel model) {
		iDocumentMagDao.insertsysEnumdanzheng(model);
		
	}

}
