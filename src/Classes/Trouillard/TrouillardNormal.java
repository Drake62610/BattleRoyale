/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Trouillard;

import Carte.Carte;
import Carte.Foret;
import Carte.Terrain;
import Classes.Personnage;

/**
 *
 * @author ISEN
 */
public class TrouillardNormal extends Personnage implements Trouillard {

    private boolean hidden;

    public TrouillardNormal(int position_x, int position_y) {
        super(position_x, position_y);
        hidden = false;
    }
    
    @Override
    public void seCamoufler(Carte carte) {
        Terrain terrainActuel = carte.getCarte_Terrain()[this.getPosition_x()][this.getPosition_y()];
        if(terrainActuel instanceof Foret){
           // Foret terrainForet = terrainActuel;
            
        }
    }
    
}
