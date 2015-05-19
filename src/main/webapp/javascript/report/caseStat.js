$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();
	var bC = $('#latestCaseByProfile');
	var bC2 = $('#latestCase');
	
	$.get(contextroot + "latestCasesByProfile", function(data) {
		var arr = new Array();
		for ( var i = 0; i < data.length; i++) {
			var row =generateRow(data[i]);
			arr.push(row);
		}
		bC.find("tbody").html(arr.join(""));
	});

	$.get(contextroot + "latestCases", function(data) {
		var arr = new Array();
		for ( var i = 0; i < data.length; i++) {
			var row =generateRow(data[i]);
			arr.push(row);
		}
		bC2.find("tbody").html(arr.join(""));
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
			$.get(contextroot + "dashboard/caseInfo", function(data) {
				$.each(data.aaData, function( index, value ) {
					var label =value.label;
					
					if(label=='today'){
						$("#today1").html(value.data+"<span>Today's Cases</span>");
					}
					if(label=='week'){
						$("#week1").html(value.data+"<span>This week's Cases</span>");
					}
					if(label=='month'){
						$("#month1").html(value.data+"<span>This month's Cases</span>");
					}
					if(label=='year'){
						$("#year1").html(value.data+"<span>This year's Cases</span>");
					}
				});
				
			});	
			
			$.get(contextroot + "dashboard/caseYearStatistics", function(data) {
				var arr = new Array();
				$.each(data.aaData, function( index, value ) {
					var row="<tr>" + "<td align='center'>" + value.label + "</td>"
					+ "<td align='center'>" + value.data + "</td>";
					arr.push(row);
				});
				$("#tableYearStat tbody").html(arr.join(""));
			});
			$.get(contextroot + "dashboard/caseInfoByStatus", function(data) {
				var arr = new Array();
				var dataArr = [];
				$.each(data.aaData, function( index, value ) {
					var row="<tr>" + "<td align='center'>" + value.label + "</td>"
					+ "<td align='center'>" + value.data + "</td>";
					arr.push(row);
					dataArr[index] = {label:value.label,data:value.data};
				});
				$("#tableStatus tbody").html(arr.join(""));
				
				if($("#chart-status").length > 0){       
			        
				       // var data = [];        	               		
					

				        $.plot($("#chart-status"), dataArr, 
					{
				            series: {
				                pie: { show: true }
				            },
				            legend: { show: false }
					});

				    }
				
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
			
			$.get(contextroot + "dashboard/caseInfoByPriority", function(data) {
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
				$("#tablePriority2 tbody").html(arr.join(""));
			
				if($("#chart-widget1").length > 0){       
			        
			       // var data = [];        	               		
				

			        $.plot($("#chart-widget1"), dataArr, 
				{
			            series: {
			                pie: { show: true }
			            },
			            legend: { show: true }
				});

			    }
			
			});

	
			
			
			
			var dataTableOption = $("#list-meeting-room").dataTable({
				"sPaginationType": "full_numbers",
		        "bProcessing": true,
				"bDestroy" : true,
		        "bServerSide": true,
		        "bSort": false,
		        "bFilter": false,
		        "sAjaxSource": contextroot+'complaint/request/list/mycomplaints',
		        "aoColumns": [
		             { "mData" : "no" ,"sClass":"center" },
		             { "mData" : "id" ,"sClass":"center" },
		             { "mData" : "title" },
		             { "mData" : "createDate" ,"sClass":"center" },
		             { "mData" : "priority" ,"sClass":"center"},
		             { "mData" : "status" ,"sClass":"center"}
		           ],
		           "aoColumnDefs": [
		                            {
		                                "bUseRendered": false,
		                                "fnRender" : function ( oObj ) {
		                               	return '<a href="'+contextroot+'complaint/request/view/'+oObj.aData.id+'">'+oObj.aData.id+'</a>';
		                                },
		                                                         
		                                "aTargets": [ 1 ]                                  
		                            }, 
		                            {
		                                "bUseRendered": false,
		                                "fnRender" : function ( oObj ) {
		                                	var priority="";
		                                	if(oObj.aData.priority=="LOW"){
		                                		priority="<span class='label label-success'>"+oObj.aData.priority+"</span>";
		                                	}else if(oObj.aData.priority=="MEDIUM"){
		                                		priority="<span class='label label-info'>"+oObj.aData.priority+"</span>";
		                                	}else if(oObj.aData.priority=="HIGH"){
		                                		priority="<span class='label label-warning'>"+oObj.aData.priority+"</span>";
		                                	}else if(oObj.aData.priority=="CRITICAL"){
		                                		priority="<span class='label label-important'>"+oObj.aData.priority+"</span>";
		                                	}
		                               	return priority;
		                                },
		                                                         
		                                "aTargets": [ 4 ]                                  
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