package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controleur.Controleur;
import modele.ConstantesTextes;
import modele.Date;

public class PanelCreationAjoutEvt extends JPanel implements ConstantesTextes {

	private static final long serialVersionUID = 1L;

	JButton boutonAjout, boutonPhoto;
	JTextField listeTextField[] = new JTextField[4];
	JLabel listeLabels[] = new JLabel[6];
	JTextArea textareaDescription = new JTextArea(5, 10);
	JSpinner spinner;
	
	public PanelCreationAjoutEvt() {

		setLayout(new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();

		// Ajouts des labels à gauche

		String intitulesLabels[] = {"Création d'un événement", "Titre", "Date (JJ/MM/AAAA)",
				"Photo", "Poids", "Description"};

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

		contrainte.gridx = 0;
		contrainte.gridy = 6;

		boutonAjout = new JButton(CREATION_EVT_BOUTON_AJOUT);
		add(boutonAjout, contrainte);

		contrainte.gridx = 1;

		// Ajouts des JTextField

		contrainte.gridx = 1;
		contrainte.gridy = 1;

		for (int i = 0; i != 3; i++) {
			listeTextField[i] = new JTextField(15);
			add(listeTextField[i], contrainte);
			contrainte.gridy += 1;
		}
		
		//Ajout du JSpinner
		
		spinner = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
		add(spinner, contrainte);
		
		// Ajout du bouton choix de photo
		
		contrainte.gridy = 3;
		contrainte.gridx = 3;
		
		boutonPhoto = new JButton(CREATION_EVT_BOUTON_PHOTO);
		add(boutonPhoto, contrainte);
		
		// Ajout du JTextArea

		contrainte.gridx = 1;
		contrainte.gridy = 5;

		add(textareaDescription, contrainte);

		// Ajout des mnémoniques

		listeLabels[1].setDisplayedMnemonic('T');
		listeLabels[1].setLabelFor(listeTextField[0]);

		listeLabels[2].setDisplayedMnemonic('L');
		listeLabels[2].setLabelFor(listeTextField[1]);

		listeTextField[0].requestFocus();
	}
	
	public JButton getBoutonAjout() {
		return boutonAjout;
	}

	public void setBoutonAjout(JButton boutonAjout) {
		this.boutonAjout = boutonAjout;
	}

	public JButton getBoutonPhoto() {
		return boutonPhoto;
	}

	public void setBoutonPhoto(JButton boutonPhoto) {
		this.boutonPhoto = boutonPhoto;
	}

	public JTextField[] getListeTextField() {
		return listeTextField;
	}

	public void setListeTextField(JTextField[] listeTextField) {
		this.listeTextField = listeTextField;
	}

	public JLabel[] getListeLabels() {
		return listeLabels;
	}

	public void setListeLabels(JLabel[] listeLabels) {
		this.listeLabels = listeLabels;
	}

	public JTextArea getTextareaDescription() {
		return textareaDescription;
	}

	public void setTextareaDescription(JTextArea textareaDescription) {
		this.textareaDescription = textareaDescription;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}

	public void enrengistreEcouteur(Controleur parC) {
		boutonAjout.addActionListener(parC);
		boutonPhoto.addActionListener(parC);
	}

}
