package Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Terrain {
    //VARIABLE DE CLASSE
    /**
     * Le personnage qui est sur le terrain OU la team
     */
    private Object perso; 
    /**
     * Indique si le terrain est piégé ou non
     */
    private boolean piege; 
    /**
     * Indique si la zone va devenri rouge à la fin du tour de jeu
     */
    private boolean dangerImminant; 

    //SETTER
    /**
     * Setter pour la variable piège
     * @param piege
     */
    public void setPiege(boolean piege) {
        this.piege = piege;
    }

    //GETTER
    /**
     * Getter pour la variable piège
     * @return si le terrain est piègé ou non.
     */
    public boolean isPiege() {
        return piege;
    }
    /**
     * Getter pour la variable Personnage
     * @return si le terrain est piègé ou non.
     */
    public Object getPerso() {
        return perso;
    }
    /**
     * Getter pour la variable DangerImminant
     * @return si le terrain est piègé ou non.
     */
    public boolean isDangerImminant() {
        return dangerImminant;
    }
    
    
    
}
