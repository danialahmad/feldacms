$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	
	 
	 $('#validate').ajaxForm({
			
		    url:contextPath+"administration/notification/complaint/save",
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
	            window.location=contextPath+'success?alert=alert.success.lodge&param='+contextPath+'administration/notification/complaint';
			}
		}
		
		
	 
	 
	
});