/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Traitre;

import Classes.Personnage;
import Classes.Team;

/**
 *
 * @author ISEN
 */
public class TraitreNormal extends Personnage implements Traitre {

    public TraitreNormal(int position_x, int position_y) {
        super(position_x, position_y);
    }

   
    @Override
    public void trahir(Team team) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
