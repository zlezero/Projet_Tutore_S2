package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;

import controleur.Controleur;
import modele.ConstantesTextes;
import modele.Evenement;
import modele.FriseChronologique;

/**
 * Est le formulaire de création/modification d'un événement
 * @author Thomas Vathonne
 * @version 1
 */

public class PanelCreationAjoutEvt extends JPanel implements ConstantesTextes {

	private static final long serialVersionUID = 1L;

	private JButton boutonAjout, boutonPhoto, boutonAnnulation;
	private JTextField listeTextField[] = new JTextField[3];
	private JLabel listeLabels[] = new JLabel[6];
	private JTextArea textareaDescription = new JTextArea(5, 10);
	private JSpinner spinner;
	private FriseChronologique friseChronologique;
	
	private boolean estModification = false;
	
	public PanelCreationAjoutEvt(FriseChronologique parFrise) {
		
		friseChronologique = parFrise;
		
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

		//Ajout du bouton ajout

		contrainte.gridx = 0;
		contrainte.gridy = 6;

		boutonAjout = new JButton(CREATION_EVT_BOUTON_AJOUT);
		add(boutonAjout, contrainte);

		//Ajout du bouton annulation
		
		contrainte.gridx = 1;
		
		boutonAnnulation = new JButton(CREATION_EVT_BOUTON_ANNULATION);
		add(boutonAnnulation, contrainte);
		boutonAnnulation.setVisible(false);
		
		//Ajouts des JTextField

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
		
		JScrollPane scroll = new JScrollPane(textareaDescription, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scroll, contrainte);

		// Ajout des mnémoniques

		listeLabels[1].setDisplayedMnemonic('i');
		listeLabels[1].setLabelFor(listeTextField[0]);

		listeLabels[2].setDisplayedMnemonic('a');
		listeLabels[2].setLabelFor(listeTextField[1]);
		
		listeLabels[3].setDisplayedMnemonic('h');
		listeLabels[3].setLabelFor(listeTextField[2]);
		
		boutonPhoto.setMnemonic('j');
		
		listeLabels[4].setDisplayedMnemonic('o');
		listeLabels[4].setLabelFor(spinner);
		
		listeLabels[5].setDisplayedMnemonic('e');
		listeLabels[5].setLabelFor(textareaDescription);
		
		boutonAjout.setMnemonic('r');
		
		boutonAnnulation.setMnemonic('n');
		
		listeTextField[0].requestFocus();
	}
	
	public boolean isEstModification() {
		return estModification;
	}

	public void setEstModification(boolean estModification) {
		this.estModification = estModification;
	}

	public void resetUI() {
		for (int i=0;i!=listeTextField.length;i++) {
			listeTextField[i].setText("");
		}
		spinner.setValue(0);
		textareaDescription.setText("");
	}
	
	public void setEvt(Evenement parEvt) {
		
		listeTextField[0].setText(parEvt.getTitre());
		listeTextField[1].setText(parEvt.getDate().dateFormatee());
		listeTextField[2].setText(parEvt.getChPhoto());
		textareaDescription.setText(parEvt.getChDescription());
		
		int resultPoids = friseChronologique.getPoidsEvenement(parEvt);
		
		if (resultPoids != -1) 
			spinner.setValue(resultPoids);
		else 
			spinner.setValue(0);
		
		listeLabels[0].setText("Modification de l'événement");
		boutonAjout.setText(CREATION_EVT_BOUTON_MODIF);
		boutonAjout.setActionCommand(CREATION_EVT_BOUTON_AJOUT);
		boutonAnnulation.setVisible(true);
	}
	
	public void finirModification() {
		listeLabels[0].setText("Création d'un événement");
		boutonAjout.setText(CREATION_EVT_BOUTON_AJOUT);
		estModification = false;
		boutonAnnulation.setVisible(false);
		resetUI();
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
		boutonAnnulation.addActionListener(parC);
	}

}
