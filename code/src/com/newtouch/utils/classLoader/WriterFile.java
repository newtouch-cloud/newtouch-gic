package com.newtouch.utils.classLoader;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class WriterFile {
	private static boolean ISAPPEND = false;// false:创建新文件;true:在原文件中添加

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("111111111\t222222222");
		list.add("222222222\t111111111");
		WriterFile.write("E:\\123.txt", list);
	}

	/**
	 * 设置文件写入类型
	 * 
	 * @param isAppend
	 *            false:创建新文件<br>
	 *            true:在原文件中添加
	 */
	public static void isAppend(boolean isAppend) {
		WriterFile.ISAPPEND = isAppend;
	}

	public static void write(String path, List<String> list) {
		System.out.println(path);
		WriterFile.createDirs(path);
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(f, WriterFile.ISAPPEND);
			for (String arg : list) {
				System.out.println(arg);
				fileWriter.write(arg + "\n");
			}
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createDirs(String path) {
		File fd = null;
		try {
			fd = new File(path);
			String dir = fd.getPath();
			if (dir.contains(".")) {
				dir = dir.substring(0, dir.lastIndexOf(File.separator));
			}
			fd = null;
			fd = new File(dir);
			if (!fd.exists()) {
				fd.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			fd = null;
		}
	}
}
