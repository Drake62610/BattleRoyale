package Classes.Pacifiste;
/**
 * Projet JAVA Semestre1 M1
 * Classe Pacifiste Soigneur, Personnage possédant la caractéristique Pacifiste qui a l'habilité de soigner
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
import BattleRoyale.Constant;
import Carte.Carte;
import Carte.Terrain;
import Classes.Personnage;
import Classes.Soigneur;
import Classes.Team;
import Exception.WalkOnWaterException;

public class PacifisteSoigneur extends Soigneur implements Pacifiste {
    //VARIABLE DE CLASSE
    /**
     * raison est utilisé lors de l'appel de "raisonner", elle détermine le pourcentage de réussite de la capacité
     */
    int raison;
    /**
     * Constructeur de la Classe Pacifiste
     * @param position_x
     * @param position_y
     * @param carte 
     */    
    public PacifisteSoigneur(int position_x, int position_y, Carte carte) {
        super(5+(int)(Math.random()*(10-5)) //PV
                ,1 + (int)(Math.random()*(3-1))//Force
                ,1                        //Deplacement
                ,1                        //Vitesse
                ,position_x, position_y,carte);
        this.raison = 30 + (int)(Math.random()*(30));
    }
    //GETTER
    /**
     * Getter de la variable raison
     * @return 
     */      
    @Override
    public int getRaison() {
        return raison;
    }
    
    /**
     * Capacité spéciale la classe Pacifiste, permet de convaincre n'importe quelle autre classe de rejoindre sa team ou de créer une team
     * @param cible qui pourrait rejoindre la Team
     */  
    @Override
    public void raisonner(Personnage cible) {
        this.parler("Rejoint moi "+ cible.getName() + " !"); //Inspirational speech
        
        boolean success = false;
        if((int)(Math.random()*100)<raison){
            success = true;
            //Travailler sur la raison ici
        }
        else{
            this.parler("Non je ne peux pas vous faire confiance pour le moment");
        }
        
        if(success){
            if(this.getTeam() == null){
                cible.parler("Oui créons une Team dont tu es le leader !");
                this.setTeam(new Team(this));
                this.getTeam().addMember(cible);
            }
            else{
                cible.parler("Oui je veux bien rejoindre ta Team !");
                this.getTeam().addMember(cible);
            }
        }
    }

    /**
     * Polymorphisme pour une Team de la capacité de classe Pacifiste "raisonner"
     * @param cible 
     */  
    @Override
    public void raisonner(Team cible) {
        this.parler("Rejoint moi "+ cible.getLeader().getName() + "toi et ta Team ! Ensemble nous serons plus fort"); //Inspirational speech

        if (this.getTeam() == null){
            cible.getLeader().parler("Bien entendu !");
        }
        else{
            if(cible instanceof Pacifiste && this.raison>((Pacifiste)cible.getLeader()).getRaison()){ //Le nouveau leader est celui avec le plus de raison
                cible.getLeader().parler("Je ne peux pas refuser l'offre d'un Pacifiste comme vous ! Devenez leader à ma place !");
                this.getTeam().getMembres().addAll(cible.getMembres());
                cible.getLeader().getCarte().getCarte_Terrain()[cible.getLeader().getPosition_x()][cible.getLeader().getPosition_y()].setPerso(null);
                //Transfert
                for (int i=0;i<this.getTeam().getMembres().size();i++){
                    this.getTeam().getMembres().get(i).setPosition_x(this.getPosition_x());
                    this.getTeam().getMembres().get(i).setPosition_y(this.getPosition_y());
                    this.getTeam().getMembres().get(i).setTeam(this.getTeam());
                }                
            }
            else{
                cible.getLeader().parler("Bien sur mais je suis plus qualifié que toi, je reste Leader.");
                cible.getMembres().addAll(this.getTeam().getMembres());
                this.getCarte().getCarte_Terrain()[this.getPosition_x()][this.getPosition_y()].setPerso(null);
                //Transfert
                for (int i=0;i<this.getTeam().getMembres().size();i++){
                    cible.getMembres().get(i).setPosition_x(cible.getLeader().getPosition_x());
                    cible.getMembres().get(i).setPosition_y(cible.getLeader().getPosition_y());
                    this.getTeam().getMembres().get(i).setTeam(cible);
                }  
            }
        }
    }
        
    /**
     * Override de phaseDeplacement de la classe Personnage pour le déplacement d'un Pacifiste
     * Un Pacifiste va tenter de se rapprocher des autres et de les rallier à sa cause !
     */
    @Override
    public void choixDeplacement() {
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        try{
            //On regarde si le personnage est en danger
            if (carte[x][y].isDangerImminant()){
                if(!(carte[x+1][y].isDangerImminant()) && carte[x+1][y].accessible(this)){this.moveSouth();}
                else if(!(carte[x-1][y].isDangerImminant()) && carte[x-1][y].accessible(this)){this.moveNorth();}
                else if(!(carte[x][y+1].isDangerImminant()) && carte[x][y+1].accessible(this)){this.moveEast();}
                else if(!(carte[x][y-1].isDangerImminant()) && carte[x][y-1].accessible(this)){this.moveWest();}
                else{this.dontMove();}
            }
            else if (carte[x-1][y].isDangerImminant()){
                if(carte[x+1][y].accessible(this)){this.moveSouth();}
                else if(carte[x][y+1].accessible(this)){this.moveEast();}
                else if(carte[x][y-1].accessible(this)){this.moveWest();}
            }
            else if (carte[x][y+1].isDangerImminant()){
                if(carte[x][y-1].accessible(this)){this.moveWest();}
                else if(carte[x-1][y].accessible(this)){this.moveNorth();}
                else if(carte[x+1][y].accessible(this)){this.moveSouth();}
            }
            else if (carte[x+1][y-1].isDangerImminant()){
                if(carte[x][y+1].accessible(this)){this.moveEast();}
                else if(carte[x-1][y].accessible(this)){this.moveNorth();}
                else if(carte[x+1][y].accessible(this)){this.moveSouth();}
            }
            else if (carte[x+1][y].isDangerImminant()){
                if(carte[x-1][y].accessible(this)){this.moveNorth();}
                else if(carte[x][y+1].accessible(this)){this.moveEast();}
                else if(carte[x][y-1].accessible(this)){this.moveWest();}
            }
            else{//Sinon il peut se deplacer normalement
                //Si il est déjà au CaC alors il ne bouge pas pour recruter
                if(carte[x+1][y].getPerso() != null || carte[x-1][y].getPerso() != null || carte[x][y+1].getPerso() != null || carte[x][y-1].getPerso() != null){
                    this.dontMove();
                }
                //Si y y quelqun d'atteignable en Haut etc
                else if(carte[x-1][y+1].getPerso() != null || carte[x-1][y-1].getPerso() != null || (x-2>=0 && carte[x-2][y].getPerso() != null)){
                    if(carte[x-1][y].accessible(this)){this.moveNorth();}
                    else if(carte[x][y+1].accessible(this)){this.moveEast();}
                    else{this.moveWest();}
                }//En bas
                else if(carte[x+1][y+1].getPerso() != null || carte[x+1][y-1].getPerso() != null || (x+2<Constant.LARGEUR && carte[x+2][y].getPerso() != null)){
                    if(carte[x+1][y].accessible(this)){this.moveSouth();}
                    else if(carte[x][y+1].accessible(this)){this.moveEast();}
                    else{this.moveWest();}
                }//A Droite
                else if(y+2<Constant.LONGUEUR && carte[x][y+2].getPerso() != null){
                    if(carte[x][y+1].accessible(this)){this.moveEast();}
                    else if(carte[x-1][y].accessible(this)){this.moveNorth();}
                    else{this.moveSouth();}
                }//A Gauche
                else if(y-2>=0 && carte[x][y-2].getPerso() != null){
                    if(carte[x][y-1].accessible(this)){this.moveWest();}
                    else if(carte[x-1][y].accessible(this)){this.moveNorth();}
                    else{this.moveSouth();}
                }
                else{
                    this.moveRandom(); 
                }
            }    
        }
        catch (WalkOnWaterException ex){
                this.dontMove();
        }
        catch(Exception ex){
            ex.getMessage();
        }
    }
    
    /**
     * Override de phaseAction de la classe Personnage
     * Un Pacifiste raisonne les personne autour de lui
     * Un Soigneur se soigne en plus
     */
    @Override
    public void phaseAction() {
        int x =this.getPosition_x();
        int y = this.getPosition_y();
        Terrain[][] carte = this.getCarte().getCarte_Terrain();
        if(carte[x+1][y].getPerso() != null){
            if(carte[x+1][y].getPerso() instanceof Personnage){this.raisonner((Personnage)carte[x+1][y].getPerso());}
            else{this.raisonner((Team)carte[x+1][y].getPerso());}
        }
        else if(carte[x-1][y].getPerso() != null){
            if(carte[x-1][y].getPerso() instanceof Personnage){this.raisonner((Personnage)carte[x-1][y].getPerso());}
            else{this.raisonner((Team)carte[x-1][y].getPerso());}
        }
        else if(carte[x][y+1].getPerso() != null){
            if(carte[x][y+1].getPerso() instanceof Personnage){this.raisonner((Personnage)carte[x][y+1].getPerso());}
            else{this.raisonner((Team)carte[x][y+1].getPerso());}
        }
        else if(carte[x][y-1].getPerso() != null){
            if(carte[x][y-1].getPerso() instanceof Personnage){this.raisonner((Personnage)carte[x][y-1].getPerso());}
            else{this.raisonner((Team)carte[x][y-1].getPerso());}
        }
        this.soigner(); //Il essaie toujours de se soigner
    }
    
    
}
