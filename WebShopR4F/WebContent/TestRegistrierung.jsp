<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	


<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="error" class="r4f.model.ErrorMessage" scope="request">
	</jsp:useBean> 
	
	<form action="./RegistrierungsServlet" method="post">
		<table>
			<tr>
				<td>Anrede</td>
				<td><select name="anrede"><option value="Herr">Herr</option>
						<option value="Frau">Frau</option></select></td>
			</tr>
			<tr>
				<td>Vorname</td>
				<td><input name="vorname" type="text" value=""></td>
			</tr>
			<tr>
				<td>Nachname</td>
				<td><input name="nachname" type="text" value=""></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input name="email" type="email" value=""></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input name="password" type="password" value=""></td>
			</tr>
			<tr>
				<td>Geburtstdatum</td>
				<td><input name="geburtstdatum" type="date" value=""></td>
			</tr>
			<tr>
				<td>Straße</td>
				<td><input name="strasse" type="text" value=""></td>
			</tr>
			<tr>
				<td>Hausnummer</td>
				<td><input name="hausnummer" type="text" value=""></td>
			</tr>
			<tr>
				<td>Postleizahl</td>
				<td><input name="postleitzahl" type="text" value=""></td>
			</tr>
			<tr>
				<td>Stadt</td>
				<td><input name="stadt" type="text" value=""></td>
			</tr>
		</table>
		<br> <input type="submit" value="Registrieren" class="button">
	</form>
	<%if(error.getErrorMessage()!=null){ %>
	<jsp:getProperty property="errorMessage" name="error"/>
	<%} %>
</body>
</html>