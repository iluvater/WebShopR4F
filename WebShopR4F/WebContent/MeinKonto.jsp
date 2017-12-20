<!-- * 
 * @author Laura
 *
 * -->
 
<%@ include file="Head.jspf" %>
<title>Mein Konto</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	

	<div id="container">
		<c:if test = "${f:checkAuthorization(user, 'Kunde')}">
		<div class="block">
			<h4><a class="containerLink" href="Kundendaten.jsp">Meine Daten bearbeiten</a></h4>
		</div>
		
		<div class="block">
			<h4><a class="containerLink" href="./NavigationOrderListServlet">Meine Bestellungen</a></h4>
		</div>
		</c:if>
	</div>

	
<%@ include file="Footer.jspf" %>