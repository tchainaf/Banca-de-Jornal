package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    public void checkLogin(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//principal//principal.fxml"));
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
            Stage novaTela = new Stage();
            novaTela.setTitle("Banca de Jornal");
            novaTela.setScene(new Scene(loader));
            novaTela.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}