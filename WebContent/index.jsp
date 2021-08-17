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
					    <input type="text" class="form-control" id="inputResherch" name ="inputResherch" aria-describedby="resherch" placeholder="Le nom de l'article contient">
					    <small id="resherch" class="form-text text-muted">We'll never share your email with anyone else.</small>
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
							    	<input id="achats" type="radio"><label for="achats"> Achats</label><br>
									<input id="encheresOuvertes" name="encheresOuvertes" type="checkbox"><label for="encheresOuvertes"> enchères ouvertes</label><br>
									<input id="mesEncheresEnCours" name="mesEncheresEnCours" type="checkbox"><label for="mesEncheresEnCours"> mes enchères en cours</label><br>
									<input id="mesEncheresRemportees" name="mesEncheresRemportees" type="checkbox"><label for="mesEncheresRemportees"> mes enchères remportées</label><br>
						    	</div>
						    	
						    	<div class="col">
							    	<input id="ventes" type="radio"><label for="ventes"> Mes ventes</label><br>
									<input id="mesVentesEnCours" name="mesVentesEnCours" type="checkbox"><label for="mesVentesEnCours">mes ventes en cours</label><br>
									<input id="ventesNonDebute" name="ventesNonDebute" type="checkbox"><label for="ventesNonDebute">ventes non débutées</label><br>
									<input id="ventesTerminees" name="ventesTerminees" type="checkbox"><label for="ventesTerminees">ventes terminées</label><br>
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
			
			<c:if test="${articles.isEmpty()}">
				<h3 class="text-center">Aucune enchère en cours</h3>
			</c:if>
					
			<c:forEach var="article" items="${articles}">
				<div class="card mb-4 m-1" style="max-width: 500px;">
				  <div class="row no-gutters">
				    <div class="col-md-4">
				      <img src="https://via.placeholder.com/150" alt="img">
				    </div>
				    <div class="col-md-6">
				      <div class="card-body">
				        <h5 class="card-title"><a id="${article.getNoArticle()}" href="${pageContext.request.contextPath}/AfficherVente?IdVente=${article.getNoArticle()}" class="">${article.getNomArticle()}</a></h5>
				        <p class="card-text">Prix : ${article.getPrixVente()} points</p>
				        <p class="card-text">Date fin : ${article.getDateFinEncheres()}</p>
				        <p class="card-text"><a href="${pageContext.request.contextPath}/VoirProfilServlet?sellerId=${article.getVendeur().getNoUtilisateur()}" class="">Vendeur : ${article.getVendeur().getPseudo()}</a></p>
				      </div>
				    </div>
				  </div>
				</div>		
			</c:forEach>
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