<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Creation de compte</title>
		<%@include file="import/import.jsp" %>
	</head>
	<body>
		<form method="post" action="${pageContext.request.contextPath}/CreateUserServlet">
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="inputNickname">Pseudo</label>
		      <input type="text" class="form-control" name="inputNickname" id="inputNickname" placeholder="Pseudo">
		    </div>
		    <div class="form-group col-md-6">
		      <label for="inputMail">Email</label>
		      <input type="email" class="form-control" name="inputMail" id="inputMail" placeholder="jesuis@tropfort.dieu">
		    </div>
		  </div>
		  
		  <div class="form-group">
		  	<div class="form-group col-md-3">
		      <label for="inputPassword">Mot de passe</label>
		      <input type="password" class="form-control" name="inputPassword" id="inputPassword" placeholder="Mot de passe">
		    </div>
		    <div class="form-group col-md-3">
		      <label for="inputConfirmPassword">Confirmer mot de passe</label>
		      <input type="password" class="form-control" name="inputConfirmPassword" id="inputConfirmPassword" placeholder="Mot de passe">
		    </div>
		  </div>
		  
		  <div class="form-group">
		  	<div class="form-group col-md-3">
		  		<label for="inputName">Nom</label>
			    <input type="text" class="form-control" name="inputName" id="inputName" placeholder="Belmondo">
			</div>
			<div class="form-group col-md-3">
				<label for="inputFirstname">Prenom</label>
			    <input type="text" class="form-control" name="inputFirstname" id="inputFirstname" placeholder="Jean-Paul">
			</div>
			<div class="form-group col-md-3">
				<label for="inputPhone">Téléphone</label>
			    <input type="text" class="form-control" name="inputPhone" id="inputPhone" placeholder="0123456789">
			</div>
		  </div>
		  
		  <div class="form-row">
		  	<div class="form-group col-md-3">
				<label for="inputAddress">Rue</label>
			    <input type="text" class="form-control" name="inputAddress" id="inputAddress" placeholder="1234 Rue du magnifique">
			</div>
		    <div class="form-group col-md-3">
		      <label for="inputCity">Ville</label>
		      <input type="text" class="form-control" name="inputCity" id="inputCity">
		    </div>
		    <div class="form-group col-md-3">
		      <label for="inputZip">Code postal</label>
		      <input type="text" class="form-control" name="inputZip" id="inputZip">
		    </div>
		  </div>
		  
		  <button type="submit" class="btn btn-primary">Créer son compte</button>
		</form>
	</body>
</html>