package controleur;

import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import modele.ConstantesTextes;
import modele.Date;
import modele.Evenement;
import modele.FriseChronologique;
import modele.LectureEcriture;
import modele.ModeleTable;
import vue.PanelAffichage;
import vue.PanelCreation;
import vue.PanelFils;

/**
 * Permet de synchroniser la vue et le modèle
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

public class Controleur implements ActionListener, ConstantesTextes {

	private FriseChronologique friseChronologique;
	private PanelAffichage panelAP;
	private PanelCreation panelCreation;
	private CardLayout gestionnaireDeCartes;
	private PanelFils panelFils;
	
	public Controleur(FriseChronologique parFrise, PanelAffichage parPanelAP, PanelCreation parPanelCreation, CardLayout parGestionnaireDeCartes, PanelFils parPanelFils) {

		friseChronologique = parFrise;
		panelAP = parPanelAP;
		panelCreation = parPanelCreation;
		gestionnaireDeCartes = parGestionnaireDeCartes;
		panelFils = parPanelFils;
		
		//Se met à l'écoute des différents panels
		panelCreation.enrengistreEcouteur(this);
		panelAP.enrengistreEcouteur(this);
		
	}


	public void actionPerformed(ActionEvent parEvt) {

		if (parEvt.getActionCommand().equals(CREATION_FRISE_BOUTON_AJOUT)) { //Si l'on veut créer / modifier une frise
			
			//Si le textfield du titre n'est pas vide
			if (!panelCreation.getPanelCreationFrise().getListeTextField()[0].getText().isEmpty()) { 
				
				//Si le textfield de l'année de départ est bien un nombre et qu'il ne dépasse pas les 4 chiffres
				if (isInteger(panelCreation.getPanelCreationFrise().getListeTextField()[1].getText()) && panelCreation.getPanelCreationFrise().getListeTextField()[1].getText().length() <= 4 && !panelCreation.getPanelCreationFrise().getListeTextField()[1].getText().isEmpty()) {
					
					//Si le textfield de l'année de fin est bien un nombre et qu'il ne dépasse pas les 4 chiffres
					if (isInteger(panelCreation.getPanelCreationFrise().getListeTextField()[2].getText()) && panelCreation.getPanelCreationFrise().getListeTextField()[2].getText().length() <= 4 && !panelCreation.getPanelCreationFrise().getListeTextField()[2].getText().isEmpty()) {
						
						//Si la valeur du JSpinner est supérieure à 1 pour ne pas avoir une période à 0
						if ( (int)panelCreation.getPanelCreationFrise().getSpinner().getValue() >= 1) {
							
							//Si l'année de début est inférieure à l'année de fin
							if (Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[1].getText()) <= Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[2].getText()) ) {
								
								//Si cela risque de supprimer des événements hors période
								if (friseChronologique.isEstInitialisee() && (Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[1].getText()) > friseChronologique.getDateDebut().getAnnee() || Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[2].getText()) < friseChronologique.getDateFin().getAnnee())) {
									
									//On demande une confirmation à l'utilisateur
									int resultat = JOptionPane.showConfirmDialog(panelCreation, "Si des événements se trouvent hors des nouvelles périodes de la frise ils seront supprimés. Voulez-vous continuer ?", "Modification de la frise", JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
									
									//Si l'utilisateur ne confirme pas alors on ne fait rien et on quitte la fonction
									if (resultat != JOptionPane.YES_OPTION) {
										panelCreation.setFriseChronologique(friseChronologique);
										return;
									}
								}
								
								//Alors on met à jour les informations de la frise chronologique
								
								friseChronologique.setTitreFrise(panelCreation.getPanelCreationFrise().getListeTextField()[0].getText());
								friseChronologique.setDateDebut(new Date(1, 1, Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[1].getText()) ));
								friseChronologique.setDateFin(new Date(1, 1, Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[2].getText()) ));
								friseChronologique.setPeriodeFrise((int)panelCreation.getPanelCreationFrise().getSpinner().getValue());
								
								if (!friseChronologique.isEstInitialisee()) //Si la frise n'est pas initialisée alors on affiche un message différent
									JOptionPane.showMessageDialog(panelCreation, "La frise a été créer avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(panelCreation, "La frise a été modifiée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
								
								friseChronologique.setEstInitialisee(true);
								
								//On supprimer les événements qui ne sont plus dans la période de la frise
								friseChronologique.supprimerEvenementsHorsPeriode();
								
								//On met à jour les différents composants graphiques (JTable / Formulaire / Card Layout)
								panelAP.getPanelFrise().updateTable(friseChronologique);
								panelCreation.updateComponents();
								panelAP.updatePanelNord();
								
								try { //On essaye de sauvegarder la frise
									LectureEcriture.ecriture(new File(friseChronologique.getEmplacementSauvegarde()), friseChronologique);
								} catch (IOException e) { //On affiche un message en cas d'erreur
									e.printStackTrace();
									JOptionPane.showMessageDialog(panelCreation, "Erreur : La frise n'a pas pu étre sauvegardée !", "Erreur", JOptionPane.ERROR_MESSAGE);
								}

							}
							else {
								JOptionPane.showMessageDialog(panelCreation, "Erreur : L'année de départ est supérieure à l'année de fin !", "Erreur", JOptionPane.ERROR_MESSAGE);
							}

						}
						else {
							JOptionPane.showMessageDialog(panelCreation, "Le champ 'Période' est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
						}

					}
					else {
						JOptionPane.showMessageDialog(panelCreation, "Le champ 'Fin' est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}

				}
				else {
					JOptionPane.showMessageDialog(panelCreation, "Le champ 'Début' est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
				}

			}
			else {
				JOptionPane.showMessageDialog(panelCreation, "Le champ 'Titre' ne peut pas étre vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

		}
		else if (parEvt.getActionCommand().equals(CREATION_EVT_BOUTON_AJOUT)) { //Si l'on veut ajouter (modifier) un événement
			
			//Si le textfield du titre n'est pas vide
			if (!panelCreation.getPanelAjoutEvt().getListeTextField()[0].getText().isEmpty()) {
				
				//Si la date n'est pas vide et qu'elle respecte bien le format demandée
				if (!panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().isEmpty() && panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().matches("\\d{2}/\\d{2}/\\d{4}")) {
					
					//Si le textarea de la description n'est pas vide
					if (!panelCreation.getPanelAjoutEvt().getTextareaDescription().getText().isEmpty()) {
						
						//On sépare le jour/mois/année du champ en question
						int jour = Integer.parseInt(panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().split("/")[0]);
						int mois = Integer.parseInt(panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().split("/")[1]);
						int annee = Integer.parseInt(panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().split("/")[2]);
						
						//Si l'année indiquée pour l'événement est bien dans la période de la frise
						if (annee >= friseChronologique.getDateDebut().getAnnee() && annee <= friseChronologique.getDateFin().getAnnee()) {
							
							
							if (friseChronologique.evenementExisteFrise(annee, (int)panelCreation.getPanelAjoutEvt().getSpinner().getValue())) { //Si un événement existe déjà dans la case où l'on veut le placer
								
								//On demande une confirmation à l'utilisateur
								int resultat = JOptionPane.showConfirmDialog(panelCreation, "Un événement existe déjà a cet emplacement. Voulez-vous le remplacer ?", "Événement existant", JOptionPane.YES_NO_OPTION ,JOptionPane.INFORMATION_MESSAGE);
								
								//Si l'utilisateur ne confirme pas alors on ne fait rien et on quitte la fonction
								if (resultat != JOptionPane.YES_OPTION) {
									return;
								}
							}
							
							//Si il s'agit d'une modification d'un événement existant
							if (panelCreation.getPanelAjoutEvt().isEstModification()) {
								ModeleTable modele = (ModeleTable) panelAP.getPanelFrise().getMonModele();
								Evenement evenementTab = (Evenement) modele.getValueAt(panelAP.getPanelFrise().getRowIndex(), panelAP.getPanelFrise().getColIndex());
								friseChronologique.supprimerEvenement(evenementTab); //Alors on supprime l'ancien
							}
							
							//On rajoute le nouvel événement
							friseChronologique.ajoutEvenement(
									(int) panelCreation.getPanelAjoutEvt().getSpinner().getValue(),
									new Evenement(new Date(jour, mois, annee),
											panelCreation.getPanelAjoutEvt().getListeTextField()[0].getText(),
											panelCreation.getPanelAjoutEvt().getTextareaDescription().getText(),
											panelCreation.getPanelAjoutEvt().getListeTextField()[2].getText()));
							
							//On met à jour la JTable et le diaporama des événements
							panelAP.getPanelFrise().updateTable(friseChronologique);
							panelAP.resetCardLayout();
							
							try { //On essaye de sauvegarder la frise chronologique
								LectureEcriture.ecriture(new File(friseChronologique.getEmplacementSauvegarde()), friseChronologique);
							} catch (IOException e) { //On affiche un message d'erreur en cas de problème
								e.printStackTrace();
								JOptionPane.showMessageDialog(panelCreation, "Erreur : La frise n'a pas pu être sauvegardée !", "Erreur", JOptionPane.ERROR_MESSAGE);
							}
							
							if (panelCreation.getPanelAjoutEvt().isEstModification()) { //On affiche un message différent en fonction de si il s'agit d'une modification ou d'un ajout d'événement
								JOptionPane.showMessageDialog(panelCreation, "L'événement " + panelCreation.getPanelAjoutEvt().getListeTextField()[0].getText() + " a été modifié !", "Succès", JOptionPane.INFORMATION_MESSAGE);
								panelCreation.getPanelAjoutEvt().finirModification();
							}
							else 
								JOptionPane.showMessageDialog(panelCreation, "L'événement " + panelCreation.getPanelAjoutEvt().getListeTextField()[0].getText() + " a été créer !", "Succès", JOptionPane.INFORMATION_MESSAGE);
							
							panelCreation.getPanelAjoutEvt().resetUI(); //On remet le formulaire d'ajout/modification à 0
						}
						else {
							JOptionPane.showMessageDialog(panelCreation, "L'événement n'est pas dans la période de la frise chronologique !", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
		
					}
					else {
						JOptionPane.showMessageDialog(panelCreation, "Le champ 'Description' ne peut pas être vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}

				}
				else {
					JOptionPane.showMessageDialog(panelCreation, "Le champ 'Date' est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
				}

			}
			else {
				JOptionPane.showMessageDialog(panelCreation, "Le champ 'Titre' ne peut pas étre vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

		}
		else if (parEvt.getActionCommand().equals(CREATION_EVT_BOUTON_PHOTO)) { //Si l'on veut choisir une photo pour un événement

			JFileChooser ouvrirPhoto = new JFileChooser();
			FileFilter monFiltre = new FileNameExtensionFilter("Images", "bmp", "gif", "jpg", "jpeg", "png"); //Les extensions acceptées

			ouvrirPhoto.setFileFilter(monFiltre); //On met le filtre
			ouvrirPhoto.setAcceptAllFileFilterUsed(false); //On accepte que les fichiers qui proviennent du filtre
			
			int resultatOuverture = ouvrirPhoto.showOpenDialog(panelCreation); //Alors on demande à l'utilisateur d'ouvrir la photo

			if (resultatOuverture == JFileChooser.APPROVE_OPTION) { //Si l'utilisateur a sélectionné un fichier
				panelCreation.getPanelAjoutEvt().getListeTextField()[2].setText(ouvrirPhoto.getSelectedFile().getPath()); //Alors on rajoute au textfield du formulaire le chemin d'accès vers la photo
			}

		}
		else if (parEvt.getActionCommand().equals(AFFICHAGE_POPUPMENU_MODIFIER)) { //Si l'on veut modifier un événement (va l'ajouter au menu de création d'événement)
			//On obtient l'événement sur lequel on a cliqué
			ModeleTable modele = (ModeleTable) panelAP.getPanelFrise().getMonModele();
			Evenement evenementTab = (Evenement) modele.getValueAt(panelAP.getPanelFrise().getRowIndex(), panelAP.getPanelFrise().getColIndex());
			//On ajoute l'événement au formulaire
			panelCreation.getPanelAjoutEvt().setEvt(evenementTab);
			panelCreation.getPanelAjoutEvt().setEstModification(true);
			//On affiche le panel de création
			gestionnaireDeCartes.show(panelFils, MENU_CREATION);
		}
		else if (parEvt.getActionCommand().equals(AFFICHAGE_POPUPMENU_SUPPRIMER)) { //Si l'on veut supprimer un événement	
			
			//On demande une confirmation à l'utilisateur
			int resultat = JOptionPane.showConfirmDialog(panelAP, "Voulez-vous vraiment supprimer l'événement ?", "Supprimer ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (resultat == JOptionPane.YES_OPTION) { //Si l'utilisateur a confirmé
				//On obtient l'événement sur lequel il a cliqué
				ModeleTable modele = (ModeleTable) panelAP.getPanelFrise().getMonModele();
				Evenement evenementTab = (Evenement) modele.getValueAt(panelAP.getPanelFrise().getRowIndex(), panelAP.getPanelFrise().getColIndex());
				//On supprime de la frise chronologique
				friseChronologique.supprimerEvenement(evenementTab);
				//On met à jour les composants graphiques
				panelAP.getPanelFrise().updateTable(friseChronologique);
				panelAP.resetCardLayout();
				//On affiche un message de succès
				JOptionPane.showMessageDialog(panelCreation, "L'événement a été supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
				try { //On essaye d'enrengistrer la nouvelle frise
					friseChronologique.sauvegarderFrise();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		}
		else if (parEvt.getActionCommand().equals(CREATION_EVT_BOUTON_ANNULATION)) { //Si l'on veut annuler la modification d'un événement
			panelCreation.getPanelAjoutEvt().finirModification(); //On remet à 0 le formulaire de modification
		}
		else if (parEvt.getActionCommand().equals(CREATION_FRISE_BOUTON_SAUVEGARDE)) { //Si l'on veut sauvegarder la frise
			
			boolean showFileChooser = false;
			
			if (friseChronologique.getEmplacementSauvegarde() != "Frise.ser") { //Si un emplacement différent de celui originel est déjà mis
				
				//On demande si l'on veut enrengistrer au même endroit
				int resultat = JOptionPane.showConfirmDialog(panelAP, "Voulez-vous garder le même emplacement de sauvegarde ?", "Sauvegarde", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (resultat == JOptionPane.NO_OPTION) { //Si non alors on ouvrira le JFileChooser pour choisir l'emplacement
					showFileChooser = true;
				}
				else if (resultat != JOptionPane.YES_OPTION) //Si on appui sur la croix alors on quitte la fonction
					return;
			}
			
			if (showFileChooser || friseChronologique.getEmplacementSauvegarde().equals("Frise.ser")) { //Si l'emplacement de sauvegarde de base est mis
				//On demande alors à l'utilisateur de choisir l'emplacement de sauvegarde
				JFileChooser enrengistrerFrise = new JFileChooser();
				int resultatOuverture = enrengistrerFrise.showSaveDialog(panelCreation);
				
				if (resultatOuverture == JFileChooser.APPROVE_OPTION) { //Si l'utilisateur a sélectionné un fichier
					friseChronologique.setEmplacementSauvegarde(enrengistrerFrise.getSelectedFile().getPath()); //On change l'emplacement de sauvegarde de la frise
				}
				else 
					return;
			}
			
			try { //On essaye d'enrengistrer la frise
				friseChronologique.sauvegarderFrise();
				JOptionPane.showMessageDialog(panelCreation, "La frise a été enrengistrée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) { //Sinon on affiche un message d'erreur
				e.printStackTrace();
				JOptionPane.showMessageDialog(panelCreation, "Une erreur est survenue lors de l'enrengistrement de la frise !", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}

	}
	
	/**
	 * Permet de savoir si un String est un nombre
	 * @param str le string que l'on veut tester
	 * @return true si il s'agit d'un nombre / false sinon
	 */
	
	public boolean isInteger(String str)
	{
		try {
			Integer.parseInt(str);
			return true;
		}
		catch(Exception parExc) {
			return false;
		}
	}

}
