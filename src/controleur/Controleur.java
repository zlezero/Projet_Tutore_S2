package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modele.ConstantesTextes;
import modele.Date;
import modele.FriseChronologique;
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
			
			if (panelCreation.getPanelCreationFrise().getListeTextField()[1].getText().chars().allMatch(Character::isDigit) && panelCreation.getPanelCreationFrise().getListeTextField()[1].getText().length() <= 4 && panelCreation.getPanelCreationFrise().getListeTextField()[2].getText().chars().allMatch(Character::isDigit) && panelCreation.getPanelCreationFrise().getListeTextField()[2].getText().length() <= 4) {
				friseChronologique.setTitreFrise(panelCreation.getPanelCreationFrise().getListeTextField()[0].getText());
				friseChronologique.setDateDebut(new Date(1, 1, Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[1].getText()) ));
				friseChronologique.setDateFin(new Date(1, 1, Integer.parseInt(panelCreation.getPanelCreationFrise().getListeTextField()[2].getText()) ));
				friseChronologique.setPeriodeFrise((int)panelCreation.getPanelCreationFrise().getSpinner().getValue());
				friseChronologique.setEstInitialisee(true);
				panelAP.getPanelFrise().updateTable(friseChronologique);
				panelCreation.updateComponents();
				if (!friseChronologique.isEstInitialisee())
					JOptionPane.showMessageDialog(panelCreation, "La frise a été créer avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(panelCreation, "La frise a été modifiée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(panelCreation, "Les champs rentrés sont incorrects !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
