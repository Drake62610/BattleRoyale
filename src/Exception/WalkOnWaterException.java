package Exception;
/**
 * Projet JAVA Semestre1 M1
 * Exception levée quand un personnage essaie de marcher sur une case d'eau
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class WalkOnWaterException extends Exception {

    /**
     * Constructeur de l'exception
     * @param string
     */
    public WalkOnWaterException() {
        super("Un personnage essaie de marcher sur l'eau !");
    } 
}
