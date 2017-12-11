<%@ include file="Head.jspf" %>
<title>Passwort zur&uuml;cksetzten</title>
<%@ include file="Header.jspf" %>
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 

	<div id="container">
	<h3 class="ueberschrift">Passwort zur&uuml;cksetzten</h3>
	<form action="./ChangePasswordServlet" method="post">
		<div id="inhalt">
			<p>Bitte geben Sie hier Ihre E-Maile und den Code ein, der Ihnen per E-Mail zugesendet wurde. <br/>
			Anschlie&szlig;end k&ouml;nnen die Ihr Passowort zur&uuml;cksetzen!</p>
			
			<p><label for="email">E-Mailadresse</label>
			<input id="email" name="email" value="" type="text" /></p>
				<c:if test="${not empty error }">
					<c:if test="${error.errorCode == 106 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>	
			<p><label for="code">Code</label>
			<input id="code" name="code" value="" type="text" /></p>
			
			<p><label for="password">neues Passwort</label>
			<input id="password" name="password" value="" type="password" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 109 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="password2"> neuesPasswort wiederholen</label>
			<input id="password2"  name="password2" value="" type="password" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 132 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	
			<hr/>
			<input type="submit" id="button" value="Kundenkonto aktivieren" />
			<hr/>
		</div>
	</form>
	</div>
<%@ include file="Footer.jspf" %>