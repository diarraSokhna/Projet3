<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />

</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
 <div class="panel panel-default">
  <div class="panel-heading"><h2>La liste des Articles</h2></div>
 <div class="panel-body">
<c:forEach var = "article" items = "${ articles }">
    <div class="panel panel-default">
      
        <div class="panel-body">
         
            <div class="media">
                <div class="media-left">
                    <a href="#">
                        <img class="media-object" src="<c:url value="/images/${ article.photo }"/>" alt="" width="250" height="250">
                    </a>
                </div>
                <div class="media-body">
                <h3 class="media-heading"><c:out value="${ article.titre }"/></h3><br/>
                Ecrit par <c:out value="${ article.utilisateur.nom }"/> <c:out value="${ article.utilisateur.prenom }"/><br/><br/>
                <c:out value="${ article.contenu}" /> <br/>
                         <section style="margin-top: 75px">
		                    
		                    <i class="glyphicon glyphicon-comment"></i><c:out value=""/>
                            <i class="glyphicon glyphicon-calendar"></i><fmt:formatDate pattern="dd-MM-yyyy" value="${article.datepubli}" />
		                    <a href="<c:url value="/DetailsArticle"> 
		                    <c:param name="titrearticle" value="${ article.titre }" > </c:param>
		                    <c:param name="idArt" value="${ article.id_art }" > </c:param></c:url> " class="btn btn-default btn-sm pull-right" >Lire la suite ... </a>
		                </section>
               </div>
            </div>
          
        </div>
    </div>
     </c:forEach>
</div>
</div>
</div>

 

  </body>
</html>