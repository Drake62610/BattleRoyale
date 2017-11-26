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
    public int getRaison() {
        return raison;
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
    public void raisonner(Team cible) {
        this.parler("Rejoint moi "+ cible.getLeader().getName() + "toi et ta Team ! Ensemble nous serons plus fort"); //Inspirational speech

        if (this.getTeam() == null){
            cible.getLeader().parler("Bien entendu !");
        }
        else{
            if(cible instanceof Pacifiste && this.raison>((Pacifiste)cible.getLeader()).getRaison()){ //Le nouveau leader est celui avec le plus de raison
                this.getTeam().getMembres().addAll(cible.getMembres());
                cible.getLeader().getCarte().getCarte_Terrain()[cible.getLeader().getPosition_x()][cible.getLeader().getPosition_y()].setPerso(null);
            }
            else{
                cible.getMembres().addAll(this.getTeam().getMembres());
                this.getCarte().getCarte_Terrain()[this.getPosition_x()][this.getPosition_y()].setPerso(null);
            }
        }
    }
    
    @Override
    public void choixDeplacement() {
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        //Si il est déjà au CaC alors il ne bouge pas pour recruter
        if(carte[y+1][x].getPerso() != null || carte[y-1][x].getPerso() != null || carte[y][x+1].getPerso() != null || carte[y][x-1].getPerso() != null){
            this.dontMove();
        }
        //Si x a quelqun d'atteignable en Haut etc
        else if(carte[y-1][x+1].getPerso() != null || carte[y-1][x-1].getPerso() != null || carte[y-2][x].getPerso() != null){
            if(carte[y-1][x].accessible(this)){this.moveNorth();}
            else if(carte[y][x+1].accessible(this)){this.moveEast();}
            else{this.moveWest();}
        }//En bas
        else if(carte[y+1][x+1].getPerso() != null || carte[y+1][x-1].getPerso() != null || carte[y+2][x].getPerso() != null){
            if(carte[y+1][x].accessible(this)){this.moveSouth();}
            else if(carte[y][x+1].accessible(this)){this.moveEast();}
            else{this.moveWest();}
        }//A gauche
        else if(carte[y][x+2].getPerso() != null){
            if(carte[y][x-1].accessible(this)){this.moveEast();}
            else if(carte[y-1][x].accessible(this)){this.moveNorth();}
            else{this.moveSouth();}
        }//A droite
        else if(carte[y][x-2].getPerso() != null){
            if(carte[y][x-1].accessible(this)){this.moveWest();}
            else if(carte[y-1][x].accessible(this)){this.moveNorth();}
            else{this.moveSouth();}
        }
        else{
            this.moveRandom(); //Changer par deplacementrandom (à coder)
        }
    }
}
