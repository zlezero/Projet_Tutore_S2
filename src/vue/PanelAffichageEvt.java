package vue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Evenement;

public class PanelAffichageEvt extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JLabel labels[] = new JLabel[4]; //Création d'un tableau de JLabel, qui contiendra la date, le titre, la description et l'image associé à l'évènement
	
	public PanelAffichageEvt(Evenement parEvt) {
		
		//Permet d'obtenir l'affichage au niveau texte des évènements dans le cardLayout avec la date, le titre et la description de ceux-ci 		
		labels[0] = new JLabel(parEvt.getDate().toString());
		labels[1] = new JLabel("<html><h1>" + parEvt.getTitre() + "</h1></html>");
		labels[2] = new JLabel(parEvt.getChDescription());
		
		//Si l'évènement a une image qui lui est associée, on la lui attribue
		if (!parEvt.getChPhoto().isEmpty()) {
			labels[3] = new JLabel("");
			labels[3].setIcon(new ImageIcon(parEvt.getChPhoto()));
			//add(labels[3]);
		}
	
		//on ajoute tout ça pour pouvoir l'afficher réellement !
		for (int i=0;i!=3;i++) {
			add(labels[i]);
		}
		
	}
	
}
