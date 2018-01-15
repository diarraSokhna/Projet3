package fr.escalade_presentation.filtre;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFiltre implements Filter {

	public static final String ACCES_CONNEXION = "/Connection";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession();

		if (session.getAttribute(ATT_SESSION_USER) == null) {
			response.sendRedirect(request.getContextPath() + ACCES_CONNEXION);
		} else {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
