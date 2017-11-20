package Classes;


import Carte.Terrain;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Piegeur extends Personnage {

    /**
     * Classe Piegeur possede le même constructeur que la classe Personnage, les statisitque sont établie en bas d'arbres
     * @param position_x
     * @param position_y
     */
    public Piegeur(int position_x, int position_y) {
        super(position_x, position_y);
    }
    
    /**
     * Permet de poser un piège sur la carte
     * @param carte Terrain de jeu du battle royale
     */
    public void posePiege(Terrain[][] carte){
        Terrain terrain = carte[this.getPosition_x()][this.getPosition_y()];
        terrain.setPiege(true);
    }
}
