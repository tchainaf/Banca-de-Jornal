package fornecedor;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Controller {
    public void novoForn(ActionEvent actionEvent) {
    }

    public void altForn(ActionEvent actionEvent) {
    }

    public void delForn(ActionEvent actionEvent) {
    }

    public void irParaFornecedor(ActionEvent actionEvent) {
    }

    public void cancelarForn(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirForn(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
