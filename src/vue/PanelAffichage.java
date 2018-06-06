package vue;

/**
 * Est le panel principal où est affiché la frise et le card layout des différents événements
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

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

	private PanelAffichageFrise panelFrise;
	private JPanel panelNord, panelNordCL;
	private FriseChronologique friseChronologique;

	private CardLayout monGestionnaireDeCartes;

	private JLabel titreFrise, flecheGauche, flecheDroite;

	public PanelAffichage(FriseChronologique parFrise) {

		friseChronologique = parFrise;

		setLayout(new BorderLayout());

		// On affiche et on gère la partie haute de l'affichage

		monGestionnaireDeCartes = new CardLayout(50, 50);

		panelNord = new JPanel();
		panelNord.setLayout(new BorderLayout());

		panelNordCL = new JPanel();
		panelNordCL.setLayout(monGestionnaireDeCartes);

		resetCardLayout(); //On ajoute tous les événements dans le card layout

		monGestionnaireDeCartes.last(panelNordCL);

		panelNord.add(panelNordCL, BorderLayout.CENTER);

		// On ajoute le titre de la frise
		titreFrise = new JLabel(parFrise.getTitreFrise());

		titreFrise.setHorizontalAlignment(JLabel.CENTER);
		titreFrise.setVerticalAlignment(JLabel.CENTER);
		titreFrise.setFont(new Font("Dialog.plain", Font.BOLD, 20));

		panelNord.add(titreFrise, BorderLayout.NORTH);
		
		//On créer les flèches pour pouvoir faire défiler le diaporama
		
		flecheGauche = new JLabel("<html><h1>&lt;</h1></html>");
		flecheDroite = new JLabel("<html><h1>></h1></html>");

		panelNord.add(flecheGauche, BorderLayout.WEST);
		panelNord.add(flecheDroite, BorderLayout.EAST);

		add(panelNord, BorderLayout.NORTH);

		// On affiche la JTable

		panelFrise = new PanelAffichageFrise(parFrise, monGestionnaireDeCartes, panelNordCL);
		add(panelFrise, BorderLayout.SOUTH);

		// On gère les événements des flèches
		
		flecheGauche.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				// panelFrise.getScrollPane().getHorizontalScrollBar().setValue(panelFrise.getTableFrise().getColumn(Integer.toString(2004)).getWidth()
				// * 4);
				monGestionnaireDeCartes.previous(panelNordCL);
			}
		});

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
		System.out.println(friseChronologique.getListeEvenements());
		for (Evenement monEvt : friseChronologique.getListeEvenements()) {
			PanelAffichageEvt panelAffichageEvt = new PanelAffichageEvt(monEvt);
			panelNordCL.add(panelAffichageEvt, monEvt.toString());
		}
		
	}

	/**
	 * Méthode qui permet l'utilisation du Controleur, PanelFrise sera à l'écoute du
	 * controleur
	 * @param parC Le controleur qui doit se mettre à l'écoute
	 */

	public void enrengistreEcouteur(Controleur parC) {
		panelFrise.enrengistreEcouteur(parC);
	}

	/**
	 * Méthode qui permet de modifier le texte présent dans le panel Nord du
	 * BordelLayout
	 */

	public void updatePanelNord() {
		titreFrise.setText(friseChronologique.getTitreFrise());
	}

	/**
	 * Accesseur qui permet d'obtenir le PanelFrise
	 * 
	 * @return PanelFrise
	 */

	public PanelAffichageFrise getPanelFrise() {
		return panelFrise;
	}

	/**
	 * Modifieur qui permet de changer le panelFrise
	 * @param panelFrise Le panel frise que l'on veut mettre dans l'affichage
	 */

	public void setPanelFrise(PanelAffichageFrise panelFrise) {
		this.panelFrise = panelFrise;
	}

}