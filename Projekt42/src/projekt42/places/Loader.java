package projekt42.places;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import projekt42.Gegenstand;
import projekt42.Projekt42;

/**
 *
 * @author larcado
 */
public class Loader {

    public void setPlace(Raum raum) {
        Projekt42.background.setBgImage(raum.getBackground());
        Projekt42.Gegenstaende.getChildren().clear();
        if(raum.getGegenstände()!=null){
            for(Gegenstand g:raum.getGegenstände()){
                ImageView imgView = g.getImageView(g.getFirstImageName());

                imgView.setFitWidth(Projekt42.gameSize.width);
                imgView.setFitHeight(Projekt42.gameSize.height);

                Projekt42.Gegenstaende.getChildren().add(imgView);

                Label tooltip = new Label();
                tooltip.setText(g.getToolTip());
                tooltip.setLabelFor(imgView);

                tooltip.setStyle("-fx-text-fill:goldenrod;"
                        + "-fx-font-size: 20;"
                        + "-fx-font-family: 'monotype corsiva','mistral','serif'");
                tooltip.setVisible(false);
                Projekt42.Gegenstaende.getChildren().add(tooltip);

                imgView.setOnMouseEntered((e) -> {
                    if (imgView.isVisible()) {
                        tooltip.setVisible(true);
                    }
                    tooltip.setTranslateX(e.getSceneX() - 20);
                    tooltip.setTranslateY(e.getSceneY() - 80);
                });
                imgView.setOnMouseMoved((e) -> {
                    tooltip.setTranslateX(e.getSceneX() - 20);
                    tooltip.setTranslateY(e.getSceneY() - 80);
                });
                imgView.setOnMouseExited((e) -> {
                    if (imgView.isVisible()) {
                        tooltip.setVisible(false);
                    }
                });
            }
        }
        if(raum.getTextbox()!=null){
            Projekt42.textBox.clear();
            Projekt42.textBox.addTextList(raum.getTextbox());
        }
    }
}
