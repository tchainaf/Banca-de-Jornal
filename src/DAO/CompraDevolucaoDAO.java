package DAO;

import VO.CompraDevolucaoVO;
import VO.PadraoVO;
import VO.ProdutoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CompraDevolucaoDAO extends PadraoDAO {
    public int idMov = 0;
    String tabela;
    public int idFornecedor;

    public CompraDevolucaoDAO(boolean devolucao) {
        if(devolucao)
            tabela = "DEVOLUCAO";
        else
            tabela = "COMPRA";
        conn = Database.getConnection();
    }

    @Override
    public boolean Inserir(PadraoVO obj) {
        try {
            CompraDevolucaoVO compra = (CompraDevolucaoVO) obj;
            CallableStatement stm = conn.prepareCall("{call SP_INSERE_" + tabela + " (?, ?, ?, ?)}");

            stm.setInt("IDFORNECEDOR", compra.getIdFornecedor());
            stm.setDouble("PRECO_VENDA", compra.getPreco());
            stm.setDate("DATA_VENDA", new java.sql.Date(compra.getData().getDate()));
            stm.registerOutParameter("IDVENDA", Types.INTEGER);

            stm.execute();
            idMov = stm.getInt("IDVENDA");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Alterar(PadraoVO obj) {
        try {
            CompraDevolucaoVO compra = (CompraDevolucaoVO) obj;
            CallableStatement stm = conn.prepareCall("{call SP_ATUALIZA_" + tabela + " (?, ?, ?, ?)}");

            stm.setInt("ID", compra.getCodigo());
            stm.setInt("IDFORNECEDOR", compra.getIdFornecedor());
            stm.setDouble("PRECO_VENDA", compra.getPreco());
            stm.setDate("DATA_VENDA", new java.sql.Date(compra.getData().getDate()));

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
            stm.setInt("ID", id);

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
            stm.setInt("ID", id);
            ResultSet result = stm.executeQuery();
            result.next();

            CompraDevolucaoVO compra = new CompraDevolucaoVO();
            compra.setIdFornecedor(result.getInt("IDFORNECEDOR"));

            if(tabela.equalsIgnoreCase("COMPRA")){
                compra.setCodigo(result.getInt("IDCOMPRA"));
                compra.setPreco(result.getDouble("PRECO_VENDA"));
                compra.setData(result.getDate("DATA_VENDA"));
            } else {
                compra.setCodigo(result.getInt("IDDEVOLUCAO"));
                compra.setPreco(result.getDouble("PRECO_DEVOLUCAO"));
                compra.setData(result.getDate("DATA_DEVOLUCAO"));
            }
            return compra;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<PadraoVO> Listar(boolean flag) {
        ObservableList<PadraoVO> list = FXCollections.observableArrayList();
        try {
            CallableStatement stm = conn.prepareCall("{call SP_LISTA_COMPRA (?, ?)}");
            stm.setInt("IDFORNECEDOR", idFornecedor);
            stm.setString("SN_DEVOLUCAO", flag ? "S" : "N");
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

    public boolean AddItens(ObservableList<PadraoVO> list, int idVenda, boolean flag) {
        try {
            for (PadraoVO item : list) {

                ProdutoVO prod = (ProdutoVO) item;
                prod.setIdMov(idVenda);
                CallableStatement stm = conn.prepareCall("{call SP_INSERE_PRODUTO_COMPRA (?, ?, ?, ?, ?)}");

                stm.setInt("IDPRODUTO", prod.getCodigo());
                stm.setInt("IDCOMPRA", prod.getIdMov());
                stm.setInt("QUANTIDADE_COMPRA", prod.getQtde());
                stm.setDouble("PRECO_UNITARIO", prod.getPreco());
                stm.setString("SN_DEVOLUCAO", flag ? "S" : "N");

                stm.execute();
            }
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
