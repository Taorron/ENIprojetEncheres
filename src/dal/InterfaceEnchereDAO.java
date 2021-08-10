package dal;

import java.util.List;

import bo.Utilisateur;

public interface InterfaceEnchereDAO {

	public List<Utilisateur> selectUtilisateur(
			Integer noUtil, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, 
			String MotDePasse, Integer credit, Boolean admin) throws DALException;

	public Utilisateur insertUtilisateur(Utilisateur newUtil) throws Exception;
	
}
