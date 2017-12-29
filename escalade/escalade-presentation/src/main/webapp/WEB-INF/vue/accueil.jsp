
<html>
<head>
 <link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
 
</head>
<body>

<%@ include file="menu.jsp" %>

<%-- <%@ include file="footer.jsp" %> --%>



  <div class="container">

    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
      </ol>

      <!-- Wrapper for slides -->
      <div class="carousel-inner">
        <div class="item active">
          <img src="/escalade-presentation/img/slide1.jpg" alt="...">
          <div class="carousel-caption">
            <h2>Heading</h2>
          </div>
        </div>
        <div class="item">
          <img src="/escalade-presentation/img/slide2.jpg" alt="...">
          <div class="carousel-caption">
            <h2>Heading</h2>
          </div>
        </div>
        <div class="item">
          <img src="/escalade-presentation/img/slide3.jpg" alt="...">
          <div class="carousel-caption">
            <h2>Heading</h2>
          </div>
        </div>
      </div>

      <!-- Controls -->
      <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
      </a>
      <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
      </a>
    </div>

<br/>

 <div class="panel panel-default">
  <div class="panel-heading"><h4><b>Actualité</b></h4></div>
 <div class="panel-body">
 <c:forEach var = "topo" items = "${ topos }">
                    <div class="col-sm-6 col-md-3 colonn">
                        <div class="thumbnail">

                        
                <img src="<c:url value="/images/${ topo.image }"/>" alt=""/>
                            
                            <div class="caption">
                            
                                <h3><c:out value="${ topo.nom }"/> </h3>
                                <p><c:out value="${ topo.description}" /></p>
                                
                                <a href="<c:url value="/DetailsTopo"><c:param name="idtopo" value="${ topo.idtopo }" ></c:param><c:param name="nomtopo" value="${ topo.nom }" ></c:param></c:url>">
                              Voir détail</a>
                            </div>
                        </div>
                    </div>
                 
                     </c:forEach></div>
</div>

  </div>
</body>
</html>