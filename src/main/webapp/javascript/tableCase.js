$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();

	
	
	$("#today1").click(function(e){
		//location.href=contextPath+"case/activity/add/"+$("#id").val();
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
			
			
			
		
		
});