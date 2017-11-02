<%-- <%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%> --%>
<%-- <%@page import="java.util.Iterator"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 

<html>
<head>
 <meta charset="utf-8" />
        <title>Escalade</title>
        
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
 <link href="/escalade-presentation/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="/escalade-presentation/css/style.css" type="text/css">
 
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="<%=request.getContextPath()+"/accueil"%>"> <i class="glyphicon glyphicon-home"></i></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="<%=request.getContextPath()+"/ListeTopo"%>">Topo</a></li>
         <li><a href="">Site</a></li>
        <li><a href="#">Article</a></li>
        <li><a href="#">S'inscrire</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Connexion</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">
 <div class="row">
 <c:forEach var = "topo" items = "${ topos }">
                    <div class="col-sm-6 col-md-3 colonn">
                        <div class="thumbnail">
                            <img src="/escalade-presentation/img/1.jpg" alt=""/>
                            <div class="caption">
                            
                                <h3><c:out value="${ topo.nom }"/> </h3>
                                <p><c:out value="${ topo.description}" /></p>
                                <p> <a href="#" class="btn btn-default boutton" role="button">Voir détail</a></p>
                            </div>
                        </div>
                    </div>
                 
                     </c:forEach>
</div>

<!-- 		<table class="table table-striped table-bordered" -->
<!-- 			style="font-size: 20px"> -->
<!-- 			<thead> -->
<!-- 				<tr> -->
<!-- 					<th>Nom</th> -->
<!-- 					<th>Description</th> -->
<!-- 					<th>Nbr page</th> -->
<!-- 				</tr> -->
<%-- 				<c:forEach var="topo" items="${ topos }"> --%>
<!-- 					<tr> -->
<%-- 						<td><c:out value="${ topo.nom }" /></td> --%>
<%-- 						<td><c:out value="${ topo.description}" /></td> --%>
<%-- 						<td><c:out value="${ topo.nbpage}" /></td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</thead> -->
<!-- 		</table> -->



	</div>
  </body>
</html>