/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt42;

import java.util.HashMap;
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
                imgView.setOnDragDetected((event) -> imgView.startFullDrag());
                imgView.setOnMousePressed(new javafx.event.EventHandler<MouseEvent>() {

                    public void handle(MouseEvent event) {
                        imgView.translateXProperty().bind(Projekt42.mouseX.subtract(Projekt42.root.sceneToLocal(
                                event.getSceneX(), event.getSceneY()).getX()));
                        imgView.translateYProperty().bind(Projekt42.mouseY.subtract(Projekt42.root.sceneToLocal(
                                event.getSceneX(), event.getSceneY()).getY()));
                    }
                });
                imgView.setOnMouseReleased(new javafx.event.EventHandler<MouseEvent>() {

                    public void handle(MouseEvent event) {
                        imgView.translateXProperty().unbind();
                        imgView.translateYProperty().unbind();
                        imgView.translateXProperty().set(0);
                        imgView.translateYProperty().set(0);
                    }
                });
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
