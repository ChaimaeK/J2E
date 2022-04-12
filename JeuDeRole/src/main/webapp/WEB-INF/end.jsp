<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Statistiques des combats</title>
		<link href="<c:url value="style.css" />" rel="stylesheet">
	</head>
	<body>
	<h1 class="head">Jeu de rôle</h1>
	<div class="cont">
		<form class="buttons2" method="post">
			<button name="action" value="Recommencer">Recommencer</button>
		</form>
		
		<c:if test="${sessionScope.perso.getVie()>0}">
		<form class="buttons3"  method="post">
			<button name="action" value="Continuer">Reprendre</button>
		</form>
		</c:if>
		<br>
		<h2 style="text-decoration: underline;">Statistiques des combats</h2>
		<br>
		<br>
		<div class="combat">
			<c:forEach var="i" begin="1" end="${actions.size()}">
			   <p> <c:out value="${ actions.get(actions.size()-i) }" /></p><br>
			</c:forEach>
		</div>
	</div>
	</body>
</html>