
package Exception;
/**
 * Projet JAVA Semestre1 M1
 * Exception levée quand un probleme intervient au niveau de la création de la carte
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class InitialisationCarteException extends Exception{

    /**
     * Constructeur de l'exception
     * @param string
     */
    public InitialisationCarteException(String string) {
        super(string);
    }
    
}
