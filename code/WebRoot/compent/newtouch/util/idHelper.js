
/*函数清单

从身份证中获取生日信息，输出格式为yyyy-MM-dd
getBirthdayFromCnId

从身份证号中获取性别信息
getGenderFromcnId

检查输入参数是否为合法的身份证号码
isCheckCnId

检查身份证号上的生日与输入生日(yyyy-MM-dd)是否相符
isCheckCnIdBirth


检查身份证上的性别与输入的性别
isCheckCnIdGender
*/

/*
 *从身份证中获取生日信息，输出格式为yyyy-MM-dd
 *@param id 身份证号
 *@return 生日日期
 */
function getBirthdayFromCnId( id ) {
  try {
   var birthday;
   if ( id.length==15 )
      birthday = "19" + id.substring(6, 8)+"-"+id.substring(8, 10)+"-"+id.substring(10, 12);
   else if ( id.length==18 )
      birthday = id.substring(6, 10)+"-"+id.substring(10, 12)+"-"+id.substring(12, 14);
   else
      birthday = "";
   return birthday;
  }
  catch(e){
    return "";
  }
}


/*
 *从身份证号中获取性别信息
 *@param id_value 身份证号
 *@return 男(M)或女(F)
 */
function getGenderFromCnId( id_value ) {
   var gender;
   var id_gender;
   if ( id_value.length==15 )
      id_gender = id_value.substring(14);
   else if ( id_value.length==18 )
      id_gender = id_value.substring(16, 17);
   else
       return "";
   if ( id_gender=="1" || id_gender=="3" || id_gender=="5" || id_gender=="7" || id_gender=="9" ) {
            gender = "M";
   }
   else  {
          gender = "F";
   }
   return gender;
}



/*
 *检查输入参数是否为合法的身份证号码 (中国)(validate China	ID code	return boolean value)
 *@param sID 身份证号码
 *@return boolean值，true or false
 */
function isCheckCnId(sID){ 
	var isIDCard1 = new Object();
	var isIDCard2 = new Object();

	//身份证正则表达式(15位) 
	isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/; 
	//身份证正则表达式(18位) 
	isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/; 

	//验证身份证，如果正确，提交到reg.asp页面
	if (isIDCard1.test(sID)||isIDCard2.test(sID)){
		return true; 
	}
	return false;
}

/*
 *检查身份证号上的生日与输入生日(yyyy-MM-dd)是否相符
 *@param id 身份证号码
 *@param birth 生日
 *@return boolean值，true or false
 */
function isCheckCnIdBirth(id,birth){
	if (id.length == 15||id.length == 18){
		var id_birthday = getBirthdayFromCnId(id);
		if (birth!=id_birthday ) return false;
   	}else{
   		return false;
   	}
    return true;
}


/*
 *检查身份证上的性别与输入的性别 (M:male;F:female)是否相符
 *@param id 身份证号码
 *@param gender	性别
 */
function isCheckCnIdGender(id,gender){
	if (id.length == 15||id.length == 18){
		var id_gender = getGenderFromCnId(id);
		if (gender=="M" || gender=="m") {
			if ( id_gender=="F" || id_gender=="f" ) {
				return false;
			}
   		}else{
			if ( id_gender=="M" || id_gender=="m" ) {
				return false;
			}
   		}
   	}else{
   		return false;
	}
    return true;
}
