package vue;

import javax.swing.JPanel;

import modele.FriseChronologique;

public class PanelAffichagePrincipal extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public PanelAffichagePrincipal(FriseChronologique parFrise) {
		
		PanelFrise panelFrise = new PanelFrise(parFrise);
		add(panelFrise);
		
	}
	
}
