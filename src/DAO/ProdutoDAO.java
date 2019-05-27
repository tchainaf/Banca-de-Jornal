package DAO;

import VO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO extends PadraoDAO {

    public ProdutoDAO() {
        tabela = "CATALOGO";
        conn = Database.getConnection();
    }

    @Override
    public boolean Inserir(PadraoVO obj) {
        try {
            ProdutoVO prod = (ProdutoVO) obj;
            CallableStatement stm = conn.prepareCall("{call SP_INSERE_" + tabela + " (?, ?, ?, ?)}");

            stm.setString("desc_prod", prod.getDescricao());
            stm.setDouble("val_prod", prod.getPreco());
            stm.setInt("idforn", prod.getFornecedor());
            stm.setInt("qtd_est", 0);

            stm.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Alterar(PadraoVO obj) {
        try {
            ProdutoVO prod = (ProdutoVO) obj;
            CallableStatement stm = conn.prepareCall("{call SP_ATUALIZA_" + tabela + " (?, ?, ?, ?, ?)}");

            stm.setInt("idprod", prod.getCodigo());
            stm.setString("desc_prod", prod.getDescricao());
            stm.setDouble("val_prod", prod.getPreco());
            stm.setInt("idforn", prod.getFornecedor());
            stm.setInt("qtd_est", prod.getQtdeEstoque());

            stm.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Deletar(int id) {
        try {
            CallableStatement stm = conn.prepareCall("{call SP_EXCLUI_" + tabela + " (?)}");
            stm.setInt("idprod", id);
            stm.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PadraoVO Ler(int id) {
        try {
            CallableStatement stm = conn.prepareCall("{call SP_CONSULTA_" + tabela + " (?)}");
            stm.setInt("idprod", id);
            ResultSet result = stm.executeQuery();
            result.next();

            ProdutoVO prod = new ProdutoVO();
            prod.setCodigo(result.getInt("IDPRODUTO"));
            prod.setDescricao(result.getString("DESC_PRODUTO"));
            prod.setPreco(result.getDouble("VALOR_PRODUTO"));
            prod.setFornecedor(result.getInt("IDFORNECEDOR"));
            prod.setQtdeEstoque(result.getInt("QUANTIDADE_ESTOQUE"));

            return prod;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<PadraoVO> Listar(boolean flag) {
        ObservableList<PadraoVO> list = FXCollections.observableArrayList();
        try {
            CallableStatement stm = conn.prepareCall("{call SP_LISTA_" + tabela + " (?)}");
            stm.setString("SN_ESTOQUE", flag ? "S" : "N");
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                ProdutoVO prod = new ProdutoVO();
                prod.setCodigo(result.getInt("IDPRODUTO"));
                prod.setDescricao(result.getString("DESC_PRODUTO"));
                prod.setPreco(result.getDouble("VALOR_PRODUTO"));
                prod.setFornecedor(result.getInt("IDFORNECEDOR"));
                prod.setQtdeEstoque(result.getInt("QUANTIDADE_ESTOQUE"));
                list.add(prod);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
