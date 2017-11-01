
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Loïc
 */

public class BattleRoyal {

    /**
     * @param args the command line arguments
     */     
    private static final int LARGEUR=50;
    private static final int LONGUEUR=12;
    
    public static void main(String[] args) {
        // TODO code application logic here
        Carte carte = new Carte(LONGUEUR,LARGEUR);
        carte.afficher();
        Constant constante = new Constant();
        
        Personnage perso1 = new Personnage(0,0);
        System.out.println(perso1.toString());
    }
    
}