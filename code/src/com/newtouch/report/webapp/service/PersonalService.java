package com.newtouch.report.webapp.service;

import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.report.model.vo.Sysbranchcommission;

public interface PersonalService {

	ReturnMsg sysbranchcommission(Sysbranchcommission commission);

	ReturnMsg citycommission(Sysbranchcommission commission);

	ReturnMsg departcommission(Sysbranchcommission commission);

	ReturnMsg downloadcitycommission(Sysbranchcommission commission);

	ReturnMsg downloadsysbranchcommission(Sysbranchcommission commission);

	ReturnMsg downloaddepartcommission(Sysbranchcommission commission);

	ReturnMsg sysagencycommission(Sysbranchcommission commission);

	ReturnMsg downloadagencycommission(Sysbranchcommission commission);

	ReturnMsg cpybranchcommission(Sysbranchcommission commission);

	ReturnMsg downloadcpybranchcommission(Sysbranchcommission commission);

	ReturnMsg insurancecommission(Sysbranchcommission commission);

	ReturnMsg downloadinsurancecommission(Sysbranchcommission commission);


}
