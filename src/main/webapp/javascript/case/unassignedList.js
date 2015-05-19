$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	
	var dataTableOption = $("#list-meeting-room").dataTable({
		"sPaginationType": "full_numbers",
        "bProcessing": true,
		"bDestroy" : true,
        "bServerSide": true,
        "bSort": false,
        "bFilter": false,
        "sAjaxSource": $("#search-form").attr("action"),
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
                               	return '<a href="'+contextPath+'case/assignment/edit/'+oObj.aData.id+'/'+oObj.aData.assignmentId+'">'+oObj.aData.id+'</a>';
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