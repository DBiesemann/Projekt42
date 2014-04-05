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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projekt42.places.Loader;
import projekt42.places.*;

/**
 *
 * @author namibj , larcado
 */
public class Projekt42 extends Application {

    public static Dimension gameSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static Group root = new Group();
    Scene scene = new Scene(root, 800, 450, Color.BLACK);
    public static Background background;
    public static TextBox textBox;
    public static Inventar inventar;
    public static Group Gegenstaende = new Group();
    public static Loader loader = new Loader();
    static DoubleProperty mouseX = new SimpleDoubleProperty();
    static DoubleProperty mouseY = new SimpleDoubleProperty();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setFullScreen(true);

        background = new Background(gameSize.getWidth(), gameSize.getHeight(), new Image(Projekt42.class.getResource("images/start.png").toString()));
        root.getChildren().add(background);
        root.getChildren().add(Gegenstaende);

        textBox = new TextBox(gameSize.getWidth() - gameSize.getWidth() / 10.0, gameSize.getHeight() / 6.0);
        textBox.setTranslateX(gameSize.getWidth() / 80.0);
        textBox.setTranslateY(gameSize.getHeight() - textBox.height);
        textBox.setVisible(false);
        root.getChildren().add(textBox);

        inventar = new Inventar();
        inventar.symbol.setVisible(false);
        root.getChildren().add(inventar);

        //textBox.addTextList(new String[]{"Das Spiel startet in 2s"});

        scene.setCursor(new ImageCursor(new Image(Projekt42.class.getResource("images/maus.png").toString())));
        root.setOnMouseDragged((event) -> {
            mouseX.setValue(event.getSceneX());
            mouseY.setValue(event.getSceneY());
        });
        primaryStage.setTitle("Projekt 42");
        primaryStage.setScene(scene);
        primaryStage.show();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Projekt42.class.getName()).log(Level.SEVERE, null, ex);
        }
        inventar.symbol.setVisible(true);
        textBox.setVisible(true);
        textBox.clear();
        loader.setPlace(new Raum_1());
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
