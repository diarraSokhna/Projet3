


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
						<form method="post" accept-charset="UTF-8" action="AjoutSite" enctype="multipart/form-data"  >
							<div class="panel-body form-horizontal payment-form">
								<c:set var="pays" value="${ site.pays }" scope="request" />
								<c:set var="classement" value="${ site.classement }"
									scope="request" />
						

									<div class="form-group">
										<label for="nom" class="col-sm-3 control-label">Nom site<span class="requis">*</span>
										</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="nom" name="nom"
												value="<c:out value="${site.nomsite}"/>"> 
												 <span class="erreur">${formsite.erreurs['nom']}</span>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label">Classement</label>
										<div class="col-sm-6 selectContainer">
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
										<div class="col-sm-6 selectContainer">
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
										<div class="col-sm-6">
											<input type="file" class="form-control-file" id="image"
												name="image" value="<c:out value="${site.image}"/>"
												onchange="" multiple /> <span class="erreur">${formsite.erreurs['image']}</span>
										</div>
										<div class="col-sm-3 text-right">
											<button type="submit" 
												class="btn btn-default preview-add-button">Ajouter dans session
											</button>

										</div>
									</div>
									
									 <c:if test="${ !empty sessionScope.sessionSite }">
									   <c:out value="${sessionScope.sessionSite.nomsite}"/>
								 </c:if>
	
					</div>
					</form>			
						
