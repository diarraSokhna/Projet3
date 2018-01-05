$(document).ready(function() {
	$('#idpays').change(function(event) {
		var idpays = $("select#idpays").val();
		 // Autant utiliser les fonctions AJAX de jQuery
	    $.ajax({
	        type: 'GET',
	        url: 'ChargerVille', // L'URL de la servlet de recherche
	        data: { // Les parmètres envoyées
	        	idpays : idpays
	        },
	        success: function(response) {
	            // On vide la liste
	            $('#idville').empty();
	            response.forEach(function (ville){
	            	$('#idville').append('<option value="' + ville.id_ville +'">'+ ville.nom_ville + '</option>')})
	           
	        }
	        
	   });
	    
	});
});

