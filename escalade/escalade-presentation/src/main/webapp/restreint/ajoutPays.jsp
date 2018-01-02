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

		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>
						<b> <a data-toggle="collapse" data-parent="#accordion"
							href="#collapse1">Ajouter un pays</a></b>
					</h4>
				</div>
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="panel-body">
						<form method="post" accept-charset="UTF-8" action="AjoutPays">
							<div class="panel-body form-horizontal payment-form">
								<fieldset>

									<div class="form-group">
										<label for="nom" class="col-sm-4 control-label">Nom
											pays <span class="requis">*</span>
										</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="nomPays"
												name="nomPays" value="<c:out value="${ pays.nompays}" />">
											<span class="erreur">${form.erreurs['nomPays']}</span>

										</div>

									</div>
									<div class="form-group">
										<div class="col-sm-9 text-right">
											<button type="submit"
												class="btn btn-default preview-add-button">Ajouter
											</button>
											<a href="<c:url value="AjoutSite"/> ">
												<button type="button"
													class="btn btn-default preview-add-button">Retour
												</button>
											</a>
										</div>
									</div>
									<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
								</fieldset>

							</div>
						</form>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<b> <a data-toggle="collapse" data-parent="#accordion"
								href="#collapse2">Ajouter une ville</a></b>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse">
						<div class="panel-body">
							<form method="post" accept-charset="UTF-8" action="AjoutVille">
								<div class="panel-body form-horizontal payment-form">
									<fieldset>

										<div class="form-group">
											<label for="nom" class="col-sm-4 control-label">Nom
												Ville <span class="requis">*</span>
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" id="nomVille"
													name="nomVille"
													value="<c:out value="${ ville.nom_ville}" />"> <span
													class="erreur">${form.erreurs['nomVille']}</span>
											</div>
										</div>

										<div class="form-group">
											<label for="cp" class="col-sm-4 control-label">Code
												Postal <span class="requis">*</span>
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" id="codep"
													name="codep" value="<c:out value="${ ville.cp}" />">
												<span class="erreur">${form.erreurs['codep']}</span>
											</div>
										</div>

										<div class="form-group">
											<label class="col-xs-4 control-label">Pays <span
												class="requis">*</span></label>
											<div class="col-sm-5 selectContainer">
												<select class="form-control" name="idpays" id="idpays">
													<option value="0">Choisir un pays</option>
													<c:forEach var="pays" items="${ payss }">
														<option value="<c:out value="${pays.idpays}" /> "><c:out
																value="${pays.nompays}" /></option>
													</c:forEach>
												</select> <span class="erreur">${form.erreurs['idpays']}</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-9 text-right">
												<button type="submit"
													class="btn btn-default preview-add-button">Ajouter
												</button>
												<a href="<c:url value="AjoutSite"/> ">
													<button type="button"
														class="btn btn-default preview-add-button">Retour
													</button>
												</a>
											</div>
										</div>
										<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
									</fieldset>

								</div>
							</form>
						</div>

					</div>
				</div>

			</div>
		</div>








	</div>
</body>
</html>








