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

public class PanelCreationFrise extends JPanel implements ConstantesTextes {

	private static final long serialVersionUID = 1L;

	JButton boutonAjout;
	JTextField listeTextField[] = new JTextField[3];
	JLabel listeLabels[] = new JLabel[6];
	JSpinner spinner;
	
	public PanelCreationFrise() {

		setLayout(new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();

		//Ajouts des labels � gauche

		String intitulesLabels[] = { "Cr�ation de la frise", "Titre", "D�but", "Fin", "P�riode" };

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

		//Ajout du bouton cr�ation

		contrainte.gridx = 0;
		contrainte.gridy = 5;
		
		boutonAjout = new JButton(CREATION_FRISE_BOUTON_AJOUT);
		add(boutonAjout, contrainte);

		contrainte.gridx = 1;

		//Ajouts des JTextField

		contrainte.gridx = 1;
		contrainte.gridy = 1;
		
		for (int i = 0; i != 3; i++) {
			listeTextField[i] = new JTextField(15);
			add(listeTextField[i], contrainte);
			contrainte.gridy += 1;
		}
		
		//Ajouts du JSpinner pour choisir la p�riode
		contrainte.gridwidth = 1;
		contrainte.gridx = 1;
		contrainte.gridy = 4;
		
		spinner = new JSpinner(new SpinnerNumberModel());
		add(spinner, contrainte);
		
		//Ajout des mn�moniques

		listeLabels[1].setDisplayedMnemonic('T');
		listeLabels[1].setLabelFor(listeTextField[0]);

		listeLabels[2].setDisplayedMnemonic('L');
		listeLabels[2].setLabelFor(listeTextField[1]);

		listeTextField[0].requestFocus();

	}
	
	public void enrengistreEcouteur(Controleur parC) {
		boutonAjout.addActionListener(parC);
	}
	
	public void setFrise(FriseChronologique parFrise) {
		listeTextField[0].setText(parFrise.getTitre());
		listeTextField[1].setText(Integer.toString(parFrise.getDateDebut().getAnnee()));
		listeTextField[2].setText(Integer.toString(parFrise.getDateFin().getAnnee()));
		spinner.setValue(parFrise.getPeriode());
	}

	public JButton getBoutonAjout() {
		return boutonAjout;
	}

	public void setBoutonAjout(JButton boutonAjout) {
		this.boutonAjout = boutonAjout;
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

	public JSpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}
}