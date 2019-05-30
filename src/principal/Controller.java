package principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML BorderPane tela;
    @FXML Button btnVenda, btnCompra, btnDev, btnProdutos, btnForn, btnUser;

    static boolean dev = false;

    public static boolean isDevolucao(){
        return dev;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!login.Controller.isAdmin())
            btnUser.setDisable(true);
    }

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
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//produto//prodTabela.fxml"));
            tela.setCenter(loader);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirCompra(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//compra_devolucao//compra.fxml"));
            tela.setCenter(loader);
            dev = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void abrirDevolucao(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//compra_devolucao//devolucao.fxml"));
            tela.setCenter(loader);
            dev = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirFornecedor(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//fornecedor//fornTabela.fxml"));
            tela.setCenter(loader);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirUser(ActionEvent actionEvent) {
        try {
            corBotoes ((Button) actionEvent.getSource());
            Parent loader = FXMLLoader.load(this.getClass().getResource("..//usuario//userTabela.fxml"));
            tela.setCenter(loader);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void sair(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
            Stage novaTela = new Stage();
            novaTela.setTitle("Banca de Jornal");
            novaTela.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("..//login//login.fxml"))));
            novaTela.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

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
