jQuery(document).ready(function($) {
 
	$('#crunchifyMessage').html("<h4>This message is coming from 'indexPage.js' file...</h4>")


});

	
	function showManual() {
	var instructionsWrapper = $('.instructionsWrapper');
	instructionsWrapper.css('z-index', 100);
	instructionsWrapper.css('pointer-events', 'auto');
	instructionsWrapper.animate({ opacity: 1 }, 300, 'linear');
	}

	function hideManual(ev) {
	var instructionsWrapper = $('.instructionsWrapper');
	instructionsWrapper.animate({ opacity: 0 }, 300, 'linear', onAnimationDone);

	function onAnimationDone() {
		instructionsWrapper.css('z-index', -100);
		instructionsWrapper.css('pointer-events', 'none');
	}
	var _manualIsVisible = false;

	$("manualButton").on(clickEventType, showManual);
	$(".instructionsWrapper").on(clickEventType, hideManual);
	$(".instructionsCloseButton").on(clickEventType, hideManual);
	$(".instructions").on(clickEventType, function(ev) { ev.stopPropagation(); }); /* contains the instruction text, do nothing*/

}