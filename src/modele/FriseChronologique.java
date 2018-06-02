package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FriseChronologique {
	
	private HashMap<Integer, HashMap<Integer, Evenement>> hashMapEvts;
	private String titreFrise;
	private Date dateDebut;
	private Date dateFin;
	private int periodeFrise;
	
	public FriseChronologique(String parTitre, Date parDateDebut, Date parDateFin, int parPeriodeFrise) {
		
		titreFrise = parTitre;
		dateDebut = parDateDebut;
		dateFin = parDateFin;
		periodeFrise = parPeriodeFrise;
		hashMapEvts = new HashMap<>();
		
		for (int i=0; i != dateFin.getAnnee() - dateDebut.getAnnee();i++) {
			HashMap<Integer, Evenement> hashMap = new HashMap<>();
			hashMap.put(0, null);
			hashMapEvts.put(dateDebut.getAnnee() + i, hashMap);
		}
		
	}
	
	public FriseChronologique() {
		
	}
	
	public Evenement[] getListeEvenements(int parAnnee) {
		
		
		return null;
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
	
	public int getPeriode() {
		return periodeFrise;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}
	
	public Date getDateFin() {
		return dateFin;
	}
	
	public String getTitre() {
		return titreFrise;
	}
	
	public HashMap<Integer, HashMap<Integer, Evenement>> getHashMapEvts() {
		return hashMapEvts;
	}
	
	public String toString() {
		return hashMapEvts.toString();
	}
}
