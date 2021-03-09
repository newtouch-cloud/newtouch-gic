package com.ca.cacore.common;

public class CodeTypeConst {

	// 人员职级序列RankSeries_Code
	/**
	 * 销售人员职级序列
	 * CODE_TYPE_RANKSERIES_SALES= "sales"
	 */
	public static final String CODE_TYPE_RANKSERIES_SALES = "sales"; // 销售人员
	/**
	 * 管理人员职级序列
	 * CODE_TYPE_RANKSERIES_MANAGER="manager"
	 */
	public static final String CODE_TYPE_RANKSERIES_MANAGER = "manager"; // 管理人员

	
	// 人员职位代码
	public static final String CODE_TYPE_POST_CODE_SALES = "sales"; // 销售人员
	public static final String CODE_TYPE_POST_CODE_ZULEADER = "zuleader"; // 管理人员
	public static final String CODE_TYPE_POST_CODE_QULEADER = "quleader"; // 管理人员
	
	//新增(职位代码)：暂定，可能要做修改
	public static final String CODE_TYPE_POST_CODE_LEADER = "leader"; // 团队主管
	public static final String CODE_TYPE_POST_CODE_WDSALES = "wdsales"; // 网电销人员
	public static final String CODE_TYPE_POST_CODE_MANAGER="manager";//管理人员

	// 渠道组织级别信息
	public static final String CODE_TYPE_TEAM_LVL_Q = "Q";// Q 区
	public static final String CODE_TYPE_TEAM_LVL_Z = "Z";// Z 组

	//销售人员状态
	/*0	待入司
	1	在职
	2	离司
	4	预离司
	5	冻结
	6	考核保护
	7         审批不通过*/
	public static final String SALES_STATUS_WAIT = "0";// 人员状态——待入司
	public static final String SALES_STATUS_NORMAL = "1";// 人员状态——在职
	public static final String SALES_STATUS_DIMISSION = "2";// 人员状态——解约(离职)
	public static final String SALES_STATUS_PREDISMISSION = "4";// 人员状态——预解约
	public static final String SALES_STATUS_FROZEN = "5";// 人员状态——冻结
	public static final String SALES_STATUS_PROTECT = "6";// 人员状态——考核保护
	public static final String SALES_STATUS_APPRNOPASS="7";//人员状态--审批不通过
	
	/*审批状态数据字典
	0	待审批
	1	审批不通过
	9	审批通过
	4	已撤销*/
	public static final String CODE_TYPE_APPR_STATUS_WAIT="0";//人员状态--待审批
	public static final String CODE_TYPE_APPR_STATUS_NOPASS="1";//人员状态--审批不通过
	public static final String CODE_TYPE_APPR_STATUS_PASS="9";//人员状态--审批通过
	public static final String CODE_TYPE_APPR_STATUS_REVOKE="4";//人员状态--已撤销
	
	/*销售人员关系类型
	1	推荐关系
	2	育成关系
	3	管理关系*/
	public static final String CODE_TYPE_SALES_RELATION_RECOM="1";//推荐关系
	public static final String CODE_TYPE_SALES_RELATION_TRAIN="2";//育成关系
	public static final String CODE_TYPE_SALES_RELATION_CONTR="3";//管理关系
	/*组织状态
	1	有效
	0       无效*/
	public static final String TEAM_STATUS_CODE0="0";
	public static final String TEAM_STATUS_CODE1="1";
	
	/*是否是直辖组
	 * Y 是
	 * N 否
	 */
	public static final String CODE_TYPE_IS_DIRECTLY_Y="Y";
	public static final String CODE_TYPE_IS_DIRECTLY_N="N";
	
	/*审批业务对象类型
	 * sales	销售人员
	 * RecruitBatch	招募批次
	 * TrainingProjectApp	培训立项
	 * TrainingCourse	培训班课程
	 * LawContract  法律合同
	 * Announcement 公告
	 */
	public static final String CODE_TYPE_BUSINESS_TYPE_SALES="sales";
	public static final String CODE_TYPE_BUSINESS_TYPE_RECRUITBATCH="RecruitBatch";
	public static final String CODE_TYPE_BUSINESS_TYPE_TRAININGPROJECTAPP="TrainingProjectApp";
	public static final String CODE_TYPE_BUSINESS_TYPE_TRAININGCOURSE="TrainingCourse";
	public static final String CODE_TYPE_BUSINESS_TYPE_LAWCONTRACT="LawContract";
	public static final String CODE_TYPE_BUSINESS_TYPE_ANNOUNCEMENT="Announcement";
	
	/*审批事项数据字典
	 01	销售人员入司审批
	 02	销售人员解约审批
	 03	销售人员解约恢复审批
	 04	招募批次审批
     05	培训立项审批
     06	培训班课程审批
     07 人员合同订立审批
     08   公告审批
	 */
	public static final String APPROVAL_ITEM_SALES_ENTER="01";
	public static final String APPROVAL_ITEM_SALES_DISS="02";
	public static final String APPROVAL_ITEM_SALES_DISSBACK="03";
	public static final String APPROVAL_ITEM_RECRUIT_BATCH="04";
	public static final String APPROVAL_ITEM_TRAINING_PROJECTAPP="05";
	public static final String APPROVAL_ITEM_TRAINING_COURSE="06";
	public static final String APPROVAL_ITEM_ANNOUNCEMENT="07";
	public static final String APPROVAL_ITEM_LAW_CONSTRACT="08";

	

	
	/*系统是否已生效
	 * Y 是
	 * N 否
	 */
	public static final String CODE_TYPE_PROCESS_COMPLETED_Y="Y";
	public static final String CODE_TYPE_PROCESS_COMPLETED_N="N";
	
	/* 组织状态是否有效
	 * 0	失效
	 * 1	有效
	 */
	public static final String CODE_TYPE_TEAM_STATUS_N="0";
	public static final String CODE_TYPE_TEAM_STATUS_Y="1";
	
	//产品模块
	/*产品缴费方式：
	 * 1	年交
	 * 2	半年交
	 * 3	季交
	 * 4	月交
	 * 5	趸交
	 */
	public static final String PRODUCT_CHANRGE_TYPE_YEAR = "1";
	public static final String PRODUCT_CHANRGE_TYPE_HALFYEAR = "2";
	public static final String PRODUCT_CHANRGE_TYPE_SEASON = "3";
	public static final String PRODUCT_CHANRGE_TYPE_MONTH = "4";
	public static final String PRODUCT_CHANRGE_TYPE_SINGLE = "5";
	
	//保单模块
	/*回访成功是否
	 * N：未成功
	 * Y：成功
	 */
	public static final String CONTRACTLIFE_IS_ANSWER_Y="Y";
	public static final String CONTRACTLIFE_IS_ANSWER_N="N";
	
	/*销售方式
	 * 0：一般方式
	 * 32：个险
	 */
	public static final String POLICYLIFE_SELL_WAY_COMMON="0";
	public static final String POLICYLIFE_SELL_WAY_PERSONAL="32";
	
	/*被保成员数据字典
	 * 0	无关或不确定
	 * 1	配偶
	 * 2	子女
	 * 3	父母
	 * 4	亲属
	 * 5	本人
	 * 6	其它
	 * 7	雇佣关系
	 * 8	金融机构
	 */
	public static final String POLICYLIFE_APPLICANT_RELATION2_NO="0";
	public static final String POLICYLIFE_APPLICANT_RELATION2_MATE="1";
	public static final String POLICYLIFE_APPLICANT_RELATION2_CHILDREN="2";
	public static final String POLICYLIFE_APPLICANT_RELATION2_PARENT="3";
	public static final String POLICYLIFE_APPLICANT_RELATION2_HINSHIP="4";
	public static final String POLICYLIFE_APPLICANT_RELATION2_MYSELF="5";
	public static final String POLICYLIFE_APPLICANT_RELATION2_OTHER="6";
	public static final String POLICYLIFE_APPLICANT_RELATION2_EMPLOYEE="7";
	public static final String POLICYLIFE_APPLICANT_RELATION2_FINANCIAL="8";
	
	/*默认初值：
	 * 保单年度为1
	 * 缴费期次 1
	 * insure_stop险种是否终止：N
	 * 是否生成记账：N
	 */
	public static final Integer POLICYLIFE_YEAR=1;
	public static final Integer POLICYLIFE_PERIOD=1;
	public static final Integer POLICYLIFE_PRODUCT_UNIT=1;
	public static final String POLICYLF_PRODUCT_INSURE_STOP="N";
	public static final String POLICYLF_PRODUCT_POSTEDN="N";
	
	/*投保单产品（险种）状态：
	 * 3	撤件
	 * 5	拒保
	 * 6	首期待承保
	 * 7	承保生效
	 */
	public static final String PRODUCT_STATUS_FIRSTCOVERED="6";
	
	/*费用处理状态
	 * 0	待处理
	 * 2	取消
	 * 6	费用已付
	 */
	public static final String FEE_STATUS_WAIT="0";
	public static final String FEE_STATUS_CHARGED="6";
	
	/* 费用类型
	 * 41:首期保费收入
	 * 43：续期保费收入
	 */
	public static final String FEE_TYPE_FIRST="41";
	public static final String FEE_TYPE_NEXT="43";
	
	/*投保单状态
	 * 6        待核保
	 * 14	首期待承保
	 * 18	承保前撤件
	 * 19	拒保
	 * 21	强撤
	 * 22	待承保前撤件
	 * 53	保单进入正常有效状态
	 * 200	无效投保单
	 */
	public static final String POLICYLIFE_STATUS_WAITCHECK="6";
	public static final String POLICYLIFE_STATUS_FIRSTCOVERED="14";
	public static final String POLICYLIFE_STATUS_CONTRACTLIFE_EFFECTIVE="53";
	public static final String POLICYLIFE_STATUS_COVERED_BACK="22";
	public static final String POLICYLIFE_STATUS_CONTRACTLIFE_BACK="18";
	public static final String POLICYLIFE_STATUS_INVILAD="200";
	
	/*保单影像上传类型
	 * 1       投保单影像件
	 * 2	保单影像件
	 */
	public static final String POLICY_IMAGE_BUS_TYPE="1";
	public static final String CONTRACT_IMAGE_BUS_TYPE="2";
	
	/*
	 * 备份业务类型
	 */
	public static final String LOG_BUSTYPE="01";
	
	/*影像业务类型
	 * 1 投保单影像件
	 * 2 保单影像件
	 * 3 问题单影像件
	 * 4 保单回执影像件
	 */
	public static final String IMAGE_BUSTYPE_POLICYLIFE="1";
	public static final String IMAGE_BUSTYPE_CONTRACTLIFE="2";
	public static final String IMAGE_BUSTYPE_ACK="4";
	public static final String POLICY_PROBLEM_IMAGE_BUS_TYPE="3";
	public static final String CONSER_PROBLEM_IMAGE_BUS_TYPE="5";
	public static final String CLAIM_PROBLEM_IMAGE_BUS_TYPE="6";
	/*
	 * 投保单相关的识别码
	 * 生成保单id和客户id
	 */
	public static final String POLICYLIFE_SERIES_CODE="20";
	
	/*保单状态
	 * 0	           已承保，待客户回执
	 * 1              有效
	 * 2              失效
	 * 3              终止
	 */
	public static final String CONTRACTLIFE_STATUS_EFFECTIVE="1";
	public static final String CONTRACTLIFE_STATUS_WAITACK="0";
	public static final String CONTRACTLIFE_STATUS_INVILAD="2";
	public static final String CONTRACTLIFE_STATUS_STOP="3";
	

	//客户相关：
	/*客户代码生成的标识符
	 * 
	 */
	public static final String POLICYLIFE_CUSTOMER_IDENTIFIER ="04";
	
	/*客户类型
	 * 1 准客户 
	 * 2 客户
	 */
	public static final String CUSTOMER_TYPE_INSURED ="1";
	public static final String CUSTOMER_TYPE_GENERAL="2";
	
	/*客户状态
	 * 1:有效
	 */
	public static final String CUSTOMER_STATUS_EFFECTIVE="1";
	
	/*机构状态
	 * 1:有效
	 * 0:无效
	 */
	public static final String BRANCH_STATUS1="1";
	public static final String BRANCH_STATUS0="0";
	
	/*机构级别
	 * 4 4级机构
	 */
	public static final String BRANCH_LEVEL4="4";
	
	
	//招募模块
	
	//招募批次状态
	/*0	待启动
	1	进行中
	2	终止
	9	审批未通过*/
	public static final String RECRUITBATCH_STATUS_WAIT = "0";// 招募批次状态--待启动
	public static final String RECRUITBATCH_STATUS_NORMAL = "1";// 招募批次状态--进行中
	public static final String RECRUITBATCH_STATUS_TERMINATION = "2";// 招募批次状态--终止
	public static final String RECRUITBATCH_STATUS_APPRNOPASS="9";//招募批次状态--审批未通过
	
	//招募类型信息
	/*
	 *  0	幼狮计划
		1	高手引进
		2	经理引进
		3	区域总监引进
		4	有工作经验“白板”
	 */
	public static final String YOUNG_LIONS_TYPE = "0";// 招募类型信息--幼狮计划
	public static final String MASTER_TYPE = "1";// 招募类型信息--高手引进
	public static final String MANAGER_TYPE = "2";// 招募类型信息--经理引进
	public static final String REGIONAL_DIRECTOR_TYPE = "3";// 招募类型信息--区域总监引进
	public static final String WHITEBOARD_TYPE = "4";// 招募类型信息--有工作经验“白板”
	
	//招募流程状态信息
	/*
	 *  人员基本信息录入0                            
		参加创说会1		
		不参加创说会2		
		人员简历信息录入完成13                              
		参加初次面试14                              
		初次面试通过15                              
		初次面试不通过16                              
		参加深度面试7                              
		参加初次面试24                              
		初次面试通过25                              
		初次面试不通过26                              
		人员简历信息录入完成23                              
		深度面试通过8		
		深度面试不通过9		
		参加确认面试10		
		确认面试通过11		
		确认面试不通过12		
		确认参加培训13		
		确认不参加培训14
	 */
	public static final String RECRUITPERSON_BASIC = "0";// 招募流程状态信息--人员基本信息录入完成
	public static final String RECRUITPERSON_JOIN_CONFERENCE = "1";// 招募流程状态信息--参加创说会
	public static final String RECRUITPERSON_NOJOIN_CONFERENCE = "2";// 招募流程状态信息--不参加创说会
	public static final String RECRUITPERSON_RESUME = "13";// 招募流程状态信息--人员简历信息录入完成
	public static final String RECRUITPERSON_JOIN_INITIAL_INTERVIEW = "14";// 招募流程状态信息--已参加初次面试
	public static final String RECRUITPERSON_JOIN_INITIAL_PASSED = "15";// 招募流程状态信息--初次面试通过
	public static final String RECRUITPERSON_JOIN_INITIAL_UNPASSED = "16";// 招募流程状态信息--初次面试不通过
	public static final String RECRUITPERSON_RESUME2 = "23";// 招募流程状态信息--人员简历信息录入完成
	public static final String RECRUITPERSON_JOIN_INITIAL_INTERVIEW2 = "24";// 招募流程状态信息--已参加初次面试
	public static final String RECRUITPERSON_JOIN_INITIAL_PASSED2 = "25";// 招募流程状态信息--初次面试通过
	public static final String RECRUITPERSON_JOIN_INITIAL_UNPASSED2 = "26";// 招募流程状态信息--初次面试不通过
	public static final String RECRUITPERSON_JOIN_DEEP_INTERVIEW = "7";// 招募流程状态信息--参加深度面试
	public static final String RECRUITPERSON_JOIN_DEEP_PASSED = "8";// 招募流程状态信息--深度面试通过
	public static final String RECRUITPERSON_JOIN_DEEP_UNPASSED = "9";// 招募流程状态信息--深度面试不通过
	public static final String RECRUITPERSON_JOIN_FINAL_INTERVIEW = "10";// 招募流程状态信息--参加确认面试
	public static final String RECRUITPERSON_JOIN_FINAL_PASSED = "11";// 招募流程状态信息--确认面试通过
	public static final String RECRUITPERSON_JOIN_FINAL_UNPASSED = "12";// 招募流程状态信息--确认面试不通过
	public static final String RECRUITPERSON_JOIN_TRAINING = "17";// 招募流程状态信息--确认参加培训
	public static final String RECRUITPERSON_NOJOIN_TRAINING = "18";// 招募流程状态信息--确认不参加培训
	
	//面试评估类型数据字典
	/*0	单选框类型
	  1	文本输入域类型*/
	public static final String RADIO_TYPE = "0";// 面试评估类型--单选框类型
	public static final String TEXTAREA_TYPE = "1";// 面试评估类型--文本输入域类型
	
	//面试评估项目信息
	/*
	 *  0 	求职动机
		1 	工作能力
		2 	销售认知
		3 	管理能力
		4 	市场开拓能力
		5 	综合评价
		6 	沟通能力	
		7 	企图心、进取精神	
		8 	销售潜质	
		9 	抗压能力	
		10 	团队协作能力	
		11	学习能力	
		12 	针对初试结果进行提问	
		13	个性匹配	
		14	工作规划	
		15	目标管理能力	
		16	开拓能力
		17 	管理勇气
		18 	自我评价
		19	结语问题
	 */
	public static final String JOB_MOTIVATION = "0";// 面试评估项目信息--求职动机
	public static final String WORK_ABILITY = "1";// 面试评估项目信息--工作能力
	public static final String SALES_GRASP = "2";// 面试评估项目信息--销售认知
	public static final String MANAGEMENT_ABILITY = "3";// 面试评估项目信息--管理能力
	public static final String MARKETING_ABILITY = "4";// 面试评估项目信息--市场开拓能力
	public static final String COMPLEX_EVALUATION = "5";// 面试评估项目信息--综合评价
	public static final String COMMUNICATION_SKILLS = "6";// 面试评估项目信息--沟通能力
	public static final String AMBITION_SPIRIT = "7";// 面试评估项目信息--企图心、进取精神
	public static final String SALES_POTENTIAL = "8";// 面试评估项目信息--销售潜质
	public static final String COMPRESSIVE_STRENGTH = "9";// 面试评估项目信息--抗压能力
	public static final String TEAM_WORK = "10";// 面试评估项目信息--团队协作能力
	public static final String LEARNING_ABILITY = "11";// 面试评估项目信息--学习能力
	public static final String INITIAL_INTERVIEW_QUESTIONS = "12";// 面试评估项目信息--针对初试结果进行提问
	public static final String PERSONALIZED_MATCH = "13";// 面试评估项目信息--个性匹配
	public static final String WORK_PLAN = "14";// 面试评估项目信息--工作规划
	public static final String TARGET_MANAGEMENT = "15";// 面试评估项目信息--目标管理能力
	public static final String DEVELOPMENT_ABILITY = "16";// 面试评估项目信息--开拓能力
	public static final String MANAGEMENT_COURAGE = "17";// 面试评估项目信息--管理勇气
	public static final String SELF_EVALUATION = "18";// 面试评估项目信息--自我评价
	public static final String CONCLUSION_QUESTION = "19";// 面试评估项目信息--结语问题
	
	//面试是否通过信息
	/*Y	通过
	  N	不通过*/
	public static final String INTERVIEW_PASSED = "Y";// 面试是否通过信息--通过
	public static final String INTERVIEW_UNPASSED = "N";// 面试是否通过信息--不通过
	
		
	//渠道代码
	public static final String CODE_TYPE_CHANNEL_ID = "01"; // 个险渠道

	/*主页面main三个功能区的位置代码
	 *0: 右上方区域
	 *1：左下方左侧区域
	 *2：右下方右侧区域
	 */
	public static final int rightTop=0;
	public static final int rightBottomleft=1;
	public static final int rightBottomright=2;

	//培训---------------------------------------------------------------------start
	/*培训立项状态信息
	0	待启动
	1	有效
	2	无效
	3	终止
	9	审批未通过*/
	public static final String TRAININGPROJECT_STATUSCODE0 = "0"; // 培训立项待启动
	public static final String TRAININGPROJECT_STATUSCODE2 = "2"; // 培训立项无效
	public static final String TRAININGPROJECT_STATUSCODE1 = "1"; //培训立项有效
	public static final String TRAININGPROJECT_STATUSCODE9 = "9";  //培训立项审批未通过
	public static final String TRAININGPROJECT_STATUSCODE3 = "3";//培训立项审批	终止
	/*培训类型数据字典
	0 	销售类
	1 	管理类
	2 	讲师类
	3 	后援管理类
	4 	其它类*/
	public static final String TRAINING_TYPE0 = "0"; // 销售类
	public static final String TRAINING_TYPE1 = "1"; // 	管理类
	public static final String TRAINING_TYPE2 = "2"; // 讲师类
	public static final String TRAINING_TYPE3 = "3"; // 后援管理类
	public static final String TRAINING_TYPE4 = "4"; // 其它类
	
	/*
	 * 培训项目
	 * 0 新人入职培训 
	 */
	public static final String TRAINING_ITEM_NEW_PERSON = "0"; // 新人入职培训
	
/*	培训期数信息
	1	一期
	2	二期
	3	三期
	4	四期
	5	五期*/
	public static final String plan_periods1 = "1"; 
	public static final String plan_periods2 = "2"; 
	public static final String plan_periods3 = "3"; 
	public static final String plan_periods4 = "4"; 
	public static final String plan_periods5 = "5"; 
	/*
	 * 培训制式类型
	 * 0 	制式
	 * 1 	非制式
	 */
	public static final String TRAINING_FORMAT_CODE0 = "0"; 
	public static final String TRAINING_FORMAT_CODE1 = "1"; 
	
/*	培训方式信息
	0	在线培训
	1	面授培训*/
	public static final String TRAINING_MODE0 = "0"; 
	public static final String TRAINING_MODE1 = "1"; 
	
	/*
	 * 课程状态：
	 * 0              待审批
	 * 1              有效
	 * 2              无效
	 * 3              审批未通过
	 */
	public static final String TRAININGCOURSE_STATUS_EFFECTIVE = "1"; 
	public static final String TRAININGCOURSE_STATUS_NOPASS = "3"; 
	public static final String TRAININGCOURSE_STATUS_WAIT = "0"; 
	
	/*
	 * 培训班的状态：
	 * 0              有效              可以配置课程，录入学员
	 * 1              无效              培训班不能使用
	 * 2              完结              培训班生命周期结束
	 */
	public static final String TRAININGCLASS_STATUS_EFFECTIVE = "0"; 
	/*
	 * 培训班学员状态
	 * 0              培训中
	 * 1              未结训
	 * 2              已结训
	 */
	public static final String CLASS_STUDNET_STATUS_ING = "0"; 	
	public static final String CLASS_STUDNET_STATUS_NOT = "1"; 	
	public static final String CLASS_STUDNET_STATUS_PASS = "2"; 
	
	public static final String CLASS_STUDNET_STATUS_N = "未结训"; 	
	public static final String CLASS_STUDNET_STATUS_P = "结训"; 
	
	/**
	 * 是否自动添加课程
	 */
	public static final String CLASS_IS_CONFIGURED_COURSE_Y = "Y"; //是 
	
	/**
	 * 附件上传分类   SYS_ATTACHMENT_BUSTYPE
	 * 附件上传类型数据字典
	 * 	1 	投保单影像件
		2	保单影像件
		3 	问题单影像件
		4	保单回执影像件
		5 	招募人员简历附件
		6	培训计划附件
		7	培训立项附件
		8	培训讲师简历附件
		9	培训课程附件
		10	培训班实际经费明细附件
		11	培训学员考试信息附件
		12	培训学员出勤附件
		13	风险事件附件
		14	提示函附件
		15	法律合同附件
		16	诉讼案件附件
		17    规章制度附件
		20    公告附件
		21   保险公司协议附件
		22   销售人员合同附件
		23   代理人协议附件
		30    客户价值分析附件
		40    票据附件
	 */
	public static final String ATTACHMENT_BUSTYPE_PERSONRESUME = "5";
	public static final String ATTACHMENT_BUSTYPE_TRAININGPROJECT = "7";
	public static final String ATTACHMENT_BUSTYPE_TRAININGTEACHER = "8";
	public static final String ATTACHMENT_BUSTYPE_TRAININGCOURSE = "9";
	public static final String ATTACHMENT_BUSTYPE_TRAININGCLASS = "10";
	public static final String ATTACHMENT_BUSTYPE_TRAININGATTEND = "12";
	public static final String ATTACHMENT_BUSTYPE_RISK="13";
	public static final String ATTACHMENT_BUSTYPE_WARNINGLETTER = "14";
	public static final String ATTACHMENT_BUSTYPE_LITIGATION="16";
	public static final String ATTACHMENT_BUSTYPE_REGULATION = "17";
	public static final String ATTACHMENT_BUSTYPE_LEGALCONTRACT="15";
	public static final String ATTACHMENT_BUSTYPE_ANNOUNCEMENT = "20";
	public static final String ATTACHMENT_BUSTYPE_PROTOCOL = "21";
	public static final String ATTACHMENT_BUSTYPE_SALESCONTRACT= "22";
	public static final String ATTACHMENT_BUSTYPE_SALESPROTOCOL= "23";
	public static final String ATTACHMENT_BUSTYPE_POUNDAGE= "24";
	public static final String ATTACHMENT_BUSTYPE_CRMFENXI = "30";
	public static final String ATTACHMENT_BUSTYPE_RECEIPT = "40";
	
	/**
	 * 附件上传页面设置 写死
	 * 	1	
	 */
	public static final String ATTACHMENT_SEQ_NUM = "12";
	
	/**
	 * 投诉分类   CIS_Complaint_Type
	 * 投诉分类数据字典
	 * 	0	保险公司
	 * 	1	产品
	 * 	2	代理人
	 */
	public static final String COMPLAINT_TYPE_INSBRANCH = "0";//保险公司
	public static final String COMPLAINT_TYPE_PRODUCT = "1";//产品
	public static final String COMPLAINT_TYPE_CHANNEL = "2";//代理人
	
	/**
	 * 诉讼案件类型   Litigation_Type_Code
	 * 诉讼案件类型数据字典
	 * 0	类型一
	 * 1	类型二
	 * 2	类型三
	 */
	public static final String LITIGATION_TYPE_CODE="0";//诉讼案件类型
	/**
	 * 投诉处理状态
	 */
	public static final String COMPLAINT_HANDLE_STATUS_CODE="2";//投诉-处理状态
	/**
	 * 产品主附险标志   
	 * 主附险标志   数据字典
	 * 	1	主险
	 * 	2	附加险
	 */
	public static final String INS_TYPE_CODE1 = "1";//主险
	public static final String INS_TYPE_CODE2 = "2";//附加险
	
	/**
	 * 产品
	 * 产品状态   数据字典
	 * 	1	有效
	 * 	0	无效
	 */
	public static final String PRO_STATUS_CODE1 = "1";//有效
	public static final String PRO_STATUS_CODE0 = "0";//无效
	/**
	 * 产品
	 * 产品分类   数据字典
	 * 	1	产品分类1
	 * 	2	产品分类2
	 *  3       产品分类3
	 */
	public static final String PRO_TYPE_CODE1 = "1";//产品分类1
	public static final String PRO_TYPE_CODE2 = "2";//产品分类2
	public static final String PRO_TYPE_CODE3 = "3";//产品分类3

	/*培训立项状态信息
	0	待启动
	1	有效
	2	无效
	3	终止
	9	审批未通过*/
	
	/**
	 * 二期附件上传状态
	 * 	上传失败
	 * 	上传成功
	 * 	未上传文件
	 *  设置错误
	 */
	
	public static final String ATTACHMENT_UPLOAD_FAILURE = "附件上传失败";//上传失败
	public static final String ATTACHMENT_UPLOAD_SUCCESS = "上传成功";//上传成功
	public static final String ATTACHMENT_UPLOAD_NULL = "未上传文件";	//未上传文件
	public static final String ATTACHMENT_UPLOAD_ERROR = "附件上传失败，Form表单未设置enctype=‘multipart/form-data’属性。";	//设置错误
	public static final String ATTACHMENT_UPLOAD_TOBIG = "最大附件10M";	//最大附件10M
	/**
	 * 项目附件最大上传大小 2M
	 */
	//公告------------------------------------------------start
	
	public static final int ATTACHMENT_UPLOAD_MAXSIZE = 10485760;//最大大小10M
	
	/**
	 * 客户状态常量 
	 * 1 有效
	 * 0 失效
	 */
	public static final String CUSTOMER_STATUS_VALID = "1";//有效
	public static final String CUSTOMER_STATUS_NULLITY = "0";//失效
	
	/**
	 * 客户备份业务类型 
	 * 1  写死
	 */
	public static final String CUSTOMER_LOG_BUSTYPE = "1";
	
	/**
	 * 合同类型
	 * 1     劳动制合同
	 * 2     代理制合同
	 */
	public static final String CONTRACT_TYPE_LAO="1";
	public static final String CONTRACT_TYPE_DAI="2";
	
	/**
	 * 合同状态：
	 * 1    有效
	 * 0    无效
	 */
	public static final String CONTRACT_STATUS_Y = "1";//有效
	public static final String CONTRACT_STATUS_N = "0";//失效
	
	/**
	 * 用户是否包含下级机构
	 * 0 不包含(默认)
	 * 1 包含
	 */
	public static final String BRANCH_IS4SUB_YES = "1";
	public static final String BRANCH_IS4SUB_NO = "0";
	
}
