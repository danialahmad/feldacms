$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	
	 dataTableOption = $("#userTable").dataTable({
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
             { "mData" : "name"},
             { "mData" : "createBy" },
             { "mData" : "createDate" },
             { "mData" : "id" }
         
           
           ],
           "aoColumnDefs": [
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                                	return '<a href="'+contextPath+'administration/notification/template/edit/'+oObj.aData.id+'">'+oObj.aData.id+'</a>';
                                },
                                                         
                                "aTargets": [ 1 ]                                  
                            },
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return '<button class="btn btn-mini btn-danger btn-block remove" href="'+contextPath+'administration/notification/template/delete/'+oObj.aData.id+'">Remove</button>';
                                },
                                                         
                                "aTargets": [ 5 ]                                  
                            }
           ],
		"fnServerParams" : function(aoData) {
			//if($("#username").val()!=null){
				aoData.push(
					{"name": "name", "value" : $("#name").val() }
				);
			//}
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
	$("#btn-search").click(function(e){
		$("#search-form").submit();
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
	
	$("#btn-open-setting").click(function(e){
		e.preventDefault();
		location.href=contextPath+"administration/notification/template/add";
		
		
	});
	
	//remove row
    $(".remove").live("click",function(){
        
    	delUrl=$(this).attr("href");
    	 rRow = $(this).parent().parent();
          $("#row_delete").dialog("open");
       });
       function remove_row(row){
          $.get(delUrl,function(data){
        	  
          });
          
          dataTableOption.fnDeleteRow(row.index(),null,true);
         
       }
       $("#row_delete").dialog({
           autoOpen: false,
           resizable: false,        
           modal: true,
           buttons: {
               "Delete": function() {
                   remove_row(rRow);
                   $(this).dialog("close");
               },
               Cancel: function() {
                   $(this).dialog("close");
               }
           }
       });
	
	
	
});