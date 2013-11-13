$(document).ready(function() {
	$("#followBtn").on('click', function() {
		var targetSpitter = $("#currentSpitter").val();
		$.ajax({
			url : "/spitter-web/spitter/" + targetSpitter + "?follow",
			type : "POST",
			// data : "targetSpitter="+targetSpitter,
			success : function(data, textStatus, jqXHR) {
				alert("You now follow " + targetSpitter + "!");
			},
			error : function(data, textStatus, errorThrown) {
				alert(data);
				alert(textStatus);
				alert(errorThrown);
			}
		});
	});
});