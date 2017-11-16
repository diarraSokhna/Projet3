<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>Escalade</title>
<link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
 <link href="/escalade-presentation/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="/escalade-presentation/css/style.css" type="text/css">
 
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

</head>
<body>

<%@ include file="menu.jsp" %>
  <form method="post" action="AjoutTopo" enctype="multipart/form-data" name="formt"></form>
Nom: <input type="text" name="nom" /> <br/>
Description: <input type="text" name="description" /><br/>
User: <input type="text" name="user" /><br/>
Nbr page: <input type="text" name="page" /><br/>
Choisir image: <input type="text" name="img" />
</body>
</html>