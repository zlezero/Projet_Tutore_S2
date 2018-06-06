package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

/**
 * Est le modèle de base pour la JTable d'événements
 * @author Thomas Vathonne
 * @author Yanis Levesque
 * @version 1
 */

public class ModeleTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	private int rowNumber = 4;
	private int columnNumber;

	public ModeleTable(FriseChronologique parFrise) {

		columnNumber = (parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee()) + 1; //On calcule le nombre de colonnes nécessaires

		this.setRowCount(rowNumber);
		this.setColumnCount(columnNumber);

		ArrayList<String> intitulesTab = new ArrayList<String>();

		//On créer les intitulés du tableau en fonction de la période
		
		for (int i = 0; i != (parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee()) + 1; i++) {
			if ((i % parFrise.getPeriodeFrise() == 0)
					|| (i == (parFrise.getDateFin().getAnnee() - parFrise.getDateDebut().getAnnee()))) {
				intitulesTab.add(Integer.toString(parFrise.getDateDebut().getAnnee() + i));
			} else {
				intitulesTab.add("");
			}
		}

		setColumnIdentifiers(intitulesTab.toArray()); //On met les identifiants

		Collection<HashMap<Integer, Evenement>> evenements = parFrise.getHashMapEvenementsPoids();

		if (evenements != null) { //Si l'événement n'est pas null

			Iterator<HashMap<Integer, Evenement>> iterateur = evenements.iterator();

			while (iterateur.hasNext()) {

				HashMap<Integer, Evenement> hashMap = iterateur.next();

				for (int i = 0; i != hashMap.values().toArray().length; i++) {
					if ((Evenement) hashMap.values().toArray()[i] != null) {
						ajoutEvenement((int) hashMap.keySet().toArray()[i], (Evenement) hashMap.values().toArray()[i]); //On ajoute l'événement au tableau
					}
				}
			}
		}
	}

	/**
	 * Méthode qui permet de détermine si la cellule est éditable
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @param indiceColonne L'indice de la colonne
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
	 * @param parPoids Le poids de l'événement
	 * @param parEvt L'événement à ajouter
	 */

	public void ajoutEvenement(int parPoids, Evenement parEvt) {

		int indiceColonne = this.findColumn(Integer.toString(parEvt.getDate().getAnnee())); //On essaye de trouver la colonne correspondant à l'année de l'événement
		int indiceLigne = parPoids;

		int compteur = 0;

		if (indiceColonne == -1) { //Si on a pas trouvé l'indice de la colonne alors on le calcul
			while (Integer.parseInt((String) this.columnIdentifiers.lastElement()) - compteur != parEvt.getDate()
					.getAnnee()) {
				compteur += 1;
			}
			indiceColonne = (this.columnNumber - compteur) - 1;
		}

		while (indiceLigne < columnNumber && getValueAt(indiceLigne, indiceColonne) != null) { //On décale pour trouver un emplacement de libre dans le tableau
			indiceLigne++;
		}

		setValueAt(parEvt, indiceLigne, indiceColonne); //On ajoute l'événement au tableau
		
	}
}
