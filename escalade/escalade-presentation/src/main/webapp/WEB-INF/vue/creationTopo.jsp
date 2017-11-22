

<html>
<head>
</head>
<body>
<%@ include file="menu.jsp" %>
 <form method="post" action="CreationTopo" enctype="multipart/form-data">
  <div class="col-sm-5">
    <div class="panel panel-default">
             <div class="panel-body form-horizontal payment-form">
                    <div class="form-group">
                        <label for="nom" class="col-sm-3 control-label">Nom <span class="requis">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="nom" name="nom" value="<c:out value="${topo.nom}"/>" >
                            <span class="erreur">${form.erreurs['nom']}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">Description <span class="requis">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="description" name="description" value="<c:out value="${topo.description}"/>" >
                            <span class="erreur">${form.erreurs['description']}</span>
                        </div>
                    </div> 
                       <div class="form-group">
                        <label for="iduser" class="col-sm-3 control-label">Utilisateur</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="iduser" name="iduser" value="<c:out value="${topo.iduser}"/>" >
                            <span class="erreur">${form.erreurs['utilisateur']}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nbpage" class="col-sm-3 control-label">Nombre de page</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="nbpage" name="nbpage" value="<c:out value="${topo.nbpage}"/>" >
                            <span class="erreur">${form.erreurs['nbpage']}</span>
                        </div>
                    </div>
                    
                  
                    <div class="form-group">
                        <label for="photo" class="col-sm-3 control-label">Choisir photo</label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" id="photo" name="photo" value="<c:out value="${topo.image}"/>" onchange="" multiple/>
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
                </div>
                </div>
 </div>


 </form>

</body>
</html>