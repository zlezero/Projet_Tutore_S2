package modele;

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
		
		for (int i=0; i != dateDebut.getAnnee() - dateFin.getAnnee();i++) {
			HashMap<Integer, Evenement> hashMap = new HashMap<>();
			hashMap.put(0, new Evenement(new Date(1, 1, dateDebut.getAnnee() + i), "", ""));
			hashMapEvts.put(dateDebut.getAnnee() + i, hashMap);
		}
		
	}
	
	public FriseChronologique() {
		
	}
	
	public Evenement[] getListeEvenementsAnnee(int parAnnee) {
		
		
		return null;
	}
	
	public int getPoidsEvenement(int parAnnee, Evenement parEvenement) {
		
		for (HashMap<Integer,Evenement> hashMap : hashMapEvts.values()) {
			for (int i=0;i!=hashMap.size();i++) {
				System.out.println(hashMap.values().contains(parEvenement));
			}
		}
		
		return 0;
	}
}
