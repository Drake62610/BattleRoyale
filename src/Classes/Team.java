package Classes;


import Carte.Mer;
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
    }
    
    /**
     * Trouve l'index d'un objet Personnage dans une arraylist du même type
     * @param cible Personnage dont il faut trouver l'id
     * @return
     */
    public int findIndex(Personnage cible){
         int i = 0;  
         System.out.println("On recherche l'objet " + cible + ":");  
         for(i=0;i<membres.size();i++)  
         {  
            if(membres.get(i).equals(cible))  
            {  
               return i;
            }  
         }  
         return -1;
    }
    
        void deplacementHaut(){
        if (this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()+1][this.getLeader().getPosition_y()] instanceof Mer){
            //throw Exception
        }
        else{
            this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()][this.getLeader().getPosition_y()].setPerso(null);
            this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()+1][this.getLeader().getPosition_y()].setPerso(this);
        }
    }
    void deplacementBas(){
        if (this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()][this.getLeader().getPosition_y()] instanceof Mer){
            //throw Exception
        }
        else{
            this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()][this.getLeader().getPosition_y()].setPerso(null);
            this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()-1][this.getLeader().getPosition_y()].setPerso(this);
        }
    }
    void deplacementGauche(){
        if (this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()][this.getLeader().getPosition_y()-1] instanceof Mer){
            //throw Exception
        }
        else{
            this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()][this.getLeader().getPosition_y()].setPerso(null);
            this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()][this.getLeader().getPosition_y()-1].setPerso(this);
        }
    }
    void deplacementDroite(){
        if (this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()][this.getLeader().getPosition_y()+1] instanceof Mer){
            //throw Exception
        }
        else{
            this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()][this.getLeader().getPosition_y()].setPerso(null);
            this.leader.getCarte().getCarte_Terrain()[this.getLeader().getPosition_x()][this.getLeader().getPosition_y()+1].setPerso(this);
        }
    }
    
    void phaseDeplacement(){
        
    }
    
    void phaseAction(){
        //Le leader fait l'action pour le groupe
        //SI il y a des soigneurs dans els non actifs ils soignent
    }

}  
