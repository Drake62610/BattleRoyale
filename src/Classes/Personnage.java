package Classes;


import BattleRoyale.Constant;
import Carte.Carte;


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
    private int critique;
    private int deplacement;
    private int position_x;
    private int position_y;
    //private Arme arme;
    private Team team;

    
    public Personnage( int position_x, int position_y) {
        String[] tabNom = Constant.TABLEAUNOM;
        this.name = tabNom[(int)(Math.random()*(tabNom.length))];
        this.pv = 5 + (int)(Math.random()*(10-5));
        this.PVMAX = this.pv;
        this.force = 1 + (int)(Math.random()*(10-5)); //Force à définir sur les différences classe plus tard
        this.position_x = position_x;
        this.position_y = position_y;
        this.team = null;
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

    public int getCritique() {
        return critique;
    }

    public int getPosition_x() {
        return position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    //SETTER
    public void setPv(int pv) {
        this.pv = pv;
    } 

    public void setCritique(int critique) {
        this.critique = critique;
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
    
    public void attaquer(Personnage passif){
        //Notion de coup critique
        if((int)(Math.random()*100)<this.getCritique()){
            this.parler("COUP CRITIQUE");
            passif.enquaisser(this.force * 2);
        }
        else{
            //Augmenter le ratio 
            passif.enquaisser(this.force);
            
            
        }
    }
    
    public void enquaisser(int dmg){
        if(this.getPv()<dmg){
            this.setPv(0);
            this.parler("Monde de merde ! x|");
        }
        else{
            this.setPv(this.getPv()-dmg);
            this.parler("Aie");
            //Augmenter ratio critique
        }
    }
    
    public void deplacement(Carte carte){
        
    }   
}
