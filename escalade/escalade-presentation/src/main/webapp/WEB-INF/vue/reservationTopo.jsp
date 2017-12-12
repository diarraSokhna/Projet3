

<html>
<head>
<title>Escalade</title>
 <link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
 <script src="https://cdn.rawgit.com/atatanasov/gijgo/master/dist/combined/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://cdn.rawgit.com/atatanasov/gijgo/master/dist/combined/css/gijgo.min.css" rel="stylesheet" type="text/css" />
 
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
 <div class="panel panel-default">
 <div class="panel-heading"><h2>Réserver un topo</h2></div>
 <div class="panel-body">
 <form method="post" accept-charset="UTF-8" action="Reservation" >

             <div class="panel-body form-horizontal payment-form">
              <fieldset>
          
              
              <div class="form-group">
                        <label for="nom" class="col-sm-3 control-label">Date réservation <span class="requis">*</span></label>
                        <div class="col-sm-9">
                      <input id="datepicker" name="datepicker" class="form-control" value="<c:out value="${ reservation.date_resa}" />"/>
                      </div>
                    </div>
                    
                       <div class="form-group">
        <label class="col-xs-3 control-label">Topo</label>
        <div class="col-sm-9 selectContainer">
            <select  class="form-control" name="topo" id="topo">
            <option value="0"> Choisir un topo </option>
             <c:forEach var = "topo" items = "${ topos }">
                <option value="<c:out value="${ topo.idtopo}" />"><c:out value="${ topo.nom}" /></option>
                            
               </c:forEach>
            </select>
            <span class="erreur">${form.erreurs['topo']}</span>
          

              Si le site n'existe pas vous pouvez l'<a href="<c:url value="/CreationTopo" />">ajouter</a>
        </div>
    </div>
                   
                       
                    <div class="form-group">
                        <div class="col-sm-12 text-right">
                            <button type="submit" class="btn btn-default preview-add-button">
                                Réserver
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
            <c:otherwise>   <input type="text"  class="form-control" id="utilisateur" name="utilisateur" value="<c:out value="${sessionScope.sessionUtilisateur.iduser}"/>" >
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
<script type="text/javascript">
$(document).ready(function () {
    $('#datepicker').datepicker({
      uiLibrary: 'bootstrap'
    });
});
        </script>
</body>
</html>