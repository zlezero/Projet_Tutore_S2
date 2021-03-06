package vue;

/**
 * Génère une mise en page d'un événement en fonction de celui-ci
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

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

	private JLabel labels[] = new JLabel[4]; // Création d'un tableau de JLabel, qui contiendra la date, le titre, la
											 // description et l'image associé à l'évènement
	public PanelAffichageEvt(Evenement parEvt) {

		// Permet d'obtenir l'affichage au niveau texte des évènements dans le
		// cardLayout avec la date, le titre et la description de ceux-ci

		// setBackground(new Color(100, 100, 100));

		setLayout(new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();

		contrainte.insets = new Insets(5, 10, 10, 0);
		contrainte.anchor = GridBagConstraints.WEST;
		
		labels[0] = new JLabel(parEvt.getDate().toString()); //Label de la date de l'evt

		labels[1] = new JLabel("<html><b>" + parEvt.getTitre() + "</b></html>"); //Label du titre que l'on met en gras

		//On formatte la description de l'événement
		
		String messageLabelDesc = "<html>";
		int compteurMots = 0;

		for (int i = 0; i != parEvt.getChDescription().split(" ").length; i++) {

			messageLabelDesc += parEvt.getChDescription().split(" ")[i] + " ";
			compteurMots += 1;

			if (compteurMots == 12) { //On ajoute un saut de ligne tout les 12 mots
				compteurMots = 0;
				messageLabelDesc += "<br>";
			}

		}

		messageLabelDesc += "</html>";

		labels[2] = new JLabel(messageLabelDesc); //Label du message de description

		if (!parEvt.getChPhoto().isEmpty()) { // Si l'évènement a une image qui lui est associée, on la lui attribue
			labels[3] = new JLabel("");
			ImageIcon monImage = new ImageIcon(
					new ImageIcon(parEvt.getChPhoto()).getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
			labels[3].setIcon(monImage);
			contrainte.gridx = 0;
			contrainte.gridy = 0;
			add(labels[3], contrainte);
		}

		// On ajoute tout ça pour pouvoir l'afficher réellement !

		contrainte.gridx = 1;
		contrainte.gridy = 0;
		
		for (int i = 0; i != 3; i++) {
			add(labels[i], contrainte);
			contrainte.gridy += 1;
		}

	}

}
