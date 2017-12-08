<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bestellabschluss</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body>
	<div id="container">
	<h3></h3>
		<div id="inhalt">
			<center>
			<h4>Herzlichen Glückwunsch! Sie haben ihre Bestellung abgeschlossen!</h4>
			<p>Ihre Bestellung wird voraussichtlich am ${Order.entryDate}</p>
			<p>Sie können sich Ihre Bestellungen im Überblick ansehen oder weitere Artikel kaufen</p>
			<input type="submit" id="button" value="Bestellüberblick" />
			<input type="submit" id="button" value="Weiter Einkaufen" />
			</center>
			<hr/>
		</div>
	</div>
</body>
</html>