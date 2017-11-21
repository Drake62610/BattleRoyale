package Carte;

import BattleRoyale.Constant;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Carte {
    //VARIABLE DE CLASSE
    Terrain[][] carte_Terrain;  //Contient une île entourée de mer sur lequel le jeus e déroulera

    /**
     * CONSTRUCTEUR de la classe Carte, prends en paramètre la longueur et la largeur de la map à générer
     * @param longueur
     * @param largeur
     */
    public Carte(int longueur,int largeur) {
        //Génération aléatoire du terrain
        carte_Terrain = this.genererCarte(longueur, largeur);
        
        //Constante de Carte qui contiendra
    }

    // Affichage de la carte dans la console

    /**
     * Methode permettant d'afficher dans la console la carte
     */
    public void afficher(){
        for (Terrain[] carte1 : carte_Terrain) {
            for (int j = 0; j < carte1.length; j++) {
                    if(!(carte1[j] instanceof Mer)){
                        System.out.print("⬛ ");
                    }
                    else{
                        System.out.print("  ");
                    }
            }
            System.out.println();
        }
    }
    
    /**
     * Methode permettant d'afficher dans une fenetre à part la carte à l'iade de JFrame et JPanel
     */
    public void afficher2(){
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Carte");  //Titre de la fenetre
        fenetre.setSize((Constant.LARGEUR)*Constant.ZOOM_RESO,(Constant.LONGUEUR+2)*Constant.ZOOM_RESO); // Definition de la resolution de la fenêtre
        fenetre.setLocationRelativeTo(null); //Centre la fenetre 
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Permet l'arret du JFrame en cliquand sur la croix rouge
        fenetre.setContentPane(new Panneau()); //Fait appel à la fonction paintComponent de la classe Panneau et lit le JFrame au JPanel
        fenetre.setVisible(true); //Fait apparaitre la fenêtre
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
                    else{
                        g.setColor(Color.lightGray);
                    }
                    //Draw
                    g.fillRect(x,y,pas_x,pas_y);
                    
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
    
    //Methode appellée pour générer la carte
    private Terrain[][] genererCarte(int longueur,int largeur){
        carte_Terrain = new Terrain[longueur][largeur];
        for (Terrain[] carte1 : carte_Terrain) {
                for (int j = 0; j< largeur; j++) {
                    carte1[j] = new Mer();
                }
            }

            for( int i=largeur/4; i<(3*largeur)/4; i++){
                int nb = (int) (Math.random() * 3 );
                for( int t=longueur/4-nb; t<(3*longueur)/4+nb; t++){
                    carte_Terrain[t][i]= new Foret();            
                }
            }
            for(int p=longueur/4; p<(3*longueur)/4; p++){
                int rdn = (int) (Math.random()*3);
                for(int q=largeur/4-rdn; q<(3*largeur)/4+rdn; q++){
                    carte_Terrain[p][q]= new Foret();
                }
        }
        return carte_Terrain;
    }
}


