/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt42;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
    KOERPER_1("Körper", "Ein Körper.", false),
    /**
     * Noch nen Demo-Dings.
     */
    LEICHE_1("", "", false),
    /**
     * Das dritte Demo-Dings.
     */
    SCHLUESSEL_1("Schlüssel", "Ein Schlüssel.", true, "keyInv", "keyTestScene", "keyEbene");
    Image defaultImage;
    Image[] roomImages;
    HashMap<String, Image> Images;
    String name, tip;
    private final boolean toTakeAway;

    Gegenstand(String Name, String tooltip/*, String pathDefaultImg*/, boolean ToTakeAway, String... pImages) {
        Images = new HashMap<>(pImages.length, 1.0f);
        for (String cur : pImages) {
            Images.put(cur,
                    new Image(Gegenstand.class.getResource("images/" + cur + ".png").toString(), false));
        }
        tip = tooltip;
        name = Name;
        toTakeAway = ToTakeAway;

    }

    public ImageView getImageView(String key) {
        ImageView imgView;
        if (key.endsWith("Inv")) {
            imgView = new ImageView(Images.get(key));
            imgView.setOnDragDetected((event) -> imgView.startFullDrag());
            imgView.setOnMouseDragged((event) -> {
                imgView.translateXProperty().set(event.getSceneX());
                imgView.translateYProperty().set(event.getSceneY());
            });
        } else {
            imgView = new ImageView(Images.get(key));
            if (toTakeAway) {
                imgView.setOnMouseDragged((MouseEvent event) -> {
                    imgView.setTranslateX(event.getSceneX() - Projekt42.gameSize.width*0.84);
                    imgView.setTranslateY(event.getSceneY() - Projekt42.gameSize.height*0.675);
                });
                /*
                imgView.setOnDragDetected((event) -> imgView.startFullDrag());
                imgView.setOnMouseMoved((MouseEvent event) -> {
                    if (Boolean.TRUE.equals(imgView.getUserData())) {
                        imgView.setTranslateX(event.getSceneX());
                        imgView.setTranslateY(event.getSceneY());
                    }
                    int x =1;
                    x++;
                });
                imgView.setOnMousePressed((MouseEvent event) -> {
                    imgView.setUserData(Boolean.TRUE);
                });
                imgView.setOnMouseReleased((MouseEvent event) -> {
                    imgView.setUserData(Boolean.FALSE);
                });*/
            }
        }
        return imgView;
    }

    public String getToolTip() {
        return tip;
    }

    public String getName() {
        return name;
    }

}
