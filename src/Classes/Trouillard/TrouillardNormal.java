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
import Exception.WalkOnWaterException;

public class TrouillardNormal extends Personnage implements Trouillard {
    /**
     * Indique aux autres personnage si le Trouillard est visible ou non
     */
    private boolean hidden;
    /**
     * Constructeur de la classe Trouillard
     * @param position_x
     * @param position_y
     * @param carte 
     */
    public TrouillardNormal(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(5-1)) //PV
                ,0                         //Force
                ,2                         //Deplacement
                ,4                         //Vitesse
                ,position_x, position_y,carte);
        hidden = false;
    }
    
    /**
     * Getter pour la variable hidden, variable indiquant si un personnage est visible des autres ou non
     * @return 
     */
    @Override
    public boolean isHidden() {
        return hidden;
    }
    
    /**
     * Setter de la variable hidden
     * @param hidden 
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Le trouillard cherche à se cacher dans les forêts et fuis sans réfléchir dès qu'une personne se trouve à coté de lui
     */
    @Override
    public void choixDeplacement() {
        //Recupération des variables utiles
        int x = this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        try{
            //On regarde si le personnage est en danger
            if (carte[x][y].isDangerImminant()){
                if(!(carte[x+1][y].isDangerImminant()) && carte[x+1][y].accessible(this)){this.moveSouth();}
                else if(!(carte[x-1][y].isDangerImminant()) && carte[x-1][y].accessible(this)){this.moveNorth();}
                else if(!(carte[x][y+1].isDangerImminant()) && carte[x][y+1].accessible(this)){this.moveEast();}
                else if(!(carte[x][y-1].isDangerImminant()) && carte[x][y-1].accessible(this)){this.moveWest();}
                else{this.dontMove();}
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
            else if (carte[x+1][y].isDangerImminant()){
                if(carte[x-1][y].accessible(this)){this.moveNorth();}
                else if(carte[x][y+1].accessible(this)){this.moveEast();}
                else if(carte[x][y-1].accessible(this)){this.moveWest();}
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
        catch (WalkOnWaterException ex){
            this.dontMove();
            }
        catch(Exception ex){
            ex.getMessage();
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
    
    @Override
    public void seCamoufler() {
        if(((Foret)this.getCarte().getCarte_Terrain()[this.getPosition_x()][this.getPosition_y()]).seCacher()){
            hidden = true;
            System.out.println("Je vais me cacher dans cette forêt");
        }
        else{
            hidden = false;
            System.out.println("Je ne trouve pas de cachette");
        }
    }

    @Override
    public void pleurer() {
        System.out.println(this.getName() + "craque psychologiquement !");
        this.parler("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOON POURQUOI ??????");
    }
    
}
