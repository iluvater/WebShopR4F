<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Benutzerauthentifizierung</title>
<link href="Design.css" rel="stylesheet">
</head>
<body>
	<!--  <h2>Dein Konto für alles von Run 4 Fun</h2>-->
	<div id="seitenbereich">
		<div class="inhalt_farbe_div">
			<h4>Log-In Daten</h4>			
			<p><label for="email">E-Mailadresse</label>
			<input id="email" placeholder="E-Mailadresse" name="email" value="" type="email" /><br /></p>
			<p><label for="password">Passwort</label>
			<input id="password" placeholder="Passwort" name="password"value="" type="password" /></p>
		</div>
	</div>
	<center>
		<input type="submit" id="button" value="Login" />
	</center>
</body>
</html>