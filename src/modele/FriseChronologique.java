package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FriseChronologique implements Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<Integer, HashMap<Integer, Evenement>> hashMapEvts;
	private String titreFrise;
	private Date dateDebut;
	private Date dateFin;
	private int periodeFrise;
	private boolean estInitialisee = true;

	public FriseChronologique(String parTitre, Date parDateDebut, Date parDateFin, int parPeriodeFrise) {

		titreFrise = parTitre;
		dateDebut = parDateDebut;
		dateFin = parDateFin;
		periodeFrise = parPeriodeFrise;
		hashMapEvts = new HashMap<>();
		estInitialisee = true;

		for (int i=0; i != dateFin.getAnnee() - dateDebut.getAnnee();i++) {
			HashMap<Integer, Evenement> hashMap = new HashMap<>();
			hashMap.put(0, null);
			hashMapEvts.put(dateDebut.getAnnee() + i, hashMap);
		}

	}

	public FriseChronologique(String parTitre, Date parDateDebut, Date parDateFin, int parPeriodeFrise, HashMap<Integer, HashMap<Integer, Evenement>> parHashMapEvts) {

		titreFrise = parTitre;
		dateDebut = parDateDebut;
		dateFin = parDateFin;
		periodeFrise = parPeriodeFrise;
		hashMapEvts = parHashMapEvts;
		estInitialisee = true;	
	}

	public FriseChronologique() {
		titreFrise = "";
		dateDebut = new Date();
		dateFin = new Date();
		periodeFrise = 1;
		hashMapEvts = new HashMap<>();
		estInitialisee = false;
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
	* Accesseur qui permet d'obtenir les valeurs de la HashMap d'évènements
	* @return Un ensemble d'évènements
	*/
	
	public Collection<HashMap<Integer, Evenement>> getHashMapEvenementsPoids() {
		return hashMapEvts.values();
	}

	/**
	* Accesseur qui permet d'obtenir le poids d'un Evenement
	* @param un entier parAnnee, un Evenement evenement
	*/

	public int getPoidsEvenement(Evenement parEvenement) {	
		
		for (int i=0;i!=3;i++) {
			HashMap<Integer,Evenement> maHashMap = new HashMap<Integer,Evenement>();
			maHashMap.put(i, parEvenement);
			if (hashMapEvts.containsValue(maHashMap)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	* Méthode qui permet d'ajouter un évènement en donnant en paramètre l'évènement que l'on veut ajouter
	* @param un Evenement 
	*/

	public int ajoutEvenement(int parPoids, Evenement parEvenement) {

		if (parEvenement.getDate().getAnnee() >= dateDebut.getAnnee() && parEvenement.getDate().getAnnee() <= dateFin.getAnnee()) {
			if (hashMapEvts.containsKey(parEvenement.getDate().getAnnee())) {

				if (hashMapEvts.get(parEvenement.getDate().getAnnee()).size() <= 4) {
					hashMapEvts.get(parEvenement.getDate().getAnnee()).put(parPoids, parEvenement);
					return 1;
				}
				else {
					return -1;
				}
			}
			else {
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
	* Méthode qui permet de supprimer un évènement en donnant en paramètre l'évènement que l'on veut supprimer
	* @param un Evenement 
	*/
	
	public void supprimerEvenement(Evenement parEvt) {
		
		for (int i=0;i!=3;i++) {
			HashMap<Integer,Evenement> maHashMap = new HashMap<Integer,Evenement>();
			maHashMap.put(i, parEvt);
			hashMapEvts.values().remove(maHashMap);
		}

		
	}

	/**
	* Méthode qui permet de retourner une hashMap en format chaîne de caractère 
	* @return une hashMap d'évènement en format 
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
	*Modifieur qui permet de changer la date du début de la frise chronologique
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
	*/

	public void setEstInitialisee(boolean estInitialisee) {
		this.estInitialisee = estInitialisee;
	}
}