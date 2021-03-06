$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();
	var bC = $('#latestCaseByProfile');
	
	$.get(contextroot + "latestCasesByProfile", function(data) {
		var arr = new Array();
		for ( var i = 0; i < data.length; i++) {
			var row =generateRow(data[i]);
			arr.push(row);
		}
		bC.find("tbody").html(arr.join(""));
	});

	
	
		/**	setInterval(function() {				
				block_loading(bC);
				// Timer
				setTimeout(function() {
					block_loading(bC);
					$.get(contextroot + "latestCases", function(data) {
						var arr = new Array();
						for ( var i = 0; i < data.length; i++) {
							var row =generateRow(data[i]);
							arr.push(row);
						}
						bC.find("tbody").html(arr.join(""));
					});
				}, 2000);
			}, 10000);**/

			
			$.get(contextroot + "dashboard/caseInfoByProfile", function(data) {
				$.each(data.aaData, function( index, value ) {
					var label =value.label;
					
					if(label=='today'){
						$("#today").html(value.data+"<span>Today's Cases</span>");
					}
					if(label=='week'){
						$("#week").html(value.data+"<span>This week's Cases</span>");
					}
					if(label=='month'){
						$("#month").html(value.data+"<span>This month's Cases</span>");
					}
					if(label=='year'){
						$("#year").html(value.data+"<span>This year's Cases</span>");
					}
				});
				
			});
		
			
	
			
			$.get(contextroot + "dashboard/caseInfoByPriorityByProfile", function(data) {
				var arr = new Array();
				var dataArr = [];
				$.each(data.aaData, function( index, value ) {
					var priority="";
					var color="";
					if(value.label=="LOW"){
						priority="<span class='label label-success'>"+value.label+"</span>";
						color="#468847";
					}else if(value.label=="MEDIUM"){
						priority="<span class='label label-info'>"+value.label+"</span>";
						color="#3A87AD";
					}else if(value.label=="HIGH"){
						priority="<span class='label label-warning'>"+value.label+"</span>";
						color="#F89406";
					}else if(value.label=="CRITICAL"){
						priority="<span class='label label-important'>"+value.label+"</span>";
						color="#B94A48";
					}
					
					var row="<tr>" + "<td align='center'>" + priority + "</td>"
					+ "<td align='center'>" + value.data + "</td>";
					arr.push(row);
					dataArr[index] = {label:"",data:value.data,color:color};
				});
				$("#tablePriority tbody").html(arr.join(""));
			
				if($("#chart-widget2").length > 0){       
			        
			       // var data = [];        	               		
				

			        $.plot($("#chart-widget2"), dataArr, 
				{
			            series: {
			                pie: { show: true }
			            },
			            legend: { show: true }
				});

			    }
			
			});
			
		

		
function generateRow(data){
	var cDate= data.date.split(" ");
	var date="<span class='date'>"+cDate[0]+"</span>";
	var time="<span class='time'>"+cDate[1]+"</span>";
	var priority="";
	if(data.priority=="LOW"){
		priority="<span class='label label-success'>"+data.priority+"</span>";
	}else if(data.priority=="MEDIUM"){
		priority="<span class='label label-info'>"+data.priority+"</span>";
	}else if(data.priority=="HIGH"){
		priority="<span class='label label-warning'>"+data.priority+"</span>";
	}else if(data.priority=="CRITICAL"){
		priority="<span class='label label-important'>"+data.priority+"</span>";
	}
	
	var row = "<tr>" + "<td>" + date+time + "</td>"
	+ "<td>" + data.title + "</td>" + "<td>"
	+ priority + "</td>" + "<td>"
	+ data.status + "</td>" + "</tr>";
	return row;
}		
		
});