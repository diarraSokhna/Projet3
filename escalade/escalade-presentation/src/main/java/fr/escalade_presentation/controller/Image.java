package fr.escalade_presentation.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_TAMPON = 10240; // 10ko

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chemin = this.getServletConfig().getInitParameter("chemin");

		String fichierRequis = request.getPathInfo();

		if (fichierRequis == null || "/".equals(fichierRequis)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		fichierRequis = URLDecoder.decode(fichierRequis, "UTF-8");
		File fichier = new File(chemin, fichierRequis);

		if (!fichier.exists()) {

			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String type = getServletContext().getMimeType(fichier.getName());

		if (type == null) {
			type = "application/octet-stream";
		}

		response.reset();
		response.setBufferSize(TAILLE_TAMPON);
		response.setContentType(type);
		response.setHeader("Content-Length", String.valueOf(fichier.length()));
		response.setHeader("Content-Disposition", "inline; filename=\"" + fichier.getName() + "\"");

		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {

			entree = new BufferedInputStream(new FileInputStream(fichier), TAILLE_TAMPON);
			sortie = new BufferedOutputStream(response.getOutputStream(), TAILLE_TAMPON);

			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}
}