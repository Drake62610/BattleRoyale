/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package BattleRoyale;

import Carte.Carte;
import Carte.Mer;
import Classes.Personnage;
import java.util.ArrayList;

/**
 *
 * @author ISEN
 */
public class BattleRoyale {
    private ArrayList <Personnage> participants = new ArrayList <Personnage>();  
    private ArrayList <Personnage> morts = new ArrayList <Personnage>(); 
    private Carte carteTerrain;

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
    public void deploiement(int nbr_Perso, Carte carte){
        Personnage tmp;
        int x=1;
        int y=Constant.LONGUEUR-1;
        
        // on recupère notre (0;0) i.e le point le plus en bas a gauche de la carte
        
        while(carte.getCarte_Terrain()[y][x] instanceof Mer){
            y = y-1;
            x = x+5;
            System.out.println(x);
            System.out.println(y);
        }

        
        for (int i=0;i<nbr_Perso;i++){
         
            int rng = (int) (Math.random() * 3 );
            int rng2 = (int) (Math.random() * 3);
            
            if (!(carte.getCarte_Terrain()[y-rng2][x+rng] instanceof Mer)){
                if (carte.getCarte_Terrain()[y-rng2][x+rng].getPerso()!=null){
                    //tmp = new Personnage(x+rng,y-rng2,carte);
                    //participants.add(tmp);
                  //  carte.getCarte_Terrain()[x+rng][y-rng2].setPerso(tmp);
                }
                else{
                    for (int m=0; m<Constant.LONGUEUR; m++){
                        for (int n=0; n<Constant.LARGEUR; n++){
                            if (!(carte.getCarte_Terrain()[m][n] instanceof Mer)){
                                 if (carte.getCarte_Terrain()[m][n].getPerso()==null){
                                     tmp = new Personnage(m,n,carte);
                                     participants.add(tmp);
                                     carte.getCarte_Terrain()[m][n].setPerso(tmp);
                                 }
                            }
                        }
                    }
                }    
            }
        
        }
    }
}
