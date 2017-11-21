package Classes;


import BattleRoyale.Constant;
import Carte.Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */

public class Personnage {
    private final String name;//Nom du Personnage
    private final int PVMAX;  //Constante contenant les PV maximum du personnages, au début de la partie le personnage possède le maximum
    private int pv;           //Stat de pv actuels du personnage
    private final int force;  //Stat de force du personnage influant sur les dégat qu'il cause
    private int critique;     //Pourcentage d'infliger un coup critique
    private int deplacement;  //Nombre de cases maximum que peut parcourir un personnage durant la phase déplacement
    private int position_x;   //Position x sur la carte (Horizontale)
    private int position_y;   //Position y sur la carte (Verticale)
    //private Arme arme;      //Arme que possède le personnage (octroie un bonus de portée et/ou de force)
    private Team team;

    /**
     * Constucteur de Personnage, 
     * il n'initialise pas PVMAX, pv, force, deplacement car ces caractéristiques sont initialisées dans les classes filles
     * il initialise le nom qui sera choisit aléatoirement dans une liste configurable grace à name.txt
     *               la team du personnage n'appartient à aucune team donc elle est initialisée à null
     * @param position_x Position x du personnage sur la carte générée pour le Battle Royale
     * @param position_y Position y du personnage sur la carte générée pour le Battle Royale
     */
    public Personnage( int position_x, int position_y) {
        String[] tabNom = Constant.TABLEAUNOM;
        this.name = tabNom[(int)(Math.random()*(tabNom.length))];
        this.pv = 5 + (int)(Math.random()*(10-5));
        this.PVMAX = this.pv;
        this.force = 1 + (int)(Math.random()*(10-5)); //Force à définir sur les différences classe plus tard
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
     * Getter de la variable critique
     * @return
     */
    public int getCritique() {
        return critique;
    }

    /**
     * Getter de la variable position horizontale
     * @return
     */
    public int getPosition_x() {
        return position_x;
    }

    /**
     * Getter de la variable position verticale
     * @return
     */
    public int getPosition_y() {
        return position_y;
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
     * Setter pour la variable position_x
     * @param position_x
     */
    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    /**
     * Setter pour la variable position_y
     * @param position_y
     */
    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }
    
    /**
     * Setter pour la variable critique
     * @param critique
     */
    public void setCritique(int critique) {
        this.critique = critique;
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
        return "Fiche Personnage de " + name +".\n"
                + "PV : " + pv +"\n"
                + "FORCE : "+ force+"\n"
                + "DEPLACEMENT : " + deplacement+"\n"
                + "Armes : à implémenter" + "\n"
                + "Team : à implémenter";
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
    public void attaquer(Personnage cible){
        //Notion de coup critique
        if((int)(Math.random()*100)<this.getCritique()){
            this.parler("COUP CRITIQUE");
            cible.enquaisser(this.force * 2);
        }
        else{
            //Augmenter le ratio 
            cible.enquaisser(this.force);
            
            
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
        }
        else{
            this.setPv(this.getPv()-dmg);
            this.parler("Aie");
            //Augmenter ratio critique
        }
    }
    
    /**
     * Unique action effectuable durant la phase de déplacement, se situe jsute avant la phase d'action.
     * @param carte La carte utilisée pour la partie
     */
    public void deplacement(Carte carte){
        
    }   
}
