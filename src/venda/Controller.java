package venda;

import VO.ProdutoVO;
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

    public double valorTotal = 0;
    public ProdutoVO produto;

    public void concluirVenda(ActionEvent actionEvent) {
        try {

            //chamar DAO para salvar

            if(true) { //erro
                Show.MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados da venda! Tente novamente.", false);
                return;
            }

            Show.MessageBox(Alert.AlertType.INFORMATION, "Os dados da venda foram salvos com sucesso!", false);

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

            //preencher lista de itens se tiver nula
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
            int qtde = Integer.getInteger(txtQtde.getText().trim());
            double precoQtde = qtde * produto.getPreco();

            //adicionar item no tbItens os dados do produto

            valorTotal += precoQtde;
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
        //chamar DAO para buscar informações do item e preencher variavel produto

        produto = new ProdutoVO();

        lblEstoque.setText("Em estoque: " + produto.getQtdeEstoque());
    }
}
