package Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Foret extends Terrain{
    //VARIABLE DE CLASSE
    private final int densiteArbre;   //Permet de pouvoir se cacher ou non dans la forêt

    /**
     * Genère aleatoire la densité d'une case foret entre 0 et 100%
     */
    public Foret(){
        densiteArbre = (int) (Math.random() * 100 );
    }
        
    /**
     * 
     * @return True si un personnage sur cette case peut se cacher, False sinon
     */
    public boolean seCacher(){
        
        return densiteArbre>50;
    }
}
