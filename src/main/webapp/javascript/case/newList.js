$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	$('.datepicker2').datepicker({ 
	    dateFormat: 'dd/mm/yy'
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
             { "mData" : "no" ,"sClass":"center" },
             { "mData" : "id" ,"sClass":"center" },
             { "mData" : "title" },
             { "mData" : "createDate" ,"sClass":"center" },
             { "mData" : "status","sClass":"center" },
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
                                	var res=oObj.aData.status;
                                	if(oObj.aData.rate==null && oObj.aData.status=="CLOSED"){
                                		//res=oObj.aData.status+rateForm(oObj.aData.id);
                                		//res='<a class="rateClick" class="text-danger" >'+oObj.aData.status+'</a>';
                                		res=oObj.aData.status+'<p><button type="button" href="'+contextPath+'case/rating/'+oObj.aData.id+'" class="rateClick btn btn-success">Rate</button></p>';
                                	}if(oObj.aData.rate!=null && oObj.aData.status=="CLOSED"){
                                		res=oObj.aData.status+'<p class="text-success">Rating : <strong>'+oObj.aData.rate+'</strong></p>';
                                	}
                               	return res;
                                },
                                                         
                                "aTargets": [ 4 ]                                  
                            },
                            {
                                "bUseRendered": false,
                                "fnRender" : function ( oObj ) {
                               	return '<a class="logClick" href="'+contextPath+'eventLog/'+oObj.aData.id+'"><i class="i-cabinet"></i></a>';
                                },
                                                         
                                "aTargets": [ 5 ]                                  
                            }
           ],
		"fnServerParams" : function(aoData) {
			aoData.push(
				
					{ "name": "id", "value" : $("#id").val() },
					{ "name": "ticketTitle", "value" : $("#ticketTitle").val() },
					{ "name": "status", "value" : $("#status").val() },
					{ "name": "phoneNo", "value" : $("#phoneNo").val() },
					{ "name": "icNo", "value" : $("#icNo").val() },
					{ "name": "dateFrom", "value" : $("#dateFrom").val() },
					{ "name": "dateTo", "value" : $("#dateTo").val() }
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
	
	$("#button-open-search").click(function(e){
		dataTableOption.fnDraw();
		$(".all-area").hide();
		$("#search-area").show();
    });
	
	
	 $(".rate").live('click',function(){   
		 var popup = $(this).parent('.item').find('.popup');             
	        if(popup.length > 0){            
	            popup.is(':visible')?popup.fadeOut(200):popup.fadeIn(300);                        
	            return false;
	        }        
	    });
	    $(".popup-close").click(function(){
	        $(this).parents('.popup').fadeOut(200);
	    });
	    
	    
	    
	    $(".rateClick").live('click',function(e){
			//location.href=contextPath+"case/activity/add/"+$("#id").val();
	    	var ux=$(this).attr("href");
	    	e.preventDefault();
			$("#ratingModal").removeData ('modal');
			$("#ratingModal").modal({
				show:true,
				remote:ux
			}).css({
				
				'width': '500px',
			    'margin-left': function () 
						{
						  return -($(this).width() / 2);
						}
					
			});
			
			
		});
	    
	    $(".rateClose").live('click',function(e){
	    	e.preventDefault();
	    	$("#ratingModal").modal('hide');
	    	
	    });
	    $('#ratingModal').on('hidden.bs.modal', function (e) {
			$(this).removeData('modal');
		});
	    
	    $('#ratingModal').on('shown.bs.modal', function (e) {

		    $('#ratingView').ajaxForm({
				
			    url:$("#ratingView").prop('action'),
			    beforeSubmit : function(formData, jqForm, options) {
					//$("#modal-waiting").modal("show");
				},
			    success: function(data){
			    	dataTableOption.fnDraw();
			    	$("#ratingModal").modal('hide');
			    },
		    	error:    function(data) { 
		    		alert("Error");
		    		//$("#modal-waiting").modal("hide");
		    		
			  
		    	} ,
		    	
		    }); 
	});
	
	
});





function rateForm(id){
	var val='<br/><div class="btn-group"><button class="btn">Rate</button>'
    +'<button class="btn dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>'
    +'<ul class="dropdown-menu">'
    +' <li><a href="#">POOR</a></li>'
    +' <li><a href="#">FAIR</a></li>'
    +' <li><a href="#">AVERAGE</a></li>'
    +' <li><a href="#">GOOD</a></li>'
    +' <li><a href="#">EXECELLENT</a></li>'
    +'</ul>'                                                    
    +'</div>';
	
	return val;
}

