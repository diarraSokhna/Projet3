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
					<b>Ajouter une exposition</b>
				</h4>
			</div>
			<div class="panel-body">
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="panel-body">
						<form method="post" accept-charset="UTF-8"
							action="AjoutExposition">
							<div class="panel-body form-horizontal payment-form">
								<fieldset>

									<div class="form-group">
										<label for="nom" class="col-sm-4 control-label">Libelle
											exposition <span class="requis">*</span>
										</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="libelleExpo"
												name="libelleExpo"
												value="<c:out value="${ exposition.libelle_expo}" />">
											<span class="erreur">${form.erreurs['libelleExpo']}</span>

										</div>

									</div>
									<div class="form-group">
										<div class="col-sm-9 text-right">
											<button type="submit"
												class="btn btn-default preview-add-button">Ajouter
											</button>
											<a href="<c:url value="AjoutVoie"/> ">
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
</body>
</html>








