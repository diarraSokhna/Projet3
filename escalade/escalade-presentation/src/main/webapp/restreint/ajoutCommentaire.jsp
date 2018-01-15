
<form method="post" accept-charset="UTF-8"
	action="<c:url value="/AjoutCommentaire"> 
	                    <c:param name="nomtopo" value="${ topo.nom }" > </c:param>
	                    <c:param name="idtopo" value="${ topo.idtopo }" > </c:param></c:url>"
	class="form-inline">
	<div class="form-group">
		<input type="hidden" class="form-control" id="topo" name="topo"
			placeholder="" value="<c:out value="${topo.idtopo}"/>" size="20"
			maxlength="60" /> <span class="erreur">${form.erreurs['topo']}</span>

	</div>
	<div class="form-group">
		<div class="col-sm-12">
			<input type="text" id="libelle" name="libelle"
				value="<c:out value="${commentaire.libelle}"/>" class="form-control"
				placeholder="Votre message ici..." />
			<button type="submit" value="" class="btn btn-primary btn-sm"
				OnClick="">Ajouter commentaire</button>
		</div>
	</div>
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