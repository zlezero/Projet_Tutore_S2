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
	
	public int ajoutEvenement(int parAnnee, int parPoids, Evenement parEvenement) {
		
		if (hashMapEvts.containsKey(parAnnee)) {
			if (hashMapEvts.get(parAnnee).size() <= 4) {
				hashMapEvts.get(parAnnee).put(parPoids, parEvenement);
				return 1;
			}
			else {
				return -1;
			}
		}
		else {
			hashMapEvts.put(parAnnee, new HashMap<Integer, Evenement>());
			hashMapEvts.get(parAnnee).put(parPoids, parEvenement);
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
	
	public String toString() {
		return hashMapEvts.toString();
	}
}
