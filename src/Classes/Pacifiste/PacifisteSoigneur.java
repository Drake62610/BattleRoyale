/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Pacifiste;

import Classes.Personnage;
import Classes.Soigneur;

/**
 *
 * @author ISEN
 */
public class PacifisteSoigneur extends Soigneur implements Pacifiste {

    public PacifisteSoigneur(int position_x, int position_y) {
        super(position_x, position_y);
    }

    @Override
    public void raisonner(Personnage cible) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
