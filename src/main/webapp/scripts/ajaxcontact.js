$(document).ready(function(){
	$("#contact_form").submit(function(){
		event.preventDefault();  
		$.ajax({ url:"ajaxcontact", type:"POST", 
			data: $("#contact_form").serialize()
		}).done(function (response){		  		
			$("#contact_result").html("<div style='color: #2db3e8;text-align: center; margin-top: 15px;'>"+response+"</div>");
			$("#contact_form").slideToggle();
			$("#contact_result").slideToggle();	
		})
		.fail(function (response){		
			$("#contact_result").html("<div style='color: red;text-align: center; margin-top: 15px;'>"+response+"</div>");
		});
	}); 	
});