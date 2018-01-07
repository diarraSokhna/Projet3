<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>

<html>
<head>
<%@ include file="head.jsp"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<jsp:useBean id="site" class="fr.escalade.beans.Site" scope="request" />
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<b>La liste des sites</b>
				</h4>
			</div>
			<div class="panel-body">
				<div class="pos">
					<div class="col-sm-2">
						<h4>
							<b>Rechercher par</b>
						</h4>
					</div>
					<form method="post"
						action="<c:url value="ListeSiteParPays" > <c:param name="id" value="${ sessionScope.sessionPays.idpays }" /><c:param name="nomPays" value="${ sessionScope.sessionPays.nompays }" /> </c:url>"
						class="form  col-sm-2">

						<c:set var="pays" value="${ site.pays }" scope="request" />
						<c:set var="classement" value="${ site.classement }"
							scope="request" />

						<select class="form-control form-control-lg" name="idpays"
							id="idpays" onchange="this.form.submit();">
							<option value="0">Pays</option>
							<c:forEach var="pays" items="${ payss }">
								<option value="<c:out value="${pays.idpays}" /> "><c:out
										value="${pays.nompays}" /></option>
							</c:forEach>
						</select>
					</form>
					<form method="post"
						action="<c:url value="ListeSiteParVille" > <c:param name="id" value="${ sessionScope.sessionVille.id_ville }" /><c:param name="nomVille" value="${ sessionScope.sessionVille.nom_ville }" /> </c:url>"
						class="form  col-sm-2">

						<select class="form-control" name="idville" id="idville"
							onchange="this.form.submit();">
							<option value="0">Ville</option>
							<c:forEach var="ville" items="${ villes }">
								<option value="<c:out value="${ ville.id_ville}" />"><c:out
										value="${ ville.nom_ville}" /></option>
							</c:forEach>
						</select>
					</form>
					<form method="post"
						action="<c:url value="ListeSiteParCotation" > <c:param name="id" value="${ sessionScope.sessionCotation.idcot }" /><c:param name="libelleCotation" value="${ sessionScope.sessionCotation.libelle_cot }" /> </c:url>"
						class="form  col-sm-2">

						<select class="form-control" name="idcotation" id="idcotation"
							onchange="this.form.submit();">
							<option value="0">Cotation</option>
							<c:forEach var="cotation" items="${ cotations }">
								<option value="<c:out value="${ cotation.idcot}" />"><c:out
										value="${ cotation.libelle_cot}" /></option>
							</c:forEach>
						</select>
					</form>
					<form method="post"
						action="<c:url value="ListeSiteParClassement" > <c:param name="id" value="${ sessionScope.sessionClassement.id_class }" /><c:param name="libelleClassement" value="${ sessionScope.sessionClassement.libelle_class }" /> </c:url>"
						class="form  col-sm-2">

						<select class="form-control" name="idclass" id="idclass"
							onchange="this.form.submit();">
							<option value="0">Classement</option>
							<c:forEach var="classement" items="${ classements }">
								<option value="<c:out value="${ classement.id_class}" />"><c:out
										value="${ classement.libelle_class}" /></option>
							</c:forEach>
						</select>
					</form>

					<form method="get" action="ListeSite" class="col-sm-2">
						<div class="form-group">
							<button type="submit" class="btn btn-default preview-add-button">Tous
								les sites</button>
						</div>
					</form>

				</div>


				<div style="">

<%-- 					<c:choose> --%>
<%-- 						<c:when test="${ empty sessionScope.sessionSite  }"> --%>
<!-- 							<h1>La recherche n'a rien donné</h1> -->

<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
							<c:forEach var="site" items="${ sites }">
								<div class="col-sm-6 col-md-3 colonn">
									<div class="thumbnail">
										<a
											href="<c:url value="/DetailsSite"><c:param name="nomsite" value="${ site.nomsite }" ></c:param>
					 <c:param name="idsite" value="${ site.idsite }" ></c:param></c:url>">
											<img src="<c:url value="/images/${ site.image }"/>" alt="" />
										</a>
										<div class="caption">
											Nom du site : <Strong><c:out
													value="${ site.nomsite  }" /></Strong><br /> Pays du site : <Strong><c:out
													value="${ site.pays.nompays}" /></Strong><br /> Classement du site
											: <Strong><c:out
													value="${ site.classement.libelle_class}" /></Strong>
										</div>
									</div>
								</div>

							</c:forEach>

<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>

				</div>
			</div>
		</div>
	</div>
</body>
</html>