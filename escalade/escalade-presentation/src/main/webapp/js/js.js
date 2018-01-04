$(document).ready(function() {
	$('#idpays').change(function(event) {
		var idpays = $("select#idpays").val();
		 // Autant utiliser les fonctions AJAX de jQuery
	    $.ajax({
	        type: 'GET',
	        url: 'AjoutSite', // L'URL de la servlet de recherche
	        data: { // Les parmètres envoyées
	        	idpays : idpays
	        },
	        success: function(response) {
	            // On vide la liste
	            $('#idville').empty();
	        }
	   });
	    
	});
});

