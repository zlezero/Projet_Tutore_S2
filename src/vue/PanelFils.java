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
		
		while (true) {
			
			String intitulesBoutons[] = {"Nouvelle frise", "Ouvrir une frise"};	
			int resultat = JOptionPane.showOptionDialog(this, "Choisissez une option : ", "Bienvenue !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, intitulesBoutons, intitulesBoutons[0]);
			
			if (resultat == JOptionPane.YES_OPTION) { //Si l'on veut créer une nouvelle frise
				return new FriseChronologique(); //Alors on renvoit juste une frise vide
			}
			else if (resultat == JOptionPane.NO_OPTION) { //Si l'on veut ouvrir une nouvelle frise
				
				JFileChooser ouvrirFrise = new JFileChooser();
				int resultatOuverture = ouvrirFrise.showOpenDialog(this); //Alors on demande à l'utilisateur d'ouvrir le fichier correspondant
				
				if (resultatOuverture == JFileChooser.APPROVE_OPTION) { //Si l'utilisateur a sélectionné un fichier
					
					File monFichier = ouvrirFrise.getSelectedFile(); //On récupère ce fichier
					
					try {
						FriseChronologique maFrise = (FriseChronologique) LectureEcriture.lecture(monFichier); //Et on le retourne déserialisé
						return maFrise;
					}
					catch (FileNotFoundException e) {
						e.printStackTrace();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
					finally {
						JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'ouverture du fichier !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
									
				} //if
				
			} //else if
			
		} //while
			
	} //affichageDemarrage()
	
	
	public void actionPerformed(ActionEvent evt) {
		
		//On gère le menu
		
		if (evt.getActionCommand().equals(MENU_QUITTER)) { //Si l'utilisateur veut quitter
			
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
		else if (evt.getActionCommand().equals(MENU_AIDE)) { //Affichage du menu d'aide
			JOptionPane.showMessageDialog(this, "Frise Chronologique 1.0 créer par Thomas Vathonne et Yanis Levesque", "Aide", JOptionPane.INFORMATION_MESSAGE);
		}
		else { //Affichage des autres panels
			monGestionnaireDeCartes.show(this, evt.getActionCommand());
		}
		
	}

}
