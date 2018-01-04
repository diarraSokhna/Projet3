



<nav class="navbar navbar-default ">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      
  
      <a class="navbar-brand" href="<c:url value="/Accueil"/>" > <i class="glyphicon glyphicon-home"></i></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
      
        <li><a href="<c:url value="AjoutArticle" />">Article</a></li>
        
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown"  href="">Site
        <span class="caret"></span></a>
         <ul class="dropdown-menu">
           <li class=""><a href="<c:url value="/AjoutSite" />">Ajouter site</a></li> 
           <li class=""><a href="<c:url value="/ListeSite" />">Liste des sites</a></li> 
            
        </ul>
        </li>
        
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown"  href="">Topo
        <span class="caret"></span></a>
         <ul class="dropdown-menu">
           <li class=""><a href="<c:url value="CreationTopo" />">Proposer topo</a></li> 
           <li class=""><a href="<c:url value="/ListeTopo" />">Liste des topos</a></li> 
            
        </ul>
        </li>
      <c:choose>
							<c:when test="${ empty sessionScope.sessionUtilisateur }">
       
        <li><a href="<c:url value="/Inscription" />">S'inscrire</a></li>
        </c:when>
        </c:choose>
      </ul>
      <ul class="nav navbar-nav navbar-right">
                      <c:choose>
							<c:when test="${ empty sessionScope.sessionUtilisateur }">
							<li><a href="<c:url value="/Connection" />"> <span class="glyphicon glyphicon-log-in"></span> Connexion </a></li>
							</c:when>
							<c:otherwise>
							
							<li><a href="<c:url value="/Deconnexion" />">Bonjour <c:out value="${ sessionScope.sessionUtilisateur.nom }"></c:out>!&nbsp; &nbsp;<span class="glyphicon glyphicon-log-in"></span> Deconnexion</a></li>

							</c:otherwise>
						</c:choose>
        
      </ul>
    </div>
  </div>
</nav>
