/**
 * Projet JAVA Semestre1 M1
 *
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
package Classes.Traitre;

import Carte.Carte;
import Classes.Pacifiste.Pacifiste;
import Classes.Personnage;
import Classes.Piegeur;
import Classes.Team;
import Classes.Trouillard.Trouillard;
import Classes.Tueur.Tueur;
import java.util.ArrayList;

/**
 *
 * @author ISEN
 */
public class TraitrePiegeur extends Piegeur implements Traitre {

    public TraitrePiegeur(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(10-5)) //PV
                ,(int)(Math.random()*(3-1))//Force
                ,1                        //Deplacement
                ,3                        //Vitesse
                ,position_x, position_y,carte);
    }
    
    /**
     * Cette action est un passif et sera executée dès qu'une personne dans la team sera tuable.
     * Ensuite il quitera la team et deplacera 2 cases plus loin.
     * Il n'est pas impossible qu'il rentre à nouveau dans la team puisque sa trahison n'est connue uniquement de la personne morte.
     * @param team 
     */
    @Override
    public void trahir(Team team) {
        if(this.getTeam()!=null){
            Personnage cible = null;
            ArrayList <Personnage> membres = this.getTeam().getMembres();
            for(int i=0;i<membres.size();i++){  
                if(membres.get(i).getPv()<this.getForce()){ //Un traitre passse à l'action uniquement si il est sur de tuer
                    if(cible == null){ //Si il n'as pas de cible dans la team alors il prends la personne repérée
                        cible = membres.get(i);
                    }
                    else if(membres.get(i) instanceof Tueur){ //Cas où la personne est une Tueur
                        if( cible instanceof Tueur && cible.getPv()<membres.get(i).getPv()){ //Si egualitée de classe on préfère tuer la personne avec le plus de PV
                            cible = membres.get(i);
                        }//Tuer un Tueur est une priorité car il est le plus dangereux
                    }
                    else if(membres.get(i) instanceof Pacifiste){
                        //TODO
                    }
                    else if(membres.get(i) instanceof Trouillard){
                        //TODO
                    }
                }
            }
            if (cible != null){
                this.attaquer(cible);
                //this.deplacement(null);
            }
        }
    }
    
}
