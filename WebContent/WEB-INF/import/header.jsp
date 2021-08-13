<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/RedirectIndexServlet">ENI-Enchères</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto">

	<c:choose>
	    <c:when test="${user!=null}">
	    	<li class="nav-item">
	       		<a class="nav-link" href="#">Enchères</a>
	       	</li>
	       	
	       	<li class="nav-item">
				<a class="nav-link" href="#">Vendre un article</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/VoirProfilServlet">Mon profil</a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/IndexServlet">Déconnexion</a>
			</li>
	    </c:when>
	    <c:otherwise>
		    <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/ConnexionServlet">S'inscrire - Se connecter</a>
		    </li>
	    </c:otherwise>
	</c:choose>   
        
      </ul>
      
    </div>
  </div>
</nav>
