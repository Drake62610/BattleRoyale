package Classes.Pacifiste;

import Classes.Personnage;
import Classes.Team;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */

public interface Pacifiste {

    /**
     * Capacité spéciale de la caractéristique Pacifiste, permet de convaincre n'importe quelle autre classe de rejoindre sa team ou de créer une team
     * @param cible qui pourrait rejoindre la Team
     */
    abstract void raisonner(Personnage cible);
    abstract void raisonner(Team cible);
    abstract int getRaison();
}
