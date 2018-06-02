package modele;

import javax.swing.table.DefaultTableModel;

public class ModeleTable extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	public ModeleTable(FriseChronologique parFrise) {
		this.setRowCount(4);
		this.setColumnCount(10);
	}
	
	public boolean isCellEditable(int indiceLigne, int indiceColonne) {
		return false;
	}
	
	public Class getColumnClass(int parNum) {
		return Evenement.class;
	}
}
