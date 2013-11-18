/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt42;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author namibj
 */
public enum Gegenstand {

    /**
     *Ein Demo-Dings, nur um die Struktur für das Einfügen zu zeigen.
     */
    KOERPER_1("Körper","Ein Körper.",""),

    /**
     *Noch nen Demo-Dings.
     */
    LEICHE_1("","",""),

    /**
     *Das dritte Demo-Dings.
     */
    SCHLUESSEL_1("Schlüssel","Ein Schlüssel.", "key.png");
    Image defaultImage;
    String name,tip;
    Gegenstand(String Name, String tooltip, String pathDefaultImg){
        defaultImage = new Image(Gegenstand.class.getResource("images/"+pathDefaultImg).toString(), false);
        tip=tooltip;
        name=Name;
    }
    
    public ImageView getImageView(){
        return new ImageView(defaultImage);
    }
    
    public String getToolTip(){
        return tip;
    }
    
    public String getName(){
        return name;
    }
    
}