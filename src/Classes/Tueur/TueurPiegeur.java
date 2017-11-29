/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Tueur;

import BattleRoyale.Constant;
import Carte.Carte;
import Carte.Terrain;
import Classes.Personnage;
import Classes.Piegeur;
import Classes.Team;

public class TueurPiegeur extends Piegeur implements Tueur {

    private boolean ravageReady = true;
    
    public TueurPiegeur(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(5-1)) //PV
                ,5 + (int)(Math.random()*(8-5))//Force
                ,1                        //Deplacement
                ,2                        //Vitesse
                ,position_x, position_y,carte);
    }

    @Override
    public void ravage() {
        int x = this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        
        for(int i=-1;i<2;i++){ //On  parcourt le carré autour de lui
            for(int j=-1;j<2;j++){
                if(!(i==0 && j==0)){ //Attention il ne doit pas s'attaquer lui même !
                    if(carte[i][j].getPerso() != null && carte[i][j].getPerso() instanceof Personnage){ //Si c'est un perso il l'attaque
                        this.attaquer((Personnage)carte[i][j].getPerso());
                    } 
                    else if((carte[i][j].getPerso() != null && carte[i][j].getPerso() instanceof Team)){ //Si c'est une Team il attaque son leader
                        this.attaquer(((Team)carte[i][j].getPerso()).getLeader());
                    }
                }
            }
        }
    }

    @Override
    public void choixDeplacement() {
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
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
            //Si il est déjà au CaC alors il ne bouge pas pour attaquer
            if(carte[x+1][y].getPerso() != null || carte[x-1][y].getPerso() != null || carte[x][y+1].getPerso() != null || carte[x][y-1].getPerso() != null){
                this.dontMove();
            }
            //Si y y quelqun d'atteignable en Haut etc
            else if(carte[x-1][y+1].getPerso() != null || carte[x-1][y-1].getPerso() != null || (x-2>=0 && carte[x-2][y].getPerso() != null)){                
                if(carte[x-1][y].accessible(this)){this.moveNorth();}
                else if(carte[x][y+1].accessible(this)){this.moveEast();}
                else if(carte[x][y-1].accessible(this)){this.moveWest();}
                else{this.dontMove();}
            }//En bas
            else if(carte[x+1][y+1].getPerso() != null || carte[x+1][y-1].getPerso() != null || (x+2<Constant.LARGEUR && carte[x+2][y].getPerso() != null)){
                if(carte[x+1][y].accessible(this)){this.moveSouth();}
                else if(carte[x][y+1].accessible(this)){this.moveEast();}
                else if(carte[x][y-1].accessible(this)){this.moveWest();}
                else{this.dontMove();}
            }//A Droite
            else if(y+2<Constant.LONGUEUR && carte[x][y+2].getPerso() != null){
                if(carte[x][y+1].accessible(this)){this.moveEast();}
                else if(carte[x-1][y].accessible(this)){this.moveNorth();}
                else if(carte[x+1][y].accessible(this)){this.moveSouth();}
                else{this.dontMove();}
            }//A Gauche
            else if(y-2>=0 && carte[x][y-2].getPerso() != null){
                if(carte[x][y-1].accessible(this)){this.moveWest();}
                else if(carte[x-1][y].accessible(this)){this.moveNorth();}
                else if(carte[x+1][y].accessible(this)){this.moveSouth();}
                else{this.dontMove();}
            }
            else{
                this.moveRandom(); 
            }
        }
    }

    @Override
    public void phaseAction() {
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        if(carte[x+1][y].getPerso() != null){
            this.attaquer(carte[x+1][y].getPerso());
        }
        else if(carte[x-1][y].getPerso() != null){
            this.attaquer(carte[x-1][y].getPerso());
        }
        else if(carte[x][y+1].getPerso() != null){
            this.attaquer(carte[x][y+1].getPerso());
        }
        else if(carte[x][y-1].getPerso() != null){
            this.attaquer(carte[x][y-1].getPerso());
        }
        else{
            if(Math.random() * ( 100 )< 33){
                this.posePiege();
            }
            
        }
    }
    
}
