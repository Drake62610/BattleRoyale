/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Trouillard;

import Carte.Carte;
import Carte.Foret;
import Carte.Terrain;
import Classes.Personnage;

/**
 *
 * @author ISEN
 */
public class TrouillardNormal extends Personnage implements Trouillard {

    private boolean hidden;

    public TrouillardNormal(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(5-1)) //PV
                ,0                          //Force
                ,2                        //Deplacement
                ,4                        //Vitesse
                ,position_x, position_y,carte);
        hidden = false;
    }
    /**
     * Pour l'instant il ne peut se cacher que dans la Forêt
     * @param carte 
     */
    @Override
    public void seCamoufler(Carte carte) {
        Terrain terrainActuel = carte.getCarte_Terrain()[this.getPosition_x()][this.getPosition_y()];
        if(terrainActuel instanceof Foret && ((Foret)terrainActuel).seCacher()){
           hidden = true;            
        }
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Le trouillard cherche à se cacher dans les forêts et fuis sans réfléchir dès qu'une personne se trouve à coté de lui
     */
    @Override
    public void choixDeplacement() { //Trouver solution pour avoir un mouvement cohérant (2 deplacements)
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        //Si il y a personne autour alors il reste dans la Forêt pour se cacher
        if(carte[x][y] instanceof Foret && carte[x+1][y].getPerso()==null && carte[x-1][y].getPerso()==null && carte[x][y+1].getPerso()==null && carte[x][y-1].getPerso()==null){
            this.dontMove();
        }
        else{
            //Sinon priorités aux forêts vides aux alentours
            if (carte[x+1][y] instanceof Foret && carte[x+1][y].getPerso()==null){
                hidden = false;
                this.moveSouth();
            }
            else if (carte[x-1][y] instanceof Foret && carte[x-1][y].getPerso()==null){
                hidden = false;
                this.moveNorth();
            } 
            else if (carte[x][y+1] instanceof Foret && carte[x][y+1].getPerso()==null){
                hidden = false;
                this.moveEast();
            }
            else if (carte[x][y-1] instanceof Foret && carte[x][y-1].getPerso()==null){
                hidden = false;
                this.moveWest();
            }
            //Sinon si il y a quelqu'un il fuit à l'opposé
            else{
                if(carte[x+1][y].getPerso() != null && carte[x-1][y].getPerso() == null){
                    hidden = false;
                    this.moveNorth();
                }
                else if(carte[x-1][y].getPerso() != null && carte[x+1][y].getPerso() == null){
                    hidden = false;
                    this.moveSouth();
                }
                else if(carte[x][y+1].getPerso() != null && carte[x][y-1].getPerso() == null){
                    hidden = false;
                    this.moveWest();
                }
                else if(carte[x][y-1].getPerso() != null && carte[x][y+1].getPerso() == null){
                    hidden = false;
                    this.moveEast();
                }
                //Sinon pris de panique il va n'importe où !!
                else{
                    this.moveRandom();
                }
            }    
        }
    }

    @Override
    public void phaseAction() {
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        if(carte[x][y] instanceof Foret){
            this.seCamoufler();
        }
        else{
            this.pleurer();
        }
    }

    private void seCamoufler() {
        if(((Foret)this.getCarte().getCarte_Terrain()[this.getPosition_x()][this.getPosition_y()]).seCacher()){
            hidden = true;
        }
    }

    private void pleurer() {
        this.parler("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOON POURQUOI ??????");
    }
    
}
