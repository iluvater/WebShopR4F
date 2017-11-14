<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Warenkorb</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body>
	<jsp:useBean id="ShoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean> 
<c:if test ="${empty ShoppingBasket.items}">
<div id="container">
		<div class="inhalt">
		<h4>Warenkorb</h4>
		<p>Dein Warenkorb ist noch leer. 
		<br/>
		Lege los und fülle ihn mit deinen neuen Lieblingsschuhen!</p>
		</div>
		<hr />
		<center>
			<input type="submit" id="button" value="Zu deinen Lieblingschuhen" />
			<input type="submit" id="button" value="Neues R4F Konto erstellen" />
		</center>
</div>
</c:if>
<c:if test ="${not empty ShoppingBasket.items}">
	<div id="container">
		<div class="inhalt">
		<h4>Warenkorb</h4>
		<ul class="inline">
			<li class="inline"><h4>Artikel</h4></li>
			<li class="inline"><h4>Farbe</h4></li>
			<li class="inline"><h4>Größe</h4></li>
			<li class="inline"><h4>Preis</h4></li>			
		</ul>
		<c:forEach items="${ShoppingBasket.items}" var="item">		
		<ul>
			<li><p>${item.article.name}</p></li>
			<li><p>${item.article.color}</p></li>
			<li><p>${item.article.size}</p></li>
			<li><p>${item.article.price}</p></li>			
		</ul>
		</c:forEach>
		<ul>
		<li><h4>Gesamtpreis:</h4></li>
		<li><p><jsp:getProperty property="price" name="shoppingbasket"/></p></li>
		</ul>		
		</div>
		<hr />
		<center>
			<input type="submit" id="button" value="Zur Kasse" />
		</center>
	</div>
</c:if>
</body>
</html>