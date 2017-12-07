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
	<div id="container">	
		<h3 class="impressum">BEstellübersicht</h3>
 			<c:forEach items="${shoppingBasket.items}" var="item">
				<div class="artikel">
						<div class="bild"><img class="ImgWarenkorb" src="./ImageServlet/${item.article.image}" alt="Bild"></div> 
						<div class="daten">
							<p><b>${item.article.name}</b></p>
							<p><b>Farbe: </b>${item.article.color}</p>
							<p><b>Größe: </b>${item.article.size}</p>
							<p><b>Preis: </b><b>${item.article.price}&euro;</b></p>
							<p><b>Menge: </b>${item.amount}</p>
						</div> 		
					</div>
				</c:forEach>
 				<div id="weiterEinkaufen">
 						<p><input type="submit" id="button" value="Abbrechen" /></p>
						<p><input type="submit" id="button" value="Kostenpflichtig bestellen" /></p>
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
						<p><jsp:getProperty property="totalPrice" name="shoppingBasket"/>&euro;</p>
						<h5><jsp:getProperty property="totalPrice" name="shoppingBasket"/>&euro;</h5>
					</div>
				</div>
			</div>
</body>
</html>