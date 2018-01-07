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
						<form method="post" accept-charset="UTF-8" action="AjoutSecteur">
							<div class="panel-body form-horizontal payment-form">
								<fieldset>
									<legend>Ajouter secteur</legend>
									<div class="form-group">
										<label for="nom" class="col-sm-4 control-label">Nom
											secteur <span class="requis">*</span>
										</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="nomsect"
												name="nomsect" value="<c:out value="${secteur.nomsect}"/>">
											<span class="erreur">${formsect.erreurs['nomsect']}</span>

										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-9 text-right" style="margin-top: 50px">
											<button type="submit"
												class="btn btn-default preview-add-button">Ajouter
											</button>
											<a href="<c:url value="AjoutSite"/>">
												<button type="button"
													class="btn btn-default preview-add-button">Annuler
												</button>
											</a>

										</div>
									</div>
									<p class="${empty formsect.erreurs ? 'succes' : 'erreur'}">${formsect.resultat}</p>
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





