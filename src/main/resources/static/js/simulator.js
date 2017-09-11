$( document ).ready(function() {
    var w = $(window).height();
	w = w - 180;
	$('.box-simulator').css('height', w);
	
	
	$(window).resize(function() {
	    var w = $(window).height();
		w = w - 180;
		$('.box-simulator').css('height', w);
	});
	
	

});







