package vue;

import java.awt.BorderLayout;

import javax.swing.JPanel;
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
	}
	
}
