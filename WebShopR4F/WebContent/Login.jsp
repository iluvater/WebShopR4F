<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body> --%>
<%@ include file="Head.jspf" %>
<title>Login</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	
	<div id="container">
		<h2 class="ueberschrift">Dein Konto f&uuml;r alles von Run 4 Fun</h2>
		<h3 class="center">Bereits Kunde bei Run 4 Fun?</h3>
		<form action="./LoginServlet" method="post">
			<div id="inhalt">
				<h4>Log-In Daten</h4>			
				<p><label for="email">E-Mailadresse</label>
				<input id="email" placeholder="E-Mailadresse" name="email" value="" type="email" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 106 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>	
				<p><label for="password">Passwort</label>
				<input id="password" placeholder="Passwort" name="password" value="" type="password" />
				<br/><a href="passwortVergessen.jsp">Passwort vergessen</a></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 114 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>	
				
			</div>
		<hr />
		<center>
			<input type="submit" id="button" value="Login" />
		</center> 
		</form>
		<br />
	
		<h3 class="center">Noch kein Mitglied? Jetzt registrieren...</h3>
		<form action="./RegistrationServlet" method="post">
		<div id="inhalt">
			<h4>Log-In Daten</h4>			
			<p><label for="email">E-Mailadresse</label>
			<input id="email" placeholder="E-Mailadresse" name="email" value="" type="email" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 103 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
				<c:if test="${error.errorCode == 106 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
				<c:if test="${error.errorCode == 108 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="password">Passwort</label>
			<input id="password" placeholder="Passwort" name="password"value="" type="password" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 109 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="password2">Passwort wiederholen</label>
			<input id="password2" placeholder="Passwort" name="password2" value="" type="password" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 132 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>				
		</div>
		<hr/>
		<div id="inhalt">
			<h4>Pers&ouml;nliche Daten</h4>				
 				<p><label for="anrede"> Anrede</label> 
 				<select name="salutation"><option value="Herr">Herr</option>
					<option value="Frau">Frau</option></select></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 104 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>											
   				<p><label for="vorname1">Vorname</label>
				<input id="vorname1" placeholder="Vorname" name="firstName" value="" type="text" /><br /> </p> 
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 100 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="nachname1">Nachname</label>
				<input id="nachname1" placeholder="Nachname" name="lastName" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 107 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="geburtsdatum1">Geburtsdatum</label>
				<input id="geburtsdatum1" placeholder="DD.MM.JJJJ" name="birthday" value="" type="date" /><br /></p>
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
				<p><label for="strasse1">Strasse</label>
				<input id="strasse1" placeholder="Strasse" name="street" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 110 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="hausnummer1">Hausnummer</label>
				<input id="hausnummer1" placeholder="Hausnummer" name="houseNumber" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 111 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="postleitzahl1">Postleitzahl</label>
				<input id="postleitzahl1" placeholder="Postleitzahl" name="postCode" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 105 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="stadt1">Ort</label>
				<input id="stadt1" placeholder="Ort" name="city" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 112 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
		</div>	
		<hr />
		<div id="checkboxRegistrierung">
			<p><input type="checkbox" id="" name="AGB" value="1"> Ja, ich habe die AGB's gelesen<br></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 135 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
		</div>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 101 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<hr />
			<center>
				<input type="submit" id="button" value="Registrieren" />
			</center>
		</form>
	</div>
	
<%@ include file="Footer.jspf" %>
