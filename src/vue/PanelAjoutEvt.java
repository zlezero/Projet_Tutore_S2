package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modele.Date;

public class PanelAjoutEvt extends JPanel {

	private static final long serialVersionUID = 1L;

	public final static String INTITULE_BOUTON_AJOUT = "+";

	JButton boutonAjout;
	JTextField listeTextField[] = new JTextField[2];
	JComboBox<String> listeComboBox[] = new JComboBox[4];
	JLabel listeLabels[] = new JLabel[6];
	JTextArea textareaDescription = new JTextArea(5, 10);

	public PanelAjoutEvt() {

		setLayout(new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();

		// Ajouts des labels à gauche

		String intitulesLabels[] = { new Date().toString(), "Titre", "Lieu",
				"Début", "Fin", "Description" };

		contrainte.gridx = 0;
		contrainte.gridwidth = 1;
		contrainte.insets = new Insets(5, 10, 10, 10);
		contrainte.anchor = GridBagConstraints.WEST;

		for (int i = 0; i < intitulesLabels.length; i++) {
			contrainte.gridy = i;

			if (i == 0)
				contrainte.gridwidth = 2;
			else
				contrainte.gridwidth = 1;

			listeLabels[i] = new JLabel(intitulesLabels[i]);
			add(listeLabels[i], contrainte);
		}

		// Ajout du bouton ajout

		contrainte.gridx = 5;
		contrainte.gridy = 0;

		boutonAjout = new JButton(INTITULE_BOUTON_AJOUT);
		//boutonAjout.addActionListener(this);
		add(boutonAjout, contrainte);

		contrainte.gridx = 1;

		// Ajouts des JTextField

		contrainte.gridx = 1;
		contrainte.gridy = 1;

		for (int i = 0; i != 2; i++) {
			listeTextField[i] = new JTextField(15);
			add(listeTextField[i], contrainte);
			//listeTextField[i].addActionListener(this);
			contrainte.gridy += 1;
		}

		// Création des données pour les ComboBox

		String minutes[] = new String[60];
		String heures[] = new String[24];

		for (int i = 1; i != 25; i++)
			heures[i - 1] = String.valueOf(i);

		for (int i = 0; i != 60; i++)
			minutes[i] = String.valueOf(i);

		// Ajouts des JComboBox

		contrainte.gridx = 1;
		contrainte.gridy = 3;

		Calendar rightNow = Calendar.getInstance();
		contrainte.gridwidth = 2;

		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				listeComboBox[i] = new JComboBox(heures);
				listeComboBox[i].setSelectedIndex(rightNow
						.get(Calendar.HOUR_OF_DAY) - 1);
				break;
			case 1:
				contrainte.gridx = 3;
				listeComboBox[i] = new JComboBox(minutes);
				listeComboBox[i]
						.setSelectedIndex(rightNow.get(Calendar.MINUTE));
				break;
			case 2:
				contrainte.gridx = 1;
				contrainte.gridy = 4;
				listeComboBox[i] = new JComboBox(heures);
				listeComboBox[i].setSelectedIndex(rightNow
						.get(Calendar.HOUR_OF_DAY) - 1);
				break;
			case 3:
				contrainte.gridx = 3;
				listeComboBox[i] = new JComboBox(minutes);
				listeComboBox[i]
						.setSelectedIndex(rightNow.get(Calendar.MINUTE));
				break;
			}

			add(listeComboBox[i], contrainte);

		}

		// Ajout des labels entre les comboBoxs

		contrainte.gridwidth = 1;
		contrainte.gridx = 2;
		contrainte.gridy = 3;

		for (int i = 0; i != 2; i++) {
			add(new JLabel(" : "), contrainte);
			contrainte.gridy += 1;
		}

		// Ajout du JTextArea

		contrainte.gridx = 1;
		contrainte.gridy = 5;

		add(textareaDescription, contrainte);

		// Ajout des mnémoniques

		listeLabels[1].setDisplayedMnemonic('T');
		listeLabels[1].setLabelFor(listeTextField[0]);

		listeLabels[2].setDisplayedMnemonic('L');
		listeLabels[2].setLabelFor(listeTextField[1]);

		listeLabels[3].setDisplayedMnemonic('D');
		listeLabels[3].setLabelFor(listeComboBox[0]);

		listeLabels[4].setDisplayedMnemonic('F');
		listeLabels[4].setLabelFor(listeComboBox[2]);

		listeLabels[5].setDisplayedMnemonic('e');
		listeLabels[5].setLabelFor(textareaDescription);

		listeTextField[0].requestFocus();
	}

}
