/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package BattleRoyale;

import Classes.Personnage;
import java.util.ArrayList;

/**
 *
 * @author ISEN
 */
public class BattleRoyale {
    private ArrayList <Personnage> participants = new ArrayList <Personnage>();  
    private ArrayList <Personnage> morts = new ArrayList <Personnage>();  

    public BattleRoyale(int nbParticipant) {
        //Generer la carte
        //Generer les personnages sur la cartes
        //Distribuer les armes
    }
    
    public ArrayList<Personnage> getParticipants() {
        return participants;
    }

    public ArrayList<Personnage> getMorts() {
        return morts;
    }
    
    public void nextTurn(){
        //Parcourt la liste des vivants et joue leurs tours
        //Checker les morts et les ajouter à la liste tout en les suprimant des participants
        //Restreint la zone
        //Checker de nouveau les morts ? Ou le faire en dur à voir
        //Annonce les zones en danger du prochian tour
        //Recapitule les morts
    }
    
    
}
