/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Pacifiste;

import Carte.Carte;
import Carte.Terrain;
import Classes.Personnage;
import Classes.Team;

public class PacifisteNormal extends Personnage implements Pacifiste {

    int raison;
    
    public PacifisteNormal(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(10-5)) //PV
                ,1 + (int)(Math.random()*(3-1))//Force
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
    
        @Override
    public void choixDeplacement() {
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        //Si il est déjà au CaC alors il ne bouge pas pour recruter
        if(carte[x+1][y].getPerso() != null || carte[x-1][y].getPerso() != null || carte[x][y+1].getPerso() != null || carte[x][y-1].getPerso() != null){
            this.deplacementRien();
        }
        //Si y a quelqun d'atteignable à gauche etc
        else if(carte[x-1][y+1].getPerso() != null || carte[x-1][y-1].getPerso() != null || carte[x-2][y].getPerso() != null){
            this.deplacementGauche();
        }
        else if(carte[x+1][y+1].getPerso() != null || carte[x+1][y-1].getPerso() != null || carte[x+2][y].getPerso() != null){
            this.deplacementDroite();
        }
        else if(carte[x][y+2].getPerso() != null){
            this.deplacementHaut();
        }
        else if(carte[x][y-2].getPerso() != null){
            this.deplacementBas();
        }
        else{
            this.deplacementRien(); //Changer par deplacementrandom (à coder)
        }
    }
}
