/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Tueur;

import Classes.Piegeur;

/**
 *
 * @author ISEN
 */
public class TueurPiegeur extends Piegeur implements Tueur {

    public TueurPiegeur(int position_x, int position_y) {
        super(position_x, position_y);
    }

    @Override
    public void ravage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
