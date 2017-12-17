<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />

</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
 <div class="panel panel-default">
  <div class="panel-heading"><h2><c:out value="${ article.titre }"/></h2></div>
 <div class="panel-body">
         
            <div class="media">
                <div class="media-left">
                   
                         <img class="media-object" src="<c:url value="/images/${ article.photo }"/>" alt="" width="250" height="250"> 
                </div>
                <div class="media-body">
                <h3 class="media-heading"><c:out value="${ article.titre }"/></h3><br/>
                Ecrit par  <c:out value="${ article.utilisateur.nom }"/> <c:out value="${ article.utilisateur.prenom }"/><br/><br/>
                <c:out value="${ article.contenu}" /> <br/>
                         <section style="margin-top: 75px">
		                    
		                    <i class="glyphicon glyphicon-comment"></i>2
                            <i class="glyphicon glyphicon-calendar"></i><fmt:formatDate pattern="dd-MM-yyyy" value="${article.datepubli}" />
		                    <a href="<c:url value="/ListeArticle"/>" class="btn btn-default btn-sm pull-right">Retour</a>
		                </section>
               </div>
            </div>
          
        </div>
    </div>
    
    
 <div class="panel panel-default">
  <div class="panel-heading"><h2>Vous aussi exprimez vous</h2></div>
 <div class="panel-body">
				<form method="post" accept-charset="UTF-8" action="<c:url value="/AjoutCommentaire"> 
	                    <c:param name="titrearticle" value="${ article.titre }" > </c:param>
	                    <c:param name="idArt" value="${ article.id_art }" > </c:param></c:url>" class="form-inline">
					<div class="form-group">
					<input type="hidden" class="form-control" id="article"name= "article" placeholder="Article"
						value="<c:out value="${article.id_art}"/>" size="20"maxlength="60" /> 
						<span class="erreur">${form.erreurs['article']}</span>
						
						</div>
					 
						<input type="text" id="libelle" name="libelle" value="<c:out value="${commentaire.libelle}"/>" 
						   class="form-control" placeholder="Votre message ici..." /> 
						<button type="submit" value="" class="btn btn-primary btn-sm" OnClick="" >Ajouter
							commentaire </button>
							<span class="erreur">${form.erreurs['libelle']}</span>
					
					<c:choose>
						<c:when test="${ empty sessionScope.sessionUtilisateur }">
							<p class="erreur">
								Merci de vous connecter d'abord! <a
									href="<c:url value="/Connection" />">ici</a>
							</p>
						</c:when>
						<c:otherwise>
							<input type="hidden" class="form-control" id="utilisateur"
								name="utilisateur"
								value="<c:out value="${sessionScope.sessionUtilisateur.iduser}"/>">
							<span class="erreur">${form.erreurs['utilisateur']}</span>
						</c:otherwise>
					</c:choose>
				</form>
</div>
				<c:forEach var="commentaire" items="${ commentaires }">
					<hr data-brackets-id="12673">
					<ul data-brackets-id="12674" id="sortable"
						class="list-unstyled ui-sortable">
						<strong class="pull-left primary-font"> <i
							class="glyphicon glyphicon-user"></i>
						<c:out value="${ commentaire.utilisateur.nom }" /> <c:out
								value="${ commentaire.utilisateur.prenom }" /></strong>
						<small class="pull-right text-muted"> <i
							class="glyphicon glyphicon-calendar"></i></span> <fmt:formatDate
								pattern="dd-MM-yyyy" value="${commentaire.datecom}" />
						</small>
						</br>
						<br />
						<li class="ui-state-default"><c:out
								value="${ commentaire.libelle }" /></li>

					</ul>
				</c:forEach>

			</div>
    
   
</div>

  </body>
</html>