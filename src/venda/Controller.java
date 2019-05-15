package venda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.Show;

public class Controller {
    @FXML
    TableView tbItens;

    @FXML
    ComboBox cbxPagamento, cbxItens;

    @FXML
    TextField txtValorTotal, txtValorPago, txtTroco, txtQtde;

    @FXML
    Label lblEstoque;

    public void concluirVenda(ActionEvent actionEvent) {
        try {

            tbItens.setItems(null);
            cbxPagamento.setValue(null);
            txtValorTotal.setText(null);
            txtValorPago.setText(null);
            txtTroco.setText(null);
        } catch (Exception e) {
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

            //preencher cbxItens se tiver vazio
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void incluirItem(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();

            if(txtQtde.getText().isEmpty()){
                Show.MessageBox(Alert.AlertType.ERROR, "Informe a quantidade!", false);
                return;
            }

            Object item = cbxItens.getValue();
            int qtde = Integer.getInteger(txtQtde.getText());

            //adicionar item no tbItens
            //alterar valor total
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePagamento(ActionEvent actionEvent) {
        if (cbxPagamento.getValue() == "Dinheiro") {
            txtValorPago.setDisable(false);
            txtTroco.setDisable(false);
        } else {
            txtValorPago.setDisable(true);
            txtTroco.setDisable(true);
        }
    }

    public void changeItem(ActionEvent actionEvent) {

        lblEstoque.setText("Em estoque: "); // + item.Qtde
    }
}
