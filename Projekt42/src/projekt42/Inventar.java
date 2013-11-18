package projekt42;

import java.util.ArrayList;

/**
 *
 * @author larcado
 */
public class Inventar {
    
    ArrayList<Gegenstand> inventar = new ArrayList<>();
    
    public Inventar(){
        
    }
    
    public void addGegenstand(Gegenstand g){
        inventar.add(g);
    }
    
    public void entferneGegenstand(Gegenstand g){
        inventar.remove(g);
    }
    
    public Gegenstand[] getAlleGegenstände(){
        Gegenstand[] alles = (Gegenstand[]) inventar.toArray();
        return alles;
    }
}
