<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<!-- let's add tag srping:url -->
<spring:url value="/resources/welcome.css" var="welcomeCSS" />
<spring:url value="/resources/welcome.js" var="welcomeJS" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link href="${welcomeCSS}" rel="stylesheet" />
<script src="${welcomeJS}"></script>
<!-- Finish adding tags -->


<title> Manage the Virtual Power Plant and prevent a blackout!</title>
<style type="text/css">
body {
 	background-image: url('https://cdn.crunchify.com/bg.png');
} 

/* body { 
  background-image: url('../resources/virtual_power_plant.png');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center; 
} */
</style>
</head>
<body>${message}

<div id="intro">
	<div id="gameTitleAndSubHeadline">
					<h1 id="gameTitle">Demand Side Management </h1>
					
	</div>
	<div id="menuButtonsWrapper" style="display: block;">
		
		<h1 id="startButton" class="btnBig" onclick="location.href='simulation.html';">Start Simulation</h1>
		<h1 class="manualButton btnBig" styletop="420" onclick="showManual()" >Instructions</h1>
		<h1> ${currentBattery}</h1>
	</div>
</div>
</body>
</html>