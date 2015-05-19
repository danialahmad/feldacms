jQuery.noConflict();

jQuery(document).ready(function(){
	
	
	/**
	 * This will remove username/password text in the login form fields
	**/
	jQuery('.username, .password').focusout(function(){
		if(jQuery(this).val() != '') {
			jQuery(this).css({backgroundPosition: "0 -32px"});	
		} else {
			jQuery(this).css({backgroundPosition: "0 0"});	
		}
	});
	
	jQuery('.username, .password').focusin(function(){
		if(jQuery(this).val() == '') {
			jQuery(this).css({backgroundPosition: "0 -32px"});	
		}
	});
	
	
	/**
	 * Message Notify Drop Down
	**/
	jQuery('.messagenotify .wrap, .alertnotify .wrap').click(function(){
		var t = jQuery(this).parent();
		var url = t.attr('href');
		if(t.hasClass('showmsg')) {
			t.removeClass('showmsg');
			t.find('.thicon').removeClass('thiconhover');
			t.parent().find('.dropbox').remove();
			
		} else {
			
			jQuery('.topheader li').each(function(){
				jQuery(this).find('.showmsg').removeClass('showmsg');
				jQuery(this).find('.thicon').removeClass('thiconhover');
				jQuery(this).find('.dropbox').remove();
			});
			
			t.addClass('showmsg');
			t.find('.thicon').addClass('thiconhover');
			t.parent().append('<div class="dropbox"></div>');
						
			jQuery.post(url,function(data){
				jQuery('.dropbox').append(data);
			});
		}
		return false;
		
	});
	
	jQuery(document).click(function(event) {
		var msglist = jQuery('.dropbox');
		if(!jQuery(event.target).is('.dropbox')) {
			if(msglist.is(":visible")) {
				msglist.prev().removeClass('showmsg');
				msglist.prev().find('.thicon').removeClass('thiconhover');
				msglist.remove();
			}
		}        
	});

	
	/**
	 * Login form validation
	**/
	jQuery('#loginform').submit(function(){
		var username = jQuery('.username').val();
		var password = jQuery('.password').val();
		if(username == '' && password == '') {
			jQuery('.loginNotify').find('span').text('Sila Masukkan Nama dan Katalaluan');
			jQuery('.loginNotify').slideDown('fast');	
			return false;
		} else {
			return true;
		}
	});
	if(getUrlParameter('errorMessage')!=null){
		jQuery('.loginNotify').slideDown('fast');	
	}
    function getUrlParameter(sParam)
	{
	    var sPageURL = window.location.search.substring(1);
	    var sURLVariables = sPageURL.split('&');
	    for (var i = 0; i < sURLVariables.length; i++) 
	    {
	        var sParameterName = sURLVariables[i].split('=');
	        if (sParameterName[0] == sParam) 
	        {
	            return sParameterName[1];
	        }
	    }
	}   
	/**
	 * Sidebar accordion
	**/
	jQuery('#accordion h3').live('click',function() {
		if(jQuery(this).hasClass('open')) {
			jQuery(this).removeClass('open');
			jQuery(this).next().slideUp('fast');
		} else {
			jQuery(this).addClass('open');
			jQuery(this).next().slideDown('fast');
		} return false;
	});
		
	
	/**
	 * Widget Box Toggle
	**/
	jQuery('.widgetbox h3, .widgetbox2 h3').hover(function(){
		jQuery(this).addClass('arrow');
		return false;
	},function(){
		jQuery(this).removeClass('arrow');
		return false;
	});
	
	jQuery('.widgetbox h3, .widgetbox2 h3').toggle(function(){
		jQuery(this).next().slideUp('fast');
		jQuery(this).css({MozBorderRadius: '3px', 
						  WebkitBorderRadius: '3px',
						  borderRadius: '3px'});
		return false;
	},function(){
		jQuery(this).next().slideDown('fast');
		jQuery(this).css({MozBorderRadius: '3px 3px 0 0', 
						  WebkitBorderRadius: '3px 3px 0 0',
						  borderRadius: '3px 3px 0 0'});
		return false;
	});
	
	
	/**
	 * Notification
	**/
	jQuery('.notification .close').click(function(){
		jQuery(this).parent().fadeOut();
	});	
	
	
	/** Make footer always at the bottom**/
	if(jQuery('body').height() > jQuery(window).height()) {
		jQuery('.footer').removeClass('footer_float');
	}
	
	/** to uppercase **/
/**	jQuery('.form_default').find('input[type=text]').live('keyup',function(){
		var className = jQuery(this).attr('class');
		var name=jQuery(this).attr('id');
		if(className!='lowercase'){
			if(name!="comEmail"){
				jQuery(this).val(jQuery(this).val().toUpperCase());
			}
			
		}
	});**/
	
	
	/**DROP DOWN MENU**/
	jQuery(".subnav").css({display: "none"}); // Opera Fix
	jQuery(".tabmenu li").hover(function(){
		jQuery(this).find('ul:first').css({visibility: "visible",display: "none"}).show(400);
	},function(){
		jQuery(this).find('ul:first').css({visibility: "hidden"});
	});

    
	
	
});



function getURL(url) {
	var odata;
	var op = jQuery.ajax({
		type : "GET",
		url : url,
		cache : false,
		async : false,
		dataType : "json",
		complete : function(xhr, textStatus) {

		},
		success : function(data, textStatus, xhr) {

			odata = data;
		},
		error : function(xhr, textStatus, errorThrown) {
			//called when there is an error
		}
	//contentType: "json"
	});
	return odata;
}



Date.prototype.format = function(format) //author: meizz
{
	var hours = this.getHours();
	var ampm = hours >= 12 ? 'PM' : 'AM';
	hours = hours % 12;
	hours = hours ? hours : 12; // the hour '0' should be '12'
	
	
  var o = {
    "M+" : this.getMonth()+1, //month
    "d+" : this.getDate(),    //day
    "h+" :hours,   //hour
    "m+" : this.getMinutes(), //minute
    "s+" : this.getSeconds(), //second
    "am/pm":ampm,
    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
    "S" : this.getMilliseconds() //millisecond
  }

  if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
    (this.getFullYear()+"").substr(4 - RegExp.$1.length));
  for(var k in o)if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,
      RegExp.$1.length==1 ? o[k] :
        ("00"+ o[k]).substr((""+ o[k]).length));
  return format;
}

