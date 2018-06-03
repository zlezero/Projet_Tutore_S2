package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import modele.Evenement;
import modele.FriseChronologique;

public class PanelAffichage extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	PanelAffichageFrise panelFrise;
	
	CardLayout monGestionnaireDeCartes;
	
	public PanelAffichage(FriseChronologique parFrise) {
		
		setLayout(new BorderLayout());
		monGestionnaireDeCartes = new CardLayout(50, 50);
		
		for (Evenement monEvt : parFrise.getListeEvenements()) {
			PanelAffichageEvt panelAffichageEvt = new PanelAffichageEvt(monEvt.getTitre(), monEvt.getDate(), monEvt.getChDescription());
			add(panelAffichageEvt, BorderLayout.NORTH);
		}
		
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
