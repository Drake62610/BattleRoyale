package Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Terrain {
    //VARIABLE DE CLASSE
    private Object perso;  //Le personnage qui est sur le terrain OU la team
    private boolean piege; //Indique si le terrain est piégé ou non

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
    
    
}
