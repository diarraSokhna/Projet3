package fr.escalade_presentation.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import fr.escalade.persistance.TopoDao;


public class ListeTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public static final String CONF_DAO_FACTORY = "daofactory";
	    public static final String ATT_TOPO       = "topo";

	    public static final String VUE  = "/WEB-INF/vue/listeTopos.jsp";
          public TopoDao topoDao;
	     
//	    org.springframework.context.ApplicationContext context = new ClassPathXmlApplicationContext("BootstrapContext.xml");
//	    TopoDao topoDao = (TopoDao) context.getBean("topoDao");

	
    public ListeTopo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    
			request.setAttribute("topos", topoDao.lister());
		
	    //on dit a notre servlet d'afficher la page jsp
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
