
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleroyal;

/**
 *
 * @author Loïc
 */

import java.util.Random;
import java.lang.Math; 

public class BattleRoyal {

    /**
     * @param args the command line arguments
     */     
    private static final int LARGEUR=50;
    private static final int LONGUEUR=12;
    
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] _carte = new int[LONGUEUR][LARGEUR];
        Carte carte = new Carte();
        carte.créer(_carte,LARGEUR,LONGUEUR);
        carte.afficher(_carte);
    }
    
}