package BattleRoyale;

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
        BattleRoyale platinum = new BattleRoyale(0,4,0,5,0,0,0); //nbr_soigneur, nbr_piegeur, nbr_normal, nbr_trouillard
        platinum.lancer(1);
    }  
}
