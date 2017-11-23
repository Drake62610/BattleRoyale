package BattleRoyale;


import Classes.Personnage;
import Carte.Carte;
import Carte.Foret;
import Carte.Terrain;
import Classes.Pacifiste.PacifisteNormal;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */

public class main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Generation Carte
        Carte carte = new Carte(Constant.LONGUEUR,Constant.LARGEUR);
        //carte.afficher();

         // deploiement
         
         BattleRoyale broyale = new BattleRoyale (20);
         broyale.deploiement(20, carte);
        carte.afficher2();
        
    }  
}
