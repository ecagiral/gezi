$(document).ajaxError(function(event, jqXHR, ajaxOptions) {
	if(jqXHR.status === 403){
		$('.modal').modal('hide');
		$("#loginModal").modal();
	}
});

$(document).ready(function(){	
	
	
});