/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt42;

import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

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
                
                /*
                imgView.setOnMouseDragged((MouseEvent event) -> {
                    imgView.setTranslateX(event.getSceneX() - Projekt42.gameSize.width*0.84);
                    imgView.setTranslateY(event.getSceneY() - Projekt42.gameSize.height*0.675);
                    imgView.setOpacity(0.5);
                });
                
                imgView.setOnMouseReleased((MouseEvent event) -> {
                    imgView.translateXProperty().unbind();
                    imgView.translateYProperty().unbind();
                    imgView.translateXProperty().set(0);
                    imgView.translateYProperty().set(0);
                    imgView.setOpacity(1);
                });*/
                
                imgView.setOnDragDetected((MouseEvent event) -> {
                    Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);

                    ClipboardContent content = new ClipboardContent();
                    content.put(DataFormat.RTF, this);
                    db.setContent(content);
                    
                    imgView.setOpacity(0.5);

                    event.consume();
                });
                
                imgView.setOnDragOver((DragEvent event) -> {
                    imgView.setTranslateX(event.getSceneX() - Projekt42.gameSize.width*0.84);
                    imgView.setTranslateY(event.getSceneY() - Projekt42.gameSize.height*0.675);
                });

                imgView.setOnDragDone((DragEvent event) -> {
                    if (event.getTransferMode() == TransferMode.MOVE) {
                        imgView.setVisible(false);
                    }else{
                        imgView.translateXProperty().unbind();
                        imgView.translateYProperty().unbind();
                        imgView.translateXProperty().set(0);
                        imgView.translateYProperty().set(0);
                        imgView.setOpacity(1);
                    }
                    event.consume();
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
