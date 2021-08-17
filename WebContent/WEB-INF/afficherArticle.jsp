<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="import/import.jsp" %>
<%@ page import="bo.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%@include file="import/header.jsp" %>
		<h2 class="text-center mt-5">Nouvelle vente</h2>
		
		<div class="container">
			<div class="row">
				<div class="col pt-5">
					<form method="post" action="#">
						
						<table class="ml-auto mr-auto">
							<tr>
								<td> <label for="article">Article : </label> </td>
								<td><div class="" name="article" id="article">${article.getNomArticle() }</div></td>
							</tr>
							
							<tr>
								<td><label for="description">Description : </label></td>
								<td><div class="" name="description" id="description">${article.getDescription() }</div></td>
							</tr>
							
							<tr>
								<td><label for=category>Categorie : </label> </td>
								<td>
									<div class="" name="category" id="category">${article.getCategorie().getLibelle() }</div>
								</td>
							</tr>
							
							<tr>
								<td><label for="prix">Meilleure offre : </label></td>
								<td><div class="" name="prix" id="prix">${article.getPrixVente() } points</div> </td>
							</tr>
							
							<tr>
								<td><label for="prixDepart">Mise à prix : </label> </td>
								<td><div class="" name="prixDepart" id="prixDepart">${article.getMiseAPrix() } points</div>  </td>
							</tr>
							
							<tr>
								<td> <label for="dateDebut">Début de l'enchère : </label></td>
								<td><div class="" name="dateDebut" id="dateDebut">${article.getDateDebutEncheres()}</div></td>
							</tr>
							
							<tr>
								<td> <label for="dateFin">Fin de l'enchère : </label></td>
								<td><div class="" name="dateFin" id="dateFin">${article.getDateFinEncheres()}</div> </td>
							</tr>
							<tr>
								<td> <label for="retrait">Retrait : </label></td>
								<td><div class="" name="retrait" id="retrait">${article.getRetrait().getRue()} ${article.getRetrait().getCodePostal()} ${article.getRetrait().getVille()}</div> </td>
							</tr>
							<tr>
								<td> <label for="retrait">Vendeur : </label></td>
								<td><div class="" name="retrait" id="retrait"><a href="${pageContext.request.contextPath}/VoirProfilServlet?sellerId=${article.getVendeur().getNoUtilisateur()}">${article.getVendeur().getPseudo()}</a></div> </td>
							</tr>
							
							
						</table>

						<button class="btn btn-primary" type="submit">Enregistrer</button>
					</form>				
				</div>
				
			</div>
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/RedirectIndexServlet">
						Annuler
					</a>
		</div>
	</body>
</html>