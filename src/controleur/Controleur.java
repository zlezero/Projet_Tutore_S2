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
		
		panelCreation.enrengistreEcouteur(this);
		panelAP.enrengistreEcouteur(this);
		
	}


	public void actionPerformed(ActionEvent parEvt) {

		if (parEvt.getActionCommand().equals(CREATION_FRISE_BOUTON_AJOUT)) { //Si l'on veut créer / modifier une frise

			if (!panelCreation.getPanelCreationFrise().getListeTextField()[0].getText().isEmpty()) {

				if (isInteger(panelCreation.getPanelCreationFrise().getListeTextField()[1].getText()) && panelCreation.getPanelCreationFrise().getListeTextField()[1].getText().length() <= 4 && !panelCreation.getPanelCreationFrise().getListeTextField()[1].getText().isEmpty()) {

					if (isInteger(panelCreation.getPanelCreationFrise().getListeTextField()[2].getText()) && panelCreation.getPanelCreationFrise().getListeTextField()[2].getText().length() <= 4 && !panelCreation.getPanelCreationFrise().getListeTextField()[2].getText().isEmpty()) {

						if ( (int)panelCreation.getPanelCreationFrise().getSpinner().getValue() >= 1) {

							if (Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[1].getText()) <= Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[2].getText()) ) {

								friseChronologique.setTitreFrise(panelCreation.getPanelCreationFrise().getListeTextField()[0].getText());
								friseChronologique.setDateDebut(new Date(1, 1, Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[1].getText()) ));
								friseChronologique.setDateFin(new Date(1, 1, Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[2].getText()) ));
								friseChronologique.setPeriodeFrise((int)panelCreation.getPanelCreationFrise().getSpinner().getValue());
								friseChronologique.setEstInitialisee(true);

								panelAP.getPanelFrise().updateTable(friseChronologique);
								panelCreation.updateComponents();
								panelAP.updatePanelNord();
								
								try {
									LectureEcriture.ecriture(new File(friseChronologique.getEmplacementSauvegarde()), friseChronologique);
								} catch (IOException e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(panelCreation, "Erreur : La frise n'a pas pu étre sauvegardée !", "Erreur", JOptionPane.ERROR_MESSAGE);
								}
								
								if (!friseChronologique.isEstInitialisee())
									JOptionPane.showMessageDialog(panelCreation, "La frise a été créer avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(panelCreation, "La frise a été modifiée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

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
		else if (parEvt.getActionCommand().equals(CREATION_EVT_BOUTON_AJOUT)) { //Si l'on veut ajouter un événement

			if (!panelCreation.getPanelAjoutEvt().getListeTextField()[0].getText().isEmpty()) {

				if (!panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().isEmpty() && panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().matches("\\d{2}/\\d{2}/\\d{4}")) {

					if (!panelCreation.getPanelAjoutEvt().getTextareaDescription().getText().isEmpty()) {
						
						int jour = Integer.parseInt(panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().split("/")[0]);
						int mois = Integer.parseInt(panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().split("/")[1]);
						int annee = Integer.parseInt(panelCreation.getPanelAjoutEvt().getListeTextField()[1].getText().split("/")[2]);
						
						if (annee >= friseChronologique.getDateDebut().getAnnee() && annee <= friseChronologique.getDateFin().getAnnee()) {
							
							if (panelCreation.getPanelAjoutEvt().isEstModification()) {
								ModeleTable modele = (ModeleTable) panelAP.getPanelFrise().getMonModele();
								Evenement evenementTab = (Evenement) modele.getValueAt(panelAP.getPanelFrise().getRowIndex(), panelAP.getPanelFrise().getColIndex());
								//System.out.println(evenementTab);
								friseChronologique.supprimerEvenement(evenementTab);
							}
							
							friseChronologique.ajoutEvenement(
									(int) panelCreation.getPanelAjoutEvt().getSpinner().getValue(),
									new Evenement(new Date(jour, mois, annee),
											panelCreation.getPanelAjoutEvt().getListeTextField()[0].getText(), "",
											panelCreation.getPanelAjoutEvt().getTextareaDescription().getText(),
											panelCreation.getPanelAjoutEvt().getListeTextField()[2].getText()));
							
							panelAP.getPanelFrise().updateTable(friseChronologique);
							panelAP.resetCardLayout();
							
							try {
								LectureEcriture.ecriture(new File(friseChronologique.getEmplacementSauvegarde()), friseChronologique);
							} catch (IOException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(panelCreation, "Erreur : La frise n'a pas pu être sauvegardée !", "Erreur", JOptionPane.ERROR_MESSAGE);
							}
							
							if (panelCreation.getPanelAjoutEvt().isEstModification()) {
								JOptionPane.showMessageDialog(panelCreation, "L'événement " + panelCreation.getPanelAjoutEvt().getListeTextField()[0].getText() + " a été modifié !", "Succès", JOptionPane.INFORMATION_MESSAGE);
								panelCreation.getPanelAjoutEvt().finirModification();
							}
							else 
								JOptionPane.showMessageDialog(panelCreation, "L'événement " + panelCreation.getPanelAjoutEvt().getListeTextField()[0].getText() + " a été créer !", "Succès", JOptionPane.INFORMATION_MESSAGE);
							
							
							
							panelCreation.getPanelAjoutEvt().resetUI();
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
		else if (parEvt.getActionCommand().equals(CREATION_EVT_BOUTON_PHOTO)) { //Si l'on veut choisir une photo

			JFileChooser ouvrirPhoto = new JFileChooser();
			FileFilter monFiltre = new FileNameExtensionFilter("Images", "bmp", "gif", "jpg", "jpeg", "png");

			ouvrirPhoto.setFileFilter(monFiltre);	
			ouvrirPhoto.setAcceptAllFileFilterUsed(false);
			
			int resultatOuverture = ouvrirPhoto.showOpenDialog(panelCreation); //Alors on demande à l'utilisateur d'ouvrir le fichier correspondant

			if (resultatOuverture == JFileChooser.APPROVE_OPTION) { //Si l'utilisateur a sélectionné un fichier
				panelCreation.getPanelAjoutEvt().getListeTextField()[2].setText(ouvrirPhoto.getSelectedFile().getPath());
			}

		}
		else if (parEvt.getActionCommand().equals(AFFICHAGE_POPUPMENU_MODIFIER)) { //Si l'on veut modifier un événement (va l'ajouter au menu de création d'événement)
			ModeleTable modele = (ModeleTable) panelAP.getPanelFrise().getMonModele();
			Evenement evenementTab = (Evenement) modele.getValueAt(panelAP.getPanelFrise().getRowIndex(), panelAP.getPanelFrise().getColIndex());
			panelCreation.getPanelAjoutEvt().setEvt(evenementTab);
			panelCreation.getPanelAjoutEvt().setEstModification(true);
			gestionnaireDeCartes.show(panelFils, MENU_CREATION);
		}
		else if (parEvt.getActionCommand().equals(AFFICHAGE_POPUPMENU_SUPPRIMER)) { //Si l'on veut supprimer un événement	
			
			int resultat = JOptionPane.showConfirmDialog(panelAP, "Voulez-vous vraiment supprimer l'événement ?", "Supprimer ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (resultat == JOptionPane.YES_OPTION) {
				ModeleTable modele = (ModeleTable) panelAP.getPanelFrise().getMonModele();
				Evenement evenementTab = (Evenement) modele.getValueAt(panelAP.getPanelFrise().getRowIndex(), panelAP.getPanelFrise().getColIndex());
				//System.out.println("Row : " + panelAP.getPanelFrise().getRowIndex() + " / Column : " + panelAP.getPanelFrise().getColIndex() + " Model : " + modele.getValueAt(panelAP.getPanelFrise().getRowIndex(), panelAP.getPanelFrise().getColIndex()) + "/ Evt : " + evenementTab);
				friseChronologique.supprimerEvenement(evenementTab);
				panelAP.getPanelFrise().updateTable(friseChronologique);
				panelAP.resetCardLayout();
				JOptionPane.showMessageDialog(panelCreation, "L'événement a été supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
				try {
					friseChronologique.sauvegarderFrise();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		}
		else if (parEvt.getActionCommand().equals(CREATION_EVT_BOUTON_ANNULATION)) { //Si l'on veut annuler la modification d'un événement
			panelCreation.getPanelAjoutEvt().finirModification();
		}
		else if (parEvt.getActionCommand().equals(CREATION_FRISE_BOUTON_SAUVEGARDE)) { //Si l'on veut sauvegarder la frise
			
			boolean showFileChooser = false;
			
			if (friseChronologique.getEmplacementSauvegarde() != "Frise.ser") {
				
				int resultat = JOptionPane.showConfirmDialog(panelAP, "Voulez-vous garder le même emplacement de sauvegarde ?", "Sauvegarde", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (resultat == JOptionPane.NO_OPTION) {
					showFileChooser = true;
				}
				else if (resultat != JOptionPane.YES_OPTION)
					return;
			}
			
			if (showFileChooser || friseChronologique.getEmplacementSauvegarde().equals("Frise.ser")) {
				JFileChooser enrengistrerFrise = new JFileChooser();
				int resultatOuverture = enrengistrerFrise.showSaveDialog(panelCreation);
				
				if (resultatOuverture == JFileChooser.APPROVE_OPTION) { //Si l'utilisateur a sélectionné un fichier
					friseChronologique.setEmplacementSauvegarde(enrengistrerFrise.getSelectedFile().getPath());
				}
				else 
					return;
			}
			
			try {
				friseChronologique.sauvegarderFrise();
				JOptionPane.showMessageDialog(panelCreation, "La frise a été enrengistrée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(panelCreation, "Une erreur est survenue lors de l'enrengistrement de la frise !", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}

	}

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
