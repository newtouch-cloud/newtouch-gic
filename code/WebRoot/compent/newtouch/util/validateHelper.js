/*
 * JavaScript 公用模块
 */
 
/*函数清单

 校验电话格式是否匹配 
IsCheckTel


校验邮编格式是否匹配
IsCheckPost

检查输入的E-mail是否正确
IsCheckEmail


检查输入的字符类型是否为number型
isNumber

校验指定长度的数字字符串
IscheckNumLth

判断参数是否为未定义或null,如未定义或为null返回true
isUndefined
********************************************************************************************************************************************************

/*
 *判断参数是否为未定义或null,如未定义或为null返回true
 *@param paraValue 待处理字符串
 *@return true或者false
 */
function isUndefined(paraValue){
   if(paraValue==null||"undefined"==paraValue+"") return true;
   return false;
}

/*
 *校验指定长度的数字字符串
 *@param value 待校验的字符串
 *@param length 指定的长度
 *@return true or false
 */

function IscheckNumLth(value,length){
	if(value.length!=length){
		return false;
	}
	var reg=/^[0-9]*$/;
	if(reg.test(value)===false){
		return false;
	}
}

  
/*
 *检查输入的字符类型是否为number型
 *@param str 待检查的字符
 *@return boolean值，true or false
 */
function isNumber(str){
    var	rc=true;
    if (isUndefined(str)){
		rc=false;
    } else if(str.length==0){
		rc=false;
    } else {
	for(i=0;i<str.length;i++){
	    if(str.charAt(i)<'0' || str.charAt(i)>'9'){
		rc=false;
		break;
	    }
	}
    }
    return rc;
}



/*
 *检查输入的E-mail是否正确
 *@param str 待检查的e-mail
 *@return boolean值，true or false
 */
function IsCheckEmail(str){
	var reg = new Object();
	//Email正则表达式规则 
	reg=/^[1-9]\\d{5}$/; 
	if (reg.test(str)){
		return true; 
	} 
    return false;
}



/*
 *校验邮编格式是否匹配
 *@param str 待检查的邮编
 *@return boolean值，true or false
 */
function IsCheckPost(str){
	var reg = new Object();
	//邮编正则表达式规则 
	reg=/^[1-9]\\d{5}$/; 
	if (reg.test(str)){
		return true; 
	} 
    return false;
}


/*
 *校验电话格式是否匹配
 *@param str 待检查的电话
 *@return boolean值，true or false
 */
function IsCheckTel(str){
	var reg = new Object();
	//邮编正则表达式规则 
	reg=/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{7,8}$)|(^0{0,1}1[0-9]{10}$)/; 
	if (reg.test(str)){
		return true; 
	} 
    return false;
}











