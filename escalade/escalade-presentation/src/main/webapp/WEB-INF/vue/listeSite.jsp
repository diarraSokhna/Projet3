
    
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />

</head>
<body>
<%@ include file="menu.jsp" %>



	<div class="container">

	 <div class="panel panel-default">
  <div class="panel-heading"><h2>La liste des sites</h2></div>
 <div class="panel-body">
		
		<form method="post" action="ListeSite" class="form-inline recadre">
       <c:set var="pays" value="${ site.pays }" scope="request" />
        <c:set var="classement" value="${ site.classement }" scope="request" />
      
			<Strong>Rechercher par </strong>
			<select class="form-control form-control-lg" name="idpays" id="idpays" >
				<option value="0">Pays</option>
				<c:forEach var="pays" items="${ payss }">
					<option value="<c:out value="${pays.idpays}"/>"><c:out
							value="${pays.nompays}" /></option>
				</c:forEach>
			</select> /
			<select class="form-control" name="iddep" id="iddep">
				<option value="0">Département</option>
				<c:forEach var="departement" items="${ departements }">
					<option value="<c:out value="${ departement.id_dep}" />"><c:out
							value="${ departement.nom_dep}" /></option>
				</c:forEach>
			</select>/
			
			</select> <select class="form-control" name="idcot" id="idcot">
				<option value="0">Cotation</option>
				<c:forEach var="cotation" items="${ cotations }">
					<option value="<c:out value="${ cotation.idcot}" />"><c:out
							value="${ cotation.libelle_cot}" /></option>
				</c:forEach>
			</select>/
			</select> <select class="form-control" name="idclass" id="idclass">
				<option value="0">Classement</option>
				<c:forEach var="classement" items="${ classements }">
					<option value="<c:out value="${ classement.id_class}" />"><c:out
							value="${ classement.libelle_class}" /></option>
				</c:forEach>
			</select>
          <div class="form-group">
                        <div class="col-sm-12 text-right">
                            <button type="submit" class="btn btn-default preview-add-button">
                                Rechercher
                            </button>
                        </div>
                    </div>
                    
		</form>
				<hr/>
			<c:forEach var="site" items="${ sites }">
				<div class="col-sm-6 col-md-3 colonn">
					<div class="thumbnail">
					 <a href="<c:url value="/DetailsSite"><c:param name="nomsite" value="${ site.nomsite }" ></c:param></c:url>">	
					 <img src="<c:url value="/images/${ site.image }"/>"  alt="" /></a>
  								<div class="caption">
								Nom du site : <Strong><c:out value="${ site.nomsite  }" /></Strong><br/>
								Pays du site : <Strong><c:out value="${ site.pays.nompays}" /></Strong><br/>
								Classement du site : <Strong><c:out value="${ site.classement.libelle_class}" /></Strong>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
</div>
</div>

</body>
</html>