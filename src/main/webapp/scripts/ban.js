var i = 0;
window.setInterval(function(){
	i-=3; //vitesse
	i%=2000; //largeur bannière 
	$("#banniere").css('margin-left',i+'px');
}, 40);

$(window).scroll(function(){
	posScroll=$(document).scrollTop();
	
	250 <= posScroll ? $("#goTop").fadeIn() : $("#goTop").fadeOut();
});

$("#goTop").click(function(b){
	b.preventDefault();
	$("html, documentElement").animate({scrollTop:0},"slow");
});