package com.newtouch.report.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.report.model.vo.IWorkNatureModel;


@Component
public class WorkNatureDao extends BaseDao implements IWorkNatureDao{

	@Override
	public List<IWorkNatureModel> queryWorkNature(IWorkNatureModel model) {
		// TODO Auto-generated method stub
		/*int count = (Integer) this.getSqlMapClientTemplate().queryForObject("workNature.queryWorkNature_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);	*/
		List<IWorkNatureModel> workNatureList = new ArrayList<IWorkNatureModel>();
		 if(model!=null && model.getBranch_id() !=null && !"".equals(model.getBranch_id()) && !"0".equals(model.getBranch_level())){
			 String branchLevel = (String) this.getSqlMapClientTemplate().queryForObject("workNature.queryBranchLevel",model);
			 model.setBranch_level(branchLevel);
			 if("1".equals(model.getBranch_level()) || "3".equals(model.getBranch_level()) || "4".equals(model.getBranch_level())){
				IWorkNatureModel  worknature1 = (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryWorkNature",model);
				if(worknature1 != null && worknature1.getBranch_name()!=null){
					worknature1.setBranch_name(worknature1.getBranch_name()+"总计");
				}
				int total1 = (int)this.getSqlMapClientTemplate().queryForObject("workNature.queryTotal",model);
				worknature1.setTotal(total1);
				workNatureList.add(worknature1);
				if(!"4".equals(model.getBranch_level())){
					int branch_level = Integer.parseInt(model.getBranch_level());
					if("1".equals(model.getBranch_level())){
						branch_level = branch_level+2;
					}
					if("3".equals(model.getBranch_level())){
						branch_level = branch_level+1;
					}
					model.setBranch_level(String.valueOf(branch_level));
					//总公司本部
					IWorkNatureModel  worknature2 = (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryWorkNature1",model);
					int total2 = (int)this.getSqlMapClientTemplate().queryForObject("workNature.queryTotal1",model);
					worknature2.setTotal(total2);
					workNatureList.add(worknature2);
					List<String> branchList = this.getSqlMapClientTemplate().queryForList("workNature.queryBranchId",model);
					for(int i=0; i<branchList.size(); i++){
						String branch_id = branchList.get(i);
						model.setBranch_id(branch_id);
						IWorkNatureModel  worknature3 = (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryWorkNature",model);
						int total3 = (int)this.getSqlMapClientTemplate().queryForObject("workNature.queryTotal",model);
						worknature3.setTotal(total3);
						workNatureList.add(worknature3);
					}
				}else{
					IWorkNatureModel  worknature2 = (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryWorkNature1",model);
					int total2 = (int)this.getSqlMapClientTemplate().queryForObject("workNature.queryTotal1",model);
					worknature2.setTotal(total2);
					workNatureList.add(worknature2);
				}	
			 }
		}else if(model == null || (model != null && ("".equals(model.getBranch_level()) || "0".equals(model.getBranch_level())))){
			model.setBranch_id("0001");//默认查询总公司
			model.setBranch_level("0");
			//总公司汇总
			IWorkNatureModel  worknature1 = (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryWorkNature",model);
			if(worknature1 != null && worknature1.getBranch_name()!=null){
				worknature1.setBranch_name(worknature1.getBranch_name()+"总计");
			}
			int total1 = (int)this.getSqlMapClientTemplate().queryForObject("workNature.queryTotal",model);
			worknature1.setTotal(total1);
			workNatureList.add(worknature1);
			//总公司本部
			IWorkNatureModel  worknature2 = (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryWorkNature1",model);
			int total2 = (int)this.getSqlMapClientTemplate().queryForObject("workNature.queryTotal1",model);
			worknature2.setTotal(total2);
			workNatureList.add(worknature2);
			model.setBranch_level("1");
			List<String> branchList = this.getSqlMapClientTemplate().queryForList("workNature.queryBranchId",model);
			for(int i=0; i<branchList.size(); i++){
				String branch_id = branchList.get(i);
				model.setBranch_id(branch_id);
				IWorkNatureModel  worknature3 = (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryWorkNature",model);
				int total3 = (int)this.getSqlMapClientTemplate().queryForObject("workNature.queryTotal",model);
				worknature3.setTotal(total3);
				workNatureList.add(worknature3);
			}
			
		}
		 return workNatureList;
	}

	@Override
	public IWorkNatureModel queryTotal(IWorkNatureModel model) {
		// TODO Auto-generated method stub
		if(model!=null && !("0".equals(model.getBranch_level())||"1".equals(model.getBranch_level()))){
			return (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryTotal1",model);
		}else{
			return (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryTotal",model);
		}
		
	}

	@Override
	public IWorkNatureModel queryHeadquarter(IWorkNatureModel model) {
		// TODO Auto-generated method stub
		return (IWorkNatureModel) this.getSqlMapClientTemplate().queryForObject("workNature.queryHeadquarter",model);
	}

}
