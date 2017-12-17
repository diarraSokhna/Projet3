

<html>
<head>
<title>Escalade</title>
<link rel="shortcut icon" type="image/x-icon"
	href="/escalade-presentation/img/favicon.png" />
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="/escalade-presentation/js/js.js"></script>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<jsp:useBean id="site" class="fr.escalade.beans.Site" scope="session" />

	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>Ajouter un site</h2>
			</div>
			<div class="panel-body">
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="panel-body">
					
					
					
<form method="post" accept-charset="UTF-8" action="" enctype="multipart/form-data"  >
						<div class="panel-body form-horizontal payment-form">
				 <fieldset>
									
			
						<%@ include file="ajoutSite.jsp"%>
						<hr/>
						<%@ include file="ajoutSecteur.jsp"%>
						<hr/>
						<%@ include file="ajoutVoie.jsp"%>

     <hr/>
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
									</fieldset>
</div>

</form>

						
					</div>
				</div>
			</div>

		</div>

	</div>


</body>
</html>