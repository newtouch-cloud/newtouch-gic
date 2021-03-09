
    function getNowFormatDate() {
       var day = new Date();

       var Year = 0;
       var Month = 0;
       var Day = 0;
       var CurrentDate = "";
       //初始化时间
       //Year       = day.getYear();//有火狐下2008年显示108的bug
       Year       = day.getFullYear();//ie火狐下都可以
       Month      = day.getMonth()+1;
       Day        = day.getDate();
       
       CurrentDate += Year + "-";
       
       if (Month >= 10 ) {
           CurrentDate += Month + "-";
       } else {
           CurrentDate += "0" + Month + "-";
       }
       if (Day >= 10 ) {
           CurrentDate += Day ;
       } else {
           CurrentDate += "0" + Day ;
       }

       return CurrentDate;
    }

    function compareDate(startDate,endDate) {   
        var startMonth = startDate.substring(5,startDate.lastIndexOf ("-"));  
        var startDay = startDate.substring(startDate.length,startDate.lastIndexOf ("-")+1);  
        var startYear = startDate.substring(0,startDate.indexOf ("-"));  
      
        var endMonth = endDate.substring(5,endDate.lastIndexOf ("-"));  
        var endDay = endDate.substring(endDate.length,endDate.lastIndexOf ("-")+1);  
        var endYear = endDate.substring(0,endDate.indexOf ("-"));  
          
        if (Date.parse(startMonth+"/"+startDay+"/"+startYear) >  
            Date.parse(endMonth+"/"+endDay+"/"+endYear)) {  
            return false;  
        }  
        return true;  
    }  