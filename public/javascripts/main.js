$(document).ready(function() {
	var showChar = 120;
	var ellipsestext = "...";
	var moretext = "Devamını gör";
	var lesstext = "Gizle";
	$('.more').each(function() {
		var content = $(this).clone().find('a').remove().end().text();
		if(content.length > showChar) {

			var c = content.substr(0, showChar);
			var h = content.substr(showChar-1, content.length - showChar);

			var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';

			$(this).html(html);
		}

	});

	$(".morelink").click(function(){
		if($(this).hasClass("less")) {
			$(this).removeClass("less");
			$(this).html(moretext);
		} else {
			$(this).addClass("less");
			$(this).html(lesstext);
		}
		$(this).parent().prev().toggle();
		$(this).prev().toggle();
		return false;
	});
	
	
	$(function() {
		$('textarea.expand').on('click', function() {
			$(this).animate({ height: "145px" }, 200);
	    });		      
	});
	
	
//	$('html').click(function(event) {
//		if ($('.share').has(event.target).length == 0)
//		{ 
//			e.stopPropagation();
//						
//		}
//		}); 
	
	
	
	$('.share').bind('click', function (e) {
			e.stopPropagation();			 
	});
	
});