<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Anmeldung abschließen</title>
<link href="DesignV1.css" rel="stylesheet">
</head>
<body>
	<div id="container">
	<h3>Anmeldung abschließen</h3>
	<form action="./ConfirmationCodeServlet" method="post">
		<div id="inhalt">
			<p>Bitte geben Sie hier den Code ein, der Ihnen per E-Mail zugesendet wurde, um Ihr Kundenkonto zu aktivieren!</p>
			<center>
			<input id="code" name="code" value="" type="text" /> <hr/>
			<input type="submit" id="button" value="Kundenkonto aktivieren" />
			</center>
			<hr/>
		</div>
	</form>
	</div>
</body>
</html>