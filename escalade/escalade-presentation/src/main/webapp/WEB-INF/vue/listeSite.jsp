
    
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
<script>
$(document).ready(function() {
    $('#idpays').change(function() {
        var idpays = $(this).val();
        var servletUrl = 'idvilleoptions?value=' + idpays;

        $.getJSON(servletUrl, function(villes) {
            var ville = $('#idville');
            $('>option', idville).remove(); // Clean old options first.
            if (villes) {
                $.each(opts, function(key, value) {
                    idville.append($('<option/>').val(key).text(value));
                });
            } else {
                idville.append($('<option/>').text("Please select dropdown1"));
            }
        });
    });
});
</script>
</head>
<body>
<%@ include file="menu.jsp" %>

<jsp:useBean id="site" class="fr.escalade.beans.Site" scope="request" />

	<div class="container">

	 <div class="panel panel-default">
  <div class="panel-heading"><h4><b>La liste des sites</b></h4></div>
 <div class="panel-body">
		<div class="pos">
		<form method="post" action="ListeSite" class="form-inline recadre col-sm-7">
		
       <c:set var="pays" value="${ site.pays }" scope="request" />
        <c:set var="classement" value="${ site.classement }" scope="request" />
        
                            
			<Strong>Rechercher par </strong>
			<select class="form-control form-control-lg" name="idpays" id="idpays"   onchange="change()">
				<option value="0">Pays</option>
				<c:forEach var="pays" items="${ payss }">
				  	<option value="<c:out value="${pays.idpays}" /> "><c:out value="${pays.nompays}" /></option>
				</c:forEach>
			</select> /
			<select class="form-control" name="idville" id="idville" onchange="">
				<option value="0">Ville</option>
				<c:forEach var="ville" items="${ villes }">
					<option value="<c:out value="${ ville.id_ville}" />"><c:out value="${ ville.nom_ville}" /></option>
				</c:forEach>
			</select>/
			
			 <select class="form-control" name="idcotation" id="idcotation" onchange="">
				<option value="0">Cotation</option>
				<c:forEach var="cotation" items="${ cotations }">
					<option value="<c:out value="${ cotation.idcot}" />"><c:out value="${ cotation.libelle_cot}" /></option>
				</c:forEach>
			</select>/
			 
			<select class="form-control" name="idclass" id="idclass" onchange="">
				<option value="0">Classement</option>
				<c:forEach var="classement" items="${ classements }">
					<option value="<c:out value="${ classement.id_class}" />"><c:out value="${ classement.libelle_class}" /></option>
				</c:forEach>
			</select>
	
		</form>
		
		<form method="get" action="ListeSite" class="col-sm-5">	
          <div class="form-group">
                            <button type="submit" class="btn btn-default preview-add-button" >Tous les sites</button>
                    </div>
                 </form> 
		</div>	
			<c:forEach var="site" items="${ sites }">
				<div class="col-sm-6 col-md-3 colonn">
					<div class="thumbnail">
					 <a href="<c:url value="/DetailsSite"><c:param name="nomsite" value="${ site.nomsite }" ></c:param>
					 <c:param name="idsite" value="${ site.idsite }" ></c:param></c:url>">	
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
<%@ include file="footer.jsp" %>
</body>
</html>