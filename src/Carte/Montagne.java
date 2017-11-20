package Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Montagne extends Terrain{
    
    
    private final int pratictabilite;
    
    /**
     * genère aléatoirement la pratictabilite d'une montagne entre 0 et 100%
     */
    public Montagne(){
       
        pratictabilite = (int) (Math.random() * 100 );
   }
   
    /**
     *
     * @return la capacite d'un joueur à escalader la montagne
     */
    public boolean estGrimpable(){
       
       return pratictabilite<35;
   }
    
}
