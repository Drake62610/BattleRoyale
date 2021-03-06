/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Traitre;

import BattleRoyale.Constant;
import Carte.Carte;
import Carte.Terrain;
import Classes.Personnage;
import Classes.Piegeur;
import Classes.Team;
import Exception.WalkOnWaterException;
import java.util.ArrayList;

/**
 * Projet JAVA Semestre1 M1
 * Classe Traitre Piegeur, Personnage possédant la caractéristique Traitre qui a l'habilité de poser des pièges
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class TraitrePiegeur extends Piegeur implements Traitre {

    public TraitrePiegeur(int position_x, int position_y, Carte carte) {
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
                if(membres.get(i).getPv()<=this.getForce() && !(membres.get(i).equals(this))){ //Un traitre passse à l'action uniquement si il est sur de tuer
                    cible = membres.get(i);
                    break;
                }
            }
            if (cible != null){
                String tmp1 = cible.getTeam().getLeader().getName();
                String tmp2 = cible.getName();
                this.getTeam().removeMember(this); // IL retourne sa veste
                this.attaquer(cible);              //Et attque
                System.out.println(this.getName() + " a trahit la team de " + tmp1 + " en tuant " + tmp2 + " !");
                this.setTeam(null);
                System.out.println("Attention le traitre s'enfuit ! ");
                int x =this.getPosition_x();
                int y = this.getPosition_y();
                Terrain[][] carte = this.getCarte().getCarte_Terrain();
                try{
                    if(carte[x+1][y].accessible(this)){this.moveSouth();
                        x = this.getPosition_x();
                        y = this.getPosition_y();
                        if(carte[x+1][y].accessible(this)){this.moveSouth();}
                        else if(carte[x][y+1].accessible(this)){this.moveEast();}
                        else if(carte[x][y-1].accessible(this)){this.moveWest();}}
                    else if(carte[x-1][y].accessible(this)){this.moveNorth();
                        x = this.getPosition_x();
                        y = this.getPosition_y();
                        if(carte[x-1][y].accessible(this)){this.moveNorth();}
                        else if(carte[x][y+1].accessible(this)){this.moveEast();}
                        else if(carte[x][y-1].accessible(this)){this.moveWest();}}
                    else if(carte[x][y+1].accessible(this)){this.moveEast();
                        x = this.getPosition_x();
                        y = this.getPosition_y();
                        if(carte[x+1][y].accessible(this)){this.moveSouth();}
                        else if(carte[x][y+1].accessible(this)){this.moveEast();}
                        else if(carte[x-1][y].accessible(this)){this.moveNorth();}}
                    else if(carte[x][y-1].accessible(this)){this.moveWest();
                        x = this.getPosition_x();
                        y = this.getPosition_y();
                        if(carte[x+1][y].accessible(this)){this.moveSouth();}
                        else if(carte[x-1][y].accessible(this)){this.moveNorth();}
                        else if(carte[x][y-1].accessible(this)){this.moveWest();}}
                }            
                catch (WalkOnWaterException ex){
                this.dontMove();
                }
                catch(Exception ex){
                    ex.getMessage();
                }
            }
        }
    }
    
    @Override
    public void choixDeplacement() {
        int x =this.getPosition_x();
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
            else{//Sinon il se deplace normalement
                //Si il est déjà au CaC alors il ne bouge pas pour recruter
                if(carte[x+1][y].getPerso() instanceof Team || carte[x-1][y].getPerso() instanceof Team || carte[x][y+1].getPerso() instanceof Team || carte[x][y-1].getPerso() instanceof Team){
                    this.dontMove();
                }
                //Recherche des team autour de lui
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
        this.trahir();
        if(Math.random() * ( 100 )< 33 && (carte[x][y-1].accessible(this) || carte[x][y+1].accessible(this) || carte[x-1][y].accessible(this) || carte[x+1][y].accessible(this))){
                this.posePiege();
            }
    }
}
