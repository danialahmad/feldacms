$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	
	
	 $("#ticketGroup").trigger("change");
	 $("#ticketGroup").bind("change",function(){
		 loadComboOptions(this, "complaint/request/list/ticketCategory/"+$(this).val(), "ticketCategory");
		});
	
	
	var dataTableOption = $("#list-meeting-room").dataTable({
		"sPaginationType": "full_numbers",
        "bProcessing": true,
		"bDestroy" : true,
        "bServerSide": true,
        "bSort": false,
        "bFilter": false,
        "sAjaxSource": $("#search-form").attr("action"),
        "aoColumns": [
             { "mData" : "no" ,"sClass":"center"},
             { "mData" : "id" ,"sClass":"center" },
             { "mData" : "title" },
             { "mData" : "createDate" ,"sClass":"center" },
         
             { "mData" : "priority","sClass":"center"},
             { "mData" : "status","sClass":"center"},
             { "mData" : "id" ,"sClass":"center"}
           ],
           "aoColumnDefs": [
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return '<a href="'+contextPath+'case/edit/'+oObj.aData.id+'">'+oObj.aData.id+'</a>';
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
                            },
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return '<a class="logClick" href="'+contextPath+'eventLog/'+oObj.aData.id+'"><i class="i-cabinet"></i></a>';
                                },
                                                         
                                "aTargets": [ 6 ]                                  
                            }
           ],
		"fnServerParams" : function(aoData) {
			
				aoData.push(
					{ "name": "id", "value" : $("#id").val() },
					{ "name": "ticketTitle", "value" : $("#ticketTitle").val() },
					{ "name": "ticketGroupId", "value" : $("#ticketGroup").val() },
					{ "name": "ticketCategoryId", "value" : $("#ticketCategory").val() },
					{ "name": "phoneNo", "value" : $("#phoneNo").val() },
					{ "name": "icNo", "value" : $("#icNo").val() },
					{ "name": "status", "value" : $("#status").val() }
					
				);
			
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
	
	$("#buttonx").click(function(e){
		e.preventDefault();
		$(':input','#search-form')
		 .not(':button, :submit, :reset, :hidden')
		 .val('')
		 .removeAttr('checked')
		 .removeAttr('selected');
		dataTableOption.fnDraw();
    });
	
	$("#button-open-search").click(function(e){
		dataTableOption.fnDraw();
		$(".all-area").hide();
		$("#search-area").show();
    });
});