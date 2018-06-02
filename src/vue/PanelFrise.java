package vue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import modele.FriseChronologique;
import modele.ModeleTable;

public class PanelFrise extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	JTable tableFrise;
	
	public PanelFrise(FriseChronologique parFrise) {
		
		ModeleTable monModele = new ModeleTable(parFrise);
		
		tableFrise = new JTable(monModele);
		tableFrise.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scrollPane = new JScrollPane(tableFrise,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane);
		
	}
	
}
