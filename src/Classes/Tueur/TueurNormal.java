/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Tueur;

import Carte.Carte;
import Carte.Terrain;
import Classes.Personnage;
import Classes.Team;

public class TueurNormal extends Personnage implements Tueur{
    /**
     * Afin de limiter les dégats il ne peut utiliser son attaque spéciale que tout les X tours
     */
    private boolean ravageReady = true;
    /**
     * Constructeur de la classe Tueur
     */        
    public TueurNormal(int position_x, int position_y,Carte carte) {
        super(position_x, position_y,carte);
    }

    /**
     * Override de la methode ravage, elle permet si ravageReady le permet d'attaquer sur un carré autour de lui
     */
    @Override
    public void ravage() {
        int x = this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarteTerrain().getCarte_Terrain();
        
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
