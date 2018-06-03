package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import modele.FriseChronologique;

public class PanelAffichage extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	PanelAffichageFrise panelFrise;
	
	public PanelAffichage(FriseChronologique parFrise) {
		
		setLayout(new BorderLayout());
		
		PanelAffichageEvt panelAffichageEvt = new PanelAffichageEvt();
		add(panelAffichageEvt, BorderLayout.NORTH);
		
		panelFrise = new PanelAffichageFrise(parFrise);
		add(panelFrise, BorderLayout.SOUTH);
	}

	public PanelAffichageFrise getPanelFrise() {
		return panelFrise;
	}

	public void setPanelFrise(PanelAffichageFrise panelFrise) {
		this.panelFrise = panelFrise;
	}
	
}
