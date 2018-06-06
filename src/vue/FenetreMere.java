package vue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import modele.ConstantesTextes;

/**
 * Est la fenêtre principale du programme
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

public class FenetreMere extends JFrame implements ConstantesTextes {

	private static final long serialVersionUID = 1L;

	public FenetreMere(String parTitre) {

		super(parTitre);

		PanelFils contentPane = new PanelFils();

		// Ajout d'une barre de menu
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		String[] nomsItemsMenu = { MENU_CREATION, MENU_AFFICHAGE, MENU_AIDE, MENU_QUITTER }; //Noms des items du menu

		for (int i = 0; i != nomsItemsMenu.length; i++) { //On ajoute les différents items de menu et on les mets à l'écoute
			JMenuItem item = new JMenuItem(nomsItemsMenu[i]);
			item.addActionListener(contentPane); 
			item.setActionCommand(nomsItemsMenu[i]);
			menuBar.add(item);
		}

		setContentPane(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 850);
		setVisible(true);
	}

	/**
	 * Méthode main de la Classe FenetreMere permettant de créer une fenêtre mère
	 * @param args Les arguments venant du système d'exploitation
	 */

	public static void main(String[] args) {
		new FenetreMere("Frise chronologique");
	}

}
