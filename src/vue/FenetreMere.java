package vue;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FenetreMere extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public FenetreMere(String parTitre) {
		
		super(parTitre);
		
		PanelFils contentPane = new PanelFils();
		
		//Ajout d'une barre de menu
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		String[] nomsItemsMenu = {"Création", "Affichage", "Aide", "Quitter"};
		
		for (int i = 0; i!=nomsItemsMenu.length;i++) {
			JMenuItem item = new JMenuItem(nomsItemsMenu[i]);
			item.addActionListener(contentPane);
			item.setActionCommand(nomsItemsMenu[i]);
			menuBar.add(item);
		}
		
		setContentPane(contentPane);
		//contentPane.setBackground(new Color(100, 60, 120));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 700);
		setVisible(true);
		setLocation(200, 300);
	}
	
	public static void main(String[] args) {
		new FenetreMere("Frise chronologique");
	}

}
