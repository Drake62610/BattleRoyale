/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Trouillard;

import Carte.Carte;
import Carte.Foret;
import Carte.Terrain;
import Classes.Soigneur;

public class TrouillardSoigneur extends Soigneur implements Trouillard {

    private boolean hidden = false;
    
    public TrouillardSoigneur(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(5-1)) //PV
                ,0                          //Force
                ,2                        //Deplacement
                ,4                        //Vitesse
                ,position_x, position_y,carte);
        hidden = false;
    }

    @Override
    public boolean isHidden() {
        return hidden;
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
    
    /**
     * Le trouillard cherche à se cacher dans les forêts et fuis sans réfléchir dès qu'une personne se trouve à coté de lui
     */
    @Override
    public void choixDeplacement() { //Trouver solution pour avoir un mouvement cohérant (2 deplacements)
        int x = this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        //On regarde si le personnage est en danger
        if (carte[x+1][y].isDangerImminant()){
            if(carte[x-1][y].accessible(this)){this.moveNorth();}
            else if(carte[x][y+1].accessible(this)){this.moveEast();}
            else if(carte[x][y-1].accessible(this)){this.moveWest();}
        }
        else if (carte[x-1][y].isDangerImminant()){
            if(carte[x+1][y].accessible(this)){this.moveSouth();}
            else if(carte[x][y+1].accessible(this)){this.moveEast();}
            else if(carte[x][y-1].accessible(this)){this.moveWest();}
        }
        else if (carte[x][y+1].isDangerImminant()){
            if(carte[x][y-1].accessible(this)){this.moveWest();}
            else if(carte[x-1][y].accessible(this)){this.moveNorth();}
            else if(carte[x+1][y].accessible(this)){this.moveSouth();}
        }
        else if (carte[x+1][y-1].isDangerImminant()){
            if(carte[x][y+1].accessible(this)){this.moveEast();}
            else if(carte[x-1][y].accessible(this)){this.moveNorth();}
            else if(carte[x+1][y].accessible(this)){this.moveSouth();}
        }
        else if (carte[x][y].isDangerImminant()){
            if(!(carte[x+1][y].isDangerImminant()) && carte[x+1][y].accessible(this)){this.moveSouth();}
            else if(!(carte[x-1][y].isDangerImminant()) && carte[x-1][y].accessible(this)){this.moveNorth();}
            else if(!(carte[x][y+1].isDangerImminant()) && carte[x][y+1].accessible(this)){this.moveEast();}
            else if(!(carte[x][y-1].isDangerImminant()) && carte[x][y-1].accessible(this)){this.moveWest();}
            else{this.dontMove();}
        }
        else{
            if(carte[x][y].getPerso() != null && !carte[x][y].getPerso().equals(this)){ //Si quelqun est dans sa forêt alors qu'il est caché
                this.dontMove(); //Il ne bouge pas et reste caché
            }
            else if(carte[x][y] instanceof Foret && carte[x+1][y].getPerso()==null && carte[x-1][y].getPerso()==null && carte[x][y+1].getPerso()==null && carte[x][y-1].getPerso()==null){
                this.dontMove(); //Si il y a personne autour alors il reste dans la Forêt pour se cacher
            }
            else{
                //Sinon priorités aux forêts vides aux alentours
                if (carte[x+1][y] instanceof Foret && carte[x+1][y].accessible(this)){
                    hidden = false;
                    this.moveSouth();
                }
                else if (carte[x-1][y] instanceof Foret && carte[x-1][y].accessible(this)){
                    hidden = false;
                    this.moveNorth();
                } 
                else if (carte[x][y+1] instanceof Foret && carte[x][y+1].accessible(this)){
                    hidden = false;
                    this.moveEast();
                }
                else if (carte[x][y-1] instanceof Foret && carte[x][y-1].accessible(this)){
                    hidden = false;
                    this.moveWest();
                }
                //Sinon si il y a quelqu'un il fuit à l'opposé
                else{
                    if(carte[x+1][y].getPerso() != null && carte[x-1][y].accessible(this)){
                        hidden = false;
                        this.moveNorth();
                    }
                    else if(carte[x-1][y].getPerso() != null && carte[x+1][y].accessible(this)){
                        hidden = false;
                        this.moveSouth();
                    }
                    else if(carte[x][y+1].getPerso() != null && carte[x][y-1].accessible(this)){
                        hidden = false;
                        this.moveWest();
                    }
                    else if(carte[x][y-1].getPerso() != null && carte[x][y+1].accessible(this)){
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
