<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Hilfeseite</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body> --%>
<%@ include file="Head.jspf" %>
<title>Hilfeseite</title>
<%@ include file="Header.jspf" %>

<div id="containerAgb">
<h2 class="ueberschrift">Kundenservice</h2>
	
	<table class="hilfe">
		
		<tr>
		<td class="hilfe">
		<a href="#FAQs"><img width="150" height="150" border="1" src="./FAQ.png" alt="FAQ"><br/>FAQs</a>
		</td>
		<td class="hilfe">
		<a href="#Kontakt"><img width="150" height="150" border="1" src="./Kontakt.jpg" alt="Kontakt"><br/>Kontakt</a>
		</td>
		<td class="hilfe">
		<a href="#VersandundLieferung"><img width="150" height="150" border="1" src="./Versand.jpg" alt="Versand und Lieferung"><br/>Versand und Lieferung</a>
		</td>
		</tr>
		<tr>
		<td class="hilfe">
		<a href="#MeinR4F"><img width="150" height="150" border="1" src="./Konto.jpg" alt="Mein R4F"><br/>Mein R4F</a>
		</td>
		<td class="hilfe">
		<a href="#Produkte"><img width="150" height="150" border="1" src="./Produkte.png" alt="Produkte"><br/>Produkte</a>
		</td>
		<td class="hilfe">
		<a href="#MeineKarrierebeiR4F"><img width="150" height="150" border="1" src="./Karriere.jpg" alt="Meine Karriere bei R4F"><br/>Meine Karriere bei R4F</a>
		</td>	
		</tr>
	</table>

	<div class="inhalt">
	<h4 class="hilfe" id="FAQs" tabindex="-1">FAQs</h4>
	<p class="hilfeFrage">Gibt es einen Mindestbestellwert?</p>
	<p class="hilfeAntwort">Nein, bei uns gibt es keinen Mindestbestellwert. Sie k&ouml;nnen selbst bestellen, wenn Sie nur den g&uuml;nstigsten Artikel unseres Sortiments kaufen.</p>
	<p class="hilfeAntwort">Allerdings haben wir Standard-Versandkosten von 4,95&euro;. Mehr dazu finden Sie unter <a href="#FAQVersandundLieferung">Lieferung und Versand</a></p>
	<p class="hilfeFrage">Falls mir ein Artikel nicht passt, kann ich ihn zur&uuml;ckschicken?</p>
	<p class="hilfeAntwort">Ja, Sie k&ouml;nnen Artikel auch zur&uuml;ck schicken. Die Retourenabwicklung k&ouml;nnen Sie gerne mit unserem Personal besprehen. Hier gelangen Sie zu unserem <a href="Impressum.jsp">Impressum.</a></p>
	<p class="hilfeFrage">Ist ein vergriffener Artikel sp&auml;ter wieder lieferbar?</p>
	<p class="hilfeAntwort">Vielleicht, wir f&uuml;llen unseren Produktbestand regelm&auml;&szlig;ig auf. Es kann allerdings auch sein, dass Ihr gew&uuml;nschter Artikel aus unserem Sortiment genommen wurde. Unser <a href="Kontakt.jsp">Kundenservice</a> hilft Ihnen bei diesem Problem gerne weiter. </p>
	<p class="hilfeFrage">Warum kann ich nichts bestellen?</p>
	<p class="hilfeAntwort">Es k&ouml;nnte daran liegen, dass Sie nicht angemeldet oder noch nicht registriert sind. <a href="Anmelden.jsp">Hier</a> kommen Sie zum Login, falls Sie schon einen Account haben. Wenn nicht, k&ouml;nnen Sie sich <a href="Registirerungsmaske.jsp">hier</a> registrieren. </p>
	
	<hr/>
	<h4 class="hilfe" id="Kontakt" tabindex="-1">Kontakt</h4>
	<p class="hilfeFrage">Telefon:05251 123 <br /> E-Mail: <a href="mailto:webshop.r4f@gmail.com">webshop.r4f@gmail.com</a></p>
	<p class="hilfeAntwort">Unser Kundenservice hilft Ihnen bei individuellen Fragen immer gerne!</p>
	<p class="hilfeAntwort">Besuchen Sie dazu einfach unsere <a href="Kontakt.jsp">Kontaktseite</a>.</p> 
	<p class="hilfeAntwort">Wenn sie mehr &uuml;ber uns wissen wollen, dann hilft Ihnen unser <a href="Impressum.jsp">Impressum.</a> vielleicht weiter.</p>

	<hr/>
	<h4 class="hilfe" id="VersandundLieferung" tabindex="-1">Versand und Leiferung</h4>
	<p class="hilfeFrage">Welche Zahlungsarten gibt es?</p>
	<p class="hilfeAntwort">Sie k&ouml;nnen bei uns mit Kreditkarte, PayPal, per Rechnung, oder per Vorkasse zahlen. W&auml;hlen Sie Ihren Favorit dirket bei der Bestellung aus.</p>
	<p class="hilfeFrage">Welche Versandarten sind m&ouml;glich und &uuml;ber welche Lieferanten werden die Artikel geliefert?</p>
	<p class="hilfeAntwort">Wir bieten Ihnen einen Standarsversand, sowie einen Expressversand an. </p>
	<p class="hilfeAntwort">Der Standarsversand dauert etwa 5 Werktage. Mit dem Expressversand kommt Ihre Ware bei einer Bestellung bis 18 Uhr bereits am n&auml;sten Werktag bei Ihnen an.</p>
	<p class="hilfeAntwort">In Bezug auf unsere Lieferanten kooperieren wir mit Hermes, UPS und DHL.</p>
	<p class="hilfeFrage">Ab welchem Bestellwert ist meine Bestellung kostenlos?</p>
	<p class="hilfeAntwort">Bei einer Lieferung per Standardversand kommen ab einem Bestellwert von 50&euro; keine zus&auml;tzlichen Bestellkosten mehr aus Sie zu.</p>
	<p class="hilfeAntwort">Bei einer Lieferung per Expressversand m&uuml;ssen Sie mindestens Artikel im Wert von 100&euro; bestellen, damit keine zustzlichen Versandkosten berechnet werden.</p>
	
	<hr/>
	<h4 class="hilfe" id="MeinR4F" tabindex="-1">Mein R4F</h4>
	<p class="hilfeFrage">Wie kann ich mich registrieren?</p>
	<p class="hilfeAntwort">Auf der Seite <a href="Registrierungsmaske.jsp">Registrierung</a> k&ouml;nnen Sie Ihr eigenes Konto einrichten.</p>
	<p class="hilfeFrage">Wo kann ich meine Daten &auml;ndern?</p>
	<p class="hilfeAntwort">Verlinkung zur Seite der Kundendaten&auml;nderung</p>
	<p class="hilfeFrage">Kann ich meine letzten Bestellungen sehen?</p>
	<p class="hilfeAntwort">Verlinkung zu Seite der letzten Bestellungen </p>

	<hr/>
	<h4 class="hilfe" id="Produkte" tabindex="-1">Produkte</h4>
	<p class="hilfeFrage">Inhalt zu Produkte</p>
	<hr/>
	<h4 class="hilfe" id="MeineKarrierebeiR4F" tabindex="-1">Meine Karriere bei R4F</h4>
	<p class="hilfeFrage">Sie suchen neue Herausforderungen?</p>
	<p class="hilfeFrage">Dann sind Sie bei Run 4 Fun genau richtig!</p>
	<p class="hilfeFrage">Bei uns finden Sie Jobangebote vom IT-Spezialist bis zum Lagerist.</p>
	<p class="hilfeAntwort">Senden Sie Ihre Bewerbung inkl. Motivationsschreiben und Lebenslauf mit dem Betreff "Meine Karriere bei R4F" an <a href="mailto:webshop.r4f@gmail.com">webshop.r4f@gmail.com</a>!</p>
	</div>
</div>
<%@ include file="Footer.jspf" %>