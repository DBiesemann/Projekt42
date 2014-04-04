/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt42.places;

import javafx.scene.image.Image;
import projekt42.Gegenstand;

/**
 *
 * @author Tobias
 */
public class Raum_1 extends Raum{
    
    public Raum_1(){
        background = new Image(projekt42.Projekt42.class.getResource("images/raum_1/Raum_1.png").toString());
        gegenstände = new Gegenstand[3];
        gegenstände[0] = Gegenstand.KNOCHEN;
        gegenstände[1] = Gegenstand.TUCH;
        gegenstände[2] = Gegenstand.TÜR;
        
        textbox = null;
        
    }
}
