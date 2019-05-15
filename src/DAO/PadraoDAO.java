package DAO;

import VO.PadraoVO;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class PadraoDAO {
    protected static Connection conn;
    protected static String tabela;

    public static ObservableList<PadraoVO> Listar() {
        ObservableList<PadraoVO> list = FXCollections.observableArrayList();
        try {
            CallableStatement stm = conn.prepareCall("SP_LIST_" + tabela);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                PadraoVO obj = new PadraoVO();
                obj.setCodigo(result.getInt("ID"));
                list.add(obj);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    abstract public boolean Inserir(PadraoVO obj);

    abstract public boolean Alterar(PadraoVO obj);

    abstract public boolean Deletar(int id);

    abstract public PadraoVO Ler(int id);
}
