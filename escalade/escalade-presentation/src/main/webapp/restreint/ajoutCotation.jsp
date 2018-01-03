<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/vue/taglib.jsp"%>
<html>
<head>
<%@ include file="../WEB-INF/vue/head.jsp"%>
</head>
<body>
<%@ include file="../WEB-INF/vue/menu.jsp" %>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<b>Ajouter une cotation</b>
				</h4>
			</div>
			<div class="panel-body">
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="panel-body">
						<form method="post" accept-charset="UTF-8" action="AjoutCotation">
							<div class="panel-body form-horizontal payment-form">
								<fieldset>

									<div class="form-group">
										<label for="nom" class="col-sm-4 control-label">Libellé
											cotation <span class="requis">*</span>
										</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="libelleCota"
												name="libelleCota"
												value="<c:out value="${ cotation.libelle_cot}" />">
											<span class="erreur">${form.erreurs['libelleCota']}</span>

										</div>
									</div>

									<div class="form-group">
										<label for="nom" class="col-sm-4 control-label">Type
											d'escalade </label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="typeEscalade"
												name="typeEscalade"
												value="<c:out value="${ cotation.type_escalade}" />">
											<span class="erreur">${form.erreurs['typeEscalade']}</span>

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








