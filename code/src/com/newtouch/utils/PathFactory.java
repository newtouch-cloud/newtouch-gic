package com.newtouch.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.newtouch.newutil.string.StrUtil;

public class PathFactory {
	private static Properties perties = null;

	private PathFactory() {

	}

	public static void main(String[] args) {
		String file = "D:\\jboss1\\jboss-as-7.1.1.Final\\standalone\\tmp\\vfs\\temp1bd45049f7973c10";
		System.out.println(StringUtils.replace(file, "\\", "/"));

		System.out.println(System.getProperty("catalina.home"));
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("java.io.tmpdir"));
		System.out.println("getSource=" + PathFactory.getSource());
		System.out.println("getWebRoot=" + PathFactory.getWebRoot());
		System.out.println(PathFactory.getImagePath());

	}

	/**
	 * 获得项目代码目录
	 * 
	 * @return 项目部署目录<br>
	 *         例如：.../apache-tomcat/mobile-foundation/
	 */
	public static String getSource() {
		String fileName = "config/jdbc.properties";
		String basePath = "";
		try {

			try {
				basePath = Thread.currentThread().getContextClassLoader().getResource(fileName).toURI().getPath();
				// try {
				// URL url =
				// Thread.currentThread().getContextClassLoader().getResource(fileName);
				// Object content = url.openConnection().getContent();
				// Class<?> czz = Class.forName("org.jboss.vfs.VirtualFile");
				// Method m = czz.getMethod("getPhysicalFile");
				// File physicalFile = (File) m.invoke(content);
				// basePath = physicalFile.getPath();
				// basePath = StringUtils.replace(basePath, "\\", "/");
				// System.out.println("physicalFile.getPath()=" + basePath);
				// System.out.println("physicalFile.getAbsolutePath()=" +
				// physicalFile.getAbsolutePath());
				//
				// } catch (Exception e) {
				// e.printStackTrace();
				// }

			} catch (URISyntaxException e) {
				fileName = "log4j2.xml";
				try {
					basePath = Thread.currentThread().getContextClassLoader().getResource(fileName).toURI().getPath();
				} catch (URISyntaxException e1) {
					throw new RuntimeException("查找路径出错");
				}
			}

		} catch (

		NullPointerException e) {
			fileName = "";
			basePath = new PathFactory().getClass().getResource("/").getPath();
			if (basePath.indexOf("/target/") > 0)
				basePath = basePath.substring(0, basePath.indexOf("/target/")) + "/target/classes";
		}
		if (StrUtil.isNull(basePath))
			throw new RuntimeException("查找路径出错");

		if (basePath.indexOf("WEB-INF") > 0) {
			basePath = basePath.substring(0, basePath.indexOf("WEB-INF/classes"));
		}
		if (basePath.endsWith(fileName))
			basePath = basePath.substring(0, basePath.length() - fileName.length() - 1);
		return basePath;
	}

	/**
	 * 获得项目部署的class目录
	 * 
	 * @return 例如：.../apache-tomcat/mobile-foundation/WEB-INF/classes
	 */
	public static String getClassPath() {
		System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssss" + PathFactory.getSource() + "/WEB-INF/classes");

		System.out.println("System.getProperty(\"catalina.home\")=" + System.getProperty("catalina.home"));
		System.out.println("System.getProperty(\"java.home\")" + System.getProperty("java.home"));
		System.out.println("System.getProperty(\"user.home\")" + System.getProperty("user.home"));
		System.out.println("System.getProperty(\"java.io.tmpdir\")" + System.getProperty("java.io.tmpdir"));
		System.out.println("getSource=" + PathFactory.getSource());
		System.out.println("getWebRoot=" + PathFactory.getWebRoot());

		String str = System.getProperty("user.dir") + "/web";
		System.out.println("user.dir=" + str);

		// 2
		String pa = PathFactory.class.getResource("/").getFile();
		System.out.println("pa=" + pa);

		String pb = PathFactory.class.getResource("").toString();
		System.out.println("pb=" + pb);

		String pc = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		System.out.println("pc=" + pc);

		String pd = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println("pd=" + pd);

		return PathFactory.getSource() + "/WEB-INF/classes";
	}

	/**
	 * 返回web窗口的根目录
	 * <p>
	 * 例如：tomcat的根目录为/我的电脑/工作/Workspace/apache-tomcat-7.0.55
	 * 
	 * @return
	 */
	public static String getWebRoot() {
		return System.getProperty("catalina.home");

	}

	public static Properties getProperties() {
		initProperty();
		return perties;
	}

	/**
	 * 根据操作系统不同，返回配置文件中目录
	 * <p>
	 * 注：返回的路径最后均带"/"。<br>
	 * 例如：<br>
	 * Windows下返回格式为"c:/"<br>
	 * Linux下返回格式为"/home/sms/app/smstest/"
	 * 
	 * @return
	 */
	public static String getProperty() {
		initProperty();
		String osName = System.getProperty("os.name");
		if (osName.contains("Windows")) {
			return perties.getProperty("Windows");
		} else {
			return perties.getProperty("Linux");
		}
	}

	/**
	 * 根据操作系统不同，返回ImageMagick配置文件中目录
	 * 
	 */
	public static String getImagePath() {
		initPropertyImage();
		String osName = System.getProperty("os.name");
		if (osName.contains("Windows")) {
			return perties.getProperty("IMPath_win");
		} else {
			return perties.getProperty("IMPath_linxu");
		}
	}

	/**
	 * 获得资源文件路径
	 * 
	 * @return
	 */
	public static String getResources() {
		initProperty();
		return perties.getProperty("Resources");
	}

	public static synchronized void initProperty() {
		if (perties != null) {
			return;
		}
		PathFactory.perties = new Properties();
		InputStream file = null;
		try {
			file = PathFactory.class.getClassLoader().getResourceAsStream("config/path.properties");
			perties.load(file);
		} catch (Exception e) {
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static synchronized void initPropertyImage() {
		if (perties != null) {
			return;
		}
		PathFactory.perties = new Properties();
		InputStream file = null;
		try {
			file = PathFactory.class.getClassLoader().getResourceAsStream("config/img4javapath.properties");
			perties.load(file);
		} catch (Exception e) {
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
