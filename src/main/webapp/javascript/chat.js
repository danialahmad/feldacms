$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	
$('#chatPost').click(function(e){
	e.preventDefault();
	 
	$.ajax({
		url:contextPath+"chat/save",
		method : "POST",
		data :[{"name": "ticketId", "value" : $("#id").val() },{"name": "message", "value" : $("#note").val() }],
	    success: function(data) { 
	    	$.get(contextPath+'chat/'+$("#id").val(), function(data) {
				var arr = new Array();
				for ( var i = 0; i < data.length; i++) {
					var row =generateRow(data[i]);
					arr.push(row);
				}
				$("#chatContent").html(arr.join(""));
				$(".chat_wysiwyg").val('').blur();
				$("#note").val('').blur();
			});
	    },
		error:    function(data) { 
			alert("Error");
			//$("#modal-waiting").modal("hide");
			
	  
		} ,
	});
	
});
		
$('#chat').ajaxForm({
			
		    url:contextPath+"chat/save",
		    beforeSubmit : function(formData, jqForm, options) {
			//	$("#modal-waiting").modal("show");
			},
		    success: function(data) { 
		    	$.get(contextPath+'chat/'+$("#id").val(), function(data) {
					var arr = new Array();
					for ( var i = 0; i < data.length; i++) {
						var row =generateRow(data[i]);
						arr.push(row);
					}
					$("#chatContent").html(arr.join(""));
					$(".chat_wysiwyg").val('').blur();
				});
		    },
	    	error:    function(data) { 
	    		alert("Error");
	    		//$("#modal-waiting").modal("hide");
	    		
		  
	    	} ,
	    	
	    });
		
		$.get(contextPath+'chat/'+$("#id").val(), function(data) {
			var arr = new Array();
			for ( var i = 0; i < data.length; i++) {
				var row =generateRow(data[i]);
				arr.push(row);
			}
			$("#chatContent").html(arr.join(""));
		});
		
		
		function generateRow(data){
			var cDate= data.date.split(" ");
			
			
			var row ='<div class="item">'+
            '<div class="img">'+
               ' <img src="'+data.img+'" class="img-polaroid"/>'+
            '</div>'+
            '<div class="info">'+
                '<a href="#" class="name">'+data.createBy+'</a><br/> <span class="muted">'+cDate+'</span>'+
                '<div class="text">'+
                data.msg+
                '</div>'+
            '</div>'+
        '<button class="close" data-dismiss="alert" type="button">×</button></div>';
			return row;
		}	
		
		var c_editor=[];
		 /* Msg */
        if($(".chat_wysiwyg").length > 0){
            c_editor = $(".chat_wysiwyg").cleditor({
            	width:"100%", 
            	height:"100%",
            	 bodyStyle: "margin:4px; font:10pt Arial,Verdana; cursor:text; word-wrap:break-word;",
            	controls:"bold italic underline strikethrough | font size style | color highlight removeformat | bullets numbering | outdent alignleft center alignright justify"
            		
            });
            c_editor[0].focus();
        }
        $('#uploadPic').click(function (e) {
           // m_editor.refresh();   
        	e.preventDefault();
        	//console.log('exec');
        //	c_editor[0].execCommand('inserthtml','Here some dynamic text');  
           $("#picinput").trigger('click');
        }); 
        $('#uploadFile').click(function (e) {
            // m_editor.refresh();   
         	e.preventDefault();
         	//console.log('exec');
         //	c_editor[0].execCommand('inserthtml','Here some dynamic text');  
            $("#fileinput").trigger('click');
         });
       
        function readURL(input) {
         
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                   // $('#blah').attr('src', e.target.result);
                	 var img=null;
                	img='<img src="'+e.target.result+'"></img>';
                	 c_editor[0].execCommand('inserthtml',img);
                	
                };

                reader.readAsDataURL(input.files[0]);
            }
        
           
        }
        
       var successPic= function success (msg) {
	    	var pic='<img src="'+contextPath+'chat/file?path='+msg.path+'"></img>';
	    //	$(".chat_wysiwyg").val(pic).blur();
       	   // c_editor[0].execCommand('insertimage',contextPath+'chat/file?path='+msg.path);
	    	var currentval = $(".chat_wysiwyg").val();
	    	$(".chat_wysiwyg").val(pic).blur();
	    	$(".chat_wysiwyg").val(currentval + pic).blur();
	    };
        function storeImage(input){
        	sendFile(input.files[0],contextPath+'chat/storeImage?name='+input.files[0].name,successPic);
        }
        
        $("#picinput").change(function(){
        	storeImage(this);
        	
        });
        
        var successFile= function success (msg) {
        	var html='<a href="'+contextPath+'chat/file?path='+msg.path+'">'+msg.name+'</a><br/>'
        //	$(".chat_wysiwyg").val(html).blur();
        	// c_editor[0].execCommand('inserthtml',html);
	    	//$("#fileList").append('<br/><a href="'+contextPath+'chat/file?path='+msg.path+'">'+msg.name+'</a>');
        	var currentval = $(".chat_wysiwyg").val();
	    	$(".chat_wysiwyg").val(html).blur();
	    	$(".chat_wysiwyg").val(currentval + html).blur();
	    };
        
	    function storeFile(input){
        	sendFile(input.files[0],contextPath+'chat/storeImage?name='+input.files[0].name,successFile);
        }
        $("#fileinput").change(function(){
        	storeFile(this);
        });
        
        /* EOF Msg */
        function sendFile(file,url,success) {
        
        	$.ajax({
        	    type: 'post',
        	    url: url,
        	    data: file,
        	    success: success,
        	    xhrFields: {
        	      // add listener to XMLHTTPRequest object directly for progress (jquery doesn't have this yet)
        	      onprogress: function (progress) {
        	        // calculate upload progress
        	        var percentage = Math.floor((progress.total / progress.totalSize) * 100);
        	        // log upload progress to console
        	        console.log('progress', percentage);
        	        if (percentage === 100) {
        	          console.log('DONE!');
        	        }
        	      }
        	    },
        	    processData: false,
        	    contentType: file.type
        	  });
        	
       
        }
	
});