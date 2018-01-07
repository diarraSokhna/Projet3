package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Site;
import fr.escalade.beans.Voie;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SecteurDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VoieDao;
import fr.escalade_metier.forms.AjoutSiteForm;
import fr.escalade_metier.forms.AjoutSiteSecteurVoieForm;


@WebServlet("/AjoutSiteSecteurVoie")
public class AjoutSiteSecteurVoie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String SESSION_SITES  = "sessionSite";
	public static final String SESSION_SECTEURS  = "sessionSecteur";
	public static final String SESSION_VOIES  = "sessionVoie";

    public static final String ATT_Site      = "site";
    public static final String ATT_FORM         = "form";

    public static final String VUE_SUCCES    = "/escalade-presentation/ListeSite";
    public static final String VUE    = "/restreint/ajoutSiteSecteurVoie.jsp";
   
    private SiteDao siteDao;
    private SecteurDao secteurDao;
    private VoieDao voieDao;
    private PaysDao paysDao;
    private ClassementDao classementDao;
    private CotationDao cotationDao;
    private ExpositionDao expositionDao;
    
    public AjoutSiteSecteurVoie() { }

    public void init() throws ServletException{
    	this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();
    	this.secteurDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSecteurDao();
    	this.paysDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPaysDao();
    	this.voieDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVoieDao();
    	this.classementDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClassementDao();
    	this.cotationDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCotationDao();
    	this.expositionDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getExpositionDao();
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("payss", paysDao.lister());
		request.setAttribute("classements", classementDao.lister());
		request.setAttribute("cotations", cotationDao.lister());
		request.setAttribute("expositions",expositionDao.lister());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AjoutSiteSecteurVoieForm form = new AjoutSiteSecteurVoieForm(siteDao, secteurDao, voieDao);
		
		Site site = form.ajouterSiteSecteurVoie(request);
		
		request.setAttribute(ATT_Site, site);
		request.setAttribute(ATT_FORM, form);
		
		HttpSession session = request.getSession();
		
		if ( form.getErreurs().isEmpty() ) {
		
			 session.removeAttribute(SESSION_SITES);
			 session.removeAttribute(SESSION_SECTEURS);
			 session.removeAttribute(SESSION_VOIES);
			 response.sendRedirect(VUE_SUCCES);
		}else {
			
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
		
		
        
	}
	

}
