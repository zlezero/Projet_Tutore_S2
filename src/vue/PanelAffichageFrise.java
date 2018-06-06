package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import controleur.Controleur;
import modele.ConstantesTextes;
import modele.Evenement;
import modele.FriseChronologique;
import modele.ModeleTable;

/**
 * Affiche la JTable d'événements
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

public class PanelAffichageFrise extends JPanel implements ConstantesTextes {

	private static final long serialVersionUID = 1L;

	private JTable tableFrise;
	private JMenuItem itemModifier, itemSupprimer;
	private JScrollPane scrollPane;
	private ModeleTable monModele;
	private int rowIndex, colIndex;

	public PanelAffichageFrise(FriseChronologique parFrise, CardLayout parGestionnaireDeCartes, Container panelCL) {

		setLayout(new BorderLayout());

		monModele = new ModeleTable(parFrise);

		// Création d'une JTable et de toute ces caractéristiques
		tableFrise = new JTable(monModele);
		tableFrise.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableFrise.getTableHeader().setReorderingAllowed(false);
		tableFrise.setRowHeight(80);
		tableFrise.setDefaultRenderer(Evenement.class, new CelluleRenderer());

		// On créer un JScrollPane
		scrollPane = new JScrollPane(tableFrise, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setPreferredSize(new Dimension(360, 360));

		add(scrollPane, BorderLayout.CENTER);

		// scrollPane.getHorizontalScrollBar().setValue(scrollPane.getX() +500);
		// scrollPane.getHorizontalScrollBar().setValue(scrollPane.getX() +300);

		// Création d'un popupMenu
		JPopupMenu popupMenu = new JPopupMenu("Outils");
		itemModifier = new JMenuItem(AFFICHAGE_POPUPMENU_MODIFIER);
		itemSupprimer = new JMenuItem(AFFICHAGE_POPUPMENU_SUPPRIMER);

		// Ajout des items de menu
		popupMenu.add(itemModifier);
		popupMenu.add(itemSupprimer);

		/**
		 * Méthode qui permet d'utiliser MouseListener sur la tableFrise
		 */

		tableFrise.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				JTable table = (JTable) evt.getSource();
				ModeleTable model = (ModeleTable) table.getModel();
				Point point = evt.getPoint();
				int rowIndex = table.rowAtPoint(point);
				int colIndex = table.columnAtPoint(point);
				if (model.getValueAt(rowIndex, colIndex) != null) {
					Evenement evenementTab = (Evenement) model.getValueAt(rowIndex, colIndex);
					parGestionnaireDeCartes.show(panelCL, evenementTab.toString());
				}

			}

			/**
			 * Méthode qui permet de savoir s'il y a un eu un clic sur la JTable
			 */

			public void mousePressed(MouseEvent evt) {

				JTable table = (JTable) evt.getSource();
				ModeleTable model = (ModeleTable) table.getModel();
				Point point = evt.getPoint();
				rowIndex = table.rowAtPoint(point);
				colIndex = table.columnAtPoint(point);

				if (evt.isPopupTrigger() && model.getValueAt(rowIndex, colIndex) != null) {
					popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
				}
			}

			/**
			 * Méthode qui permet de savoir si le clic de la souris est fini
			 */

			public void mouseReleased(MouseEvent evt) {

				JTable table = (JTable) evt.getSource();
				ModeleTable model = (ModeleTable) table.getModel();
				Point point = evt.getPoint();
				rowIndex = table.rowAtPoint(point);
				colIndex = table.columnAtPoint(point);

				if (evt.isPopupTrigger() && model.getValueAt(rowIndex, colIndex) != null) {
					popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
				}
			}

		});

	}

	/**
	 * Accesseur de la ModeleTable
	 * 
	 * @return une ModeleTable
	 */

	public ModeleTable getMonModele() {
		return monModele;
	}

	/**
	 * Modifieur de la ModeleTable
	 * @param monModele Le modele que l'on veut mettre dans le tableau
	 */

	public void setMonModele(ModeleTable monModele) {
		this.monModele = monModele;
	}

	/**
	 * Accesseur du JScrollPane
	 * 
	 * @return JScrollPane
	 */

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * Modifieur du JScrollPane
	 * @param scrollPane Un scrollPane pour le tableau
	 */

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	 * Accesseur pour obtenir une ligne de la JTable
	 * 
	 * @return rowIndex qui est l'indice d'une ligne
	 */

	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * Modifieur qui permet de changer l'indice d'une ligne de la JTable
	 * @param rowIndex La ligne du tableau
	 */

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * Accesseur pour obtenir une colonne de la JTable
	 * 
	 * @return colIndex qui est l'indice de la colonne
	 */

	public int getColIndex() {
		return colIndex;
	}

	/**
	 * Modifieur qui permet de changer l'indice d'une colonne de la JTable
	 * @param colIndex La colonne du tableau
	 */

	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}

	/**
	 * Méthode qui permet de synchroniser l'écoute sur le contrôleur des items du
	 * popmenu
	 * @param parC Le controleur qui doit se mettre à l'écoute du popmenu
	 */

	public void enrengistreEcouteur(Controleur parC) {
		itemModifier.addActionListener(parC);
		itemSupprimer.addActionListener(parC);
	}

	/**
	 * Accesseur qui permet d'obtenir la tableFrise
	 * 
	 * @return tableFrise
	 */

	public JTable getTableFrise() {
		return tableFrise;
	}

	/**
	 * Modifieur qui permet de changer la tableFrise
	 * @param tableFrise La frise que l'on veut mettre dans le tableau
	 */

	public void setTableFrise(JTable tableFrise) {
		this.tableFrise = tableFrise;
	}

	/**
	 * Méthode qui permet de mettre a jour la JTable
	 * @param parFrise La frise qui sera affichée dans le tableau
	 */

	public void updateTable(FriseChronologique parFrise) {
		monModele = new ModeleTable(parFrise);
		tableFrise.setModel(monModele);
	}

}
