<form method="post" accept-charset="UTF-8" action="AjoutSecteur"  >
							<div class="panel-body form-horizontal payment-form">
							
											<fieldset>
											<legend>Ajouter secteur </legend>
									<div class="form-group">
										<label for="nom" class="col-sm-3 control-label">Nom secteur <span class="requis">*</span></label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="nomsect" name="nomsect"
												value="<c:out value="${secteur.nomsect}"/>"> <span
												class="erreur">${formsect.erreurs['nomsect']}</span>
												
										</div>
										<div class="col-sm-3 text-right">
											<button type="submit" class="btn btn-default preview-add-button">Ajouter à site
											</button>

										</div>	
									</div>
																		
                                    <div class="col-sm-6">
											<input type="hidden" class="form-control" id="site" name="site"
												value="<c:out value="${sessionScope.sessionSite.idsite}"/>"> <span
												class="erreur">${formsect.erreurs['site']}</span>
											
										</div>
										<hr/>
										<c:if test="${ !empty sessionScope.sessionSecteur }">
										
										 <h4>La liste des secteurs :</h4>  <br/>  
									  <c:forEach items="${ sessionScope.sessionSecteur}" var="secteurs" >
								
									 <ul>
									 <li>
									 <c:out value="${ secteurs.key }"></c:out> 
									 </li>
									</ul> 
									 <br/>
									
									  </c:forEach>
									 </c:if>
								
									<p class="${empty formSect.erreurs ? 'succes' : 'erreur'}">${formSect.resultat}</p>


									
							</fieldset>
					
			</div>
			</form>				