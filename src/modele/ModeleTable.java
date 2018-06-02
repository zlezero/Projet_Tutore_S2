package modele;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class ModeleTable extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	public ModeleTable(FriseChronologique parFrise) {
		
		this.setRowCount(4);
		this.setColumnCount(parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee());
		
		ArrayList<String> intitulesTab = new ArrayList<String>();
		
		for (int i=0;i!=parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee();i++) {
			if (i % parFrise.getPeriode() == 0) {
				intitulesTab.add(Integer.toString(parFrise.getDateDebut().getAnnee() + i));
			}
			else { 
				intitulesTab.add("");
			}
		}
		
		setColumnIdentifiers(intitulesTab.toArray());
	}
	
	public boolean isCellEditable(int indiceLigne, int indiceColonne) {
		return false;
	}
	
	public Class getColumnClass(int parNum) {
		return Evenement.class;
	}
}
