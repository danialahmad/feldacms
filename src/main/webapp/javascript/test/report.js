$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();
	var bC = $('#latestCaseByProfile');
	var bC2 = $('#latestCase');
	
	 
	        
	        var data = [];
	        	        
		for( var i = 0; i < 3; i++){
			if(i==0){
				data[i] = { label: "Undertaking Letter", data: Math.floor(Math.random()*100)+1 };
			}
		if(i==1){
			data[i] = { label: "Letter of Intent", data: Math.floor(Math.random()*100)+1 };
		}
		if(i==2){
			data[i] = { label: "Letter of Award", data: Math.floor(Math.random()*100)+1 };
		}
		}
			
			
		
		   if($("#chart-3x").length > 0){
	        $.plot($("#chart-3x"), data, 
		{
	            series: {
	                pie: { show: true }
	            },
	            legend: { show: false }
		});
		   }
	        
	        var d1 =[];
	        var d2 =[];
	        var d3 =[];
	         d1 = [[0, 3]];
	         d2 = [[1, 2]];
	         d3 = [[2, 6]];


	        $.plot($("#chart-3"), [
	                                   {  label: "Undertaking Letter",
	                                       data: d1,
	                                       bars: {
	                                           show: true
	                                       }
	                                   },
	                                   {
	                                	   label: "Letter of Intent",
	                                       data: d2,
	                                       bars: {
	                                           show: true
	                                       }
	                                   },
	                                   {
	                                	   label: "Letter of Award ",
	                                	   data: d3,
	                                       bars: {
	                                           show: true
	                                       }
	                                   }
	                                   
	                                   
	                               ],
	                               { legend: { show: true,position: "right" }}
	        );
       
	       
		
});