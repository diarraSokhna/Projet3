<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />

</head>
<body>
<%@ include file="menu.jsp" %>



	<div class="container">


<div class="panel-group" id="accordion">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4>
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"><c:out value="${ site.nomsite }"/></a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse in">
        <div class="panel-body">
        <div class="thumbnail centre">
                <img src="<c:url value="/images/${ site.image }"/>" class="img-thumbnail taille" alt=""/>
                            
                            <div class="caption centre">
                            
                                <h3><c:out value="${ site.nomsite }"/> </h3>
				<ul class="items">
					<li>Nom du site : <Strong><c:out value="${ site.nomsite  }" /></Strong><br/></li>
					<li>Pays du site : <Strong><c:out value="${ site.pays.nompays}" /></Strong><br/></li>
					<li>Classement du site : <Strong><c:out value="${ site.classement.libelle_class}" /></Strong> </li>
				</ul>
				
				<a href="<c:url value="/ListeSite"/>" class="btn btn-primary" role="button">Retour</a>
				           </div>
                        </div>
                         </div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4>
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">La liste des Secteurs</a>
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body">
      
				<c:forEach var="secteur" items="${ secteurs }">
				<b> <c:out value="${ secteur.nomsect }"/></b> 
				
				
				<c:forEach var="voie" items="${ voies }">
				<c:choose>
				<c:when test="${ voie.secteur.nomsect == secteur.nomsect}">
				
               <ul><li><b><c:out value="${ voie.nom_voie}" /></b> : altitude: <c:out value="${ voie.secteur.nomsect}" /> - Nombre de longueur :<c:out value="${ voie.nbr_longueur}" /> - cotation: <c:out value="${ voie.cotation.libelle_cot}" />
				</li></ul>
				</c:when>
				</c:choose>
				 </c:forEach>
				
				</c:forEach> 
				
				</div>
				
				
      </div>
    </div>
    
  </div> 
  
    </div>
  </body>
</html>