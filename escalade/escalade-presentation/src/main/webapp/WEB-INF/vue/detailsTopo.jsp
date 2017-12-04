
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
                        <div class="thumbnail">
                      
                        
                <img src="<c:url value="/images/${ topo.image }"/>" width="600" height="600" class="img-thumbnail" alt=""/>
                            
                            <div class="caption">
                            
                                <h3><c:out value="${ topo.nom }"/> </h3>
				<ul class="items">
					<li><b>Description: </b> <c:out value="${ topo.description}" /></li>
					<li><b>Nombre de pages: </b> <c:out value="${ topo.nbpage}" /> pages</li>
					<li><b>Proposé par: </b> <c:out value="${ topo.utilisateur}" /> </li>
				</ul>
				    <p> <a href="<c:url value="/Inscription" />" class="btn btn-primary" role="button">Réserver</a>
                                <a href="<c:url value="/ListeTopo"/>" class="btn btn-primary" role="button">Retour</a></p>
                            </div>
                        </div>
                    </div>
                
                     

	
  </body>
</html>