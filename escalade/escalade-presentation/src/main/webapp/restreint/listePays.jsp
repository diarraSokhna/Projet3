	<form method="post" action="<c:url value="ChargerVille" > </c:url>"
						class="form  col-sm-2">
									<div class="form-group">
										<label class="col-xs-4 control-label">Pays<span class="requis">*</span></label>
										<div class="col-sm-5 selectContainer">
											<select class="form-control" name="idpays" id="idpays" onchange="this.form.submit();">
												<option value="0">Choisir un pays</option>
												<c:forEach var="pays" items="${ payss }">
													<option value="<c:out value="${ pays.idpays}" />"><c:out
															value="${ pays.nompays}" /></option>
												</c:forEach>
											</select> <span class="erreur">${formsite.erreurs['idpays']}</span> Si le
											pays n'existe pas vous pouvez <a href="<c:url value="AjoutPays" /> ">l'ajouter</a>
										</div>
									</div>
									
							
									</form>