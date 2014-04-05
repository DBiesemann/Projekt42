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
abstract class Raum {
    public abstract Image getBackground();
    public abstract Gegenstand[] getGegenstände();
    public abstract String[] getTextbox();
}
