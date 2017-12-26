<html>
<head>
<title>Escalade</title>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
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
						<c:choose>
						<c:when test="${ !empty sessionScope.sessionSite }">
								<div class="col-sm-9 recadre">
									<h2 style="text-align: center">Récapitulatif</h2>
									<h3>Vous avez entré le site "<c:out value="${sessionScope.sessionSite.nomsite}" />"</h3>
									<c:if test="${ !empty sessionScope.sessionSecteur }">
										<h4>La liste de ses secteurs:</h4>
									</c:if>
									<c:forEach items="${sessionScope.sessionSecteur}" var="secteurs">
											<c:if test="${ secteurs.key != null }">
												<ul class="api-list">
												<li><c:out value="${secteurs.key}" /></li>
												
												<c:if test="${ !empty sessionScope.sessionVoie }">
													<c:forEach items="${sessionSecteur.voies}" var="voies">
														<ul>
														   
															<li><c:out value="${ voies.secteur}" /></li>
															
														</ul>
													</c:forEach>
												</c:if>
												</ul>
											</c:if>
									</c:forEach>
								</div>
								
								<div class="form-group">
									 <div class="col-sm-6 text-center">
											<button type="button" onClick="bascule('secteur');"
												class="btn btn-default preview-add-button">Ajouter des secteurs
											</button>
										</div>
										</div>
								<c:choose>
									<c:when test="${ !empty sessionScope.sessionSecteur }">
									<div class="form-group">
									 <div class="col-sm-6 text-center">
											<button type="button" onClick="bascule('voie');"
												class="btn btn-default preview-add-button">Ajouter voie
											</button>
										</div>
										</div>
									</c:when>
									</c:choose>
									
											<c:choose>
									<c:when test="${ !empty sessionScope.sessionVoie}">
									<div class="form-group">
									 <div class="col-sm-12 text-center">
											<button type="button" onClick="bascule('finir');"
												class="btn btn-default preview-add-button">Finir
											</button>
										</div>
										</div>
									</c:when>
									</c:choose>
						
						
						<div class="col-sm-12" id="site" style="display:none" >
							<%@ include file="ajoutSite.jsp"%>
						</div>
						
						</c:when>
						<c:otherwise>
						<div class="col-sm-12" id="site" style="display:block" >
							<%@ include file="ajoutSite.jsp"%>
						</div>
						</c:otherwise>
						</c:choose>
						
						  <c:choose>
						  <c:when test="${ !empty sessionScope.sessionVoie }">
						  <div class="col-sm-12" id="secteur" style="display:block" >
							<%@ include file="ajoutSecteur.jsp"%>
						</div>
						
						  </c:when>
						  <c:otherwise>
						  <div class="col-sm-12" id="secteur" style="display:none" >
							<%@ include file="ajoutSecteur.jsp"%>
						</div>
						
						  </c:otherwise>
						  </c:choose>     	
						
						
						<div class="col-sm-12" id="voie" style="display:none" >
							<%@ include file="ajoutVoie.jsp"%>
						</div>
						
						
						<div class="col-sm-8" id="finir" style="display: none">
							<form method="post" accept-charset="UTF-8" action="AjoutSiteSecteurVoie" >
								<div class="panel-body form-horizontal payment-form">
									<div class="form-group">
										<div class="col-sm-12 text-right">
											<button type="submit"
												class="btn btn-default preview-add-button">Enregistrer
												le site</button>
											<button type="reset"
												class="btn btn-default preview-add-button">Annuler</button>
										</div>
									</div>
								</div>
								
								<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
							</form>
						</div>

					</div>
				</div>
			</div>

		</div>

	</div>
</body>
</html>