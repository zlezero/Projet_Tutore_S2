package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Evenement;
import modele.FriseChronologique;

public class PanelAffichage extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	PanelAffichageFrise panelFrise;
	FriseChronologique friseChronologique;
	
	CardLayout monGestionnaireDeCartes;
	
	JLabel titreFrise;
	
	public PanelAffichage(FriseChronologique parFrise) {
		
		friseChronologique = parFrise;
		
		monGestionnaireDeCartes = new CardLayout(50, 50);
		setLayout(monGestionnaireDeCartes);
		
		for (Evenement monEvt : parFrise.getListeEvenements()) {
			PanelAffichageEvt panelAffichageEvt = new PanelAffichageEvt(monEvt.getTitre(), monEvt.getDate(), monEvt.getChDescription());
			add(panelAffichageEvt);
		}
		
		setLayout(new BorderLayout());
		
		panelFrise = new PanelAffichageFrise(parFrise);
		add(panelFrise, BorderLayout.SOUTH);

		JPanel panelNord = new JPanel();
		
		//On ajoute le titre de la frise
		titreFrise = new JLabel(parFrise.getTitreFrise());
		panelNord.add(titreFrise);
		
		add(panelNord, BorderLayout.NORTH);
		
	}
	
	public void updatePanelNord() {
		titreFrise.setText(friseChronologique.getTitreFrise());
	}

	public PanelAffichageFrise getPanelFrise() {
		return panelFrise;
	}

	public void setPanelFrise(PanelAffichageFrise panelFrise) {
		this.panelFrise = panelFrise;
	}
	
}
