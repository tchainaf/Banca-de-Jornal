package produto;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Controller {
    public void novoProduto(ActionEvent actionEvent) {
    }

    public void altProduto(ActionEvent actionEvent) {
    }

    public void delProduto(ActionEvent actionEvent) {
    }

    public void irParaProduto(ActionEvent actionEvent) {
    }

    public void cancelarProduto(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirProduto(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
