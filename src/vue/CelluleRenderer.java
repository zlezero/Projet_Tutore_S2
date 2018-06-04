package vue;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import modele.Evenement;

public class CelluleRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public CelluleRenderer() {
		super();
		setOpaque(false);
		setHorizontalAlignment(JLabel.CENTER);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object valeur, boolean estSelectionne, boolean aLeFocus, int ligne, int colonne) {
				
		if (valeur != null) {
			
			Evenement evt = (Evenement) valeur;
			
			if (evt.getChPhoto().isEmpty()) {
				setText(evt.toString());
			}
			else {
				setIcon(new ImageIcon(evt.getChPhoto()));
			}
			
		}
		else {
			setIcon(null);
			setText("");
		}
		
		return this;
	}

}
