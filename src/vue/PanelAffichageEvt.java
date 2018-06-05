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
		labels[1] = new JLabel("<html><h1>" + parEvt.getTitre() + "</h1></html>");
		
		String messageLabelDesc = "<html>";
		int compteurMots = 0;
		
		for (int i=0;i!=parEvt.getChDescription().split(" ").length;i++) {
			
			messageLabelDesc += parEvt.getChDescription().split(" ")[i] + " ";
			compteurMots += 1;
			
			if (compteurMots==12) {
				compteurMots = 0;
				messageLabelDesc += "<br>";
			}

		}
		
		messageLabelDesc += "</html>";
		
		labels[2] = new JLabel(messageLabelDesc);

		if (!parEvt.getChPhoto().isEmpty()) {
			labels[3] = new JLabel("");
			labels[3].setIcon(new ImageIcon(parEvt.getChPhoto()));
			//add(labels[3]);
		}
		
		for (int i=0;i!=3;i++) {
			add(labels[i]);
		}
		
	}
	
}
