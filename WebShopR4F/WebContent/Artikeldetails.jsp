<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artikeldetailseite</title>
<link href="DesignV1.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:useBean id="article" class="r4f.model.Article" scope="request">
	</jsp:useBean> 
	<div id="kopf">
		<h1>Überschrift Test</h1>
	</div>
	<div id="container">
		<div>
			<c:if test ="${not empty article.name}">
				<div class="containerArtikelDetails">
					<img id="ImgArtikelDetails" src="./ImageServlet/${article.image }" alt="Bild">
					<h3 class="artikelDetails"><jsp:getProperty property="name" name="article"/></h3> 
					<h4 class="artikelDetails">Preis: <jsp:getProperty property="price" name="article"/> &euro; </h4>
					<hr />
					<a href="Willkommen.jsp" id="button" class="btn btn-default btn-sm">
          				<span class="glyphicon glyphicon-heart-empty"></span> Merkliste
        			</a>
        			<form action="./AddToShoppingBasketServlet" method="post">
        			<input type="hidden" name="articleId" value="${article.id }" />
        			<button id="button" class="btn btn-default btn-sm">
          				<span class="glyphicon glyphicon-shopping-cart"></span> Warenkorb
        			</button>
        			</form>
				</div>
				<div class="containerArtikelDetails">
					<h4 class="artikelDetails">Produktioninformationen</h4>
					<p class="artikelDetails">
						<strong>Gr&ouml;&szlig;e:</strong> <jsp:getProperty property="size" name="article"/><br />
						<strong>Farbe: </strong><jsp:getProperty property="color" name="article"/><br />
						<strong>Hersteller: </strong><jsp:getProperty property="manufacturer" name="article"/><br />
						<strong>Sportart: </strong><jsp:getProperty property="sport" name="article"/><br />
						<strong>Kategorie: </strong><jsp:getProperty property="category" name="article"/><br />
					</p>
				</div>
				<div class="containerArtikelDetails">
					<h4 class="artikelDetails">Beschreibung</h4>
					<p class="artikelDetails">
						<jsp:getProperty property="description" name="article"/>	
					</p>				
				</div>
			</c:if>
			<c:if test ="${empty article.name}">
				<p class="fehler">
					Es ist ein Fehler aufgetreten. Bitte Versuchen Sie es erneut.
				</p>
			</c:if>
		</div>
	</div>
	<div id="fuss">
		<p class="footer"><a class="footer" href="Test.jsp">AGB's</a> <a class="footer" href="Test.jsp">Kontaktseite</a> <a class="footer" href="Test.jsp">Impressum</a> <a class="footer" href="Test.jsp">Hilfeseite</a> © 2017 Run4Fun GmbH, Alle Rechte vorbehalten</p>
	</div>
</body>
</html>