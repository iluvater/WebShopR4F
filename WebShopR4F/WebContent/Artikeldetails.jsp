<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Artikeldetailseite</title>
<link href="DesignV1.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:useBean id="article" class="r4f.model.Article" scope="request">
	</jsp:useBean> 
<%-- 	<jsp:useBean id="size" class="java.lang.Integer" scope="session">
	</jsp:useBean>
	<jsp:useBean id="color" class="java.lang.String" scope="session">
	</jsp:useBean> --%>
	<div id="container">
			<c:if test ="${not empty article.name}">
				<div>
					<img id="ImgArtikelDetails" src="./ImageServlet/${article.mainImage }" alt="Bild">
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
						<strong>Artikel-ID:</strong> <jsp:getProperty property="id" name="article"/> <br />
						
						
						
						
						<c:if test="${empty size }">
							<label for="size">Größe:</label>
							<select id="size" name="size">
							<c:forEach items="${article.size}" var="articleSize" begin="0" end="0">
								<option selected value="${articleSize}">${articleSize}</option>
							</c:forEach>
							</select>
						</c:if>
						
						
						
<%-- 						<strong>Gr&ouml;&szlig;e:</strong> <jsp:getProperty property="size" name="article"/><br />
						<strong>Farbe: </strong><jsp:getProperty property="color" name="article"/><br /> --%>
						<strong>Hersteller: </strong><jsp:getProperty property="manufacturer" name="article"/><br />
						<strong>Sportart: </strong><jsp:getProperty property="sport" name="article"/><br />
						<strong>Kategorie: </strong><jsp:getProperty property="category" name="article"/><br />
					</p>
				</div>
				<div>
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
</body>
</html>