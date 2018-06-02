package modele;

import java.io.Serializable;

public class Evenement implements Comparable <Evenement>, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Date chDate;
	private String chTitre;
	private String chLieu;
	private static int chNbInstancie;
	
	public Evenement(Date parDate, String parTitre, String parLieu)
	{
		chDate = parDate;
		chTitre = parTitre;
		chLieu = parLieu;
		chNbInstancie += 1;
	}
	
	public Date getDate()
	{
		return chDate;
	}
	
	public String getTitre()
	{
		return chTitre;
	}
	
	public String getLieu()
	{
		return chLieu;
	}
	
	public static int getNbInstancie()
	{
		return chNbInstancie;
	}
	
	public void setDate(Date parDate)
	{
		chDate = parDate;
	}
	
	public void setTitre(String parTitre)
	{
		chTitre = parTitre;
	}
	
	public void setLieu(String parLieu)
	{
		chLieu = parLieu;
	}
	
	/*
	public void lireEvenement()
	{
		chDate = Date.lireDate();
		System.out.print("Entrez le titre de l'événement : ");
		chTitre = Clavier.lireString();
		System.out.print("Entrez le lieu de l'événement : ");
		chLieu = Clavier.lireString();
	}
	
	public static Evenement returnEvenement()
	{
		Date chDate = Date.lireDate();
		System.out.print("Entrez le titre de l'événement : ");
		String chTitre = Clavier.lireString();
		System.out.print("Entrez le lieu de l'événement : ");
		String chLieu = Clavier.lireString();
		return new Evenement(chDate, chTitre, chLieu);
	} */
	
	public int compareToMauvais(Evenement parEvt)
	{
		if (this.chDate.compareTo(parEvt.chDate) == 0 && this.chTitre.equals(parEvt.chTitre) && this.chLieu.equals(parEvt.chLieu))
			return 0;
		else if (this.chDate == parEvt.chDate)
			return 0;
		else if (chDate.compareTo(parEvt.chDate) == 1)
			return 1;
		else
			return -1;
	}
	
	public int compareTo(Evenement parEvt)
	{
		if (this.chDate.compareTo(parEvt.chDate) != 0)
			return chDate.compareTo(parEvt.chDate);
		if (chTitre.compareTo(parEvt.chTitre) != 0)
			return chTitre.compareTo(parEvt.chTitre);
		return chLieu.compareTo(parEvt.chLieu);
	}
	
	public String toString()
	{
		return "L'événement " + chTitre + " a lieu le " + chDate + " à " + chLieu;
	}
}
