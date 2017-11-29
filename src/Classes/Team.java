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
    private ArrayList <Personnage> membres = new ArrayList <Personnage>();  
    /**
     * Leader de la Team qui agit pour les autres
     */
    private Personnage leader;                                             

    /**
     * CONSTRUCTEUR de la classe Team, il faut assigner un leader qui aura l'avantage d'agir et de dirriger pour les autres
     * @param leader
     */
    public Team(Personnage leader) {
        this.leader = leader;
        membres.add(leader);
        leader.setTeam(this);
        leader.getCarte().getCarte_Terrain()[leader.getPosition_x()][leader.getPosition_y()].setPerso(this);
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
    @Override
    public String toString() {
        String str = new String();
        for(int i=0;i<membres.size();i++)  
        {  
            str += membres.get(i).getName();
            str+= " , ";
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
        membres.add(cible);
        cible.setTeam(this);
        //Update coordonnées
        cible.getCarte().getCarte_Terrain()[cible.getPosition_x()][cible.getPosition_y()].setPerso(null);
        cible.setPosition_x(leader.getPosition_x());
        cible.setPosition_y(leader.getPosition_y());
    }
    
    /**
     * Trouve l'index d'un objet Personnage dans une arraylist du même type
     * @param cible Personnage dont il faut trouver l'id
     * @return
     */
    public int findIndex(Personnage cible){
         int i = 0;  
         for(i=0;i<membres.size();i++)  
         {  
            if(membres.get(i).equals(cible))  
            {  
               return i;
            }  
         }  
         return -1;
    }
    
    /**
     * Methode qui gerre le tour de jeu d'une Team
     */
    public void jouer(){
        //Phase deplacement
        leader.choixDeplacement();
        leader.getCarte().getCarte_Terrain()[leader.getPosition_x()][leader.getPosition_y()].setPerso(this);
        for(int i=0;i<membres.size();i++){ //Tout le monde suit le Leader
                membres.get(i).setPosition_x(leader.getPosition_x());
                membres.get(i).setPosition_y(leader.getPosition_y());
                //Et jouent leurs action directement si ils sont soigneurs
                if (membres.get(i) instanceof Soigneur){
                    for(int j=0;j<membres.size();j++){
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
        
        
        //Phase action du leader
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
