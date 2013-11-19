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

/**
 *
 * @author namibj , larcado
 */
public class Projekt42 extends Application {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static Dimension gameSize;
    public static Group root = new Group();
    Scene scene = new Scene(root, 800, 450, Color.BLACK);
    public static Background background;
    public static TextBox textBox;
    static DoubleProperty mouseX = new SimpleDoubleProperty();
    static DoubleProperty mouseY = new SimpleDoubleProperty();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setFullScreen(true);

        int gameY = (int) ((screenSize.width * 9.0) / 16.0);
        gameSize = new Dimension(screenSize.width, gameY);

        //root.setBackground(javafx.scene.layout.Background.EMPTY);
        background = new Background(gameSize.getWidth(), gameSize.getHeight(), new Image(Projekt42.class.getResource("images/start.png").toString()));
        root.getChildren().add(background);

        textBox = new TextBox(gameSize.getWidth() - 100, gameSize.getHeight() / 6);
        textBox.setTranslateX(gameSize.getWidth() / 2.0 - textBox.width / 2.0);
        textBox.setTranslateY(gameSize.getHeight() - textBox.height);
        root.getChildren().add(textBox);

        textBox.addTextList(new String[]{"Das Spiel startet in 2s"});

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

        setPlace("firstRoom.dat");
    }

    private void setPlace(String f) {
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
