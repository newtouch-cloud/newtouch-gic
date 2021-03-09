package com.ca.cacore.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CheckCodeUtil {
	private static int WIDTH=70;
	private static int HEIGHT=30;
	private static char[] CODE={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2',
			'3','4','5','6','7','8','9'};
	//字体大小一致版
	public static Map<String, BufferedImage> getCheckCode1(){
		Random r=new Random();
		StringBuffer sb=new StringBuffer();
		//1.绘图
		//step1,创建一个内存映像对象（画布）
		BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//step2,获得画笔
		Graphics g=image.getGraphics();
		//step3,给画笔上一个颜色
		g.setColor(Color.LIGHT_GRAY);
		//step4,填充花布
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//step5,填充验证码
		for(int i=0;i<4;i++){
			sb.append(CODE[r.nextInt(CODE.length)]);
		}
		//step6,设置字体的颜色,大小,风格
		g.setColor(new Color(0));
		g.setFont(new Font(null, Font.ITALIC|Font.BOLD, 20));
		g.drawString(sb.toString(), 5, 22);
		//step7,划线
		g.setColor(new Color(r.nextInt(255)));
		for(int i=0;i<4;i++){
			g.drawLine(r.nextInt(40),r.nextInt(15), r.nextInt(65), r.nextInt(30));
		}
		//将验证码字符串和BufferedImage对象保存到map里返回
		Map<String, BufferedImage> codeMap=new HashMap<String, BufferedImage>();
		codeMap.put(sb.toString(), image);
		return codeMap;
	}
	// 字体大小随机版
	public static Map<String, BufferedImage> getCheckCode2(){
		StringBuffer sb=new StringBuffer();
		//1.绘图
		//step1,创建一个内存映像对象（画布）
		BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//step2,获得画笔
		Graphics g=image.getGraphics();
		//step3,给画笔上一个随即的颜色
		Random r=new Random();
		g.setColor(Color.WHITE);
		//step4,填充花布
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//step5,填充验证码,设置字体的大小，风格
		for(int i=0;i<5;i++){
			String number=CODE[r.nextInt(CODE.length)]+"";
			g.setColor(new Color(r.nextInt(255)));
			g.setFont(new Font(null, Font.ITALIC|Font.BOLD, r.nextInt(3)+14));
			g.drawString(number, 5+i*12, 22);
			sb.append(number);
		}
		//step6,划线
		g.setColor(new Color(r.nextInt(255)));
		for(int i=0;i<5;i++){
			g.drawLine(r.nextInt(40),r.nextInt(15), r.nextInt(80), r.nextInt(30));
		}
		//将验证码字符串和BufferedImage对象保存到map里返回
		Map<String, BufferedImage> codeMap=new HashMap<String, BufferedImage>();
		codeMap.put(sb.toString(), image);
		return codeMap;
	}
	public static InputStream getInputStream(BufferedImage image) throws ImageFormatException, IOException{
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(bos);
		encoder.encode(image);
		byte[] imageBts = bos.toByteArray();
		InputStream in = new ByteArrayInputStream(imageBts);
		return in;
	}
}
