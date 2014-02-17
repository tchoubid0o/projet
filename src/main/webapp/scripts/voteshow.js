$('.img_index').mouseover(function() {
	var id = $(this).attr('id');
	$('#img_' + id).show();
	$('#vote_' + id).show();
});

$('.img_index').mouseout(function() {
	var id = $(this).attr('id');
	$('#img_' + id).hide();
	$('#vote_' + id).hide();
});
