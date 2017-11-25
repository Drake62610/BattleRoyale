/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package BattleRoyale;

import Carte.Carte;
import Carte.Mer;
import Carte.Terrain;
import Classes.Pacifiste.PacifisteNormal;
import Classes.Personnage;
import Classes.Trouillard.TrouillardNormal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BattleRoyale {
    private ArrayList <Personnage> participants = new ArrayList <Personnage>();  
    private ArrayList <Personnage> morts = new ArrayList <Personnage>(); 
    private Carte carteTerrain;
    
    
    public BattleRoyale(int nbParticipant) {
        carteTerrain = new Carte(Constant.LONGUEUR,Constant.LARGEUR);
        this.deploiement(nbParticipant, carteTerrain);
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
        this.pause();
    }
    public void deploiement(int nbr_Perso, Carte carte){
        TrouillardNormal tmp;
        int x=0;
        int i = 1;
        int y= 0 ;
        Terrain[][] carte_terrain = carte.getCarte_Terrain();
        // on recupère notre (0;0) i.e le point le plus en bas a gauche de la carte
        
        while(carte_terrain[y][x] instanceof Mer){
            y++;
            if (y == carte_terrain.length){
                x=x+1;
                y=0;
            }
        }
        while (i<nbr_Perso+1){         
            int rngY = (int) (Math.random() * Constant.LARGEUR-3 +1);  //En partant du coté inférieur gauche du rectangle on peut avor de la terre sur LARGEUR-3 blocs
            int rngX = (int) (Math.random() * Constant.LONGUEUR/2+3 +1); 
            //(int)(Math.random()*(tabNom.length))
            System.out.println(rngY);
            System.out.println(rngX);
            System.out.println("****");
            if (carte_terrain[1+rngY][x+rngX].getPerso()==null ){
                if (!(carte_terrain[1+rngY][x+rngX] instanceof Mer)){
                    tmp = new TrouillardNormal(1+rngY,x+rngX,carte);
                    participants.add(tmp);
                    carte_terrain[1+rngY][x+rngX].setPerso(tmp);
                    i = i+1;
                }
            }  
        }
    }
    
    public void pause(){
        System.out.println("Appuyez sur Enter pour continuer");
        Scanner scanner = new Scanner(System.in); //Pour les inputs entre tours de jeux
        scanner.nextLine();
    }
}
