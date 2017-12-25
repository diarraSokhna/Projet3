
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form method="post" name="boite" id="boite" accept-charset="UTF-8" action="AjoutSecteur" style="display:none" >
							<div class="panel-body form-horizontal payment-form">
											<fieldset>
											<legend>Ajouter secteur</legend>
									<div class="form-group">
										<label for="nom" class="col-sm-3 control-label">Nom secteur <span class="requis">*</span></label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="nomsect" name="nomsect"
												value="<c:out value="${secteur.nomsect}"/>"> <span
												class="erreur">${formsect.erreurs['nomsect']}</span>
												
										</div>
										
										
										<div class="col-sm-3 text-right">
											<button type="submit" class="btn btn-default preview-add-button">Ajouter
											</button>

										</div>
										
											
									</div>
																		
                                    
										<hr/>
								
									<p class="${empty formSect.erreurs ? 'succes' : 'erreur'}">${formSect.resultat}</p>
		</fieldset>
					
			</div>
			</form>	
			
			</div>			