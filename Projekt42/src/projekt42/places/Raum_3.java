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
 * @author larcado
 */
public class Raum_3 extends Raum{

    @Override
    public Image getBackground() {
        return new Image(projekt42.Projekt42.class.getResource("images/raum_3/Raum_3.png").toString());
    }

    @Override
    public Gegenstand[] getGegenstände() {
        return null;
    }

    @Override
    public String[] getTextbox() {
        return null;
    }
}
