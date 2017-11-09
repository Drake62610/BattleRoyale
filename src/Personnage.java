/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ISEN
 */
public class Personnage {
    private String name;
    private final int PVMAX;
    private int pv;
    private int force;
    private int deplacement;
    private int position_x;
    private int position_y;
    //private Arme arme;
    //private Team team;

    
    public Personnage( int position_x, int position_y) {
        String[] tabNom = Constant.TABLEAUNOM;
        this.name = tabNom[(int)(Math.random()*(tabNom.length))];
        this.pv = 5 + (int)(Math.random()*(10-5));
        this.PVMAX = this.pv;
        this.position_x = position_x;
        this.position_y = position_y;
    }
    
    //GETTER
    public String getName() {
        return name;
    }

    public int getPv() {
        return pv;
    }

    public int getPVMAX() {
        return PVMAX;
    }
    

    //SETTER
    public void setPv(int pv) {
        this.pv = pv;
    } 
    
    @Override
    public String toString() {
        return "Fiche Personnage de " + name +".\n"
                + "PV : " + pv +"\n"
                + "FORCE : "+ force+"\n"
                + "DEPLACEMENT : " + deplacement+"\n"
                + "Armes : à implémenter" + "\n"
                + "Team : à implémenter";
    }
    
    //METHODS
    public void parler(String text){
        System.out.println(name + " dit : " + text);
    }
    
}
