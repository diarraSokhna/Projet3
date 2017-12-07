
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
 <div class="row">
 <c:forEach var = "topo" items = "${ topos }">
                    <div class="col-sm-6 col-md-3 colonn">
                        <div class="thumbnail">

                        
                <img src="<c:url value="/images/${ topo.image }"/>" alt=""/>
                            
                            <div class="caption">
                            
                                <h3><c:out value="${ topo.nom }"/> </h3>
                                <p><c:out value="${ topo.description}" /></p>
                                
                                <a href="<c:url value="/DetailsTopo"><c:param name="nomtopo" value="${ topo.nom }" ></c:param></c:url>">
                              Voir d�tail</a>
                            </div>
                        </div>
                    </div>
                 
                     </c:forEach>
</div>

	</div>
  </body>
</html>