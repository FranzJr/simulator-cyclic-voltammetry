$( document ).ready(function() {

	$( "#action-log" ).click(function() {
		
		if($('.box-log').is(':visible')){
			$('.box-log').hide();
			$('.box-log').removeClass('col-sm-3');
			$('.box-chart').removeClass('col-sm-6');
			$('.box-chart').addClass('col-sm-9');
			$('#action-log').addClass('inactive');				
			chartReflow();
		}else{
			$('.box-log').show();
			$('.box-log').addClass('col-sm-3');
			$('.box-chart').addClass('col-sm-6');
			$('.box-chart').removeClass('col-sm-9');
			$('#action-log').removeClass('inactive');	
			chartReflow();		
		}		
	});

});

function logMessage(message){
	$( "#console-log" ).append( "<p> ->"+message+"</p>" );
}

function logErrorMessage(message){
	$( "#console-log" ).append( "<p class='error'>  -> "+message+"</p>" );
}

function logError(message){
	if(!$('#console-log').is(':visible')){
		swal("Error", message, "error")
		$( "#console-log" ).append( "<p class='error'>  -> "+message+"</p>" );
	}else{
		$( "#console-log" ).append( "<p class='error'>  -> "+message+"</p>" );
	}
}