$(document).ready(function() {
			
	var contextroot = $("#web-context-root").html();
	
	
	
	   $('#reportForm').validate({
	        rules: {
	        	month: {
		                required: true
		            },
		            year: {
		                required: true
		            }
	        },
	        messages: {
	        	month: {
		                required: "Sila Masukkan Bulan"
		            },
		            year: {
		                required: "Sila Masukkan Tahun"
		            }
		     
	        },
	        errorElement: "span", // contain the error msg in a span tag
	        errorClass: 'help-block',
	        errorPlacement: function (error, element) { // render error placement for each input type
	           
	                
	                if(element.attr('class')=="datetimepicker"){
	                	error.insertAfter(".input-prepend");
	                }else{
	                	error.insertAfter(element);
	                }
	           
	        },
	        highlight: function (element) {
	           
	            $(element).closest('.controls-row').addClass('error');
	        },
	        unhighlight: function (element) { // revert the change done by hightlight
	        	 $(element).closest('.controls-row').removeClass('error');
	        }
	   
	    });
	   
	   $("#jana").click(function(e){
			e.preventDefault();
			if( $('#reportForm').valid()){
				genReport1();
			}
			
		});
	   $("#jana2").click(function(e){
			e.preventDefault();
			if( $('#reportForm2').valid()){
				genReport2();
			}
			
		});
	   $("#jana3").click(function(e){
			e.preventDefault();
			if( $('#reportForm3').valid()){
				genReport3();
			}
			
		});
	   $("#jana4").click(function(e){
			e.preventDefault();
			if( $('#reportForm4').valid()){
				genReport4();
			}
			
		});
	   $("#jana5").click(function(e){
			e.preventDefault();
			if( $('#reportForm5').valid()){
				genReport5();
			}
			
		});
	
	   
	   function genReport1()
	    {
			
			var bulan_dari=$("#bulan_dari").val();
			var bulan_hingga=$("#bulan_hingga").val();
			var tahun=$("#tahun").val();
			
	    	window.open(contextroot+'report/kumulatif/feldacms_1?&bulan_dari='+bulan_dari+'&bulan_hingga='+bulan_hingga+'&tahun='+tahun, 'formpopup', 'width=800,height=800,resizeable,scrollbars');
	       // this.target = 'formpopup';
	    }
	
	function genReport2()
    {
		
		var month=$("#month").val();
		var year=$("#year").val();
		
    	window.open(contextroot+'report/bulanan/feldacms_2?&month='+month+'&year='+year, 'formpopup', 'width=800,height=800,resizeable,scrollbars');
       // this.target = 'formpopup';
    }
	function genReport3()
    {
		
		var month=$("#month").val();
		var year=$("#year").val();
		
    	window.open(contextroot+'report/tambahan/feldacms_3?&month='+month+'&year='+year, 'formpopup', 'width=800,height=800,resizeable,scrollbars');
       // this.target = 'formpopup';
    }
	
	function genReport4()
    {
		
		var month=$("#month4").val();
		var year=$("#year4").val();
		
    	window.open(contextroot+'report/bulanan/feldacms_4?&month='+month+'&year='+year, 'formpopup', 'width=800,height=800,resizeable,scrollbars');
       // this.target = 'formpopup';
    }
	
	function genReport5()
    {
		
		var month=$("#month5").val();
		var year=$("#year5").val();
		
    	window.open(contextroot+'report/bulanan/feldacms_5?&month='+month+'&year='+year, 'formpopup', 'width=800,height=800,resizeable,scrollbars');
       // this.target = 'formpopup';
    }
    
		
});