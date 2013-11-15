package projekt42;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author larcado
 */
public class TextBox extends StackPane{
    
    private Label label = new Label();
    private TextArea ta = new TextArea();
    private utils.List<String> textQueue = new utils.List<>();
    public double width,height;
    
    public TextBox(double pWidth, double pHeight){        
        width=pWidth;
        height=pHeight;
        this.setPrefSize(width, height);
        this.setMaxSize(width, height);
        
        label.setStyle("-fx-text-fill:goldenrod;"
                     + "-fx-font-size: 50;"
                     + "-fx-font-family: 'mistral','serif'");
        
        this.setStyle("-fx-background-color: rgba(0,100,100,0.5);"
                    + "-fx-background-radius:25;");
        
                
        
        
        ta.setEditable(false);
        ta.setOpacity(0);
        ta.setOnKeyReleased((KeyEvent event) -> {
            System.out.println("event");
            if(event.getCode()==KeyCode.ENTER){
                System.out.println("enter");
                textQueue.entfernen(label.getText());
                update();
            }
        });
        
        this.getChildren().addAll(ta,label);
    }
    
    public void addText(String content){
        if(content!="" || content!=null)
            textQueue.hinzufügen(content);
        update();
    }
    
    public void addTextList(String[] textList){
        for(String str:textList){
            if(str!="" || str!=null)
                textQueue.hinzufügen(str);
        }
        update();
    }
    
    private void update(){
        if(textQueue.getFirst()!=null){
            label.setText(textQueue.getFirst().getContent());
        }else{
            label.setText("");
        }
    }
}
