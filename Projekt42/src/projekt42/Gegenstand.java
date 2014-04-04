/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt42;

import java.util.HashMap;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.GenericDoubleDouble;

/**
 *
 * @author namibj
 */
public enum Gegenstand {

    KNOCHEN("raum_1/Knochen", "Ein Knochen.", false, new String[]{"Der ist heiß", "Ich sollte ihn nicht anfassen", "Ich brauche etwas um ihn aus dem Feuer zu holen"}, "raum_1/Knochen", "raum_1/Knochen_Inv"),

    TUCH("raum_1/Tuch", "Ein Tuch.", true, new String[]{"Ein Tuch, das könnte nützlich sein"}, "raum_1/Tuch", "raum_1/Tuch_Inv"),
    
    TÜR("raum_1/Tür", "Eine Tür.", false, new String[]{"Die Tür ist verschlossen"}, "raum_1/Tür"),

    SCHLUESSEL_1("Schlüssel", "Ein Schlüssel.", true, new String[]{"Ein goldener Schlüssel..."}, "keyInv", "keyTestScene", "keyEbene");
    Random rand = new Random();
    Image[] roomImages;
    HashMap<String, Image> Images;
    String name, tip, firstImageName;
    String[] textboxinfo;
    private boolean toTakeAway;

    Gegenstand(String Name, String tooltip, boolean ToTakeAway, String[] tboxInfo, String... pImages) {
        firstImageName = pImages[0];
        textboxinfo = tboxInfo;
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
            imgView.setOnMousePressed((MouseEvent event) -> {
                double x = event.getSceneX() - Projekt42.root.sceneToLocal(event.getSceneX(), event.getSceneY()).getX();
                double y = event.getSceneY() - Projekt42.root.sceneToLocal(event.getSceneX(), event.getSceneY()).getY();
                imgView.setUserData(new GenericDoubleDouble<>(this, x, y));
                imgView.setMouseTransparent(true);
            });
            imgView.setOnMouseDragged(event -> {
                imgView.setTranslateX(event.getSceneX() - ((GenericDoubleDouble<Gegenstand>) imgView.getUserData()).getI());
                imgView.setTranslateY(event.getSceneY() - ((GenericDoubleDouble<Gegenstand>) imgView.getUserData()).getJ());
            });
            imgView.setOnMouseReleased(event -> {
                imgView.setTranslateX(event.getSceneX() - ((GenericDoubleDouble<Gegenstand>) imgView.getUserData()).getI());
                imgView.setTranslateY(event.getSceneY() - ((GenericDoubleDouble<Gegenstand>) imgView.getUserData()).getJ());
                imgView.setUserData(null);
                imgView.setOpacity(1);
                imgView.setMouseTransparent(false);
            });
        } else {
            imgView = new ImageView(Images.get(key));
            imgView.setOnDragDetected(event -> imgView.startFullDrag());
            imgView.setOnMousePressed((MouseEvent event) -> {
                if(toTakeAway){
                    double x = Projekt42.root.sceneToLocal(event.getSceneX(), event.getSceneY()).getX();
                    double y = Projekt42.root.sceneToLocal(event.getSceneX(), event.getSceneY()).getY();
                    imgView.setUserData(new GenericDoubleDouble<>(this, x, y));
                    imgView.setMouseTransparent(true);
                }
            });
            imgView.setOnMouseDragged(event -> {
                if(toTakeAway){
                    imgView.setTranslateX(event.getSceneX() - ((GenericDoubleDouble<Gegenstand>) imgView.getUserData()).getI());
                    imgView.setTranslateY(event.getSceneY() - ((GenericDoubleDouble<Gegenstand>) imgView.getUserData()).getJ());
                }
            });
            imgView.setOnMouseReleased(event -> {
                Projekt42.textBox.clear();
                Projekt42.textBox.addText(textboxinfo[rand.nextInt(textboxinfo.length)]);
                if(toTakeAway){
                    imgView.setUserData(null);
                    imgView.setTranslateX(0);
                    imgView.setTranslateY(0);
                    imgView.setOpacity(1);
                    imgView.setMouseTransparent(false);
                }
            });
            /*
            imgView.setOnMouseDragEntered(event -> {
                //TODO: Add code to support higlighting of Objects when dragging other ones over.
            });
            imgView.setOnMouseDragExited(event -> {
                //TODO: Add code to handle the end of animations/highlighting of this after dragging Stuff over it.
            });
            imgView.setOnMouseDragReleased(event -> {
                //TODO: Add code to support dragging Stuff onto this Object.
            });*/
        }
        return imgView;
    }

    public String getToolTip() {
        return tip;
    }

    public String getName() {
        return name;
    }
    
    public String getFirstImageName() {
        return firstImageName;
    }
    
    public void enableToTakeAway(){
        toTakeAway = true;
    }
}
