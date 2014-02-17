$(document).ready(function(){
	$("#registername").click(function(){
		if ($("#registertop").is(":hidden")){ /* Si le menu d'inscription est cach� alors lorsque l'on clique... */
			
			$("#registertop").slideDown("slow");  /* Permet de descendre le menu inscription */
			$('#global').css({'position': 'fixed','left': '0px','top': '0px','background-color': 'black','height': '100%','width':'100%','z-index': '100','opacity': '0.7'});
		}
		else{
			$("#registertop").slideUp("slow");
			$('#global').css({'position': 'fixed','left': '0px','top': '0px','background-color': '','height': '','width':'100%','z-index': '100','opacity': ''});
		}
	});
	
	$("#loginname").click(function(){
		if ($("#logintop").is(":hidden")){ /* Si le menu d'inscription est cach� alors lorsque l'on clique... */
			
			$("#logintop").slideDown("slow");  /* Permet de descendre le menu inscription */
			$('#global').css({'position': 'fixed','left': '0px','top': '0px','background-color': 'black','height': '100%','width':'100%','z-index': '100','opacity': '0.7'});
		}
		else{
			$("#logintop").slideUp("slow");
			$('#global').css({'position': 'fixed','left': '0px','top': '0px','background-color': '','height': '','width':'100%','z-index': '100','opacity': ''});
		}
	});
	
	$(".UDecide").click(function(){
		//Si l'on est pas connecté
		if($(this).attr("data-user_pseudo") == "null" || $(this).attr("data-user_pseudo") == ""){
			if($("#menu_insc_log_vote").is(":hidden")){
				$("#menu_insc_log_vote").slideDown("slow");
				$('#global').css({'position': 'fixed','left': '0px','top': '0px','background-color': 'black','height': '100%','width':'100%','z-index': '100','opacity': '0.7'});
			}
			else{
				$("#menu_insc_log_vote").slideUp("slow");
				$('#global').css({'position': 'fixed','left': '0px','top': '0px','background-color': '','height': '','width':'100%','z-index': '100','opacity': ''});
			}
		}
		else{
			if($(this).attr("class") == "vote_plus UDecide"){
				document.location.href="vote?id="+$(this).attr("data-cleeImg")+"&vote="+$(this).attr("data-vote");
			}
			else{
				document.location.href="vote?id="+$(this).attr("data-cleeImg")+"&vote="+$(this).attr("data-vote");
			}
		}
	});
	
	$(".UrChoice").click(function(){
		//Si l'on est pas connecté
		if($(this).attr("data-user_pseudo") == "null" || $(this).attr("data-user_pseudo") == ""){
			if($("#menu_insc_log_vote").is(":hidden")){
				$("#menu_insc_log_vote").slideDown("slow");
				$('#global').css({'position': 'fixed','left': '0px','top': '0px','background-color': 'black','height': '100%','width':'100%','z-index': '100','opacity': '0.7'});
			}
			else{
				$("#menu_insc_log_vote").slideUp("slow");
				$('#global').css({'position': 'fixed','left': '0px','top': '0px','background-color': '','height': '','width':'100%','z-index': '100','opacity': ''});
			}
		}
		else{
			if($(this).attr("class") == "modify UrChoice"){
				//On met les valeurs de l'image � modifier dans les div
				$("#imgFileInputEdit").attr("value", $(this).attr("data-imgLink"));
				$("#imgSnapchatEdit").attr("src", "images/uploads/"+$(this).attr("data-imgLink"));
				$("#imgSnapchatEdit").attr("style", "max-width: 300px;");
				$("#imgSnapchatEdit").show();
				$("#cleePrevImg").attr("value", $(this).attr("data-cleeImg"));
				$("#extPrevImg").attr("value", $(this).attr("data-extImg"));
				$("#titreImgEdit").attr("value", $(this).attr("data-imgTitre"));
				$("#textImgEdit").attr("value", $(this).attr("data-imgTexte"));
				$("#categorie_selectEdit option[value='" +$(this).attr("data-idCategorie") + "']").attr("selected", "selected");
				
				$("#editImage").slideDown("slow");
				$('html, body').animate({
                    scrollTop: $("#editImage").offset().top
                }, 2000);
			}
			else{
				//On delete l'image en ajax 
				$.ajax({ url:"ajaxdeleteimage", type:"POST", 
					data:{cleeImg:$(this).attr("data-cleeImg"), idUser:$(this).attr("data-idUser")}
				});
				$(this).parent().parent().parent().hide("slow");
			}
		}
	});
	
	$("#global").click(function(){	/* Si l'on clique hors des menus alors, on remonte tous et on enl�ve l'opacit� du fond */
		$("#registertop").slideUp("slow");
		
		$("#logintop").slideUp("slow");
		
		$("#menu_insc_log_vote").slideUp("slow");
		
		$('#global').css({'position': 'fixed','left': '0px','top': '0px','background-color': '','height': '','width':'100%','z-index': '100','opacity': ''});
	});
});