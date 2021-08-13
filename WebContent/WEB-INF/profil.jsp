<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%@include file="import/import.jsp" %>
		<title>Mon profil</title>
	</head>
	<body>
	<%@include file="import/header.jsp" %>
	
		<h2 class="text-center mt-5">Profil de ${user.getPseudo()}</h2>
		
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col pt-5 text-center">
					<p>Pseudo : ${user.getPseudo()}</p>
					<p>Nom : ${user.getNom()}</p>
					<p>Prénom : ${user.getPrenom()}</p>
					<p>Email : ${user.getEmail()}</p>
					<p>Téléphone : ${user.getTelephone()}</p>
					<p>Rue : ${user.getRue()}</p>
					<p>Code Postal : ${user.getCodePostal()}</p>
					<p>Ville : ${user.getVille()}</p>
					
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/ModifierProfilServlet">Modifier</a>				
				</div>
			</div>
		</div>

	</body>
</html>