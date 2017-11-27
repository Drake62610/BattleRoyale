package Carte;

import Classes.Personnage;
import Classes.Piegeur;
import Classes.Trouillard.Trouillard;

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
    /**
     * Setter pour la variable Perso
     * @param piege
     */
    public void setPerso(Object perso) {
        this.perso = perso;
    }

    public void setDangerImminant(boolean dangerImminant) {
        this.dangerImminant = dangerImminant;
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
     * Cette méthode est exclusivement utilisé par les joueurs car ils ne peuvent pas voir les Trouillards cachés
     * @return si le terrain est piègé ou non.
     */
    public Object getPerso() {
        if (perso instanceof Trouillard && ((Trouillard)perso).isHidden()){
            return null;
        }
        return perso;
    }
        /**
     * Getter pour la variable Personnage
     * @param i si un paramètre est trouvé alors la requete vient d'un administrateur de la partie qui a le droit de voir les trouillard caché
     * @return si le terrain est piègé ou non.
     */
    public Object getPerso(int i) {
        return perso;
    }
    /**
     * Getter pour la variable DangerImminant
     * @return si le terrain est piègé ou non.
     */
    public boolean isDangerImminant() {
        return dangerImminant;
    }
    
    public boolean accessible(Personnage perso){
        if (this instanceof Mer){
            return false;
        }
        if (piege && perso instanceof Piegeur){
            return false;
        }
        if (perso.equals(null)){
            return false;
        }
        return true;
    }
    
}
