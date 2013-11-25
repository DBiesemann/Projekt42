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
import utils.GenericDoubleDouble;

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

    @SuppressWarnings("Unchecked")
    public ImageView getImageView(String key) {
        ImageView imgView;
        if (key.endsWith("Inv")) {
            imgView = new ImageView(Images.get(key));
            imgView.setOnDragDetected((event) -> imgView.startFullDrag());
            imgView.setOnMouseDragged((event) -> {
                imgView.setTranslateX(event.getSceneX());
                imgView.setTranslateY(event.getSceneY());
            });
        } else {
            imgView = new ImageView(Images.get(key));
            if (toTakeAway) {
                imgView.setOnDragDetected(event -> imgView.startFullDrag());
                imgView.setOnMousePressed((MouseEvent event) -> {
                    double x = Projekt42.root.sceneToLocal(event.getSceneX(), event.getSceneY()).getX();
                    double y = Projekt42.root.sceneToLocal(event.getSceneX(), event.getSceneY()).getY();
                    imgView.setUserData(new GenericDoubleDouble<>(this, x, y));
                    imgView.setMouseTransparent(true);
                });
                imgView.setOnMouseDragged(event -> {
                    imgView.setTranslateX(event.getSceneX() - ((GenericDoubleDouble<Gegenstand>) imgView.getUserData()).getI());
                    imgView.setTranslateY(event.getSceneY() - ((GenericDoubleDouble<Gegenstand>) imgView.getUserData()).getJ());
                });
                imgView.setOnMouseReleased(event -> {
                    imgView.setUserData(null);
                    imgView.setTranslateX(0);
                    imgView.setTranslateY(0);
                    imgView.setOpacity(1);
                    imgView.setMouseTransparent(false);
                });
                imgView.setOnMouseDragEntered(event -> {
                    //TODO: Add code to support higlighting of Objects when dragging other ones over.
                });
                imgView.setOnMouseDragExited(event -> {
                    //TODO: Add code to handle the end of animations/highlighting of this after dragging Stuff over it.
                });
                imgView.setOnMouseDragReleased(event -> {
                    //TODO: Add code to support dragging Stuff onto this Object.
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
