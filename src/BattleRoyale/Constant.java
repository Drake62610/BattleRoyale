package BattleRoyale;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Projet JAVA Semestre1 M1
 * @author MATTE FLORIAN, MARISSAL LOIC
 */
public class Constant {
    
    //Constante pour génération du Terrain

    /**
     *constante de largeur de la carte
     */
    public static final int LARGEUR=12;

    /**
     *constante de longueur de la carte
     */
    public static final int LONGUEUR=50;

    /**
     *constante pour fixer la resolution de l'affichage de la carte
     */
    public static final int ZOOM_RESO = 25;

    /**
     *
     */
    public static final String NAMETXTPATH = new java.io.File("").getAbsolutePath() + "\\src\\name.txt";

    /**
     *
     */
    public static final String [] TABLEAUNOM = txtToString(NAMETXTPATH) ;


    /**
     *constante pour le soin maximum
     */
    public static final int MAXSOIN =4;

    /**
     *constante pour le soin minimum
     */
    public static final int MINSOIN = 1;
            
    //Get .txt file to have more random fun

    /**
     *
     * @param path
     * @return
     */
public static String[] txtToString(String path){
    try{
            //Open file
            FileReader filereader = new FileReader(path);
            BufferedReader reader = new BufferedReader(filereader);

            //First line treated before the while in order how many name there is in the .txt
            String line ;
            line=reader.readLine();
            int length = Integer.parseInt(line);
            String[] tab = new String[length];

            //Start general treatment
            int i=0;
            while((line=reader.readLine())!=null){ //Assignation of ligne in the while statement
                tab[i]=line;
                i++;
            }
        return tab;
        }
    catch(Exception e){
        System.out.println(e);
        return null;
    }
}

    /**
     *getter pour le nom du tableau
     * @return
     */
    public String[] getTABLEAUNOM() {
        return TABLEAUNOM;
    }
}


