package Classes;

import BattleRoyale.Constant;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Soigneur extends Personnage{
    //Variable de classe
    private int capacité; //Capacité de soin qui intervient lors du gain de PV de la methode soigner()

    //CONSTRUCTOR
    /**
     * Initialisation de la capacité en fonctione de la capacitée minimale et maximale
     * @param position_x sur la Carte
     * @param position_y sur la Carte
     */
    public Soigneur( int position_x, int position_y) {
        super(position_x,position_y);
        
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
    void soigner(Personnage Ami){
        if (Ami.getPVMAX()-Ami.getPv()>=capacité){
            Ami.setPv(Ami.getPv()+capacité);
            System.out.println(this.getName() + " a soigné "+Ami.getName()+" de "+ capacité + " !");
        }
        else{
            System.out.println(this.getName() + " a soigné "+Ami.getName()+" de "+ Integer.toString(Ami.getPVMAX()-Ami.getPv()) + " !");
            Ami.setPv(Ami.getPVMAX());
        }
    }
    
    /**
     * Polymorphisme de la methode soigner pour se l'aplliquer à soi même
     */
    void soigner(){
        if (this.getPVMAX()-this.getPv()>=capacité){
            System.out.println(this.getName() + " se soigne de "+ capacité + " ! ");
            this.setPv(this.getPv() + capacité);
        }
        else{
            System.out.println(this.getName() + " se soigne de "+ Integer.toString(this.getPVMAX()-this.getPv()) + " !");
            this.setPv(this.getPVMAX());
        }
            
    }
}
