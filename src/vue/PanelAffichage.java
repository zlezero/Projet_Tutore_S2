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

		setLayout(new BorderLayout());

		//On affiche la JTable
		
		panelFrise = new PanelAffichageFrise(parFrise);
		add(panelFrise, BorderLayout.SOUTH);
		
		//On affiche la partie haute de l'affichage
		
		JPanel panelNord = new JPanel();
		
		monGestionnaireDeCartes = new CardLayout(50, 50);
		panelNord.setLayout(monGestionnaireDeCartes);

		for (Evenement monEvt : parFrise.getListeEvenements()) {
			PanelAffichageEvt panelAffichageEvt = new PanelAffichageEvt(monEvt.getTitre(), monEvt.getDate(), monEvt.getChDescription());
			panelNord.add(panelAffichageEvt);
		}
		
		monGestionnaireDeCartes.first(panelNord);
				
		//On ajoute le titre de la frise
		titreFrise = new JLabel(parFrise.getTitreFrise());
		add(titreFrise, BorderLayout.NORTH);
		
		add(panelNord, BorderLayout.CENTER);
		
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
