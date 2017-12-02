package Classes.Trouillard;


import Carte.Carte;

/**
 * Projet JAVA Semestre1 M1
 * Interface du comportement Trouillard
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public interface Trouillard {

    /**
     * Capacité spéciale la classe Trouillard, permet de se cacher dans les Bois si le terrain le permet
     */
    void seCamoufler();
    /**
     * Getter pour la variable hidden, variable indiquant si un personnage est visible des autres ou non
     * @return 
     */
    boolean isHidden();
    /**
     * Fonction inutile qui fait pousser un cri de peur au Trouillard
     */
    void pleurer();
}
