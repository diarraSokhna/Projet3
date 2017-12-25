<html>
<head>
<title>Escalade</title>
<link rel="shortcut icon" type="image/x-icon"
	href="/escalade-presentation/img/favicon.png" />


</head>
<body>
	<%@ include file="../WEB-INF/vue/menu.jsp"%>
	

	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>Ajouter un site</h2>
			</div>
			<div class="panel-body">
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="panel-body">
					
							<%@ include file="ajoutSite.jsp"%>
					
						<%@ include file="ajoutSecteur.jsp"%>
					
						<%@ include file="ajoutVoie.jsp"%>
					
<form method="post" accept-charset="UTF-8" action="AjoutSiteSecteurVoie" name="site" id="site" style="display:none" >

						<div class="panel-body form-horizontal payment-form">

  
<div class="form-group">
										<div class="col-sm-12 text-right">
											<button type="submit" 
												class="btn btn-default preview-add-button">Enregistrer le site
											</button>

											<button type="reset"
												class="btn btn-default preview-add-button">Annuler
											</button>
										</div>
									</div>
								
									
</div>

</form>

						
					</div>
				</div>
			</div>

		</div>

	</div>


</body>
</html>