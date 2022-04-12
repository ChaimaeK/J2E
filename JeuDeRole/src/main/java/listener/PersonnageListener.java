package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import beans.Guerrier;
import beans.Magicien;
import beans.Monstre;
import beans.Personnage;

/**
 * Application Lifecycle Listener implementation class RequestListener
 *
 */
@WebListener
public class PersonnageListener implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public PersonnageListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    	HttpSession session = se.getSession();
    	System.out.println(se.getName()+" : "+se.getValue());
    	if (se.getValue().equals("guerrier")) {
    		session.setAttribute("perso",new Guerrier(100,20));
    	}
    	if (se.getValue().equals("magicien")) {
    		session.setAttribute("perso",new Magicien(80,90));
    	}
    	if (se.getName().equals("nom")) {
    		((Personnage) session.getAttribute("perso")).setNom((String) se.getValue());
    		session.setAttribute("monstre", new Monstre(100,30));
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    	HttpSession session = se.getSession();
    	System.out.println(se.getName()+" : "+se.getValue());
    	if (se.getValue().equals("guerrier")) {
    		session.setAttribute("perso",new Guerrier(100,20));
    	}
    	if (se.getValue().equals("magicien")) {
    		session.setAttribute("perso",new Magicien(80,90));
    	}
    	if (se.getName().equals("nom")) {
    		((Personnage) session.getAttribute("perso")).setNom((String) se.getValue());
    		session.setAttribute("monstre", new Monstre(100,30));
    	}
    }
	
}
