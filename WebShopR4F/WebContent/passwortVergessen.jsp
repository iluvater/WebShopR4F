<!-- * 
 * @author Maike
 *
 * -->

<%@ include file="Head.jspf" %>
<title>Passwort vergessen</title>
<%@ include file="Header.jspf" %>
<div id="container">
	<h3 class="ueberschrift">Passwort vergessen</h3>
	<form action="./SendForgotPasswordMailServlet" method="post">
		<div id="inhalt">
			<p>Bitte geben Sie hier Ihr E-Mail-Adresse ein. <br/>Wir werden Ihnen anschlie&szlig;end einen Code zusenden mit dem Sie Ihr Passwort zur&uuml;cksetzten k&ouml;nnen!</p>
			<center>
				<input id="email" name="email" value="" type="text" /> <hr/>
				<input type="submit" id="button" value="Code senden" />
			</center>
			<hr/>
		</div>
	</form>
<%@ include file="Footer.jspf" %>