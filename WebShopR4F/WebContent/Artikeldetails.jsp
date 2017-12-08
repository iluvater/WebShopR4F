<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Artikeldetailseite</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="DesignV1.css" rel="stylesheet">
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
					<p class="deliveryDate">Lieferbar bis spätestens: <fmt:formatDate pattern = "dd.MM.yyyy" value = "${article.deliveryDate}" /></p>
					
					<%-- <jsp:getProperty property="deliveryDate" name="article"/></p> --%>
					<hr />
					
					<form action="./AddToWishlistServlet" method="post">
						<input type="hidden" name="articleId" value="${article.id }" />
						<button id="button" class="btn btn-default btn-sm">
          					<span class="glyphicon glyphicon-heart-empty"></span> Merkliste
        				</button>
					</form>
					<br />
        			<form action="./AddToShoppingBasketServlet" method="post">
        				<input type="hidden" name="articleId" value="${article.id }" />
        			<button id="button" class="btn btn-default btn-sm">
          				<span class="glyphicon glyphicon-shopping-cart"></span> Warenkorb
        			</button>
        			</form>
				</div>
				<div class="containerArtikelDetails">
					<h4 class="artikelDetails">Produktioninformationen</h4>
					<p class="artikelDetails"><b>Artikel-ID: </b> <jsp:getProperty property="id" name="article"/> <br /></p>
					<form action="./ChangeSizeColorServlet" method="post">
						<input type="hidden" name="articleId" value="${article.id }" />
 						<c:if test="${empty size }">
							<p class="artikelDetails"><b>Größe: </b>
							<select id="size" name="size" onChange="submit();">
								<c:forEach items="${article.size}" var="articleSize" begin="0" end="0">
									<option selected value="${articleSize}">${articleSize}</option>
								</c:forEach>
								<c:forEach items="${article.size}" var="articleSize" begin="1">
									<option value="${articleSize}">${articleSize}</option>
								</c:forEach>
							</select>
							</p>
						</c:if> 
						<c:if test="${not empty size }">
							<p class="artikelDetails"><b>Größe: </b>
							<select id="size" name="size" onChange="submit();">
								<c:forEach items="${article.size}" var="articleSize">
									<c:if test="${articleSize eq size}">
										<option selected value="${articleSize}">${articleSize}</option>
									</c:if>
									<c:if test="${articleSize ne size}">
										<option value="${articleSize}">${articleSize}</option>
									</c:if>
								</c:forEach>
							</select>
							</p>
						</c:if>
					 	<c:if test="${empty color }">
							<p class="artikelDetails"><b>Farbe: </b>
							<select id="color" name="color" onChange="submit();">
								<c:forEach items="${article.color}" var="articleColor" begin="0" end="0">
									<option selected value="${articleColor}">${articleColor}</option>
								</c:forEach>
								<c:forEach items="${article.color}" var="articleColor" begin="1">
									<option value="${articleColor}">${articleColor}</option>
								</c:forEach>
							</select>
							</p>
						</c:if> 
						<c:if test="${not empty color }">
							<p class="artikelDetails"><b>Farbe: </b>
							<select id="color" name="color" onChange="submit();">
								<c:forEach items="${article.color}" var="articleColor">
									<c:if test="${articleColor eq color}">
										<option selected value="${articleColor}">${articleColor}</option>
									</c:if>
									<c:if test="${articleColor ne color}">
										<option value="${articleColor}">${articleColor}</option>
									</c:if>
								</c:forEach>
							</select>
							</p>
						</c:if>
					</form>

					<p class="artikelDetails"><b>Hersteller: </b><jsp:getProperty property="manufacturer" name="article"/><br /></p>
					<p class="artikelDetails"><b>Sportart: </b><jsp:getProperty property="sport" name="article"/><br /> </p>
					<p class="artikelDetails"><b>Kategorie: </b><jsp:getProperty property="category" name="article"/><br /></p>				
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
					Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut.
				</p>
			</c:if>
	</div>
</body>
</html>