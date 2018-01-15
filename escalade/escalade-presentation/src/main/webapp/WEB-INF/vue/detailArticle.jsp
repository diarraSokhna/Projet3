<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<html>
<head>
<%@ include file="head.jsp"%>
</head>
<body>
	<%@ include file="menu.jsp"%>

	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<b><c:out value="${ article.titre }" /></b>
				</h4>
			</div>
			<div class="panel-body">
				<div class="media">
					<div class="media-left">
						<img class="media-object"
							src="<c:url value="/images/${ article.photo }"/>" alt=""
							width="250" height="250">
					</div>
					<div class="media-body">
						<h3 class="media-heading">
							<c:out value="${ article.titre }" />
						</h3>
						<br /> Publié par
						<c:out value="${ article.utilisateur.nom }" />
						<c:out value="${ article.utilisateur.prenom }" />
						<br /> <br />
						<c:out value="${ article.contenu}" />
						<br />
						<section style="margin-top: 75px">
							<i class="glyphicon glyphicon-calendar"></i>
							<fmt:formatDate pattern="dd-MM-yyyy" value="${article.datepubli}" />
							<a href="<c:url value="/Accueil"/>"
								class="btn btn-default btn-sm pull-right">Retour</a>
						</section>
					</div>
				</div>

			</div>
		</div>

	</div>

</body>
</html>