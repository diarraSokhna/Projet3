
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">

<div class="panel panel-default">
  <div class="panel-heading"><h2><c:out value="${ topo.nom }"/></h2></div>
 <div class="panel-body">
                        <div class="thumbnail centre">
                <img src="<c:url value="/images/${ topo.image }"/>" class="img-thumbnail taille" alt=""/>
                            
                            <div class="caption centre">
                            
                                <h3><c:out value="${ topo.nom }"/> </h3>
				<ul class="items">
					<li><b>Description: </b> <c:out value="${ topo.description}" /></li>
					<li><b>Nombre de pages: </b> <c:out value="${ topo.nbpage}" /> pages</li>
					<li><b>Proposé par: </b> <c:out value="${ topo.utilisateur.nom}" /> <c:out value="${ topo.utilisateur.prenom}" /> </li>
				</ul>
				
					 La liste des sites du topo :
					 <c:forEach var = "site" items = "${ sites }">
				<ul class="items">
				
					<li><c:out value="${ site.nomsite}" /></li>
					
				</ul>
				</c:forEach>
				
				    <p> <a href="<c:url value="/ReservationTopo" > <c:param name="idtopo" value="${ topo.idtopo }" /> </c:url>" class="btn btn-primary" role="button">Réserver</a>
                                <a href="<c:url value="/ListeTopo"/>" class="btn btn-primary" role="button">Retour</a></p>
                            </div>
                        </div>
                    </div>
                
                     
</div>
</div>

	
  </body>
</html>