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
		});			
		$('#editModal').modal("show");		
	});
	
	$('.table #editButton2').on('click',function(event){		
		event.preventDefault();		
		console.log("ModelId");
		console.log($(this).attr('href'));
		var href= "/states/findById/?id="+$(this).attr('href');		
		$.get(href, function(state, status){
			console.log(state);
			$('#idEdit').val(state.id);
			$('#nameEdit').val(state.name);
		});			
		//$('#editModal').modal("show");		
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