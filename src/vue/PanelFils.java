package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelFils extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	CardLayout monGestionnaireDeCartes;
	
	public PanelFils() {
		
		monGestionnaireDeCartes = new CardLayout(50, 50);
		setLayout(monGestionnaireDeCartes);
		
	}
	
	
	public void actionPerformed(ActionEvent evt) {
		
		if (evt.getActionCommand().equals("Quitter")) {
			
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
		else {
			monGestionnaireDeCartes.show(this, evt.getActionCommand());
		}
		
	}

}
