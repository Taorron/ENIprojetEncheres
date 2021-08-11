package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import bo.*;

public class EnchereDAO implements InterfaceEnchereDAO {
	
	//RequeteSQL
	private String SELECT_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur is not null ";
	private String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS "
			+ "(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
	private String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS "
			+ "SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? "
			+ "WHERE no_utilisateur = ? ";
	private String DELETE_UTILISATEUR = "DELETE UTILISATEURS WHERE no_utilisateur = ?";
	
	
	//Methode Utilisateur
	@Override
	public List<Utilisateur> selectUtilisateur(
			Integer noUtil, String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, 
			String MotDePasse, Integer credit, Boolean admin) throws DALException
	{
		int i = 0;
		List<Utilisateur> result = new ArrayList<>();
		
		Map<Integer, Object> mapping = new HashMap<>();
		
		//Adaptation de la requête en fonction des paramètres
		
		StringBuilder query = new StringBuilder(SELECT_UTILISATEUR);
		
		if(noUtil != null) {
			i++;
			mapping.put(i, noUtil);
			query.append("AND no_utilisateur = ? ");
		}
		
		if(pseudo != null) {
			i++;
			mapping.put(i, pseudo);
			query.append("AND pseudo = ? ");
		}
		
		if(nom != null) {
			i++;
			mapping.put(i, nom);
			query.append("AND nom = ? ");
		}
		
		if(prenom != null) {
			i++;
			mapping.put(i, prenom);
			query.append("AND prenom = ? ");
		}			
		
		if(email != null) {
			i++;
			mapping.put(i, email);
			query.append("AND email = ? ");
		}
					
		if(telephone != null) {
			i++;
			mapping.put(i, telephone);
			query.append("AND telephone = ? ");
		}			
		
		if(rue != null) {
			i++;
			mapping.put(i, rue);
			query.append("AND rue = ? ");
		}
			
		
		if(codePostal != null) {
			i++;
			mapping.put(i, codePostal);
			query.append("AND code_postal = ? ");
		}
			
		
		if(ville != null) {
			i++;
			mapping.put(i, ville);
			query.append("AND ville = ? ");
		}
			
		
		if(MotDePasse != null) {
			i++;
			mapping.put(i, MotDePasse);
			query.append("AND mot_de_passe = ? ");
		}
			
		
		if(credit != null) {
			i++;
			mapping.put(i, credit);
			query.append("AND credit = ? ");
		}
		
		if(admin != null) {
			i++;
			mapping.put(i, admin);
			query.append("AND administrateur = ? ");
		}
		
		System.out.println(query);
		
		try(Connection connexion = JdbcTools.getConnection(); 
				PreparedStatement rqt = connexion.prepareStatement(query.toString());) 
		{
			for(int j = 1; j <= mapping.size(); j++) 
			{
				if(mapping.get(j) instanceof Boolean) {
					rqt.setBoolean(j, (Boolean)mapping.get(j));
					
				} else if(mapping.get(j) instanceof String) {
					rqt.setString(j, (String)mapping.get(j));
					
				} else if(mapping.get(j) instanceof Integer) {
					rqt.setInt(j, (Integer)mapping.get(j));	
				}
			}
			
			ResultSet rs = rqt.executeQuery();
			while(rs.next())
			{
				result.add(new Utilisateur(
					rs.getInt("no_utilisateur"), rs.getString("pseudo"),
					rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
					rs.getString("telephone"),  rs.getString("rue"), rs.getString("code_postal"),
					rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
					rs.getBoolean("administrateur")));
			}
			
			System.out.println("success selectUtilisateur");
		} catch (SQLException e) {
			throw new DALException("erreur dans selectById : " + e.toString());
		} 
		
		return result;
	}	
	
	@Override
	public Utilisateur insertUtilisateur(Utilisateur newUtil) throws Exception {
		
		Utilisateur result = newUtil;
		Connection cnx = null;
		try {
			cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(INSERT_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS);
			
			rqt.setString(1,newUtil.getPseudo());
			rqt.setString(2,newUtil.getNom());
			rqt.setString(3,newUtil.getPrenom());
			rqt.setString(4,newUtil.getEmail());
			rqt.setString(5,newUtil.getTelephone());
			rqt.setString(6,newUtil.getRue());
			rqt.setString(7,newUtil.getCodePostal());
			rqt.setString(8,newUtil.getVille());
			rqt.setString(9,newUtil.getMotDePasse());
			rqt.setInt(10,newUtil.getCredit());
			rqt.setBoolean(11,newUtil.isAdministrateur());
			
			rqt.executeUpdate();
			ResultSet rs = rqt.getGeneratedKeys();
			if (rs.next()) {
				result.setNoUtilisateur(rs.getInt(1));
			}
			
			System.out.println("success insertUtilisateur new id : "+result.getNoUtilisateur());
			
		} catch (SQLException e) {
			cnx.rollback();
			//propager une exception personnalisée
			throw new Exception("Problème d'ajout d'un utilisateur en base. Cause : " + e.getMessage());
		}
		return result;
		
	}

	@Override
	public void updateUtilisateur(Utilisateur util) throws DALException {
		// TODO Auto-generated method stub
		try(Connection connexion = JdbcTools.getConnection();
				PreparedStatement prpStmt = connexion.prepareStatement(UPDATE_UTILISATEUR);){
			
			prpStmt.setString(1, util.getPseudo());
			prpStmt.setString(2, util.getNom());
			prpStmt.setString(3, util.getPrenom());
			prpStmt.setString(4, util.getEmail());
			prpStmt.setString(5, util.getTelephone());
			prpStmt.setString(6, util.getRue());
			prpStmt.setString(7, util.getCodePostal());
			prpStmt.setString(8, util.getVille());
			prpStmt.setString(9, util.getMotDePasse());
			prpStmt.setInt(10, util.getCredit());
			prpStmt.setBoolean(11, util.isAdministrateur());
			prpStmt.setInt(12, util.getNoUtilisateur());
			
			prpStmt.executeUpdate();
			
			System.out.println("Success updateUtilisateur id : " + util.getNoUtilisateur());
			
		} catch (SQLException e) {
			throw new DALException("erreur update, utilisateur id : " + util.getNoUtilisateur());
		}		
	}
	
	public void deleteUtilisateur(int noUtil) throws DALException{
		try(Connection connexion = JdbcTools.getConnection();
				PreparedStatement prpStmt = connexion.prepareStatement(DELETE_UTILISATEUR);){
			
			prpStmt.setInt(1, noUtil);
			prpStmt.executeUpdate();
			
			System.out.println("Success deleteUtilisateur id : " + noUtil);
		} catch (SQLException e) {
			throw new DALException("erreur deleteUtilisateur id : " + noUtil);
		}
	}

	
	
}


