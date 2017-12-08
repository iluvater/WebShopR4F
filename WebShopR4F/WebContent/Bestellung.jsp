<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bestelldetails</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body>

	<jsp:useBean id="shoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean>
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	
	<div id="container">
		<h3>Bestelldetails</h3>
		<form action="./OrderInputToOverviewServlet" method="post">
		<div id="inhalt">
			<h4>Versandadresse</h4>		
				<p><input type="radio" id="alte" name="Versandadresse" value="matching" onchange=""> Die Versandadresse entspricht der Adresse aus dem Benutzerprofil.</p>	
				<p><input type="radio" id="neue" name="Versandadresse" value="notmatching"> Die Versandadresse ist abweichend von der Adresse aus dem Benutzerprofil.</p>	
				
				<p><label for="strasse1">Strasse</label>
				<input id="strasse1" placeholder="${user.street}" name="street" value="" type="text" /></p>
				
				<p><label for="hausnummer1">Hausnummer</label>
				<input id="hausnummer1" placeholder="${user.houseNumber}" name="houseNumber" value="" type="text" /></p>
				
				<p><label for="postleitzahl1">Postleitzahl</label>
				<input id="postleitzahl1" placeholder="${user.postCode}" name="postCode" value="" type="text" /><br /></p>
				
				<p><label for="stadt1">Ort</label>
				<input id="stadt1" placeholder="${user.city}" name="city" value="" type="text" /><br /></p>
		</div>
			<hr />
			
		<div id="inhalt">
			<h4>Bezahlart</h4>				
				<p><input type="radio" id="vorkasse" name="paymentMethod" value="Vorkasse"> Vorkasse</p>
				<p class="erlaeuterung">Wählen Sie die Variante "Vorkasse" bekommen Sie mit der Bestätigungmail unsere Bankdaten. 
				Nach Eingang der Bezahlung wird Ihre Bestellung versendet.<p>
    			<p><input type="radio" id="nachname" name="paymentMethod" value="Nachname"> Nachname</p>
    			<p class="erlaeuterung">Wählen Sie die Variante "Nachname" zahlen Sie erst, nachdem die Bestellung bei Ihnen eingetroffen ist.</p>

	
		</div>
		<hr/>
			<center>
				<input type="submit" id="button" value="Weiter zur Bestellübersicht" />
			</center>
		</form>
	</div>
	
	<div id="fuss">
		<ul>
			<li><a class="footer" href="Test.jsp">AGB's</a></li>
			<li><a class="footer" href="Test.jsp">Kontaktseite</a></li>
			<li><a class="footer" href="Test.jsp">Impressum</a></li>
			<li><a class="footer" href="Test.jsp">Hilfeseite</a></li>
			<li> © 2017 Run4Fun GmbH, Alle Rechte vorbehalten</li>
		</ul>	
	</div>

</body>
</html>
