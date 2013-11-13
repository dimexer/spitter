$(document).ready(function() {
	$("#publish").on('click', function() {
		var text = $("#spittleArea").val();
		if (!text){
			alert("Oops, missing text!");
			return;
		}
		$.ajax({
			url : "/spitter-web/spittle/ajaxpublish",
			type : "POST",
			data : "spittle="+text,
			success : function(data, textStatus, jqXHR) {
				alert("SUCCESS!");
			},
			error : function(data, textStatus, errorThrown) {
				alert(data);
				alert(textStatus);
				alert(errorThrown);
			}
		});
	});
});