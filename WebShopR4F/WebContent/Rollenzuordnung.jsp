<%@ include file="Head.jspf" %>
<title>Rollenzuordnung</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="userList" type="java.util.ArrayList<r4f.model.User>" class="java.util.ArrayList" scope="request">
	</jsp:useBean> 	

<div id="container">
	<h2 class="ueberschrift">Rollenzuordnung</h2>
	<form action="./CreateUserRoleMappingServlet" method="post">
		<div id="inhalt">
			<h4>Rolle einem Benutzer zuordnen</h4>
			<p><label for="email">Benutzer</label>
			<input id="email" placeholder="E-Mailadresse" name="email" value="" type="email" style='width:80%'/><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 108}">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>				
			<p><label for="name">Bezeichnung</label>
			<input id="name" placeholder="Rolle" name="name" value="" type="text" style='width:80%'/><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 138}">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	
			<center><p><input type="submit" id="button" value="Speichern" /></p></center>
		</div>
	</form>
</div>

<%@ include file="Footer.jspf" %>