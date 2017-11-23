/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Trouillard;

import Carte.Carte;
import Carte.Foret;
import Carte.Terrain;
import Classes.Piegeur;

public class TrouillardPiegeur extends Piegeur implements Trouillard {

    private boolean hidden;
    
    public TrouillardPiegeur(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(5-1)) //PV
                ,(int)(Math.random()*(3-1))//Force
                ,2                        //Deplacement
                ,4                        //Vitesse
                ,position_x, position_y,carte);
        hidden = false;
    }

   /**
     * Pour l'instant il ne peut se cacher que dans la Forêt
     * @param carte 
     */
    @Override
    public void seCamoufler(Carte carte) {
        Terrain terrainActuel = carte.getCarte_Terrain()[this.getPosition_x()][this.getPosition_y()];
        if(terrainActuel instanceof Foret && ((Foret)terrainActuel).seCacher()){
           hidden = true;            
        }
    }
    
}
