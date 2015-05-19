$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var locale = $("#app-locale").text();
	var url;
	$(".logClick").die();
	 
		
	$(".logClick").live('click',function(e){
		//location.href=contextPath+"case/activity/add/"+$("#id").val();
    	url= $(this).attr('href');
		e.preventDefault();
		$("#logModal").removeData ('modal');
		$("#logModal").modal({
			show:true
			}).css({
			
			'width': '400px',
		    'margin-left': function () 
					{
					  return -($(this).width() / 2);
					}
				
		});
		
		
	});
	var eventTable = $('#eventTable');
	$('#logModal').on('shown.bs.modal', function (e) {
		$.get(url, function(data) {
			var arr = new Array();
			for ( var i = 0; i < data.length; i++) {
				var row =generateRow(data[i]);
				arr.push(row);
			}
			eventTable.find("tbody").html(arr.join(""));
		});
	});

	function generateRow(data){
		var cDate= data.date.split(" ");
		var date="<span class='date'>"+cDate[0]+"</span>";
		var time="<span class='time'>"+cDate[1]+"</span>";
		var name="<span class='muted'><em>Created by :"+data.createBy+"</em></span>";
		
		
		var row = "<tr>" + "<td>" + date+time + "</td>"
		+ "<td>" + data.action + "<br/>"+name+"</td></tr>";
		return row;
	}
	
});