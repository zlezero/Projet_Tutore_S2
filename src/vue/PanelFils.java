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
 * @author Yanis Levesque
 * @version 1
 */

public class PanelFils extends JPanel implements ActionListener, ConstantesTextes {

	private static final long serialVersionUID = 1L;

	private CardLayout monGestionnaireDeCartes;
	private FriseChronologique maFrise;
	private PanelCreation panelCreation;
	
	public PanelFils() {

		//Gestionnaire de cartes des différents panels pour le menu
		monGestionnaireDeCartes = new CardLayout(50, 50);
		setLayout(monGestionnaireDeCartes);

		maFrise = null;

		while (true) { //Tant qu'une option n'a pas été choisie
			
			String intitulesBoutons[] = {"Nouvelle frise", "Ouvrir une frise"};	
			//On demande à l'utilisateur ce qu'il souhaite faire (Nouvelle frise / Ouvrir frise)
			int resultat = JOptionPane.showOptionDialog(this, "Choisissez une option : ", "Bienvenue !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, intitulesBoutons, intitulesBoutons[0]);

			if (resultat == JOptionPane.YES_OPTION) { //Si l'on veut créer une nouvelle frise
				maFrise = new FriseChronologique(); //Alors on renvoit juste une frise vide
				break; //Et on sort de la boucle
			}
			else if (resultat == JOptionPane.NO_OPTION) { //Si l'on veut ouvrir une nouvelle frise

				JFileChooser ouvrirFrise = new JFileChooser();
				int resultatOuverture = ouvrirFrise.showOpenDialog(this); //Alors on demande à l'utilisateur d'ouvrir le fichier correspondant

				if (resultatOuverture == JFileChooser.APPROVE_OPTION) { //Si l'utilisateur a sélectionné un fichier

					File monFichier = ouvrirFrise.getSelectedFile(); //On récupère ce fichier
					
					boolean erreur = false;
					
					try {
						FriseChronologique maFriseTmp = (FriseChronologique) LectureEcriture.lecture(monFichier); //On déserialise ce fichier
						maFrise =  new FriseChronologique(maFriseTmp.getTitreFrise(), maFriseTmp.getDateDebut(), maFriseTmp.getDateFin(), maFriseTmp.getPeriodeFrise(), maFriseTmp.getHashMapEvts(), ouvrirFrise.getSelectedFile().getPath()); //Et on le retourne d�serialis�
					}
					catch (FileNotFoundException e) { //Erreur si le fichier est inexistant
						e.printStackTrace();
						erreur = true;
					} 
					catch (IOException e) { //Erreur si le fichier est invalide
						e.printStackTrace();
						erreur = true;
					}
					finally {
						if (erreur) //Si il y a eu une erreur alors on affiche un message 
							JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de l'ouverture du fichier !", "Erreur", JOptionPane.ERROR_MESSAGE);
						else //Sinon on sort de la boucle pour continuer le programme
							break;
					}

				} //if

			} //else if
			else 
				System.exit(0);

		} //while

		try { //On essaye de sauvegarder la frise
			maFrise.sauvegarderFrise();
		} catch (IOException e) { //Si il y a une erreur lors de la sauvegarde
			e.printStackTrace();
		}
		
		//On créer les différents panels
		
		panelCreation = new PanelCreation(maFrise);
		add(panelCreation, ConstantesTextes.MENU_CREATION);
		
		PanelAffichage panelAffichage = new PanelAffichage(maFrise);
		add(panelAffichage, ConstantesTextes.MENU_AFFICHAGE);

		new Controleur(maFrise, panelAffichage, panelCreation, monGestionnaireDeCartes, this);
		
		if (!maFrise.isEstInitialisee()) //Si il s'agit d'une nouvelle frise 
			monGestionnaireDeCartes.show(this, ConstantesTextes.MENU_CREATION); //Alors on redirige vers le menu de création
		else //Sinon on redirige vers le menu d'affichage
			monGestionnaireDeCartes.show(this, ConstantesTextes.MENU_AFFICHAGE);
		

	} //PanelFils

	public void actionPerformed(ActionEvent evt) {

		//On gère le menu

		if (evt.getActionCommand().equals(MENU_QUITTER)) { //Si l'utilisateur veut quitter
			
			//On lui demande une confirmation
			int resultat = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter ?", "Quitter ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (resultat == JOptionPane.YES_OPTION) { //Si l'utilisateur a cliqué sur oui alors on quitte l'application
				System.exit(0);
			}
		} 
		else if (evt.getActionCommand().equals(MENU_AIDE)) { //Affichage du menu d'aide
			JOptionPane.showMessageDialog(this, "<html>Frise Chronologique 1.0 créer par Thomas Vathonne et Yanis Levesque<br/>Pour consulter l'aide référez vous au manuel utilisateur : Manuel_Utilisateur.pdf </html>", "Aide", JOptionPane.INFORMATION_MESSAGE);
		}
		else { //Affichage des autres panels
			
			if (!maFrise.isEstInitialisee() && evt.getActionCommand().equals(MENU_AFFICHAGE)) { //Si l'on essaye d'accéder au panel affichage sans avoir créer sa frise
				JOptionPane.showMessageDialog(this, "Impossible de visualiser la frise tant qu'elle n'a pas été créer !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else if (panelCreation.getPanelAjoutEvt().isEstModification() && evt.getActionCommand().equals(MENU_AFFICHAGE)) { //Si l'on essaye d'accéder au panel affichage sans avoir terminer la modification d'un événement
				JOptionPane.showMessageDialog(this, "Impossible de visualiser la frise tant que la modification n'est pas terminée !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else //Sinon on affiche le panel correspondant
				monGestionnaireDeCartes.show(this, evt.getActionCommand());
		} //else

	} //actionPerformed

} //PanelFils
