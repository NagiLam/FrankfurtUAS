$(document).ready(function() {
	function getData() {
		return Math.round(Math.random() * (115 - 85) + 85);
	}

	function getDataEating() {
		return Math.round(Math.random() * (160 - 120) + 120);
	}

	function getDataExercising() {
		return Math.round(Math.random() * (80 - 40) + 40);
	}

	Plotly.plot('chart', [ {
		y : [ getData() ],
		type : 'line'
	} ]);

	var cnt = 0;
	
	function plotlyUpdate(){
		Plotly.extendTraces('chart', {
			y : [ [ getData() ] ]
		}, [ 0 ]);
		cnt++;
		if (cnt > 30) {
			Plotly.relayout('chart', {
				xaxis : {
					range : [ cnt - 30, cnt ]
				}
			});
		}
	}
	
	var t = setInterval(plotlyUpdate,1000);
	var m;
	var n;
	
	function plotlyUpdateEating(){
		Plotly.extendTraces('chart', {
			y : [ [ getDataEating() ] ]
		}, [ 0 ]);
		cnt++;
		if (cnt > 30) {
			Plotly.relayout('chart', {
				xaxis : {
					range : [ cnt - 30, cnt ]
				}
			});
		}
	}
	
	function plotlyUpdateExercising(){
		Plotly.extendTraces('chart', {
			y : [ [ getDataExercising() ] ]
		}, [ 0 ]);
		cnt++;
		if (cnt > 30) {
			Plotly.relayout('chart', {
				xaxis : {
					range : [ cnt - 30, cnt ]
				}
			});
		}
	}
//	setInterval(function() {
//		Plotly.extendTraces('chart', {
//			y : [ [ getData() ] ]
//		}, [ 0 ]);
//		cnt++;
//		if (cnt > 30) {
//			Plotly.relayout('chart', {
//				xaxis : {
//					range : [ cnt - 30, cnt ]
//				}
//			});
//		}
//	}, 1000);

	$("#insulinButton").click(function (e){
		clearInterval(m);
		clearInterval(n);
		t = setInterval(plotlyUpdate, 1000);
	});
	
	$("#glucagonButton").click(function (e){
		clearInterval(m);
		clearInterval(n);
		t = setInterval(plotlyUpdate, 1000);
	});
	
	$("#ddlViewBy").change(function() {
		var e = document.getElementById("ddlViewBy");
		var optionValue = e.options[e.selectedIndex].value;
		if (optionValue == 1) {
			clearInterval(m);
			clearInterval(n);
			t = setInterval(plotlyUpdate, 1000);

		}
		if (optionValue == 2) {
			alert("Emergency! Out of Power");
		}
		if (optionValue == 4) {
			clearInterval(t);
			clearInterval(n);
			m = setInterval(plotlyUpdateEating, 1000);
		}
		if (optionValue == 5) {
			clearInterval(t);
			clearInterval(m);
			n = setInterval(plotlyUpdateExercising, 1000);
		}

	});
});
//var battery = battery.on("update", function() {
//	$("baterry.bar").css("width", battery.life + "%")
//});
