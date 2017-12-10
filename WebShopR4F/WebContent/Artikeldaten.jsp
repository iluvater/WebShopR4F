<%@ include file="Head.jspf" %>
<title>Artikeldatenbearbeitung</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="article" class="r4f.model.Article" scope="request">
	</jsp:useBean> 
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	<jsp:useBean id="success" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	<jsp:useBean id="filter" class="r4f.controller.filter.CheckboxStatus" scope="request">
	</jsp:useBean> 

	<div id="container">
		<h2>Artikeldatenänderung</h2>
		<h3>Bitte im Folgenden die Artikeldaten anpassen</h3>
		<form action="./ChangeArticleServlet" method="post" enctype="multipart/form-data">
		<div id="inhalt">
			<h4>Artikeldaten ändern</h4>
				
			<p><label for="id">ID:</label>
			<input id="id" name="id" value="<jsp:getProperty property="id" name="article"/>" type="text" disabled/><br />
			<input type="hidden" name="id" value="${article.id }" /></p>
				
			<p><label for="name">Bezeichnung:</label>
			<input id="name" name="name" value="<jsp:getProperty property="name" name="article"/>" type="text" /><br /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 117 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			
			<p><label for="price">Preis:</label>
			<input id="price" name="price" min="0" value="<jsp:getProperty property="price" name="article"/>" type="number" step="0.01" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 115 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			
<%--		<p><label for="size">Gr&ouml;&szlige</label>
			<input id="size" name="size" min="20" value="<jsp:getProperty property="size" name="article"/>" type="number" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 116 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if> --%>
			
			<p><label for="size" class="artikeldaten">Gr&ouml;&szlig;e:</label></p>
			<fieldset>
				<input type="checkbox" id="size" name="size" value="1" ${filter.size1}> 36<br />
				<input type="checkbox" id="size" name="size" value="2" ${filter.size2}> 37<br />
				<input type="checkbox" id="size" name="size" value="3" ${filter.size3}> 38<br />
				<input type="checkbox" id="size" name="size" value="4" ${filter.size4}> 39<br />
				<input type="checkbox" id="size" name="size" value="5" ${filter.size5}> 40<br />
				<input type="checkbox" id="size" name="size" value="6" ${filter.size6}> 41<br />
				<input type="checkbox" id="size" name="size" value="7" ${filter.size7}> 42<br />
				<input type="checkbox" id="size" name="size" value="8" ${filter.size8}> 43<br />
				<input type="checkbox" id="size" name="size" value="9" ${filter.size9}> 44<br />
				<input type="checkbox" id="size" name="size" value="10" ${filter.size10}> 45<br />
  			</fieldset> 
  			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 116 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			
<%-- 			<p><label for="color">Farbe</label>
			<input id="color" name="color" value="<jsp:getProperty property="color" name="article"/>" type="text" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 120 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>		 --%>
			
			<p><label for="color" class="artikeldaten">Farbe:</label> </p>
			<fieldset>
          		<input type="checkbox" id="color" name="color" value="1" ${filter.color1}> Gelb<br />
           		<input type="checkbox" id="color" name="color" value="2" ${filter.color2}> Orange<br />
          		<input type="checkbox" id="color" name="color" value="3" ${filter.color3}> Rot<br />
          		<input type="checkbox" id="color" name="color" value="4" ${filter.color4}> Pink<br />
          		<input type="checkbox" id="color" name="color" value="5" ${filter.color5}> Grün<br />
          		<input type="checkbox" id="color" name="color" value="6" ${filter.color6}> Blau<br />
          		<input type="checkbox" id="color" name="color" value="7" ${filter.color7}> Schwarz<br />
          		<input type="checkbox" id="color" name="color" value="8" ${filter.color8}> Weiß<br />
  			</fieldset>
  			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 120 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	
			
<%--  			<p><label for="manufacturer">Hersteller</label> 
 			<select id="manufacturer" name="manufacturer"><option><jsp:getProperty property="manufacturer" name="article"/></option>
 			<c:if test="${article.manufacturer == 'Nike'}">
 			 	<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
 				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option>
 			</c:if>
  			<c:if test="${article.manufacturer == 'Asics'}">
 			 	<option value="Nike">Nike</option>
 				<option value="Kempa">Kempa</option>
 				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option>
 			</c:if>			
   			<c:if test="${article.manufacturer == 'Kempa'}">
 			 	<option value="Nike">Nike</option>
 				<option value="Asics">Asics</option>
 				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option>
 			</c:if>				
    		<c:if test="${article.manufacturer == 'Hummel'}">
 			 	<option value="Nike">Nike</option>
 				<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option>
 			</c:if>		
    		<c:if test="${article.manufacturer == 'Adidas'}">
 			 	<option value="Nike">Nike</option>
 				<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
				<option value="Hummel">Hummel</option>
				<option value="Puma">Puma</option>
 			</c:if>	 			
     		<c:if test="${article.manufacturer == 'Puma'}">
 			 	<option value="Nike">Nike</option>
 				<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
 			</c:if>	 
 			</select>				
			</p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 119 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if> --%>
			
			<p><label for="manufacturer">Hersteller:</label>
			<select id="manufacturer" name="manufacturer">
 			<c:if test="${article.manufacturer == 'Nike'}">
 				<option selected value="Nike">Nike</option>
 			 	<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
 				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option>
 			</c:if>
 			 <c:if test="${article.manufacturer == 'Asics'}">
 				<option value="Nike">Nike</option>
 			 	<option selected value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
 				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option>
 			</c:if>
 			<c:if test="${article.manufacturer == 'Kempa'}">
 				<option value="Nike">Nike</option>
 			 	<option value="Asics">Asics</option>
 				<option selected value="Kempa">Kempa</option>
 				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option>
 			</c:if>
 			<c:if test="${article.manufacturer == 'Hummel'}">
 				<option value="Nike">Nike</option>
 			 	<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
 				<option selected value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option value="Puma">Puma</option>
 			</c:if>
 			<c:if test="${article.manufacturer == 'Adidas'}">
 				<option value="Nike">Nike</option>
 			 	<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
 				<option value="Hummel">Hummel</option>
				<option selected value="Adidas">Adidas</option>
				<option value="Puma">Puma</option>
 			</c:if>
 			<c:if test="${article.manufacturer == 'Puma'}">
 				<option value="Nike">Nike</option>
 			 	<option value="Asics">Asics</option>
 				<option value="Kempa">Kempa</option>
 				<option value="Hummel">Hummel</option>
				<option value="Adidas">Adidas</option>
				<option selected value="Puma">Puma</option>
 			</c:if>
			</select>
			</p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 119 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
		
<%-- 		<p><label for="category">Kategorie</label> 
			<select id="category" name="category"><option><jsp:getProperty property="category" name="article"/></option>
			<c:if test="${article.category == 'Herren'}">
 			 	<option value="Damen">Damen</option>
 				<option value="Kinder">Kinder</option>
 			</c:if>	
 			<c:if test="${article.category == 'Damen'}">
 			 	<option value="Herren">Herren</option>
 				<option value="Kinder">Kinder</option>
 			</c:if>	
 			 <c:if test="${article.category == 'Kinder'}">
 			 	<option value="Herren">Herren</option>
 				<option value="Damen">Damen</option>
 			</c:if>	
			</select>
			</p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 121 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if> --%>
			
			<p><label for="category">Kategorie:</label>
			<select id="categorie" name="category">
				<c:if test="${article.category == 'Herren'}">
					<option selected value="Herren">Herren</option>
					<option value="Damen">Damen</option>
 					<option value="Kinder">Kinder</option>
				</c:if>
				<c:if test="${article.category == 'Damen'}">
					<option value="Herren">Herren</option>
					<option selected value="Damen">Damen</option>
 					<option value="Kinder">Kinder</option>
				</c:if>
				<c:if test="${article.category == 'Kinder'}">
					<option value="Herren">Herren</option>
					<option value="Damen">Damen</option>
 					<option selected value="Kinder">Kinder</option>
				</c:if>
			</select>
			</p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 121 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			
<%-- 			<p><label for="sport">Sportart</label> 
 			<select id="sport" name="sport"><option><jsp:getProperty property="sport" name="article"/></option>
 			 <c:if test="${article.sport == 'Laufen'}">
 			 	<option value="Fussball">Fu&szligball</option>
 				<option value="Basketball">Basketball</option>
 				<option value="Golf">Golf</option>
				<option value="Schwimmen">Schwimmen</option>
				<option value="Fahrrad">Fahrrad</option>
 			</c:if>
 			<c:if test="${article.sport == 'Fussball'}">
 			 	<option value="Laufen">Laufen</option>
 				<option value="Basketball">Basketball</option>
 				<option value="Golf">Golf</option>
				<option value="Schwimmen">Schwimmen</option>
				<option value="Fahrrad">Fahrrad</option>
 			</c:if>
 			 <c:if test="${article.sport == 'Basketball'}">
 			 	<option value="Laufen">Laufen</option>
 				<option value="Fussball">Fu&szligball</option>
 				<option value="Golf">Golf</option>
				<option value="Schwimmen">Schwimmen</option>
				<option value="Fahrrad">Fahrrad</option>
 			</c:if>
 			<c:if test="${article.sport == 'Golf'}">
 			 	<option value="Laufen">Laufen</option>
 				<option value="Fussball">Fu&szligball</option>
 				<option value="Basketball">Basketball</option>
				<option value="Schwimmen">Schwimmen</option>
				<option value="Fahrrad">Fahrrad</option>
 			</c:if>
 			<c:if test="${article.sport == 'Schwimmen'}">
 			 	<option value="Laufen">Laufen</option>
 				<option value="Fussball">Fu&szligball</option>
 				<option value="Basketball">Basketball</option>
				<option value="Golf">Golf</option>
				<option value="Fahrrad">Fahrrad</option>
 			</c:if>
 			 <c:if test="${article.sport == 'Fahrrad'}">
 			 	<option value="Laufen">Laufen</option>
 				<option value="Fussball">Fu&szligball</option>
 				<option value="Basketball">Basketball</option>
				<option value="Golf">Golf</option>
				<option value="Schwimmen">Schwimmen</option>
 			</c:if>
			</select>
			</p>	
 			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 123 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	 --%>
				 			
			<p><label for="sport">Sportart:</label> 
			<select id="sport" name="sport">
				<c:if test="${article.sport == 'Laufen'}">
					<option selected value="Laufen">Laufen</option>
				 	<option value="Fussball">Fu&szligball</option>
 					<option value="Basketball">Basketball</option>
 					<option value="Golf">Golf</option>
					<option value="Schwimmen">Schwimmen</option>
					<option value="Fahrrad">Fahrrad</option>
				</c:if>
				<c:if test="${article.sport == 'Fussball'}">
					<option value="Laufen">Laufen</option>
				 	<option selected value="Fussball">Fu&szligball</option>
 					<option value="Basketball">Basketball</option>
 					<option value="Golf">Golf</option>
					<option value="Schwimmen">Schwimmen</option>
					<option value="Fahrrad">Fahrrad</option>
				</c:if>
				<c:if test="${article.sport == 'Basketball'}">
					<option value="Laufen">Laufen</option>
				 	<option value="Fussball">Fu&szligball</option>
 					<option selected value="Basketball">Basketball</option>
 					<option value="Golf">Golf</option>
					<option value="Schwimmen">Schwimmen</option>
					<option value="Fahrrad">Fahrrad</option>
				</c:if>
				<c:if test="${article.sport == 'Golf'}">
					<option value="Laufen">Laufen</option>
				 	<option value="Fussball">Fu&szligball</option>
 					<option value="Basketball">Basketball</option>
 					<option selected value="Golf">Golf</option>
					<option value="Schwimmen">Schwimmen</option>
					<option value="Fahrrad">Fahrrad</option>
				</c:if>
				<c:if test="${article.sport == 'Schwimmen'}">
					<option value="Laufen">Laufen</option>
				 	<option value="Fussball">Fu&szligball</option>
 					<option value="Basketball">Basketball</option>
 					<option value="Golf">Golf</option>
					<option selected value="Schwimmen">Schwimmen</option>
					<option value="Fahrrad">Fahrrad</option>
				</c:if>
				<c:if test="${article.sport == 'Fahrrad'}">
					<option value="Laufen">Laufen</option>
				 	<option value="Fussball">Fu&szligball</option>
 					<option value="Basketball">Basketball</option>
 					<option value="Golf">Golf</option>
					<option value="Schwimmen">Schwimmen</option>
					<option selected value="Fahrrad">Fahrrad</option>
				</c:if>
			</select>	
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 123 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if> 				 			
				 				
			<p><label for="mainImage">Bild:</label>
			<input id="mainImage" name="mainImage" value="<jsp:getProperty property="mainImage" name="article"/>" type="file"/></p>
<%-- 		<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 124 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	 --%>
			
			<p><label for="description">Beschreibung:</label>
			<textarea id="description" name="description" cols=37><jsp:getProperty property="description" name="article"/></textarea>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 118 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
		</div>
		
		<c:if test ="${not empty error }">
			<c:if test="${error.errorCode == 126 }">
				<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
			</c:if>	
		</c:if>
		<c:if test ="${not empty success }">
			<c:if test="${success.errorCode == 602 }">
				<p class="erfolg"><jsp:getProperty property="errorMessage" name="success"/></p>
			</c:if>	
		</c:if>
		
		<hr />
		<center> 
			<input type="submit" id="button" value="Speichern" />
		</center> 
		</form>
		<center>
		<form action="Artikelsuche.jsp"> 
			<input type="submit" id="button" value="Abbrechen" />
		</form> 
		</center>
		 
	</div>
<%@ include file="Footer.jspf" %>
