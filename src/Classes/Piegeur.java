package Classes;


import Carte.Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Piegeur extends Personnage {

    /**
     * Classe Piegeur possede le même constructeur que la classe Personnage, les statisitque sont établie en bas d'arbres
     * @param pv
     * @param force
     * @param deplacement
     * @param vitesse
     * @param position_x
     * @param position_y
     * @param carte
     */
    public Piegeur(int pv, int force, int deplacement, int vitesse, int position_x, int position_y, Carte carte) {
        super(pv,force,deplacement,vitesse,position_x, position_y,carte);
    }
    
    /**
     * Permet de poser un piège sur la carte
     */
    public void posePiege(){
        System.out.println(this.getName() + " pose un piège ! ");
        this.getCarte().getCarte_Terrain()[this.getPosition_x()][this.getPosition_y()].setPiege(true);
    }
}
