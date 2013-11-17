package projekt42;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

/**
 *
 * @author larcado
 */
public class TextBox extends StackPane{
    
    private Label label = new Label();
    private TextArea ta = new TextArea();
    private utils.List<String> textQueue = new utils.List<>();
    public double width,height;
    private int maxZeichen,maxZeilen,fontSize;
    
    public TextBox(double pWidth, double pHeight){        
        width=pWidth;
        height=pHeight;
        this.setPrefSize(width, height);
        this.setMaxSize(width, height);
        
        fontSize=(int)(height/3.6);
        
        maxZeichen=(int)(width/(fontSize/2.5));
        maxZeilen=(int)(height/fontSize/1.8);
        
        //maxZeichen=(int)(width/18.0);
        //maxZeilen=(int)(height/75.0);
        
        label.setStyle("-fx-text-fill:goldenrod;"
                     + "-fx-font-size: "+fontSize+";"
                     + "-fx-font-family: 'monotype corsiva','mistral','serif'");
        
        this.setStyle("-fx-background-color: rgba(0,100,100,0.5);"
                    + "-fx-background-radius:"+(int)(width/height)+";");
        
                
        
        
        ta.setEditable(false);
        ta.setOpacity(0);
        ta.setOnKeyReleased((KeyEvent event) -> {
            if(event.getCode()==KeyCode.ENTER){
                textQueue.entferneErstesElement();
                update();
            }
        });
        
        this.getChildren().addAll(ta,label);
    }
    
    public void displayTextNow(String content){
        if(content!="" || content!=null)
            for(String str:trimString(content)){
                if(str!="" || str!=null)
                    textQueue.vorneAnhängen(str);
            }
        update();
    }
    
    public void addText(String content){
        if(content!="" || content!=null)
            addTextArray(trimString(content));
        update();
    }
    
    private void addTextArray(String[] content){
        for(String str:content){
            if(str!="" || str!=null)
                textQueue.hinzufügen(str);
        }
        update();
    }
    
    public void addTextList(String[] textList){
        for(String str:textList){
            if(str!="" || str!=null)
                addTextArray(trimString(str));
        }
        update();
    }
    
    public void clear(){
        textQueue.alleEntfernen();
        update();
    }
    
    private String[] trimString(String toSet){
        String newS = "";

        if(toSet.length()>maxZeichen){
            String[] split = toSet.split(" ");
            toSet="";
            int i=0;
            while(i<split.length){
                if((newS.length()+split[i].length()+1)<maxZeichen){
                    newS+=split[i]+" ";
                }else{
                    toSet+=newS;
                    newS="\n";
                    i--;
                }
                i++;
            }
            if(newS!="\n"){
                toSet+=newS;
            }
        }
        
        String[] check = toSet.split("\n");
        newS="";
        
        ArrayList<String> al = new ArrayList<>();
        if(check.length>maxZeilen){
            int i=0;
            for(String s:check){
                newS+=s;
                i++;
                if(i<maxZeilen){
                    newS+="\n";
                }else{
                    i=0;
                    newS+="...";
                    al.add(newS);
                    newS="...";
                }
            }
            if(newS!="..."){
                al.add(newS);
            }
            
            check = new String[al.size()];
            al.toArray(check);
        
            return check;
        }
        
        check = new String[1];
        check[0] = toSet;
        
        return check;
    }
    
    private void update(){
        if(textQueue.getFirst()!=null){
            label.setText(textQueue.getFirst().getContent());
        }else{
            label.setText("");
        }
    }
}
