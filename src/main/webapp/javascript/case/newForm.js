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
			console.log(prev.html());
			console.log(prev.attr('class'));
			
			if(prev.children().is(".text-error")){
				prev.prev().remove();
				prev.remove();
				
			}else{
				prev.remove();
			}
			
			
			next.remove();
			
			$(this).remove();
			 $("#attachmentList .uploader").each(function(i){
	        	 $(this).find('input').attr('name','files['+i+']');
	         });
		 });
	 
	 $("#country").trigger("change");
	 $("#country").bind("change",function(){
		 loadComboOptions(this, "case/helpdesk/list/state/"+$(this).val(), "state");
		});
	 
	 $("#ticketGroup").trigger("change");
	 $("#ticketGroup").bind("change",function(){
		 loadComboOptions(this, "complaint/request/list/ticketCategory/"+$(this).val(), "ticketCategory");
		});
	 $("#region").trigger("change");
	 $("#region").live("change",function(){
		 loadComboOptions(this, "list/plan/"+$(this).val(), "plan");
		});	
	 
	 $(".felda").hide();
	 $(".staffB").hide();
	 $(".relate").hide();
	 $("#personCategory").trigger("change");
	 $("#personCategory").live("change",function(){
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
	 
	 $("#relation").trigger("change");
	 $("#relation").live("change",function(){
			var val=$(this).val();
			if(val!=1){
				$(".felda").show();
				 $("#plan").val(null);
				 $("#region").val(null);
			}else{
				$(".felda").hide();
			}
		 });
	 
	 
	
	 
	 $('#newCase').validate({
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
             ticketTitle: {
                 minlength: 2,
                 required: true
             },
             description: {
                 required: true
             },
         
             originatorId: {
                 
                 required: true
             },
             "region.id": {
                 
                 required: true
             },
             "plan.id": {
                 
                 required: true
             },
"category.id": {
                 
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
        	icNo: {required:"Sila masukkan No. Kad Pengenalan/No. Pendaftaran anda"},
        	"personCategory.id": {required:"Sila masukkan Kategori Pengguna"},
        	ticketTitle: {required:"Sila masukkan Tajuk Aduan",minlength:"Minimum 2 character"},
        	description: {required:"Sila masukkan Keterangan"},
        	c_password: {equalTo:"Sila padankan Kata Laluan"},
        	"region.id": {required:"Sila masukkan Wilayah"},
        	"plan.id": {required:"Sila masukkan Tanah Rancangan"},
        	"category.id": {required:"Sila masukkan Kategori Aduan/Cadangan"},
        	originatorId: {required:"Sila masukkan Sumber"},
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
            } else {
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
        			
        		 url:contextPath+"case/helpdesk/save",
     		    beforeSubmit : function(formData, jqForm, options) {
     				
     			},
     		    success: showResponse,
     	    	error:    function(data) { 
     	    		alert("Error");
     	    		$("#modal-waiting").modal("hide");
     	    		
     		  
     	    	} ,
        	    	
        	    });
        }
	 });	
	 
	 
	
	 
	 function hasExtension(element, exts) {
		    var fileName = element.val();
		    return (new RegExp('(' + exts.join('|').replace(/\./g, '\\.') + ')$')).test(fileName);
  }
	 
	 
	 $('.uni').live('change',function() {
		 var res=true;
		 var con=4000000/1024/1024;
		 var file =  $(this)[0].files[0];
		 var ext=hasExtension($(this),['.jpg', '.gif', '.png','.pdf','.doc','.docx']);
		 
		if (!ext){
			res=false;
		}
		if(file.size/1024/1024>=con||file.fileSize/1024/1024>=con){
			res=false;
		}
		 
		if(res==false){
			$(this).val(null);
			$(this).next().text('No file selected');
			if($(this).parent().next().children().is('.text-error')){
				$(this).parent().next().remove();
			}
			$(this).parent().after('<span class="help-inline"><span class="text-error">Fail tidak dibenarkan</span></span>');
		}else{
		   $(this).parent().next().remove();
		}
	 });
	 
		function showResponse(responseText, statusText, xhr, $form) {
	        if(statusText != 'success'){
	        	alert("Error")
			} else if(statusText == 'success'){
				 window.location=contextPath+'success?alert=alert.success.lodge&param='+contextPath+'case/new';
			}
		}
		
		
	 
	 
	
});