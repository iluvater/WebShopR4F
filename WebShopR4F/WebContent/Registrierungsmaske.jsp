<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrierungsmaske</title>
<link href="Design.css" rel="stylesheet">
</head>
<body>
	<form action="./RegistrierungsServlet" method="post">
		
		<h2>Neu bei Run4Fun? </h2>
		<h3>Herzlich Willkommen! Noch ein paar Infos angeben, bevor es losgeht... </h3>
		<div id="seitenbereich"> <!--  Farbe für den Hintergrund der Textfelder-->
			<div class="inhalt_farbe_div">
				<h4>Log-In Daten</h4>			
				<p><label for="email">E-Mailadresse</label>
				<input id="email" placeholder="E-Mailadresse" name="email" value="" type="email" /><br /></p>
				<p><label for="password">Passwort</label>
				<input id="password" placeholder="Passwort" name="password"value="" type="password" /></p>
			</div>
			<hr/>
			<div class="inhalt_farbe_div">
				<h4>Persönliche Daten</h4>				
 				<p><label for="anrede"> Anrede</label> 
 				<select name="anrede"><option value="Herr">Herr</option>
					<option value="Frau">Frau</option></select></p>						
   				<p><label for="vorname1">Vorname</label>
				<input id="vorname1" placeholder="Vorname" name="vorname" value="" type="text" /><br /> </p> 
				<p><label for="nachname1">Nachname</label>
				<input id="nachname1" placeholder="Nachname" name="nachname" value="" type="text" /><br /></p>
				<p><label for="geburtsdatum1">Geburtsdatum</label>
				<input id="geburtsdatum1" placeholder="DD.MM.JJJJ" name="geburtsdatum" value="" type="date" /><br /></p>
				<p><label for="strasse1">Strasse</label>
				<input id="strasse1" placeholder="Strasse" name="strasse" value="" type="text" /><br /></p>
				<p><label for="hausnummer1">Hausnummer</label>
				<input id="hausnummer1" placeholder="Hausnummer" name="hausnummer" value="" type="text" /><br /></p>
				<p><label for="postleitzahl1">Postleitzahl</label>
				<input id="postleitzahl1" placeholder="Postleitzahl" name="postleitzahl" value="" type="text" /><br /></p>
				<p><label for="stadt1">Ort</label>
				<input id="stadt1" placeholder="Ort" name="stadt" value="" type="text" /><br /></p>
			</div>
			<hr />
			<center>
				<input type="submit" id="button" value="Registrieren" />
			</center>
		</div> 
		
		
		
<!-- 		<div id="wrapper">
			<h4>Log-In Daten</h4>
			<div id="Feldname">	
					<p>E-Mail</p>
					<p>Passwort</p>
			</div>
			<div id="Eingabefeld">
					<input type="email" name="email" value=""><br />
					<input type="password" name="passwort" value=""><br />
			</div>
			<h4>Persönliche Daten</h4>
		</div> -->
		
		
	
<!--  Versuch mit Flexboxen, hat nur teilweise funktioniert -->	
<!-- 		<div class="Container">
			<div class="elem1">
				<div class="Feldnamen">
					<p>E-Mail</p>
					<p>Passwort</p>
				</div>
			</div>
			<div class="elem2">
				<div class="Eingabefelder">
					<input type="email" name="email" value=""><br />
					<input type="password" name="passwort" value=""><br />
				</div>
			</div>
		</div> -->
<!-- Versuch mit einer Tabelle, soweit fertig nicht formatiert -->	
	<!-- 	<table>
			<tr>
				<td>E-Mail</td>
				<td><input type="email" name="email" value=""></td>
			</tr>	
			<tr>
				<td>Passwort</td>
				<td><input type="password" name="passwort" value=""></td>
			</tr>
		</table>
		<h4>Persönliche Daten</h4>
		<table>
			<tr>
				<td>Anrede</td>
				<td><select name="anrede"><option value="Herr">Herr</option>
						<option value="Frau">Frau</option></select></td>
			</tr>
			<tr>
				<td>Vorname</td>
				<td><input name="vorname" type="text" value""></td>
			</tr>
			<tr>
				<td>Nachname</td>
				<td><input name="nachname" type="text" value""></td>
			</tr>
			<tr>
				<td>Geburtsdatum</td>
				<td><input name="geburtsdatum" type="date" value""></td>
			</tr>
			<tr>
				<td>Strasse</td>
				<td><input name="strasse" type="text" value""></td>
			</tr>
			<tr>
				<td>Hausnummer</td>
				<td><input name="hausnummer" type="text" value""></td>
			</tr>
			<tr>
				<td>Postleitzahl</td>
				<td><input name="posteitzahl" type="text" value""></td>
			</tr>
			<tr>
				<td>Ort</td>
				<td><input name="ort" type="text" value""></td>
			</tr>
			
		</table>		 -->
	</form>
</body>
</html>