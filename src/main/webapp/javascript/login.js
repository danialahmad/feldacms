$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();
	
	$("#sign").click(function(e){
		//location.href=contextPath+"case/activity/add/"+$("#id").val();
		$("#registerModal").removeData ('modal');
		$("#registerModal").modal({
			show:true,
			remote:contextroot+"registration"
		}).css({
			
			'width': '1000px',
		    'margin-left': function () 
					{
					  return -($(this).width() / 2);
					}
				
		});
		
		
	});		
			
	 $("#country").trigger("change");
	 $("#country").bind("change",function(){
		 loadComboOptions(this, "list/state/"+$(this).val(), "state");
		});		
			
});