package com.newtouch.report.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.report.model.vo.IWorkRelationModel;
import com.newtouch.report.model.vo.Sysbranchcommission;

@Component
public class PersonalDaoImpl extends BaseDao implements PersonalDao {

	@Override
	public List<Sysbranchcommission> sysbranchcommission(Sysbranchcommission commission) {
		//查询所选机构的真实层级
		Integer branchLevel = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.querySysbranchLevel", commission);
		commission.setBranch_level(branchLevel);
		/*int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.sysbranchcommissioncount", commission);
		commission.getPageCount().setAllRows(count);
		commission.getPageCount().calcPage();
		commission.getPageCount().setNowPage(commission.getPageCount().getNowPage() + 1);
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querysysbranchcommission", commission);*/
		List<Sysbranchcommission> commissionList = new ArrayList<Sysbranchcommission>();
		if(commission!=null && commission.getBranch_id() !=null && !"".equals(commission.getBranch_id()) && commission.getBranch_level()!=0){
			 if((commission.getBranch_level()==1) || (commission.getBranch_level()==3) || (commission.getBranch_level()==4)){
				 Sysbranchcommission  commission1 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission",commission);
				if(commission1 != null && commission1.getBranch_name()!=null){
					commission1.setBranch_name(commission1.getBranch_name()+"总计");
					commissionList.add(commission1);
				}
				if(commission.getBranch_level()!=4){
					int branch_level = commission.getBranch_level();
					if(commission.getBranch_level()==1){
						branch_level = branch_level+2;
					}
					if(commission.getBranch_level()==3){
						branch_level = branch_level+1;
					}
					commission.setBranch_level(branch_level);
					//总公司本部
					Sysbranchcommission  commission2 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission1",commission);
					if(commission2!=null){
						commissionList.add(commission2);
					}
					List<Sysbranchcommission> list = (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querysysbranchcommission", commission);
					if(list!=null && list.size()>0){
						commissionList.addAll(list);
					}
				}else{
					//直属营业部
					Sysbranchcommission  commission2 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission",commission);
					if(commission2!=null){
						commissionList.add(commission2);
					}	
				}	
			 }
		}else if(commission == null || (commission != null && ((commission.getBranch_level()==null) || (commission.getBranch_level()==0)))){
			commission.setBranch_id("0001");//默认查询总公司
			//总公司汇总
			commission.setBranch_level(0);
			Sysbranchcommission  commission1 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission",commission);
			if(commission1 != null && commission1.getBranch_name()!=null){
				commission1.setBranch_name(commission1.getBranch_name()+"总计");
				commissionList.add(commission1);
			}
			//总公司本部
			commission.setBranch_level(1);
			Sysbranchcommission  commission2 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission1",commission);
			if(commission2!=null){
				commissionList.add(commission2);
			}
			List<Sysbranchcommission> list = (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querysysbranchcommission", commission);
			if(list!=null && list.size()>0){
				commissionList.addAll(list);
			}
		}
		 return commissionList;
	}
	
	@Override
	public List<Sysbranchcommission> sysbranch_monthcommission(Sysbranchcommission commission) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.sysbranch_monthcommissioncount", commission);
		commission.getPageCount().setAllRows(count);
		commission.getPageCount().calcPage();
		commission.getPageCount().setNowPage(commission.getPageCount().getNowPage() + 1);
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querysysbranch_monthcommission", commission);
	}
	

	@Override
	public List<Sysbranchcommission> citycommission(Sysbranchcommission commission) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.citycommissioncount", commission);
		commission.getPageCount().setAllRows(count);
		commission.getPageCount().calcPage();
		commission.getPageCount().setNowPage(commission.getPageCount().getNowPage() + 1);
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querycitycommission", commission);
	}

	@Override
	public List<Sysbranchcommission> departcommission(Sysbranchcommission commission) {
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querydepartcommission", commission);
	}

	@Override
	public Sysbranchcommission collectbranch(Sysbranchcommission commission) {
		return (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.collectbranch", commission);
	}
	@Override
	public Sysbranchcommission collectbranch_month(Sysbranchcommission commission) {
		return (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.collectbranch_month", commission);
	}

	@Override
	public Sysbranchcommission collectcity(Sysbranchcommission commission) {
		return (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.collectcity", commission);
	}

	@Override
	public List<Sysbranchcommission> downloadcitycommission(Sysbranchcommission commission) {
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcitycommission", commission);
	}

	@Override
	public List<Sysbranchcommission> downloadsysbranchcommission(Sysbranchcommission commission) {
		/*Integer branch_level = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.querySysbranchLevel", commission);
		commission.setBranch_level(branch_level);
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadsysbranchcommission", commission);*/
		//查询所选机构的真实层级
		Integer branchLevel = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.querySysbranchLevel", commission);
		commission.setBranch_level(branchLevel);
		List<Sysbranchcommission> commissionList = new ArrayList<Sysbranchcommission>();
		if(commission!=null && commission.getBranch_id() !=null && !"".equals(commission.getBranch_id()) && commission.getBranch_level()!=0){
			 if((commission.getBranch_level()==1) || (commission.getBranch_level()==3) || (commission.getBranch_level()==4)){
				 Sysbranchcommission  commission1 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission",commission);
				if(commission1 != null && commission1.getBranch_name()!=null){
					commission1.setBranch_name(commission1.getBranch_name()+"总计");
					commissionList.add(commission1);
				}
				if(commission.getBranch_level()!=4){
					int branch_level = commission.getBranch_level();
					if(commission.getBranch_level()==1){
						branch_level = branch_level+2;
					}
					if(commission.getBranch_level()==3){
						branch_level = branch_level+1;
					}
					commission.setBranch_level(branch_level);
					//总公司本部
					Sysbranchcommission  commission2 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission1",commission);
					if(commission2!=null){
						commissionList.add(commission2);
					}
					List<Sysbranchcommission> list = (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querysysbranchcommission", commission);
					if(list!=null && list.size()>0){
						commissionList.addAll(list);
					}
				}else{
					//直属营业部
					Sysbranchcommission  commission2 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission",commission);
					if(commission2!=null){
						commissionList.add(commission2);
					}	
				}	
			 }
		}else if(commission == null || (commission != null && ((commission.getBranch_level()==null) || (commission.getBranch_level()==0)))){
			commission.setBranch_id("0001");//默认查询总公司
			//总公司汇总
			commission.setBranch_level(0);
			Sysbranchcommission  commission1 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission",commission);
			if(commission1 != null && commission1.getBranch_name()!=null){
				commission1.setBranch_name(commission1.getBranch_name()+"总计");
				commissionList.add(commission1);
			}
			//总公司本部
			commission.setBranch_level(1);
			Sysbranchcommission  commission2 = (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.querysysbranchcommission1",commission);
			if(commission2!=null){
				commissionList.add(commission2);
			}
			List<Sysbranchcommission> list = (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querysysbranchcommission", commission);
			if(list!=null && list.size()>0){
				commissionList.addAll(list);
			}
		}
		 return commissionList;
	}
	
	@Override
	public List<Sysbranchcommission> downloadsysbranch_monthcommission(Sysbranchcommission commission) {
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadsysbranch_monthcommission", commission);
	}

	@Override
	public List<Sysbranchcommission> downloaddepartcommission(Sysbranchcommission commission) {
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querydepartcommission", commission);
	}

	
	
	
	@Override
	public List<Sysbranchcommission> sysagencycommission(Sysbranchcommission commission) {
		/*Integer branch_level = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.querySysbranchLevel", commission);
		commission.setBranch_level(branch_level);*/
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.sysagencycommissioncount", commission);
		commission.getPageCount().setAllRows(count);
		commission.getPageCount().calcPage();
		commission.getPageCount().setNowPage(commission.getPageCount().getNowPage() + 1);
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querysysagencycommission", commission);
	}

	@Override
	public Sysbranchcommission collectagency(Sysbranchcommission commission) {
		return (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.collectagency", commission);
	}

	@Override
	public List<Sysbranchcommission> downloadagencycommission(Sysbranchcommission commission) {
		/*Integer branch_level = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.querySysbranchLevel", commission);
		commission.setBranch_level(branch_level);*/
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadagencycommission", commission);
	}

	
	
	
	
	@Override
	public List<Sysbranchcommission> cpybranchcommission(Sysbranchcommission commission) {
		/*if(commission != null && commission.getCpy_branch_id()!=null && !"".equals(commission.getCpy_branch_id())){
			int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.cpybranchcommissioncount", commission);
			commission.getPageCount().setAllRows(count);
			commission.getPageCount().calcPage();
			commission.getPageCount().setNowPage(commission.getPageCount().getNowPage() + 1);
			return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranchcommission", commission);
		}else{
			List<String> list = this.getSqlMapClientTemplate().queryForList("personal.queryCpyBranch", commission);//查询一级保险公司代码
			List<Sysbranchcommission> commissionlist = new ArrayList<Sysbranchcommission>();
			for(int i=0; i<list.size(); i++){
				commission.setCpy_branch_id(list.get(i));
				int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.cpybranchcommissioncount", commission);
				commission.getPageCount().setAllRows(count);
				commission.getPageCount().calcPage();
				commission.getPageCount().setNowPage(commission.getPageCount().getNowPage() + 1);
				List<Sysbranchcommission> commisslist = (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranchcommission", commission);
				commissionlist.addAll(commisslist);
			}
			return commissionlist;
		}*/
		List<Sysbranchcommission> commissionlist = new ArrayList<Sysbranchcommission>();
		if(commission != null && commission.getCpy_branch_id()!=null && !"".equals(commission.getCpy_branch_id())){
			/*int branch_level = (int)this.getSqlMapClientTemplate().queryForObject("personal.queryInsBranchLevel", commission);*/
			//递归查询该公司
			List<Sysbranchcommission> list1 =  (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranchcommission", commission);
			if(list1 != null && list1.size() > 0 ){
				commissionlist.addAll(list1);
			}
			//查询该公司本部
			Sysbranchcommission sysbranchcommission = (Sysbranchcommission)this.getSqlMapClientTemplate().queryForObject("personal.downloadcpybranchcommission1", commission);
			if(sysbranchcommission != null){
				sysbranchcommission.setBranch_name(sysbranchcommission.getBranch_name()+"本部");
				commissionlist.add(sysbranchcommission);
			}
			//commission.setOrg_level(commission.getOrg_level()+1);
			List<String> cpyBranchList = (List<String>)this.getSqlMapClientTemplate().queryForList("personal.queryCpyBranchId", commission);
			for(int i=0; i<cpyBranchList.size(); i++){
				String cpy_branch_id = cpyBranchList.get(i);
				commission.setCpy_branch_id(cpy_branch_id);
				List<Sysbranchcommission> list2 =  (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranchcommission", commission);
				if(list2 != null && list2.size() > 0 ){
					commissionlist.addAll(list2);
				}
			}
		}else{
			//commission.setOrg_level(1);
			List<String> list = this.getSqlMapClientTemplate().queryForList("personal.queryCpyBranch", commission);//查询一级保险公司代码
			for(int i=0; i<list.size(); i++){
				commission.setCpy_branch_id(list.get(i));
				List<Sysbranchcommission> commisslist = (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranchcommission", commission);
				if(commisslist != null && commisslist.size() > 0 ){
					commissionlist.addAll(commisslist);
				}
			}
		}
		return commissionlist;
		
	}

	@Override
	public Sysbranchcommission collectcpybranch(Sysbranchcommission commission) {
		return (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.collectcpybranch", commission);
	}

	@Override
	public List<Sysbranchcommission> downloadcpybranchcommission(Sysbranchcommission commission) {
		//return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranchcommission", commission);
		List<Sysbranchcommission> commissionlist = new ArrayList<Sysbranchcommission>();
		if(commission != null && commission.getCpy_branch_id()!=null && !"".equals(commission.getCpy_branch_id())){
			/*int branch_level = (int)this.getSqlMapClientTemplate().queryForObject("personal.queryInsBranchLevel", commission);*/
			//递归查询该公司
			List<Sysbranchcommission> list1 =  (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranchcommission", commission);
			if(list1 != null && list1.size() > 0 ){
				commissionlist.addAll(list1);
			}
			//查询该公司本部
			Sysbranchcommission sysbranchcommission = (Sysbranchcommission)this.getSqlMapClientTemplate().queryForObject("personal.downloadcpybranchcommission1", commission);
			if(sysbranchcommission != null){
				sysbranchcommission.setBranch_name(sysbranchcommission.getBranch_name()+"本部");
				commissionlist.add(sysbranchcommission);
			}
			//commission.setOrg_level(commission.getOrg_level()+1);
			List<String> cpyBranchList = (List<String>)this.getSqlMapClientTemplate().queryForList("personal.queryCpyBranchId", commission);
			for(int i=0; i<cpyBranchList.size(); i++){
				String cpy_branch_id = cpyBranchList.get(i);
				commission.setCpy_branch_id(cpy_branch_id);
				List<Sysbranchcommission> list2 =  (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranchcommission", commission);
				if(list2 != null && list2.size() > 0 ){
					commissionlist.addAll(list2);
				}
			}
		}else{
			//commission.setOrg_level(1);
			List<String> list = this.getSqlMapClientTemplate().queryForList("personal.queryCpyBranch", commission);//查询一级保险公司代码
			for(int i=0; i<list.size(); i++){
				commission.setCpy_branch_id(list.get(i));
				List<Sysbranchcommission> commisslist = (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranchcommission", commission);
				if(commisslist != null && commisslist.size() > 0 ){
					commissionlist.addAll(commisslist);
				}
			}
		}
		return commissionlist;
	}
	
	
	
	@Override
	public List<Sysbranchcommission> cpybranch_monthcommission(Sysbranchcommission commission) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.cpybranch_monthcommissioncount", commission);
		commission.getPageCount().setAllRows(count);
		commission.getPageCount().calcPage();
		commission.getPageCount().setNowPage(commission.getPageCount().getNowPage() + 1);
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.querycpybranch_monthcommission", commission);
	}

	@Override
	public Sysbranchcommission collectcpybranch_month(Sysbranchcommission commission) {
		return (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.collectcpybranch_month", commission);
	}

	@Override
	public List<Sysbranchcommission> downloadcpybranch_monthcommission(Sysbranchcommission commission) {
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadcpybranch_monthcommission", commission);
	}
	
	
	
	
	
	@Override
	public List<Sysbranchcommission> insurancecommission(Sysbranchcommission commission) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.insurancecommissioncount", commission);
		commission.getPageCount().setAllRows(count);
		commission.getPageCount().calcPage();
		commission.getPageCount().setNowPage(commission.getPageCount().getNowPage() + 1);
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.queryinsurancecommission", commission);
	}
	
	@Override
	public Sysbranchcommission collectinsurance(Sysbranchcommission commission) {
		return (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.collectinsurance", commission);
	}

	@Override
	public List<Sysbranchcommission> downloadinsurancecommission(Sysbranchcommission commission) {
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadinsurancecommission", commission);
	}
	
	
	
	
	
	

	@Override
	public List<Sysbranchcommission> insurance_monthcommission(Sysbranchcommission commission) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personal.insurancec_monthommissioncount", commission);
		commission.getPageCount().setAllRows(count);
		commission.getPageCount().calcPage();
		commission.getPageCount().setNowPage(commission.getPageCount().getNowPage() + 1);
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.queryinsurance_monthcommission", commission);
	}
	
	@Override
	public Sysbranchcommission collectinsurance_month(Sysbranchcommission commission) {
		return (Sysbranchcommission) this.getSqlMapClientTemplate().queryForObject("personal.collectinsurance_month", commission);
	}

	@Override
	public List<Sysbranchcommission> downloadinsurance_monthcommission(Sysbranchcommission commission) {
		return (List<Sysbranchcommission>) this.getSqlMapClientTemplate().queryForList("personal.downloadinsurance_monthcommission", commission);
	}

	@Override
	public Sysbranchcommission collectinsurance_sum(
			Sysbranchcommission commission) {
		// TODO Auto-generated method stub
		return (Sysbranchcommission) this.getSqlMapClientTemplate().queryForList("personal.collectinsurance_sum", commission);
	}
	
	
}
