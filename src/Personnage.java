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
        this.pv = 10 + (int)(Math.random()*(30-10));
        this.position_x = position_x;
        this.position_y = position_y;
    }
    
    public String getName() {
        return name;
    }

    public int getPv() {
        return pv;
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
    
    
}
