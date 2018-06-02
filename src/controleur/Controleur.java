package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.FriseChronologique;
import vue.PanelAffichagePrincipal;

public class Controleur implements ActionListener {
	
	private FriseChronologique friseChronologique;
	private PanelAffichagePrincipal panelAP;
	
	public Controleur(FriseChronologique parFrise, PanelAffichagePrincipal parPanelAP) {
		friseChronologique = parFrise;
		panelAP = parPanelAP;
	}
	
	
	public void actionPerformed(ActionEvent arg0) {		
	}

}
