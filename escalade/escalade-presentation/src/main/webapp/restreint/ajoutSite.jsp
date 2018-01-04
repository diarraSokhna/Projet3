
<form method="post" accept-charset="UTF-8" action="AjoutSite"
	enctype="multipart/form-data">
	<div class="panel-body form-horizontal payment-form">
		<fieldset>
			<c:set var="pays" value="${ site.pays }" scope="request" />
			<c:set var="classement" value="${ site.classement }" scope="request" />
			<div class="form-group">
				<label for="nom" class="col-sm-4 control-label">Nom site<span
					class="requis">*</span>
				</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="nom" name="nom"
						value="<c:out value="${site.nomsite}"/>"> <span
						class="erreur">${formsite.erreurs['nom']}</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-4 control-label">Classement<span
					class="requis">*</span></label>
				<div class="col-sm-5 selectContainer">
					<select class="form-control" name="idclass" id="idclass">
						<option value="0">Choisir un classement</option>
						<c:forEach var="classement" items="${ classements }">
							<option value="<c:out value="${ classement.id_class}" />"><c:out
									value="${ classement.libelle_class}" /></option>
						</c:forEach>
					</select> <span class="erreur">${formsite.erreurs['idclass']}</span>
				</div>
			</div>


			<div class="form-group">
				<label class="col-xs-4 control-label">Pays<span
					class="requis">*</span></label>
				<div class="col-sm-5 selectContainer">
					<select class="form-control" name="idpays" id="idpays" >
						<option value="0">Choisir un pays</option>
						<c:forEach var="pays" items="${ payss }">
							<option value="<c:out value="${ pays.idpays}" />"><c:out
									value="${ pays.nompays}" /></option>
						</c:forEach>
					</select> <span class="erreur">${formsite.erreurs['idpays']}</span> Si le
					pays n'existe pas vous pouvez <a
						href="<c:url value="AjoutPays" /> ">l'ajouter</a>
				</div>
			</div>


			<div class="form-group">
				<label class="col-xs-4 control-label">Ville<span
					class="requis">*</span></label>
				<div class="col-sm-5 selectContainer">
					<select class="form-control" name="idville" id="idville">
						<option value="0">Choisir une ville</option>
												<c:forEach var="ville" items="${ villes }">
													<option value="<c:out value="${ ville.id_ville}" />"><c:out
															value="${ ville.nom_ville}" /></option>
												</c:forEach>
					</select> <span class="erreur">${formsite.erreurs['idville']}</span> Si la
					ville n'existe pas vous pouvez <a
						href="<c:url value="AjoutVille" /> ">l'ajouter</a>
				</div>
			</div>

			<div class="form-group">
				<label for="photo" class="col-sm-4 control-label">Choisir
					photo</label>
				<div class="col-sm-5">
					<input type="file" class="form-control-file" id="image"
						name="image" value="<c:out value="${site.image}"/>" onchange=""
						multiple /> <span class="erreur">${formsite.erreurs['image']}</span>


				</div>
			</div>

			<c:choose>
				<c:when test="${ empty sessionScope.sessionSite }">
					<div class="form-group">
						<div class="col-sm-9 text-right">
							<button type="submit" class="btn btn-default preview-add-button">Ajouter
							</button>
						</div>
					</div>
				</c:when>
			</c:choose>


		</fieldset>
	</div>
	<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

</form>



