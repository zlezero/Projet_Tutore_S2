package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

/**
 * Est le modèle de base pour la JTable d'événements
 * @author Thomas Vathonne
 * @version 1
 */

public class ModeleTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	private int rowNumber = 4;
	private int columnNumber;

	public ModeleTable(FriseChronologique parFrise) {

		columnNumber = (parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee()) + 1;

		this.setRowCount(rowNumber);
		this.setColumnCount(columnNumber);

		ArrayList<String> intitulesTab = new ArrayList<String>();

		// Créer les intitulés du tableau
		for (int i = 0; i != (parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee()) + 1; i++) {
			if ((i % parFrise.getPeriodeFrise() == 0)
					|| (i == (parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee()))) {
				intitulesTab.add(Integer.toString(parFrise.getDateDebut().getAnnee() + i));
			} else {
				intitulesTab.add("");
			}
		}

		setColumnIdentifiers(intitulesTab.toArray());

		Collection<HashMap<Integer, Evenement>> evenements = parFrise.getHashMapEvenementsPoids();

		if (evenements != null) {

			Iterator<HashMap<Integer, Evenement>> iterateur = evenements.iterator();

			while (iterateur.hasNext()) {

				HashMap<Integer, Evenement> hashMap = iterateur.next();

				for (int i = 0; i != hashMap.values().toArray().length; i++) {
					if ((Evenement) hashMap.values().toArray()[i] != null) {
						ajoutEvenement((int) hashMap.keySet().toArray()[i], (Evenement) hashMap.values().toArray()[i]);
					}
				}
			}
		}
	}

	/**
	 * Méthode qui permet de détermine si la cellule est éditable
	 * 
	 * @param l'indice
	 *            de la ligne et de la colonne
	 * @return false
	 */
	public boolean isCellEditable(int indiceLigne, int indiceColonne) {
		return false;
	}

	/**
	 * Méthode qui retourne la classe de la colonne
	 * 
	 * @return Evenement.class
	 */

	public Class<?> getColumnClass(int parNum) {
		return Evenement.class;
	}

	/**
	 * Méthode qui permet de rajouter un évènement a la JTable
	 * 
	 * @param un
	 *            entier témoin du poids et un évènement
	 */

	public void ajoutEvenement(int parPoids, Evenement parEvt) {

		int indiceColonne = this.findColumn(Integer.toString(parEvt.getDate().getAnnee()));
		int indiceLigne = parPoids;

		// System.out.println("Indice colonne : " + indiceColonne + " / Indice Ligne : "
		// + indiceLigne);

		int compteur = 0;

		if (indiceColonne == -1) {
			while (Integer.parseInt((String) this.columnIdentifiers.lastElement()) - compteur != parEvt.getDate()
					.getAnnee()) {
				compteur += 1;
			}
			indiceColonne = (this.columnNumber - compteur) - 1;
		}

		while (indiceLigne < columnNumber && getValueAt(indiceLigne, indiceColonne) != null) {
			indiceLigne++;
		}

		setValueAt(parEvt, indiceLigne, indiceColonne);

	}
}
