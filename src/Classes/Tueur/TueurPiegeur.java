/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Tueur;

import Carte.Carte;
import Classes.Piegeur;

/**
 *
 * @author ISEN
 */
public class TueurPiegeur extends Piegeur implements Tueur {

    public TueurPiegeur(int position_x, int position_y,Carte carte) {
        super(position_x, position_y,carte);
    }

    @Override
    public void ravage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
