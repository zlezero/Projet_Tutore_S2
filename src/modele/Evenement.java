package modele;

import java.io.Serializable;

/**
 * Gère les événements
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

public class Evenement implements Comparable <Evenement>, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Date chDate;
	private String chTitre;
	private String chDescription;
	private String chPhoto;
	private static int chNbInstancie;
		
	public Evenement(Date parDate, String parTitre, String parDescription, String parPhoto)
	{
		chDate = parDate;
		chTitre = parTitre;
		chDescription = parDescription;
		chPhoto = parPhoto;
		chNbInstancie += 1;
	}
	
	/**
	* Accesseur qui retourne la description d'un objet Evenement
	* @return une chaîne de caractère qui est la description
	*/
	
	public String getChDescription() {
		return chDescription;
	}

	/**
	* Modifieur qui permet de changer la description d'un objet Evenement
	* @param chDescription La description de l'événement
	*/
	
	public void setChDescription(String chDescription) {
		this.chDescription = chDescription;
	}
	
	/**
	* Accesseur qui retourne un objet Date
	* @return un objet Date
	*/

	public Date getDate()
	{
		return chDate;
	}
	
	/**
	* Accesseur qui retourne une chaîne de caractère qui est le titre d'un objet Evenement
	* @return une chaîne de caractère qui est le titre de l'évènement
	*/
	
	public String getTitre()
	{
		return chTitre;
	}
	
	/**
	* Accesseur qui retourne un entier qui est le témoin du nombre total d'évènements qui ont été créés 
	* @return un entier
	*/
		
	public static int getNbInstancie()
	{
		return chNbInstancie;
	}
	
	/**
	* Modifieur qui permet de changer la date d'un évènement  
	* @param parDate La date de l'événement
	*/
	
	public void setDate(Date parDate)
	{
		chDate = parDate;
	}
	
	/**
	* Modifieur qui permet de changer le titre d'un évènement
	* @param parTitre Le titre de l'événement
	*/
	
	public void setTitre(String parTitre)
	{
		chTitre = parTitre;
	}
	
	/**
	* Accesseur qui retourne le chemin d'accès de la photo associé à l'évènement 
	* @return une chaîne de caractère qui est le chemin d'accès sur le disque dur de la photo associé à l'évènement
	*/
	
	public String getChPhoto() {
		return chPhoto;
	}

	/**
	* Modifieur qui prend en paramètre une chaîne de caractère qui est le nouveau chemin d'accès que l'on veut attribuer à la photo
	* @param chPhoto Une chaîne de caractère qui est le chemin d'accès de la photo 
	*/
	
	public void setChPhoto(String chPhoto) {
		this.chPhoto = chPhoto;
	}
	
	/**
	* Méthode qui compare l'objet appelant et l'objet en paramètre qui sont tout les deux des évènements égaux
	* @param parEvt Un objet Evenement
	* @return -1 ou 0 ou 1 pour indiquer si l'objet appelant est considéré comme inférieur, égal ou supérieur à l'objet donné en paramètre
	*/
	
	public int compareTo(Evenement parEvt)
	{
		if (this.chDate.compareTo(parEvt.chDate) != 0)
			return chDate.compareTo(parEvt.chDate);
		if (chTitre.compareTo(parEvt.chTitre) != 0)
			return chTitre.compareTo(parEvt.chTitre);
		return chPhoto.compareTo(parEvt.chPhoto);
	}
	
	/**
	* Méthode qui retourne le titre d'un évènement sous la forme d'une chaîne de caractère
	* @return le titre de l'évènement
	*/
	
	public String toString()
	{
		return chTitre;
	}
}
