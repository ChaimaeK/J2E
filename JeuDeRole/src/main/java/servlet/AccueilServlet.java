package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Guerrier;
import beans.Magicien;
import beans.Monstre;
import beans.Personnage;

//Jeu de rôle -- Chaimae KHARBOUCH - Miguel Bryan Tenede Tene - Yunqiao ZHANG
/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Restaurer les données a partir des cookies
    public HttpServletRequest loadFromCookies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		Cookie[]cookies = request.getCookies();
		String type="";
		double attaque = 0;
		double defense = 0;
		int mana = 0;
		int vie = 0;
		for (int i=0;i<cookies.length;i++) {
			if (cookies[i].getName()=="nom") session.setAttribute(cookies[i].getName(),cookies[i].getValue());
			if (cookies[i].getName()=="type") type = cookies[i].getValue();
			if (cookies[i].getName()=="vie") vie = Integer.parseInt(cookies[i].getValue());
			if (cookies[i].getName()=="mana") mana = Integer.parseInt(cookies[i].getValue());
			if (cookies[i].getName()=="attaque") attaque = Double.parseDouble(cookies[i].getValue());
			if (cookies[i].getName()=="defense") defense = Double.parseDouble(cookies[i].getValue());
		}
		if(type.equals("guerrier")) {
			Guerrier guerrier = new Guerrier(vie,mana);
			guerrier.setAttaque(attaque);
			guerrier.setDefense(defense);
			session.setAttribute("perso",guerrier);
		}
		else if(type.equals("magicien")) {
			Magicien mage = new Magicien(vie,mana);
			mage.setAttaque(attaque);
			mage.setDefense(defense);
			session.setAttribute("perso",mage);
		}
		return request;
    }
    
    
    // Sauvegarder les données dans les cookies
    public HttpServletResponse createInCookies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	response.addCookie(new Cookie("nom",(String)request.getSession().getAttribute("nom")));
		response.addCookie(new Cookie("type",(String)request.getSession().getAttribute("type")));
		response.addCookie(new Cookie("vie", ""+((Personnage) session.getAttribute("perso")).getVie()));
		response.addCookie(new Cookie("mana", ""+((Personnage) session.getAttribute("perso")).getMana()));
		response.addCookie(new Cookie("attaque", ""+((Personnage) session.getAttribute("perso")).getAttaque()));
		response.addCookie(new Cookie("defense", ""+((Personnage) session.getAttribute("perso")).getDefense()));
		return response;
    }
    
    
    // Vider les cookies
    public HttpServletResponse cleanCookies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Cookie[]cookies = request.getCookies();
    	for (int i=0;i<cookies.length;i++) {
			cookies[i].setMaxAge(0);
			response.addCookie(cookies[i]);
		}
    	return response;
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Cookie[]cookies = request.getCookies();
		if(cookies !=null && cookies.length>1) {
			HttpServletRequest newRequest = this.loadFromCookies(request, response);
			newRequest.getSession().setAttribute("monstre",new Monstre(100,30));
			newRequest.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(newRequest, response);
		}
		else {
			String nom = (String) session.getAttribute("nom");
			if(nom!=null && nom !="") {
				HttpServletResponse newResponse = this.createInCookies(request, response);
				request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, newResponse);
			}
			else {
				session.removeAttribute("type");
				request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 
		HttpSession session = request.getSession();
		Cookie[]cookies = request.getCookies();		
		
		session.setAttribute("type",request.getParameter("type"));			
			
		String nom = request.getParameter( "nom");
		//gérer les exceptions lors de la saisie du nom
		try {
			validationNom( nom );	//méthode qui valide la syntaxe
			session.setAttribute("nom",request.getParameter("nom"));
			
		} catch (Exception e) {
			
			//erreurs.put( "nom", e.getMessage() );;
		}
		
			
		if(cookies !=null && cookies.length>1) {
			this.cleanCookies(request, response).sendRedirect("Accueil");
		}
		else {
			response.sendRedirect("Accueil");
		}

		
	}
	//vérifier si le nom saisie ne contient que des lettres et des chiffres
	private void validationNom( String nom ) throws Exception{
	  
		if ( nom != null &&  !nom.matches("^[a-zA-Z0-9]*$") ) {
			
				throw new Exception( "Le nom du joueur ne doit pas contenir des caractères spéciaux." );
			
	        
	    }
	}

}
