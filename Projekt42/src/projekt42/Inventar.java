package projekt42;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.GenericDoubleDouble;

/**
 *
 * @author larcado
 */
public class Inventar extends Group implements Serializable {

    ArrayList<InvItem> inventar = new ArrayList<>();
    ImageView gui, symbol;
    Group items = new Group();
    final Image inv = new Image(Inventar.class.getResource("images/beutel zu.png").toString());
    final Image invOpen = new Image(Inventar.class.getResource("images/beutel offen.png").toString());
    private Dimension gameSize;
    public boolean isInventarDrag = false;

    @SuppressWarnings("Unchecked")
    public Inventar() {
        gui = new ImageView(new Image(Inventar.class.getResource("images/Inventar.png").toString()));
        gameSize = Projekt42.gameSize;

        gui.setFitWidth(gameSize.width - gameSize.width / 5);
        gui.setFitHeight(gameSize.height - gameSize.height / 5);
        gui.setTranslateX(gameSize.width / 10);
        gui.setTranslateY(gameSize.height / 10);
        gui.setOpacity(0.98);
        this.getChildren().add(gui);
        this.getChildren().add(items);

        gui.setVisible(false);
        items.setVisible(false);

        symbol = new ImageView(inv);

        symbol.setFitWidth(gameSize.width / 12.0);
        symbol.setFitHeight(gameSize.width / 12.0);
        symbol.setTranslateX(gameSize.width - symbol.getFitWidth());
        symbol.setTranslateY(gameSize.height - symbol.getFitHeight());
        this.getChildren().add(symbol);

        symbol.setOnMouseReleased((MouseEvent e) -> {
            clicked();
        });

        symbol.setOnMouseDragEntered(event -> {
            if(!isInventarDrag){
                symbol.setImage(invOpen);
                event.consume();
            }else{
                gui.setVisible(!gui.isVisible());
                if(!gui.isVisible()){
                    setAllItemsVisible(false);
                    ((Node) event.getGestureSource()).setVisible(true);
                }else{
                    setAllItemsVisible(true);
                }
            }
        });

        symbol.setOnMouseDragExited(event -> {
            if(!isInventarDrag){
                symbol.setImage(inv);
                event.consume();
            }
        });

        symbol.setOnMouseEntered(event -> {
            if(!isInventarDrag){
                symbol.setImage(invOpen);
            }
        });
        symbol.setOnMouseExited(event -> {
            if(!isInventarDrag){
                symbol.setImage(inv);
            }
        });

        symbol.setOnMouseDragReleased(event -> {
            if(!isInventarDrag){
                addGegenstand(((GenericDoubleDouble<Gegenstand>) ((Node) event.getGestureSource()).getUserData()).getObj());
                Projekt42.Gegenstaende.getChildren().remove((Node) event.getGestureSource());
                System.out.println(inventar.toString());
                event.consume();
            }
        });
    }

    public void addGegenstand(Gegenstand g) {
        InvItem i = new InvItem(g, g.getImageView(g.name+"_Inv"));
        inventar.add(i);
        items.getChildren().add(i.imageView);
        refreshInventar();
    }

    public void entferneGegenstand(Gegenstand g) {
        //inventar.remove(g);
        refreshInventar();
    }

    public Gegenstand[] getAlleGegenstände() {
        Gegenstand[] alles = (Gegenstand[]) inventar.toArray();
        return alles;
    }

    public Gegenstand getFromIndex(int i) {
        return inventar.get(i).gegenstand;
    }

    private void clicked() {
        gui.setVisible(!gui.isVisible());
        items.setVisible(!items.isVisible());
    }
    
    private void refreshInventar(){
        for(InvItem item:inventar){
            item.imageView.setFitWidth(gui.getFitHeight()/7);
            item.imageView.setFitHeight(gui.getFitHeight()/7);
            item.imageView.setTranslateX(gui.getTranslateX()+item.imageView.getFitWidth()*2.3);
            item.imageView.setTranslateY(gui.getTranslateY()+item.imageView.getFitHeight());
        }
    }
    
    public void setAllItemsVisible(boolean vi){
        ImageView[] it = new ImageView[items.getChildren().size()];
        items.getChildren().toArray(it);
        
        for(ImageView iview:it){
            iview.setVisible(vi);
        }
    }

    private class InvItem {

        public Gegenstand gegenstand;
        public ImageView imageView;

        public InvItem(Gegenstand g, ImageView i) {
            gegenstand = g;
            imageView = i;
        }
    }
}
