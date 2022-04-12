<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Combat</title>
	<link href="<c:url value="style.css" />" rel="stylesheet">
	</head>
	<body>
	
		<c:choose>
		
		<c:when test="${sessionScope.monstre.getVie() != 0 and sessionScope.perso.getVie() != 0}">
			<center><h1 class="head">Jeu de rôle</h1></center>
		 	<div class="cont">
		 		<div class="blockLeft">
					<p>Votre adversaire est le suivant :</p> <br>
					<p style="font-weight: bold;"><c:out value="${sessionScope.monstre.getNom() }"/> </p> <br>
					
					<ul class="list-group">
							<li>Vie  
								<span class="badgeAd"><c:out value="${sessionScope.monstre.getVie() }"/></span>
							</li>
							<li>Mana  
								<span class="badgeAd"><c:out value="${sessionScope.monstre.getMana() }"/></span>
							</li>
							
							<li>Attaque 
								<span class="badgeAd"><c:out value="${sessionScope.monstre.getAttaque() }"/></span>
							</li>
							
							<li>Defense  
								<span class="badgeAd"><c:out value="${sessionScope.monstre.getDefense() }"/></span>
							</li>
							  
						</ul>
				</div>
				<div class="blockRight">
					
					<p>Votre personnage <c:out value="${sessionScope.type }"/> est le suivant : </p><br>
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
				</div>
					
					<form class="buttons"  method="post">
						<button name="action" value="attaque">Attaque</button>
						<button name="action" value="attaqueSpe" >Att Speciale</button>
						<button name="action" value="defense">Defense</button>
						<button name="action" value="concentration">Concentration</button>
					</form>
				<div class="combat">
					<c:forEach var="i" begin="1" end="${actions.size()}">
					   <p> <c:out value="${ actions.get(actions.size()-i) }" /></p><br>
					</c:forEach>
				</div>
			</div>
		</c:when>
		
		<c:when test="${sessionScope.perso.getVie() == 0}">
		<center><h1 class="head">Jeu de rôle</h1></center>
		<div class="block">
				<p style="font-weight: bold" >Vous êtes mort!!</p>
				<form class="buttons1" method="post">
					<button name="action" value="Recommencer">Recommencer</button>
					<button name="action" value="Arreter">Terminer</button>
				</form>
			
		</div>
		</c:when>
		
		
		<c:otherwise>
		<center><h1 class="head">Jeu de rôle</h1></center>
		<div class="block">    
			<p style="font-weight: bold;">Bravo, vous avez gagné!!!  </p>
			<form class="buttons1" method="post">
				<button name="action" value="Continuer">Continuer</button>
				<button name="action" value="Arreter">Arreter</button> 
				<p style="clear:left;">Vos données sont sauvegardés. Si vous arretez, vous pourrez continuer plus tard.</p>
			</form>
		</div>
		 </c:otherwise>  
		
		</c:choose>
	
	</body>
</html>