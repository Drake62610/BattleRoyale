/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Loïc
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
