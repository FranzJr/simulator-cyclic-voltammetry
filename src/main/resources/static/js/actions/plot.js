$( document ).ready(function() {
	$('#action-concentration-plot').click(function(){
		swal({
		  title: "Important Message",
		  text: "This functionality is not yet developed.",
		  imageUrl: '../../img/progress.png'
		});
	});
});

var chartDataExp;

function plotChart(data){
	chartDataExp = Highcharts.chart('chart', {
	    chart: {
		    borderRadius: 10,
	        type: 'spline',
	        inverted: false,
	        style: {
	            fontFamily: 'inherit'
	        }
	    },
	    credits:	{enabled:false},
	    title: {
	        text: 'Experimental  data'
	    },
	    subtitle: {
	        text: ''
	    },
	    xAxis: {
	        reversed: false,
	        title: {
	            enabled: true,
	            text: 'Potential (V)'
	        },
	        labels: {
	            formatter: function () {
	                return this.value + '';
	            }
	        },
	        maxPadding: 0.05,
	        showLastLabel: true
	    },
	    yAxis: {
	        title: {
	            text: 'Current (A)'
	        },
	        labels: {
	            formatter: function () {
	                return this.value + '';
	            }
	        },
	        lineWidth: 2
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        headerFormat: '<b>{series.name}</b><br/>',
	        pointFormat: '{point.x} : {point.y}'
	    },
	    plotOptions: {
	        spline: {
	            marker: {
	                enable: false
	            }
	        }
	    },
	    series: [{
		    lineWidth: 4,
	        name: 'Point',
	        data: data
	    }]
	});
}

function plotChartOn(dataSimulation, dataExperimental){
	chartDataExp = Highcharts.chart('chart',{
		chart:     {
            borderRadius: 10,
	        type: 'spline',
	        inverted: false,
	        style: {
	            fontFamily: 'inherit'
	        }
		},
		credits:	{enabled:false},
	    title:		{text:'Experimental Data vs Simulated Data'},
	    xAxis: {
	        reversed: false,
	        title: {
	            enabled: true,
	            text: 'Potential (V)'
	        },
	        labels: {
	            formatter: function () {
	                return this.value + '';
	            }
	        },
	        maxPadding: 0.05,
	        showLastLabel: true
	    },
	    yAxis: {
	        title: {
	            text: 'Current (A)'
	        },
	        labels: {
	            formatter: function () {
	                return this.value + '';
	            }
	        },
	        lineWidth: 2
	    },
	    plotOptions: {
	        series: {
	            connectNulls: true
	        }
	    },
	    series: [{
	    	dataLabels: {
				y:15,
				color:'rgba(131,180,223,1)',
				style: { fontWeight:'bold' }
			},
			name: 'Experimental Data',
	        color:'rgba(131,180,223,1)',
		    data:jQuery.parseJSON(dataExperimental)			
		},{
			dataLabels: {
				y:0,
				color:'red',
				style: { fontWeight:'bold' }
			},
			name: 'Simulated Data',
		    color:'red',
		    data:dataSimulation		    
		}]
	});
}

function plotChartIn(dataSimulation, dataExperimental){
	chartDataExp = Highcharts.chart('chart',{
		chart:     {
            borderRadius: 10,
	        type: 'spline',
	        inverted: false,
	        style: {
	            fontFamily: 'inherit'
	        }
		},
		credits:	{enabled:false},
	    title:		{text:'Experimental Data vs Simulated Data'},
	    xAxis: {
	        reversed: false,
	        title: {
	            enabled: true,
	            text: 'Potential (V)'
	        },
	        labels: {
	            formatter: function () {
	                return this.value + '';
	            }
	        },
	        maxPadding: 0.05,
	        showLastLabel: true
	    },
	    yAxis: {
	        title: {
	            text: 'Current (A)'
	        },
	        labels: {
	            formatter: function () {
	                return this.value + '';
	            }
	        },
	        lineWidth: 2
	    },
	    plotOptions: {
	        series: {
	            connectNulls: true
	        }
	    },
	    series: [{
	    	dataLabels: {
				y:15,
				color:'rgba(131,180,223,1)',
				style: { fontWeight:'bold' }
			},
			name: 'Experimental Data',
	        color:'rgba(131,180,223,1)',
		    data:dataExperimental			
		},{
			dataLabels: {
				y:0,
				color:'red',
				style: { fontWeight:'bold' }
			},
			name: 'Simulated Data',
		    color:'red',
		    data:dataSimulation		    
		}]
	});
}

function chartReflow(){
	if(chartDataExp != null){
		chartDataExp.reflow();
	}
}