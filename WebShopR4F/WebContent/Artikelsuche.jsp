<!-- * 
 * @author Laura
 *
 * -->

<%@ include file="Head.jspf" %>
<title>Artikelsuche</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="article" class="r4f.model.Article" scope="request">
	</jsp:useBean> 
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	
	<div id="container">
	    <c:if test = "${f:checkAuthorization(user, 'Mitarbeiter')}">
		<h2 class="ueberschrift">Artikelsuche</h2>
		<h3 class="impressum">Bitte geben Sie die ID des Artikel ein, den Sie bearbeiten m&ouml;chten</h3>
		<form action="./SearchArticleForChangeServlet" method="post">
		<div id="inhalt">
			<h4>Eingabe der ID des gew&uuml;nschten Artikels</h4>
			<p><label for="id">ID:</label>
			<input id="id" name="id" min="1" value="<jsp:getProperty property="id" name="article"/>" type="number" step="1" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 127 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<hr />
			<center> 
				<input type="submit" id="button" value="Bearbeiten" />
			</center>
		</div>
		</form>
		</c:if>
	</div>
<%@ include file="Footer.jspf" %>

