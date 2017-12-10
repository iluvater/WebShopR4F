<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://run4fun.de/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Artikelsuche</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="DesignV1.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
</head>
<body> --%>

<%@ include file="Head.jspf" %>
<title>Artikelsuche</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="article" class="r4f.model.Article" scope="request">
	</jsp:useBean> 
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	
	<div id="container">
		<h2>Artikelsuche</h2>
		<h3>Bitte geben Sie die ID des Artikel ein, den Sie bearbeiten möchten.</h3>
		<form action="./SearchArticleForChangeServlet" method="post">
		<div id="inhalt">
			<h4>Eingabe der ID des gewünschten Artikels</h4>
			<p><label for="id">ID:</label>
			<input id="id" name="id" min="1" value="<jsp:getProperty property="id" name="article"/>" type="number" step="1" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 127 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<hr />
			<center> 
				<input type="submit" id="button" value="Bearbeiten" />
			</center>
		</div>
		</form>
	</div>
<%@ include file="Footer.jspf" %>

