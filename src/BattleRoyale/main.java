package BattleRoyale;

import Exception.InitialisationPersonnageException;
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
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        JFrame intG ;
        //while(true){
        BattleRoyale platinum;
        try {
            platinum = new BattleRoyale(20,0,0,0,20,0,0); //nbr_soigneur, nbr_piegeur, nbr_normal, nbr_trouillard
            platinum.lancer(1);
        } catch (InitialisationPersonnageException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
}
