

<div class="container">


   
      <div class="col-lg-13 col-sm-13 text-left">
    <div class="well">
        <h4>Vous aussi donnez votre avis</h4>
    <div class="input-group">
    <form method="post" accept-charset="UTF-8" action="AjoutCommentaire">
    <input type="text"  class="form-control" id="id_user" name="id_user" placeholder="Utilisateur"
								value="<c:out value="${commentaire.id_user}"/>" size="20"
								maxlength="60" />
		 <input type="text"  class="form-control" id="id_art" name="id_art" placeholder="Article"
								value="<c:out value="${article.id_art}"/>" size="20"
								maxlength="60" />						
								
        <input type="text" id="libelle" name="libelle" value="<c:out value="${commentaire.libelle}"/>" class="form-control input-sm chat-input" placeholder="Votre message ici..." />
	    <button  type="submit" value="" class="btn btn-primary">
                                Ajouter commentaire
                            </button>
        </form>
    </div>
    <c:forEach var = "commentaire" items = "${ commentaires }">
    <hr data-brackets-id="12673">
    <ul data-brackets-id="12674" id="sortable" class="list-unstyled ui-sortable">
        <strong class="pull-left primary-font">
        <i class="glyphicon glyphicon-user"></i><c:out value="${ commentaire.id_user }"/></strong>
        <small class="pull-right text-muted">
          <i class="glyphicon glyphicon-calendar"></i></span>
        <c:out value="${ commentaire.datecom }"/> 
        </small>
        </br><br/>
        <li class="ui-state-default"><c:out value="${ commentaire.libelle }"/></li>
        
    </ul>
    </c:forEach>
    
    </div>
    
   
</div>

 </div>

  