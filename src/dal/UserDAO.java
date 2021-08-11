package dal;

import java.util.List;

import bo.Utilisateur;

public interface UserDAO {
	static final String SELECT_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur is not null ";
	static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS "
			+ "(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
	static final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS "
			+ "SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? "
			+ "WHERE no_utilisateur = ? ";
	static final String DELETE_UTILISATEUR = "DELETE UTILISATEURS WHERE no_utilisateur = ?";
	
	public List<Utilisateur> select(
			Integer noUtil, String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, 
			String MotDePasse, Integer credit, Boolean admin) throws DALException;

	public Utilisateur insert(Utilisateur newUtil) throws Exception;
	
	public void update(Utilisateur util) throws DALException;
	
	public void delete(int noUtil) throws DALException;
}
