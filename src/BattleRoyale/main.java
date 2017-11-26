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
        
        JFrame intG ;
        //while(true){
        BattleRoyale platinum = new BattleRoyale(0,0,0,3,4,1,2);
        
        int i= 0;
        while(i<100){    
            Thread.sleep(1000);
            platinum.nextTurn();
            intG = platinum.getCarteTerrain().getIntG();
            intG.setVisible(true);
            i++;
        }
        //}
        
        
    }  
}
