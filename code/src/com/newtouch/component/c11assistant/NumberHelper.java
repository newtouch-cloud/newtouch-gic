package com.newtouch.component.c11assistant;

import java.math.BigDecimal;


/**
 * C11_10 - Number Helper Component
 * 数字处理助手
 * @author ma_cj
 *
 */
public class NumberHelper {
	
	  /** 
	   * 获取对象的double值 
	   *  
	   * @param object 
	   *            被转换的对象 
	   * @return 
	   */  
	  public static double getDoubleValue(Object object) {  
	    try {  
	      return Double.valueOf(StringHelper.getStrValue(object)).doubleValue();  
	    } catch (Exception ex) {  
	      return 0;  
	    }  
	  }  
	  
	  /** 
	   * 高精度加法运算 
	   *  
	   * @param data1  加数 
	   * @param data2  被加数 
	   * @return 
	   */  
	  public static double addition(Object data1, Object data2) {  
	    return new BigDecimal(StringHelper.getStrValue(getDoubleValue(data1))).add(new BigDecimal(StringHelper.getStrValue(getDoubleValue(data2)))).doubleValue();  
	  }  
	  
	  /** 
	   * 高精度减法运算 
	   *  
	   * @param data1  减数 
	   * @param data2  被减数 
	   * @return 
	   */  
	  public static double subduction(Object data1, Object data2) {  
	    return new BigDecimal(StringHelper.getStrValue(getDoubleValue(data1))).subtract(new BigDecimal(StringHelper.getStrValue(getDoubleValue(data2)))).doubleValue();  
	  }  
	  
	  /** 
	   * 高精度乘法运算 
	   *  
	   * @param data1 乘数 
	   * @param data2   被乘数 
	   * @return 
	   */  
	  public static double multiplication(Object data1, Object data2) {  
	    return new BigDecimal(StringHelper.getStrValue(getDoubleValue(data1))).multiply(new BigDecimal(StringHelper.getStrValue(getDoubleValue(data2)))).doubleValue();  
	  }  
	  
	  /** 
	   * 高精度除法运算 
	   *  
	   * @param data1  除数 
	   * @param data2   被除数 
	   * @return 
	   */  
	  public static double division(Object data1, Object data2) {  
	    if (getDoubleValue(data2) != 0) {  
	      return new BigDecimal(StringHelper.getStrValue(getDoubleValue(data1))).divide(new BigDecimal(StringHelper.getStrValue(getDoubleValue(data2)))).doubleValue();  
	    } else {  
	      return 0;  
	    }  
	  }  
}