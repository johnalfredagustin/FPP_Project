package utility;

import java.time.LocalDate;
import java.time.chrono.Chronology;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFX_DatePicker extends Application {

	
    @Override
    public void start(Stage primaryStage) {

        //New DataPicker init at now
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                LocalDate date = datePicker.getValue();
                System.err.println("Selected date: " + date);
            }

        });

        //reload datePicker at now
        Button btnNow = new Button("Now");
        btnNow.setVisible(false);
        btnNow.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                datePicker.setValue(LocalDate.now());
            }

        });
        
        final ToggleGroup groupChronology = new ToggleGroup();
        RadioButton optDefault = new RadioButton("default");
        optDefault.setToggleGroup(groupChronology);
        optDefault.setSelected(true);
        optDefault.setUserData(null);
        optDefault.setVisible(false);

        groupChronology.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, 
                    Toggle oldValue, Toggle newValue) {
                if (groupChronology.getSelectedToggle() != null) {
                    datePicker.setChronology(
                        (Chronology)groupChronology.getSelectedToggle().getUserData());
                
                }else{
                    datePicker.setChronology(null);
                }
            }
        });
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(optDefault, 
                        btnNow, datePicker);
        
        StackPane root = new StackPane();
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 200, 100);

        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    public static void main(String[] args) {
//        launch(args);
//    }

}