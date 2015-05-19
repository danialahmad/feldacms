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
	 
	
	 $("#region").trigger("change");
	 $("#region").bind("change",function(){
		 loadComboOptions(this, "list/plan/"+$(this).val(), "plan");
		});
	 
	 
	 $(".felda").hide();
	 $(".staffB").hide();
	 $("#personCategory").trigger("change");
	 
	 var val=$("#personCategory").val();
	 if(val==3){
			$(".felda").hide();
			 $(".staffB").show();
			 $("#plan").val(null);
			 $("#region").val(null);
			 $("#settlerNo").val(null);
			 $('#regionBlock').hide();
		}else if(val==4){
			$(".felda").show();
			 $(".staffB").hide();
			 $("#group").val(null);
			 $("#regionStaff").val(null);
			 $("#staffNo").val(null);
			
		}else{
			$(".felda").hide();
			 $(".staffB").hide();
			 $("#plan").val(null);
			 $("#region").val(null);
			 $("#settlerNo").val(null);
			 $("#group").val(null);
			 $("#regionStaff").val(null);
			 $("#staffNo").val(null);
		}
	 
	 $("#personCategory").bind("change",function(){
		var val=$(this).val();
		if(val==3){
			$(".felda").hide();
			 $(".staffB").show();
			 $("#plan").val(null);
			 $("#region").val(null);
			 $("#settlerNo").val(null);
			 $('#regionBlock').hide();
		}else if(val==4){
			$(".felda").show();
			 $(".staffB").hide();
			 $("#group").val(null);
			 $("#regionStaff").val(null);
			 $("#staffNo").val(null);
			
		}else{
			$(".felda").hide();
			 $(".staffB").hide();
			 $("#plan").val(null);
			 $("#region").val(null);
			 $("#settlerNo").val(null);
			 $("#group").val(null);
			 $("#regionStaff").val(null);
			 $("#staffNo").val(null);
		}
	 });	
	 
	 $('#regionBlock').hide();
	 $("#group").trigger("change");
	 var txt=$("#group option:selected").text();
	 if(txt=='PENGURUSAN WILAYAH'){
		 $('#regionBlock').show();
		
	 }else{
		 $('#regionBlock').hide();
		 $("#regionStaff").val(null);
	 }
	 $("#group").bind("change",function(){
		// loadComboOptions(this, "case/list/profiles/"+$(this).val(), "assignee");
		 var txt=$("#group option:selected").text();
		 if(txt=='PENGURUSAN WILAYAH'){
			 $('#regionBlock').show();
			
		 }else{
			 $('#regionBlock').hide();
			 $("#regionStaff").val(null);
		 }
		 
		});
	 
	 
	 
	 $('#register').validate({
		 rules: {
			name: {
                 
                 required: true
             },
           "personCategory.id": {
                 required: true
             },
             icNo: {
                 
                 required: true
             },
             username: {
                 
                 required: true
             },
             "region.id": {
                 
                 required: true
             },
             "plan.id": {
                 
                 required: true
             },
             "role.id": {
                 
                 required: true
             },
             email: {
                 
                 required: true
             },
             mobileNo: {
                 
                 required: true
             }
             
	    },
        messages: {
        	name: {required:"Sila masukkan Nama anda"},
        	"personCategory.id": {required:"Sila masukkan Kategori Pengguna"},
        	icNo: {required:"Sila masukkan No. Kad Pengenalan/No. Pendaftaran anda"},
        	c_password: {equalTo:"Sila padankan Kata Laluan"},
        	"region.id": {required:"Sila masukkan Wilayah"},
        	"plan.id": {required:"Sila masukkan Tanah Rancangan"},
        	"role.id": {required:"Sila masukkan Role"},
        	email: {required:"Sila masukkan Email"},
        	mobileNo: {required:"Sila masukkan No Telefon Bimbit"}
        	
            
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
            $(form).ajaxSubmit({
    			
    		    url:$(this).attr('action'),
    		    beforeSubmit : function(formData, jqForm, options) {
    				//$("#modal-waiting").modal("show");
    			},
    		    success: function(data){
    		    	if(data=="Success"){
    		    	window.location=contextPath+'administration/um/user';
    		    	}else{
    		    		alert(data);
    		    	}
    		    },
    	    	error:    function(data) { 
    	    		alert("Error");
    	    	//	$("#registerModal").modal("hide");
    	    		
    		  
    	    	} ,
    	    	
    	    }); 
        }
	 });
	 
	 
	
	    
	    
	    $("#resetPassword").click(function(){
	    	$("#confirm").dialog("open");
	    });
	    
	    function resetPassword(){
	    	 $.get(contextPath+"administration/um/user/reset/"+$("#username").val(),function(data){
    	    		if(data=="success"){
    	    			 open_dialog("sent");
    	    			 $("#sent").dialog("open");
    	    		}else{
    	    			open_dialog("failed");
    	    			$("#failed").dialog("open");
    	    		}
 	    	   });
	    }
	    
	    $("#confirm").dialog({
	           autoOpen: false,
	           resizable: false,        
	           modal: true,
	           buttons: {
	               "Reset": function() {
	            	   resetPassword();
	                   $(this).dialog("close");
	               },
	               Cancel: function() {
	                   $(this).dialog("close");
	               }
	           }
	       });
	    
	    function open_dialog(id){
	    	$("#"+id).dialog({
		           autoOpen: false,
		           resizable: false,        
		           modal: true,
		           buttons: {
		               "OK": function() {
		            	  $(this).dialog("close");
		               }
		           }
		       });
	    }
	    
	    
});