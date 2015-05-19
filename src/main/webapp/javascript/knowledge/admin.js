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
             { "mData" : "title"},
             { "mData" : "ticketGroup"},
             { "mData" : "staffOnly"},
             { "mData" : "createBy" },
             { "mData" : "createDate" },
             { "mData" : "updateBy" },
             { "mData" : "status","sClass":"center" },
             { "mData" : "id" }
           
           ],
           "aoColumnDefs": [
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return '<a href="'+contextPath+'knowledge/edit/'+oObj.aData.id+'">'+oObj.aData.title+'</a>';
                                },
                                                         
                                "aTargets": [1]                                  
                            },
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return oObj.aData.updateDate;
                                },
                                                         
                                "aTargets": [6]                                  
                            },
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                                	var val=oObj.aData.status;
                                	var app=$("#approver").val();
                                	if(val=="PUBLISHED"){
                                		if(!oObj.aData.approved){
                                    		if(app=="true"){
                                    			val='<button type="button" val="'+oObj.aData.id+'" class="approve btn btn-danger">'+val+'</button>';
                                    		}else{
                                    			val=val+'<span class="label label-important">Waiting for Approval</span>';
                                    		}
                                			
                                    	}
                                	}
                                	
                               	return val;
                                },
                                                         
                                "aTargets": [7]                                  
                            },
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return '<button class="btn btn-mini btn-danger btn-block remove" href="'+contextPath+'knowledge/delete/'+oObj.aData.id+'">Remove</button>';
                                },
                                                         
                                "aTargets": [ 8 ]                                  
                            }
           ],
		"fnServerParams" : function(aoData) {
			//if($("#username").val()!=null){
				aoData.push(
					{"name": "title", "value" : $("#title").val() },
					{"name": "knowledgeCategory.id", "value" : $("#ticketGroup").val() },
					{"name": "status", "value" : $("#status").val() }
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
		location.href=contextPath+"knowledge/add";
		
		
		
	});
	$(".approve").live("click",function(e){
		e.preventDefault();
		var elem=$(this).parent();
		
		$.ajax({
			type : "GET",
			url : contextPath+"knowledge/approve/"+$(this).attr("val"),
			cache : false,
			async : false,
			dataType : "json",
			complete : function(xhr, textStatus) {
				elem.html("PUBLISHED");
			},
			success : function(data, textStatus, xhr) {
                   alert('s');
				
			},
			error : function(xhr, textStatus, errorThrown) {
				//called when there is an error
			}
		//contentType: "json"
		});
		
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
          
          dataTableOption.fnDeleteRow(row.index());
         
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