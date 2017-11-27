<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://run4fun.de/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Übersichtsseite</title>
<link href="DesignV1.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:useBean id="articleList" type="java.util.ArrayList<r4f.model.Article>" class="java.util.ArrayList" scope="request">
	</jsp:useBean> 
	<jsp:useBean id="filter" class="r4f.controller.filter.CheckboxStatus" scope="session">
	</jsp:useBean> 

	<div id="container">
		<div id="containerFilter"> 
		<form action="./NavigationOverviewFilterServlet" method="post">
			<p><strong>Sportart</strong></p>
			<input type="checkbox" id="paddingLeft" name="sport" value="1" onChange="submit();" ${filter.sport1}>checked> Laufen<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="2" onChange="submit();" ${filter.sport2}>checked> Fußball<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="3" onChange="submit();" ${filter.sport3}>checked> Golf<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="4" onChange="submit();" ${filter.sport4}>checked> Basketball<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="5" onChange="submit();" ${filter.sport5}>checked> Fahrrad<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="6" onChange="submit();" ${filter.sport6}>checked> Schwimmen<br>
			<br />
		
			<p><strong>Hersteller/Marke</strong></p>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="1" onChange="submit();" ${filter.manufacturer1}>checked> Adidas<br />
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="2" onChange="submit();" ${filter.manufacturer2}>checked> Asics<br />
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="3" onChange="submit();" ${filter.manufacturer3}>checked> Hummel<br />
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="4" onChange="submit();" ${filter.manufacturer4}>checked> Kempa<br />
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="5" onChange="submit();" ${filter.manufacturer5}>checked> Nike<br />
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="6" onChange="submit();" ${filter.manufacturer6}>checked> Puma<br />
			<br />
		
			<p><strong>Farbe</strong></p>
			<input type="checkbox" id="paddingLeft" name="color" value="1" onChange="submit();" ${filter.color1}>checked> Gelb<br />
			<input type="checkbox" id="paddingLeft" name="color" value="2" onChange="submit();" ${filter.color2}>checked> Orange<br />
			<input type="checkbox" id="paddingLeft" name="color" value="3" onChange="submit();" ${filter.color3}>checked> Rot<br />
			<input type="checkbox" id="paddingLeft" name="color" value="4" onChange="submit();" ${filter.color4}>checked> Pink<br />
			<input type="checkbox" id="paddingLeft" name="color" value="5" onChange="submit();" ${filter.color5}>checked> Grün<br />
			<input type="checkbox" id="paddingLeft" name="color" value="6" onChange="submit();" ${filter.color6}>checked> Blau<br />
			<input type="checkbox" id="paddingLeft" name="color" value="7" onChange="submit();" ${filter.color7}>checked> Schwarz<br />
			<input type="checkbox" id="paddingLeft" name="color" value="8" onChange="submit();" ${filter.color8}>checked> Weiß<br />
			<br />
		
			<p><strong>Preis</strong></p>
			<input type="checkbox" id="paddingLeft" name="price" value="1" onChange="submit();" ${filter.price1}>checked> bis 50 &euro;<br />
			<input type="checkbox" id="paddingLeft" name="price" value="2" onChange="submit();" ${filter.price2}>checked> 50 &euro; bis 100 &euro;<br />
			<input type="checkbox" id="paddingLeft" name="price" value="3" onChange="submit();" ${filter.price3}>checked> 100 &euro; bis 150 &euro;<br />
			<input type="checkbox" id="paddingLeft" name="price" value="4" onChange="submit();" ${filter.price4}>checked> ab 150 &euro;<br />
			<br />
		
			<p><strong>Gr&ouml;&szlig;e</strong></p>
			<input type="checkbox" id="paddingLeft" name="size" value="1" onChange="submit();" ${filter.size1}>checked> 36<br />
			<input type="checkbox" id="paddingLeft" name="size" value="2" onChange="submit();" ${filter.size2}>checked> 37<br />
			<input type="checkbox" id="paddingLeft" name="size" value="3" onChange="submit();" ${filter.size3}>checked> 38<br />
			<input type="checkbox" id="paddingLeft" name="size" value="4" onChange="submit();" ${filter.size4}>checked> 39<br />
			<input type="checkbox" id="paddingLeft" name="size" value="5" onChange="submit();" ${filter.size5}>checked> 40<br />
			<input type="checkbox" id="paddingLeft" name="size" value="6" onChange="submit();" ${filter.size6}>checked> 41<br />
			<input type="checkbox" id="paddingLeft" name="size" value="7" onChange="submit();" ${filter.size7}>checked> 42<br />
			<input type="checkbox" id="paddingLeft" name="size" value="8" onChange="submit();" ${filter.size8}>checked> 43<br />
			<input type="checkbox" id="paddingLeft" name="size" value="9" onChange="submit();" ${filter.size9}>checked> 44<br />
			<input type="checkbox" id="paddingLeft" name="size" value="10" onChange="submit();" ${filter.size10}>checked> 45<br />
			<br />
			</form>
		</div>
		<div id="containerInhalt">
			<c:forEach items="${articleList}" var="article">
			<div id="containerArtikel">
			
				<form action="./NavigationArticleDetailsServlet" method="post">
        			<input type="hidden" name="articleId" value="${article.id }" />
					<input id="ImgArtikelUebersicht" type="image" src="./ImageServlet/${article.image}" alt="Bild">
				</form> 
				
				<div id="containerButtonUebersicht" >
				<button type="button" class="btn btn-default btn-sm">
          			<span class="glyphicon glyphicon-eye-open"></span>
       			</button>
				<button type="button" class="btn btn-default btn-sm">
          			<span class="glyphicon glyphicon-heart-empty"></span>
       			</button>
       			<button type="button" class="btn btn-default btn-sm btnPadding">
          			<span class="glyphicon glyphicon-shopping-cart"></span>
        		</button> 
				</div>
				
				<form action="./NavigationArticleDetailsServlet" method="post">
					<input type="hidden" name="articleId" value="${article.id }" />
					<button type="submit" class="linkButton"><strong>Name:</strong> ${article.name}</button>
				</form>
				<form action="./NavigationArticleDetailsServlet" method="post">
					<input type="hidden" name="articleId" value="${article.id }" />
					<button type="submit" class="linkButton"><strong>Gr&ouml;&szlig;e:</strong> ${article.size}</button>
				</form>
				<form action="./NavigationArticleDetailsServlet" method="post">
					<input type="hidden" name="articleId" value="${article.id }" />
					<button type="submit" class="linkButton"><strong>Hersteller: </strong>${article.manufacturer}</button>
				</form>
				<form action="./NavigationArticleDetailsServlet" method="post">
					<input type="hidden" name="articleId" value="${article.id }" />
					<button type="submit" class="linkButton"><strong>Sportart: </strong>${article.sport}</button>
				</form>
				
			</div> 
			</c:forEach> 
		</div>
		<div class="clearLeft"></div>
	</div>
	

</body>
</html>