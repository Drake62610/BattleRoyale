
package BattleRoyale;

import Carte.Carte;
import Carte.Mer;
import Carte.Terrain;
import Classes.Pacifiste.PacifisteNormal;
import Classes.Pacifiste.PacifisteSoigneur;
import Classes.Personnage;
import Classes.Team;
import Classes.Traitre.Traitre;
import Classes.Traitre.TraitreNormal;
import Classes.Traitre.TraitrePiegeur;
import Classes.Traitre.TraitreSoigneur;
import Classes.Trouillard.TrouillardNormal;
import Classes.Trouillard.TrouillardPiegeur;
import Classes.Trouillard.TrouillardSoigneur;
import Classes.Tueur.TueurNormal;
import Classes.Tueur.TueurPiegeur;
import Exception.InitialisationCarteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JFrame;
import Exception.InitialisationPersonnageException;
/**
 * Projet JAVA Semestre1 M1
 * Classe BattleRoyale avec les règles
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class BattleRoyale {
    private ArrayList <Personnage> participants = new ArrayList <>();  
    private ArrayList <Personnage> morts = new ArrayList <>(); 
    private Carte carteTerrain;
    private int tour;
    private int zone;
    private int cadenceTour = 2;    
    private Object gagnant = null;
    
    //CONSTRUCTOR
    /**
     * Constucteur de la classe BattleRoyale avec tout les paramètres possible.
     * Gere le jeu en lui meme : créé les personnages, les deploient sur la carte
     * et effectue les transitions entre deux tours
     * @param nbr_soigneur
     * @param nbr_piegeur
     * @param nbr_normal
     * @param nbr_trouillard
     * @param nbr_tueur
     * @param nbr_pacifiste
     * @param nbr_traitre
     * @throws Exception.InitialisationPersonnageException
     */
    public BattleRoyale(int nbr_soigneur, int nbr_piegeur, int nbr_normal,
    int nbr_trouillard, int nbr_tueur, int nbr_pacifiste, int nbr_traitre) throws InitialisationPersonnageException, InitialisationCarteException {
        carteTerrain = new Carte(Constant.LONGUEUR,Constant.LARGEUR);
        tour = 0;
        zone =0;
        if(nbr_soigneur+nbr_piegeur+nbr_normal < nbr_trouillard+ nbr_tueur + nbr_pacifiste + nbr_traitre){
            throw new InitialisationPersonnageException("La somme des classes ne correspond pas à la somme des caractéristiques");
        }
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
    }

    /**
     * Constructeur de la classe BattleRoyale avec une config de base
     * @throws InitialisationPersonnageException 
     */
    public BattleRoyale() throws InitialisationPersonnageException, InitialisationCarteException {
        this(5,3,15,3,8,5,7); //Une config de base que j'aime bien
    }    
    
    //Getter
    /**
     *getter de la variable participants
     * @return
     */
    public ArrayList<Personnage> getParticipants() {
        return participants;
    }
    /**
     * Getter de la variable du nombre de morts
     * @return
     */
    public ArrayList<Personnage> getMorts() {
        return morts;
    }
    /**
     * Getter de la variable carteTerrain
     * @return
     */
    public Carte getCarteTerrain() {
        return carteTerrain;
    }    
    /**
     * Getter de la variable gagnant
     * @return 
     */
    public Object getGagnant() {
        return gagnant;
    }
    
    /**
     * Lancer permet de lance rle BattleRoyale, suivre son déroulement et l'arréter quand il se termine
     * @param mode Si mode = 0 alors on est en full text, si mode = 1 alors on ajoute l'interface graphique (JPanel + JFrame donc limitée)
     * @throws InterruptedException 
     */
    public void lancer(int mode) throws InterruptedException{
        JFrame intG ;
        intG = this.getCarteTerrain().getIntG(); //On récupère l'interface en créant une fenêtre
        if( mode ==1){intG.setVisible(true);} //On affiche une première fois pour l'innitialisation
        while(this.getGagnant() == null){                   
            this.nextTurn(); //On joue un tour entier
            if( mode ==1){ //Si mode graphique alors on afiiche
                intG.setContentPane(this.carteTerrain.getPanneau()); //Renvoit la classe Panneau de Carte, cela permet
                intG.setVisible(true); // Permet d'afficher la fenêtre
                Thread.sleep(500);
            }
        }
        if (gagnant instanceof Team){
            for(int i=0;i<participants.size();i++){
                if (participants.get(i) instanceof Traitre){ 
                    System.out.println("Oh non il y avait un Traitre dans la Team ! Il a empoisonné tout leurs sommeil !");
                    gagnant = participants.get(i);
                }
            }
        }
        if (gagnant instanceof Team){
            System.out.println("Nous avons un gagnant : " + ((Team)this.getGagnant()).getLeader().getName() + " et toute sa Team !");
            System.out.println(this.getGagnant());
        }
        else{
            System.out.println("Nous avons un gagnant : " + ((Personnage)this.getGagnant()).getName());
        }
    }
    
    /**
     * Gère les étapes et la fin d'un tour de jeu : 
     *  -Faire avancer la zone rouge
     *  -Faire jouer tout le monde
     *  -Ajouter à la liste des morts ceux qui ont 0 PV
     *  -Tuer les personnages en zone rouge et les ajouter à la liste des morts
     *  -Recapitulation du tour
     */
    public void nextTurn(){
        ArrayList <Personnage> mortDuTour = new ArrayList <Personnage>(); 
        int stopwatch;
        System.out.println("******************");
        System.out.println("DEBUT DU TOUR "+tour);
        System.out.println("******************");
        //La zone rouge avance, à la fin du tour elle prend effet
        stopwatch =stop();
        if (stopwatch == 1){
            if(participants.size()<1){
                gagnant = this;
                }
            else if (participants.get(0).getTeam() == null){
                gagnant = participants.get(0);
            }
            else{
                gagnant = participants.get(0).getTeam();
            }
        }
        else if(this.tour%cadenceTour==0 && this.tour !=0){
            zone ++;
            this.determine_zone_rouge();
            System.out.println("La zone rouge avance !");
        }
        //Parcourt la liste des vivants et joue leurs tours
        for(int i=0;i<participants.size();i++){
            if (participants.get(i).getPv() != 0){ // LES MORTS NE JOUENT PAS
            participants.get(i).jouer();
            }
        }
        //Checker les morts et les ajouter à la liste tout en les suprimant des 
        Personnage tmp;
        for(int i=0;i<participants.size();i++){
            tmp = participants.get(i);            
            if(tmp.getPv()==0){
                //System.out.println(tmp.getName() + " est mort ce tour.");
                mortDuTour.add(tmp);
                participants.remove(i);
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
        //Recapitule les morts fait au dessus      
        System.out.println("******************");
        System.out.println("Résumé du tour" + tour);
        if(!mortDuTour.isEmpty()){            
            System.out.print("Sont mort ce tour :");
            for(int i=0;i<mortDuTour.size();i++){
                System.out.print(" "+ mortDuTour.get(i).getName());
            }
            System.out.println(".");
            System.out.println("Paix à leurs âmes");
            morts.addAll(mortDuTour);
        }        
        System.out.println("");
        System.out.println("Il reste " + participants.size() +" participants.");
        System.out.println(morts.size() + " ont déjà succombé.");
        System.out.println("******************");
        if(participants.size() == 1 || (participants.get(0).getTeam()!=null && participants.get(0).getTeam().getMembres().size() == participants.size())){
            if (participants.get(0).getTeam() == null ){
                gagnant = participants.get(0);
            }
            else{
                gagnant = participants.get(0).getTeam();
            }
        }
        //this.pause();
        tour ++;        
    }
    
    /**
     * A partir du nombre de participants et du nombre de type de personnage que l'on veut
     * cette fonction va les placer de manière pseudo-aléatoire sur la carte
     * Mettre le nombre de participants à l'argument 1,2,3 va activer une génération aléatoire des soigneurs/piégeurs/normaux
     * @param carte corresponds a notre carte de jeu
     * @param nbr_soigneur
     * @param nbr_piegeur
     * @param nbr_normal
     * @param nbr_trouillard
     * @param nbr_tueur
     * @param nbr_pacifiste
     * @param nbr_traitre 
     */
    public void deploiement(Carte carte, int nbr_soigneur, int nbr_piegeur, int nbr_normal,
                int nbr_trouillard, int nbr_tueur, int nbr_pacifiste, int nbr_traitre) throws InitialisationPersonnageException{
        
        // declaration de nos variables locales 
        
        int nbr_Perso = nbr_trouillard+ nbr_tueur + nbr_pacifiste+ nbr_traitre;
        int x=0;
        int i = 1;
        int y= 0 ;
        
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
 
        Terrain[][] carte_terrain = carte.getCarte_Terrain();
        
        // on recupère notre (0;0) i.e le point le plus en bas a gauche de la carte
        
        //bloc permettant de trouver le bout de terre le plus a gauche puis le plus
        // en bas de notre carte
        while(carte_terrain[y][x] instanceof Mer){
            y++;
            if (y == carte_terrain.length){
                x=x+1;
                y=0;
            }
        }        
        // bloc créant les personnages en fonctions de paramètres d'entrées
        while (i<=nbr_Perso){             
            // variables qui s'incrémentes ou change de valeurs dans le bloc            
            int test = i;
            int rng_carac_perso =(int) (Math.random() * 3);
            int rngY = (int) (Math.random() * Constant.LARGEUR-3 +1);  
            int rngX = (int) (Math.random() * Constant.LONGUEUR/2+3 +1);            
            //s'il n'y a personne sur une case            
            if (carte_terrain[1+rngY][x+rngX].getPerso()==null ){                
                // si ce terrain n'est pas une mer                
                if (!(carte_terrain[1+rngY][x+rngX] instanceof Mer)){                      
                    // on créé un nombre entre 0 et 3                    
                   // int rng_type_perso = (int) (Math.random() * 4);
                    // si c'est 0 et que le nombre de pacifiste voulu est superieur a 0
                    //alors c'est un pacifiste                    
                    if (nbr_pacifiste>0){                        
                        //si la variable aléatoire modulo 2 et que le nombre de soigneur desiré et superieur a 0
                        // alors c'est un pacifiste soigneur                        
                        if(rng_carac_perso%2 == 0 && nbr_soigneur>0){                            
                            nbr_soigneur=nbr_soigneur-1;
                            nbr_pacifiste=nbr_pacifiste-1;                            
                            tmp01 = new PacifisteSoigneur(1+rngY,x+rngX,carte);
                            participants.add(tmp01);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp01);
                            i = i+1;
                        }                        
                        // idem mais avec le modulo egal à 1 
                        // et on obtient un pacifiste normal                        
                        if(rng_carac_perso%2 == 1 && nbr_normal>0){                            
                            nbr_normal=nbr_normal-1;
                            nbr_pacifiste=nbr_pacifiste-1;                            
                            tmp00 = new PacifisteNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp00);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp00);
                            i = i+1;                            
                        }                                                                                                                               
                    }                    
                    // on recommence avec les traitres le fonctionnement du bloc
                    // est identique a celui du précedent
                    if ( nbr_traitre>0){                        
                        if(rng_carac_perso == 0 && nbr_soigneur>0){                            
                            nbr_soigneur=nbr_soigneur-1;
                            nbr_traitre=nbr_traitre-1;
                            tmp12 = new TraitreSoigneur(1+rngY,x+rngX,carte);
                            participants.add(tmp12);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp12);
                            i = i+1;                            
                        }
                        if(rng_carac_perso == 1 && nbr_normal>0){                            
                            nbr_traitre=nbr_traitre-1;
                            nbr_normal=nbr_normal-1;                            
                            tmp10 = new TraitreNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp10);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp10);
                            i = i+1;                            
                        }
                        if(rng_carac_perso == 2 && nbr_piegeur>0){                            
                            nbr_piegeur=nbr_piegeur-1;
                            nbr_traitre=nbr_traitre-1;                            
                            tmp11 = new TraitrePiegeur(1+rngY,x+rngX,carte);
                            participants.add(tmp11);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp11);
                            i = i+1;                            
                        }                                            
                    }                    
                    // cette fois ci avec les trouillards                    
                    if ( nbr_trouillard>0){                        
                        if(rng_carac_perso == 0 && nbr_soigneur>0){                            
                            nbr_trouillard=nbr_trouillard-1;
                            nbr_soigneur=nbr_soigneur-1;                            
                            tmp22 = new TrouillardSoigneur(1+rngY,x+rngX,carte);
                            participants.add(tmp22);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp22);
                            i = i+1;                            
                        }
                        if(rng_carac_perso == 1 && nbr_normal>0){                            
                            nbr_trouillard=nbr_trouillard-1;
                            nbr_normal=nbr_normal-1;                            
                            tmp20 = new TrouillardNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp20);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp20);
                            i = i+1;                            
                        }
                        if(rng_carac_perso == 2 && nbr_piegeur>0){                            
                            nbr_piegeur=nbr_piegeur-1;
                            nbr_trouillard=nbr_trouillard-1;                            
                            tmp21 = new TrouillardPiegeur(1+rngY,x+rngX,carte);
                            participants.add(tmp21);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp21);
                            i = i+1;                            
                        }
                    }                    
                    // et enfin avec les tueurs                    
                    if ( nbr_tueur>0){
                        if(rng_carac_perso%2 == 0 && nbr_normal>0){
                            nbr_tueur=nbr_tueur-1;
                            nbr_normal=nbr_normal-1;
                            tmp30 = new TueurNormal(1+rngY,x+rngX,carte);
                            participants.add(tmp30);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp30);
                            i = i+1;                            
                        }
                        if(rng_carac_perso%2 == 1 && nbr_piegeur>0){                            
                            nbr_tueur=nbr_tueur-1;
                            nbr_piegeur=nbr_piegeur-1;                            
                            tmp31 = new TueurPiegeur(1+rngY,x+rngX,carte);
                            participants.add(tmp31);
                            carte_terrain[1+rngY][x+rngX].setPerso(tmp31);
                            i = i+1;                            
                        }                  
                    }
                }
            }
            //Check les exceptions
            if (nbr_pacifiste > 0 && nbr_normal + nbr_soigneur == 0){
                throw new InitialisationPersonnageException("Vous avez mis trop de pacifistes");
            }
            if (nbr_tueur > 0 && nbr_normal + nbr_piegeur == 0){
                throw new InitialisationPersonnageException("Vous avez mis trop de tueurs");
            }
        }
    }
    
    /**
     * Methode permettant de faire une pause, 
     * la suite du programme reprends lorsque l'utilisateur appuiera sur une touche dans la console
     */
    public void pause(){
        System.out.println("Appuyez sur Enter pour continuer");
        Scanner scanner = new Scanner(System.in); //Pour les inputs entre tours de jeux
        scanner.nextLine();
    }
    
    /**
     * Méthode qui créé une zone rouge sur la carte afin de forcer les
     * participants a se regrouper vers le milieu de la carte
     * a chaque fois que la metode est appelé la zone rouge augmente de taille
     */
    public void determine_zone_rouge(){       
       Terrain[][] carte_terrain = carteTerrain.getCarte_Terrain();       
       int grand = max(Constant.LARGEUR, Constant.LONGUEUR);
       int petit = min(Constant.LARGEUR, Constant.LONGUEUR); 
       int param =3*grand/4+4;       
       int param2 =grand/4-4;
       int x = param2;       
       //bloc qui determine si oui ou non une case est en zone rouge ou non
       //puis si la condition est verifiée on appelle notre methode de restriction
       //qui defini cettte case comme etant en zone rouge
       while (x<param){
                if (x<Constant.LONGUEUR && x>=0 && petit/2-grand/2+zone>0 && petit/2-grand/2+zone<Constant.LONGUEUR){
                    carteTerrain.restreindre(petit/2-grand/2+zone, x);
                    carteTerrain.restreindre(0, x);
                }
                if(x<Constant.LONGUEUR && x>=0 && petit/2+grand/2-zone>=0 && petit/2+grand/2-zone<Constant.LARGEUR){
                    carteTerrain.restreindre(petit/2+grand/2-zone, x);
                    
                }
                if(x-param2<Constant.LARGEUR && x-param2>=0 && zone>=0&& zone<Constant.LONGUEUR ){
                    carteTerrain.restreindre( x-param2,zone); 
                    carteTerrain.restreindre(x-param2, 0);
                }
                if(x-param2<Constant.LARGEUR && x-param2>=0 && zone>=0 && zone<Constant.LONGUEUR){
                    carteTerrain.restreindre(x-param2,grand-zone);
                }
                
                x++;
            }
    }
    
    /**
     * 
     * @param a
     * @param b
     * @return la valeur max entre a et b
     */
   private int max(int a, int b){
       if (a<b){
           return b;
       }else{
           return a;
       }
   }
   
   /**
    * 
    * @param a
    * @param b
    * @return  renvoi la valeur minimal entre a et b
    */
   private int min(int a, int b){
       if (a<b){
           return a;
       }else{
           return b;
       }
   }   
 
   /**
    * fonctions permettant d'interrompre l'avancée de la zone rouge
    * @return le nombre de case ou la zone rouge n'est pas active 
    */
   private int stop(){
       int nbr=0;
       Terrain[][] carte_terrain = carteTerrain.getCarte_Terrain();
       for (int m=0; m<Constant.LARGEUR; m++){
            for (int n=0; n<Constant.LONGUEUR; n++){
                if(carte_terrain[m][n].isDangerImminant() == false){
                    nbr ++;
                }
            }
       }
       return nbr;
    }
}