/**
 * 
 */

$('document').ready(function() {
	
	$('.table #editButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(state, status){
			console.log(state);
			$('#idEdit').val(state.id);
			$('#nameEdit').val(state.name);
			$('#phoneEdit').val(state.phone);
			$('#emailEdit').val(state.email);
			$('#typeEdit').val(state.type);
		});			
		$('#editModal').modal("show");		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(state, status){
			$('#idDetails').val(state.id);
			$('#nameDetails').val(state.name);
		});			
		$('#detailsModal').modal();		
	});	
	
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();		
	});	
});