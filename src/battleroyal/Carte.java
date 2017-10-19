
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleroyal;

/**
 *
 * @author Loïc
 */
public class Carte {
     
      
        public void carte(){}
        
        // Création de la matrice
        public void créer (int[][] carte, int largeur, int longueur){
            for (int[] carte1 : carte) {
                for (int j = 0; j< carte.length; j++) {
                    carte1[j] = 0;
                }
            }
            
            for( int i=largeur/4; i<(3*largeur)/4; i++){
                int nb = (int) (Math.random() * 3 );
                for( int t=longueur/4-nb; t<(3*longueur)/4+nb; t++){
                    carte[t][i]=1;            
                }
            }
            for(int p=longueur/4; p<(3*longueur)/4; p++){
                int rdn = (int) (Math.random()*3);
                for(int q=largeur/4-rdn; q<(3*largeur)/4+rdn; q++){
                    carte[p][q]=1;
                }
        }
        }
        
        // Affichage de la matrice
        public void afficher(int[][] carte){
            for (int[] carte1 : carte) {
                for (int j = 0; j < carte1.length; j++) {
                    System.out.print(carte1[j] + " ");
                }
                System.out.println();
            }
        }
    }


