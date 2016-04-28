package sample;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    public ChoiceBox<String> choiceBox; //Choice box for kids!

    @FXML
    public TextField keyText;

    @FXML
    public TextField valueText;


    @FXML
    public Label label; //Label for balance
    public static String currentKey;


    public HashMap<Integer, String> numberkeyrelation = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> value, Number oldInt, Number newInt)
            {
                int parseint = (Integer) newInt + 1;
                System.out.print(parseint);
                System.out.print(parsekey(parseint));

                currentKey = parsekey(parseint); //So we know what key we r dealing with :)
                getInt();

            }
        });
    }
//Choice boxes don't return values, so I have to parse that.
    private String parsekey(Number n)
    {
        Integer parse = (Integer) n;

        for (Map.Entry<Integer, String> i : numberkeyrelation.entrySet())
        {
            if(i.getKey() == parse){
                return i.getValue();
            }

        }
        return null;

    }
    public void newInt(){
        int value;
        try{
            value = Integer.parseInt(valueText.getText());
        }
        catch (Exception e){
            newError();
            return; //Cause am i rite?

}
        if(!performChecks(value)){
            newError();
        }
        else{
            int relation = choiceBox.getItems().toArray().length + 1;

            numberkeyrelation.put(value,keyText.getText()); //I am ASSUMING this isn't null, however don't do this!
            choiceBox.getItems().add(keyText.getText());
            DB.getInstance().addInt(keyText.getText(),value);
        }
    }
    public void getInt(){
        int temp   = DB.getInstance().getInt(currentKey); //For some reason to string method doesn't exist;
        String newtext = String.valueOf(temp);
        label.setText(newtext);
    }
 //Note, I will not add changing yet.
    //TODO: add changing
    //Region checks

    private boolean performChecks(int i){
        if (100 >= i && i > 0){
            return true;
        }
        else return false;
    }

    private void newAlert(String title, String header, String context){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.show();
    }
   private void newError(){
       newAlert("Error!","Non existent number/conditions","Hi! Unfortunately the int and key you have entered already exist, or don't exist (if you tried to get one, or is not an int(if u added one)");
   }

}
