package Exception;
/**
 * Projet JAVA Semestre1 M1
 * Exception levée quand un probleme intervient au niveau de la création d'un Personnage
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class InitialisationPersonnageException extends Exception {

    /**
     * Constructeur de l'exception
     * @param string
     */
    public InitialisationPersonnageException(String string) {
        super(string);
    }
}
