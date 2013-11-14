/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt42;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author namibj
 */
public class Projekt42 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setFullScreen(true);
        
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        StackPane root = new StackPane();
        
        Rectangle background = new Rectangle(screenSize.getWidth(),screenSize.getHeight(),Color.BLUE);
        root.getChildren().add(background);
        
        Rectangle textBox = new Rectangle(screenSize.getWidth()-100,screenSize.getHeight()/7,Color.INDIGO);
        textBox.setTranslateY((screenSize.getHeight()-textBox.getHeight())/2.0-10.0);
        root.getChildren().add(textBox);
        
        Scene scene = new Scene(root, 800, 600);
        
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
