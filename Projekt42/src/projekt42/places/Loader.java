package projekt42.places;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import projekt42.Gegenstand;
import projekt42.Projekt42;

/**
 *
 * @author larcado
 */
public class Loader {
    
    private BufferedReader br;
    
    String name="";
    
    public Loader(String filename){
        InputStreamReader isr = new InputStreamReader(Loader.class.getResourceAsStream(filename));
        br = new BufferedReader(isr);
    }
    
    public void setPlace(){
        try {
            for(String line = br.readLine();line!=null;line = br.readLine()){
                String[] splited = line.split(":");
                switch(splited[0]){
                    case "name":
                        name=splited[1];
                        break;
                    case "bgImg":
                        Projekt42.background.setBgImage(new Image(Projekt42.class.getResource(splited[1]).toString()));
                        break;
                    case "textbox":
                        int lenght = Integer.parseInt(splited[1]);
                        String[] textbox = new String[lenght];
                        
                        for(int i=0 ; i<lenght ; i++){
                            textbox[i]=br.readLine();
                        }
                        
                        Projekt42.textBox.clear();
                        Projekt42.textBox.addTextList(textbox);
                        break;
                    case "gegenstand"://1=name | 2=xPos in % | 3=yPos in % | 4=größe in %
                        Gegenstand g = Gegenstand.valueOf(splited[1]);
                        ImageView imgView = g.getImageView("keyTestScene");
                        
                        double x = Integer.parseInt(splited[2])-50.0;
                        double y = Integer.parseInt(splited[3])-50.0;
                        
                        double size = (Projekt42.gameSize.width/100.0)*Integer.parseInt(splited[4]);
                        imgView.setFitWidth(size);
                        imgView.setFitHeight(size);
                        
                        imgView.setTranslateX((Projekt42.gameSize.width/100.0)*x);
                        imgView.setTranslateY((Projekt42.gameSize.height/100.0)*y);
                        
                        Projekt42.root.getChildren().add(imgView);
                        
                        Label tooltip = new Label();
                        tooltip.setText(g.getToolTip());
                        tooltip.setLabelFor(imgView);
                        tooltip.setTranslateX((Projekt42.gameSize.width/100.0)*x);
                        tooltip.setTranslateY((Projekt42.gameSize.height/100.0)*y-imgView.getFitHeight()/2.0);
                        tooltip.setStyle("-fx-text-fill:goldenrod;"
                                        + "-fx-font-size: 20;"
                                        + "-fx-font-family: 'monotype corsiva','mistral','serif'");
                        tooltip.setVisible(false);
                        Projekt42.root.getChildren().add(tooltip);

                        imgView.setOnMouseEntered((MouseEvent e) ->{
                            if(imgView.isVisible())
                                tooltip.setVisible(true);
                        });
                        imgView.setOnMouseExited((MouseEvent e) ->{
                            if(imgView.isVisible())
                                tooltip.setVisible(false);
                        });
                        /* imgView.setOnMouseClicked((MouseEvent e) ->{
                        imgView.setVisible(false);
                        tooltip.setVisible(false);
                        Projekt42.textBox.displayTextNow("Du hast ein "+g.getName()+" eingesammelt.");
                        });*/
                        break;
                    default:
                        break;
                }
            }
        }catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
