package compra_devolucao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    @FXML
    ComboBox cbxFornecedores;

    @FXML
    TableView tbItens;

    @FXML
    TextField txtValorTotal;

    public void listarItens(ActionEvent actionEvent) {
        //preencher combobox
    }

    public void concluirCompra(ActionEvent actionEvent) {
        try {

            tbItens.setItems(null);
            txtValorTotal.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoProduto(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//produto//produto.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciamento de Produtos");
            stage.setScene(new Scene(loader));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirDev(ActionEvent actionEvent) {
        try {

            tbItens.setItems(null);
            txtValorTotal.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
