package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
		panelNord.add(titreFrise, BorderLayout.NORTH);
		
		flecheGauche = new JLabel("<");
		flecheDroite = new JLabel(">");
		
		panelNord.add(flecheGauche, BorderLayout.WEST);
		panelNord.add(flecheDroite, BorderLayout.EAST);

		add(panelNord, BorderLayout.NORTH);
		
		//On affiche la JTable
		
		panelFrise = new PanelAffichageFrise(parFrise, monGestionnaireDeCartes, panelNordCL);
		add(panelFrise, BorderLayout.SOUTH);
		
		//On gère les événements de pour la flèche gauche du cardLayout
		flecheGauche.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				monGestionnaireDeCartes.previous(panelNordCL);
			}
		});
		
		//On gère les événements de pour la flèche droite du cardLayout
		flecheDroite.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				monGestionnaireDeCartes.next(panelNordCL);
			}
		});
		
	}
	
	/**
	* Méthode qui permet de reset le CardLayout
	*/
	
	public void resetCardLayout() {
		panelNordCL.removeAll();
		for (Evenement monEvt : friseChronologique.getListeEvenements()) {
			PanelAffichageEvt panelAffichageEvt = new PanelAffichageEvt(monEvt);
			panelNordCL.add(panelAffichageEvt, monEvt.toString());
		}
	}
	
	/**
	* Méthode qui permet l'utilisation du Controleur, PanelFrise sera à l'écoute du controleur
	*/
	
	public void enrengistreEcouteur(Controleur parC) {
		panelFrise.enrengistreEcouteur(parC);
	}
	
	/**
	* Méthode qui permet de modifier le texte présent dans le panel Nord du BordelLayout 
	*/
	
	public void updatePanelNord() {
		titreFrise.setText(friseChronologique.getTitreFrise());
	}
	
	/**
	* Accesseur qui permet d'obtenir le PanelFrise
	* @return PanelFrise
	*/

	public PanelAffichageFrise getPanelFrise() {
		return panelFrise;
	}

	/**
	* Modifieur qui permet de changer le panelFrise
	*/
	
	public void setPanelFrise(PanelAffichageFrise panelFrise) {
		this.panelFrise = panelFrise;
	}
	
}
