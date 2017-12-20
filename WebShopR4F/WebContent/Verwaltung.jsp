<!-- * 
 * @author Laura
 *
 * -->
 
<%@ include file="Head.jspf" %>
<title>Verwaltung</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	

	<div id="container">
	    <c:if test = "${f:checkAuthorization(user, 'Mitarbeiter')}">
		<div class="block">
			<h4><a class="containerLink" href="Artikeldatenerfassung.jsp">Artikeldaten erfassen</a></h4>
		</div>
		
		<div class="block">
			<h4><a class="containerLink" href="Artikelsuche.jsp">Artikeldaten &auml;ndern</a></h4>
		</div>
		
		<div class="block">
			<h4><a class="containerLink" href="NavigationRoleOverviewServlet">Rollen anlegen / bearbeiten</a></h4>
		</div>
		
		<div class="block">
			<h4><a class="containerLink" href="NavigationUserOverviewServlet">Rollen zuordnen / entfernen</a></h4>
		</div>
		</c:if>	
	</div>
	
<%@ include file="Footer.jspf" %>