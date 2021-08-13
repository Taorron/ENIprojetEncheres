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
	<c:choose>
	    <c:when test="${cookie['seSouvenir'].getValue().equals('on')}">
	        <input name="seSouvenir" type="checkbox" checked="checked">
	    </c:when>
	    <c:otherwise>
	        <input name="seSouvenir" type="checkbox">
	    </c:otherwise>
	</c:choose>
</form>
<div>Se souvenir de moi</div>
<a href="#">Mot de passe oublié</a>
<button class="btn btn-primary">Créer un compte</button>
 <c:if test="${erreur!=null}">
 	<div class="alert alert-danger" role="alert">${erreur}</div>
 
 </c:if>

</body>
</html>