/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Trouillard;

import Carte.Carte;
import Classes.Soigneur;

/**
 *
 * @author ISEN
 */
public class TrouillardSoigneur extends Soigneur implements Trouillard {

    public TrouillardSoigneur(int position_x, int position_y,Carte carte) {
        super(position_x, position_y,carte);
    }

    @Override
    public void seCamoufler(Carte carte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
