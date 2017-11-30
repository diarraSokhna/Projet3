
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />

</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">

<c:forEach var = "article" items = "${ articles }">
    <div class="panel panel-default">
      
        <div class="panel-body">
         
            <div class="media">
                <div class="media-left">
                    <a href="#">
                        <img class="media-object" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Canis_lupus.jpg/260px-Canis_lupus.jpg" alt="Kurt">
                    </a>
                </div>
                <div class="media-body">
                <h3 class="media-heading"><c:out value="${ article.titre }"/></h3><br/>
                Ecrit par <c:out value="${ article.id_user }"/><br/><br/>
                <c:out value="${ article.contenu}" /> <br/>
                         <section style="margin-top: 75px">
		                    
		                    <i class="glyphicon glyphicon-comment"></i>2
                            <i class="glyphicon glyphicon-calendar"></i><c:out value="${ article.datepubli }"/>
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

 

  </body>
</html>