package dao;

import db.DaoException;
import db.DataBaseDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import to.LojaTO;

public class LojaDAO {

    java.util.Date hoje = new java.util.Date();
    private static final String INSERT_SQL
            = "INSERT INTO loja "
            + "(cnpj, codigo, nome, nome_arquivo, data_cadastro, "
            + " hora_cadastro)"
            + "VALUES(?,?,?,?,?,?)";

    public static Connection getConnection() throws SQLException {
        return DataBaseDaoFactory.getConnection();
    }

    public static void inserirLoja(LojaTO ljTO) throws DaoException {
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
            ps.setString(i++, ljTO.getCnpj());
            ps.setString(i++, ljTO.getCodigo());
            ps.setString(i++, ljTO.getNome());
            ps.setString(i++, ljTO.getArquivo());
            ps.setString(i++, dataS);
            ps.setString(i++, horaS);
            ps.executeUpdate();            
        } catch (SQLException e) {
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
