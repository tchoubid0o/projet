function rightPressed() {
	var nextImg = $("#nextImg").html();
	document.location.replace("image?id="+nextImg+"#img");
}

function leftPressed() {
	var prevImg = $("#prevImg").html();
	document.location.replace("image?id="+prevImg+"#img");
}

document.onkeydown = function(evt) {
    evt = evt || window.event;
    switch (evt.keyCode) {
        case 37:
            leftPressed();
            break;
        case 39:
            rightPressed();
            break;
    }
};