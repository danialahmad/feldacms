$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();
	
	$.cookies.set('level1',null); 
	$.cookies.set('level2',null);
	$.cookies.set('level3',null);	
			
			
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
			
			var dataTableOption2 = $("#list-meeting-room2").dataTable({
				"sPaginationType": "full_numbers",
		        "bProcessing": true,
				"bDestroy" : true,
		        "bServerSide": true,
		        "bSort": false,
		        "bFilter": false,
		        "sAjaxSource": contextroot+"case/list/unassigned",
		        "aoColumns": [
		             { "mData" : "no" ,"sClass":"center" },
		             { "mData" : "id" ,"sClass":"center" },
		             { "mData" : "title" },
		             { "mData" : "createDate" ,"sClass":"center" },
		             { "mData" : "priority" ,"sClass":"center"}
		           ],
		           "aoColumnDefs": [
		                            {
		                                "bUseRendered": false,
		                                "fnRender" : function ( oObj ) {
		                               	return '<a href="'+contextroot+'case/assignment/edit/'+oObj.aData.id+'/'+oObj.aData.assignmentId+'">'+oObj.aData.id+'</a>';
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
			
		
			
			
			
			
			
			var dataTableOption3 = $("#list-meeting-room3").dataTable({
				"sPaginationType": "full_numbers",
		        "bProcessing": true,
				"bDestroy" : true,
		        "bServerSide": true,
		        "bSort": false,
		        "bFilter": false,
		        "sAjaxSource": contextroot+"case/list/mycases",
		        "aoColumns": [
		             { "mData" : "no" ,"sClass":"center"},
		             { "mData" : "id" ,"sClass":"center" },
		             { "mData" : "title" },
		             { "mData" : "createDate" ,"sClass":"center" },
		             { "mData" : "priority","sClass":"center"},
		             { "mData" : "status","sClass":"center"}
		           ],
		           "aoColumnDefs": [
		                            {
		                                "bUseRendered": false,
		                                "fnRender" : function ( oObj ) {
		                               	return '<a href="'+contextroot+'case/assignment/edit/'+oObj.aData.id+'/'+oObj.aData.assignmentId+'">'+oObj.aData.id+'</a>';
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
			
			
			
			
		

});


var stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
    var socket = new SockJS('/feldacms/websocket/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected v: ' + frame);
        stompClient.subscribe('/topic/greetings', function(greeting){
        	alert('s');
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var name = document.getElementById('name').value;
    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
}

function showGreeting(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}