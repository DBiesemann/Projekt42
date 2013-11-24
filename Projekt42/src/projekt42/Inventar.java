package projekt42;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 *
 * @author larcado
 */
public class Inventar extends Group implements Serializable{
    
    ArrayList<Gegenstand> inventar = new ArrayList<>();
    ImageView gui, symbol;
    final Image inv = new Image(Inventar.class.getResource("images/inv.png").toString());
    final Image invOpen = new Image(Inventar.class.getResource("images/invOpen.png").toString());
    
    public Inventar(){
        gui = new ImageView(new Image(Inventar.class.getResource("images/invGUI.png").toString()));
        Dimension gameSize = Projekt42.gameSize;

        gui.setFitWidth(gameSize.width-gameSize.width/5);
        gui.setFitHeight(gameSize.height-gameSize.height/5);
        gui.setTranslateX(gameSize.width/10);
        gui.setTranslateY(gameSize.height/10);
        this.getChildren().add(gui);
        
        gui.setVisible(false);
        
        symbol = new ImageView(inv);

        symbol.setFitWidth(gameSize.width/12.0);
        symbol.setFitHeight(gameSize.width/12.0);
        symbol.setTranslateX(gameSize.width-symbol.getFitWidth());
        symbol.setTranslateY(gameSize.height-symbol.getFitHeight());
        this.getChildren().add(symbol);
        
        symbol.setOnMouseReleased((MouseEvent e) ->{
            gui.setVisible(!gui.isVisible());
        });
        
        symbol.setOnDragOver((DragEvent event) -> {
            if (event.getDragboard().hasContent(DataFormat.RTF)) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
       });

        
       symbol.setOnDragEntered((DragEvent event) -> {
            symbol.setImage(invOpen);
            event.consume();
       });
       

       
       symbol.setOnDragExited((DragEvent event) -> {
            symbol.setImage(inv);
            event.consume();
       });
       
      
       symbol.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasContent(DataFormat.RTF)) {
                inventar.add((Gegenstand) db.getContent(DataFormat.RTF));
                success = true;
                System.out.println(inventar.toString());
            }
            event.setDropCompleted(success);

            event.consume();
       });
    }
    
    public void addGegenstand(Gegenstand g){
        inventar.add(g);
    }
    
    public void entferneGegenstand(Gegenstand g){
        inventar.remove(g);
    }
    
    public Gegenstand[] getAlleGegenstände(){
        Gegenstand[] alles = (Gegenstand[]) inventar.toArray();
        return alles;
    }
    
    public Gegenstand getFromIndex(int i){
        return inventar.get(i);
    }
}
