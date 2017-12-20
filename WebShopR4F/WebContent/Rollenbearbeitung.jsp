<!-- * 
 * @author Laura
 *
 * -->
 
<%@ include file="Head.jspf" %>
<title>Rollenbearbeitung</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 	
	<jsp:useBean id="success" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	<jsp:useBean id="roleList" type="java.util.ArrayList<r4f.model.Role>" class="java.util.ArrayList" scope="request">
	</jsp:useBean> 	
	

	<div id="container">
		<c:if test = "${f:checkAuthorization(user, 'Mitarbeiter')}">
		<h2 class="ueberschrift">Rollenbearbeitung</h2>
		<form action="./CreateRoleServlet" method="post">
			<div id="inhalt">
				<h4>Neue Rolle anlegen</h4>
				<c:if test ="${not empty success }">
					<c:if test="${success.errorCode == 604 }">
						<p class="erfolg"><jsp:getProperty property="errorMessage" name="success"/></p>
					</c:if>	
				</c:if>
				<p><label for="name">Bezeichnung</label>
				<input id="name" placeholder="Rolle" name="name" value="" type="text" style='width:80%'/><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 138}">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>	
				<p><label for="description">Beschreibung</label>
				<textarea id="description" name="description" style='width:80%'></textarea><br /></p>
				<center><p><input type="submit" id="button" value="Speichern" /></p></center>
			</div>
		</form>
		<br />
		<div id="inhalt">
			<h4>Rollen &auml;ndern</h4>
			<c:forEach items="${roleList}" var="role">
				<form action="./ChangeRoleServlet" method="post">
					<div class="changeRole"><p><input class="changeRole" id="name" name="name" value="${role.name}" type="text" disabled/><br /></p>
						<input type="hidden" name="name" value="${role.name }" />
						<input type="hidden" name="roleId" value="${role.id }" />
						<c:if test ="${not empty error }">
							<c:if test="${error.errorCode == 138 }">
								<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
							</c:if>	
						</c:if>
					</div> 
					<textarea class="roleChange" id="description" name="description">${role.description}</textarea>
					<c:if test ="${not empty error }">
						<c:if test="${error.errorCode == 118 }">
							<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
						</c:if>	
					</c:if>
					<button><i class="fa fa-floppy-o"></i></button> 
				</form>
			</c:forEach>
		</div>
		</c:if>
	</div>

<%@ include file="Footer.jspf" %>