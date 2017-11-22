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

	<jsp:useBean id="shoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean> 
<c:if test ="${empty shoppingBasket.items}">
<div id="container">
		<div class="warenkorb">
		<h4>Warenkorb</h4>
		<p>Dein Warenkorb ist noch leer. 
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
		<h4>Warenkorb</h4>
		<div class="warenkorb">
 			<div class="hilfekacheln"><h4>Artikel</h4></div>
			<div class="hilfekacheln"><h4>Farbe</h4></div>
			<div class="hilfekacheln"><h4>Gr&ouml;&szlig;e</h4></div>
			<div class="hilfekacheln"><h4>Preis</h4></div>			
		
		<c:forEach items="${shoppingBasket.items}" var="item">
			<div class="hilfekacheln"><p>${item.article.name}</p></div>
			<div class="hilfekacheln"><p>${item.article.color}</p></div>
			<div class="hilfekacheln"><p>${item.article.size}</p></div>
		</c:forEach>
		<div class="hilfekacheln"><h4>Gesamtpreis:</h4><p><jsp:getProperty property="totalPrice" name="shoppingBasket"/></p>	
		</div"> 

		<hr />
		<center>
			<input type="submit" id="button" value="Zur Kasse" />
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