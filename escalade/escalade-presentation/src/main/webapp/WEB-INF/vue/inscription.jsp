
<html>
<head>

</head>
<body>
<%@ include file="menu.jsp" %>
       
       
       
       
        <form method="post" action="Inscription">
     <div class="col-sm-10">
    <div class="panel panel-default">
             <div class="panel-body form-horizontal payment-form">
             
             <div class="form-group">
						<label for="nom" class="col-sm-5 control-label">Nom<span class="requis">*</span>
						</label>
						<div class="col-sm-5">
							<input type="text" id="nom" name="nom"
								value="<c:out value="${utilisateur.nom}"/>" size="20"
								maxlength="60" /> <span class="erreur">${form.erreurs['nom']}</span>
						</div>
					</div>
					
					 <div class="form-group">
						<label for="prenom" class="col-sm-5 control-label">Prénom<span class="requis">*</span>
						</label>
						<div class="col-sm-5">
							<input type="text" id="prenom" name="prenom"
								value="<c:out value="${utilisateur.prenom}"/>" size="20"
								maxlength="60" /> <span class="erreur">${form.erreurs['prenom']}</span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="email" class="col-sm-5 control-label">Adresse
							email <span class="requis">*</span>
						</label>
						<div class="col-sm-5">
							<input type="email" id="email" name="email"
								value="<c:out value="${utilisateur.email}"/>" size="20"
								maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>
						</div>
					</div>
                    
                    <div class="form-group">
					<label for="motdepasse" class="col-sm-5 control-label">Mot de passe <span class="requis">*</span></label>
               <div class="col-sm-5"> <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                </div>
              </div>

               <div class="form-group">
                <label for="confirmation" class="col-sm-5 control-label">Confirmation du mot de passe <span class="requis">*</span></label>
               <div class="col-sm-5"> <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation']}</span>
               </div>
               </div>

              <div class="form-group">
                        <div class="col-sm-12 text-right">
                          <button  type="submit" value="Inscription" class="btn btn-default preview-add-button">
                                Inscription
                            </button>
                             <button type="reset" class="btn btn-default preview-add-button">
                                 Annuler
                            </button>
           
                 </div>
                    </div>
               <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
          </div>
          </div>
          </div>
        </form>
        
<!--          <form method="post" action="Inscription"> -->
<!--             <fieldset> -->
<!--                 <legend>Inscription</legend> -->
<!--                 <p>Vous pouvez vous inscrire via ce formulaire.</p> -->

<!--                 <label for="email">Adresse email <span class="requis">*</span></label> -->
<%--                 <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" /> --%>
<%--                 <span class="erreur">${form.erreurs['email']}</span> --%>
<!--                 <br /> -->

<!--                 <label for="motdepasse">Mot de passe <span class="requis">*</span></label> -->
<!--                 <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" /> -->
<%--                 <span class="erreur">${form.erreurs['motdepasse']}</span> --%>
<!--                 <br /> -->

<!--                 <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label> -->
<!--                 <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" /> -->
<%--                 <span class="erreur">${form.erreurs['confirmation']}</span> --%>
<!--                 <br /> -->

<!--                 <label for="nom">Nom d'utilisateur</label> -->
<%--                 <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="20" maxlength="20" /> --%>
<%--                 <span class="erreur">${form.erreurs['nom']}</span> --%>
<!--                 <br /> -->

<!--                 <input type="submit" value="Inscription" class="sansLabel" /> -->
<!--                 <br /> -->
                
<%--                 <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p> --%>
<!--             </fieldset> -->
<!--         </form> -->
</body>
</html>