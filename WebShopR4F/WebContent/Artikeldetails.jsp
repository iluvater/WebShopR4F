<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artikeldetailseite</title>
<link href="Design.css" rel="stylesheet">
</head>
<body>
	<jsp:useBean id="article" class="r4f.model.Artikel" scope="request">
	</jsp:useBean> 
	<div id="seitenbereich">
		<div class="inhalt_farbe_div">
			<h4>Artikeldetails</h4>			
			<c:if test ="${not empty article.name}">
				<table>
					<tr>
						<td>
							<img src="./ImageServlet/${article.image}">
						</td>
					
						<td>
							<h5><jsp:getProperty property="name" name="article"/></h5>
							
							<hr />
							
							<p><jsp:getProperty property="price" name="article"/></p>
					
							<hr />
						
							<p><label for="color">Farbe:</label>
							<p><jsp:getProperty property="color" name="article"/></p>
							
							<hr />
								
							<p><label for="size">Größe:</label>
							<p><jsp:getProperty property="size" name="article"/></p>
				
							<hr />
							
							<p><label for="manufacturer">Hersteller:</label>
							<p><jsp:getProperty property="manufacturer" name="article"/></p>	
						</td>
					</tr>
					<tr>
						<td>
							<a href="./NavigationOverviewServlet"><input type="submit" id="button" value="Weiter shoppen" /></a>
							<input type="submit" id="button" value="In den Warenkorb" />
						</td>
						<td>
						
						</td>
					</tr>		
					<tr>
						<p><label for="description">Beschreibung:</label>
						<p><jsp:getProperty property="description" name="article"/></p>
					</tr>
				</table>
			</c:if>
			<c:if test ="${empty article.name}">
				<p>
				Leider ist der Artikel zur Zeit nicht vorhanden.
				<hr />
				Versuche es in ein paar Tagen nochmal, wenn der Bestand wieder aufgefüllt ist.
				</p>
			</c:if>
		</div>
		<center>

		</center>
	</div>
</body>
</html>