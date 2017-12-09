<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rollenbearbeitung</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="DesignV1.css" rel="stylesheet">
</head>
<body>
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 	
	<jsp:useBean id="roleList" type="java.util.ArrayList<r4f.model.Role>" class="java.util.ArrayList" scope="request">
	</jsp:useBean> 	
	
	<div id="container">
		<h2>Rollenbearbeitung</h2>
		<form action="./" method="post">
			<div id="inhalt">
				<h4>Neue Rolle anlegen</h4>
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
			<br />
			<div id="inhalt">
				<h4>Rollen ändern</h4>
				<c:forEach items="${roleList}" var="role">
					<form action="./" method="post">
						<p><input id="name" name="name" value="<jsp:getProperty property="name" name="role"/>" type="text" /><br /></p>
						<c:if test ="${not empty error }">
							<c:if test="${error.errorCode == 138 }">
								<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
							</c:if>	
						</c:if>
						<textarea id="description" name="description"><jsp:getProperty property="description" name="role"/></textarea>
						<c:if test ="${not empty error }">
							<c:if test="${error.errorCode == 118 }">
								<p class="fehler"><jsp:getProperty property="errorMessage" name="error"/></p>
							</c:if>	
						</c:if>
					<button><i class="fa fa-floppy-o"></i></button>
					</form>
	
				</c:forEach>
			</div>
		</form>
	</div>
</body>
</html>