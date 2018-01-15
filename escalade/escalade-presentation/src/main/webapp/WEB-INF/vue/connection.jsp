<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<html>
<head>
<%@ include file="head.jsp"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<b>Connectez vous</b>
				</h4>
			</div>
			<div class="panel-body">
				<div class="login-box">
					<form method="post" accept-charset="UTF-8" action="Connection">
						<div class="panel-body form-horizontal payment-form">
							<fieldset>
								<div class="form-group">
									<label for="email" class="col-sm-4 control-label">Adresse
										email <span class="requis">*</span>
									</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="email"
											name="email" placeholder="Email"
											value="<c:out value="${utilisateur.email}"/>" size="20"
											maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>
									</div>

								</div>

								<div class="form-group">
									<label for="motdepasse" class="col-sm-4 control-label">Mot
										de passe <span class="requis">*</span>
									</label>
									<div class="col-sm-5">
										<input type="password" class="form-control" id="motdepasse"
											name="motdepasse" placeholder="Mot de passe"
											value="<c:out value="${utilisateur.motpass}"/>" size="20"
											maxlength="20" /> <span class="erreur">${form.erreurs['motdepasse']}</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9 text-right">
										<button type="submit" value="Connection"
											class="btn btn-default">Connexion</button>
										<button type="reset" class="btn btn-default">Annuler</button>
									</div>
								</div>

								<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
							</fieldset>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%-- 	<%@ include file="footer.jsp" %> --%>
</body>
</html>