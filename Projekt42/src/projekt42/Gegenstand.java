/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt42;

import java.util.HashMap;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.GenericDoubleDouble;

/**
 *
 * @author namibj , larcado
 */
public enum Gegenstand {

    KNOCHEN("raum_1/Knochen", "Ein Knochen.", false,
            new String[]{"Der ist heiß", "Ich sollte ihn nicht anfassen", "Ich brauche etwas um ihn aus dem Feuer zu holen"},
            "raum_1/Knochen", "raum_1/Knochen_Inv"),

    TUCH("raum_1/Tuch", "Ein Tuch.", true,
            new String[]{"Ein Tuch, das könnte nützlich sein"},
            "raum_1/Tuch", "raum_1/Tuch_Inv"),
    
    TÜR("raum_1/Tür", "Eine Tür.", false,
            new String[]{"Die Tür ist verschlossen"},
            "raum_1/Tür"),

    SCHLUESSEL_1("Schlüssel", "Ein Schlüssel.", true,
            new String[]{"Ein goldener Schlüssel..."},
            "keyInv", "keyTestScene", "keyEbene");
    
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
        imgView = new ImageView(Images.get(key));
        if (key.endsWith("Inv")) {
            imgView.setOnDragDetected((event) -> imgView.startFullDrag());
            imgView.setOnMousePressed((MouseEvent event) -> {
                double x = event.getSceneX() - Projekt42.root.sceneToLocal(event.getSceneX(), event.getSceneY()).getX() + imgView.getFitWidth()/2;
                double y = event.getSceneY() - Projekt42.root.sceneToLocal(event.getSceneX(), event.getSceneY()).getY() + imgView.getFitHeight()/2;
                imgView.setUserData(new GenericDoubleDouble<>(this, x, y));
                imgView.setMouseTransparent(true);
                Projekt42.inventar.isInventarDrag = true;
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
                
                Projekt42.inventar.isInventarDrag = false;
                Projekt42.inventar.setAllItemsVisible(true);
                if(!Projekt42.inventar.gui.isVisible()){
                    Projekt42.inventar.items.setVisible(false);
                }
            });
        } else {
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

            imgView.setOnMouseDragEntered(event -> {
                if(Projekt42.inventar.isInventarDrag){
                    imgView.setCache(true);
                    Bloom bloom = new Bloom();
                    bloom.setThreshold(0.1);
                    imgView.setEffect(bloom);
                    event.consume();
                }
            });

            imgView.setOnMouseDragExited(event -> {
                if(Projekt42.inventar.isInventarDrag){
                    imgView.setEffect(null);
                    event.consume();
                }
            });
            
            imgView.setOnMouseDragReleased(event -> {
                if(Projekt42.inventar.isInventarDrag){
                    Gegenstand g = ((GenericDoubleDouble<Gegenstand>) ((Node) event.getGestureSource()).getUserData()).getObj();
                    imgView.setEffect(null);
                    boolean match = false;
                    for(GegenstandsKombination kombi:GegenstandsKombination.values()){
                        if(kombi.getSource().equals(g)){
                            if(kombi.getTarget().equals(this)){
                                kombi.doAction();
                                match  = true;
                            }
                        }
                    }
                    if(!match){
                        Projekt42.textBox.addText("Dies hat keine Wirkung hier...");
                    }
                }
                event.consume();
            });

            imgView.setOnMouseEntered(event -> {
                if(Projekt42.inventar.isInventarDrag){
                    
                }
            });
            imgView.setOnMouseExited(event -> {
                if(Projekt42.inventar.isInventarDrag){
                    
                }
            });
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
