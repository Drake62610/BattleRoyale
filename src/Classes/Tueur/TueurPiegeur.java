/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Tueur;

import Carte.Carte;
import Carte.Terrain;
import Classes.Personnage;
import Classes.Piegeur;
import Classes.Team;

public class TueurPiegeur extends Piegeur implements Tueur {

    private boolean ravageReady = true;
    
    public TueurPiegeur(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(5-1)) //PV
                ,(int)(Math.random()*(6-3))//Force
                ,1                        //Deplacement
                ,1                        //Vitesse
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
    
}
