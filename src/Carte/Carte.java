package Carte;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Carte {
    //VARIABLE DE CLASSE
    Terrain[][] carte_Terrain;
 
    public Carte(int longueur,int largeur) {
        //Génération aléatoire du terrain
        carte_Terrain = this.genererCarte(longueur, largeur);
        
        //Constante de Carte qui contiendra
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
    
    private Terrain[][] genererCarte(int longueur,int largeur){
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
        return carte_Terrain;
    }
}


