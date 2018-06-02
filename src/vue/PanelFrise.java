package vue;

import javax.swing.JPanel;
import javax.swing.JTable;

import modele.FriseChronologique;

public class PanelFrise extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	JTable tableFrise;
	
	public PanelFrise(FriseChronologique parFrise) {
		tableFrise = new JTable();
		add(tableFrise);
	}
	
}
