package Carte;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Loïc
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
