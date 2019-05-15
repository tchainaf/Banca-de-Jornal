package principal;

import DAO.*;
import VO.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller {

    @FXML
    BorderPane tela;

    @FXML
    TableView tbProdutos, tbFornecedores, tbUsuarios;

    public void abrirVenda(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//venda//venda.fxml"));
            tela.setCenter(loader);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirCatalogo(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//catalogo//produtos.fxml"));
            tela.setCenter(loader);

            ProdutoDAO prodDAO = new ProdutoDAO();
            ObservableList<PadraoVO> list = prodDAO.Listar(); //.forEach(item -> (ProdutoVO)item)
            tbProdutos.setItems(list);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirCompra(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//compra_devolucao//compra.fxml"));
            tela.setCenter(loader);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void abrirDevolucao(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//compra_devolucao//devolucao.fxml"));
            tela.setCenter(loader);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirFornecedor(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//catalogo//fornecedores.fxml"));
            tela.setCenter(loader);

            FornecedorDAO fornDAO = new FornecedorDAO();
            ObservableList<PadraoVO> list = fornDAO.Listar(); //.forEach(item -> (FornecedorVO)item)
            tbFornecedores.setItems(list);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirUser(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//catalogo//usuarios.fxml"));
            tela.setCenter(loader);

            UsuarioDAO userDAO = new UsuarioDAO();
            ObservableList<PadraoVO> list = userDAO.Listar(); //.forEach(item -> (UsuarioVO)item)
            tbUsuarios.setItems(list);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void sair(ActionEvent actionEvent) {
        try {
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//login//login.fxml"));
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
            Stage novaTela = new Stage();
            novaTela.setTitle("Banca de Jornal");
            novaTela.setScene(new Scene(loader));
            novaTela.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    Button btnVenda, btnCompra, btnDev, btnProdutos, btnForn, btnUser;

    private void corBotoes(Button btnSelecionado) {
        btnVenda.setStyle("-fx-background-color: light-grey;");
        btnCompra.setStyle("-fx-background-color: light-grey;");
        btnDev.setStyle("-fx-background-color: light-grey;");
        btnProdutos.setStyle("-fx-background-color: light-grey;");
        btnForn.setStyle("-fx-background-color: light-grey;");
        btnUser.setStyle("-fx-background-color: light-grey;");
        btnSelecionado.setStyle("-fx-background-color: grey;");
    }
}
