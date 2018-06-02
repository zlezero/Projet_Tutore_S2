package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

public class ModeleTable extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	private int rowNumber = 4;
	private int columnNumber;
	private FriseChronologique chFrise;
	
	public ModeleTable(FriseChronologique parFrise) {
		
		chFrise = parFrise;
		columnNumber = (parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee())+1;
		
		this.setRowCount(rowNumber);
		this.setColumnCount(columnNumber);

		ArrayList<String> intitulesTab = new ArrayList<String>();
		
		for (int i=0;i!=(parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee())+1;i++) {
			if ( (i % parFrise.getPeriode() == 0) || (i == (parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee())) ) {
				intitulesTab.add(Integer.toString(parFrise.getDateDebut().getAnnee() + i));
			}
			else { 
				intitulesTab.add("");
			}
		}
		
		setColumnIdentifiers(intitulesTab.toArray());
		
		
		Collection<HashMap<Integer, Evenement>> evenements = parFrise.getHashMapEvenementsPoids();

		if (evenements != null) {	
			
			Iterator<HashMap<Integer, Evenement>> iterateur = evenements.iterator();

			while (iterateur.hasNext()) {
				
				HashMap<Integer, Evenement> hashMap = iterateur.next();
			
				//for (int i=0;i!=evenements.size();i++) {
					ajoutEvenement(0, (Evenement)hashMap.values().toArray()[0]);
				//}
			}
		}
		
	}
	
	public boolean isCellEditable(int indiceLigne, int indiceColonne) {
		return false;
	}
	
	public Class<?> getColumnClass(int parNum) {
		return Evenement.class;
	}
	
	public void ajoutEvenement(int parPoids, Evenement parEvt) {

		int indiceColonne = chFrise.getDateFin().getAnnee() - parEvt.getDate().getAnnee();
		int indiceLigne = parPoids;

		while (indiceLigne < columnNumber && getValueAt(indiceLigne, indiceColonne) != null) {
			indiceLigne++;
		}

		setValueAt(parEvt, indiceLigne, indiceColonne);

	}
	
}
