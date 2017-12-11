<%@ include file="Head.jspf" %>
<title>Artikeldatenerfassung</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean>
	<jsp:useBean id="success" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean>  
	
	<div id="container">
	    <c:if test = "${f:checkAuthorization(user, 'Mitarbeiter')}">
		<form action="./ArticleCreationServlet" method="post" enctype="multipart/form-data">
		<h2 class="ueberschrift">Artikeldatenerfassung</h2>
		<c:if test ="${not empty success }">
			<c:if test="${success.errorCode == 600 }">
				<p class="erfolg"><jsp:getProperty property="errorMessage" name="success"/></p>
			</c:if>	
		</c:if>
		<div id="inhalt">
			<h4>Artikeldaten einpflegen</h4>	
			
			<p><label for="bezeichnung1">Bezeichnung:</label>
			<input id="bezeichnung1" name="name" value="" type="text" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 117 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>

			<p><label for="preis1">Preis:</label>
			<input id="preis1" name="price" min="0" value="" type="number" step="0.01" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 115 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			
<%-- 			<p><label for="groesse1">Gr&ouml;&szlig;e:</label>
			<input id="groesse1" name="size" min="20" value="" type="number" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 116 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if> --%>
			
			<p><label for="size" class="artikeldaten">Gr&ouml;&szlig;e:</label></p>
			<fieldset>
				<input type="checkbox" id="size" name="size" value="1"> 36<br />
				<input type="checkbox" id="size" name="size" value="2"> 37<br />
				<input type="checkbox" id="size" name="size" value="3"> 38<br />
				<input type="checkbox" id="size" name="size" value="4"> 39<br />
				<input type="checkbox" id="size" name="size" value="5"> 40<br />
				<input type="checkbox" id="size" name="size" value="6"> 41<br />
				<input type="checkbox" id="size" name="size" value="7"> 42<br />
				<input type="checkbox" id="size" name="size" value="8"> 43<br />
				<input type="checkbox" id="size" name="size" value="9"> 44<br />
				<input type="checkbox" id="size" name="size" value="10"> 45<br />
  			</fieldset> 
  			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 116 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			
<%-- 			<p><label for="farbe1">Farbe:</label>
			<input id="farbe1" name="color" value="" type="text" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 120 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if> --%>
			
			<p><label for="color" class="artikeldaten">Farbe:</label> </p>
			<fieldset>
          		<input type="checkbox" id="color" name="color" value="1" ${filter.color1}> Gelb<br />
           		<input type="checkbox" id="color" name="color" value="2" ${filter.color2}> Orange<br />
          		<input type="checkbox" id="color" name="color" value="3" ${filter.color3}> Rot<br />
          		<input type="checkbox" id="color" name="color" value="4" ${filter.color4}> Pink<br />
          		<input type="checkbox" id="color" name="color" value="5" ${filter.color5}> Gr&uuml;n<br />
          		<input type="checkbox" id="color" name="color" value="6" ${filter.color6}> Blau<br />
          		<input type="checkbox" id="color" name="color" value="7" ${filter.color7}> Schwarz<br />
          		<input type="checkbox" id="color" name="color" value="8" ${filter.color8}> Wei&szlig;<br />
  			</fieldset>
  			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 120 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	
			
			
 			<p><label for="hersteller">Hersteller:</label> 
 			<select name="manufacturer">
 				<option value="">Bitte w&aumlhlen</option>
 				<option value="Nike">Nike</option>
 				<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
 				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option></select></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 119 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			
			<p><label for="kategorie">Kategorie:</label> 
			<select name="category">
				<option value="">Bitte w&aumlhlen</option>
 				<option value="Herren">Herren</option>
				<option value="Damen">Damen</option>
				<option value="Kinder">Kinder</option></select></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 121 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			
			<p><label for="sportart">Sportart:</label> 
 			<select name="sport">
 			<option value="">Bitte w&aumlhlen</option>
 				<option value="Laufen">Laufen</option>
 				<option value="Fussball">Fu&szligball</option>
 				<option value="Basketball">Basketball</option>
 				<option value="Golf">Golf</option>
				<option value="Schwimmen">Schwimmen</option>
				<option value="Fahrrad">Fahrrad</option></select></p>	
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 123 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	
				
			<p><label for="image1">Bild:</label>
			<input id="image1" name="mainImage" type="file"/></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 124 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>		
					
			<p><label for="beschreibung">Beschreibung:</label>
			<textarea id="textarea_fest" name="description" cols=37></textarea>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 118 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
		</div>
		<c:if test ="${not empty error }">
			<c:if test="${error.errorCode == 122 }">
				<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
			</c:if>	
		</c:if>
		<hr />
		<center>
			<input type="submit" id="button" value="Speichern" />
		</center> 
		</form>
		</c:if>
	</div>
<%@ include file="Footer.jspf" %>
