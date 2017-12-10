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
						<form action="./ChangeSizeColorWishlistServlet" method="post">
						<input type="hidden" name="articleId" value="${article.id }" />
						<input type="hidden" name="size" value="${item.size }" />
						<input type="hidden" name="color" value="${item.color }" />	
 						<c:if test="${empty size }">
							<p><b>Gr&ouml;&szlig;e: </b>
							<select id="size" name="size" onChange="submit();">
								<c:forEach items="${item.article.size}" var="articleSize" begin="0" end="0">
									<option selected value="${articleSize}">${articleSize}</option>
								</c:forEach>
								<c:forEach items="${item.article.size}" var="articleSize" begin="1">
									<option value="${articleSize}">${articleSize}</option>
								</c:forEach>
							</select>
							</p>
						</c:if> 
						<c:if test="${not empty size }">
							<p><b>Gr&ouml;&szlig;e: </b>
							<select id="size" name="size" onChange="submit();">
								<c:forEach items="${item.article.size}" var="articleSize">
									<c:if test="${articleSize eq size}">
										<option selected value="${articleSize}">${articleSize}</option>
									</c:if>
									<c:if test="${articleSize ne size}">
										<option value="${articleSize}">${articleSize}</option>
									</c:if>
								</c:forEach>
							</select>
							</p>
						</c:if>
					 	<c:if test="${empty color }">
							<p><b>Farbe: </b>
							<select id="color" name="color" onChange="submit();">
								<c:forEach items="${item.article.color}" var="articleColor" begin="0" end="0">
									<option selected value="${articleColor}">${articleColor}</option>
								</c:forEach>
								<c:forEach items="${item.article.color}" var="articleColor" begin="1">
									<option value="${articleColor}">${articleColor}</option>
								</c:forEach>
							</select>
							</p>
						</c:if> 
						<c:if test="${not empty color }">
							<p><b>Farbe: </b>
							<select id="color" name="color" onChange="submit();">
								<c:forEach items="${item.article.color}" var="articleColor">
									<c:if test="${articleColor eq color}">
										<option selected value="${articleColor}">${articleColor}</option>
									</c:if>
									<c:if test="${articleColor ne color}">
										<option value="${articleColor}">${articleColor}</option>
									</c:if>
								</c:forEach>
							</select>
							</p>
						</c:if>
					</form>				
<%-- 						<p><b>Farbe: </b>${item.color}</p>
						<p><b>Gr&ouml;&szlig;e: </b>${item.size}</p> --%>
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
			<hr/>
 			<div id="weiterEinkaufen">
 			 	<div class="linkerButtonMerkliste">
 			 		<form action="NavigationOverviewServlet">
						<input type="submit" id="button" value="Weitere Artikel ansehen" />
					</form>
				</div>
				<div class="rechterButtonMerkliste">
					<form action="./WishlistToShoppingBasketServlet" method="post">
						<input type="submit" id="button" value="Alle Artikel in den Warenkorb" />
					</form>
				</div>
			</div>
			
 			<!-- 	<div id="weiterEinkaufen">
 				<form action="./WishlistToShoppingBasketServlet" method="post">
					<p><input type="submit" id="button" value="In den Warenkorb" /></p>
				</form>
				</div> -->
			</c:if>
		</div>
	
<%@ include file="Footer.jspf" %>