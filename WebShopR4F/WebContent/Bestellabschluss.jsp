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

	<jsp:useBean id="order" class="r4f.model.Order" scope="session">
	</jsp:useBean>

	<div id="container">
	    <c:if test = "${f:checkAuthorization(user, 'Kunde')}">
			<center>
				<h4 class="ueberschrift">Herzlichen Gl&uuml;ckwunsch! Sie haben ihre Bestellung abgeschlossen!</h4>
				<p>Ihre Bestellung wird voraussichtlich am <b><fmt:formatDate pattern = "dd.MM.yyyy" value = "${order.deliveryDate}" /> </b>eintreffen.</p>
				<p>Sie k&ouml;nnen sich Ihre letzten Bestellungen im &Uuml;berblick ansehen oder weitere Artikel kaufen</p>
			</center>
			<hr/>
			<div id="weiterEinkaufenBestellabschluss">
 				<div class="linkerButtonMerkliste">
 			 		<form action="NavigationOverviewServlet">
						<input type="submit" id="button" value="Weitere Artikel kaufen" />
					</form>
				</div>
				<div class="rechterButtonMerkliste">
					<form action="./NavigationOrderListServlet" method="post">
						<input type="submit" id="button" value="Meine Bestellungen" />
					</form>
				</div>
	 		</div>
	 	</c:if>
	</div>

<%@ include file="Footer.jspf" %>