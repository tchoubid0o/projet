function readURL1(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imgSnapchat').attr('src',e.target.result);
			$('#imgSnapchat').attr('style','max-width: 300px;');
		};

		reader.readAsDataURL(input.files[0]);
	}
}

$("#imgFileInput").change(function() {
	readURL1(this);
});

function readURL2(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imgSnapchatEdit').attr('src',e.target.result);
			$('#imgSnapchatEdit').attr('style','max-width: 300px;');
		};

		reader.readAsDataURL(input.files[0]);
	}
}

$("#imgFileInputEdit").change(function() {
	readURL2(this);
});