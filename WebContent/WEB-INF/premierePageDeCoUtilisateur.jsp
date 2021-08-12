<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%

	String user = (String)session.getAttribute("user");
	
%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>ENI-Encheres</div>
	<a href="#">Enchères</a>
	<a href="#">Vendre un article</a>
	<a href="${pageContext.request.contextPath}/VoirProfilServlet">Mon profil</a>
	<a href="#">Déconnexion</a>

	<div>Liste des enchères</div>
	<div>Filtres : </div>
	<input class="form-control" type="text" placeholder="Le nom de l'article contient">
	<div>Catégorie : </div>
	<select name="pets" id="pet-select">
	    <option value="">--Please choose an option--</option>
	    <option value="dog">Dog</option>
	    <option value="cat">Cat</option>
	    <option value="hamster">Hamster</option>
	    <option value="parrot">Parrot</option>
	    <option value="spider">Spider</option>
	    <option value="goldfish">Goldfish</option>
	</select>
	<input class="btn btn-primary" type="button" value="Rechercher">
	
	<input type="radio"><label>Achats</label>
	<input type="checkbox"><label> enchères ouvertes</label>
	<input type="checkbox"><label>mes enchères en cours</label>
	<input type="checkbox"><label>mes enchères remportées</label>
	
	<input type="radio"><label>Mes ventes</label>
	<input type="checkbox"><label>mes ventes en cours</label>
	<input type="checkbox"><label>ventes non débutées</label>
	<input type="checkbox"><label>ventes terminées</label>

</body>
</html>