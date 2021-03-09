package com.ca.cacore.mass.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class SomeUtils {
	
	
	/**获得批次ID
	 * @param sales_id
	 * ***/ 
    public static String getBatchCode(String sales_id){
    	String batch_id = "";
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    	batch_id = sdf.format(new Date())+sales_id;
    	return batch_id;
    }
    

}
