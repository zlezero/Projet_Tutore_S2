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

	public Collection<HashMap<Integer, Evenement>> getHashMapEvenementsPoidsParAnnee(int annee) {
		
		ArrayList<HashMap<Integer, Evenement>> collectionHM = new ArrayList<HashMap<Integer, Evenement>>();
		
		
		
		return collectionHM;
		
	}
	
	public Collection<HashMap<Integer, Evenement>> getHashMapEvenementsPoids() {
		return hashMapEvts.values();
	}


	public int getPoidsEvenement(int parAnnee, Evenement parEvenement) {

		for (HashMap<Integer,Evenement> hashMap : hashMapEvts.values()) {
			for (int i=0;i!=hashMap.size();i++) {
				System.out.println(hashMap.values().contains(parEvenement));
			}
		}

		return 0;
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
	
	/*
	public void updateDefaultEvts() {
		System.out.println(dateFin.getAnnee() + " / " + dateDebut.getAnnee());
		for (int i=0; i != dateFin.getAnnee() - dateDebut.getAnnee();i++) {
			HashMap<Integer, Evenement> hashMap = new HashMap<>();
			hashMap.put(0, null);
			hashMapEvts.put(dateDebut.getAnnee() + i, hashMap);
		}
	} */
}
