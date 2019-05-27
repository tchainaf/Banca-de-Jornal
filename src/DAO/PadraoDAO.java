package DAO;

import VO.PadraoVO;
import javafx.collections.ObservableList;

import java.sql.Connection;

public abstract class PadraoDAO {
    protected static Connection conn;
    protected static String tabela;

    abstract public ObservableList<PadraoVO> Listar(boolean flag);

    abstract public boolean Inserir(PadraoVO obj);

    abstract public boolean Alterar(PadraoVO obj);

    abstract public boolean Deletar(int id);

    abstract public PadraoVO Ler(int id);
}
