package projekt42.places;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

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
                        projekt42.Projekt42.background.setBgImage(new Image(projekt42.Projekt42.class.getResource(splited[1]).toString()));
                        break;
                    case "textbox":
                        int lenght = Integer.parseInt(splited[1]);
                        String[] textbox = new String[lenght];
                        
                        for(int i=0 ; i<lenght ; i++){
                            textbox[i]=br.readLine();
                        }
                        
                        projekt42.Projekt42.textBox.clear();
                        projekt42.Projekt42.textBox.addTextList(textbox);
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
