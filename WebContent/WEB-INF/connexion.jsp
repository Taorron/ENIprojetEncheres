<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="import/import.jsp" %>
<title>Encheres connection</title>
</head>
<body>
<div>ENI-Encheres</div>
<form action="ConnexionUtilisateurServlet" method="post">
	<div>Identifiant : </div>

	<input class="form-control" type="text" name="identifiant" value="${cookie['identifiant'].getValue()}">
	<div>Mot de passe : </div>
	<input class="form-control" type="text" name="mdp" value="${cookie['mdp'].getValue()}">
	<button class="btn btn-primary">Connexion</button>
</form>
<input type="checkbox">
<div>Se souvenir de moi</div>
<a href="#">Mot de passe oublié</a>
<form action="${pageContext.request.contextPath}/CreateUserServlet" method="get">
	<input class="btn btn-primary" type=submit value="Créer un compte">
</form>

 <c:if test="${erreur!=null}">
 	<div class="alert alert-danger" role="alert">${erreur}</div>
 
 </c:if>

</body>
</html>