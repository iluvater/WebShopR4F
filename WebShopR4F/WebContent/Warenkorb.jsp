<%@ include file="Head.jspf" %>
<title>Warenkorb</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="shoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean>
	<jsp:useBean id="size" class="java.lang.String" scope="session" >
	</jsp:useBean>
	<jsp:useBean id="color" class="java.lang.String" scope="session">
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
		<h3 class="ueberschrift">Warenkorb</h3>
 			<c:forEach items="${shoppingBasket.items}" var="item">
				<div class="artikel">
						<div class="bild"><img class="ImgWarenkorb" src="./ImageServlet/${item.article.mainImage}" alt="Bild"></div> 
						<div class="daten">
							<p><b>${item.article.name}</b></p>
							<form action="./ChangeAmountShoppingBasketServlet" method="post">
								<input type="hidden" name="articleId" value="${item.article.id }" />
								<input type="hidden" name="size" value="${item.size }" />
								<input type="hidden" name="color" value="${item.color }" />	
								<input type="hidden" name="amount" value="${item.amount }" />
 								<c:if test="${ size eq item.size}">
									<p><b>Gr&ouml;&szlig;e: </b>
									<select id="size" name="sizeNew" onChange="submit();">
									<c:forEach items="${item.article.size}" var="articleSize" begin="0" end="0">
										<option selected value="${articleSize}">${articleSize}</option>
									</c:forEach>
									<c:forEach items="${item.article.size}" var="articleSize" begin="1">
										<option value="${articleSize}">${articleSize}</option>
									</c:forEach>
									</select>
									</p>
								</c:if> 
								<c:if test="${size ne item.size }">
									<p><b>Gr&ouml;&szlig;e: </b>
									<select id="size" name="sizeNew" onChange="submit();">
									<c:forEach items="${item.article.size}" var="articleSize">
										<c:if test="${articleSize eq item.size}">
											<option selected value="${articleSize}">${articleSize}</option>
										</c:if>
										<c:if test="${articleSize ne item.size}">
											<option value="${articleSize}">${articleSize}</option>
										</c:if>
									</c:forEach>
									</select>
									</p>
								</c:if>
					 			<c:if test="${color eq item.color }">
									<p><b>Farbe: </b>
									<select id="color" name="colorNew" onChange="submit();">
										<c:forEach items="${item.article.color}" var="articleColor" begin="0" end="0">
											<option selected value="${articleColor}">${articleColor}</option>
										</c:forEach>
										<c:forEach items="${item.article.color}" var="articleColor" begin="1">
											<option value="${articleColor}">${articleColor}</option>
										</c:forEach>
									</select>
									</p>
								</c:if> 
								<c:if test="${color ne item.color}">
									<p><b>Farbe: </b>
									<select id="color" name="colorNew" onChange="submit();">
									<c:forEach items="${item.article.color}" var="articleColor">
										<c:if test="${articleColor eq item.color}">
											<option selected value="${articleColor}">${articleColor}</option>
										</c:if>
										<c:if test="${articleColor ne item.color}">
											<option value="${articleColor}">${articleColor}</option>
										</c:if>
									</c:forEach>
									</select>
									</p>
								</c:if>
							</form>											
							<p><b>Preis: </b><b>${item.article.price}&euro;</b></p>
						</div> 
						<div class="menge">					
							<hr />
							<form action="./ChangeAmountShoppingBasketServlet" method="post">
								<input type="hidden" name="articleId" value="${item.article.id }" />
								<input type="hidden" name="size" value="${item.size }" />
								<input type="hidden" name="color" value="${item.color }" />	
								<input type="hidden" name="sizeNew" value="${item.size }" />
								<input type="hidden" name="colorNew" value="${item.color }" />					
								<!-- <p><label for="amount">Menge:</label> -->
								<p><b>Menge:</b>
								<select name="amount"  onChange="submit();">
 								<c:if test="${item.amount == '1'}">
 									<option selected value="1">1</option>
 			 						<option value="2">2</option>
 									<option value="3">3</option>
 									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
					 			</c:if>
					 			<c:if test="${item.amount == '2'}">
					 				<option value="1">1</option>
					 			 	<option selected value="2">2</option>
					 				<option value="3">3</option>
					 				<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
					 			</c:if>
					 			<c:if test="${item.amount == '3'}">
					 				<option value="1">1</option>
					 			 	<option value="2">2</option>
					 				<option selected value="3">3</option>
					 				<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
					 			</c:if>
					 			<c:if test="${item.amount == '4'}">
					 				<option value="1">1</option>
					 			 	<option value="2">2</option>
					 				<option value="3">3</option>
					 				<option selected value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
					 			</c:if>
					 			<c:if test="${item.amount == '5'}">
					 				<option value="1">1</option>
					 			 	<option value="2">2</option>
					 				<option value="3">3</option>
					 				<option value="4">4</option>
									<option selected value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
					 			</c:if>
					 			<c:if test="${item.amount == '6'}">
					 				<option value="1">1</option>
					 			 	<option value="2">2</option>
					 				<option value="3">3</option>
					 				<option value="4">4</option>
									<option value="5">5</option>
									<option selected value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
					 			</c:if>
					 			<c:if test="${item.amount == '7'}">
					 				<option value="1">1</option>
					 			 	<option value="2">2</option>
					 				<option value="3">3</option>
					 				<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option selected value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
					 			</c:if>
					 			<c:if test="${item.amount == '8'}">
					 				<option value="1">1</option>
					 			 	<option value="2">2</option>
					 				<option value="3">3</option>
					 				<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option selected value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
					 			</c:if>
					 			<c:if test="${item.amount == '9'}">
					 				<option value="1">1</option>
					 			 	<option value="2">2</option>
					 				<option value="3">3</option>
					 				<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option selected value="9">9</option>
									<option value="10">10</option>
					 			</c:if>
					 			<c:if test="${item.amount == '10'}">
					 				<option value="1">1</option>
					 			 	<option value="2">2</option>
					 				<option value="3">3</option>
					 				<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option selected value="10">10</option>
					 			</c:if>
								</select>	
								</p>							
							</form>
							<p />
							<form action="./RemoveArticleFromShoppingBasketServlet" method="post">
								<input type="hidden" name="articleId" value="${item.article.id }" />
								<input type="hidden" name="size" value="${item.size }" />
								<input type="hidden" name="color" value="${item.color }" />
								<p><input type="submit" id="buttonEntfernen" value="entfernen" /></p>
							</form>
						</div> 
					</div>
				</c:forEach>
 				<div id="weiterEinkaufen">
 					<form action="NavigationOverviewServlet">
						<p><input type="submit" id="button" value="Weitere Artikel ansehen" /></p>
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