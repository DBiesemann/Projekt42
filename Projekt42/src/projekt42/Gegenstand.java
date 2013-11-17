/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt42;

import javafx.scene.image.Image;

/**
 *
 * @author namibj
 */
public enum Gegenstand {

    /**
     *Ein Demo-Dings, nur um die Struktur für das Einfügen zu zeigen.
     */
    KOERPER_1("Körper", ""),

    /**
     *Noch nen Demo-Dings.
     */
    LEICHE_1("",""),

    /**
     *Das dritte Demo-Dings.
     */
    SCHLUESSEL_1("Ein Schlüssel.", "schluessel1");
    Image defaultImage;
    Gegenstand(String Name, String pathDefaultImg){
        defaultImage = new Image("projekt42/images/" + pathDefaultImg, false);
    }
    
}
