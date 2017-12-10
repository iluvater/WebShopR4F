<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
<body> --%>
<%@ include file="Head.jspf" %>
<title>Rollenbearbeitung</title>
<%@ include file="Header.jspf" %>

	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 	
	<jsp:useBean id="roleList" type="java.util.ArrayList<r4f.model.Role>" class="java.util.ArrayList" scope="request">
	</jsp:useBean> 	
	
	<div id="container">
		<h2 class="ueberschrift">Rollenbearbeitung</h2>
		<form action="./CreateRoleServlet" method="post">
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
					<!-- <input type="submit" class="fa fa-floppy-o input-group-addon " value="Speichern" /> -->
					<button><i class="fa fa-floppy-o"></i></button> 
					</form>
	
				</c:forEach>
			</div>
		</form>
	</div>
<%@ include file="Footer.jspf" %>