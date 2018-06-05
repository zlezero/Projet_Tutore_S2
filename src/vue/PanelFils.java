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

import controleur.Controleur;
import modele.ConstantesTextes;
import modele.FriseChronologique;
import modele.LectureEcriture;

/**
 * Est le panel principal regroupant les différents menus (Création/Affichage)
 * @author Thomas Vathonne
 * @version 1
 */

public class PanelFils extends JPanel implements ActionListener, ConstantesTextes {

	private static final long serialVersionUID = 1L;

	private CardLayout monGestionnaireDeCartes;
	private FriseChronologique maFrise;
	private PanelCreation panelCreation;
	
	public PanelFils() {

		monGestionnaireDeCartes = new CardLayout(50, 50);
		setLayout(monGestionnaireDeCartes);

		maFrise = null;

		while (true) {

			String intitulesBoutons[] = {"Nouvelle frise", "Ouvrir une frise"};	
			int resultat = JOptionPane.showOptionDialog(this, "Choisissez une option : ", "Bienvenue !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, intitulesBoutons, intitulesBoutons[0]);

			if (resultat == JOptionPane.YES_OPTION) { //Si l'on veut créer une nouvelle frise
				maFrise = new FriseChronologique(); //Alors on renvoit juste une frise vide
				break;
			}
			else if (resultat == JOptionPane.NO_OPTION) { //Si l'on veut ouvrir une nouvelle frise

				JFileChooser ouvrirFrise = new JFileChooser();
				int resultatOuverture = ouvrirFrise.showOpenDialog(this); //Alors on demande à l'utilisateur d'ouvrir le fichier correspondant

				if (resultatOuverture == JFileChooser.APPROVE_OPTION) { //Si l'utilisateur a sélectionné un fichier

					File monFichier = ouvrirFrise.getSelectedFile(); //On récupère ce fichier
					
					boolean erreur = false;
					
					try {
						FriseChronologique maFriseTmp = (FriseChronologique) LectureEcriture.lecture(monFichier);
						maFrise =  new FriseChronologique(maFriseTmp.getTitreFrise(), maFriseTmp.getDateDebut(), maFriseTmp.getDateFin(), maFriseTmp.getPeriodeFrise(), maFriseTmp.getHashMapEvts(), ouvrirFrise.getSelectedFile().getPath()); //Et on le retourne d�serialis�
					}
					catch (FileNotFoundException e) {
						e.printStackTrace();
						erreur = true;
					} 
					catch (IOException e) {
						e.printStackTrace();
						erreur = true;
					}
					finally {
						if (erreur)
							JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'ouverture du fichier !", "Erreur", JOptionPane.ERROR_MESSAGE);
						else 
							break;
					}

				} //if

			} //else if
			else 
				System.exit(0);

		} //while

		try {
			maFrise.sauvegarderFrise();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		panelCreation = new PanelCreation(maFrise);
		add(panelCreation, ConstantesTextes.MENU_CREATION);
		
		PanelAffichage panelAffichage = new PanelAffichage(maFrise);
		add(panelAffichage, ConstantesTextes.MENU_AFFICHAGE);

		new Controleur(maFrise, panelAffichage, panelCreation, monGestionnaireDeCartes, this);
		
		if (!maFrise.isEstInitialisee()) 
			monGestionnaireDeCartes.show(this, ConstantesTextes.MENU_CREATION);
		else 
			monGestionnaireDeCartes.show(this, ConstantesTextes.MENU_AFFICHAGE);
		

	}

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
			JOptionPane.showMessageDialog(this, "<html>Frise Chronologique 1.0 créer par Thomas Vathonne et Yanis Levesque<br/>Pour consulter l'aide référez vous au manuel utilisateur : Manuel_Utilisateur.docx </html>", "Aide", JOptionPane.INFORMATION_MESSAGE);
		}
		else { //Affichage des autres panels
			
			if (!maFrise.isEstInitialisee() && evt.getActionCommand().equals(MENU_AFFICHAGE)) {
				JOptionPane.showMessageDialog(this, "Impossible de visualiser la frise tant qu'elle n'a pas été créer !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else if (panelCreation.getPanelAjoutEvt().isEstModification() && evt.getActionCommand().equals(MENU_AFFICHAGE)) {
				JOptionPane.showMessageDialog(this, "Impossible de visualiser la frise tant que la modification n'est pas terminée !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else 
				monGestionnaireDeCartes.show(this, evt.getActionCommand());
		}

	}

}
