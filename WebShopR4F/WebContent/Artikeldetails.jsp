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
<form action="./ArtikeldetailServlet" method="post">
	<jsp:useBean id="artikel" class="r4f.model.Artikel" scope="request">
	</jsp:useBean> 
	<div id="seitenbereich">
		<div class="inhalt_farbe_div">
			<h4>Artikeldetails</h4>			
			<c:if übergebenerArtikel ="${not empty artikel}">
				<h5><jsp:getProperty property="bezeichnung" name="artikel"/></h5>

				<p><jsp:getProperty property="preis" name="artikel"/></p>
					
				<p><label for="farbe">Farbe</label>
				<p><jsp:getProperty property="farbe" name="artikel"/></p>

				<p><label for="groesse">Größe</label> 
 				<select name="groesse">
 				<option value="">Bitte w&aumlhlen</option>
 				<option value="37">37</option>
 				<option value="38">38</option>
 				<option value="39">39</option>
 				<option value="40">40</option>
				<option value="41">41</option>
				<option value="42">42</option></select></p>

				<p><jsp:getProperty property="farbe" name="artikel"/></p>
				
				<p><label for="beschreibung">Beschreibung:</label>
				<p><jsp:getProperty property="beschreibung" name="artikel"/></p>
				
				<p><label for="hersteller">Hersteller:</label>
				<p><jsp:getProperty property="hersteller" name="artikel"/></p>
			</c:if>
		</div>
		<c:if test ="${not empty error }">
			<c:if test="${error.errorCode == 122 }">
				<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
			</c:if>	
		</c:if>
		<center>
			<input type="submit" id="button" value="In den Warenkorb" />
		</center>
	</div>
</form>
</body>
</html>