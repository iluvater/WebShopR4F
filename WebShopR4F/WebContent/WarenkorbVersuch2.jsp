<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WarenkorbVersuch2</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body>
	<jsp:useBean id="article" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean> 
<c:if test ="${empty ShoppingBasketItem.getArticle()}">
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
<!--<c:if test ="${not empty ShoppingBasketItem.getArticle()}">
	var=totalprice;
	totalprice=0;
	<div id="container">
		<div class="inhalt">
		<h4>Warenkorb</h4>
		<ul>
			<li><h4>Artikel</h4></li>
			<li><h4>Farbe</h4></li>
			<li><h4>Größe</h4></li>
			<li><h4>Preis</h4></li>			
		</ul>
		while(test ="${not empty ShoppingBasketItem}){
		<ul>
			<li><p><jsp:getProperty property="name" name="article"/></p></li>
			<li><p><jsp:getProperty property="color" name="article"/></p></li>
			<li><p><jsp:getProperty property="size" name="article"/></p></li>
			<li><p><jsp:getProperty property="price" name="article"/></li>			
		</ul>
		totalprice=totalprice+ShoppingBasketItem.getAmount();
		ShoppingBasketItem.next();
		}
		<ul>
		<li><h4>Gesamtpreis:</h4></li>
		<li>echo(totalprice);</li>
		</ul>		
		</div>
		<hr />
		<center>
			<input type="submit" id="button" value="Zur Kasse" />
		</center>
	</div>
</c:if>-->
</body>
</html>