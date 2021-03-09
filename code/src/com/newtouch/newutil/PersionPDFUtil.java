package com.newtouch.newutil;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PushbuttonField;
import com.newtouch.utils.PathFactory;
import com.newtouch.peoplemanage.model.po.IEducationVOModel;
import com.newtouch.peoplemanage.model.vo.IPersonManageVOModel;
import com.newtouch.newutil.date.DateUtil;

public class PersionPDFUtil {

	/**
	 * 传入参数导出PDF，并把信息存入数据库
	 */
	@SuppressWarnings("null")
	public static ByteArrayOutputStream fillEmployee(IPersonManageVOModel baseInfo) {
		Document doc = new Document();
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font textfont = new Font(bfChinese, 13, Font.NORMAL);
			Font keyfont = new Font(bfChinese, 13, Font.BOLD);
			Font headfont = new Font(bfChinese, 13, Font.BOLD);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 模板路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 设置时间格式
		String createdate = sdf.format(new Date()); // 获得当前时间
		String templatePath = PathFactory.getSource() + "/WEB-INF/pdf/保险代理从业人员执业登记注册表.pdf";
		// String newPDFPath = "E:/保险代理从业人员执业登记注册表" + createdate + ".pdf";
		PdfReader reader;
		FileOutputStream out;
		ByteArrayOutputStream bos = null;
		PdfStamper stamper;
		try {
			// out = new FileOutputStream(newPDFPath);// 输出流
			reader = new PdfReader(templatePath);// 读取pdf模板
			bos = new ByteArrayOutputStream(); // 可以捕获内存缓存中的数据，转换成字节数组。
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			java.util.Iterator<String> it = form.getFields().keySet().iterator();
			// BaseInfo baseInfo = info.getEmployeeBody().getBaseInfo();
			form.setField("Text1", baseInfo.getPerson_name());// 姓名
			if ("1".equals(baseInfo.getSex())) {
				form.setField("Text2", "男");// 性别
			}
			if ("2".equals(baseInfo.getSex())) {
				form.setField("Text2", "女");// 性别
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
			form.setField("Text3", DateUtil.getStringDateMonth(baseInfo.getBirthday()));// 出生年月
			form.setField("Text4", baseInfo.getNational());// 民族
			form.setField("Text5", baseInfo.getEducation());// 文化程度
			form.setField("Text6", baseInfo.getPolitical());// --政治面貌
			form.setField("Text7", baseInfo.getIdcard());// 身份证号
			form.setField("Text8", baseInfo.getSchool());// 毕业学校
			form.setField("Text9", baseInfo.getTel());// --固定电话
			form.setField("Text10", baseInfo.getPhone());// 移动电话
			form.setField("Text11", baseInfo.getIdcard_adress());// --户籍地址
			form.setField("Text12", "");// 乡镇
			form.setField("Text13", baseInfo.getHome_address());// --现地址
			form.setField("Text14", baseInfo.getPractice_area());// 执业区域
			form.setField("Text15", baseInfo.getPerson_no());// 公司工号
			form.setField("Text16", baseInfo.getBranch_name());// 所属机构
			form.setField("Text17", baseInfo.getContract_type());// --用工合同类型
			form.setField("Text18", baseInfo.getBusiness_scope());// 业务范围
			form.setField("Text19", "");// 申请人签字
			form.setField("Text20", DateUtil.getStringDateMonth(DateUtil.sysDate()));// 签字年月日
			form.setField("Text21", "");// 未逾5年 有
			form.setField("Text22", "√");// 未逾5年 无
			form.setField("Text23", "");// 未逾3年 有
			form.setField("Text24", "√");// 未逾3年 无
			form.setField("Text25", "");// 未届满 有
			form.setField("Text26", "√");// 未届满 无
			form.setField("Text27", "");// 申请人签字
			form.setField("Text28","");// 经办人签字
			form.setField("Text29", DateUtil.getStringDateMonth(DateUtil.sysDate()));// 签字年月日
			Image image = Image.getInstance(PathFactory.getSource() + "/WEB-INF/pdf/1522806114831.jpg");
			float documentWidth = doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin();
			float documentHeight = documentWidth / 580 * 320;// 重新设置宽高
			image.scaleAbsolute(100, 200);// 重新设置宽高
			PushbuttonField pushbuttonField = form.getNewPushbuttonFromField("image");
			pushbuttonField.setImage(image);
			PdfFormField editFormField = pushbuttonField.getField();
			form.replacePushbuttonField("image", editFormField);
			stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
			stamper.close();
			// PdfCopy copy = new PdfCopy(doc, out);
			doc.open();
			// PdfImportedPage importPage = copy.getImportedPage(new
			// PdfReader(bos.toByteArray()), 1);
			// copy.addPage(importPage);
			doc.close();
		} catch (IOException e) {
			System.out.println(1);
		} catch (DocumentException e) {
			System.out.println(2);
		}
		return bos;

	}

	// 利用模板生成pdf
	public static ByteArrayOutputStream fillOperation(IPersonManageVOModel baseInfo, List<IEducationVOModel> educationInfos,
			List<IEducationVOModel> workInfos) {
		Document doc = new Document();
		// BaseInfo baseInfo = info.getEmployeeBody().getBaseInfo();
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font textfont = new Font(bfChinese, 10, Font.NORMAL);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 模板路径
		String templatePath = PathFactory.getSource() + "/WEB-INF/pdf/保险中介从业人员基本情况登记表.pdf";
		// 生成的新文件路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 设置时间格式
		String createdate = sdf.format(new Date()); // 获得当前时间
		// String newPDFPath = "E:/保险中介从业人员基本情况登记表" + createdate + ".pdf";
		PdfReader reader;
		FileOutputStream out;
		ByteArrayOutputStream bos = null;
		PdfStamper stamper;
		try {
			// out = new FileOutputStream(newPDFPath);// 输出流
			reader = new PdfReader(templatePath);// 读取pdf模板
			bos = new ByteArrayOutputStream(); // 可以捕获内存缓存中的数据，转换成字节数组。
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			java.util.Iterator<String> it = form.getFields().keySet().iterator();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
			form.setField("Text1", baseInfo.getPerson_name());// 姓名
			if ("1".equals(baseInfo.getSex())) {
				form.setField("Text2", "男");// 性别
			}
			if ("2".equals(baseInfo.getSex())) {
				form.setField("Text2", "女");// 性别
			}
			form.setField("Text3", baseInfo.getNational());// 民族
			form.setField("Text4", DateUtil.getStringDateMonth(baseInfo.getBirthday()));// 出生年月
			form.setField("Text5", baseInfo.getEducation());// 学历
			form.setField("Text6", baseInfo.getPolitical());// 政治面貌
			form.setField("Text7", baseInfo.getIdcard());// 身份证号
			form.setField("Text8", baseInfo.getPhone());// 移动电话
			form.setField("Text9", baseInfo.getTel());// --固定电话
			form.setField("Text10", baseInfo.getIdcard_adress());// --户口所在地
			form.setField("Text11", baseInfo.getTechnology_no());// 专业技术资格
			form.setField("Text12", "");// --婚否
			form.setField("Text13", baseInfo.getHome_address());// --家庭住址
			String contract_type = baseInfo.getContract_type();// --兼职情况
			/*if ("1".equals(contract_type)) {
				form.setField("Text14", "√");// --兼职情况 专职
			} else {
				form.setField("Text15", "√");// 兼职情况 兼职
			}*/
			// List<EducationInfo> educationInfos =
			// info.getEmployeeBody().getEducationInfos();
			/*for (int i = 1; i < educationInfos.size() + 1; i++) {
				int j = 6 * i + 10;
				IEducationVOModel educationInfo = educationInfos.get(i - 1);
				// 学历教育情况列表 （学历教育集合）
				form.setField("Text" + Integer.toString(j++), format.format(educationInfo.getStart_date()));// 入学年月
				form.setField("Text" + Integer.toString(j++), format.format(educationInfo.getGraduation_date()));// 毕业年月
				form.setField("Text" + Integer.toString(j++), educationInfo.getAddress());// 毕业学校
				form.setField("Text" + Integer.toString(j++), educationInfo.getMajor());// 专业
				form.setField("Text" + Integer.toString(j++), educationInfo.getYear());// 学制
				form.setField("Text" + Integer.toString(j++), educationInfo.getDegree());// 学历
			}*/
			// List<WorkInfo> workInfos = info.getEmployeeBody().getWorkInfos();
			/*for (int i = 1; i < workInfos.size() + 1; i++) {
				int j = 5 * i + 35;
				IEducationVOModel educationInfo = workInfos.get(i - 1);
				// 个人从业经历列表（从业经历集合）1
				form.setField("Text" + Integer.toString(j++), format.format(educationInfo.getStart_date()));// 起始日期
				form.setField("Text" + Integer.toString(j++), format.format(educationInfo.getGraduation_date()));// 终止日期
				form.setField("Text" + Integer.toString(j++), educationInfo.getAddress());// 在何地，何单位从事何工作
				form.setField("Text" + Integer.toString(j++), educationInfo.getWork_position());// 职务
				form.setField("Text" + Integer.toString(j++), educationInfo.getApprove_person());// 证明人
			}*/
			Image image = Image.getInstance(PathFactory.getSource() + "/WEB-INF/pdf/1522806114831.jpg");
			float documentWidth = doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin();
			float documentHeight = documentWidth / 580 * 320;// 重新设置宽高
			image.scaleAbsolute(100, 200);// 重新设置宽高
			PushbuttonField pushbuttonField = form.getNewPushbuttonFromField("image");
			pushbuttonField.setImage(image);
			PdfFormField editFormField = pushbuttonField.getField();
			form.replacePushbuttonField("image", editFormField);

			stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
			stamper.close();

			doc.open();
			// PdfCopy copy = new PdfCopy(doc, out);
			// PdfImportedPage importPage = copy.getImportedPage(new
			// PdfReader(bos.toByteArray()), 1);
			// copy.addPage(importPage);
			doc.close();

		} catch (IOException e) {
			System.out.println(e);
		} catch (DocumentException e) {
			System.out.println(e);
		}
		return bos;

	}
}
