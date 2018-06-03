package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.FriseChronologique;
import vue.PanelAffichage;

public class Controleur implements ActionListener {
	
	private FriseChronologique friseChronologique;
	private PanelAffichage panelAP;
	
	public Controleur(FriseChronologique parFrise, PanelAffichage parPanelAP) {
		friseChronologique = parFrise;
		panelAP = parPanelAP;
	}
	
	
	public void actionPerformed(ActionEvent arg0) {		
	}

}
