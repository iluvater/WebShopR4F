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

	<div id="container">
		<div id="containerFilter"> 
			<p><strong>Sportart</strong></p>
			<input type="checkbox" id="paddingLeft" name="sport" value="1"> Laufen<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="2"> Fußball<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="3"> Golf<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="4"> Basketball<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="5"> Fahrrad<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="6"> Schwimmen<br>
			<br />
		
			<p><strong>Hersteller/Marke</strong></p>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="1"> Adidas<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="2"> Asics<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="3"> Hummel<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="4"> Kempa<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="5"> Nike<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="6"> Puma<br>
			<br />
		
			<p><strong>Farbe</strong></p>
			<input type="checkbox" id="paddingLeft" name="color" value="1"> Gelb<br>
			<input type="checkbox" id="paddingLeft" name="color" value="2"> Orange<br>
			<input type="checkbox" id="paddingLeft" name="color" value="3"> Rot<br>
			<input type="checkbox" id="paddingLeft" name="color" value="4"> Pink<br>
			<input type="checkbox" id="paddingLeft" name="color" value="5"> Grün<br>
			<input type="checkbox" id="paddingLeft" name="color" value="6"> Blau<br>
			<input type="checkbox" id="paddingLeft" name="color" value="7"> Schwarz<br>
			<input type="checkbox" id="paddingLeft" name="color" value="8"> Weiß<br>
			<br />
		
			<p><strong>Preis</strong></p>
			<input type="checkbox" id="paddingLeft" name="price" value="1"> bis 50 &euro;<br>
			<input type="checkbox" id="paddingLeft" name="price" value="2"> 50 &euro; bis 100 &euro;<br>
			<input type="checkbox" id="paddingLeft" name="price" value="3"> 100 &euro; bis 150 &euro;<br>
			<input type="checkbox" id="paddingLeft" name="price" value="4"> ab 150 &euro;<br>
			<br />
		
			<p><strong>Gr&ouml;&szlig;e</strong></p>
			<input type="checkbox" id="paddingLeft" name="size" value="1"> 36<br>
			<input type="checkbox" id="paddingLeft" name="size" value="2"> 37<br>
			<input type="checkbox" id="paddingLeft" name="size" value="3"> 38<br>
			<input type="checkbox" id="paddingLeft" name="size" value="4"> 39<br>
			<input type="checkbox" id="paddingLeft" name="size" value="5"> 40<br>
			<input type="checkbox" id="paddingLeft" name="size" value="6"> 41<br>
			<input type="checkbox" id="paddingLeft" name="size" value="7"> 42<br>
			<input type="checkbox" id="paddingLeft" name="size" value="8"> 43<br>
			<input type="checkbox" id="paddingLeft" name="size" value="9"> 44<br>
			<input type="checkbox" id="paddingLeft" name="size" value="10"> 45<br>
			<br />
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