/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Tueur;

import Classes.Personnage;

/**
 *
 * @author ISEN
 */
public class TueurNormal extends Personnage implements Tueur{

    public TueurNormal(int position_x, int position_y) {
        super(position_x, position_y);
    }

    @Override
    public void ravage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
