package dao;

import db.DaoException;
import db.DataBaseDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import to.AlterarProdutoTO;
import to.CnpjCodigoTO;
import to.HistoricoTO;
import to.ProdutoTO;

public class ProdutoDAO {

    java.util.Date hoje = new java.util.Date();
    private static final String INSERT_SQL
            = "INSERT INTO estoque "
            + "(cnpj_loja, codigo, ean, descricao, unidade, "
            + " saldo, observacao, nome_arquivo, data_cadastro, hora_cadastro, "
            + " ativo) "
            + " VALUES(?,?,?,?,?, "
            + "       ?,?,?,?,?, "
            + "       ?)";

    private static final String UPDATE_SQL_CODIGO
            = "UPDATE estoque SET "
            + "  ean = ?, descricao = ?, unidade = ?, observacao = ?, nome_arquivo = ?, data_cadastro = ?, "
            + " hora_cadastro = ? WHERE cnpj_loja = ? AND codigo = ? ";
   
    private static final String SELECT_SQL_EAN
            = "SELECT * FROM estoque WHERE cnpj_loja = ? AND ean = ? ";
    
       private static final String SELECT_SQL_CODIGO
            = "SELECT * FROM estoque WHERE cnpj_loja = ? AND codigo = ? ";
       
    public static Connection getConnection() throws SQLException {
        return DataBaseDaoFactory.getConnection();
    }

    public static void inserirProduto(ProdutoTO prTO) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;        // Data Hora Systema
        String data = "yyyy/MM/dd";
        String hora = "HH:mm";
        String dataS;
        String horaS;
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        dataS = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        horaS = formata.format(agora);

        try {
            int i = 1;
            con = getConnection();
            ps = con.prepareStatement(INSERT_SQL);
            ps.setString(i++, prTO.getCnpj());
            ps.setString(i++, prTO.getCodigo());
            ps.setString(i++, prTO.getEan());
            ps.setString(i++, prTO.getDescricao());
            ps.setString(i++, prTO.getUnidade());
            
            ps.setDouble(i++, prTO.getQuantidade());
            ps.setString(i++, prTO.getObservacao());
            ps.setString(i++, prTO.getArquivo());
            ps.setString(i++, dataS);
            ps.setString(i++, horaS);
            
            ps.setString(i++, "Sim");
            ps.executeUpdate();            
        } catch (SQLException e) {
            System.out.println("++++++" + e.getMessage());
            throw new DaoException(e);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DaoException(ex);
            }
        }
        // Inserindo historico movimentacao
        HistoricoTO hTO = new HistoricoTO();
        hTO.setCnpj(prTO.getCnpj());
        hTO.setTipo("C");
        hTO.setCodigo(prTO.getCodigo());
        hTO.setEan(prTO.getEan());
        hTO.setQuantidade(prTO.getQuantidade());
        hTO.setQual_foi_chave("");
        hTO.setObservacao(prTO.getObservacao());
        hTO.setArquivo(prTO.getArquivo());
        hTO.setDataMovimentacao(dataS);
        hTO.setHoraMovimentacao(horaS);
        HistoricoDAO.inserirHistorico(hTO);

    }

    public static void alterarProduto(AlterarProdutoTO prTO, String arquivo) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;        
        // Data Hora Systema
        String data = "yyyy/MM/dd";
        String hora = "HH:mm";
        String dataS;
        String horaS;
        String aux;
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        dataS = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        horaS = formata.format(agora);

        try {
            int i = 1;
            con = getConnection();
            ps = con.prepareStatement(UPDATE_SQL_CODIGO);

            ps.setString(i++, prTO.getNovoEan());
            ps.setString(i++, prTO.getNovaDescricao());
            ps.setString(i++, prTO.getNovaUnidade());
            ps.setString(i++, prTO.getNovaObservacao());
            ps.setString(i++, arquivo);
            ps.setString(i++, dataS);
            
            ps.setString(i++, horaS);
            ps.setString(i++, prTO.getCnpj());
            ps.setString(i++, prTO.getCodigo());
            
            ps.executeUpdate();  
            
        } catch (SQLException e) {
            System.out.println("++++++" + e.getMessage());
            throw new DaoException(e);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DaoException(ex);
            }
        }
        // Inserindo historico movimentacao
        HistoricoTO hTO = new HistoricoTO();
        hTO.setCnpj(prTO.getCnpj());
        hTO.setTipo("A");
        hTO.setCodigo(prTO.getCodigo());
        hTO.setEan(prTO.getNovoEan());
        hTO.setQuantidade(0.0);
        hTO.setQual_foi_chave(prTO.getChave());
        hTO.setObservacao(prTO.getNovaObservacao());
        hTO.setArquivo(arquivo);
        hTO.setDataMovimentacao(dataS);
        hTO.setHoraMovimentacao(horaS);
        HistoricoDAO.inserirHistorico(hTO);

    }
       
    public static boolean existeProdutoNoCnpj(CnpjCodigoTO ccTO) {
        Connection con = null;
        PreparedStatement ps = null; 
        boolean encontrou = false;
        try {
            con = getConnection();
            if (ccTO.getChave().equals("E")) {
                ps = con.prepareStatement(SELECT_SQL_EAN);
                ps.setString(1, ccTO.getCnpj());
                ps.setString(2, ccTO.getEan()); 
            } else {
                ps = con.prepareStatement(SELECT_SQL_CODIGO); 
                ps.setString(1, ccTO.getCnpj());
                ps.setString(2, ccTO.getCodigo());
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                encontrou = true;
            }
            con.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("++++++" + e.getMessage());
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                System.out.println("++++++" + ex.getMessage());
            }
        }
        return encontrou;
    }
}
