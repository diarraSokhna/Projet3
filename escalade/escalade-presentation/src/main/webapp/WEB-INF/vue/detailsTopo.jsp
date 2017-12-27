<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 
           <div class="row">
            <div class ="col-md-4 text-left ">
            <div class="thumbnail">
              <img  src="<c:url value="/images/${ topo.image }"/>" alt="" class="img-thumbnail taille"> 
</div>
            </div>
	        <div class ="col-md-6 text-left ">
                <h3 class="top_title" style=""><c:out value="${  topo.nom  }"/></h3>
                <span class="para_text" style=""> Popos� par  <c:out value="${ topo.utilisateur.nom }"/> <c:out value="${ topo.utilisateur.prenom }"/><br/><br/>
                <c:out value="${ topo.description }"/><br/>
                 <b>Nombre de pages: </b> <c:out value="${ topo.nbpage}" /> pages<br/>
<!--                  La liste des sites du topo : -->
					
<%-- 					 <c:forEach var = "site" items = "${ sites }"> --%>
<%-- 				<ul><li><c:out value="${ site.nomsite}" /></li></ul> --%>
<%-- 				</c:forEach> --%>
                </span>
                
                <section style="margin-top: 75px">
		                    
		                    
		                     <p> <a href="<c:url value="ReservationTopo" > <c:param name="idtopo" value="${ topo.idtopo }" /> </c:url>" class="btn btn-primary" role="button">R�server</a>
                                <a href="<c:url value="/ListeTopo"/>" class="btn btn-primary" role="button">Retour</a></p>
                    </section>
                    <i class="glyphicon glyphicon-comment right"></i><c:out value="${ rowCount }" />
            </div>
            
        
        
	</div>

    </div>
    </div>
    
 <div class="panel panel-default">
  <div class="panel-heading"><h2>Vous aussi exprimez vous</h2></div>
 <div class="panel-body">
			
			
			<c:choose>
						<c:when test="${ !empty sessionScope.sessionUtilisateur }">
							<%@ include file="/restreint/ajoutCommentaire.jsp" %>	
						</c:when>
					<c:otherwise>
					        <h3>Merci de vous <a href="<c:url value="/Connection" />" >connecter </a>pour laisser un commentaire!</h3>
					
					</c:otherwise>
					
					</c:choose>	
			
			
			
</div>
				<c:forEach var="commentaire" items="${ commentaires }">
					<hr data-brackets-id="12673">
					<ul data-brackets-id="12674" id="sortable"
						class="list-unstyled ui-sortable">
						<strong class="pull-left primary-font"> <i
							class="glyphicon glyphicon-user"></i>
						<c:out value="${ commentaire.utilisateur.nom }" /> <c:out
								value="${ commentaire.utilisateur.prenom }" /></strong>
						<small class="pull-right text-muted"> <i
							class="glyphicon glyphicon-calendar"></i></span> <fmt:formatDate
								pattern="dd-MM-yyyy" value="${commentaire.datecom}" />
						</small>
						</br>
						<br />
						<li class="ui-state-default"><c:out
								value="${ commentaire.libelle }" /></li>

					</ul>
				</c:forEach>

			</div>
    
   
</div>

  </body>
</html>