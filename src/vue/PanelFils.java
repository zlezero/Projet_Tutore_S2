package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.ConstantesTextes;
import modele.FriseChronologique;
import modele.LectureEcriture;

public class PanelFils extends JPanel implements ActionListener, ConstantesTextes {
	
	private static final long serialVersionUID = 1L;
	
	CardLayout monGestionnaireDeCartes;
	
	public PanelFils() {
		
		monGestionnaireDeCartes = new CardLayout(50, 50);
		setLayout(monGestionnaireDeCartes);
		
		FriseChronologique maFrise = affichageDemarrage();
		
	}
	
	public FriseChronologique affichageDemarrage() {
		
		String intitulesBoutons[] = {"Nouvelle frise", "Ouvrir une frise"};	
		int resultat = JOptionPane.showOptionDialog(this, "Choisissez une option : ", "Bienvenue !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, intitulesBoutons, intitulesBoutons[0]);
		
		if (resultat == JOptionPane.NO_OPTION) {
			
			JFileChooser ouvrirFrise = new JFileChooser();
			int resultatOuverture = ouvrirFrise.showOpenDialog(this);
			
			if (resultatOuverture == JFileChooser.APPROVE_OPTION) {
				
				File monFichier = ouvrirFrise.getSelectedFile();
				
				try {
					return (FriseChronologique) LectureEcriture.lecture(monFichier);
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
					return new FriseChronologique();
				} 
				catch (IOException e) {
					e.printStackTrace();
					return new FriseChronologique();
				}
								
			}
			
		}
		else {
			return new FriseChronologique();
		}
		
		return new FriseChronologique();
		
	}
	
	
	public void actionPerformed(ActionEvent evt) {
		
		//On gère le menu
		if (evt.getActionCommand().equals(MENU_QUITTER)) {
			
			int resultat = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter ?", "Quitter ?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			switch (resultat) {
				case JOptionPane.CLOSED_OPTION:
					break;
				case JOptionPane.OK_OPTION:
					System.exit(0);
					break;
				case JOptionPane.CANCEL_OPTION:
					break;
			}
		} 
		else if (evt.getActionCommand().equals(MENU_AIDE)) {
			JOptionPane.showMessageDialog(this, "Frise Chronologique 1.0 créer par Thomas Vathonne et Yanis Levesque", "Aide", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			monGestionnaireDeCartes.show(this, evt.getActionCommand());
		}
		
	}

}
