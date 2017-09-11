var simulationData;

$(document).ready(function() {
	// $('#action-simulation').click(function(){
	// swal({
	// title: "Important Message",
	// text: "This functionality is not yet developed.",
	// imageUrl:
	// '../../img/progress.png'
	// });
	// });
	
	$('#simulation-data-btn').click(function() {

		var dto = {
			"rateConstantK" : $('#rateConstantK').val(),
			"concentrationRedoxC" : $('#concentrationRedoxC').val(),
			"diffusionConstantD" : $('#diffusionConstantD').val(),
			"scanRateV" : $('#scanRateV').val(),
			"upperPotentialVU" : $('#upperPotentialVU').val(),
			"lowerPotentialVL" : $('#lowerPotentialVL').val(),
			"cycleNumberCN" : $('#cycleNumberCN').val()
		}

		var request = $.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			url : "play-simulation",
			data : JSON.stringify(dto),
		});

		request.done(function(msg) {

			$('#load').show();

			setTimeout(function() {
				
				logMessage(msg.description);
//				console.log("previous data :: "  + data)
//				plotChart(msg.object);
				
				$('.text-simulator').hide();
				$('#center-simulator').removeClass('body-simulator');
				
				if (data) {
					plotChartOn(msg.object, data);
				}else{
					plotChart(msg.object);
				}
				
				logMessage( "Plot chart of simulated data" );	
				
				simulationData = msg.object;
				
				var lines;
				for (var i = 0; i < simulationData.length; i++) {
					var object = simulationData[i];
					if (lines) {
						lines = lines + object[0] + "%2C" + object[1] + "%0A";
					}else{
						lines = object[0] + "%2C" + object[1] + "%0A";
					}
					
				}
				
				$("#simulation-data-file").attr("href", "data:application/octet-stream," + lines)
				$("#simulation-data-file").show();
				
				$('#load').hide();
				
			}, 800);
		});

		request.fail(function(jqXHR, textStatus) {
			logError("Request failed: " + textStatus);
		});

	});

});