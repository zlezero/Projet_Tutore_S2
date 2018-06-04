package controleur;

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
import vue.PanelAffichage;
import vue.PanelCreation;

public class Controleur implements ActionListener, ConstantesTextes {

	private FriseChronologique friseChronologique;
	private PanelAffichage panelAP;
	private PanelCreation panelCreation;

	public Controleur(FriseChronologique parFrise, PanelAffichage parPanelAP, PanelCreation parPanelCreation) {

		friseChronologique = parFrise;
		panelAP = parPanelAP;
		panelCreation = parPanelCreation;

		panelCreation.enrengistreEcouteur(this);

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
								friseChronologique.ajoutEvenement(1, new Evenement(new Date(6, 6, 2005), "TEST", "TEST", "C'est un bel evt !", ""));
								friseChronologique.ajoutEvenement(2, new Evenement(new Date(6, 6, 2014), "TEST2", "TEST2", "C'est un bel evt 2 !", ""));
								panelAP.getPanelFrise().updateTable(friseChronologique);
								panelCreation.updateComponents();
								panelAP.updatePanelNord();
								try {
									LectureEcriture.ecriture(new File("Frise.ser"), friseChronologique);
								} catch (IOException e) {
									e.printStackTrace();
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
				JOptionPane.showMessageDialog(panelCreation, "Le champ 'Titre' ne peut pas être vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
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
							
							friseChronologique.ajoutEvenement(
									(int) panelCreation.getPanelAjoutEvt().getSpinner().getValue(),
									new Evenement(new Date(jour, mois, annee),
											panelCreation.getPanelAjoutEvt().getListeTextField()[0].getText(), "",
											panelCreation.getPanelAjoutEvt().getTextareaDescription().getText(),
											panelCreation.getPanelAjoutEvt().getListeTextField()[2].getText()));
							
							panelAP.getPanelFrise().updateTable(friseChronologique);
							
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
				JOptionPane.showMessageDialog(panelCreation, "Le champ 'Titre' ne peut pas être vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
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
