$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	var url="";
	
	
	$("#btn-open-setting").click(function(e){
		//location.href=contextPath+"case/activity/add/"+$("#id").val();
		$("#activityModal").removeData ('modal');
		$("#activityModal").modal({
			show:true,
			remote:contextPath+"case/activity/add/"+$("#id").val()+"/"+$("#assignmentId").val()+"#viewComplaint"
		}).css({
			
			'width': '800px',
		    'margin-left': function () 
					{
					  return -($(this).width() / 2);
					}
				
		});
		
		
	});
	$(".modalAct").live('click',function(e){
		e.preventDefault();
		$("#activityModal").removeData ('modal');
		$("#activityModal").modal({
			show:true,
			remote:$(this).attr("href")
		}).css({
			
			'width': '800px',
		    'margin-left': function () 
					{
					  return -($(this).width() / 2);
					}
				
		});
		
	});
 
	$('#activityModal').on('hidden.bs.modal', function (e) {
		$(this).removeData('modal');
	});
	
	jQuery("#btnClose").live('click',function(e){
		e.preventDefault();
		$('#activityModal').modal('toggle');
	});
	
	
	
	$('#activityModal').on('shown.bs.modal', function (e) {
		var url=""; 
		
		if($("#action").val()=="add"){
			 url=contextPath+"case/activity/save";
		 }
		 if($("#action").val()=="edit"){
			 url=contextPath+"case/activity/update";
		 }
		 
		 var actType=$("#activityType").val();
		 if(actType=="2"){
			 $('.meeting').show();
		 }else{
			 $('.meeting').hide();
		 }
		 if(actType=="3"){
			 $('.commonBlock').hide();
			 $('.emailBlock').show();
			 if($("#mail_wysiwyg").length > 0)
		            m_editor = $("#mail_wysiwyg").cleditor({width:"100%", height:"100%",controls:"bold italic underline strikethrough | font size style | color highlight removeformat | bullets numbering | outdent alignleft center alignright justify"})[0].focus();
			    m_editor.refresh(); 
		 }
		 if(actType==""){
			 $('.commonBlock').hide();
			 $('.emailBlock').hide();
			 $('.meeting').hide();
			 $('.desc').hide();
			 $("#sbt").attr("disabled", true);
		 }
		 else{
			 $('.commonBlock').show();
			 $('.emailBlock').hide();
		 }
		    if($(".datetimepicker").length > 0)
		    	$('.datetimepicker').datetimepicker({ 
		    	    dateFormat: 'dd/mm/yy',
		    	    timeFormat: 'HH:mm'
		    	});
		    
		    
		    $("#activityType").bind("change",function(){
				 var val=$(this).val();
				 $('.desc').show();
				 $("#sbt").removeAttr("disabled");
				 if(val=="2"){
					 $('.meeting').show();
				 }else{
					 $('.meeting').hide();
				 }
				 if(val=="3"){
					 $('.commonBlock').hide();
					 $('.emailBlock').show();
					 if($("#mail_wysiwyg").length > 0)
				            m_editor = $("#mail_wysiwyg").cleditor({width:"100%", height:"100%",controls:"bold italic underline strikethrough | font size style | color highlight removeformat | bullets numbering | outdent alignleft center alignright justify"})[0].focus();
					    m_editor.refresh(); 
				 }if(val==""){
					 $('.commonBlock').hide();
					 $('.emailBlock').hide();
					 $('.meeting').hide();
					 $('.desc').hide();
					 $("#sbt").attr("disabled", true);
				 }
				 else{
					 $('.commonBlock').show();
					 $('.emailBlock').hide();
				 }
				 
			 });
		    
		   
		    
		    if($(".select2").length > 0)
		        $(".select2").select2();
		    
		    
		    
		    
		    
		    
		    $('#viewActivity').validate({
		    	 rules: {
			        	activityTypeId: {
				                required: true
				            },
				            agenda: {
				                required: true
				            },
				            location: {
				                required: true
				            },
				            startTime: {
				                required: true
				            },
				            endTime: {
				                required: true
				            },
				            description: {
				                required: true
				            }
			        },
			        messages: {
			        	activityTypeId: {
				                required: "Sila Masukkan Jenis Tindakan"
				            },
				            agenda: {
				                required: "Sila Masukkan Agenda"
				            },
				            location: {
				                required: "Sila Masukkan Lokasi"
				            },
				            startTime: {
				                required: "Sila Masukkan Tarikh/Masa Mula"
				            },
				            endTime: {
				                required: "Sila Masukkan Tarikh/Masa Tamat"
				            },
				            description: {
				                required: "Sila Masukkan Keterangan"
				            }
			        },
		        errorElement: "span", // contain the error msg in a span tag
		        errorClass: 'help-block',
		        errorPlacement: function (error, element) { // render error placement for each input type
		           
		                error.insertAfter(element);
		           
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
						
					    url:url,
					    beforeSubmit : function(formData, jqForm, options) {
							//$("#modal-waiting").modal("show");
						},
					    success: function(data){
					    	dataTableOption.fnDraw();
					    	$("#activityModal").modal('hide');
					    },
				    	error:    function(data) { 
				    		alert("Error");
				    		$("#modal-waiting").modal("hide");
				    		
					  
				    	} ,
				    	
				    });
		        }
		    });
		    
		    
	 
	});
	
	
	function showResponse(responseText, statusText, xhr, $form) {
	        if(statusText != 'success'){
	        	alert("Error")
			} else if(statusText == 'success'){
	        	//alert("success");
	        	window.location=contextPath+'case/edit/'+$("#ticketId").val()+'#list-activities';
			}
		}
		
		
	var dataTableOption = $("#list-activities").dataTable({
		"sPaginationType": "full_numbers",
        "bProcessing": true,
		"bDestroy" : true,
        "bServerSide": true,
        "bSort": false,
        "bFilter": false,
        "sAjaxSource": contextPath+"case/list/activities/"+$("#assignmentId").val(),
        "aoColumns": [
             { "mData" : "no" ,"sClass":"center"},
             { "mData" : "activityType" ,"sClass":"center"},
             { "mData" : "date" ,"sClass":"center" },
             { "mData" : "description","sClass":"center"},
            
           ],
         
		"fnServerParams" : function(aoData) {
			if($("#idx").val()!=null){
				aoData.push(
					{ "name": "id", "value" : $("#id").val() }
				);
			}
		},
        "fnServerData": function ( sSource, aoData, fnCallback ) {
            $.ajax({
                "dataType": "json",
                "type": "GET",
                "url": sSource,
                "data": aoData,
                "success": fnCallback,
            });
        },
        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
        	var numStart =  this.fnPagingInfo().iStart;
            var index = numStart +  iDisplayIndexFull + 1;
            var html='<a class="modalAct" href="'+contextPath+'case/activity/view/'+aData.id+'">'+index+'</a>';
        	$('td:eq(0)',nRow).html(html);
    	}
	});
	$("#search-form").on("submit", function(e){
		e.preventDefault();
		dataTableOption.fnDraw();
    	return false;
	});
	$("#button-search").click(function(e){
		dataTableOption.fnDraw();
		$(".all-area").hide();
		$("#list-area").show();
    });
	
	$("#button-open-search").click(function(e){
		dataTableOption.fnDraw();
		$(".all-area").hide();
		$("#search-area").show();
    });	 
	 
	
});