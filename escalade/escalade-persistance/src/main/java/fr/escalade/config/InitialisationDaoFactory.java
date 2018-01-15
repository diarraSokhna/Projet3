package fr.escalade.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.escalade.persistance.DaoFactory;

public class InitialisationDaoFactory implements ServletContextListener {

	private static final String ATT_DAO_FACTORY = "daofactory";
	private DaoFactory daoFactory;

	public void contextDestroyed(ServletContextEvent event) {

	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		this.daoFactory = DaoFactory.getInstance();

		servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);

	}

}
