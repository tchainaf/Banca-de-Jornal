package principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    public void abrirVenda(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//venda//venda.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Venda");
            //stage.setMaximized(true);
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirCatalogo(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//catalogo//catalogo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Estoque");
            //stage.setMaximized(true);
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirCompra(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//compra_devolucao//compra_devolucao.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registrar Movimentacao");
            //stage.setMaximized(true);
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirFornecedor(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//fornecedor//fornecedor.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Fornecedores");
            //stage.setMaximized(true);
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirUser(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//usuario//usuario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Usuarios");
            //stage.setMaximized(true);
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
