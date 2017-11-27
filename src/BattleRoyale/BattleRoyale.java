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
import Classes.Pacifiste.PacifisteSoigneur;
import Classes.Personnage;
import Classes.Traitre.TraitreNormal;
import Classes.Traitre.TraitrePiegeur;
import Classes.Traitre.TraitreSoigneur;
import Classes.Trouillard.TrouillardNormal;
import Classes.Trouillard.TrouillardPiegeur;
import Classes.Trouillard.TrouillardSoigneur;
import Classes.Tueur.TueurNormal;
import Classes.Tueur.TueurPiegeur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BattleRoyale {
    private ArrayList <Personnage> participants = new ArrayList <Personnage>();  
    private ArrayList <Personnage> morts = new ArrayList <Personnage>(); 
    private Carte carteTerrain;
    private int tour;
    private int zone;
    private int cadenceTour = 3;
    
    
    public BattleRoyale(int nbr_soigneur, int nbr_piegeur, int nbr_normal,
    int nbr_trouillard, int nbr_tueur, int nbr_pacifiste, int nbr_traitre) {
        carteTerrain = new Carte(Constant.LONGUEUR,Constant.LARGEUR);
        tour = 0;
        zone =0;
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
        ArrayList <Personnage> mortDuTour = new ArrayList <Personnage>(); 
        System.out.println("**************");
        System.out.println("DEBUT DU TOUR "+tour);
        System.out.println("**************");
        //Restreint la zone
        this.determine_zone_rouge();
        //Parcourt la liste des vivants et joue leurs tours
        for(int i=0;i<participants.size();i++){
            participants.get(i).jouer();
        }
        //Checker les morts et les ajouter à la liste tout en les suprimant de la carte
        Personnage tmp;
        for(int i=0;i<participants.size();i++){
            tmp = participants.get(i);            
            if(tmp.getPv()==0){
                mortDuTour.add(tmp);
                participants.remove(i);
                i--;
                //tmp.getCarte().getCarte_Terrain()[tmp.getPosition_x()][tmp.getPosition_y()].setPerso(null);
            }
        }
        //Check si des gens sont en zone restreinte
        for(int i=0;i<participants.size();i++){
            tmp = participants.get(i);            
            if(tmp.getCarte().getCarte_Terrain()[tmp.getPosition_x()][tmp.getPosition_y()].isDangerImminant()){
                tmp.getCarte().getCarte_Terrain()[tmp.getPosition_x()][tmp.getPosition_y()].setPerso(null);
                System.out.println(tmp.getName() + " a été rattrapé par la zone Rouge.");
                mortDuTour.add(tmp);
                for(i=0;i<participants.size();i++)  
                {  
                   if(participants.get(i).equals(tmp))  
                   {  
                      break;
                   }  
                }  
                participants.remove(i);
            }
        }
        //Checker de nouveau les morts ? Ou le faire en dur à voir non implementé pour l'instant
        //Annonce les zones en danger du prochian tour non implémenté pour l'instant
        //Recapitule les morts fait au dessus
        
        System.out.println("Résumé du tour" + tour);
        System.out.println("Sont mort ce tour :");
        if(!mortDuTour.isEmpty()){
            for(int i=0;i<mortDuTour.size();i++){
                System.out.println("    "+ mortDuTour.get(i).getName());
            }
            morts.addAll(mortDuTour);
        }
        else{
            System.out.println("Personne !");
        }
        
        System.out.println("");
        System.out.println("Il reste " + participants.size() +" participants.");
        System.out.println(morts.size() + " ont déjà succombé.");
        
        
        if (this.tour%cadenceTour==0 && this.tour !=0){
            zone ++;
            this.determine_zone_rouge();
            System.out.println("La zone rouge avance !");
        }
        
        //this.pause();
        tour ++;        
    }
    public void deploiement(Carte carte, int nbr_soigneur, int nbr_piegeur, int nbr_normal,
                int nbr_trouillard, int nbr_tueur, int nbr_pacifiste, int nbr_traitre){
        
        int nbr_Perso = nbr_trouillard+ nbr_tueur + nbr_pacifiste+ nbr_traitre;
        PacifisteNormal tmp00;
        PacifisteSoigneur tmp01;
        TraitreNormal tmp10;
        TraitrePiegeur tmp11;
        TraitreSoigneur tmp12;
        TrouillardNormal tmp20;
        TrouillardPiegeur tmp21;
        TrouillardSoigneur tmp22;
        TueurNormal tmp30;
        TueurPiegeur tmp31;
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
            int test = i;
            int rng_carac_perso =(int) (Math.random() * 2);
            int rngY = (int) (Math.random() * Constant.LARGEUR-3 +1);  //En partant du coté inférieur gauche du rectangle on peut avor de la terre sur LARGEUR-3 blocs
            int rngX = (int) (Math.random() * Constant.LONGUEUR/2+3 +1); 
            if (carte_terrain[1+rngY][x+rngX].getPerso()==null ){
                if (!(carte_terrain[1+rngY][x+rngX] instanceof Mer)){                    
                    int rng_type_perso = (int) (Math.random() * 4);
                    if (rng_type_perso == 0){
                        if(rng_carac_perso%2 == 0 && nbr_soigneur>0){
                            nbr_soigneur=nbr_soigneur-1;
                            tmp01 = new PacifisteSoigneur(1+rngY,x+rngX,carte);
                            participants.add(tmp01);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp01);
                            i = i+1;
                        }
                        if(rng_carac_perso%2 == 1 && nbr_normal>0){
                            nbr_normal=nbr_normal-1;
                            tmp00 = new PacifisteNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp00);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp00);
                            i = i+1;
                        }
                        if (test==i){
                            tmp00 = new PacifisteNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp00);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp00);
                            i = i+1;
                        }                        
                    }
                    if (rng_type_perso == 1){
                        if(rng_carac_perso == 0 && nbr_soigneur>0){
                            tmp12 = new TraitreSoigneur(1+rngY,x+rngX,carte);
                            participants.add(tmp12);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp12);
                            i = i+1;
                        }
                        if(rng_carac_perso == 1 && nbr_normal>0){
                            tmp10 = new TraitreNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp10);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp10);
                            i = i+1;
                        }
                        if(rng_carac_perso == 2 && nbr_piegeur>0){
                            tmp11 = new TraitrePiegeur(1+rngY,x+rngX,carte);
                            participants.add(tmp11);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp11);
                            i = i+1;
                        }
                        if (test==i){
                            tmp10 = new TraitreNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp10);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp10);
                            i = i+1;
                        }                                                  
                    }
                    if (rng_type_perso == 2){
                        if(rng_carac_perso == 0 && nbr_soigneur>0){
                            tmp22 = new TrouillardSoigneur(1+rngY,x+rngX,carte);
                            participants.add(tmp22);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp22);
                            i = i+1;
                        }
                        if(rng_carac_perso == 1 && nbr_normal>0){
                            tmp20 = new TrouillardNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp20);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp20);
                            i = i+1;
                        }
                        if(rng_carac_perso == 2 && nbr_piegeur>0){
                            tmp21 = new TrouillardPiegeur(1+rngY,x+rngX,carte);
                            participants.add(tmp21);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp21);
                            i = i+1;
                        }
                        if (test==i){
                            tmp20 = new TrouillardNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp20);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp20);
                            i = i+1;
                        }  
                    }
                    if (rng_type_perso == 3){
                        if(rng_carac_perso%2 == 0 && nbr_normal>0){
                            tmp30 = new TueurNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp30);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp30);
                            i = i+1;
                        }
                        if(rng_carac_perso%2 == 1 && nbr_piegeur>0){
                            tmp31 = new TueurPiegeur(1+rngY,x+rngX,carte);
                            participants.add(tmp31);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp31);
                            i = i+1;
                        }
                        if (test==i){
                            tmp30 = new TueurNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp30);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp30);
                            i = i+1;
                        }                          
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
    public void determine_zone_rouge(){
       
       Terrain[][] carte_terrain = carteTerrain.getCarte_Terrain();
       
       int cote = max(Constant.LARGEUR, Constant.LONGUEUR);
       int param =3*cote/4+4;       
       int param2 =cote/4-4;
       
       for (int x=param2;x<param;x++){
           if (x<Constant.LONGUEUR && Constant.LARGEUR/2-cote/2+zone>0){
               carteTerrain.restreindre(Constant.LARGEUR/2-cote/2+zone, x);
           }
           if(x<Constant.LONGUEUR && Constant.LARGEUR/2+cote/2-zone<Constant.LARGEUR){
               carteTerrain.restreindre(Constant.LARGEUR/2+cote/2-zone, x);
           }
           if(x<Constant.LARGEUR && Constant.LONGUEUR/2-cote/2+zone>0){
               carteTerrain.restreindre( x-5,Constant.LONGUEUR/2-cote/2+zone);               
           }
           if(x<Constant.LARGEUR && Constant.LONGUEUR/2+cote/2-zone<Constant.LONGUEUR){
               carteTerrain.restreindre( x-5,Constant.LONGUEUR/2+cote/2-zone);
           }
        }
    }
   private int max(int a, int b){
       if (a<b){
           return b;
       }else{
           return a;
       }
   }
}