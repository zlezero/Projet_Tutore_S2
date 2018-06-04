package vue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Evenement;

public class PanelAffichageEvt extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JLabel labels[] = new JLabel[4];
	
	public PanelAffichageEvt(Evenement parEvt) {
		
		
		labels[0] = new JLabel(parEvt.getDate().toString());
		labels[1] = new JLabel(parEvt.getTitre());
		labels[2] = new JLabel(parEvt.getChDescription());
		
		if (!parEvt.getChPhoto().isEmpty()) {
			labels[3] = new JLabel("");
			labels[3].setIcon(new ImageIcon(parEvt.getChPhoto()));
		}
		
		for (int i=0;i!=3;i++) {
			add(labels[i]);
		}
		
	}
	
}
