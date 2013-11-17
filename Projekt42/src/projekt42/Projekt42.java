/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt42;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import projekt42.places.Loader;

/**
 *
 * @author namibj , larcado
 */
public class Projekt42 extends Application {
    
    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    StackPane root = new StackPane();
    Scene scene = new Scene(root, 800, 600);
    public static Background background;
    public static TextBox textBox;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setFullScreen(true);
        
        background = new Background(screenSize.getWidth(),screenSize.getHeight(),new Image(Projekt42.class.getResource("images/start.png").toString()));
        root.getChildren().add(background);
        
        textBox = new TextBox(screenSize.getWidth()-100,screenSize.getHeight()/7);
        textBox.setTranslateY((screenSize.getHeight()-textBox.height)/2.0);
        root.getChildren().add(textBox);
        
        textBox.addTextList(new String[]{"Das Spiel startet in 10s"});
        
        scene.setCursor(new ImageCursor(new Image(Projekt42.class.getResource("images/maus.png").toString())));
        
        primaryStage.setTitle("Projekt 42");
        primaryStage.setScene(scene);
        primaryStage.show();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Projekt42.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setPlace("firstRoom.dat");
    }
    
    private void setPlace(String f){
        Loader loader = new Loader(f);
        loader.setPlace();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
