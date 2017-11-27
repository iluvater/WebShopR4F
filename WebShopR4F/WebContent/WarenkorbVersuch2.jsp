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
		<br/>Lege los und f&uuml;lle ihn mit deinen neuen Lieblingsschuhen!</p>
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
		<div id="artikel">
			<table width="100%" left="0">
			<c:forEach items="${shoppingBasket.items}" var="item">
				<tr>
					<td class="warenkorb" width="50%" ><img class="ImgWarenkorb" src="./ImageServlet/${item.article.image}" alt="Bild"></td>
					<td class="warenkorb" width="40%">
						<table>
							<tr>
								<td><h4 align="left"><b>${item.article.name}</b></h4>
									<p align="left"><b>Farbe: </b>${item.article.color}</p>
									<p align="left"><b>Größe: </b>${item.article.size}</p>
									<p align="left"><b>Preis: </b><b>${item.article.price}&euro;</b></p>
								</td>
							</tr>
						</table>
					</td>
					<td class="warenkorb" width="30%">
						<p> <b>Menge</b>
 						<select name="amount" value="${item.amount}">
 							<option value="current">${item.amount}</option>
 							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
						</select>
						</p>
					</td>
					<td class="warenkorb" width="5%"> 
						<p> <a href>entfernen </a></p>
					</td>
				</tr>
			</c:forEach>
			</table>
			<left>
				<p>
					<input type="submit" id="button" value="Weiter Einkaufen" />
				</p>
			</left>
		</div>
		<div id="gesamtpreis"> 
			<h4>Gesamtpreis:</h4>
			<table>
				<tr>
					<td text-align="left"><p>Bestellwert:</p></td>
					<td text-align="right"><p><jsp:getProperty property="totalPrice" name="shoppingBasket"/>&euro;</p></td>
				</tr>
				<tr>
					<td text-align="left"><p>Versandkosten:</p></td>
					<td text-align="right"><p><jsp:getProperty property="totalPrice" name="shoppingBasket"/>&euro;</p></td>
				</tr>
				<tr>
					<td text-align="left"><h5>Gesamtkosten:</h5></td>
					<td text-align="right"><h5><jsp:getProperty property="totalPrice" name="shoppingBasket"/>&euro;</h5></td>
				</tr>
			</table>
			<p>
				<input type="submit" id="button" value="Zur Kasse" />
			</p>
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