/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Loïc
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
