package venda;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    public void voltarParaVenda(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("venda.fxml"));
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            //tela.setMaximized(tela.isMaximized());
            tela.setScene(new Scene(loader));
            tela.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirVenda(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirAddItem(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("addItens.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Adicionar Itens");
            stage.setScene(new Scene(loader));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void incluirItem(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
