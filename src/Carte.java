
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Loïc
 */
public class Carte {
        
        Terrain[][] carte_Terrain;
        
        public void carte(){}

    public Carte(int longueur,int largeur) {
        carte_Terrain = new Terrain[longueur][largeur];
        for (Terrain[] carte1 : carte_Terrain) {
                for (int j = 0; j< largeur; j++) {
                    carte1[j] = null;
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
    }
        
        // Affichage de la matrice
        public void afficher(){
            for (Terrain[] carte1 : carte_Terrain) {
                for (int j = 0; j < carte1.length; j++) {
                        if(carte1[j] instanceof Terrain){
                            System.out.print("⬛ ");
                        }
                        else{
                            System.out.print("  ");
                        }
                }
                System.out.println();
            }
        }
    }


