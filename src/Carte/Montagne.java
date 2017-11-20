package Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Montagne extends Terrain{
    //VARIABLE DE CLASSE
    private final int pratictabilite;   //Definie si la montagne est escaladable
    
    /**
     * Constructeur de la classe Montagne
     * genère aléatoirement la pratictabilite d'une montagne entre 0 et 100%
     */
    public Montagne(){
       
        pratictabilite = (int) (Math.random() * 100 );
   }
   
    /**
     * Le terrain peut être impraticable
     * @return True si la capacite d'un joueur à escalader la montagne, False sinon
     */
    public boolean estGrimpable(){
       
       return pratictabilite>33;
   }
    
}
