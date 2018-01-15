$(document).ready(
		function() {
			$('#idpays').change(
					function(event) {
						var idpays = $("select#idpays").val();
						$.ajax({
							type : 'GET',
							url : 'ChargerVille',
							data : {
								idpays : idpays
							},
							success : function(response) {
								// On vide la liste
								$('#idville').empty();
								response.forEach(function(ville) {
									$('#idville').append(
											'<option value="' + ville.id_ville
													+ '">' + ville.nom_ville
													+ '</option>')
								})

							}

						});

					});
		});
