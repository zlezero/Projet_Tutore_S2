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
		
		friseChronologique = parFrise;
		
		setLayout(new BorderLayout());
		
		panelAjoutEvt = new PanelCreationAjoutEvt();
		panelCreationFrise = new PanelCreationFrise();
		
		add(panelAjoutEvt, BorderLayout.EAST);
		add(panelCreationFrise, BorderLayout.WEST);	
		
		if (friseChronologique.isEstInitialisee()) {
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
	
	public void updateComponents() {
		
		if (friseChronologique.isEstInitialisee()) {
			activatePanelComponents(panelAjoutEvt);
			panelCreationFrise.setFrise(friseChronologique);
		}
		
	}
	
	public void enrengistreEcouteur(Controleur parC) {
		panelCreationFrise.enrengistreEcouteur(parC);
		panelAjoutEvt.enrengistreEcouteur(parC);
	}

	public PanelCreationAjoutEvt getPanelAjoutEvt() {
		return panelAjoutEvt;
	}

	public PanelCreationFrise getPanelCreationFrise() {
		return panelCreationFrise;
	}
	
	
		
}
