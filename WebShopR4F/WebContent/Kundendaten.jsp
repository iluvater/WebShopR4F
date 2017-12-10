<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
	</jsp:useBean>  --%>
	
<%@ include file="Head.jspf" %>
<title>Kundendaten</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 

	<div id="container">
		<h2 class="ueberschrift">Deine Daten bei R4F</h2>
		<h3>Überprüfen Sie, ob Ihre Kundendaten aktuell und richtig sind, oder ändern Sie die Daten einfach.  </h3>
		<form action="./ChangeUserDataServlet" method="post">
		<div id="inhalt">
			<h4>Persönliche Daten</h4>
			
			<p><label for="id">ID:</label>
			<input id="id" name="id" value="<jsp:getProperty property="id" name="user"/>" type="text" disabled/><br /></p>
			<input type="hidden" name="id" value="${user.id }" />
			
  			<p><label for="salutation"> Anrede</label> 
 				<select id="salutation" name="salutation"><option><jsp:getProperty property="salutation" name="user"/></option>
 				<c:if test="${user.salutation == 'Herr' }">
					<option value="Frau">Frau</option>
				</c:if>
				 <c:if test="${user.salutation == 'Frau' }">
					<option value="Herr">Herr</option>
				</c:if>
				</select>
			</p>				
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 104 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	  							
			<p><label for="firstName">Vorname:</label>
			<input id="firstName" name="firstName" value="<jsp:getProperty property="firstName" name="user"/>" type="text" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 100 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="lastName">Nachname:</label>
			<input id="lastName" name="lastName" value="<jsp:getProperty property="lastName" name="user"/>" type="text" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 107 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="email">E-Mailadresse:</label>
			<input id="email" name="email" value="<jsp:getProperty property="email" name="user"/>" type="email" disabled/><br /></p>
			<p><label for="birthday">Geburtsdatum:</label>
			<input id="birthday" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="dd.MM.yyyy" />" type="text" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 113 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
				<c:if test="${error.errorCode == 102 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>		
				<c:if test="${error.errorCode == 129 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>					
			</c:if>
			
			<h4>Adresse</h4>
			
			<p><label for="street">Straße:</label>
			<input id="street" name="street" value="<jsp:getProperty property="street" name="user"/>" type="text" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 110 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="houseNumber">Hausnummer:</label>
			<input id="houseNumber" name="houseNumber" value="<jsp:getProperty property="houseNumber" name="user"/>" type="text" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 111 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="postCode">Postleitzahl:</label>
			<input id="postCode" name="postCode" value="<jsp:getProperty property="postCode" name="user"/>" type="text" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 105 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="city">Stadt:</label>
			<input id="city" name="city" value="<jsp:getProperty property="city" name="user"/>" type="text" /><br /></p>			
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 112 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
		</div>
		<c:if test ="${not empty error }">
			<c:if test="${error.errorCode == 126 }">
				<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
			</c:if>	
		</c:if>
		<hr />
		<%-- <center> --%>
		<input type="submit" id="button" value="Speichern" />
		
		</form>
		
		<input type="submit" id="button" value="Abbrechen" />
		<%-- </center>  --%>
	</div>
<%@ include file="Footer.jspf" %>
