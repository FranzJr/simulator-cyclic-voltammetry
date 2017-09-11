$( document ).ready(function() {
	$('#action-data').click(function(){
		showOrHideButtons('experimental-data-btn')
	});
	$('#action-simulation').click(function(){
		showOrHideButtons('simulation-data-btn')
	});
	$('#action-fitting').click(function(){
		showOrHideButtons('fitting-data-btn')
	});
});

function showOrHideButtons(idButton) {
	
	$('.btn-action').addClass('hidden');
	$('.left-simulator').addClass('hidden');
//	$('.text-simulator').hide();
	$('.btn-footer').addClass('inactive');
	
	switch (idButton) {
	case 'experimental-data-btn':
		$('#experimental-data-btn').removeClass('hidden');
		$('#experimental-data-box').removeClass('hidden');
		$('#action-data').removeClass('inactive');
		break;

	case 'simulation-data-btn':
		$('#simulation-data-btn').removeClass('hidden');
		$('#simulation-data-box').removeClass('hidden');
		$('#action-simulation').removeClass('inactive');
		break;
		
	case 'fitting-data-btn':
		$('#fitting-data-btn').removeClass('hidden');
		$('#experimental-data-box').removeClass('hidden');
		$('#action-fitting').removeClass('inactive');
		break;

	default:
		break;
	}
}

