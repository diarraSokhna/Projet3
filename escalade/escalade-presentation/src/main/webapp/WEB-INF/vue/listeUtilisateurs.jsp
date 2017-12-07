
<html>
<head>
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="container">
 <div class="row">
 
                    <div class="col-sm-6 col-md-3 colonn">
                      <table>
                      <thead>
                      <tr> 
                      <th>id </th>
                      <th>nom </th>
                      </tr>
                      </thead>
                      <tbody>
                 <c:forEach var = "utilisateur" items = "${ utilisateurs }">     
                      <tr> 
                      <td>${ utilisateur.id_user } </td>
                      <td>${ utilisateur.nom }</td>
                      </tr>
                      
                      </c:forEach>
                      </tbody>
                      
                      
                      </table>
                    </div>
                 
                     
</div>


	</div>
  </body>
</html>