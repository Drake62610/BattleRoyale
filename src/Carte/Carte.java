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

    //CONSTRUCTEUR de la classe Carte, prends en paramètre la longueur et la largeur de la map à générer
    public Carte(int longueur,int largeur) {
        //Génération aléatoire du terrain
        carte_Terrain = this.genererCarte(longueur, largeur);
        
        //Constante de Carte qui contiendra
    }

    // Affichage de la carte dans la console
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
    
    public void afficher2(){
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Carte");
        fenetre.setSize((Constant.LARGEUR)*Constant.ZOOM_RESO,(Constant.LONGUEUR+2)*Constant.ZOOM_RESO);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setContentPane(new Panneau());
        fenetre.setVisible(true);
    }
    
    public class Panneau extends JPanel { 
        public void paintComponent(Graphics g){
            int x=0;
            int y=0;
            int pas_x = Constant.ZOOM_RESO;
            int pas_y = Constant.ZOOM_RESO;
            for (Terrain[] carte1 : carte_Terrain) {
                for (int j = 0; j < carte1.length; j++) {
                    //Set color
                    if(carte1[j] instanceof Mer){
                        System.out.println("yo");
                        g.setColor(Color.BLUE);
                    }
                    else{
                        g.setColor(Color.RED);
                    }
                    //Draw
                    g.fillRect(x,y,pas_x,pas_y);
                    
                    //Increment
                    x+=pas_x;
                    if(j==carte1.length -1){
                        y+=pas_y;
                        x=0;
                    }
                }
            }
          //g.setColor((Color.BLUE));
          //g.fillRect(0,0,100,100);

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


