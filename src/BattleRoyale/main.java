package BattleRoyale;

import Exception.InitialisationPersonnageException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        //Création d'un battleroyale appelé "platinum"
        BattleRoyale platinum;
        try {
            //initialisation de platinum
            platinum = new BattleRoyale(5,3,15,3,8,5,7); //nbr_soigneur, nbr_piegeur, nbr_normal, 
                                                          //nbr_trouillard,nbr_tueur,nbr_pacifiste,nbr_traitre
            //On lance le jeu
            platinum.lancer(1);
            //platinum.pause();
        } catch (InitialisationPersonnageException ex) {//Exception intervennant lors de l'initialisation 
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
}
