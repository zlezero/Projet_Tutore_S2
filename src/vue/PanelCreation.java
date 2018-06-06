package vue;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import controleur.Controleur;
import modele.FriseChronologique;

/**
 * Est le panel principal du menu de création d'événements qui contient le formulaire de création de frise et celui de création d'événement
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

public class PanelCreation extends JPanel {

	private static final long serialVersionUID = 1L;

	private PanelCreationAjoutEvt panelAjoutEvt;
	private PanelCreationFrise panelCreationFrise;
	private FriseChronologique friseChronologique;

	public PanelCreation(FriseChronologique parFrise) {

		friseChronologique = parFrise; // Nous créeons une frise chronologique

		setLayout(new BorderLayout()); // Nous définissons le layout en tant que BorderLayout

		//On instancie les 2 sous-panels
		panelAjoutEvt = new PanelCreationAjoutEvt(friseChronologique);
		panelCreationFrise = new PanelCreationFrise();

		// Nous rajoutons les panels a l'affichage (layout)
		add(panelAjoutEvt, BorderLayout.EAST);
		add(panelCreationFrise, BorderLayout.WEST);

		// Lorsque que la frise chronologique est déjà initialisée
		if (friseChronologique.isEstInitialisee()) {
			panelCreationFrise.setFrise(parFrise); // On ajoute ses caractéristiques dans le formulaire
		} else {
			disablePanelComponents(panelAjoutEvt); // Sinon nous désactivons les composants du panel de création d'événement
		}
	}

	/**
	 * Méthode qui permet de désactiver les composants pour la JPanel
	 * @param parPanel Le panel dont on veut désactiver les composants
	 */

	public void disablePanelComponents(JPanel parPanel) {
		Component[] com = parPanel.getComponents();
		for (int a = 0; a < com.length; a++) { //Pour tout les composants du JPanel
			com[a].setEnabled(false); //On les désactive
		}
	}

	/**
	 * Méthode qui permet d'activer les composants pour la JPanel
	 * @param parPanel Le panel dont on veut activer les composants
	 */

	public void activatePanelComponents(JPanel parPanel) {
		Component[] com = parPanel.getComponents();
		for (int a = 0; a < com.length; a++) { //Pour tout les composants du JPanel
			com[a].setEnabled(true); //On les active
		}
	}

	/**
	 * Méthode qui met à jour les composants de la JPanel
	 */

	public void updateComponents() {

		if (friseChronologique.isEstInitialisee()) { //Si la frise chronologique est initialisée
			activatePanelComponents(panelAjoutEvt); //Alors on active le panel d'ajout d'événement
			panelCreationFrise.setFrise(friseChronologique); //Et on met les informations de la frise dans le formulaire
		}

	}

	/**
	 * Méthode qui met à l'écoute le controleur pour panelCreationFrise et
	 * panelAjout
	 * @param parC Le controleur qui doit se mettre à l'écoute
	 */

	public void enrengistreEcouteur(Controleur parC) { //
		panelCreationFrise.enrengistreEcouteur(parC);
		panelAjoutEvt.enrengistreEcouteur(parC);
	}

	/**
	 * Accesseur qui permet d'obtenir la friseChronologique
	 * 
	 * @return friseChronologique
	 */

	public FriseChronologique getFriseChronologique() {
		return friseChronologique;
	}

	/**
	 * Modifieur qui permet de modifier la frise chronologique
	 * @param friseChronologique La frise chronologique
	 */

	public void setFriseChronologique(FriseChronologique friseChronologique) {
		this.friseChronologique = friseChronologique;
	}

	/**
	 * Accesseur qui permet d'obtenir le panelAjoutEvt
	 * 
	 * @return panelAjoutEvt
	 */

	public PanelCreationAjoutEvt getPanelAjoutEvt() {
		return panelAjoutEvt;
	}

	/**
	 * Accesseur qui permet d'obtenir le panelCreationFrise
	 * 
	 * @return panelCreationFrise
	 */

	public PanelCreationFrise getPanelCreationFrise() {
		return panelCreationFrise;
	}
}
