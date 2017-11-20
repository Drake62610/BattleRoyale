package Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Terrain {
    
    private Object perso; //Le personnage qui est sur le terrain OU la team
    private boolean piege;

    public void setPiege(boolean piege) {
        this.piege = piege;
    }

    public boolean isPiege() {
        return piege;
    }
    
    
}
