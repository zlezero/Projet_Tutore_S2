package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.Evenement;
import modele.FriseChronologique;

public class PanelAffichage extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	PanelAffichageFrise panelFrise;
	JPanel panelNord, panelNordCL;
	FriseChronologique friseChronologique;
	
	protected CardLayout monGestionnaireDeCartes;
	
	JLabel titreFrise, flecheGauche, flecheDroite;
	
	public PanelAffichage(FriseChronologique parFrise) {
		
		friseChronologique = parFrise;	

		setLayout(new BorderLayout());
		
		//On affiche la partie haute de l'affichage
		
		monGestionnaireDeCartes = new CardLayout(50, 50);

		panelNord = new JPanel();
		panelNord.setLayout(new BorderLayout());
		
		panelNordCL = new JPanel();
		panelNordCL.setLayout(monGestionnaireDeCartes);
		
		resetCardLayout();
		
		monGestionnaireDeCartes.last(panelNordCL);
				
		panelNord.add(panelNordCL, BorderLayout.CENTER);
		
		//On ajoute le titre de la frise
		titreFrise = new JLabel(parFrise.getTitreFrise());
		
		titreFrise.setHorizontalAlignment(JLabel.CENTER);
		titreFrise.setVerticalAlignment(JLabel.CENTER);
		titreFrise.setFont(new Font("Dialog.plain", Font.BOLD, 20));
		
		panelNord.add(titreFrise, BorderLayout.NORTH);

		flecheGauche = new JLabel("<html><h1>&lt;</h1></html>");
		flecheDroite = new JLabel("<html><h1>></h1></html>");
		
		panelNord.add(flecheGauche, BorderLayout.WEST);
		panelNord.add(flecheDroite, BorderLayout.EAST);

		add(panelNord, BorderLayout.NORTH);
		
		//On affiche la JTable
		
		panelFrise = new PanelAffichageFrise(parFrise, monGestionnaireDeCartes, panelNordCL);
		add(panelFrise, BorderLayout.SOUTH);
		//On gère les événements des flèches
		flecheGauche.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				monGestionnaireDeCartes.previous(panelNordCL);
			}
		});
		
		flecheDroite.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				monGestionnaireDeCartes.next(panelNordCL);
			}
		});
		
	}
	
	public void resetCardLayout() {
		panelNordCL.removeAll();
		for (Evenement monEvt : friseChronologique.getListeEvenements()) {
			PanelAffichageEvt panelAffichageEvt = new PanelAffichageEvt(monEvt);
			panelNordCL.add(panelAffichageEvt, monEvt.toString());
		}
	}
	
	public void enrengistreEcouteur(Controleur parC) {
		panelFrise.enrengistreEcouteur(parC);
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
