/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Pacifiste;

import Carte.Carte;
import Carte.Terrain;
import Classes.Personnage;
import Classes.Soigneur;
import Classes.Team;

/**
 *
 * @author ISEN
 */
public class PacifisteSoigneur extends Soigneur implements Pacifiste {

    int raison;
    
    public PacifisteSoigneur(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(10-5)) //PV
                ,1 + (int)(Math.random()*(3-1))//Force
                ,1                        //Deplacement
                ,2                        //Vitesse
                ,position_x, position_y,carte);
        this.raison = (int)(Math.random()*(90-50)); //Pourcentage de chance maximum : 90, Minimum : 50
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
    
    @Override
    public void choixDeplacement() {
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        //Si il est déjà au CaC alors il ne bouge pas pour recruter
        if(carte[x+1][y].getPerso() != null || carte[x-1][y].getPerso() != null || carte[x][y+1].getPerso() != null || carte[x][y-1].getPerso() != null){
            this.dontMove();
        }
        //Si y a quelqun d'atteignable à gauche etc
        else if(carte[x-1][y+1].getPerso() != null || carte[x-1][y-1].getPerso() != null || carte[x-2][y].getPerso() != null){
            this.moveNorth();
        }
        else if(carte[x+1][y+1].getPerso() != null || carte[x+1][y-1].getPerso() != null || carte[x+2][y].getPerso() != null){
            this.moveSouth();
        }
        else if(carte[x][y+2].getPerso() != null){
            this.moveEast();
        }
        else if(carte[x][y-2].getPerso() != null){
            this.moveWest();
        }
        else{
            this.dontMove(); //Changer par deplacementrandom (à coder)
        }
    }
}
