package BattleRoyale;


import Classes.Personnage;
import Carte.Carte;
import Carte.Foret;
import Carte.Terrain;
import Classes.Pacifiste.PacifisteNormal;
import Classes.Trouillard.TrouillardNormal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */

public class main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // Generation Carte
        Carte carte = new Carte(Constant.LONGUEUR,Constant.LARGEUR);
        //carte.afficher();

         // deploiement
         
        //BattleRoyale broyale = new BattleRoyale (20);
        //broyale.deploiement(20, carte);
        //carte.getCarte_Terrain()[Constant.LONGUEUR/2][Constant.LARGEUR/2].setPerso(new TrouillardNormal(Constant.LONGUEUR/2,Constant.LARGEUR/2,carte));
        //TrouillardNormal perso1 = (TrouillardNormal)carte.getCarte_Terrain()[Constant.LONGUEUR/2][Constant.LARGEUR/2].getPerso();
        //System.out.println(perso1);
        
        //JFrame intG ;
        //intG = carte.getIntG();
        //intG.setVisible(true);
        //Thread.sleep(1000);
        //intG.dispose(); //Ferme l'interface graphique
        //perso1.moveWest();
        //perso1.phaseDeplacement(); //Il ne faut surtout pas modifier la carte quand elle est affichée, ça fait bug le JFrame
        //perso1.phaseAction();
        //intG= carte.getIntG(); //Toujours réinitialiser l'interface
        //intG.setVisible(true); //Afficher
        //carte.afficher2();
        
        BattleRoyale platinum = new BattleRoyale(5);
        JFrame intG ;
        intG = platinum.getCarteTerrain().getIntG();
        intG.setVisible(true);
        Thread.sleep(1000);
        //intG.dispose();
        platinum.nextTurn();
        intG = platinum.getCarteTerrain().getIntG();
        intG.setVisible(true);
    }  
}
