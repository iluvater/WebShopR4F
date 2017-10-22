<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrierungsmaske</title>
<link href="Design.css" rel="stylesheet">
</head>
<body>
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	<form action="./RegistrierungsServlet" method="post">	
		<h2>Neu bei Run4Fun? </h2>
		<h3>Herzlich Willkommen! Noch ein paar Infos angeben, bevor es losgeht... </h3>
		<div id="seitenbereich"> <!--  Farbe f�r den Hintergrund der Textfelder-->
			<div class="inhalt_farbe_div">
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
			</div>
			<hr/>
			<div class="inhalt_farbe_div">
				<h4>Pers�nliche Daten</h4>				
 				<p><label for="anrede"> Anrede</label> 
 				<select name="anrede"><option value="Herr">Herr</option>
					<option value="Frau">Frau</option></select></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 104 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>											
   				<p><label for="vorname1">Vorname</label>
				<input id="vorname1" placeholder="Vorname" name="vorname" value="" type="text" /><br /> </p> 
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 100 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="nachname1">Nachname</label>
				<input id="nachname1" placeholder="Nachname" name="nachname" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 107 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="geburtsdatum1">Geburtsdatum</label>
				<input id="geburtsdatum1" placeholder="DD.MM.JJJJ" name="geburtsdatum" value="" type="date" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 113 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
					<c:if test="${error.errorCode == 102 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>						
				</c:if>
				<p><label for="strasse1">Strasse</label>
				<input id="strasse1" placeholder="Strasse" name="strasse" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 110 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="hausnummer1">Hausnummer</label>
				<input id="hausnummer1" placeholder="Hausnummer" name="hausnummer" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 111 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="postleitzahl1">Postleitzahl</label>
				<input id="postleitzahl1" placeholder="Postleitzahl" name="postleitzahl" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 105 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="stadt1">Ort</label>
				<input id="stadt1" placeholder="Ort" name="stadt" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 112 }">
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
		</div> 
	</form>
</body>
</html>