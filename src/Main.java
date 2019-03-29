/* Grupo:
 * Bruno Amorim			RA: 082160008
 * J�natas Prado		RA: 082160043
 * Let�cia Lima			RA: 082160028
 * Thain� Fran�a		RA: 082160022
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login//login.fxml"));
        primaryStage.setTitle("Banca de Jornal");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
