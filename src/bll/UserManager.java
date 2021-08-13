package bll;



import java.util.List;
import java.util.regex.Pattern;

import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;
import dal.UserDAO;

public class UserManager {

	private UserDAO userDao = DAOFactory.getUserDAO();
	private static UserManager instance;
	
	//Singleton
	public static UserManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public Utilisateur connect(String id, String password) 
	{
		Utilisateur utilisateur=null;
		String email=null;
		String motDePasse=null;
		String pseudo=null;
		
		if (id!=null) 
		{
			if (id.contains("@")) 
			{
				email=id;
			}
			else
			{
				pseudo=id;
			}
		}
		if (password!=null) 
		{
			motDePasse=password;
		}
		try {
			
			List<Utilisateur> selectUtilisateur = userDao.select(null, pseudo, null, null, email, null, null, null, null, motDePasse, null, null);

			if (!selectUtilisateur.isEmpty()) 
			{
				utilisateur = selectUtilisateur.get(0);
			}
			
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return	utilisateur;
		
	}
	
	public Utilisateur getUserById(Integer no_utilisateur){
		
		Utilisateur utilisateur = null;
		
		try {
			List<Utilisateur> selectUtilisateur = userDao.select(no_utilisateur,null, null, null, null, null, null, null, null, null, null, null);
			if (!selectUtilisateur.isEmpty()) 
			{
				utilisateur = selectUtilisateur.get(0);
			}
		}catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return utilisateur;
	}
	public void update(Utilisateur utilisateur, String pseudo, String nom, String prenom, String email, String telephone,
						String rue, String codePostal,String ville ,String nouveauMotDePasse,String confirmationNouveauMotDePasse) throws DALException
	{
		//TODO faire regEX
		if (pseudo!=null && !pseudo.isEmpty()&&utilisateur.getPseudo()!=pseudo) 
		{
			utilisateur.setPseudo(pseudo);
			//TODO verif si existe
		}
		if (nom!=null && !nom.isEmpty()&&utilisateur.getNom()!=nom) 
		{
			utilisateur.setNom(nom);
		}
		if (prenom!=null && !prenom.isEmpty()&&utilisateur.getPrenom()!=prenom) 
		{
			utilisateur.setPrenom(prenom);
		}
		if (email!=null && !email.isEmpty()&&utilisateur.getEmail()!=email) 
		{
			utilisateur.setEmail(email);
			//TODO verif si existe
		}
		if (telephone!=null && !telephone.isEmpty()&&utilisateur.getTelephone()!=telephone) 
		{
			utilisateur.setTelephone(telephone);
		}
		if (rue!=null && !rue.isEmpty()&&utilisateur.getRue()!=rue) 
		{
			utilisateur.setRue(rue);
		}
		if (ville!=null && !ville.isEmpty()&&utilisateur.getCodePostal()!=ville) 
		{
			utilisateur.setVille(ville);
		}
		if (codePostal!=null && !codePostal.isEmpty()&&utilisateur.getCodePostal()!=codePostal) 
		{
			utilisateur.setCodePostal(codePostal);
		}
		if (nouveauMotDePasse!=null && !nouveauMotDePasse.isEmpty()&&utilisateur.getMotDePasse()!=nouveauMotDePasse&&confirmationNouveauMotDePasse.equals(nouveauMotDePasse)) 
		{
			utilisateur.setMotDePasse(nouveauMotDePasse);
		}
		userDao.update(utilisateur);
	}
	
	public Utilisateur createUser(String pseudo, String name, String firstName, String email, String phone, String street,
			String zip, String city, String pwd){
		
		Utilisateur result = null;
		try {
			result = userDao.insert(new Utilisateur(
						0,
						pseudo,
						name,
						firstName,
						email,
						phone,
						street,
						zip,
						city,
						pwd,
						0,
						false
					));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}
	
	public boolean verificationInfoModification(Utilisateur utilisateur, String pseudo, String nom, String prenom, String email, String telephone,
						String rue, String codePostal,String ville , String acienMdp, String nouveauMotDePasse,String confirmationNouveauMotDePasse) throws DALException 
	{
		boolean verif=true;
				
		boolean matchesPseudo = Pattern.matches("[a-zA-Z0-9]+", pseudo);
		boolean matchesNom = Pattern.matches("[a-zA-Z -]+", nom);
		boolean matchesPrenom = Pattern.matches("[a-zA-Z -]+", prenom);
		boolean matchesCodePostal = Pattern.matches("[0-9]{5}", codePostal);
		boolean matchesVille = Pattern.matches("[a-zA-Z -]+", ville);
		boolean matchesTel = Pattern.matches("[0-9]{10}", telephone);
		boolean matchesEmail = Pattern.matches("[a-zA-Z0-9]+(@)[a-zA-Z]+(.)[a-zA-Z]+", email);
	
		if (!utilisateur.getNom().equals(nom)&&!matchesNom) 
		{
			verif=false;
		}
		if (!utilisateur.getPrenom().equals(prenom)&&!matchesPrenom) 
		{
			verif=false;
		}
		if (!utilisateur.getTelephone().equals(telephone)&&!matchesTel) 
		{
			verif=false;
		}
		if (!utilisateur.getCodePostal().equals(codePostal)&&!matchesCodePostal) 
		{
			verif=false;
		}
		if (!utilisateur.getVille().equals(ville)&&!matchesVille) 
		{
			verif=false;
		}
		List<Utilisateur> utilisateurs=null;
		if (!utilisateur.getMotDePasse().equals(acienMdp)) 
		{
			verif=false;
		}
		else
		{
			//verif pour la modification de mdp
			if (!nouveauMotDePasse.isEmpty()&&!confirmationNouveauMotDePasse.isEmpty()) 
			{
				if (!nouveauMotDePasse.equals(confirmationNouveauMotDePasse)) 
				{
					verif=false;
				}
			}
		}
		
		//verif si pseudo existe deja
		if (!utilisateur.getPseudo().equals(pseudo)) 
		{
			utilisateurs = userDao.select(null, pseudo, null, null, null, null, null, null, null, null, null, null);
			if (!utilisateurs.isEmpty()||!matchesPseudo) 
			{
				verif=false;
//				reponse.append("Le pseudo existe déjà. \n");
//				System.out.println(pseudo);
			}
			
		}
		
		//verfi si mail existe deja
		if (!utilisateur.getEmail().equals(email)) 
		{
			utilisateurs = userDao.select(null, null, null, null, email, null, null, null, null, null, null, null);
			if (!utilisateurs.isEmpty()||!matchesEmail) 
			{
				verif=false;
//				reponse.append("Le mail existe déjà. \n");
			}
		}
		
		return verif;
		
	}
	
	public boolean checkCredentialNewUser(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville , String pwd ,String confirmPwd) throws DALException {
				
		boolean verif=true;

		boolean matchesPseudo = Pattern.matches("[a-zA-Z0-9]+", pseudo);
		boolean matchesNom = Pattern.matches("[a-zA-Z -]+", nom);
		boolean matchesPrenom = Pattern.matches("[a-zA-Z -]+", prenom);
		boolean matchesCodePostal = Pattern.matches("[0-9]{5}", codePostal);
		boolean matchesVille = Pattern.matches("[a-zA-Z -]+", ville);
		boolean matchesTel = Pattern.matches("[0-9]{10}", telephone);
		boolean matchesEmail = Pattern.matches("[a-zA-Z0-9]+(@)[a-zA-Z]+(.)[a-zA-Z]+", email);
	
		if (!matchesNom) 
		{
			verif=false;
		}
		if (!matchesPrenom) 
		{
			verif=false;
		}
		if (!matchesTel) 
		{
			verif=false;
		}
		if (!matchesCodePostal) 
		{
			verif=false;
		}
		if (!matchesVille) 
		{
			verif=false;
		}
		
		if (!pwd.isEmpty()&&!confirmPwd.isEmpty()) 
		{
			if (!pwd.equals(confirmPwd)) 
			{
				verif=false;
			}
		} else {
			verif = false;
		}
		
		List<Utilisateur> utilisateurs=null;
		//verif si pseudo existe deja
		utilisateurs = userDao.select(null, pseudo, null, null, null, null, null, null, null, null, null, null);
		if (!utilisateurs.isEmpty()||!matchesPseudo)
		{
			verif=false;
//				reponse.append("Le pseudo existe déjà. \n");
//				System.out.println(pseudo);
		}
			
		utilisateurs=null;
		//verfi si mail existe deja
		utilisateurs = userDao.select(null, null, null, null, email, null, null, null, null, null, null, null);
		if (!utilisateurs.isEmpty()||!matchesEmail) 
		{
			verif=false;
//			reponse.append("Le mail existe déjà. \n");
		}
		
		return verif;
	}
	
//	public void checkRegex(boolean matchesPseudo, String pseudo, boolean matchesNom, String nom,
//			boolean matchesPrenom, String prenom, boolean matchesCodePostal, String codePostal,
//			boolean matchesVille, String ville, boolean matchesTel, String telephone,
//			boolean matchesEmail, String email) {
//		matchesPseudo = Pattern.matches("[a-zA-Z0-9]+", pseudo);
//		matchesNom = Pattern.matches("[a-zA-Z -]+", nom);
//		matchesPrenom = Pattern.matches("[a-zA-Z -]+", prenom);
//		matchesCodePostal = Pattern.matches("[0-9]{5}", codePostal);
//		matchesVille = Pattern.matches("[a-zA-Z -]+", ville);
//		matchesTel = Pattern.matches("[0-9]{10}", telephone);
//		matchesEmail = Pattern.matches("[a-zA-Z0-9]+(@)[a-zA-Z]+(.)[a-zA-Z]+", email);
//	}

	
	
	
}
