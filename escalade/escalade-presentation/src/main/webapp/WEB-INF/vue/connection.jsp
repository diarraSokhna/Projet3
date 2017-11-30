
<html>
<head>

</head>
<body>
<%@ include file="menu.jsp" %>

  <div class="container">
   
        <form method="post" accept-charset="UTF-8" action="Connection">
        
     <div class="col-sm-7">
    <div class="panel panel-default">
             <div class="panel-body form-horizontal payment-form">
              <fieldset>
                <legend>Connectez vous</legend>
             <div class="form-group">
						<label for="nom" class="col-sm-5 control-label">Adresse email <span class="requis">*</span>
						</label>
						<div class="col-sm-5">
							<input type="text"  class="form-control" id="email" name="email" placeholder="Email"
								value="<c:out value="${utilisateur.email}"/>" size="20"
								maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>
						</div>
					</div>
                    
                    <div class="form-group">
					<label for="motdepasse" class="col-sm-5 control-label">Mot de passe <span class="requis">*</span></label>
               <div class="col-sm-5"> <input type="password" class="form-control" id="motdepasse" name="motdepasse" placeholder="Mot de passe" value="<c:out value="${utilisateur.motpass}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']} + ${utilisateur.motpass}</span>
                </div>
              </div>


              <div class="form-group">
                        <div class="col-sm-12 text-right">
                         <button  type="submit" value="Inscription" class="btn btn-primary">
                                connection
                            </button>
                             <button type="reset" class="btn btn-primary">
                                 Annuler
                            </button>
           
                 </div>
                    </div>
               <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
               <%-- Vérification de la présence d'un objet utilisateur en session --%>
                <c:if test="${!empty sessionScope.sessionUtilisateur}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}  passe: ${sessionScope.sessionUtilisateur.motpass}</p>
                </c:if>
                </fieldset>
          </div>
          </div>
          </div>
         
        </form>
</div>

</body>
</html>