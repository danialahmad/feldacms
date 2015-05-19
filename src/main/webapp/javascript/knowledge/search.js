$(document).ready(function(){
	var contextPath = $("#web-context-root").text();
	var total= $("#total").val();
	var obj={
			 total:Math.ceil($("#total").val()/2),
			 page: 1,
			 maxVisible: 2 
			 };
	var obj2={
			 total:Math.ceil($("#total2").val()/2),
			 page: 1,
			 maxVisible: 2 
			 };
	
	var boot=	$('#page-selection');
	var boot2=	$('#page-selection2');
    boot.bootpag(obj).on("page", function(event, /* page number here */ num){
    	$(".page").text(num);
			 $.ajax({
					url:contextPath+"knowledge/keyword/results",
					method : "POST",
					data :[{"name": "search", "value" : $("#widgetInputMessage").val() },{"name": "num", "value" : (num*2)-2 },{"name": "maxVisible", "value" : 2 }],
				    success: function(data) { 	
				    	generateHtml(data.aaData);
				    	 var items = $(".info .text:containsi('"+$("#widgetInputMessage").val()+"')");
				         items.highlight($("#widgetInputMessage").val());
				    },
					error:    function(data) { 
						alert("Error");
					
				  
					} ,
				});
	});
	
	
	
	$("#faqSearch").click(function(e){
		e.preventDefault();
		var val=$("#widgetInputMessage").val();
		var maxVisible=2;
		$("#searchVal").text(val);
		boot.html(''); 
		$('.list_custom').html('');
		$(".resultItem").text(0);
		$(".totPage").text(0);
		$(".page").text(0);
		if(val.length>0){
		$.ajax({
			url:contextPath+"knowledge/keyword/results",
			method : "POST",
			data :[{"name": "search", "value" : val },{"name": "num", "value" : 0 },{"name": "maxVisible", "value" : maxVisible }],
		    success: function(data) { 
		    	$("#total").val(data.iTotalDisplayRecords);
		    	
		    	if(data.iTotalDisplayRecords>0){
		    		$(".page").text(1);
		    	}
		    		$(".resultItem").text(data.iTotalDisplayRecords);
		    	
		    	obj.total=Math.ceil($("#total").val()/2);
		    	$(".totPage").text(obj.total);
		    	boot.bootpag(obj);
		    	
		    	generateHtml(data.aaData);
		    
		    	 var items = $(".info .text:containsi('"+val+"')");
		         items.highlight(val);
		    },
			error:    function(data) { 
				alert("Error");
			
		  
			} ,
		});
		}
		
	});
	
	$.ajax({
		url:contextPath+"knowledgeCategory/list",
		method : "POST",
		data :[{"name": "num", "value" : 0 },{"name": "maxVisible", "value" : 5 }],
	    success: function(data) { 
	    	$("#total2").val(data.iTotalDisplayRecords);
	    	
	    	//if(data.iTotalDisplayRecords>0){
	    		//$(".page").text(1);
	    	//}
	    		//$(".resultItem").text(data.iTotalDisplayRecords);
	    	
	    	obj2.total=Math.ceil($("#total2").val()/2);
	    //	$(".totPage").text(obj.total);
	    	boot2.bootpag(obj2);
	    	
	    	generateHtml2(data.aaData);
	    
	    	// var items = $(".info .text:containsi('"+val+"')");
	         //items.highlight(val);
	    },
		error:    function(data) { 
			alert("Error");
		
	  
		} ,
	});
	boot2.bootpag(obj).on("page", function(event, /* page number here */ num){
    	
			 $.ajax({
					url:contextPath+"knowledgeCategory/list",
					method : "POST",
					data :[{"name": "num", "value" : (num*2)-2 },{"name": "maxVisible", "value" : 5 }],
				    success: function(data) { 	
				    	generateHtml2(data.aaData);
				    	
				    },
					error:    function(data) { 
						alert("Error");
					
				  
					} ,
				});
	});

function generateHtml(data){
	 var arr = new Array();
		for ( var i = 0; i < data.length; i++) {
			  var row='<div class="item">'
                 +'<div class="info">'
                  +   '<a href="#" class="name">'+data[i].title+'</a>'
                  +  '<p class="text-error text">'+data[i].ticketGroup+'</p>'
                  +   '<p class="text">'+data[i].description+'</p>'                                                
                  +   '<p class="muted">Attachmet 1: somefile.exe</p>'
                  +   '<p class="muted">Attachmet 2: anotherfile.avi</p>'
                 +'</div>'
             +'</div>';
			arr.push(row);
		}
	    
	    $('.list_custom').html(arr.join(""));
	
}	
function generateHtml2(data){
	 var arr = new Array();
		for ( var i = 0; i < data.length; i++) {
			 var arr2 = new Array();
			for ( var x = 0; x < data[i].titles.length; x++) {
				var subrow='<ul  class="list_simple">'
				 +'<li><a href="'+contextPath+'knowledge/view/'+data[i].ids[x]+'">'+data[i].titles[x]+'</a></li>'
				+'</ul>';
				arr2.push(subrow);
				}
			
			  var row='<ul id="faqListController" class="list_simple">'
					+'<li><strong>'+data[i].name+'</strong>'
					+arr2.join("")
		    +'</li>'
		+'</ul>'
            +'</div>';
			arr.push(row);
		}
	    
	    $('.list_custom2').html(arr.join(""));
	
}
	
	

	
	
	 
	
	
});