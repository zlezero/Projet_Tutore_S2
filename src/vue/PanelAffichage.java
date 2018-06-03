package vue;

import javax.swing.JPanel;

import modele.FriseChronologique;

public class PanelAffichage extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public PanelAffichage(FriseChronologique parFrise) {
		
		PanelFrise panelFrise = new PanelFrise(parFrise);
		add(panelFrise);
		
	}
	
}
