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
		<title>Mon profil</title>
	</head>
	<body>
	<%@include file="import/header.jsp" %>
		<c:choose> 
			<c:when test="${seller!=null}">
				<h2 class="text-center mt-5">Profil de ${seller.getPseudo()}</h2>
				
				<div class="container">
					<div class="row justify-content-md-center">
						<div class="col pt-5 text-center">
							<p>Pseudo : ${seller.getPseudo()}</p>
							<p>Nom : ${seller.getNom()}</p>
							<p>Prénom : ${seller.getPrenom()}</p>
							<p>Email : ${seller.getEmail()}</p>
							<p>Téléphone : ${seller.getTelephone()}</p>
							<p>Rue : ${seller.getRue()}</p>
							<p>Code Postal : ${seller.getCodePostal()}</p>
							<p>Ville : ${seller.getVille()}</p>
							
							<c:if test="${canEdit == true}">
								<a class="btn btn-primary" href="${pageContext.request.contextPath}/ModifierProfilServlet">Modifier</a>
							</c:if>
											
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<h2 class="text-center mt-5">Aucun profil séléctionné</h2>
			</c:otherwise>
		</c:choose>
	</body>
</html>