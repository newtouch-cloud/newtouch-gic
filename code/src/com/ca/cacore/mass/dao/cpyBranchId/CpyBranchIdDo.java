package com.ca.cacore.mass.dao.cpyBranchId;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.core.daobase.BaseDao;

@Service
public class CpyBranchIdDo extends BaseDao implements ICpyBranchIdDao {
	
	// 获取机构ID
	@Override
	public String getCpyBranchId(String branch_parentid, String branch_level) {

		String id = (String) this.getSqlMapClientTemplate().queryForObject("Cpy.queryCpyBranch", branch_parentid);
		if (id != null && id != "") {
			
			if(id.length()>=10) {
				int j=Integer.parseInt(id.substring(id.length()-4))+1;
				String bb = id.substring(0,id.length()-3);
				id=bb+Integer.toString(j);
			}else {
				int a =0;
				try {
				 a = Integer.parseInt(id) + 1;
				 id = Integer.toString(a);
				}catch (Exception e) {
					String d =id.substring(0,1);
					String d2 =id.substring(1,id.length());
					 a = Integer.parseInt(d2) + 1;
                      id=d+String.valueOf(a);
				}
				
			}
		} else {
			
			if ("1".equals(branch_level) || "2".equals(branch_level)) {
				id = branch_parentid + "01";
			} else {
				id = branch_parentid + "001";
		    }
		}
		return id;
	}
	@Override
	public String getPCQ(ICompanyBranchModel model) {

		String max_cpyserno = model.getCpy_serno();
		StringBuffer cpy_id = new StringBuffer("");
		if("1".equals(model.getCpy_branch_level())){
			ArrayList<String> list = new ArrayList<String>();
			for(int i = 65; i < 91; i++){
				for(int j = 65; j < 91; j++){
					String ij = (char)i + ""+  (char)j ;
					list.add(ij);
				}
			}
			int k = (Integer.parseInt(max_cpyserno)%10000)-1;
			cpy_id.append(list.get(k));
			//cpy_id.append(model.getCity_code().substring(0,4));
			cpy_id.append("00000000");
		}else if("2".equals(model.getCpy_branch_level())){
			cpy_id.append(model.getBranch_id());
			cpy_id.append("00");
			cpy_id.append(max_cpyserno.substring(max_cpyserno.length()-4));
		}/*else if("3".equals(model.getCpy_branch_level())){
			cpy_id.append(model.getBranch_id());
			cpy_id.append(max_cpyserno.substring(max_cpyserno.length()-4));
		}*/else{
			cpy_id.append(model.getBranch_id());
			cpy_id.append(max_cpyserno.substring(max_cpyserno.length()-4));
		}
		/*else if("2".equals(model.getCpy_branch_level())){
			cpy_id.append(model.getBranch_id());
			cpy_id.append("000000");
		}else if("3".equals(model.getCpy_branch_level())){
			cpy_id.append(model.getBranch_id());
			cpy_id.append("0000");
		}else{
			cpy_id.append(model.getBranch_id());
			cpy_id.append(max_cpyserno.substring(max_cpyserno.length()-4));
		}*/
		/*else if("2".equals(model.getCpy_branch_level())){
			cpy_id.append(model.getBranch_id());
			cpy_id.append(max_cpyserno.substring(max_cpyserno.length()-2));
			cpy_id.append("000000");
		}else if("3".equals(model.getCpy_branch_level())){
			cpy_id.append(model.getBranch_id());
			cpy_id.append(max_cpyserno.substring(max_cpyserno.length()-2));
			cpy_id.append("0000");
		}else{
			cpy_id.append(model.getBranch_id());
			cpy_id.append(max_cpyserno.substring(max_cpyserno.length()-4));
		}*/
		return cpy_id.toString();
	}
	
	
	public Integer queryCpySerno(){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Cpy.queryCpySerno");
	}
	
	
	public static void main(String[] args) {
		String id="P202701";
		String d =id.substring(0,1);
		String d2 =id.substring(1,id.length());
		System.out.println(d2);
		int a = Integer.parseInt(d2) + 1;
		System.out.println(d+String.valueOf(a));
	}
	@Override
	public Integer queryCpySerno1(ICompanyBranchModel model) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Cpy.queryCpySerno1",model);
	}
	@Override
	public Integer queryCpySerno2(ICompanyBranchModel model) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Cpy.queryCpySerno2",model);
	}
}
