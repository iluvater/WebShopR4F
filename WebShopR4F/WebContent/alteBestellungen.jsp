<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Meine Bestellungen</title>
<link href="DesignV1.css" rel="stylesheet"> 
</head>
<body>--%>
<%@ include file="Head.jspf" %>
<title>Meine Bestellungen</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="order" class="r4f.model.Order" scope="session">
	</jsp:useBean>
	<jsp:useBean id="orderList" type="java.util.ArrayList<r4f.model.Article>" class="java.util.ArrayList" scope="session">
	</jsp:useBean>
	<jsp:useBean id="shoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean>

<c:if test ="${empty orderList.order}">
	<div class="container">
		<h3 class="impressum">Meine Bestellungen</h3>
		<div class="warenkorbLeer">
			<p class="impressum">Sie haben bisher noch keine Bestellungen aufgegeben. 
			<br/>Legen Sie los und finden Ihre neuen Lieblingsschuhe!</p>
		</div>
		<hr />
		<center>
			<form action="./NavigationOverwiewServlet" method="post">
				<input type="submit" id="button" value="Zu deinen Lieblingschuhen" />
			</form>
			<form action="./NavigationToRegistrationServlet" method="post">
				<input type="submit" id="button" value="Neues R4F Konto erstellen" />
			</form>
		</center>
	</div>
</c:if>
<c:if test ="${not empty orderList.order}">
	<div id="container">	
		<h3 class="impressum">Meine Bestellungen</h3>
 			<div class="bestellungen">
 			<div class="bestellungDetails"> 
 				<div><p>Bestellung aufgegeben am <br/><fmt:formatDate pattern = "dd.MM.yyyy" value = "${order.entryDate}" /></p></div>
 				<div><p>Summe <br/><fmt:formatDate pattern = "dd.MM.yyyy" value = "${order.entryDate}" /> <br/> <b><jsp:getProperty property="totalPrice" name="shoppingBasket"/>&euro;</b></p></div>
 				<div><p>Bestellnummer <br/>${order.id}</p></div>
 			</div>
		<c:forEach items="${order.items}" var="item">
				<div class="artikelBestellungen">
						<div class="bild"><img class="ImgWarenkorb" src="./ImageServlet/${item.article.mainImage}" alt="Bild"></div> 
						<div class="daten">
							<p><b>${item.article.name}</b></p>
							<p><b>Preis: </b><b>${item.article.price}&euro;</b></p>
						</div> 
							<form action="./AddToShoppingBasketServlet" method="post">
								<p><input type="submit" id="button" value="Nochmal bestellen" /></p>
							</form>
				</div> 
</c:forEach> 
			</div>
 				<div id="weiterEinkaufen">
 					<form action="./NavigationOverwiewServlet" method="post">
					<p><input type="submit" id="button" value="Neue Schuhe bestellen" /></p>
					</form>
				</div>
			</div>
</c:if> 
</body>
</html>