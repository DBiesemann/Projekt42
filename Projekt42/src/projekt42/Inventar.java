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
    final Image inv = new Image(Inventar.class.getResource("images/inv.png").toString());
    final Image invOpen = new Image(Inventar.class.getResource("images/invOpen.png").toString());

    public Inventar() {
        gui = new ImageView(new Image(Inventar.class.getResource("images/invGUI.png").toString()));
        Dimension gameSize = Projekt42.gameSize;

        gui.setFitWidth(gameSize.width - gameSize.width / 5);
        gui.setFitHeight(gameSize.height - gameSize.height / 5);
        gui.setTranslateX(gameSize.width / 10);
        gui.setTranslateY(gameSize.height / 10);
        this.getChildren().add(gui);

        gui.setVisible(false);

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
            symbol.setImage(invOpen);
            event.consume();
        });

        symbol.setOnDragExited(event -> {
            symbol.setImage(inv);
            event.consume();
        });

        symbol.setOnMouseDragReleased(event -> {
            addGegenstand(((GenericDoubleDouble<Gegenstand>)((Node) event.getGestureSource()).getUserData()).getObj());
            //Projekt42.root.   //TODO: Mach mal Tobi.
            System.out.println(inventar.toString());
            event.consume();
        });
    }

    public void addGegenstand(Gegenstand g) {
        InvItem i = new InvItem(g, g.getImageView("Inv"));
        inventar.add(i);
        this.getChildren().add(i.imageView);
    }

    public void entferneGegenstand(Gegenstand g) {
        //inventar.remove(g);
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
        for (InvItem it : inventar) {
            it.imageView.setVisible(!it.imageView.isVisible());
        }
    }

    private class InvItem {

        public Gegenstand gegenstand;
        public ImageView imageView;

        public InvItem(Gegenstand g, ImageView i) {
            gegenstand = g;
            imageView = i;
            imageView.setVisible(false);
        }
    }
}
