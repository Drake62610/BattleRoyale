package Classes;

import Classes.Traitre.Traitre;
import java.util.ArrayList;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Team {
    /**
     * Liste des membres de la Team
     */
    private ArrayList <Personnage> membres = new ArrayList <>();  
    /**
     * Leader de la Team qui agit pour les autres
     */
    private Personnage leader;                                             
    /**
     * CONSTRUCTEUR de la classe Team, il faut assigner un leader qui aura l'avantage d'agir et de diriger pour les autres
     * La creation d'une Team se fait uniquement lorsque un Pacifiste réussi à inviter quelqun
     * A sa creation la Team n'est composée que du leader mais vite après la méthode addMember est appelée sur l'autre personne
     * @param leader
     */
    public Team(Personnage leader) {
        this.leader = leader; //Definistion du leader
        membres.add(leader); //Ajout du leader aux membres
        leader.setTeam(this); //Update de la fiche perso de la team du leader
        leader.getCarte().getCarte_Terrain()[leader.getPosition_x()][leader.getPosition_y()].setPerso(this); //On place la Team sur la carte
    }
    
    //GETTER
    /**
     * Getter de la variable leader
     * @return 
     */
    public Personnage getLeader() {
        return leader;
    }
   
    /**
     * Getter de la variable Membres
     * @return 
     */
    public ArrayList<Personnage> getMembres() {
        return membres;
    }
    
    //METHODS
    /**
     * Override de la méthode toString de la classe Object
     * Affiche le nom du Leader puis le nom de tout les membres de la Team
     */
    @Override
    public String toString() {
        String str = new String();
        for(int i=0;i<membres.size();i++)  
        {  
            str += membres.get(i).getName();
            str += " , ";
        }  
        return "Leader : "+ leader.getName() +"\n" + "       Membres :" + str;
    }
    
    /**
     * Enlève le personnage ciblé de la Team
     * @param cible
     */
    public void removeMember(Personnage cible){
        membres.remove(this.findIndex(cible));
        cible.setTeam(null);
        if(leader.equals(cible) && membres.size()>1){ //Si on enlève le leader alors on en élit un nouveau
            System.out.println(membres.get(0).getName() + " devient le nouveau leader de la team ! Il remplace donc " + leader.getName());
            leader = membres.get(0);
        }
        else if(leader.equals(cible) && membres.size() == 1){ //Si on enlève le leader et qu'il ne reste que lui alors on détruit la team
            leader.getCarte().getCarte_Terrain()[leader.getPosition_x()][leader.getPosition_y()].setPerso(null);
            
        }
        if(membres.size() == 1){ //Une team de 1 ça n'existe pas !
            leader.setTeam(null);
            leader.getCarte().getCarte_Terrain()[leader.getPosition_x()][leader.getPosition_y()].setPerso(leader);
        }
    }
    
    /**
     * Ajoute un nouveau membre à la Team
     * @param cible 
     */
    public void addMember(Personnage cible){
        System.out.println(cible.getName()+" rejoint la team de "+ leader.getName());
        membres.add(cible); //On ajoute la cible à la liste des membres de la Team
        cible.setTeam(this); //On update la team sur sa fiche perso
        //Update coordonnées
        cible.getCarte().getCarte_Terrain()[cible.getPosition_x()][cible.getPosition_y()].setPerso(null); //Le personnage disparait de la carte
        cible.setPosition_x(leader.getPosition_x()); //Il rejoint la case où se trouve le leader
        cible.setPosition_y(leader.getPosition_y());
    }
    
    /**
     * Trouve l'index d'un objet Personnage dans une arraylist du même type
     * @param cible Personnage dont il faut trouver l'id
     * Methode utilisée lors de la supression d'un membre
     * @return
     */
    public int findIndex(Personnage cible){
         int i = 0;  
         for(i=0;i<membres.size();i++)  
         {  
            if(membres.get(i).equals(cible))  //La methdoe equals de la classe object compare les références
            {  
               return i;
            }  
         }  
         return -1; //On retourne -1 si ça ne marche pas et que la personne n'existe pas
    }
    
    /**
     * Methode qui gerre le tour de jeu d'une Team
     */
    public void jouer(){
        //Phase deplacement de la Team : le leader se déplace pour tout le monde
        leader.choixDeplacement();
        leader.getCarte().getCarte_Terrain()[leader.getPosition_x()][leader.getPosition_y()].setPerso(this);
        for(int i=0;i<membres.size();i++){ //Tout le monde suit le Leader
                membres.get(i).setPosition_x(leader.getPosition_x());
                membres.get(i).setPosition_y(leader.getPosition_y());
                //Et les membres jouent leurs action directement si ils sont soigneurs
                if (membres.get(i) instanceof Soigneur){
                    for(int j=1;j<membres.size();j++){ //Le leader ne joue pas tout de suite
                        if (membres.get(j).getPVMAX() - membres.get(j).getPv()==0 && i!=j){
                            ((Soigneur)membres.get(i)).soigner(membres.get(i));
                            break;
                        }
                        else if(membres.get(j).getPVMAX() - membres.get(j).getPv()==0 && i!=j){
                            ((Soigneur)membres.get(i)).soigner();
                        }
                    }
                }//Les traitre en profite pour eventuellement passer à l'action...
                if (membres.get(i) instanceof Traitre){
                    ((Traitre)membres.get(i)).trahir();
                }
            }
        //Phase action du leader après ses membres
        leader.phaseAction();
    }
    
    /**
     * Dans une team c'est le leader qui prend tout les dégats
     * @param dmg 
     */
    public void enquaisser(int dmg){
        leader.enquaisser(dmg);
    } 
}  
