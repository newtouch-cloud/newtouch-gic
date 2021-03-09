package com.newtouch.report.webapp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.report.domain.PersonalDomain;
import com.newtouch.report.model.vo.Sysbranchcommission;
import com.newtouch.report.model.vo.Sysbranchcommissionimpl;


@Service
public class PesonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalDomain personalDomain;
	
	@Override
	public ReturnMsg sysbranchcommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.sysbranchcommission(commission);
		addUG(list);
		Sysbranchcommission sysbranchcommission = personalDomain.collectbranch(commission);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("collectcity", sysbranchcommission);
		msg.setDataList(TransHelper.list2MapList(list));
		msg.setDataTable(map);
		return msg;
	}

	@Override
	public ReturnMsg citycommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.citycommission(commission);
		addUG(list);
		Sysbranchcommission sysbranchcommission = personalDomain.collectcity(commission);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("collectcity", sysbranchcommission);
		msg.setDataList(TransHelper.list2MapList(list));
		msg.setDataTable(map);
		return msg;
	}

	@Override
	public ReturnMsg departcommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.departcommission(commission);
		addUG(list);
		Map<String,Object> map = new HashMap<String, Object>();
		if (list.size() > 0) {
			map.put("collectcity", list.get(0));
		}
		msg.setDataList(TransHelper.list2MapList(list));
		msg.setDataTable(map);
		return msg;
	}

	@Override
	public ReturnMsg downloadcitycommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.downloadcitycommission(commission);
		addUG(list);
		Sysbranchcommission sysbranchcommission = personalDomain.collectbranch(commission);
		List<Map<String,Object>> renturnlist = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("collectcity", sysbranchcommission);
		map.put("list", list);
		renturnlist.add(map);
		msg.setDataList(renturnlist);
		return msg;
	}

	@Override
	public ReturnMsg downloadsysbranchcommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.downloadsysbranchcommission(commission);
		addUG(list);
		Sysbranchcommission sysbranchcommission = personalDomain.collectcity(commission);
		List<Map<String,Object>> renturnlist = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("collectcity", sysbranchcommission);
		map.put("list", list);
		renturnlist.add(map);
		msg.setDataList(renturnlist);
		return msg;
	}

	@Override
	public ReturnMsg downloaddepartcommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.downloaddepartcommission(commission);
		addUG(list);
		List<Map<String,Object>> renturnlist = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("collectcity", list.get(0));
		map.put("list", list);
		renturnlist.add(map);
		msg.setDataList(renturnlist);
		return msg;
	}

	@Override
	public ReturnMsg sysagencycommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.sysagencycommission(commission);
		addUG(list);
		Sysbranchcommission sysbranchcommission = personalDomain.collectagency(commission);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("collectcity", sysbranchcommission);
		msg.setDataList(TransHelper.list2MapList(list));
		msg.setDataTable(map);
		return msg;
	}

	@Override
	public ReturnMsg downloadagencycommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.downloadagencycommission(commission);
		addUG(list);
		Sysbranchcommission sysbranchcommission_sum = commission_sum1(list);
		Sysbranchcommission sysagencycommission = personalDomain.collectagency(commission);
		List<Map<String,Object>> renturnlist = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sysbranchcommission_sum", sysbranchcommission_sum);
		map.put("collectcity", sysagencycommission);
		map.put("list", list);
		renturnlist.add(map);
		msg.setDataList(renturnlist);
		return msg;
	}

	@Override
	public ReturnMsg cpybranchcommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.cpybranchcommission(commission);
		addUG(list);
		Sysbranchcommission sysbranchcommission = personalDomain.collectcpybranch(commission);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("collectcity", sysbranchcommission);
		msg.setDataList(TransHelper.list2MapList(list));
		msg.setDataTable(map);
		return msg;
	}
	
	@Override
	public ReturnMsg downloadcpybranchcommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		String cpy_branch_id = commission.getCpy_branch_id();
		System.out.println("cpy_branch_id="+cpy_branch_id);
		List<Sysbranchcommission> list = personalDomain.downloadcpybranchcommission(commission);
		Sysbranchcommission sysbranchcommission_sum = new Sysbranchcommissionimpl();
		addUG(list);
		if ("1".equals(commission.getPerson_flag())) {//寿险
			if(cpy_branch_id!=null && !"".equals(cpy_branch_id)){
				System.out.println("cpy_branch_id="+cpy_branch_id);
				if(list != null && list.size() > 0){
					sysbranchcommission_sum = list.get(0);
				}
			}else{
				sysbranchcommission_sum = commission_sum2(list);
			}
		}else{//财险
			if(cpy_branch_id!=null && !"".equals(cpy_branch_id)){
				if(list != null && list.size() > 0){
					sysbranchcommission_sum = list.get(0);
				}
			}else{
				sysbranchcommission_sum = commission_sum1(list);
			}
		}
		Sysbranchcommission sysagencycommission = personalDomain.collectcpybranch(commission);
		List<Map<String,Object>> renturnlist = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("collectcity", sysagencycommission);
		map.put("sysbranchcommission_sum", sysbranchcommission_sum);
		map.put("list", list);
		renturnlist.add(map);
		msg.setDataList(renturnlist);
		return msg;
	}
	
	@Override
	public ReturnMsg insurancecommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.insurancecommission(commission);
		addUG(list);
		Sysbranchcommission sysbranchcommission = personalDomain.collectinsurance(commission);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("collectcity", sysbranchcommission);
		msg.setDataList(TransHelper.list2MapList(list));
		msg.setDataTable(map);
		return msg;
	}
	
	@Override
	public ReturnMsg downloadinsurancecommission(Sysbranchcommission commission) {
		ReturnMsg msg = new ReturnMsg();
		List<Sysbranchcommission> list = personalDomain.downloadinsurancecommission(commission);
		addUG(list);
		Sysbranchcommission sysbranchcommission_sum = new Sysbranchcommissionimpl();
		if ("1".equals(commission.getPerson_flag())) {//寿险
			sysbranchcommission_sum = commission_sum2(list);
		}else{//财险
			sysbranchcommission_sum = commission_sum1(list);
		}
		Sysbranchcommission sysagencycommission = personalDomain.collectinsurance(commission);
		List<Map<String,Object>> renturnlist = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sysbranchcommission_sum", sysbranchcommission_sum);
		map.put("collectcity", sysagencycommission);
		map.put("list", list);
		renturnlist.add(map);
		msg.setDataList(renturnlist);
		return msg;
	}
	
	
	List<Sysbranchcommission> addUG(List<Sysbranchcommission> sysagencycommission){
		for (int i = 0; i < sysagencycommission.size(); i++) {
			Sysbranchcommission sysbranchcommission = sysagencycommission.get(i);
			/*
			 * <td>${person.g_month_count}</td> 
			 * <td>${person.g_month_amount}</td>
			 * <td>${person.g_month_premium}</td> 
			 * <td>${person.g_month_fee}</td>
			 * <td>${person.newg_month_premium}</td> 
			 * <td>${person.newg_month_fee}</td>
			 */
			
			BigDecimal g_month_count = sysbranchcommission.getG_month_count();
			BigDecimal u_month_count = sysbranchcommission.getU_month_count();
			sysbranchcommission.setAll_month_count(addBigDecimal(g_month_count, u_month_count));
			
			BigDecimal g_month_amount = sysbranchcommission.getG_month_amount();
			BigDecimal u_month_amount = sysbranchcommission.getU_month_amount();
			sysbranchcommission.setAll_month_amount(addBigDecimal(g_month_amount, u_month_amount));
			
			BigDecimal g_month_premium = sysbranchcommission.getG_month_premium();
			BigDecimal u_month_premium = sysbranchcommission.getU_month_premium();
			sysbranchcommission.setAll_month_premium(addBigDecimal(g_month_premium, u_month_premium));
			
			BigDecimal g_month_fee = sysbranchcommission.getG_month_fee();
			BigDecimal u_month_fee = sysbranchcommission.getU_month_fee();
			sysbranchcommission.setAll_month_fee(addBigDecimal(g_month_fee, u_month_fee));
			
			String newg_month_premium = sysbranchcommission.getNewg_month_premium();
			String newu_month_premium = sysbranchcommission.getNewu_month_premium();
			sysbranchcommission.setNewall_month_premium(addString(newg_month_premium, newu_month_premium));
			
			String newg_month_fee = sysbranchcommission.getNewg_month_fee();
			String newu_month_fee = sysbranchcommission.getNewu_month_fee();
			sysbranchcommission.setNewall_month_fee(addString(newg_month_fee, newu_month_fee));
			
			
			BigDecimal g_year_count = sysbranchcommission.getG_year_count();
			BigDecimal u_year_count = sysbranchcommission.getU_year_count();
			sysbranchcommission.setAll_year_count(addBigDecimal(g_year_count, u_year_count));
			
			BigDecimal g_year_amount = sysbranchcommission.getG_year_amount();
			BigDecimal u_year_amount = sysbranchcommission.getU_year_amount();
			sysbranchcommission.setAll_year_amount(addBigDecimal(g_year_amount, u_year_amount));
			
			BigDecimal g_year_premium = sysbranchcommission.getG_year_premium();
			BigDecimal u_year_premium = sysbranchcommission.getU_year_premium();
			sysbranchcommission.setAll_year_premium(addBigDecimal(g_year_premium, u_year_premium));
			
			BigDecimal g_year_fee = sysbranchcommission.getG_year_fee();
			BigDecimal u_year_fee = sysbranchcommission.getU_year_fee();
			sysbranchcommission.setAll_year_fee(addBigDecimal(g_year_fee, u_year_fee));
			
			String newg_year_premium = sysbranchcommission.getNewg_year_premium();
			String newu_year_premium = sysbranchcommission.getNewu_year_premium();
			sysbranchcommission.setNewall_year_premium(addString(newg_year_premium, newu_year_premium));
			
			String newg_year_fee = sysbranchcommission.getNewg_year_fee();
			String newu_year_fee = sysbranchcommission.getNewu_year_fee();
			sysbranchcommission.setNewall_year_fee(addString(newg_year_fee, newu_year_fee));
			
		}
		return sysagencycommission;
	}
	
	BigDecimal addBigDecimal(BigDecimal a,BigDecimal b) {
		if (a == null) {
			a = new BigDecimal(0);
		}
		if (b == null) {
			b = new BigDecimal(0);
		}
		BigDecimal c = a.add(b);
		return c;
	}
	
	
	String addString(String a,String b) {
		try {
			BigDecimal aBigDecimal = null;
			BigDecimal bBigDecimal = null;
			if (a == null || "".equals(a)) {
				aBigDecimal = new BigDecimal(0);
			}else {
				aBigDecimal = new BigDecimal(Double.parseDouble(a));
			}
			if (b == null || "".equals(b)) {
				bBigDecimal = new BigDecimal(0);
			}else {
				bBigDecimal = new BigDecimal(Double.parseDouble(b));
			}
			BigDecimal c = aBigDecimal.add(bBigDecimal);
			return c.toString();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	public Sysbranchcommission commission_sum1(List<Sysbranchcommission> list){
		Sysbranchcommission sysbranchcommission_sum = new Sysbranchcommissionimpl();
		sysbranchcommission_sum.setBranch_name("合计");
		sysbranchcommission_sum.setG_day_count(new BigDecimal(0));
		sysbranchcommission_sum.setG_day_amount(new BigDecimal(0));
		sysbranchcommission_sum.setG_day_premium(new BigDecimal(0));
		sysbranchcommission_sum.setG_day_fee(new BigDecimal(0));
		sysbranchcommission_sum.setG_month_count(new BigDecimal(0));
		sysbranchcommission_sum.setG_month_amount(new BigDecimal(0));
		sysbranchcommission_sum.setG_month_premium(new BigDecimal(0));
		sysbranchcommission_sum.setG_month_fee(new BigDecimal(0));
		sysbranchcommission_sum.setG_year_count(new BigDecimal(0));
		sysbranchcommission_sum.setG_year_amount(new BigDecimal(0));
		sysbranchcommission_sum.setG_year_premium(new BigDecimal(0));
		sysbranchcommission_sum.setG_year_fee(new BigDecimal(0));
		
		sysbranchcommission_sum.setU_day_count(new BigDecimal(0));
		sysbranchcommission_sum.setU_day_amount(new BigDecimal(0));
		sysbranchcommission_sum.setU_day_premium(new BigDecimal(0));
		sysbranchcommission_sum.setU_day_fee(new BigDecimal(0));
		sysbranchcommission_sum.setU_month_count(new BigDecimal(0));
		sysbranchcommission_sum.setU_month_amount(new BigDecimal(0));
		sysbranchcommission_sum.setU_month_premium(new BigDecimal(0));
		sysbranchcommission_sum.setU_month_fee(new BigDecimal(0));
		sysbranchcommission_sum.setU_year_count(new BigDecimal(0));
		sysbranchcommission_sum.setU_year_amount(new BigDecimal(0));
		sysbranchcommission_sum.setU_year_premium(new BigDecimal(0));
		sysbranchcommission_sum.setU_year_fee(new BigDecimal(0));
		
		for(int i = 0; i < list.size(); i++){
			Sysbranchcommission sysbranchcommission = list.get(i);
			if((sysbranchcommission.getG_day_count()!=null) &&(!"".equals(sysbranchcommission.getG_day_count()))){
				sysbranchcommission_sum.setG_day_count(sysbranchcommission_sum.getG_day_count().add(sysbranchcommission.getG_day_count()));
			}else{
				sysbranchcommission_sum.setG_day_count(sysbranchcommission_sum.getG_day_count().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_day_amount()!=null) &&(!"".equals(sysbranchcommission.getG_day_amount()))){
				sysbranchcommission_sum.setG_day_amount(sysbranchcommission_sum.getG_day_amount().add(sysbranchcommission.getG_day_amount()));
			}else{
				sysbranchcommission_sum.setG_day_amount(sysbranchcommission_sum.getG_day_amount().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_day_premium()!=null) &&(!"".equals(sysbranchcommission.getG_day_premium()))){
				sysbranchcommission_sum.setG_day_premium(sysbranchcommission_sum.getG_day_premium().add(sysbranchcommission.getG_day_premium()));
			}else{
				sysbranchcommission_sum.setG_day_premium(sysbranchcommission_sum.getG_day_premium().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_day_fee()!=null) &&(!"".equals(sysbranchcommission.getG_day_fee()))){
				sysbranchcommission_sum.setG_day_fee(sysbranchcommission_sum.getG_day_fee().add(sysbranchcommission.getG_day_fee()));
			}else{
				sysbranchcommission_sum.setG_day_fee(sysbranchcommission_sum.getG_day_fee().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_month_count()!=null) &&(!"".equals(sysbranchcommission.getG_month_count()))){
				sysbranchcommission_sum.setG_month_count(sysbranchcommission_sum.getG_month_count().add(sysbranchcommission.getG_month_count()));
			}else{
				sysbranchcommission_sum.setG_month_count(sysbranchcommission_sum.getG_month_count().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_month_amount()!=null) &&(!"".equals(sysbranchcommission.getG_month_amount()))){
				sysbranchcommission_sum.setG_month_amount(sysbranchcommission_sum.getG_month_amount().add(sysbranchcommission.getG_month_amount()));
			}else{
				sysbranchcommission_sum.setG_month_amount(sysbranchcommission_sum.getG_month_amount().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_month_premium()!=null) &&(!"".equals(sysbranchcommission.getG_month_premium()))){
				sysbranchcommission_sum.setG_month_premium(sysbranchcommission_sum.getG_month_premium().add(sysbranchcommission.getG_month_premium()));
			}else{
				sysbranchcommission_sum.setG_month_premium(sysbranchcommission_sum.getG_month_premium().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_month_fee()!=null) &&(!"".equals(sysbranchcommission.getG_month_fee()))){
				sysbranchcommission_sum.setG_month_fee(sysbranchcommission_sum.getG_month_fee().add(sysbranchcommission.getG_month_fee()));
			}else{
				sysbranchcommission_sum.setG_month_fee(sysbranchcommission_sum.getG_month_fee().add(new BigDecimal(0)));
			}		
			if((sysbranchcommission.getG_year_count()!=null) &&(!"".equals(sysbranchcommission.getG_year_count()))){
				sysbranchcommission_sum.setG_year_count(sysbranchcommission_sum.getG_year_count().add(sysbranchcommission.getG_year_count()));
			}else{
				sysbranchcommission_sum.setG_year_count(sysbranchcommission_sum.getG_year_count().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_year_amount()!=null) &&(!"".equals(sysbranchcommission.getG_year_amount()))){
				sysbranchcommission_sum.setG_year_amount(sysbranchcommission_sum.getG_year_amount().add(sysbranchcommission.getG_year_amount()));
			}else{
				sysbranchcommission_sum.setG_year_amount(sysbranchcommission_sum.getG_year_amount().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_year_premium()!=null) &&(!"".equals(sysbranchcommission.getG_year_premium()))){
				sysbranchcommission_sum.setG_year_premium(sysbranchcommission_sum.getG_year_premium().add(sysbranchcommission.getG_year_premium()));
			}else{
				sysbranchcommission_sum.setG_year_premium(sysbranchcommission_sum.getG_year_premium().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getG_year_fee()!=null) &&(!"".equals(sysbranchcommission.getG_year_fee()))){
				sysbranchcommission_sum.setG_year_fee(sysbranchcommission_sum.getG_year_fee().add(sysbranchcommission.getG_year_fee()));
			}else{
				sysbranchcommission_sum.setG_year_fee(sysbranchcommission_sum.getG_year_fee().add(new BigDecimal(0)));
			}	
			if((sysbranchcommission.getU_day_count()!=null) &&(!"".equals(sysbranchcommission.getU_day_count()))){
				sysbranchcommission_sum.setU_day_count(sysbranchcommission_sum.getU_day_count().add(sysbranchcommission.getU_day_count()));
			}else{
				sysbranchcommission_sum.setU_day_count(sysbranchcommission_sum.getU_day_count().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_day_amount()!=null) &&(!"".equals(sysbranchcommission.getU_day_amount()))){
				sysbranchcommission_sum.setU_day_amount(sysbranchcommission_sum.getU_day_amount().add(sysbranchcommission.getU_day_amount()));
			}else{
				sysbranchcommission_sum.setU_day_amount(sysbranchcommission_sum.getU_day_amount().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_day_premium()!=null) &&(!"".equals(sysbranchcommission.getU_day_premium()))){
				sysbranchcommission_sum.setU_day_premium(sysbranchcommission_sum.getU_day_premium().add(sysbranchcommission.getU_day_premium()));
			}else{
				sysbranchcommission_sum.setU_day_premium(sysbranchcommission_sum.getU_day_premium().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_day_fee()!=null) &&(!"".equals(sysbranchcommission.getU_day_fee()))){
				sysbranchcommission_sum.setU_day_fee(sysbranchcommission_sum.getU_day_fee().add(sysbranchcommission.getU_day_fee()));
			}else{
				sysbranchcommission_sum.setU_day_fee(sysbranchcommission_sum.getU_day_fee().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_month_count()!=null) &&(!"".equals(sysbranchcommission.getU_month_count()))){
				sysbranchcommission_sum.setU_month_count(sysbranchcommission_sum.getU_month_count().add(sysbranchcommission.getU_month_count()));
			}else{
				sysbranchcommission_sum.setU_month_count(sysbranchcommission_sum.getU_month_count().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_month_amount()!=null) &&(!"".equals(sysbranchcommission.getU_month_amount()))){
				sysbranchcommission_sum.setU_month_amount(sysbranchcommission_sum.getU_month_amount().add(sysbranchcommission.getU_month_amount()));
			}else{
				sysbranchcommission_sum.setU_month_amount(sysbranchcommission_sum.getU_month_amount().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_month_premium()!=null) &&(!"".equals(sysbranchcommission.getU_month_premium()))){
				sysbranchcommission_sum.setU_month_premium(sysbranchcommission_sum.getU_month_premium().add(sysbranchcommission.getU_month_premium()));
			}else{
				sysbranchcommission_sum.setU_month_premium(sysbranchcommission_sum.getU_month_premium().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_month_fee()!=null) &&(!"".equals(sysbranchcommission.getU_month_fee()))){
				sysbranchcommission_sum.setU_month_fee(sysbranchcommission_sum.getU_month_fee().add(sysbranchcommission.getU_month_fee()));
			}else{
				sysbranchcommission_sum.setU_month_fee(sysbranchcommission_sum.getU_month_fee().add(new BigDecimal(0)));
			}		
			if((sysbranchcommission.getU_year_count()!=null) &&(!"".equals(sysbranchcommission.getU_year_count()))){
				sysbranchcommission_sum.setU_year_count(sysbranchcommission_sum.getU_year_count().add(sysbranchcommission.getU_year_count()));
			}else{
				sysbranchcommission_sum.setU_year_count(sysbranchcommission_sum.getU_year_count().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_year_amount()!=null) &&(!"".equals(sysbranchcommission.getU_year_amount()))){
				sysbranchcommission_sum.setU_year_amount(sysbranchcommission_sum.getU_year_amount().add(sysbranchcommission.getU_year_amount()));
			}else{
				sysbranchcommission_sum.setU_year_amount(sysbranchcommission_sum.getU_year_amount().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_year_premium()!=null) &&(!"".equals(sysbranchcommission.getU_year_premium()))){
				sysbranchcommission_sum.setU_year_premium(sysbranchcommission_sum.getU_year_premium().add(sysbranchcommission.getU_year_premium()));
			}else{
				sysbranchcommission_sum.setU_year_premium(sysbranchcommission_sum.getU_year_premium().add(new BigDecimal(0)));
			}
			if((sysbranchcommission.getU_year_fee()!=null) &&(!"".equals(sysbranchcommission.getU_year_fee()))){
				sysbranchcommission_sum.setU_year_fee(sysbranchcommission_sum.getU_year_fee().add(sysbranchcommission.getU_year_fee()));
			}else{
				sysbranchcommission_sum.setU_year_fee(sysbranchcommission_sum.getU_year_fee().add(new BigDecimal(0)));
			}
		}
		
		if((sysbranchcommission_sum.getG_day_fee()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_day_fee("+"+sysbranchcommission_sum.getG_day_fee().toString());
		}else{
			sysbranchcommission_sum.setNewg_day_fee(sysbranchcommission_sum.getG_day_fee().toString());
		}
		if((sysbranchcommission_sum.getG_day_premium()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_day_premium("+"+sysbranchcommission_sum.getG_day_premium().toString());
		}else{
			sysbranchcommission_sum.setNewg_day_premium(sysbranchcommission_sum.getG_day_premium().toString());
		}
		if((sysbranchcommission_sum.getG_month_fee()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_month_fee("+"+sysbranchcommission_sum.getG_month_fee().toString());
		}else{
			sysbranchcommission_sum.setNewg_month_fee(sysbranchcommission_sum.getG_month_fee().toString());
		}
		if((sysbranchcommission_sum.getG_month_premium()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_month_premium("+"+sysbranchcommission_sum.getG_month_premium().toString());
		}else{
			sysbranchcommission_sum.setNewg_month_premium(sysbranchcommission_sum.getG_month_premium().toString());
		}
		if((sysbranchcommission_sum.getG_year_fee()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_year_fee("+"+sysbranchcommission_sum.getG_year_fee().toString());
		}else{
			sysbranchcommission_sum.setNewg_year_fee(sysbranchcommission_sum.getG_year_fee().toString());
		}
		if((sysbranchcommission_sum.getG_year_premium()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_year_premium("+"+sysbranchcommission_sum.getG_year_premium().toString());
		}else{
			sysbranchcommission_sum.setNewg_year_premium(sysbranchcommission_sum.getG_year_premium().toString());
		}
		
		if((sysbranchcommission_sum.getU_day_fee()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewu_day_fee("+"+sysbranchcommission_sum.getU_day_fee().toString());
		}else{
			sysbranchcommission_sum.setNewu_day_fee(sysbranchcommission_sum.getU_day_fee().toString());
		}
		if((sysbranchcommission_sum.getU_day_premium()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewu_day_premium("+"+sysbranchcommission_sum.getU_day_premium().toString());
		}else{
			sysbranchcommission_sum.setNewu_day_premium(sysbranchcommission_sum.getU_day_premium().toString());
		}
		if((sysbranchcommission_sum.getU_month_fee()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewu_month_fee("+"+sysbranchcommission_sum.getU_month_fee().toString());
		}else{
			sysbranchcommission_sum.setNewu_month_fee(sysbranchcommission_sum.getU_month_fee().toString());
		}
		if((sysbranchcommission_sum.getU_month_premium()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewu_month_premium("+"+sysbranchcommission_sum.getU_month_premium().toString());
		}else{
			sysbranchcommission_sum.setNewu_month_premium(sysbranchcommission_sum.getU_month_premium().toString());
		}
		if((sysbranchcommission_sum.getU_year_fee()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewu_year_fee("+"+sysbranchcommission_sum.getU_year_fee().toString());
		}else{
			sysbranchcommission_sum.setNewu_year_fee(sysbranchcommission_sum.getU_year_fee().toString());
		}
		if((sysbranchcommission_sum.getU_year_premium()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewu_year_premium("+"+sysbranchcommission_sum.getU_year_premium().toString());
		}else{
			sysbranchcommission_sum.setNewu_year_premium(sysbranchcommission_sum.getU_year_premium().toString());
		}
		
		
		return sysbranchcommission_sum;
	}
	
	public Sysbranchcommission commission_sum2(List<Sysbranchcommission> list){
		Sysbranchcommission sysbranchcommission_sum = new Sysbranchcommissionimpl();
		sysbranchcommission_sum.setBranch_name("合计");
		/*sysbranchcommission_sum.setG_day_count(new BigDecimal(0));
		sysbranchcommission_sum.setG_day_amount(new BigDecimal(0));
		sysbranchcommission_sum.setG_day_premium(new BigDecimal(0));
		sysbranchcommission_sum.setG_day_fee(new BigDecimal(0));*/
		sysbranchcommission_sum.setG_month_count(new BigDecimal(0));
		sysbranchcommission_sum.setG_month_amount(new BigDecimal(0));
		sysbranchcommission_sum.setG_month_premium(new BigDecimal(0));
		sysbranchcommission_sum.setG_month_fee(new BigDecimal(0));
		sysbranchcommission_sum.setG_year_count(new BigDecimal(0));
		sysbranchcommission_sum.setG_year_amount(new BigDecimal(0));
		sysbranchcommission_sum.setG_year_premium(new BigDecimal(0));
		sysbranchcommission_sum.setG_year_fee(new BigDecimal(0));
		
		/*sysbranchcommission_sum.setU_day_count(new BigDecimal(0));
		sysbranchcommission_sum.setU_day_amount(new BigDecimal(0));
		sysbranchcommission_sum.setU_day_premium(new BigDecimal(0));
		sysbranchcommission_sum.setU_day_fee(new BigDecimal(0));*/
		sysbranchcommission_sum.setU_month_count(new BigDecimal(0));
		sysbranchcommission_sum.setU_month_amount(new BigDecimal(0));
		sysbranchcommission_sum.setU_month_premium(new BigDecimal(0));
		sysbranchcommission_sum.setU_month_fee(new BigDecimal(0));
		sysbranchcommission_sum.setU_year_count(new BigDecimal(0));
		sysbranchcommission_sum.setU_year_amount(new BigDecimal(0));
		sysbranchcommission_sum.setU_year_premium(new BigDecimal(0));
		sysbranchcommission_sum.setU_year_fee(new BigDecimal(0));
		
		sysbranchcommission_sum.setAll_month_count(new BigDecimal(0));
		sysbranchcommission_sum.setAll_month_amount(new BigDecimal(0));
		sysbranchcommission_sum.setAll_month_premium(new BigDecimal(0));
		sysbranchcommission_sum.setAll_month_fee(new BigDecimal(0));	
		sysbranchcommission_sum.setAll_year_count(new BigDecimal(0));
		sysbranchcommission_sum.setAll_year_amount(new BigDecimal(0));
		sysbranchcommission_sum.setAll_year_premium(new BigDecimal(0));
		sysbranchcommission_sum.setAll_year_fee(new BigDecimal(0));
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				Sysbranchcommission sysbranchcommission = list.get(i);
				if((sysbranchcommission.getG_month_count()!=null) &&(!"".equals(sysbranchcommission.getG_month_count()))){
					sysbranchcommission_sum.setG_month_count(sysbranchcommission_sum.getG_month_count().add(sysbranchcommission.getG_month_count()));
				}else{
					sysbranchcommission_sum.setG_month_count(sysbranchcommission_sum.getG_month_count().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getG_month_amount()!=null) &&(!"".equals(sysbranchcommission.getG_month_amount()))){
					sysbranchcommission_sum.setG_month_amount(sysbranchcommission_sum.getG_month_amount().add(sysbranchcommission.getG_month_amount()));
				}else{
					sysbranchcommission_sum.setG_month_amount(sysbranchcommission_sum.getG_month_amount().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getG_month_premium()!=null) &&(!"".equals(sysbranchcommission.getG_month_premium()))){
					sysbranchcommission_sum.setG_month_premium(sysbranchcommission_sum.getG_month_premium().add(sysbranchcommission.getG_month_premium()));
				}else{
					sysbranchcommission_sum.setG_month_premium(sysbranchcommission_sum.getG_month_premium().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getG_month_fee()!=null) &&(!"".equals(sysbranchcommission.getG_month_fee()))){
					sysbranchcommission_sum.setG_month_fee(sysbranchcommission_sum.getG_month_fee().add(sysbranchcommission.getG_month_fee()));
				}else{
					sysbranchcommission_sum.setG_month_fee(sysbranchcommission_sum.getG_month_fee().add(new BigDecimal(0)));
				}		
				if((sysbranchcommission.getG_year_count()!=null) &&(!"".equals(sysbranchcommission.getG_year_count()))){
					sysbranchcommission_sum.setG_year_count(sysbranchcommission_sum.getG_year_count().add(sysbranchcommission.getG_year_count()));
				}else{
					sysbranchcommission_sum.setG_year_count(sysbranchcommission_sum.getG_year_count().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getG_year_amount()!=null) &&(!"".equals(sysbranchcommission.getG_year_amount()))){
					sysbranchcommission_sum.setG_year_amount(sysbranchcommission_sum.getG_year_amount().add(sysbranchcommission.getG_year_amount()));
				}else{
					sysbranchcommission_sum.setG_year_amount(sysbranchcommission_sum.getG_year_amount().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getG_year_premium()!=null) &&(!"".equals(sysbranchcommission.getG_year_premium()))){
					sysbranchcommission_sum.setG_year_premium(sysbranchcommission_sum.getG_year_premium().add(sysbranchcommission.getG_year_premium()));
				}else{
					sysbranchcommission_sum.setG_year_premium(sysbranchcommission_sum.getG_year_premium().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getG_year_fee()!=null) &&(!"".equals(sysbranchcommission.getG_year_fee()))){
					sysbranchcommission_sum.setG_year_fee(sysbranchcommission_sum.getG_year_fee().add(sysbranchcommission.getG_year_fee()));
				}else{
					sysbranchcommission_sum.setG_year_fee(sysbranchcommission_sum.getG_year_fee().add(new BigDecimal(0)));
				}	
				
				if((sysbranchcommission.getU_month_count()!=null) &&(!"".equals(sysbranchcommission.getU_month_count()))){
					sysbranchcommission_sum.setU_month_count(sysbranchcommission_sum.getU_month_count().add(sysbranchcommission.getU_month_count()));
				}else{
					sysbranchcommission_sum.setU_month_count(sysbranchcommission_sum.getU_month_count().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getU_month_amount()!=null) &&(!"".equals(sysbranchcommission.getU_month_amount()))){
					sysbranchcommission_sum.setU_month_amount(sysbranchcommission_sum.getU_month_amount().add(sysbranchcommission.getU_month_amount()));
				}else{
					sysbranchcommission_sum.setU_month_amount(sysbranchcommission_sum.getU_month_amount().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getU_month_premium()!=null) &&(!"".equals(sysbranchcommission.getU_month_premium()))){
					sysbranchcommission_sum.setU_month_premium(sysbranchcommission_sum.getU_month_premium().add(sysbranchcommission.getU_month_premium()));
				}else{
					sysbranchcommission_sum.setU_month_premium(sysbranchcommission_sum.getU_month_premium().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getU_month_fee()!=null) &&(!"".equals(sysbranchcommission.getU_month_fee()))){
					sysbranchcommission_sum.setU_month_fee(sysbranchcommission_sum.getU_month_fee().add(sysbranchcommission.getU_month_fee()));
				}else{
					sysbranchcommission_sum.setU_month_fee(sysbranchcommission_sum.getU_month_fee().add(new BigDecimal(0)));
				}		
				if((sysbranchcommission.getU_year_count()!=null) &&(!"".equals(sysbranchcommission.getU_year_count()))){
					sysbranchcommission_sum.setU_year_count(sysbranchcommission_sum.getU_year_count().add(sysbranchcommission.getU_year_count()));
				}else{
					sysbranchcommission_sum.setU_year_count(sysbranchcommission_sum.getU_year_count().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getU_year_amount()!=null) &&(!"".equals(sysbranchcommission.getU_year_amount()))){
					sysbranchcommission_sum.setU_year_amount(sysbranchcommission_sum.getU_year_amount().add(sysbranchcommission.getU_year_amount()));
				}else{
					sysbranchcommission_sum.setU_year_amount(sysbranchcommission_sum.getU_year_amount().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getU_year_premium()!=null) &&(!"".equals(sysbranchcommission.getU_year_premium()))){
					sysbranchcommission_sum.setU_year_premium(sysbranchcommission_sum.getU_year_premium().add(sysbranchcommission.getU_year_premium()));
				}else{
					sysbranchcommission_sum.setU_year_premium(sysbranchcommission_sum.getU_year_premium().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getU_year_fee()!=null) &&(!"".equals(sysbranchcommission.getU_year_fee()))){
					sysbranchcommission_sum.setU_year_fee(sysbranchcommission_sum.getU_year_fee().add(sysbranchcommission.getU_year_fee()));
				}else{
					sysbranchcommission_sum.setU_year_fee(sysbranchcommission_sum.getU_year_fee().add(new BigDecimal(0)));
				}
					
				if((sysbranchcommission.getAll_month_count()!=null) &&(!"".equals(sysbranchcommission.getAll_month_count()))){
					sysbranchcommission_sum.setAll_month_count(sysbranchcommission_sum.getAll_month_count().add(sysbranchcommission.getAll_month_count()));
				}else{
					sysbranchcommission_sum.setAll_month_count(sysbranchcommission_sum.getAll_month_count().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getAll_month_amount()!=null) &&(!"".equals(sysbranchcommission.getAll_month_amount()))){
					sysbranchcommission_sum.setAll_month_amount(sysbranchcommission_sum.getAll_month_amount().add(sysbranchcommission.getAll_month_amount()));
				}else{
					sysbranchcommission_sum.setAll_month_amount(sysbranchcommission_sum.getAll_month_amount().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getAll_month_premium()!=null) &&(!"".equals(sysbranchcommission.getAll_month_premium()))){
					sysbranchcommission_sum.setAll_month_premium(sysbranchcommission_sum.getAll_month_premium().add(sysbranchcommission.getAll_month_premium()));
				}else{
					sysbranchcommission_sum.setAll_month_premium(sysbranchcommission_sum.getAll_month_premium().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getAll_month_fee()!=null) &&(!"".equals(sysbranchcommission.getAll_month_fee()))){
					sysbranchcommission_sum.setAll_month_fee(sysbranchcommission_sum.getAll_month_fee().add(sysbranchcommission.getAll_month_fee()));
				}else{
					sysbranchcommission_sum.setAll_month_fee(sysbranchcommission_sum.getAll_month_fee().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getAll_year_count()!=null) &&(!"".equals(sysbranchcommission.getAll_year_count()))){
					sysbranchcommission_sum.setAll_year_count(sysbranchcommission_sum.getAll_year_count().add(sysbranchcommission.getAll_year_count()));
				}else{
					sysbranchcommission_sum.setAll_year_count(sysbranchcommission_sum.getAll_year_count().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getAll_year_amount()!=null) &&(!"".equals(sysbranchcommission.getAll_year_amount()))){
					sysbranchcommission_sum.setAll_year_amount(sysbranchcommission_sum.getAll_year_amount().add(sysbranchcommission.getAll_year_amount()));
				}else{
					sysbranchcommission_sum.setAll_year_amount(sysbranchcommission_sum.getAll_year_amount().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getAll_year_premium()!=null) &&(!"".equals(sysbranchcommission.getAll_year_premium()))){
					sysbranchcommission_sum.setAll_year_premium(sysbranchcommission_sum.getAll_year_premium().add(sysbranchcommission.getAll_year_premium()));
				}else{
					sysbranchcommission_sum.setAll_year_premium(sysbranchcommission_sum.getAll_year_premium().add(new BigDecimal(0)));
				}
				if((sysbranchcommission.getAll_year_fee()!=null) &&(!"".equals(sysbranchcommission.getAll_year_fee()))){
					sysbranchcommission_sum.setAll_year_fee(sysbranchcommission_sum.getAll_year_fee().add(sysbranchcommission.getAll_year_fee()));
				}else{
					sysbranchcommission_sum.setAll_year_fee(sysbranchcommission_sum.getAll_year_fee().add(new BigDecimal(0)));
				}
			}
		}
		
		if((sysbranchcommission_sum.getG_month_fee()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_month_fee("+"+sysbranchcommission_sum.getG_month_fee().toString());
		}else{
			sysbranchcommission_sum.setNewg_month_fee(sysbranchcommission_sum.getG_month_fee().toString());
		}
		if((sysbranchcommission_sum.getG_month_premium()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_month_premium("+"+sysbranchcommission_sum.getG_month_premium().toString());
		}else{
			sysbranchcommission_sum.setNewg_month_premium(sysbranchcommission_sum.getG_month_premium().toString());
		}
		if((sysbranchcommission_sum.getG_year_fee()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_year_fee("+"+sysbranchcommission_sum.getG_year_fee().toString());
		}else{
			sysbranchcommission_sum.setNewg_year_fee(sysbranchcommission_sum.getG_year_fee().toString());
		}
		if((sysbranchcommission_sum.getG_year_premium()).compareTo(new BigDecimal(0))==1){
			sysbranchcommission_sum.setNewg_year_premium("+"+sysbranchcommission_sum.getG_year_premium().toString());
		}else{
			sysbranchcommission_sum.setNewg_year_premium(sysbranchcommission_sum.getG_year_premium().toString());
		}
		return sysbranchcommission_sum;
	}
	
}
