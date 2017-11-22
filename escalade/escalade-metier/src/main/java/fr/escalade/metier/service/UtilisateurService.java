package fr.escalade.metier.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.dao.UtilisateurDao;

@Service
@Transactional
public class UtilisateurService {
	
	@Autowired
	private final UtilisateurDao utilisateurdao;

	public UtilisateurService(UtilisateurDao utilisateurdao) {
		this.utilisateurdao = utilisateurdao;
	}
	
	public List<Utilisateur> lister() {
		
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		for(Utilisateur utilisateur : utilisateurdao.findAll()){
			utilisateurs.add(utilisateur);
		}
		return utilisateurs;
	}
}
