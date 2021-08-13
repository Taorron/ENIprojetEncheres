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
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/ValidationModifProfilServlet">
		<div>ENI-Encheres</div>
		<div>Pseudo :</div><input name="pseudo" type="text" value="${user.getPseudo()}">
		<div>Nom :</div><input name="nom" type="text" value="${user.getNom()}">
		<div>Prénom :</div><input name="prenom" type="text" value="${user.getPrenom()}">
		<div>Email :</div><input name="email" type="text" value="${user.getEmail()}">
		<div>Téléphone :</div><input name="telephone" type="text" value="${user.getTelephone()}">
		<div>Rue :</div><input name="rue" type="text" value="${user.getRue()}">
		<div>Code Postal :</div><input name="codePostal" type="text" value="${user.getCodePostal()}">
		<div>Ville :</div><input name="ville" type="text" value="${user.getVille()}">
		<div>Mot de passe actuel :</div><input name="mdpActuel" type="text" value="${user.getMotDePasse()}">
		<div>Nouveau mot de passe :</div><input name="nouveauMdp" type="text">
		<div>Confirmation :</div><input name="confirmationNouveauMdp" type="text">
		<div>Crédit :</div><div>${user.getCredit()}</div> 
		
	<button type="submit">Enregistrer</button>
	</form>
	<a href="${pageContext.request.contextPath}/SuppUserServlet"><button type="button">Supprimer mon compte</button></a>
	<%-- <input type="button" value="Modifier" onclick="openWindow('${pageContext.request.contextPath}'/ModifierProfilServlet)"> --%>
</body>
</html>