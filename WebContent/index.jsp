<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Encheres</title>
	<%@include file="WEB-INF/import/import.jsp" %>
</head>
<body>
	<div>ENI-Encheres</div>
	<a href="${pageContext.request.contextPath}/ConnexionServlet">S'inscrire - Se connecter</a>

	<div>Liste des enchères</div>
	<div>Filtres : </div>
	<input class="form-control" type="text" placeholder="Le nom de l'article contient">
	<input class="btn btn-primary" type="button" value="Rechercher">
	<div>Catégorie : </div>
	<select name="pets" id="pet-select">
		<c:forEach var="categorie" items="${categories}">
			<option value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
			
		</c:forEach>
	   
	</select>
	
</body>
</html>