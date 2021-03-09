package com.ca.cacore.ams.webapp.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.domain.IUserMgMtDomain;
import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.ca.cacore.ams.model.vo.UserMgMtVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;

@Service
public class StatisticalDataService implements IStatisticalDataService {
	
	@Autowired private IUserMgMtDomain userMgmtDomain;
	
	//静态方法-解决中文乱码
	static {
		StandardChartTheme theme = new StandardChartTheme("unicode") {
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		theme.setExtraLargeFont(new Font("ו", Font.PLAIN, 20));
		theme.setLargeFont(new Font("ו", Font.PLAIN, 14));
		theme.setRegularFont(new Font("ו", Font.PLAIN, 12));
		theme.setSmallFont(new Font("ו", Font.PLAIN, 10));
		ChartFactory.setChartTheme(theme);
	}
	
	/** 
	* 
	* @param ops
	* @param user
	* @throws IOException 
	* @description:统计数据-年度
	*/
	@Override
	public void statisticalDataYear(ServletOutputStream ops,IUserModel user) throws IOException {
		  String emp_id=user.getEmp_id();//登录用户代码
		  IUserMgMtVOModel model=new UserMgMtVOModel();
          model.setOpt_no(emp_id);
          IUserMgMtVOModel userInfoModel=userMgmtDomain.queryUserInfo(model);//查询登录用户信息
          String person_type=userInfoModel.getPerson_type();//登录用户类型
          JFreeChart createChart=null;
          //登录用户为销售员或代理人
          if("xsy".equals(person_type) || "dlr".equals(person_type)){
         	     createChart = createChartYearSalesOrProtocol(userInfoModel);//生成统计图形的方法
         }else{//登录用户为系统录入的用户
        	     createChart=createChartYearUser(userInfoModel);//生成统计图形的方法
         }
		  BufferedImage bi = createChart.createBufferedImage(300, 350);//250, 300
		  ImageIO.write(bi, "PNG", ops);
	}
	
	 /** 
	* 
	* @param model
	* @return JFreeChart
	* @description:统计数据-年度-1.销售员或代理人的统计数据------------旧方法
	*/
	private  JFreeChart createChartYearSalesOrProtocolOld(IUserMgMtVOModel model)
	  {
		 //查询相关数据信息
		 IUserMgMtVOModel countmodel=userMgmtDomain.statisticalDataYearSalesOrProtocolYear(model);
		 Date date=new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		 String year=sdf.format(date);
		 
  		    DefaultCategoryDataset dcd=new DefaultCategoryDataset();
			dcd.setValue(countmodel.getPolicyno_count(), year, "保单数量");
			dcd.setValue(countmodel.getNetpremium_count(), year, "保费金额");
			dcd.setValue(countmodel.getFnum_count(), year, "手续费金额");
			
			JFreeChart chart=ChartFactory.createBarChart("年度", "", "", dcd, PlotOrientation.VERTICAL, true, true, false);
			CategoryPlot plot=chart.getCategoryPlot();
			CategoryAxis cAxis=plot.getDomainAxis();
			cAxis.setLabelFont(new Font("黑体",Font.BOLD,20));
			cAxis.setTickLabelFont(new Font("黑体",Font.BOLD,15));
			cAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			
			NumberAxis nAxis=(NumberAxis) plot.getRangeAxis();
			nAxis.setLabelFont(new Font("黑体",Font.BOLD+Font.ITALIC,20));
			nAxis.setTickLabelFont(new Font("黑体",Font.BOLD+Font.ITALIC,10));
			
			CategoryPlot localCategoryPlot = (CategoryPlot)chart.getPlot();
		    StatisticalBarRenderer localStatisticalBarRenderer = new StatisticalBarRenderer();
		    localStatisticalBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		    localStatisticalBarRenderer.setBaseItemLabelsVisible(true);
		    localStatisticalBarRenderer.setBaseItemLabelPaint(Color.yellow);
		    localStatisticalBarRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE6, TextAnchor.BOTTOM_CENTER));
		    localCategoryPlot.setRenderer(localStatisticalBarRenderer);
			return chart;
	  }
	
	/** 
	* 
	* @param model
	* @return JFreeChart
	* @description:统计数据-年度-1.销售员或代理人的统计数据
	*/
	private  JFreeChart createChartYearSalesOrProtocol(IUserMgMtVOModel model)
	  {
		 //查询相关数据信息
		 IUserMgMtVOModel countmodel=userMgmtDomain.statisticalDataYearSalesOrProtocolYear(model);
		 Date date=new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		 String year=sdf.format(date);
		 
		    String str1 = "First";
		    String str4 = "保单数量";
		    String str5 = "保费金额";
		    String str6 = "手续费金额";
		    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		    localDefaultCategoryDataset.addValue(countmodel.getPolicyno_count(), str1, str4);
		    localDefaultCategoryDataset.addValue(countmodel.getNetpremium_count(), str1, str5);
		    localDefaultCategoryDataset.addValue(countmodel.getFnum_count(), str1, str6);
		    
		    JFreeChart localJFreeChart = ChartFactory.createBarChart("年度", year, "", localDefaultCategoryDataset, PlotOrientation.VERTICAL, false, true, false);
		    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
		    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
		    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		    localNumberAxis.setUpperMargin(0.15D);
		    CategoryItemRenderer localCategoryItemRenderer = localCategoryPlot.getRenderer();
		    localCategoryItemRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		    localCategoryItemRenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
		    CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
		    localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			return localJFreeChart;
	  }
	
	 /** 
	* 
	* @param model
	* @return JFreeChart
	* @description:统计数据-年度-2.系统用户的统计数据
	*/
	private  JFreeChart createChartYearUser(IUserMgMtVOModel model)
	  {
		Double sales_count=0d;//在职人员数量
		Double policyno_count=0d;//保单数量
		Double netpremium_count=0d;//保费金额
		Double fnum_count=0d;//手续费金额
		String branch_id=model.getDept_no();
		IUserMgMtVOModel userinfo=new UserMgMtVOModel();
		userinfo.setOpt_no(model.getOpt_no());
		userinfo.setSales_status("1");//人员类型：在职
		//登录人员属于...
		if(branch_id.length()==2){//总公司
			//查询总公司
			List<IUserMgMtVOModel> list=userMgmtDomain.querySalesInfo(userinfo);
			sales_count=(double) list.size();
			for(int i=0;i<list.size();i++){
				IUserMgMtVOModel mm=list.get(i);
				userinfo.setOpt_no(mm.getSales_id()); 
				IUserMgMtVOModel countmodel=userMgmtDomain.statisticalDataYearSalesOrProtocolYear(userinfo);
				policyno_count+=countmodel.getPolicyno_count();
				netpremium_count+=countmodel.getNetpremium_count();
				fnum_count+=countmodel.getFnum_count();
			}
		}else if(branch_id.length()==4){//分公司
			userinfo.setBranch_id(branch_id);//查询分公司和营业部
			List<IUserMgMtVOModel> list=userMgmtDomain.querySalesInfoFenGongSi(userinfo);
			sales_count=(double) list.size();
			for(int i=0;i<list.size();i++){
				IUserMgMtVOModel mm=list.get(i);
				userinfo.setOpt_no(mm.getSales_id()); 
				IUserMgMtVOModel countmodel=userMgmtDomain.statisticalDataYearSalesOrProtocolYear(userinfo);
				policyno_count+=countmodel.getPolicyno_count();
				netpremium_count+=countmodel.getNetpremium_count();
				fnum_count+=countmodel.getFnum_count();
			}
		}else if(branch_id.length()==6){//营业部
			userinfo.setBranch_id(branch_id);//查询营业部的
			List<IUserMgMtVOModel> list=userMgmtDomain.querySalesInfo(userinfo);
			sales_count=(double) list.size();
			for(int i=0;i<list.size();i++){
				IUserMgMtVOModel mm=list.get(i);
				userinfo.setOpt_no(mm.getSales_id()); 
				IUserMgMtVOModel countmodel=userMgmtDomain.statisticalDataYearSalesOrProtocolYear(userinfo);
				policyno_count+=countmodel.getPolicyno_count();
				netpremium_count+=countmodel.getNetpremium_count();
				fnum_count+=countmodel.getFnum_count();
			}
		}
		 Date date=new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		 String year=sdf.format(date);
		 
		    String str1 = "First";
		    String str2 = "在职人员数量";
		    String str3 = "保单数量";
		    String str4 = "保费金额";
		    String str5 = "手续费金额";
		    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		    localDefaultCategoryDataset.addValue(sales_count, str1, str2);
		    localDefaultCategoryDataset.addValue(policyno_count, str1, str3);
		    localDefaultCategoryDataset.addValue(netpremium_count, str1, str4);
		    localDefaultCategoryDataset.addValue(fnum_count, str1, str5);
		    
		    JFreeChart chart = ChartFactory.createBarChart("年度", year, "", localDefaultCategoryDataset, PlotOrientation.VERTICAL, false, true, false);
		    CategoryPlot localCategoryPlot = (CategoryPlot)chart.getPlot();
		    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
		    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		    localNumberAxis.setUpperMargin(0.15D);
		    CategoryItemRenderer localCategoryItemRenderer = localCategoryPlot.getRenderer();
		    localCategoryItemRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		    localCategoryItemRenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
		    CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
		    localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			return chart;
	  }
	
	
	/** 
	* 
	* @param ops
	* @param user
	* @throws IOException 
	* @description:统计数据-月度
	*/
	@Override
	public void statisticalDataMonth(ServletOutputStream ops,IUserModel user) throws IOException {
		  String emp_id=user.getEmp_id();//登录用户代码
		  IUserMgMtVOModel model=new UserMgMtVOModel();
          model.setOpt_no(emp_id);
          IUserMgMtVOModel userInfoModel=userMgmtDomain.queryUserInfo(model);//查询登录用户信息
          String person_type=userInfoModel.getPerson_type();//登录用户类型
          JFreeChart createChart=null;
          //登录用户为销售员或代理人
          if("xsy".equals(person_type) || "dlr".equals(person_type)){
         	     createChart = createChartMonthSalesOrProtocol(userInfoModel);//生成统计图形的方法
         }else{
        	     createChart=createChartMonthUser(userInfoModel);//生成统计图形的方法
         }
		  BufferedImage bi = createChart.createBufferedImage(300, 350);
		  ImageIO.write(bi, "PNG", ops);
	}
	
	
	/** 
	* 
	* @param model
	* @return JFreeChart
	* @description:统计数据-月度-1.销售员或代理人的统计数据
	*/
	private  JFreeChart createChartMonthSalesOrProtocol(IUserMgMtVOModel model)
	  {
		 //查询相关数据信息
		 IUserMgMtVOModel countmodel=userMgmtDomain.statisticalDataYearSalesOrProtocolMonth(model);
		 Date date=new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("MM");
		 String month=sdf.format(date);
		 
		    String str1 = "First";
		    String str4 = "保单数量";
		    String str5 = "保费金额";
		    String str6 = "手续费金额";
		    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		    localDefaultCategoryDataset.addValue(countmodel.getPolicyno_count(), str1, str4);
		    localDefaultCategoryDataset.addValue(countmodel.getNetpremium_count(), str1, str5);
		    localDefaultCategoryDataset.addValue(countmodel.getFnum_count(), str1, str6);
		    
		    JFreeChart localJFreeChart = ChartFactory.createBarChart("月度", month, "", localDefaultCategoryDataset, PlotOrientation.VERTICAL, false, true, false);
		    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
		    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
		    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		    localNumberAxis.setUpperMargin(0.15D);
		    CategoryItemRenderer localCategoryItemRenderer = localCategoryPlot.getRenderer();
		    localCategoryItemRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		    localCategoryItemRenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
		    CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
		    localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			return localJFreeChart;
	  }
	
	 /** 
	* 
	* @param model
	* @return JFreeChart
	* @description:统计数据-月度-2.系统用户的统计数据
	*/
	private  JFreeChart createChartMonthUser(IUserMgMtVOModel model)
	  {
		Double sales_count=0d;//在职人员数量
		Double policyno_count=0d;//保单数量
		Double netpremium_count=0d;//保费金额
		Double fnum_count=0d;//手续费金额
		String branch_id=model.getDept_no();
		IUserMgMtVOModel userinfo=new UserMgMtVOModel();
		userinfo.setOpt_no(model.getOpt_no());
		userinfo.setSales_status("1");//人员类型：在职
		//登录人员属于...
		if(branch_id.length()==2){//总公司
			//查询总公司
			List<IUserMgMtVOModel> list=userMgmtDomain.querySalesInfo(userinfo);
			sales_count=(double) list.size();
			for(int i=0;i<list.size();i++){
				IUserMgMtVOModel mm=list.get(i);
				userinfo.setOpt_no(mm.getSales_id()); 
				IUserMgMtVOModel countmodel=userMgmtDomain.statisticalDataYearSalesOrProtocolMonth(userinfo);
				policyno_count+=countmodel.getPolicyno_count();
				netpremium_count+=countmodel.getNetpremium_count();
				fnum_count+=countmodel.getFnum_count();
			}
		}else if(branch_id.length()==4){//分公司
			userinfo.setBranch_id(branch_id);//查询分公司和营业部
			List<IUserMgMtVOModel> list=userMgmtDomain.querySalesInfoFenGongSi(userinfo);
			sales_count=(double) list.size();
			for(int i=0;i<list.size();i++){
				IUserMgMtVOModel mm=list.get(i);
				userinfo.setOpt_no(mm.getSales_id()); 
				IUserMgMtVOModel countmodel=userMgmtDomain.statisticalDataYearSalesOrProtocolMonth(userinfo);
				policyno_count+=countmodel.getPolicyno_count();
				netpremium_count+=countmodel.getNetpremium_count();
				fnum_count+=countmodel.getFnum_count();
			}
		}else if(branch_id.length()==6){//营业部
			userinfo.setBranch_id(branch_id);//查询营业部的
			List<IUserMgMtVOModel> list=userMgmtDomain.querySalesInfo(userinfo);
			sales_count=(double) list.size();
			for(int i=0;i<list.size();i++){
				IUserMgMtVOModel mm=list.get(i);
				userinfo.setOpt_no(mm.getSales_id()); 
				IUserMgMtVOModel countmodel=userMgmtDomain.statisticalDataYearSalesOrProtocolMonth(userinfo);
				policyno_count+=countmodel.getPolicyno_count();
				netpremium_count+=countmodel.getNetpremium_count();
				fnum_count+=countmodel.getFnum_count();
			}
		}
		 Date date=new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("MM");
		 String month=sdf.format(date);
		 
		    String str1 = "First";
		    String str2 = "在职人员数量";
		    String str3 = "保单数量";
		    String str4 = "保费金额";
		    String str5 = "手续费金额";
		    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		    localDefaultCategoryDataset.addValue(sales_count, str1, str2);
		    localDefaultCategoryDataset.addValue(policyno_count, str1, str3);
		    localDefaultCategoryDataset.addValue(netpremium_count, str1, str4);
		    localDefaultCategoryDataset.addValue(fnum_count, str1, str5);
		    
		    JFreeChart chart = ChartFactory.createBarChart("月度", month, "", localDefaultCategoryDataset, PlotOrientation.VERTICAL, false, true, false);
		    CategoryPlot localCategoryPlot = (CategoryPlot)chart.getPlot();
		    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
		    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		    localNumberAxis.setUpperMargin(0.15D);
		    CategoryItemRenderer localCategoryItemRenderer = localCategoryPlot.getRenderer();
		    localCategoryItemRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		    localCategoryItemRenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
		    CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
		    localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			return chart;
	  }
	
	
}
