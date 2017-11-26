/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Traitre;

import BattleRoyale.Constant;
import Carte.Carte;
import Carte.Foret;
import Carte.Terrain;
import Classes.Pacifiste.Pacifiste;
import Classes.Personnage;
import Classes.Soigneur;
import Classes.Team;
import Classes.Trouillard.Trouillard;
import Classes.Tueur.Tueur;
import java.util.ArrayList;

/**
 *
 * @author ISEN
 */
public class TraitreSoigneur extends Soigneur implements Traitre {

    public TraitreSoigneur(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(10-5)) //PV
                ,2 + (int)(Math.random()*(5-1))//Force
                ,1                        //Deplacement
                ,3                        //Vitesse
                ,position_x, position_y,carte);
    }
    
     /**
     * Cette action est un passif et sera executée dès qu'une personne dans la team sera tuable.
     * Ensuite il quitera la team et deplacera 2 cases plus loin.
     * Il n'est pas impossible qu'il rentre à nouveau dans la team puisque sa trahison n'est connue uniquement de la personne morte.

     */
    @Override
    public void trahir() {
        if(this.getTeam()!=null){
            Personnage cible = null;
            ArrayList <Personnage> membres = this.getTeam().getMembres();
            for(int i=0;i<membres.size();i++){  
                if(membres.get(i).getPv()<this.getForce()){ //Un traitre passse à l'action uniquement si il est sur de tuer
                    if(cible == null){ //Si il n'as pas de cible dans la team alors il prends la personne repérée
                        cible = membres.get(i);
                    }
                    else if(membres.get(i) instanceof Tueur){ //Cas où la personne est une Tueur
                        if( cible instanceof Tueur && cible.getPv()<membres.get(i).getPv()){ //Si egualitée de classe on préfère tuer la personne avec le plus de PV
                            cible = membres.get(i);
                        }//Tuer un Tueur est une priorité car il est le plus dangereux
                    }
                    else if(membres.get(i) instanceof Pacifiste){
                        //TODO
                    }
                    else if(membres.get(i) instanceof Trouillard){
                        //TODO
                    }
                }
            }
            if (cible != null){
                this.attaquer(cible);
                //this.deplacement(null);
            }
        }
    }
    
    @Override
    public void choixDeplacement() {
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        //Si il est déjà au CaC alors il ne bouge pas pour recruter
        if(carte[x+1][y].getPerso() instanceof Team || carte[x-1][y].getPerso() instanceof Team || carte[x][y+1].getPerso() instanceof Team || carte[x][y-1].getPerso() instanceof Team){
            this.dontMove();
        }
        //Si y y quelqun d'atteignable en Haut etc
        else if(carte[x-1][y+1].getPerso() instanceof Team || carte[x-1][y-1].getPerso() instanceof Team || (x-2>=0 && carte[x-2][y].getPerso() instanceof Team)){
            if(carte[x-1][y].accessible(this)){this.moveNorth();}
            else if(carte[x][y+1].accessible(this)){this.moveEast();}
            else{this.moveWest();}
        }//En bas
        else if(carte[x+1][y+1].getPerso() instanceof Team || carte[x+1][y-1].getPerso() instanceof Team || (x+2<Constant.LARGEUR && carte[x+2][y].getPerso() instanceof Team)){
            if(carte[x+1][y].accessible(this)){this.moveSouth();}
            else if(carte[x][y+1].accessible(this)){this.moveEast();}
            else{this.moveWest();}
        }//A Droite
        else if(y+2<Constant.LONGUEUR && carte[x][y+2].getPerso() instanceof Team){
            if(carte[x][y+1].accessible(this)){this.moveEast();}
            else if(carte[x-1][y].accessible(this)){this.moveNorth();}
            else{this.moveSouth();}
        }//A Gauche
        else if(y-2>=0 && carte[x][y-2].getPerso() instanceof Team){
            if(carte[x][y-1].accessible(this)){this.moveWest();}
            else if(carte[x-1][y].accessible(this)){this.moveNorth();}
            else{this.moveSouth();}
        }
        //Sinon si il y a quelqu'un il fuit à l'opposé
        else{
            if(carte[x+1][y].getPerso() != null && carte[x-1][y].accessible(this)){
                this.moveNorth();
            }
            else if(carte[x-1][y].getPerso() != null && carte[x+1][y].accessible(this)){
                this.moveSouth();
            }
            else if(carte[x][y+1].getPerso() != null && carte[x][y-1].accessible(this)){
                this.moveWest();
            }
            else if(carte[x][y-1].getPerso() != null && carte[x][y+1].accessible(this)){
                this.moveEast();
            }
            //Sinon pris de panique il va n'importe où !!
            else{
                this.moveRandom();
            }
        }   
    }
    @Override
    public void phaseAction() {
        this.trahir();
    }
}
