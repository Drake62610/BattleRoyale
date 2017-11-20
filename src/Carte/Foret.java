package Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Foret extends Terrain{
    
    private final int densiteArbre;
    
    /**
     * genère aleatoire la densité d'une case foret entre 0 et 100%
     */
    public Foret(){
        
        densiteArbre = (int) (Math.random() * 100 );
    }
        
    /**
     *
     * @return si un personnage sur cette case peut se cacher
     */
    public boolean seCacher(){
        
        return densiteArbre>50;
    }
}
