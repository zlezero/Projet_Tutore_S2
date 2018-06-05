package modele;

import java.io.File;
import java.io.IOException;
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

	public String getEmplacementSauvegarde() {
		return emplacementSauvegarde;
	}

	public void setEmplacementSauvegarde(String emplacementSauvegarde) {
		this.emplacementSauvegarde = emplacementSauvegarde;
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
	
	public Collection<HashMap<Integer, Evenement>> getHashMapEvenementsPoids() {
		return hashMapEvts.values();
	}


	public int getPoidsEvenement(Evenement parEvenement) {	
		
		int compteur = 0;
		
		for (HashMap<Integer,Evenement> maHashMap : hashMapEvts.values()) {
			compteur = 0;
			for (Evenement evt : maHashMap.values()) {
				
				if (evt.compareTo(parEvenement) == 0) {
					return compteur;
				}
				
				compteur += 1;
			}
		}
		
		return -1;
	}

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
	
	public void supprimerEvenement(Evenement parEvt) {
		
		for (HashMap<Integer,Evenement> maHashMap : hashMapEvts.values()) {
				maHashMap.values().remove(parEvt);
		}

	}
	
	public void sauvegarderFrise() throws IOException {
		LectureEcriture.ecriture(new File(emplacementSauvegarde), this);
	}

	
	public String toString() {
		return hashMapEvts.toString();
	}
	
	public HashMap<Integer, HashMap<Integer, Evenement>> getHashMapEvts() {
		return hashMapEvts;
	}

	public void setHashMapEvts(HashMap<Integer, HashMap<Integer, Evenement>> hashMapEvts) {
		this.hashMapEvts = hashMapEvts;
	}

	public String getTitreFrise() {
		return titreFrise;
	}

	public void setTitreFrise(String titreFrise) {
		this.titreFrise = titreFrise;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getPeriodeFrise() {
		return periodeFrise;
	}

	public void setPeriodeFrise(int periodeFrise) {
		this.periodeFrise = periodeFrise;
	}

	public boolean isEstInitialisee() {
		return estInitialisee;
	}

	public void setEstInitialisee(boolean estInitialisee) {
		this.estInitialisee = estInitialisee;
	}
}