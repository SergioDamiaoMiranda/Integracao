package dao;

import db.DaoException;
import db.DataBaseDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import to.HistoricoTO;

public class HistoricoDAO {

    java.util.Date hoje = new java.util.Date();
    private static final String INSERT_SQL
            = "INSERT INTO hist_movimentacao "
            + "(cnpj_loja, tipo, codigo, ean, quantidade, "
            + " qual_foi_chave, observacao, nome_arquivo, data_movimentacao, hora_movimentacao) "
            + " VALUES(?,?,?,?,?,"
            + "        ?,?,?,?,?) ";
    
    public static Connection getConnection() throws SQLException {
        return DataBaseDaoFactory.getConnection();
    }

    public static void inserirHistorico(HistoricoTO hTO) throws DaoException {
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
            ps.setString(i++, hTO.getCnpj());
            ps.setString(i++, hTO.getTipo());            
            ps.setString(i++, hTO.getCodigo());
            ps.setString(i++, hTO.getEan());
            ps.setDouble(i++, hTO.getQuantidade());            
            
            ps.setString(i++, hTO.getQual_foi_chave());
            ps.setString(i++, hTO.getObservacao());
            ps.setString(i++, hTO.getArquivo());
            ps.setString(i++, dataS);
            ps.setString(i++, horaS);
            
            ps.executeUpdate();            
        } catch (SQLException e) {
             System.out.println("------------------------------ erro  " + e.getMessage());  
            throw new DaoException(e);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DaoException(ex);
            }
        }
    }

}
