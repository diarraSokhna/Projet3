<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Escalade</title>
 <link rel="shortcut icon" type="image/x-icon" href="/escalade-presentation/img/favicon.png" />
 <link href="/escalade-presentation/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="/escalade-presentation/css/style.css " type="text/css">
 
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="<%=request.getContextPath()+"/accueil"%>"> <i class="glyphicon glyphicon-home"></i></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
    <li class=""><a href="">Site</a></li> 
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown"  href="">Topo
        <span class="caret"></span></a>
         <ul class="dropdown-menu">
           <li class=""><a href="<%=request.getContextPath()+"/AjoutTopo"%>">Ajouter topo</a></li> 
           <li class=""><a href="<%=request.getContextPath()+"/ListeTopo"%>">Liste des topos</a></li> 
            
        </ul>
        </li>
       
        <li><a href="#">Article</a></li>
        <li><a href="<%=request.getContextPath()+"/Inscription"%>">S'inscrire</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Connexion</a></li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>