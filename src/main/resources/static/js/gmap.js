jQuery(function($){

	var longitude = 40.748817;
	var latitude = -73.985428;
	var canvas = "map";

	
	function randing_map(canvas, lan, lat){			
			var myLatlng = new google.maps.LatLng(lan,lat);
			var myOptions = {
					zoom: 15,
					scrollwheel : false,
					mapTypeControl: false,
					streetViewControl:false,
					center: myLatlng,
					mapTypeId: google.maps.MapTypeId.ROADMAP,
				}			
			var map = new google.maps.Map( document.getElementById(canvas), myOptions );
			var marker = new google.maps.Marker({
			    position : myLatlng,
			    map      : map,
			});
			
			//https://snazzymaps.com/style/132/light-gray
			var styles = [
						    {
						        "featureType": "water",
						        "elementType": "geometry.fill",
						        "stylers": [
						            {
						                "color": "#d3d3d3"
						            }
						        ]
						    },
						    {
						        "featureType": "transit",
						        "stylers": [
						            {
						                "color": "#808080"
						            },
						            {
						                "visibility": "off"
						            }
						        ]
						    },
						    {
						        "featureType": "road.highway",
						        "elementType": "geometry.stroke",
						        "stylers": [
						            {
						                "visibility": "on"
						            },
						            {
						                "color": "#b3b3b3"
						            }
						        ]
						    },
						    {
						        "featureType": "road.highway",
						        "elementType": "geometry.fill",
						        "stylers": [
						            {
						                "color": "#ffffff"
						            }
						        ]
						    },
						    {
						        "featureType": "road.local",
						        "elementType": "geometry.fill",
						        "stylers": [
						            {
						                "visibility": "on"
						            },
						            {
						                "color": "#ffffff"
						            },
						            {
						                "weight": 1.8
						            }
						        ]
						    },
						    {
						        "featureType": "road.local",
						        "elementType": "geometry.stroke",
						        "stylers": [
						            {
						                "color": "#d7d7d7"
						            }
						        ]
						    },
						    {
						        "featureType": "poi",
						        "elementType": "geometry.fill",
						        "stylers": [
						            {
						                "visibility": "on"
						            },
						            {
						                "color": "#ebebeb"
						            }
						        ]
						    },
						    {
						        "featureType": "administrative",
						        "elementType": "geometry",
						        "stylers": [
						            {
						                "color": "#a7a7a7"
						            }
						        ]
						    },
						    {
						        "featureType": "road.arterial",
						        "elementType": "geometry.fill",
						        "stylers": [
						            {
						                "color": "#ffffff"
						            }
						        ]
						    },
						    {
						        "featureType": "road.arterial",
						        "elementType": "geometry.fill",
						        "stylers": [
						            {
						                "color": "#ffffff"
						            }
						        ]
						    },
						    {
						        "featureType": "landscape",
						        "elementType": "geometry.fill",
						        "stylers": [
						            {
						                "visibility": "on"
						            },
						            {
						                "color": "#efefef"
						            }
						        ]
						    },
						    {
						        "featureType": "road",
						        "elementType": "labels.text.fill",
						        "stylers": [
						            {
						                "color": "#696969"
						            }
						        ]
						    },
						    {
						        "featureType": "administrative",
						        "elementType": "labels.text.fill",
						        "stylers": [
						            {
						                "visibility": "on"
						            },
						            {
						                "color": "#737373"
						            }
						        ]
						    },
						    {
						        "featureType": "poi",
						        "elementType": "labels.icon",
						        "stylers": [
						            {
						                "visibility": "off"
						            }
						        ]
						    },
						    {
						        "featureType": "poi",
						        "elementType": "labels",
						        "stylers": [
						            {
						                "visibility": "off"
						            }
						        ]
						    },
						    {
						        "featureType": "road.arterial",
						        "elementType": "geometry.stroke",
						        "stylers": [
						            {
						                "color": "#d6d6d6"
						            }
						        ]
						    },
						    {
						        "featureType": "road",
						        "elementType": "labels.icon",
						        "stylers": [
						            {
						                "visibility": "off"
						            }
						        ]
						    },
						    {
						        "featureType": "poi",
						        "elementType": "geometry.fill",
						        "stylers": [
						            {
						                "color": "#dadada"
						            }
						        ]
						    }
						];
			var infowindow = new google.maps.InfoWindow({
				content:"<div class='map_adresse'><div class='map_address'><span class='address'>Address : </span>1401 South Grand Avenue Los Angeles, CA 90015</div> <div class='map_tel'><span class='tel'>Phone : </span>(213) 748-2411</div></div>"
			});	
			
			map.setOptions({styles: styles});

			google.maps.event.addListener(marker, 'click', function() {
			  infowindow.open(map,marker);
			});
	}
	randing_map(canvas, longitude, latitude);

});