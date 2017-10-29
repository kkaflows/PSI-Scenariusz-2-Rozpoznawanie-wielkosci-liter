import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import javax.xml.soap.Text;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Lenovo on 2017-10-15.
 */
public class Main extends Application {

    Stage primaryStage;
    AnchorPane rootLayout;



    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("test");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Rozpoznawanie liter");

        initRootLayout();

    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            System.out.println("a");
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            System.out.println("b");
            rootLayout = loader.load();
            System.out.println("c");

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Neuron");
            System.out.println("d");
            primaryStage.show();
            System.out.println("e");




        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
