<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Encheres</title>
</head>
<body>
	<div>ENI-Encheres</div>
	<a href="${pageContext.request.contextPath}/ConnexionServlet">S'inscrire - Se connecter</a>

	<div>Liste des enchères</div>
	<div>Filtres : </div>
	<input type="text" placeholder="Le nom de l'article contient">
	<input type="button" value="Rechercher">
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
	
</body>
</html>