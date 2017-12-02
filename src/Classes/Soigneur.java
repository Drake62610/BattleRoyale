package Classes;

import BattleRoyale.Constant;
import Carte.Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */

public class Soigneur extends Personnage{
    //Variable de classe
    private final int capacité; //Capacité de soin qui intervient lors du gain de PV de la methode soigner()

    //CONSTRUCTOR
    /**
     * Initialisation de la capacité en fonctione de la capacitée minimale et maximale
     * @param position_x sur la Carte
     * @param position_y sur la Carte
     */
    public Soigneur(int pv, int force, int deplacement, int vitesse, int position_x, int position_y, Carte carte) {
        super(pv,force,deplacement,vitesse,position_x, position_y,carte);
        
        this.capacité = Constant.MINSOIN + (int)(Math.random()*(Constant.MAXSOIN));
    }
    
    //GETTER
    /**
     * Getter de la variable capacité
     * @return
     */
    public int getCapacité() {
        return capacité;
    }
    
    //METHODS
    /**
     * Permet de soigner un Personnage en fonction de la capacité de soin du soigneur
     */
    public void soigner(Personnage Ami){
        if (Ami.getPVMAX()-Ami.getPv()>=capacité){ //Si la capacité est inférieure à la distance pv -- pvactuel du personnage à soigner
            Ami.setPv(Ami.getPv()+capacité); //On reset les pv du personnage
            System.out.println(this.getName() + " a soigné "+Ami.getName()+" de "+ capacité + " !");
        }
        else{ //Sinon on reset avec les PVMAX directement
            System.out.println(this.getName() + " a soigné "+Ami.getName()+" de "+ Integer.toString(Ami.getPVMAX()-Ami.getPv()) + " !");
            Ami.setPv(Ami.getPVMAX());
        }
    }
    
    /**
     * Polymorphisme de la methode soigner pour se l'aplliquer à soi même
     * La même logique est appliquée
     */
    public void soigner(){
        if (this.getPVMAX() == this.getPv()){//Il ne se passe rien            
        }
        else if (this.getPVMAX()-this.getPv()>=capacité){
            System.out.println(this.getName() + " se soigne de "+ capacité + " ! ");
            this.setPv(this.getPv() + capacité);
        }
        else{
            System.out.println(this.getName() + " se soigne de "+ Integer.toString(this.getPVMAX()-this.getPv()) + " !");
            this.setPv(this.getPVMAX());
        }
            
    }
}
