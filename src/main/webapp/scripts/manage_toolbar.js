$("#showhidebar").click(function(){
   function togglePicture(){
		if($("#showhidebar").attr('data-alt') == "hide_header"){
			$("#button_ban").attr('style',"background: url('images/top_show.png') 0px 2px no-repeat;");
			$("#showhidebar").attr('data-alt','show_header');
		}
		else{
			$("#button_ban").attr('style',"background: url('images/top_hide.png') 0px 0px no-repeat;");
			$("#showhidebar").attr('data-alt','hide_header');
		}
   }
   $("#banniere").slideToggle();
   togglePicture();
});
