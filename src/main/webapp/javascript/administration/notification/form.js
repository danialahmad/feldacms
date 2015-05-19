$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	  $("#addFile").click(function(e){
		    e.preventDefault();
           $("#attachmentList").append('<span style="cursor: pointer; color:red;"  class="i-cancel-2"></span><br/><div class="uploader">'
        		   +'<input class="uni liveFile" type="file">'
        		   +'<span class="filename" style="-moz-user-select: none;">No file selected</span>'
        		   +'<span class="action" style="-moz-user-select: none;">Choose File</span>'
        		   +'</div>');
          var s= $("#attachmentList .uploader").length;
         $("#attachmentList .uploader").each(function(i){
        	 $(this).find('input').attr('name','files['+i+']');
         });
          
      
            
	   });
	 $(".liveFile").live('change',function(e){
		 e.preventDefault();
		var filename = $(this).val().replace(/.*(\/|\\)/, '');
		$(this).parent().find('.filename').text(filename);
	 });
	 $(".i-cancel-2").live('click',function(){
		var prev= $(this).prev();
		var next= $(this).next();
		prev.remove();
		next.remove();
		$(this).remove();
		 $("#attachmentList .uploader").each(function(i){
        	 $(this).find('input').attr('name','files['+i+']');
         });
	 });
	 
	 $('#remarkBlock').hide();
	 $("#ticketGroup").trigger("change");
	 $("#ticketGroup").bind("change",function(){
		 loadComboOptions(this, "complaint/request/list/ticketCategory/"+$(this).val(), "ticketCategory");
		 
		 var txt=$("#ticketGroup option:selected").text();
		 if(txt=='OTHERS'){
			 $('#remarkBlock').show();
			 $('#catBlock').hide();
		 }else{
			 $('#remarkBlock').hide();
			 $('#catBlock').show();
		 }
		 
		});
	
	 
	 
	 $('#validate').ajaxForm({
			
		    url:contextPath+"administration/notification/template/save",
		    beforeSubmit : function(formData, jqForm, options) {
				$("#modal-waiting").modal("show");
			},
		    success: showResponse,
	    	error:    function(data) { 
	    		alert("Error");
	    		$("#modal-waiting").modal("hide");
	    		
		  
	    	} ,
	    	
	    });
		
		function showResponse(responseText, statusText, xhr, $form) {
	        if(statusText != 'success'){
	        	alert("Error")
			} else if(statusText == 'success'){
	        //	alert("success");
	            window.location=contextPath+'success?alert=alert.success.lodge&param='+contextPath+'administration/notification/template';
			}
		}
		
		
	 
	 
	
});