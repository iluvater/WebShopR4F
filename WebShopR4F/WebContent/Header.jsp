<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://run4fun.de/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Header</title>
<link href="DesignV1.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<header>

	<jsp:useBean id="user" class="r4f.model.User" scope="session">
	</jsp:useBean> 

	<img src="LogoV2.jpg" id="logo" width="5%">
	
	<form class="header" action="./SearchServlet" method="get">
		<input class="header" id="search" name="search" value="" type="text" />
		<button type="button" id="buttonHeader" class="btn btn-default btn-sm btnPadding">
          <span class="glyphicon glyphicon-search"></span> 
        </button>
	</form>
	
         <button type="button" id="buttonHeader" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-heart-empty"></span> Merkliste
        </button>
        <button type="button" id="buttonHeader" class="btn btn-default btn-sm btnPadding">
          <span class="glyphicon glyphicon-shopping-cart"></span> Warenkorb
        </button> 
	
	<c:if test = "${empty user.email  }">
    	<a href="Benutzerauthentifizierung.jsp" id="buttonHeader" class="btn btn-default btn-sm">
        	<span class="glyphicon glyphicon-off"></span> Anmelden 
        </a>
	</c:if> 
	
	<c:if test = "${not empty user.email }">  
    	<a href="./LogoutServlet" id="buttonHeader" class="btn btn-default btn-sm">
        	<span class="glyphicon glyphicon-off"></span> Abmelden 
        </a>
    </c:if> 
    <hr />
    
    <h3>
     	<u><a class="header" href="Test.jsp">Herren</a></u>
    	<u><a class="header" href="Test.jsp">Damen</a></u>
    	<u><a class="header headerWide" href="Test.jsp">Kinder</a></u> 
   
<!--         <button type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-heart-empty"></span> Merkliste
        </button>
        <button type="button" class="btn btn-default btn-sm btnPaddingSm">
          <span class="glyphicon glyphicon-shopping-cart"></span> Warenkorb
        </button> -->
          
        <div class="dropdown">
  		<button type="button" id="buttonHeader" class="btn btn-default btn-sm btnPaddingSm">
          <span class="glyphicon glyphicon-user"></span> Mein Account
        </button>
  			<div class="dropdown-content">
    		<a href="Test.jsp">Daten ändern</a><br /><br />
    		<a href="Test.jsp">Meine Bestellungen</a>
  			</div>
		</div>
        
        <button type="button" id="buttonHeader" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-globe"></span> DE/EN
        </button>
    </h3>
</header>
</body>
</html>
