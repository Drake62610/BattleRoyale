package BattleRoyale;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Projet JAVA Semestre1 M1
 * Classe contenant toutes les constantes
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Constant {
    
    //CONSTANTS
    /**
     * Constante de largeur de la carte
     */
    public static final int LARGEUR=12;
    /**
     * Constante de longueur de la carte
     */
    public static final int LONGUEUR=50;
    /**
     * Constante pour fixer la resolution de l'affichage de la carte
     */
    public static final int ZOOM_RESO = 25;
    /**
     * Pour retrouver le fichier name.txt
     */
    public static final String NAMETXTPATH = new java.io.File("").getAbsolutePath() + "\\src\\name.txt";
    /**
     * On stocke tout les noms dans un tableau qui est une constante
     */
    public static final String [] TABLEAUNOM = txtToString(NAMETXTPATH) ;
    /**
     * Constante pour le soin maximum
     */
    public static final int MAXSOIN =4;
    /**
     * Constante pour le soin minimum
     */
    public static final int MINSOIN = 1;
    
    //GETTER
    /**
     * Getter pour le nom du tableau
     * @return
     */
    public String[] getTABLEAUNOM() {
        return TABLEAUNOM;
    }
    
    //METHODS
    /**
     * Ouvre un pichier texte et stocke toute ses lignes dans un tableau
     * @param path chemin pour acceder au pichier .txt
     * @return
     */
    public static String[] txtToString(String path){
        try{
                //Open file
                FileReader filereader = new FileReader(path);
                BufferedReader reader = new BufferedReader(filereader);

                //La première ligne indique le nombre de ligne à insérer dans le tableau
                String line ;
                line=reader.readLine();
                int length = Integer.parseInt(line);
                String[] tab = new String[length];

                //On lit
                int i=0;
                while((line=reader.readLine())!=null){ //Assignation of ligne in the while statement
                    tab[i]=line;
                    i++;
                }
            return tab;
            }
        catch(FileNotFoundException e){
            System.out.println("Il semble y avoir un problème lors de l'ouverture, tout le monde s'appellera donc John Smith");
            String[] tab = new String[1];
            tab[0] = "John Smith";
            return tab;
        }
        catch(IOException e){
            System.out.println("Veuillez vérifier le contenue de name. Convention : "); //dans le cas de notre projet uniquement
            System.out.println("1ere ligne -> nombre de noms à prendre dans le fichier");
            System.out.println("2eme et plus -> un nom par ligne");
            return null;
        }
    }
}


