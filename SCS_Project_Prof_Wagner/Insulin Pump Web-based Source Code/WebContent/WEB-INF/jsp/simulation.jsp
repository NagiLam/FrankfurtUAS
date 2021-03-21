<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<!-- let's add tag srping:url -->
<spring:url value="/resources/simulation.css" var="simulationCSS" />
<spring:url value="/resources/simulation.js" var="simulationJS" />
<spring:url value="/resources/plotly.min.js" var="plotlyJS" />

<link href="${simulationCSS}" rel="stylesheet" />
<script src="${simulationJS}"></script>
<script src="${plotlyJS}"></script>
<!-- Finish adding tags -->
<title>Insulin Pump Simulation</title>
</head>
<body>
	<div id="uibar">

		<div id="chart" style="height: 40%; width: 40%; position: center;">
			<script>
				
			</script>

		</div>

		<div id="pointsDisplayWrapper">
			<select id="ddlViewBy" style="width: 250px;">
				<option value="1" selected="selected">Normal Condition</option>
				<option value="2">Out of Power</option>
				<option value="3">Sensor Malfunction</option>
				<option value="4">Eating</option>
				<option value="5">Exercising</option>
			</select>
		</div>

		<a id="closeButton"
			href="http://localhost:8080/InsulinPump/welcome.html"> <img
			class="arrow" src="resources/Arrow.png"> <span
			id="backToMenuButtonText">Home</span>
		</a>
	</div>


	<div id="menuButtonsWrapper" style="display: inline-block;">
		<div id="insulinButtonWrapper" style="position: absolute;">
			<h1 id="insulinButton" class="manualButton btnBig">Insulin
				Injection</h1>
		</div>
<!-- 		<div id="batteryWrapper" style="height: 100px"> -->
<!-- 			<div class="progress progress-striped active" id="battery"> -->
<!-- 				<div class="progress-bar" role="progressbar" aria-valuemin="0" -->
<!-- 					aria-valuemax="100" style="width: 100%"></div> -->
<!-- 			</div> -->

<!-- 		</div> -->

		<div id="glucagonButtonWrapper" style="position: abosute;"></div>
		<h1  id="glucagonButton" class="manualButton btnBig">Glucagon
			Injection</h1>

	</div>

</body>

</html>