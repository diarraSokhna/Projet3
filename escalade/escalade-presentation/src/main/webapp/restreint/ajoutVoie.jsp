<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/vue/taglib.jsp"%>
<html>
<head>
<%@ include file="../WEB-INF/vue/head.jsp"%>
</head>
<body>
	<%@ include file="../WEB-INF/vue/menu.jsp"%>

	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<b>Ajouter un site</b>
				</h4>
			</div>
			<div class="panel-body">
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="panel-body">
<form method="post" accept-charset="UTF-8" action="AjoutVoie" >
							<div class="panel-body form-horizontal payment-form">
								<c:set var="cotation" value="${ voie.cotation }" scope="request" />
								<c:set var="exposition" value="${ voie.exposition }" scope="request" />

                                      <fieldset>
										<legend>Ajouter voie </legend>
										<div class="form-group">
											<label for="nom" class="col-sm-4 control-label">Nom voie <span class="requis">*</span>
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" id="nomvoie" name="nomvoie"
													value="<c:out value="${voie.nom_voie}"/>"> <span
													class="erreur">${formvoie.erreurs['nomvoie']}</span>
											</div>
										</div>
										
											<div class="form-group">
											<label class="col-xs-4 control-label">Secteur<span class="requis">*</span></label>
											<div class="col-sm-5 selectContainer">
												<select class="form-control" name="idsecteur" id="idsecteur">
													<option value="0">Sélectionnez un secteur</option>
													<c:forEach items="${ sessionScope.sessionSecteur}" var="secteurs">
														<option value="<c:out value="${ secteurs.key}" />"><c:out
																value="${ secteurs.key}" /></option>
													</c:forEach>
												</select> 
												<span class="erreur">${formvoie.erreurs['idsecteur']}</span>
												
												
											</div>
										</div>
										

										<div class="form-group">
											<label for="altitude" class="col-sm-4 control-label">Altitude
												
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" id="altitude" name="altitude"
													value="<c:out value="${voie.altitude}"/>"> <span
													class="erreur">${formvoie.erreurs['altitude']}</span>
											</div>
										</div>


										<div class="form-group">
											<label for="nbrlongueur" class="col-sm-4 control-label">Nombre de longueur 
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" id="nbrlongueur" name="nbrlongueur"
													value="<c:out value="${voie.nbr_longueur}"/>"> <span
													class="erreur">${formvoie.erreurs['nbrlongueur']}</span>
											</div>
										</div>
										<div class="form-group">
											<label class="col-xs-4 control-label">Cotation <span class="requis">*</span></label>
											<div class="col-sm-5 selectContainer">
												<select class="form-control" name="idcotation" id="idcotation">
													<option value="0">Choisir une cotation</option>
													<c:forEach var="cotation" items="${ cotations }">
														<option value="<c:out value="${ cotation.idcot}" />"><c:out
																value="${ cotation.libelle_cot}" /></option>
													</c:forEach>
												</select> 
												<span class="erreur">${formvoie.erreurs['idcotation']}</span>
												Si la cotation n'existe pas vous pouvez <a href="<c:url value="AjoutCotation" />">l'ajouter</a>
												
											</div>
										</div>

										<div class="form-group">
											<label class="col-xs-4 control-label">Exposition <span class="requis">*</span></label>
											<div class="col-sm-5 selectContainer">
												<select class="form-control" name="idexposition" id="idexposition">
													<option value="0">Choisir une exposition</option>
													<c:forEach var="exposition" items="${ expositions }">
														<option value="<c:out value="${ exposition.id_expo}" />"><c:out
																value="${ exposition.libelle_expo}" /></option>
													</c:forEach>
												</select> 	
												<span class="erreur">${formvoie.erreurs['idexposition']}</span>
											Si l'exposition n'existe pas vous pouvez <a href="<c:url value="AjoutExposition" />">l'ajouter</a>
												</div>
											<div class="col-sm-9 text-right" style="margin-top:50px">
											<button type="submit" class="btn btn-default preview-add-button">Ajouter
											</button>
										<a href="<c:url value="AjoutSite" />">	<button type="button" class="btn btn-default preview-add-button">Annuler
											</button></a>

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




