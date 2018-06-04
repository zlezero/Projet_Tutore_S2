package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import modele.FriseChronologique;
import modele.ModeleTable;

public class PanelAffichageFrise extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	JTable tableFrise;
	
	public PanelAffichageFrise(FriseChronologique parFrise) {
		
		setLayout(new BorderLayout());
		
		ModeleTable monModele = new ModeleTable(parFrise);
		
		tableFrise = new JTable(monModele);
		tableFrise.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableFrise.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(tableFrise,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane, BorderLayout.CENTER);
		
	}

	public JTable getTableFrise() {
		return tableFrise;
	}

	public void setTableFrise(JTable tableFrise) {
		this.tableFrise = tableFrise;
	}
	
	public void updateTable(FriseChronologique parFrise) {
		tableFrise.setModel(new ModeleTable(parFrise));
	}
	
}
