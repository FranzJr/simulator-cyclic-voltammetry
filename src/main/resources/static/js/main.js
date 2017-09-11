$(document).ready(function(){
	$('#carousel').slick({
		arrows: false,
		fade: true,
		autoplay: true,
		autoplaySpeed: 35000,
		dots: true,
		customPaging : function(slider, i) {
			var pagination = $(slider.$slides[i]).data('pagination');
			return pagination;
		},
	});
                  
//    $('#slick-slide00').hide();
});
