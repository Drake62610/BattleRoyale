package Classes;


import BattleRoyale.Constant;
import Carte.Carte;
import Carte.Mer;
import Classes.Trouillard.TrouillardPiegeur;
import Exception.WalkOnWaterException;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */

public class Personnage {
    /**
     * Nom du Personnage
     */
    private final String name;
    /**
     * Constante contenant les PV maximum du personnages, au début de la partie le personnage possède le maximum
     */
    private final int PVMAX;  
    /**
     * Stat de pv actuels du personnage
     */
    private int pv;           
    /**
     * Stat de force du personnage influant sur les dégat qu'il cause
     */
    private final int force;  
    /**
     * Stat de vitesse qui gère l'ordre des tours
     */
    private int vitesse;    
    /**
     * Nombre de cases maximum que peut parcourir un personnage durant la phase déplacement
     */
    private int deplacement;  
    /**
     * Carte sur laquelle il se trouve, utilisé pour reflechir et choisir une action
     */
    private Carte carte;
    /**
     * Position x sur la carte (Horizontale)
     */
    private int position_y;   
    /**
     * Position y sur la carte (Verticale)
     */
    private int position_x;   
    /**
     * Team dans laquelle il appartient
     */
    private Team team;

    /**
     * Constucteur de Personnage, 
     * il n'initialise pas PVMAX, pv, force, deplacement car ces caractéristiques sont initialisées dans les classes filles
     * il initialise le nom qui sera choisit aléatoirement dans une liste configurable grâce à name.txt (cf Constant.java)
     *               la team du personnage n'appartient à aucune team donc elle est initialisée à null
     * @param pv
     * @param force
     * @param deplacement
     * @param vitesse
     * @param position_x Position x du personnage sur la carte générée pour le Battle Royale
     * @param position_y Position y du personnage sur la carte générée pour le Battle Royale
     * @param carte
     */
    public Personnage(int pv, int force, int deplacement, int vitesse, int position_y, int position_x, Carte carte) {
        String[] tabNom = Constant.TABLEAUNOM;
        this.name = tabNom[(int)(Math.random()*(tabNom.length))];
        this.pv = pv;
        this.PVMAX = this.pv;
        this.force = force; 
        this.vitesse = vitesse;
        this.deplacement = deplacement;
        this.carte = carte;
        this.position_x = position_x;
        this.position_y = position_y;
        this.team = null;
    }
    
    //GETTER
    /**
     * Getter de la variable name
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * Getter de la variable pv
     * @return
     */
    public int getPv() {
        return pv;
    }
    /**
     * Getter de la variable PVMAX
     * @return
     */
    public int getPVMAX() {
        return PVMAX;
    }
    /**
     * Getter de la variable force
     * @return
     */
    public int getForce() {
        return force;
    }
    /**
     * Getter de la variable vitesse
     * @return 
     */
    public int getVitesse() {
        return vitesse;
    }
    /**
     * Getter de la variable Terrain
     * @return 
     */
    public Carte getCarte() {
        return carte;
    }    
    /**
     * Getter de la variable position horizontale
     * @return
     */
    public int getPosition_x() {
        return position_y;
    }
    /**
     * Getter de la variable position verticale
     * @return
     */
    public int getPosition_y() {
        return position_x;
    }   
    /**
     * Getter de la variable team
     * @return
     */
    public Team getTeam() {
        return team;
    }

    //SETTER
    /**
     * Setter pour la variable pv
     * @param pv
     */
    public void setPv(int pv) {
        this.pv = pv;
    } 
    /**
     * Getter de la variable vitesse
     * @param vitesse 
     */
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
    /**
     * Getter de la variable position_x
     * @param position_x 
     */
    public void setPosition_x(int position_x) {
        this.position_y = position_x;
    }
    /**
     * Setter pour la variable position_y
     * @param position_y
     */
    public void setPosition_y(int position_y) {
        this.position_x = position_y;
    }   
    /**
     * Setter pour la variable team
     * @param team
     */
    public void setTeam(Team team) {
        this.team = team;
    }
    
    //METHODS
    /**
     * Override de la methode toString de la classe Object
     * Présente les principales Stats
     * @return 
     */
    @Override
    public String toString() {
        String strTeam = new String();
        if(this.team== null){strTeam="Aucune";}else{strTeam=team.toString();}
        return "Fiche Personnage de " + name +".\n"
                + "PV : " + pv +"\n"
                + "FORCE : "+ force+"\n"
                + "DEPLACEMENT : " + deplacement+"\n"
                + "Armes : à implémenter" + "\n"
                + "Team : " + strTeam;
    }
    
    /**
     * Affiche un log pour faire parler le personnage
     * ex: "Nom du personnage dit : message"
     * @param text texte parlé par le personnage
     */
    public void parler(String text){
        System.out.println(name + " dit : " + text);
    }
    

    /**
     * Action de base que tout les personnage possède permettant d'infiger des dégats à une cible
     * @param cible de l'attaque, peut être un personnage ou une Team
     */
    public void attaquer(Object cible){
        int dmg = force;
        if (cible instanceof Personnage){ //Cas d'attaque sur une personne
            this.parler("Aya !");         
            ((Personnage) cible).encaisser(dmg); 
        }
        else if (cible instanceof Team){ //Cas d'attaque sur une Team
            this.parler("Vous ne me faites pas peur avec votre team, prennez ça ! Aya !");
            ((Team)cible).enquaisser(dmg);
        }
        else{
            throw new UnsupportedOperationException(name + "essaie d'attaquer un Alien ?");
        }        
    }
    
    /**
     * Methode invoquer lorque le personnage reçoit des dégats, met à jour les PV et gère la mort
     * @param dmg dommage brut pris par le personnage
     */
    public void encaisser(int dmg){
        if(this.getPv()<=dmg){ //Cas de mort
            this.setPv(0);
            this.parler("Monde de merde ! x|"); //Mais qu'est ce que ça peut bien vouloir dire ?
            System.out.println("Oh non "+ name + " est mort !");
            if(this.team != null){ //Si il a une Team alors il faut le retirer de la team (ce qui va aussi l'enlever de la carte)
                team.removeMember(this);
            }
            else{ //On l'enlève mannuellement de la carte
                carte.getCarte_Terrain()[position_y][position_x].setPerso(null);
            }
        }
        else{
            this.setPv(this.getPv()-dmg); //On enlève les pv
            this.parler("Aie");
        }
    }
    
    /**
     * Un tour de jeu pour un personnage:
     * PHASE DEPLACEMENT : il se déplace en fonction de sa stat "deplacement"
     * PHASE ACTION : Il agit en fonction des capacitées qu'il a ->Capacitée de Classe (Soigneur ou Piégeur)"
     *                                                           ->Capacitée de Personnalitée (Traitre,Trouillard,Tueur,etc...)
     */
    public void jouer(){ 
        if (team == null){ //Si il n'as pas de Team alors il joue seul
            System.out.println("***************");
            System.out.println("Tour de " + name); 
            System.out.println("");
            System.out.println("Phase de déplacement");
            this.phaseDeplacement(); //Methode qui overire pour chaque classe et personnalité pour implémenté une IA en dur
            System.out.println("");
            System.out.println("Phase d'action");
            this.phaseAction(); //Methode qui overire pour chaque classe et personnalité pour implémenté une IA en dur
        }
        else{ //Sinon on appelle la méthode team.jouer() uniquement si c'est le leader qui joue. Le tour d'une team peut être sauté si le leader meurt avant de jouer.
            if (team.getLeader() == this){
                team.jouer();
            }
        }
    }
    
    /**
     * Methode à Overide, ne contient rien de base
     */
    public void choixDeplacement(){}
    /**
     * Méthode pour un Personnage pour se déplacer auSud
     * @throws WalkOnWaterException 
     */
    public void moveSouth() throws WalkOnWaterException{
        System.out.println(name + " se dirige vers le Sud");
        if (this.carte.getCarte_Terrain()[this.position_y+1][this.position_x] instanceof Mer){
            throw new WalkOnWaterException(); //On vérifie sur le personage ne s'apprète pas à marcher sur de l'eau
        }
        else{
            this.carte.getCarte_Terrain()[this.position_y][this.position_x].setPerso(null); //UN déplacement consite à changer la variable perso de la classe Terrain
            this.carte.getCarte_Terrain()[this.position_y+1][this.position_x].setPerso(this);
            position_y++;
        }
    }
    /**
     * Méthode pour un Personnage pour se déplacer au Nord
     * @throws WalkOnWaterException 
     */
    public void moveNorth() throws WalkOnWaterException{
        System.out.println(name + " se dirige vers le Nord");
        if (this.carte.getCarte_Terrain()[this.position_y-1][this.position_x] instanceof Mer){
            throw new WalkOnWaterException();
        }
        else{
            this.carte.getCarte_Terrain()[this.position_y][this.position_x].setPerso(null);
            this.carte.getCarte_Terrain()[this.position_y-1][this.position_x].setPerso(this);
            position_y--;
        }
    }
    /**
     * Méthode pour un Personnage pour se déplacer à l'Ouest
     * @throws WalkOnWaterException 
     */
    public void moveWest() throws WalkOnWaterException{
        System.out.println(name + " se dirige vers l'Ouest");
        if (this.carte.getCarte_Terrain()[this.position_y][this.position_x-1] instanceof Mer){
            throw new WalkOnWaterException();
        }
        else{
            this.carte.getCarte_Terrain()[this.position_y][this.position_x].setPerso(null);
            this.carte.getCarte_Terrain()[this.position_y][this.position_x-1].setPerso(this);
            position_x--;
        }
    }
    /**
     * Méthode pour un Personnage pour se déplacer à l'Est
     * @throws WalkOnWaterException 
     */
    public void moveEast() throws WalkOnWaterException{
        System.out.println(name + " se dirige vers l'Est");
        if (this.carte.getCarte_Terrain()[this.position_y][this.position_x+1] instanceof Mer){
            throw new WalkOnWaterException();
        }
        else{
            this.carte.getCarte_Terrain()[this.position_y][this.position_x].setPerso(null);
            this.carte.getCarte_Terrain()[this.position_y][this.position_x+1].setPerso(this);
            position_x++;
        }
    }
    /**
     * Méthode inutile mais pratique pour avoir de la transparance dans l'écrire de l'IA
     */
    public void dontMove(){System.out.println(name + " ne se déplace pas");;}
    /**
     * Méthode pour un Personnage pour se déplacer une fois de manière aléatoire
     * @throws WalkOnWaterException 
     */
    public void moveRandom() throws WalkOnWaterException{
        int rdm = (int)(Math.random()*(6)); //On tire un chiffre aléatoire entre 0 et 5
        if (rdm == 0 && carte.getCarte_Terrain()[position_y-1][position_x].accessible(this)){ 
            moveNorth();
        }
        else if (rdm == 1 && carte.getCarte_Terrain()[position_y+1][position_x].accessible(this)){
            moveSouth();            
        }
        else if (rdm == 2 && carte.getCarte_Terrain()[position_y][position_x+1].accessible(this)){
            moveEast();
        }
        else if (rdm == 3 && carte.getCarte_Terrain()[position_y][position_x-1].accessible(this)){
            moveWest();
        }
        else if (rdm == 4){
            dontMove();
        }
        else{ //Dans le cas ooù le chiffre tiré correspond à une case inacessible pour le personnage
              //Un personnage auras toujours un mouvement possible puisque il y a le choix dontMove dans cette fonction
            moveRandom();
        }
    }
    
    /**
     * Methode qui effectue autant de deplacement qu'indique la variable deplacement
     */
    public void phaseDeplacement(){
        for (int i=0;i<deplacement;i++){
            this.choixDeplacement();
            if (carte.getCarte_Terrain()[position_y][position_x].isPiege() && !(this instanceof TrouillardPiegeur)){ //Si le personnage marche dans un pière
                int dmg = (int)(1 + Math.random()*(4-1));   //On tire un chiffre entre 1 et 3 pour définir les dommage que le piège inflise                                                         //Et n'est pas un TrouillardPiegeur (avantage de Personnalitée)
                this.encaisser(dmg);   //Le personnage encaisse les dégats du piège
                System.out.println(name + " marche dans un piège et perd "+ dmg +".");
                carte.getCarte_Terrain()[position_y][position_x].setPiege(false); //Le piège ayant été activée il devient innutilisable de nouveau
            }
        }
    }
    
    /**
     * Methode à overide pour implementer les différents comportements
     */
    public void phaseAction(){}

}
