package usuario;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Controller {
    public void novoUser(ActionEvent actionEvent) {
    }

    public void altUser(ActionEvent actionEvent) {
    }

    public void delUser(ActionEvent actionEvent) {
    }

    public void irParaUsuario(ActionEvent actionEvent) {
    }

    public void cancelarUser(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void concluirUser(ActionEvent actionEvent) {
        try {
            Stage tela = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tela.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
