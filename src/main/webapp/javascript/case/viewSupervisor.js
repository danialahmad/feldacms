$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	
	 
	 
	 $("#ticketGroup").trigger("change");
	 $("#ticketGroup").bind("change",function(){
		 loadComboOptions(this, "complaint/request/list/ticketCategory/"+$(this).val(), "ticketCategory");
		});
	
	 $("#group").trigger("change");
	 $("#group").bind("change",function(){
		 loadComboOptions(this, "complaint/administration/list/profiles/"+$(this).val(), "assignee");
		});
	 
	 
	 $('#viewComplaint').ajaxForm({
			
		    url:contextPath+"complaint/request/update",
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
	        	
	        	window.location=contextPath+'success?alert=alert.success.rating&param='+contextPath+'complaint/request/mycomplaints';
			}
		}
		
		
	 
	 
	
});