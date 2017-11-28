package Classes;


import BattleRoyale.Constant;
import Carte.Carte;
import Carte.Mer;

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
     * Arme que possède le personnage (octroie un bonus de portée et/ou de force)
     */
    //private Arme arme;   
    /**
     * Team dans laquelle il appartient
     */
    private Team team;

    /**
     * Constucteur de Personnage, 
     * il n'initialise pas PVMAX, pv, force, deplacement car ces caractéristiques sont initialisées dans les classes filles
     * il initialise le nom qui sera choisit aléatoirement dans une liste configurable grace à name.txt
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
     * Setter pour la variable position_y
     * @param position_x
     */
    
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setPosition_x(int position_x) {
        this.position_y = position_x;
    }
    /**
     * Setter pour la variable position_x
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
     * Overide de la methode toString de la classe Object
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
     * LE PERSONNAGE POSSEDE 3 ACTIONS EN PLUS DU DEPLACEMENT : 
     *         -Attaquer
     *         -Action spéciale de classe héritée
     *         -Action spéciale de caractéristique paramétrée graçe à l'implémentation d'interface
     */
    /**
     * Action de base que tout les personnage possède permettant d'infiger des dégats à une cible
     * @param cible de l'attaque
     */
    public void attaquer(Object cible){
        int dmg = force;
        if (cible instanceof Personnage){
            this.parler("Aya !");
            ((Personnage) cible).enquaisser(dmg);
        }
        else if (cible instanceof Team){
            this.parler("Vous ne me faites pas peur avec votre team, prennez ça ! Aya !");
            ((Team)cible).enquaisser(dmg);
        }
        else{
            throw new UnsupportedOperationException("Il y a un Alien sur le Terrain");
        }        
    }
    
    /**
     * Methode invoquer lorque le personnage reçoit des dégat, met à jour les PV et gère la mort
     * @param dmg dommage brut pris par le personnage
     */
    public void enquaisser(int dmg){
        if(this.getPv()<dmg){
            this.setPv(0);
            this.parler("Monde de merde ! x|");
            if(this.team != null){
                team.removeMember(this);
            }
            else{
                carte.getCarte_Terrain()[position_y][position_x].setPerso(null);
            }
            
            
            
        }
        else{
            this.setPv(this.getPv()-dmg);
            this.parler("Aie");
            //Augmenter ratio critique
        }
    }
    
    public void jouer(){
        if (team == null){
            System.out.println("***************");
            System.out.println("Tour de " + name);
            System.out.println("Phase de déplacement");
            this.phaseDeplacement();
            System.out.println("Phase d'action");
            this.phaseAction();
        }
        else{
            if (team.getLeader() == this){
                team.jouer();
            }
        }
    }
    
    public void choixDeplacement(){}
    public void moveSouth(){
        System.out.println("South");
        if (this.carte.getCarte_Terrain()[this.position_y+1][this.position_x] instanceof Mer){
            throw new UnsupportedOperationException("Un personnage essaie de marcher sur l'eau !");
        }
        else{
            this.carte.getCarte_Terrain()[this.position_y][this.position_x].setPerso(null);
            this.carte.getCarte_Terrain()[this.position_y+1][this.position_x].setPerso(this);
            position_y++;
        }
    }
    public void moveNorth(){
        System.out.println("North");
        if (this.carte.getCarte_Terrain()[this.position_y-1][this.position_x] instanceof Mer){
            throw new UnsupportedOperationException("Un personnage essaie de marcher sur l'eau !");
        }
        else{
            this.carte.getCarte_Terrain()[this.position_y][this.position_x].setPerso(null);
            this.carte.getCarte_Terrain()[this.position_y-1][this.position_x].setPerso(this);
            position_y--;
        }
    }
    public void moveWest(){
        System.out.println("West");
        if (this.carte.getCarte_Terrain()[this.position_y][this.position_x-1] instanceof Mer){
            throw new UnsupportedOperationException("Un personnage essaie de marcher sur l'eau !");
        }
        else{
            this.carte.getCarte_Terrain()[this.position_y][this.position_x].setPerso(null);
            this.carte.getCarte_Terrain()[this.position_y][this.position_x-1].setPerso(this);
            position_x--;
        }
    }
    public void moveEast(){
        System.out.println("East");
        if (this.carte.getCarte_Terrain()[this.position_y][this.position_x+1] instanceof Mer){
            throw new UnsupportedOperationException("Un personnage essaie de marcher sur l'eau !");
        }
        else{
            this.carte.getCarte_Terrain()[this.position_y][this.position_x].setPerso(null);
            this.carte.getCarte_Terrain()[this.position_y][this.position_x+1].setPerso(this);
            position_x++;
        }
    }
    public void dontMove(){System.out.println("Nothing");}
    public void moveRandom(){
        int rdm = (int)(Math.random()*(6));
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
        else{
            moveRandom();
        }
    }
    
    /**
     * appel autant de deplacement qu'indique la variable deplacement
     */
    public void phaseDeplacement(){
        for (int i=0;i<deplacement;i++){
            this.choixDeplacement();
            if (carte.getCarte_Terrain()[position_y][position_x].isPiege()){
                int dmg = (int)(1 + Math.random()*(3-1));
                this.pv = this.pv -dmg;
                System.out.println(name + " marche dans un piège et perd "+ dmg +".");
                carte.getCarte_Terrain()[position_y][position_x].setPiege(false);
            }
        }
    }
    /**
     * Methode à overide pour implementer les différents comportements
     */
    public void phaseAction(){}

}
