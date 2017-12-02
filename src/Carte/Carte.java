package Carte;

import BattleRoyale.Constant;
import Classes.Pacifiste.Pacifiste;
import Classes.Team;
import Classes.Traitre.Traitre;
import Classes.Trouillard.Trouillard;
import Classes.Tueur.Tueur;
import Exception.InitialisationCarteException;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Projet JAVA Semestre1 M1
 * Classe contenant la carte du jeu ainsi que les méthodes pour obtenir une interface graphique
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Carte {
    //VARIABLE DE CLASSE
    private Terrain[][] carte_Terrain;  //Contient une île entourée de mer sur lequel le jeus e déroulera

    //CONSTRUCTOR
    /**
     * CONSTRUCTEUR de la classe Carte, prends en paramètre la longueur et la largeur de la map à générer
     * @param longueur longueur doit être plus grand que largeur
     * @param largeur  largeur doit être plus petit que longueur
     * @throws Exception.InitialisationCarteException
     */
    public Carte(int longueur,int largeur) throws InitialisationCarteException {
        if(longueur<largeur){
            throw new InitialisationCarteException("LONGUEUR et LARGEUR invalid, veuillez vérifier les paramètre de la classe CONSTANT");
        }
        carte_Terrain = this.genererCarte(longueur, largeur);
    }

    //GETTER
    /**
    * Getter de la variable carte_Terrain
    * @return 
    */
    public Terrain[][] getCarte_Terrain() {
        return carte_Terrain;
    }
    /**
     * Getter de la classe Panneau qui génère un JPanel pour l'interface graphique
     * @return UNe nouvelle instance de la classe Panneau
     */
    public Panneau getPanneau() {
        return new Panneau();
    }
    
    //METHODS
    /**
     * Methode permettant d'afficher dans la console la carte
     * Utilisée au début du projet, plus maintenant
     */
    public void afficher(){
        for (int i = 0; i < carte_Terrain.length; i++) {
            for (int j = 0; j < carte_Terrain[0].length; j++) {
                    System.out.println("8");
            }
            System.out.println();
        }
    }
    
    /**
     * Initialise un JFrame
     * @return Renvoie le JFrame initialisée
     */
    public JFrame getIntG(){
        final JFrame fenetre = new JFrame();
        fenetre.setTitle("Carte");  //Titre de la fenetre
        fenetre.setSize((Constant.LONGUEUR)*Constant.ZOOM_RESO,(Constant.LARGEUR+2)*Constant.ZOOM_RESO); // Definition de la resolution de la fenêtre
        fenetre.setLocationRelativeTo(null); //Centre la fenetre 
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Permet l'arret du JFrame en cliquand sur la croix rouge
        fenetre.setContentPane(new Panneau()); //Fait appel à la fonction paintComponent de la classe Panneau et lit le JFrame au JPanel
        return fenetre;
    }
    
    /**
     * Classe gerrant la génération de la carte en utilisant un JPanel
     */
    public class Panneau extends JPanel { 
        /**
         * Methode appelée lors de la creation d'une instance Panneau
         * @param g sera généré automatiquement grace à JPanel, cet objet sert nottament au traçage de figure dans la fenêtre
         */
        public void paintComponent(Graphics g){
            int x=0; //coordonnée x de départ (en haut à gauche de l'écran)
            int y=0; //coordonnée y de départ
            int pas_x = Constant.ZOOM_RESO;  //Zoom reso est la longueur d'une case de la carte en pixel
            int pas_y = Constant.ZOOM_RESO;  //Ainsi correspond au pas pour x et pour y
            //On parcours la carte
            for (Terrain[] carte1 : carte_Terrain) { 
                for (int j = 0; j < carte1.length; j++) {
                    //Set color
                    if(carte1[j] instanceof Mer){
                        g.setColor(Color.blue);
                    }
                    if(carte1[j] instanceof Foret){
                        g.setColor(Color.DARK_GRAY);
                    }
                    if(carte1[j] instanceof Montagne){
                        g.setColor(Color.lightGray);
                    }
                    if(carte1[j] instanceof Plaine){
                        g.setColor(Color.green);
                    }
                    if(carte1[j].isPiege()){
                        g.setColor(Color.yellow);
                    }
                    if(carte1[j].isDangerImminant()){
                        g.setColor(Color.RED);
                    }
                    //Draw
                    g.fillRect(x,y,pas_x,pas_y);
                    
                    //If there is a Personnage
                    if(carte1[j].getPerso(1) != null){
                        //Set color
                        if(carte1[j].getPerso(1) instanceof Pacifiste){g.setColor(Color.YELLOW);}
                        if(carte1[j].getPerso(1) instanceof Trouillard){g.setColor(Color.PINK);}
                        if(carte1[j].getPerso(1) instanceof Tueur){g.setColor(Color.RED);}
                        if(carte1[j].getPerso(1) instanceof Traitre){g.setColor(Color.white);}
                        if(carte1[j].getPerso(1) instanceof Team){g.setColor(Color.BLUE);}
                        //Draw
                        g.fillRect((x+x+pas_x)/2,(y+y+pas_y)/2,pas_x/2,pas_y/2);
                    }
                    
                    //Increment
                    x+=pas_x;
                    if(j==carte1.length -1){ //Cas extrème où on est en bout de ligne
                        y+=pas_y; //O passe à la ligne cad on incremente le y
                        x=0;      //Et on reset le x
                    }
                    
                }
            }
        }               
      }
    
    /**
     * Methode appellée pour générer la carte de jeu de manière pseudo aléatoire
     * un rectangle de base sera toujours créé mais les contours de l'ile sont variables
     */
    private Terrain[][] genererCarte(int longueur, int largeur){
        carte_Terrain = new Terrain[largeur][longueur];
        //bloc qui créé le rectangle de base
        for (Terrain[] carte1 : carte_Terrain) {
                for (int j = 0; j< longueur; j++) {
                    carte1[j] = new Mer();
                }
            }
        // bloc qui créé le contour de la carte de manière pseudo aléatoire
        for( int i=longueur/4; i<(3*longueur)/4; i++){
            int nb = (int) (Math.random() * 3 );
            for( int t=largeur/4-nb; t<(3*largeur)/4+nb; t++){
                carte_Terrain[t][i]= new Terrain();            
                }
        }
        for(int p=largeur/4; p<(3*largeur)/4; p++){
            int rdn = (int) (Math.random()*3);
            for(int q=longueur/4-rdn; q<(3*longueur)/4+rdn; q++){
                carte_Terrain[p][q]= new Terrain();
                }
        }            
        //bloc determinant le type de terrain créé
        for (int m=0; m<largeur; m++){
            for (int n=0; n<longueur; n++){
                int type_terrain = (int) (Math.random() * 3);
                if(!(carte_Terrain[m][n] instanceof Mer)){
                    switch (type_terrain) {
                        case 0:
                            carte_Terrain[m][n]= new Foret();
                            break;
                        case 1:
                            carte_Terrain[m][n]= new Montagne();
                            break;
                        default:
                            carte_Terrain[m][n]= new Plaine();
                            break;
                    }
                }
            }
        }
        return carte_Terrain;
    }
    
    /**
     * Methode qui crée un danger sur une case du terrain en fonction de ses coordonnées
     * @param y coordonnée
     * @param x coordonnée
     */
    public void restreindre(int y, int x){
        carte_Terrain[y][x].setDangerImminant(true);
    }
}