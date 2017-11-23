/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Tueur;

import Carte.Carte;
import Classes.Personnage;

/**
 *
 * @author ISEN
 */
public class TueurNormal extends Personnage implements Tueur{
    /**
     * Afin de limiter les dégats il ne peut utiliser son attaque spéciale que tout les X tours
     */
    private boolean ravageReady = true;
    public TueurNormal(int position_x, int position_y,Carte carte) {
        super(position_x, position_y,carte);
    }

    @Override
    public void ravage() {
        //coder un truc qui regarde le carré autour si il y a des gens, les mets dans une liste et leurs enlève autant de pv.
        //Flemme de le coder
        int x = this.getPosition_x();
        int y = this.getPosition_y();
    }
    
}
