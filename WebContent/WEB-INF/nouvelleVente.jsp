<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="import/import.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="import/header.jsp" %>

	<c:choose>
			<c:when test="${article!=null}">
				<h2 class="text-center mt-5">Modifier vente</h2>
			</c:when>
			<c:otherwise>
				<h2 class="text-center mt-5">Nouvelle vente</h2>
			</c:otherwise>
	</c:choose>
		
		<div class="container">
			<div class="row">
				<div class="col pt-5">
				
					<c:choose>
							<c:when test="${article!=null}">
								<form method="post" action="${pageContext.request.contextPath}/ModifierVenteServlet">
								<input type="hidden" name="id" id="id" value="${article.getNoArticle()}">
							</c:when>
							<c:otherwise>
								<form method="post" action="${pageContext.request.contextPath}/EnregistrerNouvelleVenteServlet">
							</c:otherwise>
					</c:choose>
					
						
						<table class="ml-auto mr-auto">
							<tr>
								<td> <label for="article">Article : </label> </td>
								<td> <input type="text" class="form-control" name="article" id="article" value="${article.getNomArticle() }"> </td>
							</tr>
							
							<tr>
								<td><label for="description">Description : </label></td>
								<td><input type="text" class="form-control" name="description" id="description" value="${article.getDescription() }"></td>
							</tr>
							
							<tr>
								<td><label for=category-select>Categorie : </label> </td>
								<td>
									<select name="category" id="category-select">
										<c:if test="${article.getCategorie().getLibelle()!=null}">
											<option value="${article.getCategorie().getNoCategorie()}">${article.getCategorie().getLibelle()}
										</c:if>
										<c:forEach var="categorie" items="${categories}">
											<c:if test="${categorie.getNoCategorie()!=article.getCategorie().getNoCategorie()}">
												<option value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
							
							<tr>
								<td><label for="photo">Photo de l'article : </label></td>
								<td><input type="text" class="form-control" name="photo" id="photo"> </td>
							</tr>
							
							<tr>
								<td><label for="prix">Mise à prix : </label> </td>
								<td><input type="number" class="form-control" name="prix" id="prix" value="${article.getMiseAPrix() }"> </td>
							</tr>
							
							<tr>
								<td> <label for="dateDebut">Début de l'enchère : </label></td>
								<td><input type="date" class="form-control" name="dateDebut" id="dateDebut" value="${article.getDateDebutEncheres()}"> </td>
							</tr>
							
							<tr>
								<td> <label for="dateFin">Fin de l'enchère : </label></td>
								<td><input type="date" class="form-control" name="dateFin" id="dateFin" value="${article.getDateFinEncheres()}"> </td>
							</tr>
							
							
						</table>
						
						
					      <div class="text-center mt-5">Retrait</div>				    
					  
						<table border=1 class="ml-auto mr-auto">
							<c:choose>
								<c:when test="${article.getRetrait().getRue()!=null}">
									<tr>
										<td><label for="rue">Rue : </label></td>
									    <td><input type="text" class="form-control" name="rue" id="rue" value="${article.getRetrait().getRue()}"></td>
								  	</tr>
									
									<tr>
									    <td><label for="cp">Code postal : </label></td>
									    <td><input type="text" class="form-control" name="cp" id="cp" value="${article.getRetrait().getCodePostal()}"></td>						  
									</tr>
										
									<tr>
									    <td> <label for="ville">Ville : </label></td>
										<td><input type="text" class="form-control" name="ville" id="ville" value="${article.getRetrait().getVille()}"></td>
									</tr>  
								</c:when>
								<c:otherwise>
									<tr>
										<td><label for="rue">Rue : </label></td>
									    <td><input type="text" class="form-control" name="rue" id="rue" value="${user.getRue()}"></td>
								  	</tr>
									
									<tr>
									    <td><label for="cp">Code postal : </label></td>
									    <td><input type="text" class="form-control" name="cp" id="cp" value="${user.getCodePostal()}"></td>						  
									</tr>
										
									<tr>
									    <td> <label for="ville">Ville : </label></td>
										<td><input type="text" class="form-control" name="ville" id="ville" value="${user.getVille()}"></td>
									</tr>  
								</c:otherwise>
							</c:choose>
						 </table>
						<c:choose>
							<c:when test="${article!=null}">
								<button class="btn btn-primary" type="submit">Modifier</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-primary" type="submit">Enregistrer</button>
							</c:otherwise>
						</c:choose>
					</form>				
				</div>
				
			</div>
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/IndexServlet">
						Annuler
					</a>
		</div>
	

		
		
	
					

</body>
</html>