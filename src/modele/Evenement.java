package modele;

import java.io.Serializable;

public class Evenement implements Comparable <Evenement>, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Date chDate;
	private String chTitre;
	private String chLieu;
	private String chDescription;
	private String chPhoto;
	private static int chNbInstancie;
	
	public Evenement(Date parDate, String parTitre, String parLieu, String parDescription, String parPhoto)
	{
		chDate = parDate;
		chTitre = parTitre;
		chLieu = parLieu;
		chDescription = parDescription;
		chPhoto = parPhoto;
		chNbInstancie += 1;
	}
	
	public String getChDescription() {
		return chDescription;
	}

	public void setChDescription(String chDescription) {
		this.chDescription = chDescription;
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
