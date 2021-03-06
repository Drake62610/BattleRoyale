package Carte;

import Classes.Personnage;
import Classes.Piegeur;
import Classes.Team;
import Classes.Trouillard.Trouillard;

/**
 * Projet JAVA Semestre1 M1
 * Classe Terrain, définit toute les caractéristique des Terrains en général
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
     * Utilisée par les piégeurs (methode setpiege)
     * @param piege
     */
    public void setPiege(boolean piege) {
        if(!this.piege){
            if(perso instanceof Personnage){ System.out.println(((Personnage)perso).getName() + " pose un piège ! ");}
            if(perso instanceof Team){ System.out.println(((Team)perso).getLeader().getName() + " pose un piège ! ");}
            this.piege = piege;
        }
    }
    /**
     * Setter pour la variable Perso
     * @param perso le personage qui est sur le Terrain
     */
    public void setPerso(Object perso) {
        this.perso = perso;
    }
    /**
     * Setter de la variable dangerImminant
     * Utilisé pour la zone rouge
     * @param dangerImminant 
     */
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
     * @param i si un paramètre est trouvé alors la requete vient d'un administrateur de la partie qui a le droit de voir les trouillards caché
     * @return 
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
    
    /**
     * Methode qui determine si une case est accessible ou pas par un personnage
     * @param perso
     * @return si oui ou non une case est atteignable
     */
    public boolean accessible(Personnage perso){
        if (this instanceof Mer){ //Un personnage ne peut pas marcher sur l'eau
            return false;
        }
        //Recupération de variable utile
        Terrain[][] carte = perso.getCarte().getCarte_Terrain();
        int x = perso.getPosition_x();
        int y = perso.getPosition_y();
        if (piege && perso instanceof Piegeur && (!carte[x][y-1].isDangerImminant() || !carte[x][y+1].isDangerImminant() || !carte[x-1][y].isDangerImminant() || !carte[x+1][y].isDangerImminant())){
            return false; //Un piégeur ne marche pas dans un piège, il le fait uniquement si la zone rouge est proche
        }
        if (this.getPerso() != null){ //On ne marche pas sur les cases où il y a déjà quelqun
            return false;             //Unique cas possible : une case où un trouillard se cache
        }
        if(dangerImminant){ //Une personne n'iras jamais dans une zone rouge
           return false; 
        }
        return true; //Si toute ces conditions sont vérifié alors le personnage peut se déplacer sur le Terrain en question
    }
    
}
