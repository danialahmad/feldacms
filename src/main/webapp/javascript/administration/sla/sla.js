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
             { "mData" : "no","sClass": "center"},
             { "mData" : "title" },
             { "mData" : "description" },
             { "mData" : "createBy" },
             { "mData" : "createDate" },
             { "mData" : "updateBy" },
             { "mData" : "updateDate" },
             { "mData" : "id"}
           ],
           "aoColumnDefs": [
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return '<a href="'+contextPath+'administration/sla/setting/edit/'+oObj.aData.id+'">'+oObj.aData.title+'</a>';
                                },
                                                         
                                "aTargets": [ 1 ]                                  
                            },
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return '<button class="btn btn-mini btn-danger btn-block remove" href="'+contextPath+'administration/sla/delete/'+oObj.aData.id+'">Remove</button>';
                                },
                                                         
                                "aTargets": [ 7 ]                                  
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
	
	$("#btn-open-setting").click(function(e){
		location.href=contextPath+"administration/sla/setting/new";
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