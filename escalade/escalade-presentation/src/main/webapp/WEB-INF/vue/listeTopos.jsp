
<html>
<head>
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
 <div class="row">
 <c:forEach var = "topo" items = "${ topos }">
                    <div class="col-sm-6 col-md-3 colonn">
                        <div class="thumbnail">
                            <img src="<c:out value="${ topo.image }"/>" alt=""/>
                            <div class="caption">
                            
                                <h3><c:out value="${ topo.nom }"/> </h3>
                                <p><c:out value="${ topo.description}" /></p>
                                <p> <a href="#" class="btn btn-default boutton" role="button">Voir détail</a></p>
                            </div>
                        </div>
                    </div>
                 
                     </c:forEach>
</div>




	</div>
  </body>
</html>