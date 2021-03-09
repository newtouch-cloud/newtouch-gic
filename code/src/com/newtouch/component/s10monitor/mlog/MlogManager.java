package com.newtouch.component.s10monitor.mlog;

import com.newtouch.component.c10filerw.FileAppender;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.FileHelper;
import com.newtouch.component.c11assistant.JSONHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.component.c1properties.StaticProperties;

public class MlogManager {
	
	private static int getLogid(){
		MBuslog mlog = (MBuslog)ThreadLocalHelper.get("MBuslog");
		return mlog.busId;
	}
	
	private static String getLogFilePath(){
		String rootPath = StaticProperties.getProperty("monitor.LogPatch");
		String dayPath = DateHelper.getSysStr("yyyyMMdd");
		String hourPath = DateHelper.getSysStr("HHmm");
		int bus_id = getLogid();
		String path = rootPath+dayPath+"/"+hourPath;
		String allpath = path+"/"+bus_id+".mlog";
		FileHelper.makeFolder(path);
		return allpath;
	}
	
	public static void dumpLog(MBuslog blog) {
		String fileName = getLogFilePath();
		FileAppender fa = null;
		try{
			blog.finish();
			fa = new FileAppender(fileName);
			fa.write(JSONHelper.beanToJson(blog));
//			fa.write(mlog.toString()+"\n");
//			for(MDetaillog dlog:mlog.getLogs()){
//				fa.write(dlog.toString()+"\n");
//			}
			fa.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			fa.closeFile();
		}
	}

	
}
