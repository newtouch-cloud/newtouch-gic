package com.ca.cacore.ibs.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保单导入
* @since:    2014年11月22日   
* @author    SUNXM
* @description:
 */
public interface ICmainPolicyDomain {
	/**
	 * 校验导入数据是否完全正确
	* 
	* @param dataMap
	* @return List<String>
	* @description:
	 */
	public List<String> checkTraPlanInfoIsTrue(Map<String, List<Object>> dataMap);

	List<String> checkTraPlanInfoBusiness(Map<String, List<Object>> dataMap);
	List<String> checkTraPlanInfoInter(Map<String, List<Object>> dataMap);

	Map<String,List<String>> checkTraPlanInfoIsTrue2(Map<String, List<Object>> dataMap);
	List<String> checkTraPlanInfoIsTruePerson(Map<String, List<Object>> dataMap);

	public HashMap<String, String> findCodeNameToMap(String codeType);

	public HashMap<String, String> findCodeToMap(String codeType);

	public HashMap<String, String> getBusinessMap();

	public HashMap<String, String> getInterMap();

	List<String> checkTraPlanInfoBusinessPerson(Map<String, List<Object>> dataMap);

	List<String> checkTraPlanInfoInterPerson(Map<String, List<Object>> dataMap);

}
