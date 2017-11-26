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
import Classes.Traitre.TraitreNormal;
import Classes.Trouillard.TrouillardNormal;
import Classes.Tueur.TueurNormal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BattleRoyale {
    private ArrayList <Personnage> participants = new ArrayList <Personnage>();  
    private ArrayList <Personnage> morts = new ArrayList <Personnage>(); 
    private Carte carteTerrain;
    
    
    public BattleRoyale(int nbr_soigneur, int nbr_piegeur, int nbr_normal,
    int nbr_trouillard, int nbr_tueur, int nbr_pacifiste, int nbr_traitre) {
        carteTerrain = new Carte(Constant.LONGUEUR,Constant.LARGEUR);
        this.deploiement(carteTerrain, nbr_soigneur, nbr_piegeur, nbr_normal,
             nbr_trouillard, nbr_tueur,nbr_pacifiste,nbr_traitre);
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
        //this.pause();
    }
    public void deploiement(Carte carte, int nbr_soigneur, int nbr_piegeur, int nbr_normal,
                int nbr_trouillard, int nbr_tueur, int nbr_pacifiste, int nbr_traitre){
        
        int nbr_Perso = nbr_trouillard+ nbr_tueur + nbr_pacifiste+ nbr_traitre;
        PacifisteNormal tmp;
        TraitreNormal tmp1;
        TrouillardNormal tmp2;
        TueurNormal tmp3;
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
            if (carte_terrain[1+rngY][x+rngX].getPerso()==null ){
                if (!(carte_terrain[1+rngY][x+rngX] instanceof Mer)){
                    int rng_type_perso = (int) (Math.random() * 3);
                    if (rng_type_perso == 0){
                    tmp = new PacifisteNormal(1+rngY,x+rngX,carte);
                    participants.add(tmp);
                    carte_terrain[1+rngY][x+rngX].setPerso(tmp);
                    i = i+1;
                    }
                    if (rng_type_perso == 1){
                    tmp1 = new TraitreNormal(1+rngY,x+rngX,carte);
                    participants.add(tmp1);
                    carte_terrain[1+rngY][x+rngX].setPerso(tmp1);
                    i = i+1;
                    }
                    if (rng_type_perso == 2){
                    tmp2 = new TrouillardNormal(1+rngY,x+rngX,carte);
                    participants.add(tmp2);
                    carte_terrain[1+rngY][x+rngX].setPerso(tmp2);
                    i = i+1;
                    }
                    if (rng_type_perso == 3){
                    tmp3 = new TueurNormal(1+rngY,x+rngX,carte);
                    participants.add(tmp3);
                    carte_terrain[1+rngY][x+rngX].setPerso(tmp3);
                    i = i+1;
                    }

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
