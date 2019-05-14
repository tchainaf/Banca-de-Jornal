package DAO;

import VO.*;
import util.DBUtil;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class ProdutoDAO extends PadraoDAO {

    public ProdutoDAO() {
        tabela = "tbPRODUTO";
        conn = DBUtil.getConnection();
    }

    @Override
    public boolean Inserir(PadraoVO obj) {
        try {
            ProdutoVO prod = (ProdutoVO) obj;
            CallableStatement stm = conn.prepareCall("SP_INSERT_" + tabela);

            stm.setString("Descricao", prod.getDescricao());
            stm.setDouble("Preco", prod.getPreco());
            stm.setInt("Fornecedor", prod.getFornecedor());

            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Alterar(PadraoVO obj) {
        try {
            ProdutoVO prod = (ProdutoVO) obj;
            CallableStatement stm = conn.prepareCall("SP_ALTER_" + tabela);

            stm.setInt("Id", prod.getCodigo());
            stm.setString("Descricao", prod.getDescricao());
            stm.setDouble("Preco", prod.getPreco());
            stm.setInt("Fornecedor", prod.getFornecedor());

            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Deletar(int id) {
        try {
            CallableStatement stm = conn.prepareCall("SP_DELETE_" + tabela);
            stm.setInt("Id", id);
            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PadraoVO Ler(int id) {
        try {
            CallableStatement stm = conn.prepareCall("SP_READ_" + tabela);
            stm.setInt("Id", id);
            ResultSet result = stm.executeQuery();

            ProdutoVO prod = new ProdutoVO();
            prod.setCodigo(result.getInt("Id"));
            prod.setDescricao(result.getString("Descricao"));
            prod.setFornecedor(result.getInt("Fornecedor"));
            prod.setPreco(result.getDouble("Preco"));

            return prod;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
