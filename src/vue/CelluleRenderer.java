package vue;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import modele.Evenement;

/**
 * Génère le contenu d'une case de JTable
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

public class CelluleRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public CelluleRenderer() {
		super();
		setOpaque(false);
		setHorizontalAlignment(JLabel.CENTER);
	}

	/**
	 * Pour définir ce qu'il y a dans une cellule de la JTable
	 * 
	 * @return this qui est le Component
	 */

	public Component getTableCellRendererComponent(JTable table, Object valeur, boolean estSelectionne,
			boolean aLeFocus, int ligne, int colonne) {

		if (valeur != null) { //Si la valeur de la case n'est pas null

			Evenement evt = (Evenement) valeur; //Alors on le retransforme en événement

			if (evt.getChPhoto().isEmpty()) { //Si l'événement ne possède pas de photo
				setText(evt.toString()); //Alors on affiche juste le titre de l'événement
			} else { //Sinon on affiche l'image
				setIcon(new ImageIcon(
						new ImageIcon(evt.getChPhoto()).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
			}

		} else { //Sinon on affiche rien
			setIcon(null);
			setText("");
		}

		return this;
	}

}
