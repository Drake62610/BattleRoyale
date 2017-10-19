
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
            
            for(int i=largeur/4;i<(3*largeur)/4;i++){
                int nb = (int) (Math.random() * 3 );
                for(int t=longueur/4-nb;t<(3*longueur)/4+nb;t++){
                    carte[t][i]=1;
                }
            for(int a=longueur/4;a<(3*longueur)/4;a++){
                int alea = (int) (Math.random() * 1 );
                for(int b=largeur/4-alea;b<(3*largeur)/4+alea;b++){
                    carte[a][b]=1;
                    
                }
                
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


