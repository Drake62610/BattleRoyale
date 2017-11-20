package Classes;


import Carte.Terrain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ISEN
 */
public class Piegeur extends Personnage {

    public Piegeur(int position_x, int position_y) {
        super(position_x, position_y);
    }
    
    public void posePiege(Terrain[][] carte){
        Terrain terrain = carte[this.getPosition_x()][this.getPosition_y()];
        terrain.setPiege(true);
    }
}
