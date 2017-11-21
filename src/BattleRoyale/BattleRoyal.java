package BattleRoyale;


import Classes.Personnage;
import Carte.Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */

public class BattleRoyal {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Generation Carte
        Carte carte = new Carte(Constant.LONGUEUR,Constant.LARGEUR);
        carte.afficher();
        carte.afficher2();
        
        //Generation Perso
        Personnage perso1 = new Personnage(0,0);
        System.out.println(perso1);
        Personnage perso2 = new Personnage(0,1);
        System.out.println((int)(Math.random()*100));
    }
    
}
