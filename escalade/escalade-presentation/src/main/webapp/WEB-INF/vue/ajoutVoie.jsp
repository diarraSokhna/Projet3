

<form method="post" accept-charset="UTF-8" action="AjoutVoie"  >
							<div class="panel-body form-horizontal payment-form">
								<c:set var="cotation" value="${ voie.cotation }" scope="request" />
								<c:set var="exposition" value="${ voie.exposition }" scope="request" />

                                      <fieldset>
										<legend>Ajouter voie </legend>
										<div class="form-group">
											<label for="nom" class="col-sm-3 control-label">Nom voie <span class="requis">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="nomvoie" name="nomvoie"
													value="<c:out value="${voie.nom_voie}"/>"> <span
													class="erreur">${formvoie.erreurs['nomvoie']}</span>
											</div>
										</div>

										<div class="form-group">
											<label for="altitude" class="col-sm-3 control-label">Altitude
												<span class="requis">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="altitude" name="altitude"
													value="<c:out value="${voie.altitude}"/>"> <span
													class="erreur">${formvoie.erreurs['altitude']}</span>
											</div>
										</div>


										<div class="form-group">
											<label for="nbrlongueur" class="col-sm-3 control-label">Nombre de longueur <span class="requis">*</span>
											</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" id="nbrlongueur" name="nbrlongueur"
													value="<c:out value="${voie.nbr_longueur}"/>"> <span
													class="erreur">${formvoie.erreurs['nbrlongueur']}</span>
											</div>
										</div>
										<div class="form-group">
											<label class="col-xs-3 control-label">Cotation</label>
											<div class="col-sm-6 selectContainer">
												<select class="form-control" name="idcotation" id="idcotation">
													<option value="0">Choisir une cotation</option>
													<c:forEach var="cotation" items="${ cotations }">
														<option value="<c:out value="${ cotation.idcot}" />"><c:out
																value="${ cotation.libelle_cot}" /></option>
													</c:forEach>
												</select> 
												<span class="erreur">${formvoie.erreurs['idcotation']}</span>
												Si le pays n'existe pas vous pouvez <a href="">l'ajouter</a>
												
											</div>
										</div>

										<div class="form-group">
											<label class="col-xs-3 control-label">Exposition</label>
											<div class="col-sm-6 selectContainer">
												<select class="form-control" name="idexposition" id="idexposition">
													<option value="0">Choisir une exposition</option>
													<c:forEach var="exposition" items="${ expositions }">
														<option value="<c:out value="${ exposition.id_expo}" />"><c:out
																value="${ exposition.libelle_expo}" /></option>
													</c:forEach>
												</select> 	
												<span class="erreur">${formvoie.erreurs['idexposition']}</span>
											Si le pays n'existe pas vous pouvez <a href="">l'ajouter</a>
												</div>
											<div class="col-sm-3 text-right">
											<button type="submit" class="btn btn-default preview-add-button">Ajouter à secteur
											</button>

										   </div>
										</div>

                                 <div class="col-sm-6">
				<c:if test="${ !empty sessionScope.sessionSecteur }">
					<c:forEach items="${ sessionScope.sessionSecteur}" var="secteurs"
						varStatus="loop">
						<c:if test="${loop.last}">
							<input type="text" class="form-control" id="secteur"
								name="secteur" value="<c:out value="${ secteurs.key} "></c:out>">
							<span class="erreur">${formsect.erreurs['secteur']}</span>

						</c:if>
					</c:forEach>
				</c:if>


			</div>

										<hr/>
										<c:if test="${ !empty sessionScope.sessionVoie }">
										
										<h4>La liste des voies du secteur 
										
										<c:if test="${ !empty sessionScope.sessionSecteur }">
					                   <c:forEach items="${ sessionScope.sessionSecteur}" var="secteurs" varStatus="loop">
						                <c:if test="${loop.last}">
										<c:out value="${ secteurs.key} "></c:out>
										</c:if>
										</c:forEach>
									</c:if> </h4> 
										
										 <br/>
									  <c:forEach items="${ sessionScope.sessionVoie}" var="voies" >
									   <ul>
									 <li>
									 <c:out value="${ voies.key }"></c:out>
									 <c:out value="${ voies.value.cotation.libelle_cot }"></c:out>
									 <c:out value="${ voies.value.exposition.libelle_expo }"></c:out>
									 <c:out value="${ voies.value.altitude }"></c:out>
									 <c:out value="${ voies.value.nbr_longueur }"></c:out>
									 <c:out value="${ voies.value.secteur.nomsect }"></c:out>
									 </li>
									 </ul>
									<br/>
									  </c:forEach>
									 </c:if>
									
									<p class="${empty formvoie.erreurs ? 'succes' : 'erreur'}">${formvoie.resultat}</p>
								</fieldset>
							</div>

						</form>