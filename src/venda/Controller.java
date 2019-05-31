package venda;

import DAO.VendaDAO;
import VO.PadraoVO;
import VO.PagamentoVO;
import VO.ProdutoVO;
import VO.VendaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.Show;

import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML ComboBox<PagamentoVO> cbxPagamento;
    @FXML TextField txtValorTotal, txtValorPago, txtTroco;

    @FXML TableView<PadraoVO> tbItens;
    @FXML TableColumn<ProdutoVO, Integer> colQtde;
    @FXML TableColumn<ProdutoVO, String> colDescricao;
    @FXML TableColumn<ProdutoVO, Double> colPreco;
    @FXML TableColumn<ProdutoVO, Double> colPrecoTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colDescricao.setCellValueFactory(new PropertyValueFactory<ProdutoVO, String>("descricao"));
        colQtde.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("qtde"));
        colPreco.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("preco"));
        colPrecoTotal.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("valorTotal"));

        ObservableList<PagamentoVO> list = FXCollections.observableArrayList();
        list.add(new PagamentoVO("D", "Dinheiro"));
        list.add(new PagamentoVO("C", "Cartão"));
        cbxPagamento.setItems(list);
    }

    public void concluirVenda(ActionEvent actionEvent) {
        try {
            VendaVO venda = new VendaVO();
            venda.setData(Date.from(Instant.now()));
            venda.setPreco(Double.valueOf(txtValorTotal.getText()));
            venda.setTipo(cbxPagamento.getValue().getDescricao());

            VendaDAO dao = new VendaDAO();
            if(!dao.Inserir(venda)) {
                Show.MessageBox(Alert.AlertType.ERROR, "Erro ao salvar os dados da venda! Tente novamente.", false);
                return;
            }

            if(!dao.AddItens(tbItens.getItems(), dao.idMov)) {
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
            stage.showAndWait();

            //TODO: adicionar ControllerAdd.produto no tbItens apos fechar telinha
            tbItens.getItems().add(ControllerAdd.produto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void changePagamento(ActionEvent actionEvent) {
        if (cbxPagamento.getValue().getSigla().equalsIgnoreCase("D")) {
            txtValorPago.setDisable(false);
            txtTroco.setDisable(false);
        } else {
            txtValorPago.setDisable(true);
            txtTroco.setDisable(true);
        }
    }
}
