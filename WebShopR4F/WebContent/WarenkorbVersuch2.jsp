<%@ include file="Head.jspf" %>
<title>Warenkorb</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="shoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean>
	
<c:if test ="${empty shoppingBasket.items}">
<div id="container">
	<h3 class="impressum">Warenkorb</h3>
	<div class="warenkorbLeer">
		<p class="impressum">Dein Warenkorb ist noch leer. 
		<br/>Legen Sie los und f&uuml;llen Sie ihn mit Ihren neuen Lieblingsschuhen!</p>
	</div>
	<hr />
	<center>
		<form action="./NavigationOverviewServlet" method="post">
			<input type="submit" id="button" value="Zu deinen Lieblingschuhen" />
		</form>
	</center>
</div>
</c:if>
<c:if test ="${not empty shoppingBasket.items}">
	<div id="container">	
		<h3 class="impressum">Warenkorb</h3>
 			<c:forEach items="${shoppingBasket.items}" var="item">
				<div class="artikel">
						<div class="bild"><img class="ImgWarenkorb" src="./ImageServlet/${item.article.mainImage}" alt="Bild"></div> 
						<div class="daten">
							<p><b>${item.article.name}</b></p>
							<p><b>Farbe: </b>${item.color}</p>
							<p><b>Gr��e: </b>${item.size}</p>
							<p><b>Preis: </b><b>${item.article.price}&euro;</b></p>
						</div> 
						<div class="menge">					
							<p><b>Menge</b>
							<form action="./ChangeAmountShoppingBasketServlet" method="post">
								<input type="hidden" name="articleId" value="${item.article.id }" />
								<input type="hidden" name="size" value="${item.size }" />
								<input type="hidden" name="color" value="${item.color }" />
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
							</p>
							<form action="./RemoveArticleFromShoppingBasket" method="post">
								<input type="hidden" name="articleId" value="${item.article.id }" />
								<input type="hidden" name="size" value="${item.size }" />
								<input type="hidden" name="color" value="${item.color }" />
								<p><input type="submit" id="buttonEntfernen" value="entfernen" /></p>
								<!-- <p><a href="WarenkorbVersuch2.jsp" >entfernen</a></p> -->
							</form>
						</div> 
					</div>
				</c:forEach>
 				<div id="weiterEinkaufen">
 					<form action="NavigationOverviewServlet">
						<p><input type="submit" id="button" value="Weiter Einkaufen" /></p>
					</form>
				</div>
				
				
				
				<div class="gesamtpreis"> 
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
					</div>
 					<div id="weiterEinkaufen">
 					<form action="./OrderCheckoutServlet" method="post">
						<p><input type="submit" id="button" value="Zur Kasse" /></p>
					</form>
					</div>
				</div>
			</div>
	</c:if>
<%@ include file="Footer.jspf" %>