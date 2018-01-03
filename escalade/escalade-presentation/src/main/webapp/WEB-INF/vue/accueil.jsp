<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<html>
<head>
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<%@ include file="head.jsp"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<b>A la une</b>
				</h4>
			</div>
			<div class="panel-body">

				<div class='row'>
					<div class='col-sm-12'>
						<div class="carousel slide" data-ride="carousel"
							id="quote-carousel">
							<!-- Bottom Carousel Indicators -->
							<ol class="carousel-indicators">
								<li data-target="#quote-carousel" data-slide-to="0"
									class="active"></li>
								<li data-target="#quote-carousel" data-slide-to="1"></li>
								<li data-target="#quote-carousel" data-slide-to="2"></li>
							</ol>

							<!-- Carousel Slides / Quotes -->
							<div class="carousel-inner">

								<!-- Quote 1 -->
								<div class="item active">
									<blockquote>
										<div class="row">
											<div class="col-sm-5 text-center">
												<img class="img image"
													src="/escalade-presentation/img/slide2.jpg">
												<!--<img class="img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/kolage/128.jpg" style="width: 100px;height:100px;">-->
											</div>
											<div class="col-sm-6">
												<p><b>Film : L’Académie de l’Aventure © The Roof en stage
													"grande voie" avec Daniel Du Lac à la Jonte</b></p>
												<small> 9 décembre 2017 à 07:58</small>
											</div>
										</div>
									</blockquote>
								</div>
								<!-- Quote 2 -->
								<div class="item">
									<blockquote>
										<div class="row ">
											<div class="col-sm-5 text-center">
												<img class="img image"
													src="/escalade-presentation/img/slide1.jpg">
											</div>
											<div class="col-sm-6">
												<p><b>Catherine Destivelle présente son nouveau livre pour
													enfants : « L’escalade tu connais ? »</b></p>
												<small>17 novembre 2017 à 12:30</small>
											</div>
										</div>
									</blockquote>
								</div>
								<!-- Quote 3 -->
								<div class="item">
									<blockquote>
										<div class="row">
											<div class="col-sm-5 text-center">
												<img class="img image"
													src="/escalade-presentation/img/slide3.jpg">
											</div>
											<div class="col-sm-6">
												<p><b>Romain Desgranges gagne la coupe du monde 2017 !</b></p>
												<small>12 novembre 2017 à 00:09</small>
											</div>
										</div>
									</blockquote>
								</div>
							</div>

							<!-- Carousel Buttons Next/Prev -->
							<a data-slide="prev" href="#quote-carousel"
								class="left carousel-control"><i class="fa fa-chevron-left"></i></a>
							<a data-slide="next" href="#quote-carousel"
								class="right carousel-control"><i
								class="fa fa-chevron-right"></i></a>
						</div>
					</div>
				</div>


			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>
					<b>Les articles</b>
				</h4>
			</div>
			<div class="panel-body">

				<c:forEach var="article" items="${ articles }">
					<div class="panel panel-default">

						<div class="panel-body">

							<div class="media">
								<div class="media-left">
									<a href="#"> <img class="media-object image"
										src="<c:url value="/images/${ article.photo }"/>" alt="">
									</a>
								</div>
								<div class="media-body">
									<h3 class="media-heading">
										<c:out value="${ article.titre }" />
									</h3>
									<br /> Publié par
									<c:out value="${ article.utilisateur.nom }" />
									<c:out value="${ article.utilisateur.prenom }" />
									<br />
									<br />
									<c:out value="${ article.contenu}" />
									<br />
									<section style="margin-top: 75px">
										<i class="glyphicon glyphicon-calendar"></i>
										<fmt:formatDate pattern="dd-MM-yyyy"
											value="${article.datepubli}" />
										<a
											href="<c:url value="/DetailsArticle"> 
		                    <c:param name="titrearticle" value="${ article.titre }" > </c:param>
		                    <c:param name="idArt" value="${ article.id_art }" > </c:param></c:url> "
											class="btn btn-default btn-sm pull-right">Lire la suite
											... </a>
									</section>
								</div>
							</div>

						</div>
					</div>
				</c:forEach>


			</div>
		</div>
	</div>
<%-- <%@ include file="footer.jsp" %> --%>
</body>
</html>