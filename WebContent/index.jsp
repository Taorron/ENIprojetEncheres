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
							    	<input id="achats" type="radio"><label>Achats</label><br>
									<input id="encheresOuvertes" type="checkbox"><label> enchères ouvertes</label><br>
									<input id="mesEncheresEnCours" type="checkbox"><label>mes enchères en cours</label><br>
									<input id="mesEncheresRemportees" type="checkbox"><label>mes enchères remportées</label><br>
						    	</div>
						    	
						    	<div class="col">
							    	<input id="ventes" type="radio"><label>Mes ventes</label><br>
									<input id="mesVentesEnCours" type="checkbox"><label>mes ventes en cours</label><br>
									<input id="ventesNonDebute" type="checkbox"><label>ventes non débutées</label><br>
									<input id="ventesTerminees" type="checkbox"><label>ventes terminées</label><br>
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
		
		<div class="row justify-content-center">

	 		<div class="card mb-4 m-1" style="max-width: 500px;">
			  <div class="row no-gutters">
			    <div class="col-md-4">
			      <img src="https://via.placeholder.com/150" alt="img">
			    </div>
			    <div class="col-md-6">
			      <div class="card-body">
			        <h5 class="card-title"><a href="#" class="">Fauteuil</a></h5>
			        <p class="card-text">Prix : 30 points</p>
			        <p class="card-text">Date fin : 01/01/2220</p>
			        <p class="card-text"><a href="#" class="">Jean-pierre</a></p>
			      </div>
			    </div>
			  </div>
			</div>
			
			<div class="card mb-4 m-1" style="max-width: 500px;">
			  <div class="row no-gutters">
			    <div class="col-md-4">
			      <img src="https://via.placeholder.com/150" alt="img">
			    </div>
			    <div class="col-md-6">
			      <div class="card-body">
			        <h5 class="card-title"><a href="#" class="">Fauteuil</a></h5>
			        <p class="card-text">Prix : 30 points</p>
			        <p class="card-text">Date fin : 01/01/2220</p>
			        <p class="card-text"><a href="#" class="">Jean-pierre</a></p>
			      </div>
			    </div>
			  </div>
			</div>
			
			<div class="card mb-4 m-1" style="max-width: 500px;">
			  <div class="row no-gutters">
			    <div class="col-md-4">
			      <img src="https://via.placeholder.com/150" alt="img">
			    </div>
			    <div class="col-md-6">
			      <div class="card-body">
			        <h5 class="card-title"><a href="#" class="">Fauteuil</a></h5>
			        <p class="card-text">Prix : 30 points</p>
			        <p class="card-text">Date fin : 01/01/2220</p>
			        <p class="card-text"><a href="#" class="">Jean-pierre</a></p>
			      </div>
			    </div>
			  </div>
			</div>

		</div>
	</div>
	
	<script type="text/javascript">
	$( "#achats" ).click(function() {
		
		$( "#encheresOuvertes" ).removeAttr("disabled");
		$( "#mesEncheresEnCours" ).removeAttr("disabled");
		$( "#mesEncheresRemportees" ).removeAttr("disabled");
		
		if ($( "#achats" ).prop("checked") == true) 
		{
			$( "#ventes" ).prop("checked",false);
			
			$( "#mesVentesEnCours" ).prop("checked",false);
			$( "#mesVentesEnCours" ).attr("disabled", "disabled");
			
			$( "#ventesNonDebute" ).prop("checked",false);
			$( "#ventesNonDebute" ).attr("disabled", "disabled");
			
			$( "#ventesTerminees" ).prop("checked",false);
			$( "#ventesTerminees" ).attr("disabled", "disabled");
		}
	})
	
	$( "#ventes" ).click(function() {
		
		$( "#mesVentesEnCours" ).removeAttr("disabled");
		$( "#ventesNonDebute" ).removeAttr("disabled");
		$( "#ventesTerminees" ).removeAttr("disabled");
		
		if ($( "#ventes" ).prop("checked") == true) 
		{
			$( "#achats" ).prop("checked",false);
			
			$( "#encheresOuvertes" ).prop("checked",false);
			$( "#encheresOuvertes" ).attr("disabled", "disabled");
			
			$( "#mesEncheresEnCours" ).prop("checked",false);
			$( "#mesEncheresEnCours" ).attr("disabled", "disabled");
			
			$( "#mesEncheresRemportees" ).prop("checked",false);
			$( "#mesEncheresRemportees" ).attr("disabled", "disabled");
		}
	})
	/*  if () 
	 {
		 $( "#vente" ).attr("disabled", "disabled");
	}
	 if () 
	 {
		 $( "#achat" ).attr("disabled", "disabled");
	} */
	
 	/* document.getElementById("vente").disabled = true; */
		

	</script>
</body>
</html>