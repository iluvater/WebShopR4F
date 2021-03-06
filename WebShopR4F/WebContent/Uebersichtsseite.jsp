<!-- * 
 * @author Laura
 *
 * -->
 
<%@ include file="Head.jspf" %>
<title>‹bersichtsseite</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="articleList" type="java.util.ArrayList<r4f.model.Article>" class="java.util.ArrayList" scope="request">
	</jsp:useBean> 
	<jsp:useBean id="filter" class="r4f.controller.filter.CheckboxStatus" scope="session">
	</jsp:useBean> 

	<div id="container">
		<div id="containerFilter"> 
		<form action="./NavigationOverviewFilterServlet" method="post">
			<div class="floatLeft">
			<p class="paddingLeft1"><strong>Sportart</strong></p>
			<input type="checkbox" id="paddingLeft2" name="sport" value="1" onChange="submit();" ${filter.sport1}> Laufen<br>
			<input type="checkbox" id="paddingLeft2" name="sport" value="2" onChange="submit();" ${filter.sport2}> Fu&szlig;ball<br>
			<input type="checkbox" id="paddingLeft2" name="sport" value="3" onChange="submit();" ${filter.sport3}> Golf<br>
			<input type="checkbox" id="paddingLeft2" name="sport" value="4" onChange="submit();" ${filter.sport4}> Basketball<br>
			<input type="checkbox" id="paddingLeft2" name="sport" value="5" onChange="submit();" ${filter.sport5}> Fahrrad<br>
			<input type="checkbox" id="paddingLeft2" name="sport" value="6" onChange="submit();" ${filter.sport6}> Schwimmen<br>
			<br />
			</div>
		
			<div class="floatLeft">
			<p class="paddingLeft1"><strong>Hersteller/Marke</strong></p>
			<input type="checkbox" id="paddingLeft2" name="manufacturer" value="1" onChange="submit();" ${filter.manufacturer1}> Adidas<br />
			<input type="checkbox" id="paddingLeft2" name="manufacturer" value="2" onChange="submit();" ${filter.manufacturer2}> Asics<br />
			<input type="checkbox" id="paddingLeft2" name="manufacturer" value="3" onChange="submit();" ${filter.manufacturer3}> Hummel<br />
			<input type="checkbox" id="paddingLeft2" name="manufacturer" value="4" onChange="submit();" ${filter.manufacturer4}> Kempa<br />
			<input type="checkbox" id="paddingLeft2" name="manufacturer" value="5" onChange="submit();" ${filter.manufacturer5}> Nike<br />
			<input type="checkbox" id="paddingLeft2" name="manufacturer" value="6" onChange="submit();" ${filter.manufacturer6}> Puma<br />
			<br />
			</div>
			
			<div class="floatLeft">
			<p class="paddingLeft1"><strong>Farbe</strong></p>
			<input type="checkbox" id="paddingLeft2" name="color" value="1" onChange="submit();" ${filter.color1}> Gelb<br />
			<input type="checkbox" id="paddingLeft2" name="color" value="2" onChange="submit();" ${filter.color2}> Orange<br />
			<input type="checkbox" id="paddingLeft2" name="color" value="3" onChange="submit();" ${filter.color3}> Rot<br />
			<input type="checkbox" id="paddingLeft2" name="color" value="4" onChange="submit();" ${filter.color4}> Pink<br />
			<input type="checkbox" id="paddingLeft2" name="color" value="5" onChange="submit();" ${filter.color5}> Gr&uuml;n<br />
			<input type="checkbox" id="paddingLeft2" name="color" value="6" onChange="submit();" ${filter.color6}> Blau<br />
			<input type="checkbox" id="paddingLeft2" name="color" value="7" onChange="submit();" ${filter.color7}> Schwarz<br />
			<input type="checkbox" id="paddingLeft2" name="color" value="8" onChange="submit();" ${filter.color8}> Wei&szlig;<br />
			<br />
			</div>
		
			<div class="floatLeft">
			<p class="paddingLeft1"><strong>Preis</strong></p>
			<input type="checkbox" id="paddingLeft2" name="price" value="1" onChange="submit();" ${filter.price1}> bis 50 &euro;<br />
			<input type="checkbox" id="paddingLeft2" name="price" value="2" onChange="submit();" ${filter.price2}> 50 &euro; bis 100 &euro;<br />
			<input type="checkbox" id="paddingLeft2" name="price" value="3" onChange="submit();" ${filter.price3}> 100 &euro; bis 150 &euro;<br />
			<input type="checkbox" id="paddingLeft2" name="price" value="4" onChange="submit();" ${filter.price4}> ab 150 &euro;<br />
			<br />
			</div>
		
			<div class="floatLeft">
			<p class="paddingLeft1"><strong>Gr&ouml;&szlig;e</strong></p>
			<input type="checkbox" id="paddingLeft2" name="size" value="1" onChange="submit();" ${filter.size1}> 36<br />
			<input type="checkbox" id="paddingLeft2" name="size" value="2" onChange="submit();" ${filter.size2}> 37<br />
			<input type="checkbox" id="paddingLeft2" name="size" value="3" onChange="submit();" ${filter.size3}> 38<br />
			<input type="checkbox" id="paddingLeft2" name="size" value="4" onChange="submit();" ${filter.size4}> 39<br />
			<input type="checkbox" id="paddingLeft2" name="size" value="5" onChange="submit();" ${filter.size5}> 40<br />
			<input type="checkbox" id="paddingLeft2" name="size" value="6" onChange="submit();" ${filter.size6}> 41<br />
			<input type="checkbox" id="paddingLeft2" name="size" value="7" onChange="submit();" ${filter.size7}> 42<br />
			<input type="checkbox" id="paddingLeft2" name="size" value="8" onChange="submit();" ${filter.size8}> 43<br />
			<input type="checkbox" id="paddingLeft2" name="size" value="9" onChange="submit();" ${filter.size9}> 44<br />
			<input type="checkbox" id="paddingLeft2" name="size" value="10" onChange="submit();" ${filter.size10}> 45<br />
			<br />
			</div>
			
			</form>
		</div>
		<div id="containerInhalt">
			<c:forEach items="${articleList}" var="article">
			<div id="containerArtikel">
			
				<form action="./NavigationArticleDetailsServlet" method="post">
        			<input type="hidden" name="articleId" value="${article.id }" />
					<input id="ImgArtikelUebersicht" type="image" src="./ImageServlet/${article.mainImage}" alt="Bild">
				</form> 
				
				<form action="./NavigationArticleDetailsServlet" method="post">
					<input type="hidden" name="articleId" value="${article.id }" />
					<button type="submit" class="linkButton"><h4 class="center">${article.name}</h4></button>
				</form>
				<form action="./NavigationArticleDetailsServlet" method="post">
					<input type="hidden" name="articleId" value="${article.id }" />
					<button type="submit" class="linkButton"><h4 class="center">${article.price} &euro;</h4></button>
				</form>

				<div id="containerButtonUebersicht" >       			
<!--        				<a href="Merkliste.jsp" class="btn btn-default btn-sm">
      					<span class="glyphicon glyphicon-heart-empty"></span>
    				</a>   -->
<!--     				<a href="Warenkorb.jsp" class="btn btn-default btn-sm ">
     					<span class="glyphicon glyphicon-shopping-cart"></span>
    				</a>
    				 -->
    				<form class="header artikelUebersichtButton" action="./AddToWishlistServlet" method="post">
						<input type="hidden" name="articleId" value="${article.id }" />
						<button type="submit" class="btn btn-default btn-sm">
      						<span class="glyphicon glyphicon-heart-empty"></span>
    					</button>
					</form>
					<form  class="header artikelUebersichtButton" action="./AddToShoppingBasketServlet" method="post">
						<input type="hidden" name="articleId" value="${article.id }" />
						<button type="submit" class="btn btn-default btn-sm">
      						<span class="glyphicon glyphicon-shopping-cart"></span>
    					</button>
					</form>
    				
    				
				</div>
				<br />
			</div> 
			</c:forEach> 
		</div>
		<div class="clearLeft"></div>
	</div>
	
<%@ include file="Footer.jspf" %>