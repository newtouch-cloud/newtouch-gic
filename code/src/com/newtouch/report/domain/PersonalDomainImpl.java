package com.newtouch.report.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.report.dao.PersonalPersonDao;
import com.newtouch.report.dao.PersonalDao;
import com.newtouch.report.model.vo.Sysbranchcommission;


@Service
public class PersonalDomainImpl implements PersonalDomain {

	@Autowired
	private PersonalPersonDao personalPersonDao;
	@Autowired
	private PersonalDao personalDao;
	
	@Override
	public List<Sysbranchcommission> sysbranchcommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			if ("M".equals(commission.getDate_flag())) {
				return personalPersonDao.sysbranch_monthcommission(commission);
			}else {
				return personalPersonDao.sysbranchcommission(commission);
			}
			
		}else{
			if ("M".equals(commission.getDate_flag())) {
				return personalDao.sysbranch_monthcommission(commission);
			}else {
				return personalDao.sysbranchcommission(commission);
			}
		}
	}

	@Override
	public List<Sysbranchcommission> citycommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.citycommission(commission);
		}else{
			return personalDao.citycommission(commission);
		}
	}

	@Override
	public List<Sysbranchcommission> departcommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.departcommission(commission);
		}else{
			return personalDao.departcommission(commission);
		}
		
	}

	@Override
	public Sysbranchcommission collectbranch(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			if ("M".equals(commission.getDate_flag())) {
				return personalPersonDao.collectbranch_month(commission);
			}else {
				return personalPersonDao.collectbranch(commission);
			}
		}else{
			if ("M".equals(commission.getDate_flag())) {
				return personalDao.collectbranch_month(commission);
			}else {
				return personalDao.collectbranch(commission);
			}
			
		}
		
	}

	@Override
	public Sysbranchcommission collectcity(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.collectcity(commission);
		}else{
			return personalDao.collectcity(commission);
		}
		
	}

	@Override
	public List<Sysbranchcommission> downloadcitycommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.downloadcitycommission(commission);
		}else{
			return personalDao.downloadcitycommission(commission);
		}
	}

	@Override
	public List<Sysbranchcommission> downloadsysbranchcommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			if ("M".equals(commission.getDate_flag())) {
				return personalPersonDao.downloadsysbranch_monthcommission(commission);
			}else {
				return personalPersonDao.downloadsysbranchcommission(commission);
			}
		}else{
			if ("M".equals(commission.getDate_flag())) {
				return personalDao.downloadsysbranch_monthcommission(commission);
			}else {
				return personalDao.downloadsysbranchcommission(commission);
			}
		}
	}

	@Override
	public List<Sysbranchcommission> downloaddepartcommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.downloaddepartcommission(commission);
		}else{
			return personalDao.downloaddepartcommission(commission);
		}
	}

	@Override
	public List<Sysbranchcommission> sysagencycommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.sysagencycommission(commission);
		}else{
			return personalDao.sysagencycommission(commission);
		}
	}

	@Override
	public Sysbranchcommission collectagency(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.collectagency(commission);
		}else{
			return personalDao.collectagency(commission);
		}
	}

	@Override
	public List<Sysbranchcommission> downloadagencycommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.downloadagencycommission(commission);
		}else{
			return personalDao.downloadagencycommission(commission);
		}
	}

	@Override
	public List<Sysbranchcommission> cpybranchcommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
				return personalPersonDao.cpybranchcommission(commission);
		}else{
			if ("M".equals(commission.getDate_flag())) {
				return personalDao.cpybranch_monthcommission(commission);
			}else {
				return personalDao.cpybranchcommission(commission);
			}
			
		}
	}

	@Override
	public Sysbranchcommission collectcpybranch(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.collectcpybranch(commission);
		}else{
			if ("M".equals(commission.getDate_flag())) {
				return personalDao.collectcpybranch_month(commission);
			}else {
				return personalDao.collectcpybranch(commission);
			}
		}
	}

	@Override
	public List<Sysbranchcommission> downloadcpybranchcommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.downloadcpybranchcommission(commission);
		}else{
			if ("M".equals(commission.getDate_flag())) {
				return personalDao.downloadcpybranch_monthcommission(commission);
			}else {
				return personalDao.downloadcpybranchcommission(commission);
			}
		}
	}

	
	@Override
	public List<Sysbranchcommission> insurancecommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			
			if ("M".equals(commission.getDate_flag())) {
				return personalPersonDao.insurance_monthcommission(commission);
			}else {
				return personalPersonDao.insurancecommission(commission);
			}
		}else{
			if ("M".equals(commission.getDate_flag())) {
				return personalDao.insurance_monthcommission(commission);
			}else {
				return personalDao.insurancecommission(commission);
			}
		}
	}

	@Override
	public Sysbranchcommission collectinsurance(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			return personalPersonDao.collectinsurance(commission);
		}else{
			return personalDao.collectinsurance(commission);
		}
	}

	@Override
	public List<Sysbranchcommission> downloadinsurancecommission(Sysbranchcommission commission) {
		if ("1".equals(commission.getPerson_flag())) {
			if ("M".equals(commission.getDate_flag())) {
				return personalPersonDao.downloadinsurance_monthcommission(commission);
			}else {
				return personalPersonDao.downloadinsurancecommission(commission);
			}
		}else{
			if ("M".equals(commission.getDate_flag())) {
				return personalDao.downloadinsurance_monthcommission(commission);
			}else {
				return personalDao.downloadinsurancecommission(commission);
			}
		}
	}

	@Override
	public Sysbranchcommission collectinsurance_sum(
			Sysbranchcommission commission) {
		// TODO Auto-generated method stub
		if ("1".equals(commission.getPerson_flag())) {
			
			if ("M".equals(commission.getDate_flag())) {
				return null;
			}else {
				return personalPersonDao.collectinsurance_sum(commission);
			}
		}else{
			if ("M".equals(commission.getDate_flag())) {
				return null;
			}else {
				return personalDao.collectinsurance_sum(commission);
			}
		}
	}
	
	
}
