<head>
<title>Escalade</title>
<link rel="shortcut icon" type="image/x-icon"
	href="/escalade-presentation/img/favicon.png" />
<script language="Javascript"> 
function bascule(elem) 
{ 

etat=document.getElementById(elem).style.display; 
if(etat=="none"){document.getElementById(elem).style.display="block";} 
else{document.getElementById(elem).style.display="none";} 
} 
</script> 

</head>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 	<jsp:useBean id="site" class="fr.escalade.beans.Site" scope="session" />	 --%>
		
		<div class="row"> 
		<div class="col-sm-7">
						<form method="post" accept-charset="UTF-8" action="AjoutSite" enctype="multipart/form-data"  >
							<div class="panel-body form-horizontal payment-form">
								<fieldset>
								<c:set var="pays" value="${ site.pays }" scope="request" />
								<c:set var="classement" value="${ site.classement }"
									scope="request" />
						

									<div class="form-group">
										<label for="nom" class="col-sm-3 control-label">Nom site<span class="requis">*</span>
										</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="nom" name="nom"
												value="<c:out value="${site.nomsite}"/>"> 
												 <span class="erreur">${formsite.erreurs['nom']}</span>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label">Classement</label>
										<div class="col-sm-9 selectContainer">
											<select class="form-control" name="idclass" id="idclass">
												<option value="0">Choisir un classement</option>
												<c:forEach var="classement" items="${ classements }">
													<option value="<c:out value="${ classement.id_class}" />"><c:out
															value="${ classement.libelle_class}" /></option>
												</c:forEach>
											</select> <span class="erreur">${formsite.erreurs['idclass']}</span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-xs-3 control-label">Pays</label>
										<div class="col-sm-9 selectContainer">
											<select class="form-control" name="idpays" id="idpays">
												<option value="0">Choisir un pays</option>
												<c:forEach var="pays" items="${ payss }">
													<option value="<c:out value="${ pays.idpays}" />"><c:out
															value="${ pays.nompays}" /></option>
												</c:forEach>
											</select> <span class="erreur">${formsite.erreurs['idpays']}</span> Si le
											pays n'existe pas vous pouvez <a href="">l'ajouter</a>
										</div>
									</div>
									<div class="form-group">
										<label for="photo" class="col-sm-3 control-label">Choisir
											photo</label>
										<div class="col-sm-9">
											<input type="file" class="form-control-file" id="image"
												name="image" value="<c:out value="${site.image}"/>"
												onchange="" multiple /> <span class="erreur">${formsite.erreurs['image']}</span>
										
                                       
									</div>
									</div>
									<hr/>
									<c:choose>
									<c:when test="${ empty sessionScope.sessionSite }">
									<div class="form-group">
									 <div class="col-sm-12 text-right">
											<button type="submit" 
												class="btn btn-default preview-add-button">Ajouter
											</button>
										</div>
										</div>
									</c:when>
									
								
											
									<c:otherwise>
									<div class="form-group">
									 <div class="col-sm-12 text-center">
											<button type="button" onClick="bascule('boite');"
												class="btn btn-default preview-add-button">Ajouter des secteurs
											</button>
										</div>
										</div>
									</c:otherwise>
									</c:choose>
						       
						       		<c:choose>
									<c:when test="${ !empty sessionScope.sessionSecteur }">
									<div class="form-group">
									 <div class="col-sm-12 text-center">
											<button type="button" onClick="bascule('voie');"
												class="btn btn-default preview-add-button">Ajouter voie
											</button>
										</div>
										</div>
									</c:when>
									</c:choose>
									
											<c:choose>
									<c:when test="${ !empty sessionScope.sessionVoie}">
									<div class="form-group">
									 <div class="col-sm-12 text-center">
											<button type="button" onClick="bascule('site');"
												class="btn btn-default preview-add-button">Finir
											</button>
										</div>
										</div>
									</c:when>
									</c:choose>
										
										
					
						</fieldset>
	
	</div>
	<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
								
	</form>	
	</div>
	
	
		<div class="col-sm-4">		
	<c:if test="${ !empty sessionScope.sessionSite }">
									<c:if test="${ !empty sessionScope.sessionSecteur }">
									<legend>Récapitulatif</legend>
									<h4>La liste des secteurs du site  "<c:out value="${sessionScope.sessionSite.nomsite}"/>"</h4>
									   
									   <c:forEach items="${sessionScope.sessionSite.secteurs}" var="secteurs" >

					<div class="list-group">
					
						<div class="list-group-item ">
							<h4 class="list-group-item-heading"><c:out value="${secteurs.nomsect}"/></h4>
							<c:if test="${ !empty sessionScope.sessionVoie }">
							<c:forEach items="${secteurs.voies}" var="voies" >
							<p class="list-group-item-text">
							<ul><li><c:out value="${ voies.nom_voie }"/> </li></ul></p>
							</c:forEach>
							</c:if>
						
						</div>
					</div>

									   
									   </c:forEach>
									   </c:if>
								 </c:if>
	
			</div>				
	</div>					
