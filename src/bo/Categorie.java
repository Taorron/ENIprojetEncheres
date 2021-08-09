package bo;

import java.util.ArrayList;

public class Categorie 
{
	private int noCategorie;
	private String libelle;
	private ArrayList<ArticleVendu> articleVendu;
	
	
	
	
	public Categorie(int noCategorie, String libelle, ArrayList<ArticleVendu> articleVendu) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.articleVendu = articleVendu;
	}
	public ArrayList<ArticleVendu> getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArrayList<ArticleVendu> articleVendu) {
		this.articleVendu = articleVendu;
	}
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void SuppArticleVendu(ArticleVendu articleVendu)
	{
		this.articleVendu.remove(articleVendu);
	}
	public void AddArticleVendu(ArticleVendu articleVendu)
	{
		this.articleVendu.add(articleVendu);
	}
	
	
}
