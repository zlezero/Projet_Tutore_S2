package vue;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import controleur.Controleur;
import modele.FriseChronologique;

public class PanelCreation extends JPanel {

	private static final long serialVersionUID = 1L;
	
	PanelAjoutEvt panelAjoutEvt;
	PanelCreationFrise panelCreationFrise;
	
	public PanelCreation(FriseChronologique parFrise) {
		
		setLayout(new BorderLayout());
		
		panelAjoutEvt = new PanelAjoutEvt();
		panelCreationFrise = new PanelCreationFrise();
		
		add(panelAjoutEvt, BorderLayout.EAST);
		add(panelCreationFrise, BorderLayout.WEST);	
		
		if (parFrise.isEstInitialisee()) {
			panelCreationFrise.setFrise(parFrise);
		}
		else {
			disablePanelComponents(panelAjoutEvt);
		}
	}
	
	public void disablePanelComponents(JPanel parPanel) {
		Component[] com = parPanel.getComponents();
		for (int a = 0; a < com.length; a++) {
		     com[a].setEnabled(false);
		}
	}
	
	public void activatePanelComponents(JPanel parPanel) {
		Component[] com = parPanel.getComponents();
		for (int a = 0; a < com.length; a++) {
		     com[a].setEnabled(true);
		}
	}
	
	public void enrengistreEcouteur(Controleur parC) {
		panelCreationFrise.enrengistreEcouteur(parC);
	}

	public PanelAjoutEvt getPanelAjoutEvt() {
		return panelAjoutEvt;
	}

	public PanelCreationFrise getPanelCreationFrise() {
		return panelCreationFrise;
	}
	
	
		
}
