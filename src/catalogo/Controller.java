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
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirFornecedores(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//fornecedor//fornecedor.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Fornecedores");
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirUsuarios(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//usuario//usuario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Usuários");
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
