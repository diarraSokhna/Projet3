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
					<b>Ajouter un article</b>
				</h4>
			</div>
			<div class="panel-body">

				<form method="post" accept-charset="UTF-8" action="AjoutArticle"
					enctype="multipart/form-data">
					<div class="panel-body form-horizontal payment-form">
						<fieldset>
							<div class="form-group">
								<label for="titre" class="col-sm-4 control-label">Titre
									<span class="requis">*</span>
								</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="titre" name="titre"
										value="<c:out value="${article.titre}"/>"> <span
										class="erreur">${form.erreurs['titre']}</span>
								</div>
							</div>
							<div class="form-group">
								<label for="contenu" class="col-sm-4 control-label">Contenu
									<span class="requis">*</span>
								</label>
								<div class="col-sm-5">
									<textarea class="form-control" id="contenu" name="contenu"
										value="<c:out value="${article.contenu}"/>">
                           </textarea>
									<span class="erreur">${form.erreurs['contenu']}</span>
								</div>
							</div>



							<div class="form-group">
								<label for="photo" class="col-sm-4 control-label">Choisir
									photo</label>
								<div class="col-sm-5">
									<input type="file" class="form-control-file" id="photo"
										name="photo" value="<c:out value="${artilce.photo}"/>"
										onchange="" multiple /> <span class="erreur">${form.erreurs['photo']}</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-9 text-right">
									<button type="submit"
										class="btn btn-default preview-add-button">Ajouter</button>

									<button type="reset" class="btn btn-default preview-add-button">
										Annuler</button>
								</div>
							</div>
							<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
							<%-- 							<c:if test="${!empty sessionScope.sessionArticle}"> --%>
							<%-- 								<p class="succes">${sessionScope.sessionArticle.id_art}</p> --%>
							<%-- 							</c:if> --%>
							<c:choose>
								<c:when test="${ empty sessionScope.sessionUtilisateur }">
									<p class="erreur">
										Veuillez vous connecter d'abord! <a
											href="<c:url value="/Connection" />">ici</a>
									</p>
								</c:when>
								<c:otherwise>
									<input type="hidden" class="form-control" id="utilisateur"
										name="utilisateur"
										value="<c:out value="${sessionScope.sessionUtilisateur.iduser}"/>">
									<span class="erreur">${form.erreurs['utilisateur']}</span>


								</c:otherwise>
							</c:choose>
						</fieldset>



					</div>


				</form>

			</div>
		</div>


	</div>
</body>
</html>