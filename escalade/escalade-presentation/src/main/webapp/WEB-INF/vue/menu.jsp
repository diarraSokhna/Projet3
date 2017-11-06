<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
    <li class=""><a href="<%=request.getContextPath()+"/accueil"%>">Site</a></li> 
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown"  href="">Topo
        <span class="caret"></span></a>
         <ul class="dropdown-menu">
           <li class=""><a href="<%=request.getContextPath()+"/AjoutTopo"%>">Ajouter topo</a></li> 
           <li class=""><a href="<%=request.getContextPath()+"/ListeTopo"%>">Liste des topos</a></li> 
            
        </ul>
        </li>
       
        <li><a href="#">Article</a></li>
        <li><a href="#">S'inscrire</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Connexion</a></li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>