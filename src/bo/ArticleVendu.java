package bo;

import java.util.ArrayList;

public class ArticleVendu 
{
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private String dateDebutEncheres;
	private String dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	private Categorie categorie;
	private Retrait retrait;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	private ArrayList<Enchere> enchere;
	
	



	public ArticleVendu(int noArticle, String nomArticle, String description, String dateDebutEncheres,
			String dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Categorie categorie,
			Retrait retrait, Utilisateur vendeur, Utilisateur acheteur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.categorie = categorie;
		this.retrait = retrait;
		this.vendeur = vendeur;
		this.acheteur = acheteur;
	}

	public ArticleVendu(int noArticle, String nomArticle, String description, String dateDebutEncheres,
			String dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Categorie categorie,
			Retrait retrait, Utilisateur vendeur, Utilisateur acheteur, ArrayList<Enchere> enchere) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.categorie = categorie;
		this.retrait = retrait;
		this.vendeur = vendeur;
		this.acheteur = acheteur;
		this.enchere = enchere;
	}

	public ArrayList<Enchere> getEnchere() {
		return enchere;
	}

	public void setEnchere(ArrayList<Enchere> enchere) {
		this.enchere = enchere;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public Retrait getRetrait() {
		return retrait;
	}
	
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(String dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public String getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(String dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public String getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}
	
}
