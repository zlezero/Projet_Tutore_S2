package vue;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Date;

public class PanelAffichageEvt extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JLabel labels[] = new JLabel[3];
	
	public PanelAffichageEvt(String parTitre, Date parDate, String parDescription) {
		
		labels[0] = new JLabel(parDate.toString());
		labels[1] = new JLabel(parTitre);
		labels[2] = new JLabel(parDescription);
		
		for (int i=0;i!=2;i++) {
			add(labels[i]);
		}
		
	}
	
}
