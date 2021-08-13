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
	<c:choose>
	    <c:when test="${user!=null}">
	       	<a href="#">Enchères</a>
			<a href="#">Vendre un article</a>
			<a href="${pageContext.request.contextPath}/VoirProfilServlet">Mon profil</a>
			<a href="${pageContext.request.contextPath}/IndexServlet">Déconnexion</a>
	    </c:when>
	    <c:otherwise>
	        <a href="${pageContext.request.contextPath}/ConnexionServlet">S'inscrire - Se connecter</a>
	    </c:otherwise>
	</c:choose>
	<%-- <a href="${pageContext.request.contextPath}/ConnexionServlet">S'inscrire - Se connecter</a>
	
	<a href="#">Enchères</a>
	<a href="#">Vendre un article</a>
	<a href="${pageContext.request.contextPath}/VoirProfilServlet">Mon profil</a>
	<a href="#">Déconnexion</a> --%>

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
	
	<c:if test="${user!=null}">
		<input type="radio"><label>Achats</label>
		<input type="checkbox"><label> enchères ouvertes</label>
		<input type="checkbox"><label>mes enchères en cours</label>
		<input type="checkbox"><label>mes enchères remportées</label>
		
		<input type="radio"><label>Mes ventes</label>
		<input type="checkbox"><label>mes ventes en cours</label>
		<input type="checkbox"><label>ventes non débutées</label>
		<input type="checkbox"><label>ventes terminées</label>
	</c:if>
</body>
</html>