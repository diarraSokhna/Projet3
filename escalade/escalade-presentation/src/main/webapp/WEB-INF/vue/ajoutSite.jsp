

<html>
<head>
<title>Escalade</title>
 <link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
 <form method="post" accept-charset="UTF-8" action="AjoutSite" enctype="multipart/form-data">
  <div class="col-sm-9">
    <div class="panel panel-default">
             <div class="panel-body form-horizontal payment-form">
             <c:set var="pays" value="${ site.pays }" scope="request" />
             <c:set var="classment" value="${ site.classement }" scope="request" />
              <fieldset>
                <legend>Ajouter un site </legend>
                    <div class="form-group">
                        <label for="nom" class="col-sm-3 control-label">Nom <span class="requis">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="nom" name="nom" value="<c:out value="${site.nomsite}"/>" >
                           
                            <span class="erreur">${form.erreurs['nomsite']}</span>
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
									</select>
							<span class="erreur">${form.erreurs['pays']}</span>
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
									</select> <span class="erreur">${form.erreurs['pays']}</span> Si le pays
									n'existe pas vous pouvez <a href="">l'ajouter</a>
								</div>
							</div>
    
                  
                    <div class="form-group">
                        <label for="photo" class="col-sm-3 control-label">Choisir photo</label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control-file" id="image" name="image" value="<c:out value="${site.image}"/>" onchange="" multiple/>
                            <span class="erreur">${form.erreurs['image']}</span>
                        </div>
                    </div>   
                    <div class="form-group">
                        <div class="col-sm-12 text-right">
                            <button type="submit" class="btn btn-default preview-add-button">
                                Ajouter
                            </button>
                            
                             <button type="reset" class="btn btn-default preview-add-button">
                                 Annuler
                            </button>
                        </div>
                    </div>
                    
       
                     <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
              </fieldset>
                </div>
                </div>
 </div>



 </form>
 

 
</div>
</body>
</html>