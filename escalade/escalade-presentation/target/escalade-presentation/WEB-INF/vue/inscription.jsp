
<html>
<head>

</head>
<body>
<%@ include file="menu.jsp" %>

  <div class="container">
    <div class="panel panel-default">
  <div class="panel-heading"><h2>Inscrivez vous c'est gratuit!</h2></div>
 <div class="panel-body">
		
        <form method="post" accept-charset="UTF-8" action="Inscription">
             <div class="panel-body form-horizontal payment-form">
              <fieldset>
             <div class="form-group">
						<label for="nom" class="col-sm-5 control-label">Nom d'utilisateur<span class="requis">*</span>
						</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="nom" name="nom"
								value="<c:out value="${utilisateur.nom}"/>" size="20"
								maxlength="60" /> 
								
								<span class="erreur">${form.erreurs['nom']}</span>
						</div>
					</div>
					
					 <div class="form-group">
						<label for="prenom" class="col-sm-5 control-label">Prénom<span class="requis">*</span>
						</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="prenom" name="prenom"
								value="<c:out value="${utilisateur.prenom}"/>" size="20"
								maxlength="60" /> <span class="erreur">${form.erreurs['prenom']}</span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="email" class="col-sm-5 control-label">Adresse
							email <span class="requis">*</span>
						</label>
						<div class="col-sm-5">
							<input type="email" class="form-control" id="email" name="email"
								value="<c:out value="${utilisateur.email}"/>" size="20"
								maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>
						</div>
					</div>
                    
                    <div class="form-group">
					<label for="motdepasse" class="col-sm-5 control-label">Mot de passe <span class="requis">*</span></label>
               <div class="col-sm-5"> <input type="password" class="form-control" id="motdepasse" name="motdepasse" value="<c:out value="${utilisateur.motpass}"/>"" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                </div>
              </div>

               <div class="form-group">
                <label for="confirmation" class="col-sm-5 control-label">Confirmation du mot de passe <span class="requis">*</span></label>
               <div class="col-sm-5"> <input type="password" class="form-control" id="confirmation" name="confirmation" value=""" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation']}</span>
               </div>
               </div>

              <div class="form-group">
                        <div class="col-sm-12 text-right">
                         <a href="<c:url value="/Connection" />"> Déjà membre?</a>
                          <button  type="submit" value="Inscription" class="btn btn-primary">
                                Inscription
                            </button>
                             <button type="reset" class="btn btn-primary">
                                 Annuler
                            </button>
           
                 </div>
                    </div>
               <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
               	<c:if test="${!empty sessionScope.sessionUtilisateur}">
								<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
								<p class="succes">Vous êtes connecté(e) avec l'adresse :
									${sessionScope.sessionUtilisateur.email} passe:
									${sessionScope.sessionUtilisateur.motpass}
									${sessionScope.sessionUtilisateur.iduser}</p>
							</c:if>
               </fieldset>
          </div>
   
         
        </form>
</div>
</div>
</div>
</body>
</html>