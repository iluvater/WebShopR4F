<!-- * 
 * @author Maike
 * @edited by Laura
 *
 * -->
 
<%@ include file="Head.jspf" %>
<title>Bestelldetails</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="shoppingBasket" class="r4f.model.ShoppingBasket" scope="session">
	</jsp:useBean>
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	

	<div id="container">
	    <c:if test = "${f:checkAuthorization(user, 'Kunde')}">
		<h3 class="ueberschrift">Bestelldetails</h3>
		<form action="./OrderInputToOverviewServlet" method="post">
		<div id="inhalt">
			<h4>Versandadresse</h4>		
			<p><input type="radio" id="matchingAddresses" name="matchingAddresses" value="matchingAddresses" checked="checked"> Die Versandadresse entspricht der Adresse aus dem Benutzerprofil.</p>	
			<p><input type="radio" id="matchingAddresses" name="matchingAddresses" value="notmatching"> Die Versandadresse ist abweichend von der Adresse aus dem Benutzerprofil.</p>
			<div id="uebersicht">

 				<p><label for="anrede"> Anrede</label> 
 				<select name="salutation">
 					<option value="Herr">Herr</option>
					<option value="Frau">Frau</option>
				</select></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 104 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>											
   				<p><label for="vorname1">Vorname</label>
				<input id="vorname1" placeholder="Vorname" name="firstName" value="" type="text" /><br /> </p> 
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 100 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="nachname1">Nachname</label>
				<input id="nachname1" placeholder="Nachname" name="lastName" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 107 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="strasse1">Strasse</label>
				<input id="strasse1" placeholder="Strasse" name="street" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 110 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="hausnummer1">Hausnummer</label>
				<input id="hausnummer1" placeholder="Hausnummer" name="houseNumber" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 111 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="postleitzahl1">Postleitzahl</label>
				<input id="postleitzahl1" placeholder="Postleitzahl" name="postCode" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 105 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
				<p><label for="stadt1">Ort</label>
				<input id="stadt1" placeholder="Ort" name="city" value="" type="text" /><br /></p>
				<c:if test ="${not empty error }">
					<c:if test="${error.errorCode == 112 }">
						<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
					</c:if>	
				</c:if>
			</div>
		</div>
		<hr />	
		<div id="inhalt">
			<h4>Bezahlart</h4>
			<p><input type="radio" id="vorkasse" name="paymentMethod" value="Vorkasse" checked="checked"> Vorkasse</p>
			<div id="uebersicht"><p class="erlaeuterung">W&auml;hlen Sie die Variante "Vorkasse" bekommen Sie mit der Best&auml;tigungmail unsere Bankdaten. 
			Nach Eingang der Bezahlung wird Ihre Bestellung versendet.<p></div>
    	<p><input type="radio" id="nachnahme" name="paymentMethod" value="Nachnahme"> Nachnahme</p>
    	<div id="uebersicht"><p class="erlaeuterung">W&auml;hlen Sie die Variante "Nachnahme" zahlen Sie erst, nachdem die Bestellung bei Ihnen eingetroffen ist.</p></div>
		</div>
		<hr/>
 		<center>
 			<div class="linkerButtonMerkliste">
				<a href="Warenkorb.jsp">
					<input type="button" id="button" value="Zur&uuml;ck zum Warenkorb" />
				</a>
			</div>
			<div class="rechterButtonMerkliste">
				<input type="submit" id="button" value="Weiter zur Bestell&uuml;bersicht" />
			</div>
		</center>
		<hr/>
		</form>
		</c:if>
	</div>

<%@ include file="Footer.jspf" %>
