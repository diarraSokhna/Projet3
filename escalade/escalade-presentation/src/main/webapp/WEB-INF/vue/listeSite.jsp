
    
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />

</head>
<body>
<%@ include file="menu.jsp" %>


<div class="container">
<div class="row">
 <c:forEach var = "site" items = "${ sites }">
                    <div class="col-sm-6 col-md-3 colonn">
                        <div class="thumbnail">

                        
                <img src="<c:url value="/images/${ site.image }" />"  width="250" height="250" alt=""/>
                  <div class="caption">
                            
                                <h3><c:out value="${ site.nomsite }"/> </h3>
                                <p>Pays: <c:out value="${ site.pays.nompays}" /></p>
                                <p>Classement: <c:out value="${ site.classement.libelle_class}" /></p>
                                <a href="<c:url value="/DetailsTopo"><c:param name="nomtopo" value="${ topo.nom }" ></c:param></c:url>">
                              Voir détail</a>
                            </div>
                        </div>
                    </div>
                 
                     </c:forEach>
</div>
</div>
  </body>
</html>