<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="import/import.jsp" %>
<title>Encheres connection</title>
</head>
<body>
<div>ENI-Encheres</div>
<form action="#" method="post">
	<div>Identifiant : </div>
	<input class="form-control" type="text" name="identifiant">
	<div>Mot de passe : </div>
	<input class="form-control" type="text" name="mdp">
	<button class="btn btn-primary">Connexion</button>
</form>
<input type="checkbox">
<div>Se souvenir de moi</div>
<a href="#">Mot de passe oublié</a>
<button class="btn btn-primary">Créer un compte</button>

</body>
</html>