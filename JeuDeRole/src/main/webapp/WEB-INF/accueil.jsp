<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
		 <link href="<c:url value="style.css" />" rel="stylesheet">
	</head>
	
	<body >
	
	
		
		<c:choose>
		 
		 <c:when test="${not empty sessionScope.nom && not empty sessionScope.type}">
		 	<center><h1 class="head">Jeu de rôle</h1></center>
		 	<div class="cont">
		 	<div class="block">
		 		<div class="form">   
					<h2>Bonjour <c:out value="${sessionScope.nom}"/></h2> <br>
					
					<p>Votre personnage <c:out value="${sessionScope.type }"/> est le suivant :</p> <br>
					<p style="font-weight: bold;"><c:out value="${sessionScope.perso.getNom() }"/> </p> <br>
					<ul class="list-group">
						<li>Vie  
							<span class="badge"><c:out value="${sessionScope.perso.getVie() }"/></span>
						</li>
						<li>Mana  
							<span class="badge"><c:out value="${sessionScope.perso.getMana() }"/></span>
						</li>
						
						<li>Attaque 
							<span class="badge"><c:out value="${sessionScope.perso.getAttaque() }"/></span>
						</li>
						
						<li>Defense  
							<span class="badge"><c:out value="${sessionScope.perso.getDefense() }"/></span>
						</li>
						  
					</ul>
					
					<form  method="post">
					<input class="btn" style="padding-right:20px;" type="submit" value="Modifier">
					</form>
					<form  method="get" action="Combat">
					<input class="btn"  type="submit" value="Continuer">
					</form>
				</div>
			</div>
			</div>
		 </c:when>  
		 
		<c:otherwise>
			<h1 class="head">Jeu de rôle</h1>
			<div class="cont"> 
				<div class="block">
					<h1>Créer votre personnage</h1> 
					  
					<form  method="post">
						<label for="name" > Votre nom: </label>
						<input type="text" placeholder="sara" name="nom" id="name">
						
						<!-- <span class="erreur">${erreurs['nom']}</span>  -->
						<select name="type">
							<option value="guerrier">Guerrier</option>
							<option value="magicien">Magicien</option>
						</select>
						<input class="btn"  type="submit" value="Valider">
					</form>
				</div>
			</div>
		 </c:otherwise>  
		
		</c:choose>
		
	
	
	</body>
</html>