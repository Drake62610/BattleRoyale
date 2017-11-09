
/**
 * DATE 09/11/17
 * @author MATTE Florian
 */
public class Soigneur extends Personnage{
    private int capacité;

    //CONSTRUCTOR
    /**
     * Initialisation de la capacité en fonctione de la capacitée minimale et maximale
     * @param position_x sur la Carte
     * @param position_y sur la Carte
     */
    public Soigneur( int position_x, int position_y) {
        super(position_x,position_y);
        this.capacité = Constant.MINSOIN + (int)(Math.random()*(Constant.MAXSOIN));
    }
    
    //GETTER
    public int getCapacité() {
        return capacité;
    }
    
    
    //METHODS
    //Se soigner de sa capacitée de soin
    //On peut soigner un ami ou soi-même
    void soigner(Personnage Ami){
        if (Ami.getPVMAX()-Ami.getPv()>=capacité){
            Ami.setPv(Ami.getPv()+capacité);
            System.out.println(this.getName() + " a soigné "+Ami.getName()+" de "+ capacité + " !");
        }
        else{
            System.out.println(this.getName() + " a soigné "+Ami.getName()+" de "+ Integer.toString(Ami.getPVMAX()-Ami.getPv()) + " !");
            Ami.setPv(Ami.getPVMAX());
        }
    }
    
    void soigner(){
        if (this.getPVMAX()-this.getPv()>=capacité){
        System.out.println(this.getName() + " se soigne de "+ capacité + " ! ");
        this.setPv(this.getPv() + capacité);
        }
        else{
            
        }
            
    }
}
