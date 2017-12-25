
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 <form method="post" accept-charset="UTF-8" action="ReservationTopo" >

             <div class="panel-body form-horizontal payment-form">
              <fieldset>
          
              
              <div class="form-group">
                        <label for="nom" class="col-sm-3 control-label">Date réservation <span class="requis">*</span></label>
                        <div class="col-sm-9">
                      <input id="dateresa" name="dateresa" placeholder="jj-mm-aaaa" class="form-control" value="<c:out value="${ reservation.date_resa}" />"/>
                      </div>
                    </div>
                     <span class="erreur">${form.erreurs['dateresa']}</span>
       
                    <input type="hidden"  class="form-control" id="idtopo" name="idtopo" value="<c:out value="${topo.idtopo}"/>" >
                          
                       
                    <div class="form-group">
                        <div class="col-sm-12 text-right">
                            <button type="submit" class="btn btn-default preview-add-button">
                                Réserver
                            </button>
                            
                             <a href="<c:url value="/DetailsTopo"/>" class="btn btn-primary" role="button">Retour</a></p>
                        </div>
                    </div>
                    
                    <c:choose>
                        <c:when test="${ empty sessionScope.sessionUtilisateur }">
                <p class="erreur">Veuillez vous connecter d'abord! <a href="<c:url value="/Connection" />">ici</a></p>
            </c:when>
            <c:otherwise>   <input type="hidden"  class="form-control" id="utilisateur" name="utilisateur" value="<c:out value="${sessionScope.sessionUtilisateur.iduser}"/>" >
                            
                          
                          
                       
                      <p class="${empty form.erreurs ? 'succes' : 'erreur'}"> ${form.resultat}</p>
</c:otherwise>
</c:choose>

<c:choose>
 <c:when test="${ !empty form.resultat }">
 <h4>Récapitulatif</h4>
Date de réservation : <b> <fmt:formatDate pattern="dd-MM-yyyy" value="${reservation.date_resa}" /></b><br/>
Nom du topo  : <b><c:out value="${ reservation.topo.nom}" /></b>

</c:when>
</c:choose>
                     
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