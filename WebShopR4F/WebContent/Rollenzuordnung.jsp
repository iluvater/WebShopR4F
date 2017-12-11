<%@ include file="Head.jspf" %>
<title>Rollenzuordnung</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="userList" type="java.util.ArrayList<r4f.model.User>" class="java.util.ArrayList" scope="request">
	</jsp:useBean> 	
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	

<div id="container">
	<c:if test = "${f:checkAuthorization(user, 'Mitarbeiter')}">
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
			<p><label for="name">Rolle</label>
			<input id="name" placeholder="Rolle" name="name" value="" type="text" style='width:80%'/><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 138}">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 126}">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	
			<center><p><input type="submit" id="button" value="Speichern" /></p></center>
		</div>
	</form>
		<c:if test ="${not empty userList}">
		<form action="./DeleteUserRoleMappingServlet" method="post">
			<div id="rollen">
				<div class="userInfos">
					<p><b>Vorname, Nachname, E-Mailadresse, ID</b></p>
				</div>
				<div class="userRolle">
					<p><b>Rolle</b></p>
				</div>
				<div class="rolleLoeschen">
					<p><b>Rollenzuordnung entfernen</b></p>
				</div>
				
				<c:forEach items="${userList}" var="user">
					<c:forEach items="${user.role}" var="role">
						<div class="userInfos">
							<p>${user.firstName}, ${user.lastName}, ${user.email}, ${user.id} </p>
						</div>
						<div class="userRolle">
							<p>${role.name}</p>
						</div>
						<div class="rolleLoeschen">
							<p><input type="submit" id="button" value="entfernen" /></p>
						</div>
					</c:forEach>
				</c:forEach>
				</div>	
			</form>
		</c:if>
	</c:if>
	</div>


<%@ include file="Footer.jspf" %>