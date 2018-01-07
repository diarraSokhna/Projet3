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
					<b>Réserver un topo </b>
				</h4>
			</div>
			<div class="panel-body">
				<div id="topo" class="col-sm-12" style="display: block">
					<form method="post" accept-charset="UTF-8"
						action="<c:url value="/ReservationTopo"><c:param name="idtopo" value="${ topo.idtopo }" ></c:param> </c:url>">

						<div class="panel-body form-horizontal payment-form">
							<fieldset>
								<div class="form-group">
									<label for="dateresa" class="col-sm-4 control-label">Date
										réservation <span class="requis">*</span>
									</label>
									<div class="col-sm-5">
										<input type="date" id="dateresa" name="dateresa"
											class="form-control"
											value="<c:out value="${ reservation.date_resa}" />" /> <span
											class="erreur">${form.erreurs['dateresa']} </span>

									</div>
								</div>

								<input type="hidden" class="form-control" id="idtopo"
									name="idtopo" value="<c:out value="${topo.idtopo}"/>">

								<div class="form-group">
									<div class="col-sm-9 text-right">
										<button type="submit"
											class="btn btn-default preview-add-button">Réserver
										</button>
										<a
											href="<c:url value="/DetailsTopo"><c:param name="idtopo" value="${ topo.idtopo }" ></c:param><c:param name="nomtopo" value="${ topo.nom }" ></c:param></c:url>"
											class="btn btn-default" role="button">Retour</a>
										</p>

									</div>
								</div>
								<input type="hidden" class="form-control" id="utilisateur"
									name="utilisateur"
									value="<c:out value="${sessionScope.sessionUtilisateur.iduser}"/>">
							</fieldset>
						</div>
					</form>
				</div>
				<div id="recap" class="col-sm-12" style="display: block">

					<p class="${empty form.erreurs ? 'succes' : 'erreur'}">
						${form.resultat}</p>


				</div>

				<c:choose>
					<c:when test="${ !empty form.resultat}">

						<div id="recap" class="col-sm-12 cad" style="display: block">
							<h4>Récapitulatif</h4>
							Date de réservation : <b> <fmt:formatDate
									pattern="dd-MM-yyyy" value="${reservation.date_resa}" /></b> <br />
							Nom du topo : <b><c:out value="${ reservation.topo.nom}" /></b>
						</div>
					</c:when>
					<c:otherwise>
						<div id="recap" class="col-sm-12 cad" style="display: none">
							<h4>Récapitulatif</h4>
							Date de réservation : <b> <fmt:formatDate
									pattern="dd-MM-yyyy" value="${reservation.date_resa}" /></b> <br />
							Nom du topo : <b><c:out value="${ reservation.topo.nom}" /></b>


						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>





	<script language="Javascript"> 
function bascule(elem) 
{ 
etat=document.getElementById(elem).style.display; 
if(etat=="none"){document.getElementById(elem).style.display="block";} 
else{document.getElementById(elem).style.display="none";} 
} 
 
</script>
</body>
</html>