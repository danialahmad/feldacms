$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	
	 
	$('#formModal').on('hidden.bs.modal', function (e) {
		$(this).removeData('modal');
	});

$(".modalAct").live('click',function(e){
	e.preventDefault();
	$("#formModal").removeData ('modal');
	$("#formModal").modal({
		show:true,
		remote:$(this).attr("href")
	}).css({
		
		'width': '500px',
	    'margin-left': function () 
				{
				  return -($(this).width() / 2);
				}
			
	});
	
});
	 
	 
	 
$('#formModal').on('shown.bs.modal', function (e) {

	    $('#mdForm').ajaxForm({
			
		    url:contextPath+$('#action').val(),
		    beforeSubmit : function(formData, jqForm, options) {
				//$("#modal-waiting").modal("show");
			},
		    success: function(data){
		    	dataTableOption.fnDraw();
		    	$("#formModal").modal('hide');
		    },
	    	error:    function(data) { 
	    		alert("Error");
	    		//$("#modal-waiting").modal("hide");
	    		
		  
	    	} ,
	    	
	    }); 
});		

	
});