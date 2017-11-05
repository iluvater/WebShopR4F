<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artikeldatenerfassung</title>
<link href="Design.css" rel="stylesheet">
</head>
<body>
<form action="./ArtikelErfassungsServlet">
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	<div id="seitenbereich">
		<div class="inhalt_farbe_div">
			<h4>Artikeldaten einpflegen</h4>			
			<p><label for="bezeichnung">Bezeichnung</label>
			<input id="bezeichnung" name="bezeichnung" value="" type="text" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 117 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="preis">Preis</label>
			<input id="preis" name="preis" min="0" value="" type="number" step="0.01" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 115 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="groesse">Größe</label>
			<input id="groesse" name="groesse" min="20" value="" type="number" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 116 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="farbe">Farbe</label>
			<input id="farbe" name="farbe" value="" type="text" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 120 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
 			<p><label for="hersteller">Hersteller</label> 
 			<select name="hersteller">
 				<option value="">Bitte wählen</option>
 				<option value="Nike">Nike</option>
 				<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
 				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option></select></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 119 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="kategorie">Kategorie</label> 
			<select name="kategorie">
				<option value="">Bitte wählen</option>
 				<option value="Herren">Herren</option>
				<option value="Damen">Damen</option>
				<option value="Kinder">Kinder</option></select></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 121 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="sportart">Sportart</label> 
 			<select name="sportart">
 			<option value="">Bitte wählen</option>
 				<option value="Laufen">Laufen</option>
 				<option value="Fußball">Fußball</option>
 				<option value="Basketball">Basketball</option>
 				<option value="Golf">Golf</option>
				<option value="Schwimmen">Schwimmen</option>
				<option value="Fahrrad">Fahrrad</option></select></p>	
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 123 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>			
			<p><label for="beschreibung">Beschreibung</label>
			<textarea id="textarea_fest" name="beschreibung" cols=37></textarea>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 118 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
		</div>
		<c:if test ="${not empty error }">
			<c:if test="${error.errorCode == 122 }">
				<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
			</c:if>	
		</c:if>
		<hr />
		<center>
			<input type="submit" id="button" value="Speichern" />
		</center> 
	</div>


</form>
	
</body>
</html>