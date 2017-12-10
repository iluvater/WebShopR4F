<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bestellabschluss</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body> --%>

<%@ include file="Head.jspf" %>
<title>Bestellabschluss</title>
<%@ include file="Header.jspf" %>

	<div id="container">
		<center>
			<h4 class="ueberschrift">Herzlichen Glückwunsch! Sie haben ihre Bestellung abgeschlossen!</h4>
			<p>Ihre Bestellung wird voraussichtlich am <fmt:formatDate pattern = "dd.MM.yyyy" value = "${order.deliveryDate}" /> eintreffen.</p>
			<p>Sie können sich Ihre letzten Bestellungen im Überblick ansehen oder weitere Artikel kaufen</p>
		</center>
		<hr/>
		<div id="weiterEinkaufenBestellabschluss">
 			 	<div class="linkerButtonMerkliste">
 			 		<form action="NavigationOverviewServlet">
						<input type="submit" id="button" value="Weitere Artikel ansehen" />
					</form>
				</div>
				<div class="rechterButtonMerkliste">
					<form action="./NavigationOrderListServlet" method="post">
						<input type="submit" id="button" value="Meine Bestellungen" />
					</form>
				</div>
	</div>
<%@ include file="Footer.jspf" %>