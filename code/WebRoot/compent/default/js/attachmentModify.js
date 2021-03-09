  //对附件信息进行控制
	      $(document).ready(function() {
		 		 var file_name = $('#file_name').val();
		 		 if(file_name == ""){
		 			 $('#uplo').show();
		 		 }else{
		 			 $('#deuplo').show(); 
		 		 }
	 		 
	 	    });
   //删除附件    
	    function deleteAttachment(){
	    	if(confirm("是否确认删除附件?")){
	    		var file_id = $('#file_id').val(); 
		    	
		    	//为delete_file赋值表示对附件进行删除
		    	$('#delete_file').val(file_id);
		    	$('#file_name').val('');
		    	$('#file_id').val('');
		    	$('#deuplo').remove();
		    	$('#uplo').show(); 
 			 }
	    	
	    }
	    
	    function submitFormForFile(path){
	    	var delete_file = $('#delete_file').val(); 
	    	if(delete_file != ""){
	    		$.ajax({
	      			url:path+"/TMS/upload/deleteAttachment.do",
	      			type:"post",
	      			async: false,
	      			data:{"file_id":delete_file},
	      			success:function(data){
	      				
	      			   }
	      			});
	    	}
	    }