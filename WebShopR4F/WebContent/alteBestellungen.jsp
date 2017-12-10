<%@ include file="Head.jspf" %>
<title>Meine Bestellungen</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="orderList" type="java.util.ArrayList<r4f.model.Article>" class="java.util.ArrayList" scope="session">
	</jsp:useBean>
	<jsp:useBean id="shoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean>

<c:if test ="${empty orderList}">
	<div id="container">
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
		</center>
	</div>
</c:if>
<c:if test ="${not empty orderList}">
	<div id="container">	
		<h3 class="impressum">Meine Bestellungen</h3>
		<c:forEach items="${orderList}" var= "order">

 			<div class="bestellung">
 			 	<hr class="black" />
 				<div class="bestellungDetails"> 
 					<div class="bestelldatum"><p>Bestellung aufgegeben am: <fmt:formatDate pattern = "dd.MM.yyyy" value = "${order.entryDate}" /></p></div>
 					<div class="bestellsumme"><p>Summe: <b>${order.totalPrice}&euro;</b></p></div>
 					<div class="bestellid"><p>Bestell ID: ${order.id}</p></div>
 				</div>
				<c:forEach items="${order.items}" var="item">
					<div class="artikelBestellungen">
						<div class="bild"><img class="ImgAlteBestellungen" src="./ImageServlet/${item.article.mainImage}" alt="Bild"></div> 
						<div class="daten">
							<p><b>${item.article.name}</b></p>
							<p><b>Preis: </b><b>${item.article.price}&euro;</b></p>
						</div> 
					</div> 
				</c:forEach> 
			
			<hr class="black" />
			</div>
		</c:forEach>
 			<div id="weiterEinkaufen">

			<form action="./NavigationOverviewServlet" method="post">
				<input type="submit" id="button" value="Neue Schuhe bestellen" />
			</form>

				</div>
			</div>
</c:if> 
</body>
</html>