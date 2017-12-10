<%@ include file="Head.jspf" %>
<title>Anmeldung abschließen</title>
<%@ include file="Header.jspf" %>

	<div id="container">
	<h3>Anmeldung abschlie&szlig;en</h3>
	<form action="./ConfirmationCodeSerlvet" method="post">
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
<%@ include file="Footer.jspf" %>