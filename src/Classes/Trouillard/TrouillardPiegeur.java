/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Trouillard;

import Carte.Carte;
import Classes.Piegeur;

/**
 *
 * @author ISEN
 */
public class TrouillardPiegeur extends Piegeur implements Trouillard {

    public TrouillardPiegeur(int position_x, int position_y,Carte carte) {
        super(position_x, position_y,carte);
    }

    @Override
    public void seCamoufler(Carte carte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
