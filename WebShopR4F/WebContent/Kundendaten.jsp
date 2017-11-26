<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://run4fun.de/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kundendaten</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="DesignV1.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
</head>
<body>
	<jsp:useBean id="user" class="r4f.model.User" scope="session">
	</jsp:useBean> 

	<div id="container">
		<h2>Deine Daten bei R4F</h2>
		<div id="inhalt">
			<h4>Persönliche Daten</h4>
			
			<p><label for="id">ID:</label>
			<input id="id" name="id" value="<jsp:getProperty property="id" name="user"/>" type="text" /><br /></p>
			
			<p><label for="firstName">Vorname:</label>
			<input id="firstName" name="firstName" value="<jsp:getProperty property="firstName" name="user"/>" type="text" /><br /></p>
			
			<p><label for="lastName">Nachname:</label>
			<input id="lastName" name="lastName" value="<jsp:getProperty property="lastName" name="user"/>" type="text" /><br /></p>
			
			<p><label for="email">E-Mailadresse:</label>
			<input id="email" name="email" value="<jsp:getProperty property="email" name="user"/>" type="email" /><br /></p>
			
			<p><label for="birthday">Geburtsdatum:</label>
			<input id="birthday" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="dd.MM.yyyy" />" type="text" /><br /></p>
			
			<h4>Adresse</h4>
			
			<label for="street">Straße:</label>
			<input id="street" name="street" value="<jsp:getProperty property="street" name="user"/>" type="text" /><br />
			<label for="houseNumber">Hausnummer:</label>
			<input id="houseNumber" name="houseNumber" value="<jsp:getProperty property="houseNumber" name="user"/>" type="text" /><br />
			<label for="postCode">Postleitzahl:</label>
			<input id="postCode" name="postCode" value="<jsp:getProperty property="postCode" name="user"/>" type="text" /><br />
			<label for="city">Stadt:</label>
			<input id="city" name="city" value="<jsp:getProperty property="city" name="user"/>" type="text" /><br />			
			
			
<%-- 			
			<strong>ID:</strong> <jsp:getProperty property="id" name="user"/> <br />
			<strong>Vorname:</strong> <jsp:getProperty property="firstName" name="user"/> <br />
			<strong>Nachname:</strong> <jsp:getProperty property="lastName" name="user"/> <br />
			<strong>E-Mailadresse:</strong> <jsp:getProperty property="email" name="user"/> <br />
			
			<strong>Geburtsdatum:</strong><fmt:formatDate value="${user.birthday}" pattern=" dd.MM.yyyy" /> <br />
			<h4>Adresse</h4>
			<strong>Straße:</strong> <jsp:getProperty property="street" name="user"/> <br />
			<strong>Hausnummer:</strong> <jsp:getProperty property="houseNumber" name="user"/> <br />
			<strong>Postleitzahl:</strong> <jsp:getProperty property="postCode" name="user"/> <br />
			<strong>Stadt:</strong> <jsp:getProperty property="city" name="user"/> <br /> --%>
		</div>
	</div>
</body>
</html>
