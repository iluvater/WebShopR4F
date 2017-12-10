<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fehler</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body> --%>
<%@ page isErrorPage="true"%>
<%@ include file="Head.jspf" %>
<title>Fehler</title>
<%@ include file="Header.jspf" %>

	<div id="container">
		<center>
			<h4 class="ueberschrift">Ups... es ist ein Fehler aufgetreten!</h4>
			<p>Bitte versuchen Sie es später erneut.</p>
			</center>
	</div>
<%@ include file="Footer.jspf" %>