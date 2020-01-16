package dao;

import db.DaoException;
import db.DataBaseDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import to.HistoricoTO;
import to.MovimentacaoTO;

public class MovimentacaoDAO {

    java.util.Date hoje = new java.util.Date();
    private static final String UPDATE_SQL_ENTRADA_EAN
            = "UPDATE estoque  SET saldo = saldo + ? WHERE cnpj_loja = ? and ean = ? ";
    private static final String UPDATE_SQL_SAIDA_EAN
            = "UPDATE estoque  SET saldo = saldo - ? WHERE cnpj_loja = ? and ean = ?";
    private static final String UPDATE_SQL_INVENTARIO_EAN
            = "UPDATE estoque  SET saldo = ? WHERE cnpj_loja = ? and ean = ?";
 
    
    private static final String UPDATE_SQL_ENTRADA_CODIGO
            = "UPDATE estoque  SET saldo = saldo + ? WHERE cnpj_loja = ? and codigo = ? ";
    private static final String UPDATE_SQL_SAIDA_CODIGO
            = "UPDATE estoque  SET saldo = saldo - ? WHERE cnpj_loja = ? and codigo = ?";
    private static final String UPDATE_SQL_INVENTARIO_CODIGO
            = "UPDATE estoque  SET saldo = ? WHERE cnpj_loja = ? and codigo = ?";    
    
    public static Connection getConnection() throws SQLException {
        return DataBaseDaoFactory.getConnection();
    }

    public static void movimentacaoProduto(MovimentacaoTO mvTO, String arquivo) throws DaoException {
        // Data Hora Systema
        String data = "yyyy/MM/dd";
        String hora = "HH:mm";
        String dataS, horaS, aux;
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        dataS = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        horaS = formata.format(agora);

        try {

            
        if(mvTO.getTipo().equals("E") && mvTO.getChave().equals("E")) entradaEan(mvTO);
        if(mvTO.getTipo().equals("E") && mvTO.getChave().equals("C")) entradaCodigo(mvTO);   
        if(mvTO.getTipo().equals("S") && mvTO.getChave().equals("E")) saidaEan(mvTO);    
        if(mvTO.getTipo().equals("S") && mvTO.getChave().equals("C")) saidaCodigo(mvTO);
        if(mvTO.getTipo().equals("I") && mvTO.getChave().equals("E")) inventarioEan(mvTO);    
        if(mvTO.getTipo().equals("I") && mvTO.getChave().equals("C")) inventarioCodigo(mvTO);
        
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        
                        // Inserindo historico movimentacao
        HistoricoTO hTO = new HistoricoTO();
        hTO.setCnpj(mvTO.getCnpj());
        hTO.setTipo(mvTO.getTipo());
        hTO.setCodigo(mvTO.getCodigo());
        hTO.setEan(mvTO.getEan());
        hTO.setQuantidade(mvTO.getQuantidade());
        hTO.setQual_foi_chave(mvTO.getChave());
        hTO.setObservacao(mvTO.getObservacao());
        hTO.setArquivo(arquivo);
        hTO.setDataMovimentacao(dataS);
        hTO.setHoraMovimentacao(horaS);
        HistoricoDAO.inserirHistorico(hTO);
    }

    private static void entradaEan(MovimentacaoTO mvTO) throws SQLException {
        Connection con;
        PreparedStatement ps;         
        int i = 1;
        con = getConnection();
        ps = con.prepareStatement(UPDATE_SQL_ENTRADA_EAN);
        String aux;
        ps.setDouble(i++, mvTO.getQuantidade());
        //Condicao WHERE
        ps.setString(i++, mvTO.getCnpj());
        ps.setString(i++, mvTO.getEan());
        ps.executeUpdate();
        con.close();
        ps.close();                    
    }

    private static void entradaCodigo(MovimentacaoTO mvTO) throws SQLException {
        Connection con;
        PreparedStatement ps;         
        int i = 1;
        con = getConnection();
        ps = con.prepareStatement(UPDATE_SQL_ENTRADA_CODIGO);
        String aux;
        ps.setDouble(i++, mvTO.getQuantidade());
        //Condicao WHERE
        ps.setString(i++, mvTO.getCnpj());
        ps.setString(i++, mvTO.getCodigo());
        ps.executeUpdate();
        con.close();
        ps.close();                    
    }
    
    private static void saidaEan(MovimentacaoTO mvTO) throws SQLException {
        Connection con;
        PreparedStatement ps;         
        int i = 1;
        con = getConnection();
        ps = con.prepareStatement(UPDATE_SQL_SAIDA_EAN);
        String aux;
        ps.setDouble(i++, mvTO.getQuantidade());
        //Condicao WHERE
        ps.setString(i++, mvTO.getCnpj());
        ps.setString(i++, mvTO.getEan());
        ps.executeUpdate();
        con.close();
        ps.close();                    
    }

    private static void saidaCodigo(MovimentacaoTO mvTO) throws SQLException {
        Connection con;
        PreparedStatement ps;         
        int i = 1;
        con = getConnection();
        ps = con.prepareStatement(UPDATE_SQL_SAIDA_CODIGO);
        String aux;
        ps.setDouble(i++, mvTO.getQuantidade());
        //Condicao WHERE
        ps.setString(i++, mvTO.getCnpj());
        ps.setString(i++, mvTO.getCodigo());
        ps.executeUpdate();
        con.close();
        ps.close();                    
    }

    private static void inventarioEan(MovimentacaoTO mvTO) throws SQLException {
        Connection con;
        PreparedStatement ps;         
        int i = 1;
        con = getConnection();
        ps = con.prepareStatement(UPDATE_SQL_INVENTARIO_EAN);
        String aux;
        ps.setDouble(i++, mvTO.getQuantidade());
        //Condicao WHERE
        ps.setString(i++, mvTO.getCnpj());
        ps.setString(i++, mvTO.getEan());
        ps.executeUpdate();
        con.close();
        ps.close();                    
    }

    private static void inventarioCodigo(MovimentacaoTO mvTO) throws SQLException {
        Connection con;
        PreparedStatement ps;         
        int i = 1;
        con = getConnection();
        ps = con.prepareStatement(UPDATE_SQL_INVENTARIO_CODIGO);
        String aux;
        ps.setDouble(i++, mvTO.getQuantidade());
        //Condicao WHERE
        ps.setString(i++, mvTO.getCnpj());
        ps.setString(i++, mvTO.getCodigo());
        ps.executeUpdate();
        con.close();
        ps.close();                    
    }
    
}
