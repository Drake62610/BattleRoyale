package Classes.Pacifiste;

import Classes.Personnage;
import Classes.Team;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */

public interface Pacifiste {

    /**
     * Capacité spéciale la classe Pacifiste, permet de convaincre n'importe quelle autre classe de rejoindre sa team ou de créer une team
     * @param cible qui pourrait rejoindre la Team
     */
    abstract void raisonner(Personnage cible);
    /**
     * Polymorphisme pour une Team de la capacité de classe Pacifiste "raisonner"
     * @param cible 
     */
    abstract void raisonner(Team cible);
    /**
     * Getter de la Variable raison qui est une variable que chaque Pacifiste a (cf constructeurs)
     * @return 
     */
    abstract int getRaison();
}
