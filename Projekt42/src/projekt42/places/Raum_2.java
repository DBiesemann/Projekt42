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
public class Raum_2 extends Raum{

    @Override
    public Image getBackground() {
        return new Image(projekt42.Projekt42.class.getResource("images/dungeon finished.jpg").toString());
    }

    @Override
    public Gegenstand[] getGegenstände() {
        return new Gegenstand[]{Gegenstand.TÜR_VERSCHLOSSEN, Gegenstand.TÜR_OFFEN, Gegenstand.ALIEN};
    }

    @Override
    public String[] getTextbox() {
        return new String[]{"Raum 2"};
    }
}
