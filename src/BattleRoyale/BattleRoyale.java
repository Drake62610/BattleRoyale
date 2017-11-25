/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package BattleRoyale;

import Carte.Carte;
import Carte.Mer;
import Classes.Pacifiste.PacifisteNormal;
import Classes.Personnage;
import java.util.ArrayList;

public class BattleRoyale {
    private ArrayList <Personnage> participants = new ArrayList <Personnage>();  
    private ArrayList <Personnage> morts = new ArrayList <Personnage>(); 
    private Carte carteTerrain;

    public BattleRoyale(int nbParticipant) {
        carteTerrain = new Carte(Constant.LARGEUR,Constant.LONGUEUR);
        this.deploiement(nbParticipant, carteTerrain);
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
        for(int i=0;i<participants.size();i++){
            
        }
        //Checker les morts et les ajouter à la liste tout en les suprimant des participants
        //Restreint la zone
        //Checker de nouveau les morts ? Ou le faire en dur à voir
        //Annonce les zones en danger du prochian tour
        //Recapitule les morts
    }
    public void deploiement(int nbr_Perso, Carte carte){
        Personnage tmp;
        int x=1;
        int i =1;
        int y=Constant.LONGUEUR-1;
        
        // on recupère notre (0;0) i.e le point le plus en bas a gauche de la carte
        
        while(carte.getCarte_Terrain()[y][x] instanceof Mer){
            y=y-1;
            if (y<1){
                x=x+1;
                y=Constant.LONGUEUR-1;
            }
        }        
        // on place notre premier participant
        tmp = new PacifisteNormal(y,x,carte);
        participants.add(tmp);
        carte.getCarte_Terrain()[y][x].setPerso(tmp);
        // a partir du premier on place les autres
        while (i<nbr_Perso){         
            int rngY = (int) (Math.random() * 6);
            int rngX = (int) (Math.random() * 20);
            if (carte.getCarte_Terrain()[y-rngY][x+rngX].getPerso()==null ){
                if (!(carte.getCarte_Terrain()[y-rngY][x+rngX] instanceof Mer)){
                    System.out.println(x+rngX);
                    System.out.println(y-rngY);
                    tmp = new PacifisteNormal(y-rngY,x+rngX,carte);
                    participants.add(tmp);
                    carte.getCarte_Terrain()[y-rngY][x+rngX].setPerso(tmp);
                    i = i+1;
                }
            }  
        }
    }
}
