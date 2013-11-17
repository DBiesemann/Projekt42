/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt42;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author namibj
 */
public enum Gegenstand {

    /**
     * Ein Demo-Dings, nur um die Struktur für das Einfügen zu zeigen.
     */
    KOERPER_1("Körper", "Ein Körper.", "", false),
    /**
     * Noch nen Demo-Dings.
     */
    LEICHE_1("", "", "", false),
    /**
     * Das dritte Demo-Dings.
     */
    SCHLUESSEL_1("Schlüssel", "Ein Schlüssel.", "key.png", true, "key");
    Image defaultImage;
    Image[] roomImages;
    String name, tip;
private final boolean toTakeAway;
    
    Gegenstand(String Name, String tooltip, String pathDefaultImg, boolean ToTakeAway, String... pathsRoomImages) {
        defaultImage = new Image(Gegenstand.class.getResource("images/" + pathDefaultImg).toString(), false);
        for (int i = 0; 1 < pathsRoomImages.length; i++) {
            roomImages[i] = new Image(Gegenstand.class.getResource("images/" + pathsRoomImages + i + ".png").toString(), false);
        }
        tip = tooltip;
        name = Name;
        toTakeAway = ToTakeAway;

    }

    public ImageView getImageView(int version) {
        ImageView imgView;
        if (version < 0) {
            imgView = new ImageView(defaultImage);
            imgView.setOnDragDetected((event) -> imgView.startFullDrag());
            imgView.setUserData(true);
        } else {
            imgView = new ImageView(roomImages[version]);
            if(toTakeAway)
                imgView.setOnDragDetected((event) -> imgView.startFullDrag());
            imgView.setUserData(true);
        }
        imgView.set
        return imgView;
    }

    public String getToolTip() {
        return tip;
    }

    public String getName() {
        return name;
    }

}
