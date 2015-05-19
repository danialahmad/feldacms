$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	
	 $("#ticketGroup").trigger("change");
	 $("#ticketGroup").bind("change",function(){
		 loadComboOptionsWithDefaultLabel("ALL",this, "complaint/request/list/ticketCategory/"+$(this).val(), "ticketCategory");
		});
	
	 
	 
	 $('form').ajaxForm({
			
		    url:contextPath+'administration/sla/save',
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
	        	alert("success");
	        	//window.location=contextPath+'booking/list';
			}
		}
		
		
	 
	 
	
});