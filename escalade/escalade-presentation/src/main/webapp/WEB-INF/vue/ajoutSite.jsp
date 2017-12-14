

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
<jsp:useBean id="site" class="fr.escalade.beans.Site" scope="session"/>
	<div class="container">
		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse1">Ajouter site</a></h4>
				</div>
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="panel-body">

						<form method="post" accept-charset="UTF-8" action="AjoutSite"
							enctype="multipart/form-data">
							<div class="panel-body form-horizontal payment-form">
								<c:set var="pays" value="${ site.pays }" scope="request" />
								<c:set var="classement" value="${ site.classement }"
									scope="request" />
								<fieldset>

									<div class="form-group">
										<label for="nom" class="col-sm-3 control-label">Nom site<span class="requis">*</span>
										</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="nom" name="nom"
												value="<c:out value="${site.nomsite}"/>"> 
												 <span class="erreur">${form.erreurs['nom']}</span>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label">Classement</label>
										<div class="col-sm-9 selectContainer">
											<select class="form-control" name="idclass" id="idclass">
												<option value="0">Choisir un classement</option>
												<c:forEach var="classement" items="${ classements }">
													<option value="<c:out value="${ classement.id_class}" />"><c:out
															value="${ classement.libelle_class}" /></option>
												</c:forEach>
											</select> <span class="erreur">${form.erreurs['idclass']}</span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-xs-3 control-label">Pays</label>
										<div class="col-sm-9 selectContainer">
											<select class="form-control" name="idpays" id="idpays">
												<option value="0">Choisir un pays</option>
												<c:forEach var="pays" items="${ payss }">
													<option value="<c:out value="${ pays.idpays}" />"><c:out
															value="${ pays.nompays}" /></option>
												</c:forEach>
											</select> <span class="erreur">${form.erreurs['idpays']}</span> Si le
											pays n'existe pas vous pouvez <a href="">l'ajouter</a>
										</div>
									</div>
									<div class="form-group">
										<label for="photo" class="col-sm-3 control-label">Choisir
											photo</label>
										<div class="col-sm-9">
											<input type="file" class="form-control-file" id="image"
												name="image" value="<c:out value="${site.image}"/>"
												onchange="" multiple /> <span class="erreur">${form.erreurs['image']}</span>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-12 text-right">
											<button type="submit"
												class="btn btn-default preview-add-button">Ajouter
											</button>

											<button type="reset"
												class="btn btn-default preview-add-button">Annuler
											</button>
										</div>
									</div>
									<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

									<%-- 									    	<c:if test="${!empty sessionScope.sessionSite}"> --%>
									<!-- 								<p class="succes">Infos du site: -->
									<%-- 									${sessionScope.sessionSite.idsite} </p> --%>
									<%-- 							</c:if> --%>
								</fieldset>
							</div>

						</form>
						
<%-- 	<jsp:getProperty name="site" property="nomsite" /> --%>
	<c:out value="${site.nomsite}"/>
<%-- 	 <jsp:setProperty name="site" property="nomsite" value="Site 1"/> --%>
   
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse2">Ajouter secteur</a></h4>
				</div>
				<div id="collapse2" class="panel-collapse collapse">
					<div class="panel-body">
	<form method="post" accept-charset="UTF-8" action="AjoutSecteur">
							<div class="panel-body form-horizontal payment-form">
								<fieldset>
									<div class="form-group">
										<label for="nom" class="col-sm-3 control-label">Nom secteur <span class="requis">*</span></label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="nomsect" name="nomsect"
												value="<c:out value="${secteur.nomsect}"/>"> <span
												class="erreur">${form.erreurs['nomsect']}</span>
										</div>
									</div>
                                  <div class="col-sm-9">
											<input type="hidden" class="form-control" id="site" name="site"
												value="<c:out value="${sessionScope.sessionSite.idsite}"/>"> <span
												class="erreur">${form.erreurs['site']}</span>
										</div>
									<div class="form-group">
										<div class="col-sm-12 text-right">
											<button type="submit"
												class="btn btn-default preview-add-button">Ajouter
											</button>

											<button type="reset"
												class="btn btn-default preview-add-button">Annuler
											</button>
										</div>
									</div>
									<p class="${empty formSect.erreurs ? 'succes' : 'erreur'}">${formSect.resultat}</p>


									<c:if test="${!empty sessionScope.sessionSecteur}">
										<p class="succes">Infos du secteur:
											${sessionScope.sessionSecteur.idsect}</p>
									</c:if>
								</fieldset>
</div>
       </form>
						
						
									
	<form method="post" accept-charset="UTF-8" action="AjoutVoie">
								<div class="panel-body form-horizontal payment-form">
									<fieldset>
										<legend>Ajouter voie </legend>
										<div class="form-group">
											<label for="nom" class="col-sm-3 control-label">Nom voie <span class="requis">*</span>
											</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="nomvoie" name="nomvoie"
													value="<c:out value="${voie.nomvoie}"/>"> <span
													class="erreur">${form.erreurs['nomvoie']}</span>
											</div>
										</div>

										<div class="form-group">
											<label for="nom" class="col-sm-3 control-label">Altitude
												<span class="requis">*</span>
											</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="altitude" name="altitude"
													value="<c:out value="${voie.altitude}"/>"> <span
													class="erreur">${form.erreurs['altitude']}</span>
											</div>
										</div>


										<div class="form-group">
											<label for="nom" class="col-sm-3 control-label">Nombre de longueur <span class="requis">*</span>
											</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="nbrlongueur" name="nbrlongueur"
													value="<c:out value="${voie.nbrlongueur}"/>"> <span
													class="erreur">${form.erreurs['nbrlongueur']}</span>
											</div>
										</div>
										<div class="form-group">
											<label class="col-xs-3 control-label">Cotation</label>
											<div class="col-sm-9 selectContainer">
												<select class="form-control" name="cotation" id="cotation">
													<option value="0">Choisir un site</option>
													<c:forEach var="cotation" items="${ cotations }">
														<option value="<c:out value="${ cotation.idcot}" />"><c:out
																value="${ cotation.nomcot}" /></option>
													</c:forEach>
												</select> Si le pays n'existe pas vous pouvez <a href="">l'ajouter</a>
												<span class="erreur">${form.erreurs['cotation']}</span>
											</div>
										</div>

										<div class="form-group">
											<label class="col-xs-3 control-label">Exposition</label>
											<div class="col-sm-9 selectContainer">
												<select class="form-control" name="exposition" id="exposition">
													<option value="0">Choisir un site</option>
													<c:forEach var="exposition" items="${ expositions }">
														<option value="<c:out value="${ exposition.idexpo}" />"><c:out
																value="${ exposition.libelle_expo}" /></option>
													</c:forEach>
												</select> Si le pays n'existe pas vous pouvez <a href="">l'ajouter</a>
												<span class="erreur">${form.erreurs['exposition']}</span>
											</div>
										</div>

									
									<div class="form-group">
										<div class="col-sm-12 text-right">
											<button type="submit"
												class="btn btn-default preview-add-button">Ajouter
											</button>

											<button type="reset"
												class="btn btn-default preview-add-button">Annuler
											</button>
										</div>
									</div>
									<p class="${empty formvoie.erreurs ? 'succes' : 'erreur'}">${formvoie.resultat}</p>
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