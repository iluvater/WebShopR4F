<!-- * 
 * @author Ture
 * @edited by Laura, Maike
 *
 * -->

<%@ include file="Head.jspf" %>
<title>Artikeldetails</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="article" class="r4f.model.Article" scope="request">
	</jsp:useBean> 
	<div id="container">
			<c:if test ="${not empty article.name}">
				<div>
					<img id="ImgArtikelDetails" src="./ImageServlet/${article.mainImage }" alt="Bild">
					<h3 class="ueberschrift"><jsp:getProperty property="name" name="article"/></h3> 
					<h4 class="artikelDetails">Preis: <jsp:getProperty property="price" name="article"/> &euro; </h4>
					<p class="deliveryDate">Lieferbar bis sp&auml;testens: <fmt:formatDate pattern = "dd.MM.yyyy" value = "${article.deliveryDate}" /></p>
					
					<%-- <jsp:getProperty property="deliveryDate" name="article"/></p> --%>
					<hr />
					
					<form action="./AddToWishlistServlet" method="post">
						<input type="hidden" name="articleId" value="${article.id }" />
						<button id="button" class="btn btn-default btn-sm">
          					<span class="glyphicon glyphicon-heart-empty"></span> Merkliste
        				</button>
					</form>
					<br />
        			<form action="./AddToShoppingBasketServlet" method="post">
        				<input type="hidden" name="articleId" value="${article.id }" />
        			<button id="button" class="btn btn-default btn-sm">
          				<span class="glyphicon glyphicon-shopping-cart"></span> Warenkorb
        			</button>
        			</form>
				</div>
				<div class="containerArtikelDetails">
					<h4 class="artikelDetails">Produktioninformationen</h4>
					<p class="artikelDetails"><b>Artikel-ID: </b> <jsp:getProperty property="id" name="article"/> <br /></p>
					<form action="./ChangeSizeColorServlet" method="post">
						<input type="hidden" name="articleId" value="${article.id }" />
 						<c:if test="${empty size }">
							<p class="artikelDetails"><b>Gr&ouml;&szlig;e: </b>
							<select id="size" name="size" onChange="submit();">
								<c:forEach items="${article.size}" var="articleSize" begin="0" end="0">
									<option selected value="${articleSize}">${articleSize}</option>
								</c:forEach>
								<c:forEach items="${article.size}" var="articleSize" begin="1">
									<option value="${articleSize}">${articleSize}</option>
								</c:forEach>
							</select>
							</p>
						</c:if> 
						<c:if test="${not empty size }">
							<p class="artikelDetails"><b>Gr&ouml;&szlig;e: </b>
							<select id="size" name="size" onChange="submit();">
								<c:forEach items="${article.size}" var="articleSize">
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
							<p class="artikelDetails"><b>Farbe: </b>
							<select id="color" name="color" onChange="submit();">
								<c:forEach items="${article.color}" var="articleColor" begin="0" end="0">
									<option selected value="${articleColor}">${articleColor}</option>
								</c:forEach>
								<c:forEach items="${article.color}" var="articleColor" begin="1">
									<option value="${articleColor}">${articleColor}</option>
								</c:forEach>
							</select>
							</p>
						</c:if> 
						<c:if test="${not empty color }">
							<p class="artikelDetails"><b>Farbe: </b>
							<select id="color" name="color" onChange="submit();">
								<c:forEach items="${article.color}" var="articleColor">
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

					<p class="artikelDetails"><b>Hersteller: </b><jsp:getProperty property="manufacturer" name="article"/><br /></p>
					<p class="artikelDetails"><b>Sportart: </b><jsp:getProperty property="sport" name="article"/><br /> </p>
					<p class="artikelDetails"><b>Kategorie: </b><jsp:getProperty property="category" name="article"/><br /></p>				
				</div>
				<div>
					<h4 class="artikelDetails">Beschreibung</h4>
					<p class="artikelDetails">
						<jsp:getProperty property="description" name="article"/>	
					</p>				
				</div>
			</c:if>
			<c:if test ="${empty article.name}">
				<p class="fehler">
					Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut.
				</p>
			</c:if>
	</div>
<%@ include file="Footer.jspf" %>