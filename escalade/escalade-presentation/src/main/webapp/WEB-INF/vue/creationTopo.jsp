

<html>
<head>
<title>Escalade</title>
 <link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
 <form method="post" action="CreationTopo" enctype="multipart/form-data">
  <div class="col-sm-9">
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
                            <textarea  class="form-control" id="description" name="description" value="<c:out value="${topo.description}"/>" >
                           </textarea>
                            <span class="erreur">${form.erreurs['description']}</span>
                        </div>
                    </div> 
                       <div class="form-group">
                        <label for="iduser" class="col-sm-3 control-label">Utilisateur</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="utilisateur" name="utilisateur" value="<c:out value="${topo.iduser}"/>" >
                            <span class="erreur">${form.erreurs['utilisateur']}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nbpage" class="col-sm-3 control-label">Nombre de page</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="nbrpage" name="nbrpage" value="<c:out value="${topo.nbpage}"/>" >
                            <span class="erreur">${form.erreurs['nbpage']}</span>
                        </div>
                    </div>
                    
                  
                    <div class="form-group">
                        <label for="photo" class="col-sm-3 control-label">Choisir photo</label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" id="image" name="image" value="<c:out value="${topo.image}"/>" onchange="" multiple/>
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
</div>
</body>
</html>