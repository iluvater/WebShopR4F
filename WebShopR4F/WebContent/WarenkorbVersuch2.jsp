<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WarenkorbVersuch2</title>
<link href="Design.css" rel="stylesheet">
</head>
<body>
<!-- 
<form action="./WarenkorbServlet" method="post">
	<jsp:useBean id="artikel" class="r4f.model.Artikel" scope="request">
	</jsp:useBean> 
<!--<c:if übergebeneListe ="${empty error }">
<div id="seitenbereich">
		<div class="inhalt_farbe_div">
		<h4>Warenkorb</h4>
		<p>Dein Warenkorb ist noch leer. 
		<br/>
		Lege los und fülle ihn mit deinen neuen Lieblingsschuhen!</p>
		</div>
		<hr />
		<center>
			<input type="submit" id="button" value="Zu deinen Lieblingschuhen" />
			<input type="submit" id="button" value="Neues R4F Konto erstellen" />
		</center>
</div>
-->
<!--<c:if übergebeneListe ="${not empty error }">-->
	<div id="seitenbereich">
		<div class="inhalt_farbe_div">
		<h4>Warenkorb</h4>
		
		<table>
			<tr>
				<td>
					Artikel
				</td>
				<td>
					Farbe
				</td>
				<td>
					Größe
				</td>
				<td>
					Preis
				</td>
				<td>
					Gesamtpreis
				</td>
			</tr>
			while(übergebeneListe ="${not empty artikelimwarenkorb}"){
			<tr>
				<td>
					<p><jsp:getProperty property="bezeichnung" name="artikel"/></p>
				</td>
				<td>
					<p><jsp:getProperty property="farbe" name="artikel"/></p>
				</td>
				<td>
					<p><jsp:getProperty property="groesse" name="artikel"/></p>
				</td>
				<td>
					<p><jsp:getProperty property="preis" name="artikel"/></p>
				</td>
				<td>
				</td>
			</tr>
			}
			<tr>
				<td>
				</td>
				<td>
				</td>				
				<td>
				</td>
				<td>
				</td>
				<td>
					<p><jsp:getProperty property="gesamtpreis" name="PreiseallerArtikelimWarenkorb"/></p>
				</td>
				</table>
		
		</div>
		<hr />
		<center>
			<input type="submit" id="button" value="Weiter shoppen" />
			<input type="submit" id="button" value="Zur Kasse" />
		</center>
	</div>
<!--</c:if>-->
<!-- </form> -->
</body>
</html>