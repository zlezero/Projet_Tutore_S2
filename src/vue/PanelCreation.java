package vue;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import controleur.Controleur;
import modele.FriseChronologique;

public class PanelCreation extends JPanel {

	private static final long serialVersionUID = 1L;
	
	PanelCreationAjoutEvt panelAjoutEvt;
	PanelCreationFrise panelCreationFrise;
	FriseChronologique friseChronologique;
	
	public PanelCreation(FriseChronologique parFrise) {
		
		friseChronologique = parFrise; //Nous créeons une frise chronologique
		
		setLayout(new BorderLayout()); //Nous définissons le layout en tant que BorderLayout
		
		panelAjoutEvt = new PanelCreationAjoutEvt(friseChronologique);
		panelCreationFrise = new PanelCreationFrise();
		
		//Nous rajoutons les éléments a l'affichage (layout)
		add(panelAjoutEvt, BorderLayout.EAST);
		add(panelCreationFrise, BorderLayout.WEST);	
		
		//Lorsque que la frise chronologique est initialisé 
		if (friseChronologique.isEstInitialisee()) {
			panelCreationFrise.setFrise(parFrise); //on ajoute ses caractéristiques dans le formulaire
		}
		else { 
			disablePanelComponents(panelAjoutEvt); //sinon nous désactivons les composants 
		}
	}
	
	/**
	*Méthode qui permet de désactiver les composants pour la JPanel
	*/	
	
	public void disablePanelComponents(JPanel parPanel) {
		Component[] com = parPanel.getComponents();
		for (int a = 0; a < com.length; a++) {
		     com[a].setEnabled(false);
		}
	}
	
	/**
	* Méthode qui permet d'activer les composants pour la JPanel
	*/
	
	public void activatePanelComponents(JPanel parPanel) {
		Component[] com = parPanel.getComponents();
		for (int a = 0; a < com.length; a++) {
		     com[a].setEnabled(true);
		}
	}
	
	/**
	* Méthode qui met à jour les composants de la JPanel 
	*/	
	
	public void updateComponents() {
		
		if (friseChronologique.isEstInitialisee()) {
			activatePanelComponents(panelAjoutEvt);
			panelCreationFrise.setFrise(friseChronologique);
		}
		
	}
	
	/**
	* Méthode qui met à l'écoute le controleur pour panelCreationFrise et panelAjout  
	*/	
	
	public void enrengistreEcouteur(Controleur parC) {
		panelCreationFrise.enrengistreEcouteur(parC);
		panelAjoutEvt.enrengistreEcouteur(parC);
	}
	
	/**
	* Accesseur qui permet d'obtenir la friseChronologique
	* @return friseChronologique
	*/

	public FriseChronologique getFriseChronologique() {
		return friseChronologique;
	}
	
	/**
	* Modifieur qui permet de modifier la frise chronologique 
	*/

	public void setFriseChronologique(FriseChronologique friseChronologique) {
		this.friseChronologique = friseChronologique;
	}
	
	/**
	* Accesseur qui permet d'obtenir le panelAjoutEvt
	* @return panelAjoutEvt
	*/
	
	public PanelCreationAjoutEvt getPanelAjoutEvt() {
		return panelAjoutEvt;
	}

	/**
	* Accesseur qui permet d'obtenir le panelCreationFrise
	* @return panelCreationFrise
	*/
	
	public PanelCreationFrise getPanelCreationFrise() {
		return panelCreationFrise;
	}
}
