<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://run4fun.de/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Artikeldaten</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="DesignV1.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
</head>
<body>
	<jsp:useBean id="article" class="r4f.model.Article" scope="request">
	</jsp:useBean> 
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 

	<div id="container">
		<h2>Artikeldatenänderung</h2>
		<h3>Bitte im Folgenden die Artikeldaten anpassen</h3>
		<form action="./ChangeArticleServlet" method="post" enctype="multipart/form-data">
		<div id="inhalt">
			<h4>Artikeldaten ändern</h4>
				
			<p><label for="id">ID:</label>
			<input id="id" name="id" value="<jsp:getProperty property="id" name="article"/>" type="text" disabled/><br /></p>
			<input type="hidden" name="id" value="${article.id }" />
				
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
			<p><label for="size">Gr&ouml;&szlige</label>
			<input id="size" name="size" min="20" value="<jsp:getProperty property="size" name="article"/>" type="number" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 116 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>
			<p><label for="color">Farbe</label>
			<input id="color" name="color" value="<jsp:getProperty property="color" name="article"/>" type="text" /></p>
			<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 120 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>			
 			<p><label for="manufacturer">Hersteller</label> 
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
			</c:if>
	 		<p><label for="category">Kategorie</label> 
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
			</c:if>
			<p><label for="sport">Sportart</label> 
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
			</c:if>		 				
			<p><label for="image">Bild</label>
			<input id="image" name="image" value="<jsp:getProperty property="image" name="article"/>" type="file"/></p>
<%-- 		<c:if test ="${not empty error }">
				<c:if test="${error.errorCode == 124 }">
					<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
				</c:if>	
			</c:if>	 --%>
			<p><label for="description">Beschreibung</label>
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
		<hr />
		<center> 
		<input type="submit" id="button" value="Speichern" />
		</center> 
		</form>
		<center>
		<%-- <form> --%>
		<input type="submit" id="button" value="Abbrechen" />
		<%-- </form> --%>
		</center>
		 
	</div>
</body>
</html>
