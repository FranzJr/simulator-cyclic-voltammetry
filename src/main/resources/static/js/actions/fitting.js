$(document).ready(function() {

	$('#fitting-data-btn').click(function() {

		if (data) {

			var inputString = jQuery.parseJSON(data);

			var request = $.ajax({
				type : "POST",
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				url : "play-fitting",
				data : JSON.stringify(inputString),
			});

			request.done(function(msg) {

				$('#load').show();

				setTimeout(function() {

					logMessage(msg.description);

					if (simulationData) {
						plotChartIn(simulationData, msg.object);
					}else{
						plotChart(msg.object);
					}

					logMessage("Plot chart of fitting data");

					// data = msg.object;

					$('#load').hide();

				}, 800);
			});

			request.fail(function(jqXHR, textStatus) {
				logError("Request failed: " + textStatus);
			});
		}else{
			logError("Error: Must to put experimental data");
		}
	});

});