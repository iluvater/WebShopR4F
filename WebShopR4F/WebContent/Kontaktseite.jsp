<%@ include file="Head.jspf" %>
<title>Kontaktseite</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	<div id="container">
		<h3 class="agb">Kontakt und Feedback</h3>
		<p class="agb">Haben Sie eine Frage, eine gute Idee oder möchten Sie uns einfach Ihre Meinung mitteilen?<br/>
		Dann nutzen Sie bitte das unten stehende Formular, um uns Ihre Meinung mitzuteilen. Wir werden Ihnen sobald wie möglich antworten.<br/>
		Die häufigsten Fragen und Antworten finden sie außerdem in unseren FAQ's.</p>
		<form action="./SendContactMailServlet" method="post">
		<div id="inhalt">
			<h4>Deine Nachtricht an Run4Fun</h4>
			<p><label for="email">E-Mailadresse</label>
			<input id="email" placeholder="E-Mailadresse" name="email" value="" type="email" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 106 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	
			<p><label for="subject">Betreff</label>
			<input id="subject" placeholder="Betreff" name="subject" value="" type="text" /><br /></p>
			<p><label for="body">Ihre Nachricht</label>
			<textarea id="body" name="body" cols="35" rows="4"></textarea><br /></p>
			<p><input type="submit" id="button" value="Nachricht versenden" /></p>
		</div>
		</form>
		
	</div>
	<jsp:include page="Footer.jspf" />