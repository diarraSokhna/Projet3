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
<%@ include file="menu.jsp" %>

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




	</div>
  </body>
</html>