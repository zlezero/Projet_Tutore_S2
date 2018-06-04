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

public class PanelAffichageFrise extends JPanel implements ConstantesTextes {
	
	private static final long serialVersionUID = 1L;
	
	JTable tableFrise;
	JMenuItem itemModifier, itemSupprimer;
	JScrollPane scrollPane;
	ModeleTable monModele;
	int rowIndex, colIndex;
	
	public PanelAffichageFrise(FriseChronologique parFrise, CardLayout parGestionnaireDeCartes, Container panelCL) {
		
		setLayout(new BorderLayout());
		
		monModele = new ModeleTable(parFrise);
		
		tableFrise = new JTable(monModele);
		tableFrise.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableFrise.getTableHeader().setReorderingAllowed(false);
		tableFrise.setRowHeight(80);
		tableFrise.setDefaultRenderer(Evenement.class, new CelluleRenderer());
		
		scrollPane = new JScrollPane(tableFrise,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scrollPane.setPreferredSize(new Dimension(360, 360));
		
		add(scrollPane, BorderLayout.CENTER);
		
		JPopupMenu popupMenu = new JPopupMenu("Outils");
		itemModifier = new JMenuItem(AFFICHAGE_POPUPMENU_MODIFIER);
		itemSupprimer = new JMenuItem(AFFICHAGE_POPUPMENU_SUPPRIMER);
		
		popupMenu.add(itemModifier);
		popupMenu.add(itemSupprimer);
		
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
	
	public ModeleTable getMonModele() {
		return monModele;
	}

	public void setMonModele(ModeleTable monModele) {
		this.monModele = monModele;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}

	public void enrengistreEcouteur(Controleur parC) {
		itemModifier.addActionListener(parC);
		itemSupprimer.addActionListener(parC);
	}

	public JTable getTableFrise() {
		return tableFrise;
	}

	public void setTableFrise(JTable tableFrise) {
		this.tableFrise = tableFrise;
	}
	
	public void updateTable(FriseChronologique parFrise) {
		monModele = new ModeleTable(parFrise);
		tableFrise.setModel(monModele);
	}
	
}
