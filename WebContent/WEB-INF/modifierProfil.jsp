<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%@include file="import/import.jsp" %>
		<title>Modifier son profil</title>
	</head>
	<body>
		<%@include file="import/header.jsp" %>
		
		<h2 class="text-center mt-5">Modifier votre profil </h2>
		<div class="container">
			<div class="row">
				<div class="col pt-5">
					<form method="post" action="${pageContext.request.contextPath}/ValidationModifProfilServlet">
						
						<table class="ml-auto mr-auto">
							<tr>
								<td> <label for="pseudo">Pseudo </label> </td>
								<td> <input id="pseudo" name="pseudo" type="text" value="${user.getPseudo()}" placeholder="Pseudo"> </td>
							</tr>
							
							<tr>
								<td><label for="email">Email </label></td>
								<td><input id="email" name="email" type="email" value="${user.getEmail()}" placeholder="jesuis@tropfort.dieu"></td>
							</tr>
							
							<tr>
								<td><label for="nom"> Nom </label> </td>
								<td><input id="nom" name="nom" type="text" value="${user.getNom()}"> </td>
							</tr>
							
							<tr>
								<td><label for="prenom"> Prenom </label> </td>
								<td><input name="prenom" type="text" value="${user.getPrenom()}"> </td>
							</tr>
							
							<tr>
								<td><label for="telephone"> Téléphone </label> </td>
								<td><input name="telephone" type="text" value="${user.getTelephone()}"> </td>
							</tr>
							
							<tr>
								<td><label for="rue"> Rue </label> </td>
								<td><input name="rue" type="text" value="${user.getRue()}"> </td>
							</tr>
							
							<tr>
								<td><label for="codePostal"> Code Postal </label> </td>
								<td><input name="codePostal" type="text" value="${user.getCodePostal()}"> </td>
							</tr>
							
							<tr>
								<td><label for="ville"> Ville </label> </td>
								<td><input name="ville" type="text" value="${user.getVille()}"> </td>
							</tr>
							
							<tr>
								<td><label for="mdpActuel"> Mot de passe actuel </label> </td>
								<td><input name="mdpActuel" type="password"> </td>
							</tr>
							
							<tr>
								<td><label for="nouveauMdp"> Nouveau mot de passe </label> </td>
								<td><input name="nouveauMdp" type="text"> </td>
							</tr>
							
							<tr>
								<td><label for="confirmationNouveauMdp"> Confirmation MDP  </label> </td>
								<td><input name="confirmationNouveauMdp" type="text"> </td>
							</tr>
							
							<tr>
								<td><label for="prenom"> Crédit </label> </td>
								<td> ${user.getCredit()} </td>
							</tr>
							
							<tr>
								<td class=> <input class="btn btn-primary" type="submit" value="Enregistrer">  </td>
							</tr>
						</table>
					</form>				
				</div>
				
				<p class="mt-5 text-center">Autre option :<br>
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/SuppUserServlet">
						Supprimer mon compte
					</a>
				</p>

				
			</div>
		</div>
		
		
		
		<%-- <input type="button" value="Modifier" onclick="openWindow('${pageContext.request.contextPath}'/ModifierProfilServlet)"> --%>
	</body>
</html>