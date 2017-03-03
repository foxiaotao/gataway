// -----------------------------------------------------------------------
// author suntao
// jsprint 1.0
//
// - 2016.7.15
//

//------------------------------------------------------------------------

(function($) {
    var opt;

    $.dbgoPrint = function (options) {
        opt = $.extend({}, $.dbgoPrint.defaults, options);

        var $element = (this instanceof jQuery) ? this : $(this);
        
        var printAreaHtml = getNewWindowHtml(opt.body,opt.styleStr);
		
		var version = parseInt(getBrowserVersion());
		
        var winPrint = window.open("","myWin");
		winPrint.document.write(printAreaHtml);
		if(version > 99){
			winPrint.print();
			winPrint.close();
		}
		$.fn.domPrint.defaults = {
			//设置样式
			styleStr: ""
		};
    }

})(jQuery);



//edit new html content
function getNewWindowHtml(htmlStr,styleStr){
	var version = parseInt(getBrowserVersion());
	var html = '<html lang="en">';
	html += '<head>';
	html += '<meta charset="utf-8"/>';
	html += '<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />';
	html += '<meta name="viewport" content="width=device-width, initial-scale=1.0">';
	html += '<meta http-equiv="X-UA-Compatible" content="IE=edge">';
	html += '<title></title>';
	html += '<style>';
	html += 	styleStr;//样式
	html += '</style>';
	html += '</head>';
	html += '<body>';
	html += '<div>';
	html += 	htmlStr;
	html += '</div>';
	if(version < 100){
		html += '<OBJECT id="WebBrowser" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></OBJECT>';
	}
	html += '</body>';
	if(version < 100){
		html += '<script>document.getElementById("WebBrowser").execWB(7,1);<\/script>';
	}
	html += '</html>';
	
	return html;
}

//ie or other brower
function getBrowserVersion(){
	var userAgent = navigator.userAgent.toLowerCase();
	if(userAgent.match(/msie ([\d.]+)/)!=null){
		//ie 6-9
		var uaMatch = userAgent.match(/msie ([\d.]+)/);
		//alert(uaMatch[1]);
		return uaMatch[1];
	}else if(userAgent.match(/trident\/([\w.]+)/)){
		var uaMatch = userAgent.match(/trident\/([\w.]+)/);
		switch(uaMatch[1]){
			case "4.0" : return '8';break;
			case "5.0" : return '9';break;
			case "6.0" : return 'IE10';break;
			case "7.0" : return 'IE11';break;
			default : return 100;
		}
	}
	return 100;
}