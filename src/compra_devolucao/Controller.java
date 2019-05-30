package compra_devolucao;

import DAO.CompraDevolucaoDAO;
import DAO.FornecedorDAO;
import VO.CompraDevolucaoVO;
import VO.PadraoVO;
import VO.ProdutoVO;
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
    boolean devolucao = false;
    CompraDevolucaoDAO dao;

    @FXML TextField txtValorTotal;
    @FXML ComboBox<PadraoVO> cbxFornecedor;

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

        //TODO: habilitar edição da qtde
//        colQtde.setCellFactory(new Callback<TableColumn<ProdutoVO, Integer>, TableCell<ProdutoVO, Integer>>() {
//            @Override
//            public TableCell<ProdutoVO, Integer> call(
//                    TableColumn<ProdutoVO, Integer> arg0) {
//                return Integer.();
//            }
//        });

        devolucao = principal.Controller.isDevolucao();
        dao = new CompraDevolucaoDAO(devolucao);

        FornecedorDAO fornDAO = new FornecedorDAO();
        cbxFornecedor.setItems(fornDAO.Listar(true));
    }

    public void listarItens(ActionEvent actionEvent) {
        try {
            dao.idFornecedor = cbxFornecedor.getValue().getCodigo();
            ObservableList<PadraoVO> list = dao.Listar(devolucao);
            if(list != null)
                tbItens.setItems(list);

            txtValorTotal.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean InsereRegistro(){
        CompraDevolucaoVO reg = new CompraDevolucaoVO();
        reg.setIdFornecedor(cbxFornecedor.getValue().getCodigo());
        reg.setData(Date.from(Instant.now()));
        reg.setPreco(Double.valueOf(txtValorTotal.getText()));
        return dao.Inserir(reg);
    }

    public void concluirCompra(ActionEvent actionEvent) {
        try {
            if(!InsereRegistro()) {
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
            if(!InsereRegistro()) {
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
