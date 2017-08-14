package university;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import university.control.StartPageController;

import java.io.IOException;
import java.sql.*;

public class MainApp extends Application {

    private static Stage primaryStage;
    private BorderPane rootPage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("University");

        initRootLayout();
        showStartPage();

//        String url = "jdbc:mysql://localhost/University";
//        String user = "root";
//        String pass = "root";
//
//        try {
//             connection = DriverManager.getConnection(url, user, pass);
//             statement = connection.createStatement();
//
//            //statement.executeUpdate("INSERT INTO students (full_name, date_of_birth, passport, id_group) VALUES ('Rick Sanchez', '22.08.1988', 'MC149', 3)");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootPage.fxml"));
            rootPage = (BorderPane) loader.load();

            Scene scene = new Scene(rootPage);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStartPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/startPage.fxml"));
            AnchorPane startPage = (AnchorPane) loader.load();
            rootPage.setCenter(startPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
