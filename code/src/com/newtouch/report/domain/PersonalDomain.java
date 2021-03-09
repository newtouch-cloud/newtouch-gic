package com.newtouch.report.domain;

import java.util.List;
import java.util.Map;

import com.newtouch.report.model.vo.Sysbranchcommission;

public interface PersonalDomain {

	List<Sysbranchcommission> sysbranchcommission(Sysbranchcommission commission);

	List<Sysbranchcommission> citycommission(Sysbranchcommission commission);

	List<Sysbranchcommission> departcommission(Sysbranchcommission commission);

	Sysbranchcommission collectbranch(Sysbranchcommission commission);

	Sysbranchcommission collectcity(Sysbranchcommission commission);

	List<Sysbranchcommission> downloadcitycommission(Sysbranchcommission commission);

	List<Sysbranchcommission> downloadsysbranchcommission(Sysbranchcommission commission);

	List<Sysbranchcommission> downloaddepartcommission(Sysbranchcommission commission);

	List<Sysbranchcommission> sysagencycommission(Sysbranchcommission commission);

	Sysbranchcommission collectagency(Sysbranchcommission commission);

	List<Sysbranchcommission> downloadagencycommission(Sysbranchcommission commission);

	List<Sysbranchcommission> cpybranchcommission(Sysbranchcommission commission);

	Sysbranchcommission collectcpybranch(Sysbranchcommission commission);

	List<Sysbranchcommission> downloadcpybranchcommission(Sysbranchcommission commission);

	Sysbranchcommission collectinsurance(Sysbranchcommission commission);

	List<Sysbranchcommission> insurancecommission(Sysbranchcommission commission);

	List<Sysbranchcommission> downloadinsurancecommission(Sysbranchcommission commission);

	Sysbranchcommission collectinsurance_sum(Sysbranchcommission commission);


}
