$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();
	
	/* jQuery ValidationEngine */    
    if($("#register").length > 0)
       // $("#register").validationEngine('attach',{promptPosition : "inline"}); 
    	
    /* EOF ValidationEngine */
			
	 $("#country").trigger("change");
	 $("#country").live("change",function(){
		 loadComboOptions(this, "list/state/"+$(this).val(), "state");
		});	
	 
	 $("#region").trigger("change");
	 $("#region").live("change",function(){
		 loadComboOptions(this, "list/plan/"+$(this).val(), "plan");
		});	
	 
	 
	 $(".felda").hide();
	 $(".staffB").hide();
	 $("#personCategory").trigger("change");
	 $("#personCategory").live("change",function(){
		 
		var val=$(this).val();
		if(val==3){
			$(".felda").hide();
			 $(".staffB").show();
		}else if(val==4){
			$(".felda").show();
			 $(".staffB").hide();
			 $("#plan").val(null);
			 $("#region").val(null);
		}else{
			$(".felda").hide();
			 $(".staffB").hide();
		}
	 });	
	 
	    $('#register').ajaxForm({
			
		    url:contextroot+"registration/save",
		    beforeSubmit : function(formData, jqForm, options) {
				//$("#modal-waiting").modal("show");
		    	
		    	
		    	
			},
		    success: function(data){
		    	if(data=="Success"){
		    	window.location=contextroot+'success?alert=alert.success.registration&param=login';
		    	}else{
		    		alert(data);
		    	}
		    },
	    	error:    function(data) { 
	    		alert("Error");
	    		$("#registerModal").modal("hide");
	    		
		  
	    	} ,
	    	
	    }); 
			
});