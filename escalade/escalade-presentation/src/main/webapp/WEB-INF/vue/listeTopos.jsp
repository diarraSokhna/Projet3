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
					<b>La liste des Topos</b>
				</h4>
			</div>
			<div class="panel-body">
				<c:forEach var="topo" items="${ topos }">
					<div class="col-sm-6 col-md-3 colonn">
						<div class="thumbnail">


							<img src="<c:url value="/images/${ topo.image }"/>" alt="" />

							<div class="caption">

								<h3>
									<c:out value="${ topo.nom }" />
								</h3>
								<p>
									<c:out value="${ topo.description}" />
								</p>

								<a
									href="<c:url value="/DetailsTopo"><c:param name="idtopo" value="${ topo.idtopo }" ></c:param><c:param name="nomtopo" value="${ topo.nom }" ></c:param></c:url>">
									Voir détail</a>
							</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</div>

	</div>
</body>
</html>