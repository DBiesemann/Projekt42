/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt42;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author namibj , larcado
 */
public class Projekt42 extends Application {
    static TextBox textBox;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setFullScreen(true);
        
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        StackPane root = new StackPane();
        
        Background background = new Background(screenSize.getWidth(),screenSize.getHeight(),new Image(Projekt42.class.getResource("images/dungeon2.jpg").toString()));
        root.getChildren().add(background);
        
        textBox = new TextBox(screenSize.getWidth()-100,screenSize.getHeight()/7);
        textBox.setTranslateY((screenSize.getHeight()-textBox.height)/2.0);
        root.getChildren().add(textBox);
        
        textBox.addTextList(new String[]{"Mit [Enter] kann man einen Text weiter kommen...","Dies ist ein etwas längerer Text als vorhin!","Dies ist eine langer String mit\neinem Escape dazwischen","Diese Schriftart heißt \"Mistral\""});
        
        Scene scene = new Scene(root, 800, 600);
        scene.setCursor(new ImageCursor(new Image(Projekt42.class.getResource("images/maus.png").toString())));
        
        primaryStage.setTitle("Projekt 42");
        primaryStage.setScene(scene);
        primaryStage.show();
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
