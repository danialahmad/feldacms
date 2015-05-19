$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	
	 
	 
	 $('#remarkBlock').hide();
	 $('#regionBlock').hide();
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
	
	 $("#group").trigger("change");
	 $("#group").bind("change",function(){
		// loadComboOptions(this, "case/list/profiles/"+$(this).val(), "assignee");
		 var txt=$("#group option:selected").text();
		 if(txt=='PENGURUSAN WILAYAH'){
			 $('#regionBlock').show();
			
		 }else{
			 $('#regionBlock').hide();
			
		 }
		 
		});
	 
	 var from = document.referrer;
	 var bC =$('.block_loading').parents('.block').find('.content');
	 $('#viewComplaint').ajaxForm({
			
		    url:contextPath+"case/update",
		    beforeSubmit : function(formData, jqForm, options) {
				//$("#modal-waiting").modal("show");
		    	block_loading(bC);
			},
		    success: function(responseText, statusText, xhr, $form) {
		    	block_loading(bC);
		    	if(statusText != 'success'){
		        	alert("Error");
				} else if(statusText == 'success'){
		        	
		        	window.location=contextPath+'success?alert=alert.success.update&param='+from;
				}
		    }, 
	    	error:    function(data) { 
	    		alert("Error");
	    		$("#modal-waiting").modal("hide");
	    		
		  
	    	} ,
	    	
	    });
	
	
		$("#knowledge").click(function(e){
			e.preventDefault();
			window.location=contextPath+'case/knowledge/transfer?ticketTitle='+$("#ticketTitle").val()+'&description='+$("#description").val()+'&ticketGroupId='+$("#ticketGroup").val();
		});
	
});