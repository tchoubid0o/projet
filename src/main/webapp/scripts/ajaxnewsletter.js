$(document).ready(function(){
  $("#newsletter_form").submit(function(){
	  event.preventDefault();  
	  $.ajax({ url:"ajaxnewsletter", type:"POST", 
			data: $("#newsletter_form").serialize()
			}).done(function (response){		  		
				$("#newsletter_result").html("<div style='color: #2db3e8;text-align: center; margin-top: 15px;'>"+response+"</div>");
				$("#newsletter_form").slideToggle();
				$("#newsletter_result").slideToggle();	
			})
			.fail(function (response){		
		  		$("#newsletter_result").html("<div style='color: red;text-align: center; margin-top: 15px;'>"+response+"</div>");
		  	});
  }); 	
});