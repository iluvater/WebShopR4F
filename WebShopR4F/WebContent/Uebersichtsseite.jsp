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
			<input type="checkbox" id="paddingLeft" name="sport" value="1" onChange="submit();" <c:if test={filter.sport1}>checked</c:if>> Laufen<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="2" onChange="submit();" <c:if test={filter.sport2}>checked</c:if>> Fußball<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="3" onChange="submit();" <c:if test={filter.sport3}>checked</c:if>> Golf<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="4" onChange="submit();" <c:if test={filter.sport4}>checked</c:if>> Basketball<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="5" onChange="submit();" <c:if test={filter.sport5}>checked</c:if>> Fahrrad<br>
			<input type="checkbox" id="paddingLeft" name="sport" value="6" onChange="submit();" <c:if test={filter.sport6}>checked</c:if>> Schwimmen<br>
			<br />
		
			<p><strong>Hersteller/Marke</strong></p>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="1" onChange="submit();" <c:if test={filter.manufacturer1}>checked</c:if>> Adidas<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="2" onChange="submit();" <c:if test={filter.manufacturer2}>checked</c:if>> Asics<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="3" onChange="submit();" <c:if test={filter.manufacturer3}>checked</c:if>> Hummel<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="4" onChange="submit();" <c:if test={filter.manufacturer4}>checked</c:if>> Kempa<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="5" onChange="submit();" <c:if test={filter.manufacturer5}>checked</c:if>> Nike<br>
			<input type="checkbox" id="paddingLeft" name="manufacturer" value="6" onChange="submit();" <c:if test={filter.manufacturer6}>checked</c:if>> Puma<br>
			<br />
		
			<p><strong>Farbe</strong></p>
			<input type="checkbox" id="paddingLeft" name="color" value="1" onChange="submit();" <c:if test={filter.color1}>checked</c:if>> Gelb<br>
			<input type="checkbox" id="paddingLeft" name="color" value="2" onChange="submit();" <c:if test={filter.color2}>checked</c:if>> Orange<br>
			<input type="checkbox" id="paddingLeft" name="color" value="3" onChange="submit();" <c:if test={filter.color3}>checked</c:if>> Rot<br>
			<input type="checkbox" id="paddingLeft" name="color" value="4" onChange="submit();" <c:if test={filter.color4}>checked</c:if>> Pink<br>
			<input type="checkbox" id="paddingLeft" name="color" value="5" onChange="submit();" <c:if test={filter.color5}>checked</c:if>> Grün<br>
			<input type="checkbox" id="paddingLeft" name="color" value="6" onChange="submit();" <c:if test={filter.color6}>checked</c:if>> Blau<br>
			<input type="checkbox" id="paddingLeft" name="color" value="7" onChange="submit();" <c:if test={filter.color7}>checked</c:if>> Schwarz<br>
			<input type="checkbox" id="paddingLeft" name="color" value="8" onChange="submit();" <c:if test={filter.color8}>checked</c:if>> Weiß<br>
			<br />
		
			<p><strong>Preis</strong></p>
			<input type="checkbox" id="paddingLeft" name="price" value="1" onChange="submit();" <c:if test={filter.price1}>checked</c:if>> bis 50 &euro;<br>
			<input type="checkbox" id="paddingLeft" name="price" value="2" onChange="submit();" <c:if test={filter.price2}>checked</c:if>> 50 &euro; bis 100 &euro;<br>
			<input type="checkbox" id="paddingLeft" name="price" value="3" onChange="submit();" <c:if test={filter.price3}>checked</c:if>> 100 &euro; bis 150 &euro;<br>
			<input type="checkbox" id="paddingLeft" name="price" value="4" onChange="submit();" <c:if test={filter.price4}>checked</c:if>> ab 150 &euro;<br>
			<br />
		
			<p><strong>Gr&ouml;&szlig;e</strong></p>
			<input type="checkbox" id="paddingLeft" name="size" value="1" onChange="submit();" <c:if test={filter.size1}>checked</c:if>> 36<br>
			<input type="checkbox" id="paddingLeft" name="size" value="2" onChange="submit();" <c:if test={filter.size2}>checked</c:if>> 37<br>
			<input type="checkbox" id="paddingLeft" name="size" value="3" onChange="submit();" <c:if test={filter.size3}>checked</c:if>> 38<br>
			<input type="checkbox" id="paddingLeft" name="size" value="4" onChange="submit();" <c:if test={filter.size4}>checked</c:if>> 39<br>
			<input type="checkbox" id="paddingLeft" name="size" value="5" onChange="submit();" <c:if test={filter.size5}>checked</c:if>> 40<br>
			<input type="checkbox" id="paddingLeft" name="size" value="6" onChange="submit();" <c:if test={filter.size6}>checked</c:if>> 41<br>
			<input type="checkbox" id="paddingLeft" name="size" value="7" onChange="submit();" <c:if test={filter.size7}>checked</c:if>> 42<br>
			<input type="checkbox" id="paddingLeft" name="size" value="8" onChange="submit();" <c:if test={filter.size8}>checked</c:if>> 43<br>
			<input type="checkbox" id="paddingLeft" name="size" value="9" onChange="submit();" <c:if test={filter.size9}>checked</c:if>> 44<br>
			<input type="checkbox" id="paddingLeft" name="size" value="10" onChange="submit();" <c:if test={filter.size10}>checked</c:if>> 45<br>
			<br />
			<center>
				<input type="submit" id="button" value="Filter anwenden" />
			</center>
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