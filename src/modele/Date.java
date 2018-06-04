package modele;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * Gère la date
 * @author Thomas Vathonne
 * @version 1
 */

public class Date implements Comparable<Date>, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int jour;
	private int mois;
	private int annee;
	/**
	 * Contient le jour de la semaine de la date instanciée
	 */
	private int jourSemaine;

	public Date() {
		GregorianCalendar dateAuj = new GregorianCalendar(); //La date est basé sur le calendrier Grégorien
		annee = dateAuj.get(Calendar.YEAR);
		mois = dateAuj.get(Calendar.MONTH) + 1; // janvier = 0, fevrier = 1...
		jour = dateAuj.get(Calendar.DAY_OF_MONTH);
		jourSemaine = dateAuj.get(Calendar.DAY_OF_WEEK);
	}

	public Date(int parJour, int parMois, int parAnnee) {
		jour = parJour;
		mois = parMois;
		annee = parAnnee;
		GregorianCalendar date = new GregorianCalendar(annee, mois - 1, jour);
		jourSemaine = date.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * retourne 0 si this et parDate sont égales, -1 si this précède parDate, 1
	 * si parDate précède this
	 */
	
	public int compareTo(Date parDate) {
		if (annee < parDate.annee)
			return -1;
		if (annee > parDate.annee)
			return 1;
		// les annà¯Â¿Â½es sont =
		if (mois < parDate.mois)
			return -1;
		if (mois > parDate.mois)
			return 1;
		// les mois sont =
		if (jour < parDate.jour)
			return -1;
		if (jour > parDate.jour)
			return 1;
		return 0;
	}
	
	/**
	 * Calcule la date du lendemain d'un objet date
	 * @return Un objet date avec la date du lendemain
	 */
	
	public Date dateDuLendemain() {
		if (jour < dernierJourDuMois(mois, annee))
			return new Date(jour + 1, mois, annee);
		else if (mois < 12)
			return new Date(1, mois + 1, annee);
		else
			return new Date(1, 1, annee + 1);
	}
	
	/**
	 * Calcule la date de la veille
	 * @return Un objet date avec la date de la veille
	 */
	
	public Date dateDeLaVeille() {
		if (jour > 1)
			return new Date(jour - 1, mois, annee);
		else if (mois > 1)
			return new Date(Date.dernierJourDuMois(mois - 1, annee), mois - 1,
					annee);
		else
			return new Date(31, 12, annee - 1);
	}

	/**
	 * Retourne le dernier jour du mois selon le mois et l'année en paramètre 
	 * @param parMois
	 * @param parAnnee
	 * @return Soit 29/28/30/31
	 */
	
	public static int dernierJourDuMois(int parMois, int parAnnee) {
		switch (parMois) {
		case 2:
			if (estBissextile(parAnnee))
				return 29;
			else
				return 28;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		default:
			return 31;
		} // switch
	}

	/**
	 * Retourne si l'année en paramètre est bissextile ou non
	 * @param parAnnee
	 * @return True ou False
	 */
	
	private static boolean estBissextile(int parAnnee) {
		return parAnnee % 4 == 0
				&& (parAnnee % 100 != 0 || parAnnee % 400 == 0);
	}

	/**
	* Retourne l'objet appelant de type Date en String
	* @return une date en chaîne de caractère
	*/
	
	public String toString() {
		String chaine = new String();
		switch (jourSemaine) {
		case 1:
			chaine = "dimanche";
			break;
		case 2:
			chaine = "lundi";
			break;
		case 3:
			chaine = "mardi";
			break;
		case 4:
			chaine = "mercredi";
			break;
		case 5:
			chaine = "jeudi";
			break;
		case 6:
			chaine = "vendredi";
			break;
		case 7:
			chaine = "samedi";
			break;
		}
		chaine += " " + jour + " ";
		switch (mois) {
		case 1:
			chaine += "janvier";
			break;
		case 2:
			chaine += "février";
			break;
		case 3:
			chaine += "mars";
			break;
		case 4:
			chaine += "avril";
			break;
		case 5:
			chaine += "mai";
			break;
		case 6:
			chaine += "juin";
			break;
		case 7:
			chaine += "juillet";
			break;
		case 8:
			chaine += "août";
			break;
		case 9:
			chaine += "septembre";
			break;
		case 10:
			chaine += "octobre";
			break;
		case 11:
			chaine += "novembre";
			break;
		case 12:
			chaine += "décembre";
			break;
		}
		return chaine;
	}
	
	/**
	* Accesseur qui retourne l'année d'une date
	* @return une année
	*/
	
	public int getAnnee() {
		return annee;
	}
	
	/**
	* Accesseur qui retourne le jour d'une Date
	* @return un jour
	*/
	
	public int getJour() {
		return jour;
	}
	
	/**
	* Accesseur qui retourne le mois d'une Date
	* @return un mois
	*/
	
	public int getMois() {
		return mois;
	}
	
	/**
	 * Renvoie le nom du mois associé à un numéro de mois
	 * @param numeroMois
	 * @return Un nom de mois
	 */
	
	public static String getNomMois(int numeroMois) {
		switch (numeroMois) {
		case 1:
			return "janvier";
		case 2:
			return "février";
		case 3:
			return "mars";
		case 4:
			return "avril";
		case 5:
			return "mai";
		case 6:
			return "juin";
		case 7:
			return "juillet";
		case 8:
			return "août";
		case 9:
			return "septembre";
		case 10:
			return "octobre";
		case 11:
			return "novembre";
		case 12:
			return "décembre";
		default:
			return "Mois invalide";
		}
	}
	
	/** 
	* Accesseur qui retourne un jour de la semaine
	* @return un jour de la semaine
	*/
	
	public int getJourSemaine() {
		return jourSemaine;
	}

	/**
	 * Retourne la date du premier jour de la semaine contenant this
	 * @return Un objet date avec le premier jour de la semaine
	 */
	
	public Date datePremierJourSemaine() {
		Date datePrem = this;
		while (datePrem.getJourSemaine() != 2) {
			datePrem = datePrem.dateDeLaVeille();
		}
		return datePrem;
	}

	public static String getChMoisString(int chMois) {
		String mois[] = { "janvier", "février", "mars", "avril", "mai", "juin",
				"juillet", "août", "septembre", "novembre", "octobre",
				"décembre" };
		return mois[chMois - 1];
	}
	
	/**
	 * Retourne si l'objet date correspond à la date d'aujourd'hui
	 * @return True ou False
	 */
	
	public boolean isToday() {
		return new Date().compareTo(this) == 0;
	}
	
	/**
	* Retourne une date selon le format jour/mois/annee traditionel
	* @return une chaîne de caractère jour/mois/annee
	*/
	
	public String dateFormatee() {
		return jour + "/" + mois + "/" + annee;
	}
} // class Date
