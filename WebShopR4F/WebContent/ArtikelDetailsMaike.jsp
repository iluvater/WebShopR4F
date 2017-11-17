<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artikeldetailseite</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body>
	<jsp:useBean id="article" class="r4f.model.Article" scope="request">
	</jsp:useBean> 
		<div id="container">
		<c:if test ="${not empty article.name}">
		<div class="inhalt">
			<ul>
				<li> <img src="./ImageServlet/${article.image}"></li>
				<li><h4><jsp:getProperty property="name" name="article"/></h4> <br/>
					<p><jsp:getProperty property="price" name="article"/></p> <br/>
					<input type="submit" id="button" value="Auf den Merkzettel" /> <br/>
					<input type="submit" id="button" value="In den Warenkorb" />
				</li>
			</ul>
		</div>
		<div class="inhalt">
			<h4>Produktinformationen</h4>
			<br/>
			
			<p><label for="size">Größe:</label>
			<p><jsp:getProperty property="size" name="article"/></p>
			<br/>
			<p><label for="color">Farbe:</label>
			<p><jsp:getProperty property="color" name="article"/></p>
			<br/>
			<p><label for="manufacturer">Hersteller:</label>
			<p><jsp:getProperty property="manufacturer" name="article"/></p>	
			<br/>
			<p><label for="sport">Sportart:</label>
			<p><jsp:getProperty property="sport" name="article"/></p>
			<br/>
			<p><label for="category">Kategorie:</label>
			<p><jsp:getProperty property="category" name="article"/></p>
		</div>
		<div>
			<h4>Beschreibung:</h4>
			<p><label for="description">Beschreibung:</label>
			<p><jsp:getProperty property="description" name="article"/></p>
		</div>
	</div>
	</c:if>
	<c:if test ="${empty article.name}">
		<div class="inhalt">			
			<p>Leider ist der Artikel zur Zeit nicht vorhanden.
			<br />Versuche es in ein paar Tagen nochmal, wenn der Bestand wieder aufgefüllt ist.
			</p>
		</div>
	</c:if>
	</div>
</body>
</html>