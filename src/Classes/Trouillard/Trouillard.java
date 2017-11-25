package Classes.Trouillard;


import Carte.Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public interface Trouillard {

    /**
     *
     * @param carte
     */
    void seCamoufler(Carte carte);
    boolean isHidden();
}
