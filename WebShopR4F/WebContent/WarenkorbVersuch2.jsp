<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Warenkorb</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body>

	<jsp:useBean id="shoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean> 
<c:if test ="${empty shoppingBasket.items}">
<div id="container">
		<h3 class="impressum">Warenkorb</h3>
		<div class="warenkorbLeer">
		<p class="impressum">Dein Warenkorb ist noch leer. 
		<br/>
		Lege los und f&uuml;lle ihn mit deinen neuen Lieblingsschuhen!</p>
		</div>
		<hr />
		<center>
			<input type="submit" id="button" value="Zu deinen Lieblingschuhen" />
			<input type="submit" id="button" value="Neues R4F Konto erstellen" />
		</center>
</div>
</c:if>
<c:if test ="${not empty shoppingBasket.items}">
	<div id="container">
		<h3 class="impressum">Warenkorb</h3>
		<div class="inhaltWarenkorb">
			<table class="warenkorb">		
			<tr>
			<td width="600px">
				<table>
				<c:forEach items="${shoppingBasket.items}" var="item">
				<tr>
				<td class="warenkorb"><img class="ImgWarenkorb" src="./ImageServlet/${item.article.image}" alt="Bild"></td>
				<td class="warenkorb">
				<table>
					<tr>
					<td><h4 align="left"><b>${item.article.name}</b></h4>
					<p align="left"><b>Farbe: </b>${item.article.color}</p>
					<p align="left"><b>Größe: </b>${item.article.size}</p>
					<p align="left"><b>Preis: </b><b>${item.article.price}</b></p>
					</td>
					</tr>
				</table>
				<td>
				</td>
				<td>
				<form action="./MengenServlet" method="post">
				<p><b>Menge:</b>   <input class="warenkorb" placeholder="Menge" name="amount" value="${item.amount}" type="text" /></p>
				</form>
				</td>
			</tr>
		</c:forEach>
		</table>
		</td>
		<td width="200px">
		<div class="warenkorbGesamtpreis" >
		<center>
		<h4 text-align="right">Gesamtpreis:</h4><h5><jsp:getProperty property="totalPrice" name="shoppingBasket"/></h5>	
		<hr />
		<p>
			<input type="submit" id="button" value="Zur Kasse" />
		</p>
		</center>
		</div>
		</td>
		</tr>
		</table>
		<center><p>
			<input type="submit" id="button" value="Weiter Einkaufen" />
			<input type="submit" id="button" value="Zur Kasse" />
			</p>
		</center>
		</div>
	</div>
</c:if>
	<div id="fuss">
		<ul>
			<li><a class="footer" href="Test.jsp">AGB's</a></li>
			<li><a class="footer" href="Test.jsp">Kontaktseite</a></li>
			<li><a class="footer" href="Test.jsp">Impressum</a></li>
			<li><a class="footer" href="Test.jsp">Hilfeseite</a></li>
			<li> © 2017 Run4Fun GmbH, Alle Rechte vorbehalten</li>
		</ul>	
	</div>
</body>
</html>