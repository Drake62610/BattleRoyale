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
        BattleRoyale platinum = new BattleRoyale(0,3,0,3,4,1,2); //nbr_soigneur, nbr_piegeur, nbr_normal, nbr_trouillard
        while(platinum.getGagnant() == null){                    //int nbr_tueur int nbr_pacifiste, int nbr_traitre
            platinum.nextTurn();
            intG = platinum.getCarteTerrain().getIntG();
            intG.setVisible(true);
            //platinum.pause();
            Thread.sleep(500);
            intG.dispose();
        }
        
        System.out.println("Nous avons un gagnant : " + platinum.getGagnant());
        //}
    }  
}
