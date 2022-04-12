package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import actions.IAttaque;
import actions.IDefense;
import beans.Monstre;
import beans.Personnage;

/**
 * Servlet implementation class CombatServlet
 */
@WebServlet("/Combat")
public class CombatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArrayList<String> actions;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CombatServlet() {
        super();
        // TODO Auto-generated constructor stub
        actions = new ArrayList<String>();
        actions.add("Combatez !");
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
    
    
    // Sauvegarder les données en cours dans les cookies
    public HttpServletRequest saveInCookies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	HttpSession session = request.getSession();
    	Cookie[]cookies = request.getCookies();
    	for (int i=0;i<cookies.length;i++) {
			if (cookies[i].getName()=="nom") session.setAttribute(cookies[i].getName(),cookies[i].getValue());
			if (cookies[i].getName()=="vie") cookies[i].setValue(""+((Personnage) session.getAttribute("perso")).getVie());
			if (cookies[i].getName()=="mana") cookies[i].setValue(""+((Personnage) session.getAttribute("perso")).getMana());
			if (cookies[i].getName()=="attaque") cookies[i].setValue(""+((Personnage) session.getAttribute("perso")).getAttaque());
			if (cookies[i].getName()=="defense") cookies[i].setValue(""+((Personnage) session.getAttribute("perso")).getDefense());
		}
    	return request;
    }
    
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Monstre monstre = (Monstre) session.getAttribute("monstre");
		String action = (String) session.getAttribute("action");
		
		if (action!=null) {
			if(action.equals("Arreter")) {
				session.removeAttribute("action");
				request.setAttribute("actions", actions);
				RequestDispatcher end = request.getRequestDispatcher("/WEB-INF/end.jsp");
	    		end.forward(request, response);
			}
			else if (action.equals("Recommencer")) {
				session.removeAttribute("perso");
				session.removeAttribute("nom");
				session.removeAttribute("type");
				session.removeAttribute("action");
				actions = new ArrayList<String>();
				HttpServletResponse newResponse = this.cleanCookies(request, response);
				newResponse.sendRedirect("Accueil");
			}
			else {
				HttpServletRequest newRequest = request;
				if (action.equals("Continuer")) {
					session.setAttribute("monstre", new Monstre(100,30));
					newRequest = this.saveInCookies(request, response);
				}
				else if (action.equals("attaque")) {
					actions.add(((IAttaque) session.getAttribute("perso")).attaque(monstre));
					actions.add(((IDefense) session.getAttribute("perso")).update());
					if (monstre.getVie()>0) actions.add(monstre.attaque((IAttaque) session.getAttribute("perso")));
				}
				else if (action.equals("attaqueSpe")) {
					actions.add(((IAttaque) session.getAttribute("perso")).attaqueSpe(monstre));
					actions.add(((IDefense) session.getAttribute("perso")).update());
					if (monstre.getVie()>0) actions.add(monstre.attaque((IAttaque) session.getAttribute("perso")));
				}
				else if (action.equals("defense")) {
					actions.add(((IDefense) session.getAttribute("perso")).defend());
					actions.add(((IDefense) session.getAttribute("perso")).update());
					if (monstre.getVie()>0) actions.add(monstre.attaque((IAttaque) session.getAttribute("perso")));
				}
				else if (action.equals("concentration")) {
					actions.add(((IDefense) session.getAttribute("perso")).concentration());
					actions.add(((IDefense) session.getAttribute("perso")).update());
					if (monstre.getVie()>0) actions.add(monstre.attaque((IAttaque) session.getAttribute("perso")));
				}
				request.setAttribute("actions", actions);
				this.getServletContext().getRequestDispatcher("/WEB-INF/combat.jsp").forward(newRequest, response);
			}
		}
		else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/combat.jsp").forward(request, response);
			request.setAttribute("actions", actions);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("action", request.getParameter("action"));
		response.sendRedirect("Combat");
	}

}
