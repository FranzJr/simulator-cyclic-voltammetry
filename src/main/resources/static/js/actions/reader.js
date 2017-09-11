var data;
var pointSeparator;
var decimalSeparator;
var ignoreErros;

$( document ).ready(function() {
	
	$('#experimental-data-btn').click(function(){
		$('#myModal').on('shown.bs.modal', function () {
			  $('#myInput').focus();
		});
	});
	
	$('#experimental-btn').click(function(){
		
		pointSeparator = $('#poinSeparator').val() ? $('#poinSeparator').val() : ",";
		decimalSeparator = $('#decimalSeparator').val() ? $('#decimalSeparator').val() : ".";
		
		if (	$('#ignoreErrosYes:checked').val()) {
			ignoreErros = true;
		}else if ($('#ignoreErrosNo:checked').val()) {
			ignoreErros = false;
		}else{
			ignoreErros = false;
		}
		
		$('#experimental-data').click();
	});
	
	var fileInput = document.getElementById('experimental-data');

	fileInput.addEventListener('change', function(e) {
			
		var file = fileInput.files[0];
		var textType = /text.*/;

		if (file.type.match(textType)) {
			
			var reader = new FileReader();
			
			reader.onload = function(e) {
				
				$('#load').show();
				
				setTimeout(function() {
					logMessage( "Reading file: " + file.name );
				
					$("#table-experimental-data").find("tr:gt(0)").remove();
					
					var errorLines = 0;
					
					data = '[';
					
					var lines = reader.result.split("\n");								
					$.each(lines, function(lineRow){
						var row = lines[lineRow].split(pointSeparator);
						
						if (decimalSeparator != ".") {
							row[0] = row[0] ? row[0].replace(decimalSeparator,'.') : row[0];
							row[1] = row[1] ? row[1].replace(decimalSeparator,'.') : row[1];
						}
						
						var errorClass = '';
						if(    row[0] == null 
							|| row[1] == null
							|| !$.isNumeric( row[0] )
							|| !$.isNumeric( row[1] )){
							errorClass = 'error';
							errorLines = errorLines + 1;
						}
						
						if (errorClass == 'error') {
							if (!ignoreErros) {
								data = data + '[' + row[0] +','+ row[1] +'],';
							}
						}else{
							data = data + '[' + row[0] +','+ row[1] +'],';
						}
						
						
		              	$('#table-experimental-data tr:last').after(
						'<tr>'+
					      '<th scope="row" class="row-center '+errorClass+'">'+lineRow+'</th>'+
					      '<td class="row-center">'+row[0]+'</td>'+
					      '<td class="row-center">'+row[1]+'</td>'+
					    '</tr>'
						);	
						
		           	});	
		           	data = data.substring(0, data.length - 1) + "]";
		           		 
				   	if(errorLines > 0){
					   	logErrorMessage(errorLines + " lines were found with errors");
				   	}
		           		           	     	
		           	try {
				   		$('.text-simulator').hide();
						$('#center-simulator').removeClass('body-simulator');
						
						$("#table-experimental-data").show();
			           	
					    plotChart(jQuery.parseJSON(data));
						logMessage( "Plot chart of experimental data" );				
					}
					catch(err) {
						plotChart(null);
					    logError("It was not possible plot the graph, see details in log");
					}
					
					$('#load').hide();
				}, 500);
				
				
			}
			reader.readAsText(file);	
		} else {
			logError("File not supported!");
		}
	});
		
});