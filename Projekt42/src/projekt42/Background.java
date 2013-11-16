package projekt42;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author larcado
 */
public class Background extends ImageView{
    
    private double width,height;
    
    public Background(double pWidth,double pHeight,Image img){
        width=pWidth;
        height=pHeight;
        
        setBgImage(img);
    }
    
    public void setBgImage(Image img){
        this.setImage(img);
        this.setFitWidth(width);
        this.setFitHeight(height);
        
    }
}
