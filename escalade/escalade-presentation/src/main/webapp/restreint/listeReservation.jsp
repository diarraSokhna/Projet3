
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
					<b>La liste des réservations</b>
				</h4>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Date Réservation</th>
							<th>Topo réservé</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="reservation" items="${ reservations }">
							<tr>

								<td><fmt:formatDate pattern="dd-MM-yyyy"
										value="${reservation.date_resa }" /></td>
								<td><a
									href="<c:url value="/DetailsTopo"><c:param name="idtopo" value="${ reservation.topo.idtopo }" ></c:param><c:param name="nomtopo" value="${ reservation.topo.nom }" ></c:param></c:url>">${ reservation.topo.nom }</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>





