package Classes;

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
        if(leader.equals(cible)){ //Si on enlève le leader alors on en élit un nouveau
            leader = membres.get(0);
        }
        if(membres.size() == 1){ //Une team de 1 ça n'existe pas !
            leader.setTeam(null);
        }
    }
    
    /**
     * Ajoute un nouveau membre à la Team
     * @param cible 
     */
    public void addMember(Personnage cible){
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
    
    public void jouer(){
        //Phase deplacement
        leader.choixDeplacement();
        for(int i=0;i<membres.size();i++){ //Tout le monde suit le Leader
                membres.get(i).setPosition_x(leader.getPosition_x());
                membres.get(i).setPosition_y(leader.getPosition_y());
                //Et jouent si ils sont soigneurs
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
                }
            }
        leader.getCarte().getCarte_Terrain()[leader.getPosition_x()][leader.getPosition_y()].setPerso(this);
        
        leader.phaseAction();
        //SI il y a des soigneurs dans les non actifs ils soignent
    }
    
    public void enquaisser(int dmg){
        leader.enquaisser(dmg);
    }

    
}  
