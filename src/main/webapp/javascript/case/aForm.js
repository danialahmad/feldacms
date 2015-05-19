$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	
	 $("#komen").hide();
	 
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
	 
	
	
		$("#knowledge").click(function(e){
			e.preventDefault();
			window.location=contextPath+'case/knowledge/transfer?ticketTitle='+$("#ticketTitle").val()+'&description='+$("#description").val()+'&ticketGroupId='+$("#ticketGroup").val();
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
		
		
		
		
		 $('#viewComplaint').validate({
		        rules: {
		        	 statusId: {
			                required: true
			            },
		        	priorityId: {
		                required: true
		            },
			        group: {
			                required: true
			            }
		        },
		      
		        highlight: function (element) {
		           
		            $(element).closest('.controls-row').addClass('error');
		        },
		        unhighlight: function (element) { // revert the change done by hightlight
		        	 $(element).closest('.controls-row').removeClass('error');
		        }
		    });
		 $('#assignForm').validate({
		        rules: {
		          group: {
			                required: true
			            }
		        },
		        errorElement: "span", // contain the error msg in a span tag
		        errorClass: 'help-block',
		        errorPlacement: function (error, element) { // render error placement for each input type
		           
		                error.insertAfter('#inlineBtn');
		           
		        },
		        highlight: function (element) {
		           
		            $(element).closest('.controls-row').addClass('error');
		        },
		        unhighlight: function (element) { // revert the change done by hightlight
		        	 $(element).closest('.controls-row').removeClass('error');
		        }
		    });
		
		
		$('#submitBtn').click(function(e){
			e.preventDefault();
			var btn=$(this);
			confirmNoty(btn,"Anda pasti untuk kemaskini ?",submit);
		});
		
		
		function submit(btn){
			 var bC = $(btn).parents('.block').find('.content');
			 var empty=$("#list-activities tbody>tr>td").hasClass("dataTables_empty"); 
				if(empty){
					$("#errorTindakan").show();
					return false;
				}
				if($('#viewComplaint').valid()){
				      $.ajax({
							url:contextPath+"case/assignment/update",
							method : "POST",
							data :[{"name": "assigmentId", "value" : $("#assignmentId").val() },{"name": "statusId", "value" : $("#status").val() },{"name": "ticketGroupId", "value" : $("#ticketGroup").val() },{"name": "assigneeId", "value" : $("#assignee").val() },{"name": "message", "value" : $("#note").val() }],
							beforeSend : function(data){
								 block_loading(bC);
								
							},
							success: function(data) { 
								if(data.lastUpdate!=null){
						    		$("#lastUpdate").remove();
						    		$("#status").after('<span id="lastUpdate" class="help-inline"><span class="text-success">Kemaskini terakhir : '+data.lastUpdate+'</span></span>');
						    	} 
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
						    	block_loading(bC);
						    	noty({text: '<strong>Kemaskini berjaya</strong>', type: 'success',layout: 'topCenter',timeout:3000});
						    },
							error:    function(data) { 
								alert("Error");
								$("#group").val(null);
								block_loading(bC);
							} ,
						});
				}
		}
		
		var dataTableOption = $("#list-assignments").dataTable({
			"sPaginationType": "full_numbers",
	        "bProcessing": true,
			"bDestroy" : true,
	        "bServerSide": true,
	        "bSort": false,
	        "bFilter": false,
	        "sAjaxSource": contextPath+"case/list/assignments/"+$("#id").val(),
	        "aoColumns": [
	             { "mData" : "no" ,"sClass":"center"},
	             { "mData" : "group" ,"sClass":"center"},
	             { "mData" : "lo" ,"sClass":"center" },
	             { "mData" : "status","sClass":"center"},
	            
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
	            var html='<a class="modalAct" href="'+contextPath+'case/activity/edit/'+aData.id+'">'+index+'</a>';
	        	$('td:eq(0)',nRow).html(html);
	    	}
		});
		
		$('#assignBtn').click(function(e){
			e.preventDefault();
			 var bC = $(this).parents('.block').find('.content');
		       
		        if($('#assignForm').valid()){
		        	 block_loading(bC);
				 $.ajax({
						url:contextPath+"case/assignment/save",
						method : "POST",
						data :[{"name": "id", "value" : $("#id").val() },{"name": "group", "value" : $("#group").val() }],
					    success: function(data) { 
					    	dataTableOption.fnDraw();
					    	$("#group").val(null);
					    	block_loading(bC);
					    },
						error:    function(data) { 
							alert("Error");
							$("#group").val(null);
							block_loading(bC);
						} ,
					});
			 }
			 
			
		});
	
});