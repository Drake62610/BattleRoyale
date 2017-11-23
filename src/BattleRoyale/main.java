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
        carte.afficher();
        //carte.afficher2();
        
        //Generation Perso
        Personnage perso1 = new Personnage(0,0);
        System.out.println(perso1);
        Personnage perso2 = new Personnage(0,1);
        PacifisteNormal pacifiste = new PacifisteNormal(2,3);
        pacifiste.raisonner(perso1);
        pacifiste.raisonner(perso2);
        System.out.println(perso1);
        
        Foret terrain = new Foret();
        Terrain memeterrain = terrain;
        if (memeterrain instanceof Foret){
            System.out.println("Good");
        }
    }
    
}