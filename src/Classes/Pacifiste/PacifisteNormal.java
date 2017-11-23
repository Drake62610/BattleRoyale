/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Pacifiste;

import Carte.Carte;
import Classes.Personnage;
import Classes.Team;

public class PacifisteNormal extends Personnage implements Pacifiste {

    int raison;
    
    public PacifisteNormal(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(10-5)) //PV
                ,(int)(Math.random()*(3-1))//Force
                ,1                        //Deplacement
                ,2                        //Vitesse
                ,position_x, position_y,carte);
        this.raison = 100; //Un pacifiste normal est en fait un diplomate qui saura ralier la première personne qu'il croise
    }

    @Override
    public void raisonner(Personnage cible) {
        this.parler("Rejoint moi "+ cible.getName() + " !"); //Inspirational speech
        
        boolean success = false;
        if((int)(Math.random()*100)<raison){
            success = true;
            //Travailler sur la raison ici
        }
        
        if(success){
            if(this.getTeam() == null){
                this.setTeam(new Team(this));
                this.getTeam().addMember(cible);
            }
            else{
                this.getTeam().addMember(cible);
            }
        }
    }
    
}
