<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bestellübersicht</title>
<link href="DesignV1.css" rel="stylesheet">

</head>
<body>
	<jsp:useBean id="shoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean>
	<jsp:useBean id="order" class="r4f.model.Order" scope="session">
	</jsp:useBean>
	<div id="container">	
		<h3 class="impressum">Bestellübersicht</h3>
			<div id="weiterEinkaufen">
				<h4>Ihre Artikel</h4>
			</div>
 			<c:forEach items="${order.items}" var="item">
				<div class="artikel">
						<div class="bild"><img class="ImgWarenkorb" src="./ImageServlet/${item.article.mainImage}" alt="Bild"></div> 
						<div class="daten">
							<p><b>${item.article.name}</b></p>
							<p><b>Farbe: </b>${item.color}</p>
							<p><b>Größe: </b>${item.size}</p>
							<p><b>Preis: </b><b>${item.article.price}&euro;</b></p>
							<p><b>Menge: </b>${item.amount}</p>
						</div> 		
					</div>
				</c:forEach>
				<div id="weiterEinkaufen">
					<h4>Rechnungsadresse</h4>
						<p>${order.billingAddress.street} ${order.billingAddress.houseNumber}
						<br/>
						${order.billingAddress.postCode}
						<br/>
						${order.billingAddress.city}
						</p>
					<h4>Versandadresse</h4>
						<p>${order.deliveryAddress.street} ${order.deliveryAddress.houseNumber}
						<br/>
						${order.deliveryAddress.postCode}
						<br/>
						${order.deliveryAddress.city}
						</p>
					<h4>Bezahlart</h4>
						<p><b>${order.paymentMethod}</b></p>
				</div>
 				<div id="weiterEinkaufen">
 					<center>
 						<input type="submit" id="button" value="Bestellung abbrechen" />
						<input type="submit" id="button" value="Kostenpflichtig bestellen" />
					</center>
				</div>
				<div class="gesamtpreis"> 
					<h4>Gesamtpreis:</h4>
					<div class="preisbezeichnung">
						<p>Bestellwert:</p>
						<p>Versandkosten:</p>
						<h5>Gesamtkosten:</h5>
					</div>
					<div class="preis">
						<p><jsp:getProperty property="totalPrice" name="shoppingBasket"/>&euro;</p>
						<p><jsp:getProperty property="shippingPrice" name="shoppingBasket"/>&euro;</p>
						<h5><jsp:getProperty property="totalPrice" name="shoppingBasket"/>&euro;</h5>
					</div>
				</div>
			</div>
</body>
</html>