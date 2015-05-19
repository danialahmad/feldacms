$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	

	
	var dataTableOption = $("#userTable").dataTable({
		"sPaginationType": "full_numbers",
        "bProcessing": true,
		"bDestroy" : true,
        "bServerSide": true,
        "bSort": false,
        "bFilter": false,
        "sAjaxSource": $("#search-form").attr("action"),
        "aoColumns": [
             { "mData" : "no" ,"sClass":"center" },
             { "mData" : "username"},
             { "mData" : "name" },
            
             { "mData" : "email" },
             { "mData" : "role" },
             { "mData" : "createDate" }
             
           
           ],
           "aoColumnDefs": [
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return '<a href="'+contextPath+'user/profile/'+oObj.aData.username+'/">'+oObj.aData.username+'</a>';
                                },
                                                         
                                "aTargets": [ 1 ]                                  
                            }
           ],
		"fnServerParams" : function(aoData) {
			//if($("#username").val()!=null){
				aoData.push(
					{ "name": "username", "value" : $("#username").val() },
					{"name": "name", "value" : $("#name").val() },
					{"name": "email", "value" : $("#email").val() },
					{"name": "role.id", "value" : $("#role").val() }
					
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
	
	$("#newUser").click(function(e){
		e.preventDefault();
		 window.location=contextPath+'administration/um/user/add';
	});
	
	
});