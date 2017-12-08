<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Merkliste</title>
<link href="DesignV1.css" rel="stylesheet">

</head>
<body>
	<jsp:useBean id="wishlist" class="r4f.model.Wishlist" scope="session">
	</jsp:useBean>
	
	<c:if test ="${empty wishlist.list}">
	<div class="container">
		<h3 class="impressum">Merkliste</h3>
		<div class="warenkorbLeer">
			<p class="impressum">Ihre Merkliste ist noch leer. <br/>Legen Sie los und f&uuml;llen Sie ihn mit Ihren neuen Lieblingsschuhen!</p>
		</div>
		<hr />
		<center>
			<input type="submit" id="button" value="Zu deinen Lieblingschuhen" />
			<input type="submit" id="button" value="Neues R4F Konto erstellen" />
		</center>
	</div>
	</c:if>
	<c:if test ="${not empty wishlist.list}">
	<div id="container">	
		<h3 class="impressum">Merkliste</h3>
 			<c:forEach items="${wishlist.list}" var="item">
				<div class="artikel">
					<div class="bild"><img class="ImgWarenkorb" src="./ImageServlet/${item.article.mainImage}" alt="Bild"></div> 
					<div class="daten">
						<p><b>${item.article.name}</b></p>
						<p><b>Farbe: </b>${item.color}</p>
						<p><b>Größe: </b>${item.size}</p>
						<p><b>Preis: </b><b>${item.article.price}&euro;</b></p>
					</div> 
					<div class="menge">					
<%-- 							<p><b>Menge: </b>
							<form action="./ChangeAmountShoppingBasketServlet" method="post">
								<input type="hidden" name="articleId" value="${item.article.id }" />
 								<select name="amount" onChange="submit();">
 									<option value="${item.amount}">${item.amount}</option>
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
							</form>
							</p> --%>
						<form action="./RemoveArticleFromWishlistServlet" method="post">
							<input type="hidden" name="articleId" value="${item.article.id }" />
							<p><input type="submit" id="buttonEntfernen" value="entfernen" /></p>
						</form>
					</div> 
				</div>
			</c:forEach>
			
			<div class="gesamtpreis">
 			<div id="weiterEinkaufen">
				<p><input type="submit" id="button" value="Weiter Einkaufen" /></p>
			</div>
<%-- 			<div class="gesamtpreis"> 
					<h4>Gesamtpreis:</h4>
					<div class="preisbezeichnung">
						<p>Bestellwert:</p>
						<p>Versandkosten:</p>
						<h5>Gesamtkosten:</h5>
					</div>
					<div class="preis">
						<p><jsp:getProperty property="orderPrice" name="shoppingBasket"/>&euro;</p>
						<p><jsp:getProperty property="shippingPrice" name="shoppingBasket"/>&euro;</p>
						<h5><jsp:getProperty property="totalPrice" name="shoppingBasket"/>&euro;</h5>
					</div> --%>
 				<div id="weiterEinkaufen">
 				<form action="./WishlistToShoppingBasketServlet" method="post">
					<p><input type="submit" id="button" value="In den Warenkorb" /></p>
				</form>
				</div>
				</div>
			<!-- </div> -->
		</div>
	</c:if>
</body>
</html>