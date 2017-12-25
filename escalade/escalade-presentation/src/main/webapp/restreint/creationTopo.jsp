

<html>
<head>
<title>Escalade</title>
 <link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
</head>
<body>


<%@ include file="../WEB-INF/vue/menu.jsp" %>
<jsp:useBean id="site" class="fr.escalade.beans.Site" scope="session"/>
<div class="container">


 <div class="panel panel-default">
 <div class="panel-heading"><h2>Proposez un topo</h2></div>
 <div class="panel-body">
 <form method="post" accept-charset="UTF-8" action="CreationTopo" enctype="multipart/form-data">

             <div class="panel-body form-horizontal payment-form">
              <fieldset>
          
                    <div class="form-group">
                        <label for="nom" class="col-sm-3 control-label">Nom <span class="requis">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="nom" name="nom" value="<c:out value="${topo.nom}"/>" >
                            <span class="erreur">${form.erreurs['nom']}</span>
                        </div>
                    </div>
                       <div class="form-group">
        <label class="col-xs-3 control-label">La liste des sites<span class="requis">*</span></label>
        <div class="col-sm-9 selectContainer">
            <select multiple class="form-control" name="listeSite" id="listeSite">
<!--             <option value="0"> Choisir un site </option> -->
             <c:forEach var = "site" items = "${ sites }">
                <option value="${site.idsite}" ${param.site eq site.idsite ? 'selected' : ''}>${site.nomsite}</option>
               </c:forEach>
            </select>
            <span class="erreur">${form.erreurs['listeSite']}</span>
          
              Si le site n'existe pas vous pouvez l'<a href="<c:url value="/AjoutSite" />">ajouter</a>
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
                        <label for="nbpage" class="col-sm-3 control-label">Nombre de page</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="nbrpage" name="nbrpage" value="<c:out value="${topo.nbpage}"/>" >
                            <span class="erreur">${form.erreurs['nbrpage']}</span>
                        </div>
                    </div>
                    
                  
                    <div class="form-group">
                        <label for="photo" class="col-sm-3 control-label">Choisir photo</label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control-file" id="image" name="image" value="<c:out value="${topo.image}"/>" onchange="" multiple/>
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
                    
                    <c:choose>
                        <c:when test="${ empty sessionScope.sessionUtilisateur }">
                <p class="erreur">Veuillez vous connecter d'abord! <a href="<c:url value="/Connection" />">ici</a></p>
            </c:when>
            <c:otherwise>   <input type="hidden"  class="form-control" id="utilisateur" name="utilisateur" value="<c:out value="${sessionScope.sessionUtilisateur.iduser}"/>" >
                            <span class="erreur">${form.erreurs['utilisateur']}</span>
                       
                      
</c:otherwise>
</c:choose>
                     <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
              </fieldset>
                </div>
               




 </form>
 
</div>
 
</div>
</div>
</body>
</html>