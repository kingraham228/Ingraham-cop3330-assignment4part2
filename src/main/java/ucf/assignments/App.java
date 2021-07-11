package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ToDoList.fxml")));
        Scene scene = new Scene(root);

        stage.setTitle("To Do");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args){launch(args);}
}
