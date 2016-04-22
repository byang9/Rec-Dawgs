var bgImageArray = ["../img/soccer.png", "../img/baseball.png", "../img/football.png"];
secs = 4;
bgImageArray.forEach(function(img){
    new Image().src = img; 
    // caches images, avoiding white flash between background replacements
});

function backgroundSequence() {
	window.clearTimeout();
	var k = 0;
	for (i = 0; i < bgImageArray.length; i++) {
		setTimeout(function(){ 
			document.getElementById("introid").style.background = "url(" + bgImageArray[k] + ") no-repeat center center scroll";
		if ((k + 1) === bgImageArray.length) { setTimeout(function() { backgroundSequence() }, (secs * 1000))} else { k++; }			
		}, (secs * 1000) * i)	
	}
}
backgroundSequence();