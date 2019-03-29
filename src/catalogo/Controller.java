package catalogo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    public void abrirProdutos(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//produto//produto.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Produtos");
            //stage.setMaximized(true);
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
