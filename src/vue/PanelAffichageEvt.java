package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Evenement;

public class PanelAffichageEvt extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JLabel labels[] = new JLabel[4];
	
	public PanelAffichageEvt(Evenement parEvt) {
		
		//setBackground(new Color(100, 100, 100));
		
		setLayout(new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();
		
		contrainte.insets = new Insets(5, 10, 10, 0);
		contrainte.anchor = GridBagConstraints.WEST;
		
		labels[0] = new JLabel(parEvt.getDate().toString());
		
		labels[1] = new JLabel("<html><b>" + parEvt.getTitre() + "</b></html>");
		
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
			ImageIcon monImage = new ImageIcon(new ImageIcon(parEvt.getChPhoto()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			labels[3].setIcon(monImage);
			contrainte.gridx = 0;
			contrainte.gridy = 0;
			add(labels[3], contrainte);
		}
		
		contrainte.gridx = 1;
		contrainte.gridy = 0;
		
		for (int i=0;i!=3;i++) {
			add(labels[i], contrainte);
			contrainte.gridy += 1;
		}
		
	}
	
}
