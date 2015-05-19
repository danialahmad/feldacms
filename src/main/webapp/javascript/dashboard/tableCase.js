$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();
    var url="";
	
	
	$(".dashClick").click(function(e){
		//location.href=contextPath+"case/activity/add/"+$("#id").val();
		url= $(this).attr('url');
		$("#caseModal").removeData ('modal');
		$("#caseModal").modal({
			show:true
			}).css({
			
			'width': '800px',
		    'margin-left': function () 
					{
					  return -($(this).width() / 2);
					}
				
		});
		
		
	});
			
	
	
	$('#caseModal').on('shown.bs.modal', function (e) {
	
		
	
		var dataTableOption = $("#caseInfoTable").dataTable({
			"sPaginationType": "full_numbers",
	        "bProcessing": false,
			"bDestroy" : true,
	        "bServerSide": true,
	        "bSort": false,
	        "bFilter": false,
	        "sAjaxSource": contextroot+url,
	        "aoColumns": [
	             { "mData" : "no" ,"sClass":"center" },
	             { "mData" : "id" ,"sClass":"center" },
	             { "mData" : "title" },
	             { "mData" : "createDate" ,"sClass":"center" },
	             { "mData" : "priority" ,"sClass":"center"},
	             { "mData" : "status" ,"sClass":"center"}
	           ],
	           "aoColumnDefs": [
	                            {
	                                "bUseRendered": false,
	                                "fnRender" : function ( oObj ) {
	                               	return '<a href="'+contextroot+'case/assignment/edit/'+oObj.aData.id+'">'+oObj.aData.id+'</a>';
	                                },
	                                                         
	                                "aTargets": [ 1 ]                                  
	                            }, 
	                            {
	                                "bUseRendered": false,
	                                "fnRender" : function ( oObj ) {
	                                	var priority="";
	                                	if(oObj.aData.priority=="LOW"){
	                                		priority="<span class='label label-success'>"+oObj.aData.priority+"</span>";
	                                	}else if(oObj.aData.priority=="MEDIUM"){
	                                		priority="<span class='label label-info'>"+oObj.aData.priority+"</span>";
	                                	}else if(oObj.aData.priority=="HIGH"){
	                                		priority="<span class='label label-warning'>"+oObj.aData.priority+"</span>";
	                                	}else if(oObj.aData.priority=="CRITICAL"){
	                                		priority="<span class='label label-important'>"+oObj.aData.priority+"</span>";
	                                	}
	                               	return priority;
	                                },
	                                                         
	                                "aTargets": [ 4 ]                                  
	                            }
	           ],
			"fnServerParams" : function(aoData) {
				if($("#room-id").val()!=null){
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
	        	$('td:eq(0)',nRow).html(index);
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
	
			
		
		
});