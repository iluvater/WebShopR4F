<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Benutzerauthentifizierung</title>
<link href="Design.css" rel="stylesheet">

</head>
<body> --%>
<%@ include file="Head.jspf" %>
<title>Benutzerauthentifizierung</title>
<%@ include file="Header.jspf" %>

<form action="./LoginServlet" method="post">
 	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean>

	<!--  <h2>Dein Konto für alles von Run 4 Fun</h2>-->
	<div id="seitenbereich">
		<div class="inhalt_farbe_div">
			<h4>Log-In Daten</h4>			
			<p><label for="email">E-Mailadresse</label>
			<input id="email" placeholder="E-Mailadresse" name="email" value="" type="email" /><br /></p>
			<p><label for="password">Passwort</label>
			<input id="password" placeholder="Passwort" name="password" value="" type="password" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 114 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	
		</div>
	</div>
	<hr />
	<center>
		<input type="submit" id="button" value="Login" />
	</center> 
</form>
<%@ include file="Footer.jspf" %>