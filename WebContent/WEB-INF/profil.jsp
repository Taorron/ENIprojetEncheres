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
	<div>ENI-Encheres</div>
	<div>Pseudo :</div><div>${user.getPseudo()}</div>
	<div>Nom :</div><div>${user.getNom()}</div>
	<div>Prénom :</div><div>${user.getPrenom()}</div>
	<div>Email :</div><div>${user.getEmail()}</div>
	<div>Téléphone :</div><div>${user.getTelephone()}</div>
	<div>Rue :</div><div>${user.getRue()}</div>
	<div>Code Postal :</div><div>${user.getCodePostal()}</div>
	<div>Ville :</div><div>${user.getVille()}</div>


	<a href="${pageContext.request.contextPath}/ModifierProfilServlet"><input type="button" value="Modifier"></a>
</body>
</html>