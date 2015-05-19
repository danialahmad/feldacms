var select_one = $("#select-one").text();
var select_none = $("#select-none").text();
var context_root = $("#web-context-root").text();
function loadComboBox(cbId, url, selectedValue, colValue, colDisplay) {
    colValue = typeof colValue !== 'undefined' ? colValue : 0;
    colDisplay = typeof colDisplay !== 'undefined' ? colDisplay : 1;
    $.ajax(context_root + url, {"success" : function(data) {
    				$(cbId).empty();
                    var len = data.length;
                    var option = $("<option></option>");
                    if (len == 0) {
                    	option.text(select_none);
                    }
                    if (len > 1) {
                    	option.text(select_one);
                    }
                    if(selectedValue === null || selectedValue===""){
                    	option.attr("selected", "selected");
                    }
            		option.attr("value", "");
            		$(cbId).append(option);
                    $(data).each(function(index) {
						item = data[index];
						var option = $("<option></option>");
						option.text(item[colDisplay]);
						if(selectedValue===item[colValue]){
                        	option.attr("selected", "selected");
                        }
						option.attr("value", item[colValue]);
						$(cbId).append(option);
					});
                $(cbId).trigger("change");
            }
    });
}
function populateOptionNone(cbId) {
        $(cbId).empty();
}

function loadComboOptions(obj, url, targetId) {
	if (!isEmpty(obj.value)) {
		var data = getURL(context_root + url);
		var items = [];
		items.push('<option value="-1">- Select One -</option>');
		
		 $(data).each(function(index) {
			 item = data[index];
			 items.push('<option value="'+item[0]+'">'
						+ item[1] + '</option>');
		 });
		$("#" + targetId.replace('.', '\\.')).empty();
		$("#" + targetId.replace('.', '\\.')).append(items.join('</br>'));
	} else {
		var items = [];
		items.push('<option value="-1">- Select One -</option>');
		$("#" + targetId.replace('.', '\\.')).empty();
		$("#" + targetId.replace('.', '\\.')).append(items.join('</br>'));
	}

}
function loadComboOptionsWithDefaultLabel(defaultLabel,obj, url, targetId) {
	if (!isEmpty(obj.value)) {
		var data = getURL(context_root + url);
		var items = [];
		items.push('<option value="-1">- '+defaultLabel+' -</option>');
		
		 $(data).each(function(index) {
			 item = data[index];
			 items.push('<option value="'+item[0]+'">'
						+ item[1] + '</option>');
		 });
		$("#" + targetId.replace('.', '\\.')).empty();
		$("#" + targetId.replace('.', '\\.')).append(items.join('</br>'));
	} else {
		var items = [];
		items.push('<option value="-1">- '+defaultLabel+' -</option>');
		$("#" + targetId.replace('.', '\\.')).empty();
		$("#" + targetId.replace('.', '\\.')).append(items.join('</br>'));
	}

}
function isEmpty(obj) {
	if (typeof obj == 'undefined' || obj === null || obj === '')
		return true;
	if (typeof obj == 'number' && isNaN(obj))
		return true;
	if (obj instanceof Date && isNaN(Number(obj)))
		return true;
	return false;
}
function getURL(url) {
	var odata;
	var op = $.ajax({
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
