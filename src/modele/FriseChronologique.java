package modele;

import java.io.File;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Gère la frise chronologique
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

public class FriseChronologique implements Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<Integer, HashMap<Integer, Evenement>> hashMapEvts;
	private String titreFrise;
	private Date dateDebut;
	private Date dateFin;
	private int periodeFrise;
	private boolean estInitialisee = true;
	private String emplacementSauvegarde;
	
	public FriseChronologique(String parTitre, Date parDateDebut, Date parDateFin, int parPeriodeFrise, String parEmplacementSauvegarde) {

		titreFrise = parTitre;
		dateDebut = parDateDebut;
		dateFin = parDateFin;
		periodeFrise = parPeriodeFrise;
		hashMapEvts = new HashMap<>();
		estInitialisee = true;
		emplacementSauvegarde = parEmplacementSauvegarde;
		
		for (int i=0; i != dateFin.getAnnee() - dateDebut.getAnnee();i++) {
			HashMap<Integer, Evenement> hashMap = new HashMap<>();
			hashMap.put(0, null);
			hashMapEvts.put(dateDebut.getAnnee() + i, hashMap);
		}

	}

	public FriseChronologique(String parTitre, Date parDateDebut, Date parDateFin, int parPeriodeFrise, HashMap<Integer, HashMap<Integer, Evenement>> parHashMapEvts, String parEmplacementSauvegarde) {

		titreFrise = parTitre;
		dateDebut = parDateDebut;
		dateFin = parDateFin;
		periodeFrise = parPeriodeFrise;
		hashMapEvts = parHashMapEvts;
		estInitialisee = true;	
		emplacementSauvegarde = parEmplacementSauvegarde;
	}

	public FriseChronologique() {
		titreFrise = "";
		dateDebut = new Date();
		dateFin = new Date();
		periodeFrise = 1;
		hashMapEvts = new HashMap<>();
		estInitialisee = false;
		emplacementSauvegarde = "Frise.ser";
	}
	
	/**
	* Accesseur qui permet d'obtenir la liste d'évènement  
	* @return une liste d'évènements
	*/

	public ArrayList<Evenement> getListeEvenements() {
		
		ArrayList<Evenement> maListe = new ArrayList<Evenement>();
		
		for (HashMap<Integer,Evenement> hashMap : hashMapEvts.values()) {
			for (Evenement monEvt : hashMap.values()) {
				if (monEvt != null) {
					maListe.add(monEvt);
				}
			}
		}
		
		return maListe;
		
	}
	

	/**
	* Méthode qui permet de retourner une hashMap en format chaîne de caractère 
	* @return une hashMap d'évènement en format 
	*/
	
	public Collection<HashMap<Integer, Evenement>> getHashMapEvenementsPoids() {
		return hashMapEvts.values();
	}


	/**
	* Accesseur qui permet d'obtenir le poids d'un Evenement
	* @param parEvenement Un entier parAnnee, un Evenement evenement
	* @return Le poids de l'événement si il existe et -1 sinon
	*/

	public int getPoidsEvenement(Evenement parEvenement) {	
		
		for (HashMap<Integer,Evenement> maHashMap : hashMapEvts.values()) {
			
			for (int i=0;i!=maHashMap.keySet().size();i++) {
				Evenement evt = (Evenement)maHashMap.values().toArray()[i];
				if (evt != null) {
					if (evt.equals(parEvenement)) { //Si les événements sont égaux
						return (int)maHashMap.keySet().toArray()[i]; //On retourne la clé associée
					}
				}	
			}
		}
		
		return -1;
		
	}
	
	/**
	* Méthode qui permet d'ajouter un événement en donnant en paramètre l'évènement que l'on veut ajouter
	* @param parEvenement Un Evenement
	* @param parPoids le poids de l'événement que l'on veut ajouter 
	* @return 1 si il n'y a pas d'erreurs / -1 si il y a eu une erreur
	*/

	public int ajoutEvenement(int parPoids, Evenement parEvenement) {

		if (parEvenement.getDate().getAnnee() >= dateDebut.getAnnee() && parEvenement.getDate().getAnnee() <= dateFin.getAnnee()) { //Si l'événement a ses années dans les périodes de la frise
			if (hashMapEvts.containsKey(parEvenement.getDate().getAnnee())) { //Si la hash map contient déjà un couple correspondant à cette année

				if (hashMapEvts.get(parEvenement.getDate().getAnnee()).size() <= 4) { //Si il y a moins de 4 événements dans cette hashMap
					hashMapEvts.get(parEvenement.getDate().getAnnee()).put(parPoids, parEvenement); //Alors on ajoute l'événement
					return 1;
				}
				else {
					return -1;
				}
			}
			else { //Sinon on ajoute l'année correspondante et on ajoute l'événement
				hashMapEvts.put(parEvenement.getDate().getAnnee(), new HashMap<Integer, Evenement>());
				hashMapEvts.get(parEvenement.getDate().getAnnee()).put(parPoids, parEvenement);
				return 1;
			}
		}
		else {
			return -1;
		}
		
	}
	
	
	/**
	 * Regarde si un événement existe déjà pour une année et un poids donné
	 * @param annee Une année
	 * @param poids Un poids
	 * @return True si un événement existe et false sinon
	 */
	
	public boolean evenementExisteFrise(int annee, int poids) {
		if (hashMapEvts.containsKey(annee) && hashMapEvts.get(annee).containsKey(poids) && hashMapEvts.get(annee).get(poids) != null)
			return true;
		else
			return false;
	}
	
	/**
	 * Supprime tout les événements de la frise qui ne sont plus compris entre son année de début et de fin
	 */
	
	public void supprimerEvenementsHorsPeriode() {
		
		for (Evenement evt : getListeEvenements()) {
			if (evt.getDate().getAnnee() < dateDebut.getAnnee() || evt.getDate().getAnnee() > dateFin.getAnnee()) { //Si l'événement est hors période
				supprimerEvenement(evt);
			}
		}
		
	}
	
	/**
	* Méthode qui permet de supprimer un évènement en donnant en paramètre l'évènement que l'on veut supprimer
	* @param parEvt Un Evenement 
	*/
	
	public void supprimerEvenement(Evenement parEvt) {
		
		for (HashMap<Integer,Evenement> maHashMap : hashMapEvts.values()) {
				maHashMap.values().remove(parEvt);
		}

	}
	
	/**
	 * Permet d'écrire la frise dans un fichier défini par l'emplacement de sauvegarde : emplacementSauvegarde
	 * @throws IOException Si il y a une erreur d'écriture dans le fichier
	 */
	
	public void sauvegarderFrise() throws IOException {
		LectureEcriture.ecriture(new File(emplacementSauvegarde), this);
	}
	
	/**
	 * Retourne l'écriture de la frise chronologique
	 * @return La hash map d'événements en string
	 */
	
	public String toString() {
		return hashMapEvts.toString();
	}
	
	/**
	* Accesseur qui retourne la hashMap qui contient une hashMap d'évènements
	* @return une hashMap qui est composé d'une hashMap d'évènement en tant que valeur et d'une année en tant que clef
	*/	
	
	public HashMap<Integer, HashMap<Integer, Evenement>> getHashMapEvts() {
		return hashMapEvts;
	}
	
	/**
	* Modifieur de la hashMap 
	* @param hashMapEvts La hash map complète des événements en fonction des années
	*/

	public void setHashMapEvts(HashMap<Integer, HashMap<Integer, Evenement>> hashMapEvts) {
		this.hashMapEvts = hashMapEvts;
	}
	
	/**
	* Accesseur qui retourne le titre de la frise chronologique
	* @return une chaîne de caractère qui est le titre de la frise
	*/

	public String getTitreFrise() {
		return titreFrise;
	}
	
	/**	
	* Modifieur qui permet de changer le titre de la frise 
	* @param titreFrise Le titre de la frise
	*/

	public void setTitreFrise(String titreFrise) {
		this.titreFrise = titreFrise;
	}
	
	/**
	* Accesseur qui permet d'obtenir la date du début de la frise chronologique
	* @return un objet Date
	*/	

	public Date getDateDebut() {
		return dateDebut;
	}
	
	/**
	* Modifieur qui permet de changer la date du début de la frise chronologique
	* @param dateDebut La date du début de la frise
	*/	

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	/**
	* Accesseur qui permet d'obtenir la date de fin
	* @return une Date de fin
	*/

	public Date getDateFin() {
		return dateFin;
	}
	
	/**
	*Modifieur qui permet de changer la date de fin de la frise chronologique
	*@param dateFin La date de la fin de la frise
	*/	

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	/**
	*Accesseur qui permet d'obtenir un entier témoin de la période de la frise chronologique 
	*@return un entier témoin de la période de la frise
	*/	

	public int getPeriodeFrise() {
		return periodeFrise;
	}
	
	/**
	*Modifieur qui permet de changer la période de la frise chronologique
	*@param periodeFrise La période de la frise
	*/

	public void setPeriodeFrise(int periodeFrise) {
		this.periodeFrise = periodeFrise;
	}

	/**
	* Méthode qui retourne un booléen indiquant si la frise chronologique a été initialisé
	* @return un booléen 
	*/	
	
	public boolean isEstInitialisee() {
		return estInitialisee;
	}
	
	/**
	*Modifieur qui permet de changer l'état du booléen indiquant si la frise chronologique a été initialisé 
	*@param estInitialisee Si la frise est initialisée
	*/

	public void setEstInitialisee(boolean estInitialisee) {
		this.estInitialisee = estInitialisee;
	}
	
	/**
	 * Accesseur qui retourne l'emplacement de sauvegarde de la frise
	 * @return Un chemin d'accès vers l'emplacement de sauvegarde
	 */
	public String getEmplacementSauvegarde() {
		return emplacementSauvegarde;
	}
	
	/**
	 * Modifieur qui permet de changer l'emplacement de sauvegarde de la frise
	 * @param emplacementSauvegarde Un chemin d'accès où sauvegarder la frise
	 */
	public void setEmplacementSauvegarde(String emplacementSauvegarde) {
		this.emplacementSauvegarde = emplacementSauvegarde;
	}
}