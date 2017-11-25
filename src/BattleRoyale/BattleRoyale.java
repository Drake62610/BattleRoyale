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
import Classes.Trouillard.TrouillardNormal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BattleRoyale {
    private ArrayList <Personnage> participants = new ArrayList <Personnage>();  
    private ArrayList <Personnage> morts = new ArrayList <Personnage>(); 
    private Carte carteTerrain;

    public BattleRoyale(int nbParticipant) {
        carteTerrain = new Carte(Constant.LONGUEUR,Constant.LARGEUR);
        this.deploiement(nbParticipant, carteTerrain);
        participants.get(4).setVitesse(5);
        Collections.sort(participants, new Comparator<Personnage>() {
            @Override
            public int compare(Personnage tc1, Personnage tc2) {
                if(tc1.getVitesse()<tc2.getVitesse()){
                    return 1;
                }
                else if (tc1.getVitesse()>tc2.getVitesse()){ 
                    return -1;
                }
                return 0;
            }
        });
        //Generer les personnages sur la cartes
        //Distribuer les armes
    }
    //Getter
    public ArrayList<Personnage> getParticipants() {
        return participants;
    }

    public ArrayList<Personnage> getMorts() {
        return morts;
    }

    public Carte getCarteTerrain() {
        return carteTerrain;
    }
    
    
    public void nextTurn(){
        //Parcourt la liste des vivants et joue leurs tours
        for(int i=0;i<participants.size();i++){
            participants.get(i).jouer();
        }
        //Checker les morts et les ajouter à la liste tout en les suprimant des 
        Personnage tmp;
        System.out.println("Sont mort ce tour :");
        for(int i=0;i<participants.size();i++){
            tmp = participants.get(i);            
            if(tmp.getPv()==0){
                System.out.println(tmp.getName() + ", ");
                morts.add(tmp);
                participants.remove(i);
            }
        }
        //Restreint la zone Non implementé pour l'instant
        //Checker de nouveau les morts ? Ou le faire en dur à voir non implementé pour l'instant
        //Annonce les zones en danger du prochian tour non implémenté pour l'instant
        //Recapitule les morts fait au dessus
    }
    public void deploiement(int nbr_Perso, Carte carte){
        TrouillardNormal tmp;
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
                        
        tmp = new TrouillardNormal(y,x,carte);
        participants.add(tmp);
        carte.getCarte_Terrain()[y][x].setPerso(tmp);
        
        while (i<nbr_Perso){         
            int rngY = (int) (Math.random() * 6);
            int rngX = (int) (Math.random() * 25);
            
            if (y-rngY<0 ){
                y=Constant.LONGUEUR/2+rngY%2;
            }
            if (x-rngX<0 ){
                x=Constant.LARGEUR/2+rngX%2;
            }
            
            if (carte.getCarte_Terrain()[y-rngY][x+rngX].getPerso()==null ){
                if (!(carte.getCarte_Terrain()[y-rngY][x+rngX] instanceof Mer)){
                    System.out.println(x+rngX);
                    System.out.println(y-rngY);
                    tmp = new TrouillardNormal(y-rngY,x+rngX,carte);
                    participants.add(tmp);
                    carte.getCarte_Terrain()[y-rngY][x+rngX].setPerso(tmp);
                    i = i+1;
                }
            }  
        }
    }
}
