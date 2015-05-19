$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();
	
	
	
	
	function readURL(input) {

	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	            $('#img-prev').attr('src', e.target.result);
	           
	        };

	        reader.readAsDataURL(input.files[0]);
	    }
	}
	 $("#file").change(function(){
		 readURL(this);
		 $('#imgPath').text(document.getElementById("file").value);
	});	
	
	
	
	/* jQuery ValidationEngine */    
    if($("#register").length > 0)
       // $("#register").validationEngine('attach',{promptPosition : "inline"}); 
    	
    /* EOF ValidationEngine */
			
	 $("#country").trigger("change");
	 $("#country").change(function(){
		 loadComboOptions(this, "list/state/"+$(this).val(), "state");
		});	
	 
	 $("#region").trigger("change");
	 $("#region").change(function(){
		 loadComboOptions(this, "list/plan/"+$(this).val(), "plan");
		});	
	 
	 
	 $(".felda").hide();
	 $(".staffB").hide();
	 $(".relate").hide();
	 $("#personCategory").trigger("change");
	 $("#relation").trigger("change");
	 
	 $("#relation").change(function(){
		 
			var val=$(this).val();
			if(val!=1){
				$(".felda").show();
				 $("#plan").val(null);
				 $("#region").val(null);
			}else{
				$(".felda").hide();
			}
		 });	
	 
	 $("#personCategory").change(function(){
		 
		var val=$(this).val();
		if(val==3){
			 $(".staffB").show();
			 $(".relate").hide();
			 $("#relation").val(null);
		}if(val==1){
			 $(".relate").show();
			 $(".staffB").hide();
		}else{
			 $(".staffB").hide();
			 $(".relate").hide();
			 $("#relation").val(null);
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
	             password: {
	                 
	                 required: true
	             },
	             c_password: {
	                 
	                 equalTo: "#password"
	             },
	             email: {
	                 
	                 required: true
	             },
	             "region.id": {
	                 
	                 required: true
	             },
	             "plan.id": {
	                 
	                 required: true
	             }
	             
		    },
	        messages: {
	        	name: {required:"Sila masukkan Nama anda"},
	        	icNo: {required:"Sila masukkan No. Kad Pengenalan/No. Pendaftaran anda"},
	        	"personCategory.id": {required:"Sila masukkan Kategori Pengguna"},
	        	username: {required:"Sila masukkan ID Pengguna anda"},
	        	password: {required:"Sila masukkan Kata Laluan anda"},
	        	c_password: {equalTo:"Sila padankan Kata Laluan"},
	        	email: {required:"Sila masukkan Email anda"},
	        	"region.id": {required:"Sila masukkan Wilayah"},
	        	"plan.id": {required:"Sila masukkan Tanah Rancangan"}
	            
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
	      	    		//$("#registerModal").modal("hide");
	      	    		
	      		  
	      	    	} ,
	        	    	
	        	    });
	        }
		 });	
	    
	    
	    
	    
	    $("#closeBtn").click(function(e){
	    	e.preventDefault();
	    	window.location=contextroot+"login";
	    });
			
});