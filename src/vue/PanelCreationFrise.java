package vue;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controleur.Controleur;
import modele.ConstantesTextes;
import modele.FriseChronologique;

/**
 * Est le formulaire de création/modification d'une frise
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

public class PanelCreationFrise extends JPanel implements ConstantesTextes {

	private static final long serialVersionUID = 1L;

	private JButton boutonAjout, boutonSauvegarde;
	private JTextField listeTextField[] = new JTextField[3];
	private JLabel listeLabels[] = new JLabel[6];
	private JSpinner spinner;
	
	public PanelCreationFrise() {

		setLayout(new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();

		//Ajouts des labels à gauche

		String intitulesLabels[] = { "Création de la frise", "Titre", "Début (Année)", "Fin (Année)", "Période" };

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

		//Ajout du bouton création

		contrainte.gridx = 0;
		contrainte.gridy = 5;
		
		boutonAjout = new JButton(CREATION_FRISE_BOUTON_AJOUT);
		add(boutonAjout, contrainte);
		
		contrainte.gridx = 1;

		boutonSauvegarde = new JButton(CREATION_FRISE_BOUTON_SAUVEGARDE);
		add(boutonSauvegarde, contrainte);
		boutonSauvegarde.setVisible(false);
		
		//Ajouts des JTextField

		contrainte.gridx = 1;
		contrainte.gridy = 1;
		
		for (int i = 0; i != 3; i++) {
			listeTextField[i] = new JTextField(15);
			add(listeTextField[i], contrainte);
			contrainte.gridy += 1;
		}
		
		//Ajouts du JSpinner pour choisir la période
		
		contrainte.gridwidth = 1;
		contrainte.gridx = 1;
		contrainte.gridy = 4;
		
		spinner = new JSpinner(new SpinnerNumberModel());
		spinner.setValue(1);
		add(spinner, contrainte);
		
		//Ajout des mnémoniques

		listeLabels[1].setDisplayedMnemonic('T');
		listeLabels[1].setLabelFor(listeTextField[0]);

		listeLabels[2].setDisplayedMnemonic('D');
		listeLabels[2].setLabelFor(listeTextField[1]);
		
		listeLabels[3].setDisplayedMnemonic('F');
		listeLabels[3].setLabelFor(listeTextField[2]);
		
		listeLabels[4].setDisplayedMnemonic('P');
		listeLabels[4].setLabelFor(spinner);
		
		boutonAjout.setMnemonic('C');
		
		boutonSauvegarde.setMnemonic('S');
		
		listeTextField[0].requestFocus();

	}
	
	public void enrengistreEcouteur(Controleur parC) {
		boutonAjout.addActionListener(parC);
		boutonSauvegarde.addActionListener(parC);
	}
	
	/**
	 * Permet de mettre les informations d'une frise chronologique dans le formulaire
	 * @param parFrise La frise chronologique à mettre
	 */
	
	public void setFrise(FriseChronologique parFrise) {
		if (parFrise.isEstInitialisee()) {
			listeLabels[0].setText("Modification de la frise");
			boutonAjout.setText("Modifier la frise");
			boutonAjout.setActionCommand(CREATION_FRISE_BOUTON_AJOUT);
			boutonAjout.setMnemonic('M');
		}
		listeTextField[0].setText(parFrise.getTitreFrise());
		listeTextField[1].setText(Integer.toString(parFrise.getDateDebut().getAnnee()));
		listeTextField[2].setText(Integer.toString(parFrise.getDateFin().getAnnee()));
		spinner.setValue(parFrise.getPeriodeFrise());
		boutonSauvegarde.setVisible(true);
	}
	
	/**
	 * Accesseur du bouton d'ajout
	 * @return Un JButton contenant le bouton d'ajout
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
	 * Accesseur de la liste des différents texteFields
	 * @return Les différents textFields
	 */
	
	public JTextField[] getListeTextField() {
		return listeTextField;
	}
	
	/**
	 * Modifieur de la liste des textFields
	 * @param listeTextField Un tableau de textFields
	 */
	
	public void setListeTextField(JTextField[] listeTextField) {
		this.listeTextField = listeTextField;
	}
	
	/**
	 * Accesseur de la liste des labels
	 * @return Un tableau contenant la liste des labels
	 */
	
	public JLabel[] getListeLabels() {
		return listeLabels;
	}
	
	/**
	 * Modifieur de la liste des labels
	 * @param listeLabels Un tableau de labels
	 */
	
	public void setListeLabels(JLabel[] listeLabels) {
		this.listeLabels = listeLabels;
	}
	
	/**
	 * Accesseur du JSpinner
	 * @return Un composant JSpinner
	 */
	
	public JSpinner getSpinner() {
		return spinner;
	}
	
	/**
	 * Modifieur du JSpinner
	 * @param spinner Un JSpinner
	 */
	
	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}
}
