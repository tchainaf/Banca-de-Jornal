package compra_devolucao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Show;

public class Controller {
    @FXML
    ComboBox cbxFornecedores;

    @FXML
    TableView tbItens;

    @FXML
    TextField txtValorTotal;

    public void listarItens(ActionEvent actionEvent) {
        try {

            //chamar DAO para listar os itens, passando flag devolucao (em estoque) ou compra (todos do fornecedor)

            tbItens.setItems(null);
            txtValorTotal.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirCompra(ActionEvent actionEvent) {
        try {

            //chamar DAO para salvar

            if(true) { //erro
                Show.MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados da compra! Tente novamente.", false);
                return;
            }

            Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados da compra foram salvos com sucesso!", false);

            tbItens.setItems(null);
            txtValorTotal.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirDev(ActionEvent actionEvent) {
        try {

            //chamar DAO para salvar

            if(true) { //erro
                Show.MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados da devolução! Tente novamente.", false);
                return;
            }

            Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados da devolução foram salvos com sucesso!", false);

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
}
