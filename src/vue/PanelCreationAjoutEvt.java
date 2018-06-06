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
 * @author Yanis Levesque
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

		for (int i = 0; i < intitulesLabels.length; i++) { //Pour tous les intitulés de labels
			contrainte.gridy = i;

			if (i == 0)
				contrainte.gridwidth = 2;
			else
				contrainte.gridwidth = 1;

			listeLabels[i] = new JLabel(intitulesLabels[i]); //On créer un nouveau label
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
	
	/**
	 * Remet vide tout les champs du formulaire d'ajout d'événement 
	 */

	public void resetUI() {
		for (int i=0;i!=listeTextField.length;i++) { //Pout tout les textfields
			listeTextField[i].setText("");
		}
		spinner.setValue(0);
		textareaDescription.setText("");
	}
	
	/**
	 * Met les caractéristiques d'un événement dans le formulaire d'ajout d'événement
	 * @param parEvt L'événement à mettre
	 */
	
	public void setEvt(Evenement parEvt) {
		
		listeTextField[0].setText(parEvt.getTitre());
		listeTextField[1].setText(parEvt.getDate().dateFormatee());
		listeTextField[2].setText(parEvt.getChPhoto());
		textareaDescription.setText(parEvt.getChDescription());
		
		int resultPoids = friseChronologique.getPoidsEvenement(parEvt);
		
		if (resultPoids != -1) //Si il n'y a pas d'erreurs
			spinner.setValue(resultPoids);
		else //Sinon on met à 0
			spinner.setValue(0);
		
		listeLabels[0].setText("Modification de l'événement");
		boutonAjout.setText(CREATION_EVT_BOUTON_MODIF);
		boutonAjout.setActionCommand(CREATION_EVT_BOUTON_AJOUT);
		boutonAnnulation.setVisible(true);
	}
	
	/**
	 * Permet de remettre le formulaire d'ajout d'événements prêt après une modification
	 */
	
	public void finirModification() {
		listeLabels[0].setText("Création d'un événement");
		boutonAjout.setText(CREATION_EVT_BOUTON_AJOUT);
		estModification = false;
		boutonAnnulation.setVisible(false);
		resetUI();
	}
	
	/**
	 * Permet à un controleur de se mettre à l'écoute des boutons du formulaire
	 * @param parC Un controleur
	 */
	
	public void enrengistreEcouteur(Controleur parC) {
		boutonAjout.addActionListener(parC);
		boutonPhoto.addActionListener(parC);
		boutonAnnulation.addActionListener(parC);
	}
	
	/**
	 * Accesseur du bouton d'ajout
	 * @return Un composant JButton contenant le bouton d'ajout
	 */
	
	public JButton getBoutonAjout() {
		return boutonAjout;
	}
	
	/**
	 * Modifieur du bouton d'ajout
	 * @param boutonAjout Un JButton
	 */

	public void setBoutonAjout(JButton boutonAjout) {
		this.boutonAjout = boutonAjout;
	}
	
	/**
	 * Accesseur du bouton pour choisir une photo
	 * @return Un composant JButton contenant le bouton de photo
	 */
	
	public JButton getBoutonPhoto() {
		return boutonPhoto;
	}
	
	/**
	 * Modifieur du bouton de photo
	 * @param boutonPhoto Un JButton
	 */
	
	public void setBoutonPhoto(JButton boutonPhoto) {
		this.boutonPhoto = boutonPhoto;
	}
	
	/**
	 * Accesseur de la liste des textFields
	 * @return Un tableau de JTextField
	 */
	
	public JTextField[] getListeTextField() {
		return listeTextField;
	}
	
	/**
	 * Modifieur des textFields
	 * @param listeTextField Un tableau de JTextField
	 */
	
	public void setListeTextField(JTextField[] listeTextField) {
		this.listeTextField = listeTextField;
	}
	
	/**
	 * Accesseur de la liste des labels
	 * @return Un tableau de JLabel
	 */
	
	public JLabel[] getListeLabels() {
		return listeLabels;
	}
	
	/**
	 * Modifieur de la liste des labels
	 * @param listeLabels Un tableau de JLabel
	 */
	
	public void setListeLabels(JLabel[] listeLabels) {
		this.listeLabels = listeLabels;
	}
	
	/**
	 * Accesseur du textarea de la description d'un événement
	 * @return Un composant JTextArea
	 */
	
	public JTextArea getTextareaDescription() {
		return textareaDescription;
	}
	
	/**
	 * Modifieur du textarea de la description d'un événement
	 * @param textareaDescription Un composant JTextArea
	 */
	
	public void setTextareaDescription(JTextArea textareaDescription) {
		this.textareaDescription = textareaDescription;
	}
	
	/**
	 * Accesseur du spinner
	 * @return Un composant JSpinner
	 */
	
	public JSpinner getSpinner() {
		return spinner;
	}
	
	/**
	 * Modifieur du spinner
	 * @param spinner Un composant JSpinner
	 */
	
	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}

}
