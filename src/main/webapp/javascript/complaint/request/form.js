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
	
	 
	 
	 
	 

	 
	 function hasExtension(element, exts) {
		    var fileName = element.val();
		    return (new RegExp('(' + exts.join('|').replace(/\./g, '\\.') + ')$')).test(fileName);
     }
	 
	 

	
	   
	  
	
	  
	  
	 $('#validate').validate({
		 rules: {
             ticketTitle: {
                 minlength: 2,
                 required: true
             },
             description: {
                 required: true
             },
             "category.id": {
                 required: true
             }
	    },
        messages: {
        	ticketTitle: {required:"Sila masukkan Tajuk Aduan",minlength:"Minimum 2 character"},
        	description: {required:"Sila masukkan Keterangan"},
        	"category.id": {required:"Sila masukkan Kategori Aduan/Cadangan"}
            
        },
        errorElement: "span", // contain the error msg in a span tag
        errorClass: 'help-block',
        errorPlacement: function (error, element) { // render error placement for each input type
            if (element.attr("type") == "radio" || element.attr("type") == "checkbox") { // for chosen elements, need to insert the error after the chosen container
                error.insertAfter($(element).closest('.form-group').children('div').children().last());
            } else if (element.attr("name") == "dd" || element.attr("name") == "mm" || element.attr("name") == "yyyy") {
                error.insertAfter($(element).closest('.form-group').children('div'));
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
        			
        		    url:contextPath+"complaint/request/save",
        		    beforeSubmit : function(formData, jqForm, options) {
        				//$("#modal-waiting").modal("show");
        			     block_loading(bC);
        			},
        		    success: function(responseText, statusText, xhr, $form){
        		    	 block_loading(bC);
        		    	if(statusText != 'success'){
        		        	alert("Error");
        				} else if(statusText == 'success'){
        		            window.location=contextPath+'success?alert=alert.success.lodge&param='+contextPath+'complaint/request/mycomplaints';
        				}
        		    },
        	    	error:    function(data) { 
        	    		alert("Error");
        	    		$("#modal-waiting").modal("hide");
        	    		
        		  
        	    	} ,
        	    	
        	    });
        }
	 });	
	 
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
		
	
	            	//accept:  "Format fail yang dibenarkan adalah : jpg, img, gif, pdf, xls, doc, dan docx"
	           
	 
	
});