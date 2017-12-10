<%@ include file="Head.jspf" %>
<title>Merkliste</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="wishlist" class="r4f.model.Wishlist" scope="session">
	</jsp:useBean>

	<div id="container">	
	<c:if test ="${empty wishlist.list}">
		<h3 class="ueberschrift">Merkliste</h3>
		<div class="warenkorbLeer">
			<p class="impressum">Ihre Merkliste ist noch leer. <br/>Legen Sie los und f&uuml;llen Sie sie mit Ihren neuen Lieblingsschuhen!</p>
		</div>
		<hr />
		<center>
			<form action="./NavigationOverviewServlet" method="post">
				<input type="submit" id="button" value="Zu deinen Lieblingschuhen" />
			</form>
		</center>
	</c:if>
	<c:if test ="${not empty wishlist.list}">	
		<h3 class="ueberschrift">Merkliste</h3>
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
						<form action="./RemoveArticleFromWishlistServlet" method="post">
							<input type="hidden" name="articleId" value="${item.article.id }" />
							<input type="hidden" name="size" value="${item.size }" />
							<input type="hidden" name="color" value="${item.color }" />
							<p><input type="submit" id="buttonEntfernen" value="entfernen" /></p>
						</form>
					</div> 
				</div>
			</c:forEach>
			
			<div class="gesamtpreis">
 			<div id="weiterEinkaufen">
 			 		<form action="NavigationOverviewServlet">
						<p><input type="submit" id="button" value="Weiter Einkaufen" /></p>
					</form>
				<!-- <p><input type="submit" id="button" value="Weiter Einkaufen" /></p> -->
			</div>
 				<div id="weiterEinkaufen">
 				<form action="./WishlistToShoppingBasketServlet" method="post">
					<p><input type="submit" id="button" value="In den Warenkorb" /></p>
				</form>
				</div>
				</div>
			<!-- </div> -->
			</c:if>
		</div>
	
<%@ include file="Footer.jspf" %>