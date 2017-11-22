/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Pacifiste;

import Classes.Personnage;
import Classes.Soigneur;
import Classes.Team;

/**
 *
 * @author ISEN
 */
public class PacifisteSoigneur extends Soigneur implements Pacifiste {

    int raison;
    
    public PacifisteSoigneur(int position_x, int position_y) {
        super(position_x, position_y);
    }

    @Override
    public void raisonner(Personnage cible) {
        this.parler("Rejoint moi ! "+ cible.getName()); //Inspirational speech
        
        boolean success = false;
        if((int)(Math.random()*100)<raison){
            success = true;
            //Travailler sur la raison ici
        }
        
        if(success){
            if(this.getTeam() == null){
                this.setTeam(new Team(this));
            }
            else{
                this.getTeam().addMember(cible);
            }
        }
    }
    
}
