package Classes;


import java.util.ArrayList;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Team {
    ArrayList <Personnage> membres = new ArrayList <Personnage>();  //Liste des membre de la Team
    Personnage leader;                                              //Leader de la Team qui agit pour les autres

    public Team(Personnage leader) {
        this.leader = leader;
    }
    
    public void removeMember(Personnage cible){
        membres.remove(this.finIndex(cible));
        cible.setTeam(null);
        if(membres.size() == 1){ //Une team de 1 ça n'existe pas !
            leader.setTeam(null);
        }
    }
    
    public int finIndex(Personnage cible){
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
}  
