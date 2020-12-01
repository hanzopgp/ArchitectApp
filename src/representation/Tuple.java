package representation;

/**
 * Classe qui correspond à un couple de deux valeurs
 */
public class Tuple{

    public Object x,y;

    public Tuple(Object x, Object y){
        this.x = x;
        this.y = y;
    }

    public Object getX(){
        return this.x;
    }

    public Object getY(){
        return this.y;
    }

    @Override
    public String toString(){
        return "Tuple : (" + x + "," + y + ")";
    }

}
