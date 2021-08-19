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
<%@include file="import/header.jsp" %>

	 <c:if test="${erreur!=null}">
		 <div class="alert alert-warning alert-dismissible fade show" role="alert">
	 		 ${erreur}
	  		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	   		 <span aria-hidden="true">&times;</span>
			</button>
		</div>
	 </c:if>
	 
	 <c:if test="${result != null}">
	 	<div class="alert alert-info alert-dismissible fade show" role="info">
	 		 ${result}
	  		<button type="button" class="close" data-dismiss="info" aria-label="Close">
	   		 <span aria-hidden="true">&times;</span>
			</button>
		</div>
	 </c:if>
	 
	 
	<h2 class="text-center mt-5">Connexion</h2>
	
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-md-5 pt-5">
				<form action="ConnexionUtilisateurServlet" method="post">
				  <div class="form-group">
				    <label for="identifiant">Identifiant</label>
				    <input type="text" class="form-control" id="identifiant" name="identifiant" value="${cookie['identifiant'].getValue()}" aria-describedby="identifiantHelp" placeholder="Pseudo / email">
				    <small id="identifiantHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  <div class="form-group">
				    <label for="mdp">Mot de passe</label>
				    <input type="password" class="form-control" name="mdp" id="mdp" placeholder="Mot de passe" value="${cookie['mdp'].getValue()}">
					<a href="#">Mot de passe oublié</a>		  
				  </div>
				  <div class="form-check">
				  <label class="form-check-label" for="seSouvenir">Se souvenir de moi</label>
					  <c:choose>
					    <c:when test="${cookie['seSouvenir'].getValue().equals('on')}">
					        <input class="form-check-input" id="seSouvenir" name="seSouvenir" type="checkbox" checked="checked">
					    </c:when>
					    <c:otherwise>
					        <input name="seSouvenir" type="checkbox" id="seSouvenir" name="seSouvenir">
					    </c:otherwise>
					 </c:choose>
		
				  </div>
				  <button type="submit" class="btn btn-primary">Se connecter</button>
				</form>
		
				<!-- Create Account -->
				<form class="mt-5" action="${pageContext.request.contextPath}/CreateUserServlet" method="get">
					<input class="btn btn-primary" type=submit value="Créer un compte">
				</form>
			</div>
		</div>
	</div>

</body>
</html>