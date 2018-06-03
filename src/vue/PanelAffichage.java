package vue;

import javax.swing.JPanel;

import modele.FriseChronologique;

public class PanelAffichage extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	PanelAffichageFrise panelFrise;
	
	public PanelAffichage(FriseChronologique parFrise) {
		
		panelFrise = new PanelAffichageFrise(parFrise);
		add(panelFrise);
		
	}

	public PanelAffichageFrise getPanelFrise() {
		return panelFrise;
	}

	public void setPanelFrise(PanelAffichageFrise panelFrise) {
		this.panelFrise = panelFrise;
	}
	
}
