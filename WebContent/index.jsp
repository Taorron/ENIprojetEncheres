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
<%@include file="WEB-INF/import/header.jsp" %>
	<h2 class="text-center mt-5">Liste des enchères</h2>
	
	<div class="container mt-5">
	<form method="get" action="#">
		<div class="row">
				<div class="col-lg">
				  <div class="form-group">
				    <label for="inputResherch">filtres : </label>
				    <input type="email" class="form-control" id="inputResherch" name ="inputResherch" aria-describedby="emailHelp" placeholder="Le nom de l'article contient">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  	<br>
				  	<label for=category-select>Categorie : </label>
				  	<select name="category" id="category-select">
						<c:forEach var="categorie" items="${categories}">
							<option value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
						</c:forEach>
					</select>
				  </div>
				  
				  <div class="form-check">
				    <c:if test="${user!=null}">
					    <div class="row">
					    	<div class="col">
						    	<input type="radio"><label>Achats</label><br>
								<input type="checkbox"><label> enchères ouvertes</label><br>
								<input type="checkbox"><label>mes enchères en cours</label><br>
								<input type="checkbox"><label>mes enchères remportées</label><br>
					    	</div>
					    	
					    	<div class="col">
						    	<input type="radio"><label>Mes ventes</label><br>
								<input type="checkbox"><label>mes ventes en cours</label><br>
								<input type="checkbox"><label>ventes non débutées</label><br>
								<input type="checkbox"><label>ventes terminées</label><br>
					    	</div>
					    
					    </div>
					</c:if>
				  </div>
				  	
				  
				</div>
			
			<div class="col-lg d-flex align-self-center justify-content-center">
		
				<input class="btn btn-primary" type="submit" value="Rechercher">
				
			</div>
		</div>
		</form>
		
		
	</div>
	
	
</body>
</html>