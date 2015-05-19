$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	
	
	
	
	
	/* jQuery ValidationEngine */    
    if($("#register").length > 0){
        $("#register").validationEngine('attach',{promptPosition : "inline"}); 
}
    /* EOF ValidationEngine */
			
	 $("#country").trigger("change");
	 $("#country").bind("change",function(){
		
		 loadComboOptions(this, "list/state/"+$(this).val(), "state");
		});	
	 
	 
	
	 var from = window.location;
	 
	 
	    
	    $('#register').validate({
			 rules: {
				
	             password: {
	                 
	                 required: true
	             },
	             c_password: {
	                 
	                 equalTo: "#password"
	             },
	             currPassword: {
	                 
	                 required: true
	             }
	           
	             
		    },
	        messages: {
	        	
	        	password: {required:"Sila masukkan Kata Laluan baru"},
	        	c_password: {equalTo:"Sila padankan Kata Laluan"},
	        	currPassword: {required:"Sila masukkan Kata Laluan Semasa"}
	        	
	            
	        },
	        errorElement: "span", // contain the error msg in a span tag
	        errorClass: 'help-block',
	        errorPlacement: function (error, element) { // render error placement for each input type
	            if (element.attr("type") == "radio" || element.attr("type") == "checkbox") { // for chosen elements, need to insert the error after the chosen container
	                error.insertAfter($(element).closest('.form-group').children('div').children().last());
	            } else if (element.attr("name") == "dd" || element.attr("name") == "mm" || element.attr("name") == "yyyy") {
	                error.insertAfter($(element).closest('.form-group').children('div'));
	            } 
	            else if(element.next().attr('class')=="help-inline"){
	            	 error.insertAfter(element.next());
	            }
	            else {
	                error.insertAfter(element);
	                // for other inputs, just perform default behavior
	            }
	            
	        },
	        highlight: function (element) {
	           
	            $(element).closest('.controls-row').addClass('error');
	        },
	        unhighlight: function (element) { // revert the change done by hightlight
	        	 $(element).closest('.controls-row').removeClass('error');
	        },
		    success: function (label, element) {
	        },
	        submitHandler: function (form) {
	        	 var bC =$('.block_loading').parents('.block').find('.content');
	        	 
	        	 $(form).ajaxSubmit({
	        			
	        		   url:$(this).attr('action'),
	       		    beforeSubmit : function(formData, jqForm, options) {
	       				//$("#modal-waiting").modal("show");
	       			},
	       		    success: function(data){
	       		    	if(data.success==true){
	       		    		window.location=contextPath+'success?alert=profile.success.update&param='+from;
	       		    	}else{
	       		    		alert(data.message);
	       		    	}
	       		    	//	
	       		    	
	       		    },
	       	    	error:    function(data) { 
	       	    		alert("Error");
	       	    		$("#registerModal").modal("hide");
	       	    		
	       		  
	       	    	} 
	        	    	
	        	    });
	        }
		 });	
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    $("#closeBtn").click(function(e){
	    	e.preventDefault();
	    	window.location=contextPath;
	    });
});