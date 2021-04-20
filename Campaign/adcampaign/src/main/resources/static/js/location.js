/**
 * 
 */

$('document').ready(function() {
	
	$('.table #editButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(location, status){
			//console.log(location)
			$('#idEdit').val(location.id);
			$('#ddlStateEdit').val(location.state.id);
			$('#ddlCityEdit').val(location.city.id);
			$('#nameEdit').val(location.name);
		});			
		$('#editModal').modal();		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(state, status){
			$('#idDetails').val(state.id);
			$('#ddlCountryDetails').val(state.countryid);			
			$('#nameDetails').val(state.name);
			$('#detailsDetails').val(state.details);
			$('#lastModifiedByDetails').val(state.lastModifiedBy);
			//$('#lastModifiedDateDetails').val(state.lastModifiedDate.substr(0,19).replace("T", " "));
		});			
		$('#detailsModal').modal();		
	});	
	
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();		
	});	
	
	
	$('#ddlStateEdit').on('change',function(event){		
		event.preventDefault();
		//alert($(this));
		//console.log($(this));
		//console.log($(this).val());
		var href= "/citys/findByStateId/?stateId="+$(this).val();	
		var that = $("#ddlCityEdit");
		//console.log(href);
		$.get(href, function(locations, status){
			//console.log(locations)
			
			$(that).empty();
			$(locations).each( function(index, location){
				//console.log(location.id);
				var content='<option value="' + location.id + '">' + location.name + '</option>';
				
				console.log(content);
				$(that).append(content);
				
			});
		});			
	});
	
});